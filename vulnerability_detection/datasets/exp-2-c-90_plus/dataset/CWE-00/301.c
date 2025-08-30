static int kgdb_cpu_enter(struct kgdb_state *ks, struct pt_regs *regs,
		int exception_state)
{
	unsigned long flags;
	int sstep_tries = 100;
	int error;
	int cpu;
	int trace_on = 0;
	int online_cpus = num_online_cpus();
	u64 time_left;

	kgdb_info[ks->cpu].enter_kgdb++;
	kgdb_info[ks->cpu].exception_state |= exception_state;

	if (exception_state == DCPU_WANT_MASTER)
		atomic_inc(&masters_in_kgdb);
	else
		atomic_inc(&slaves_in_kgdb);

	if (arch_kgdb_ops.disable_hw_break)
		arch_kgdb_ops.disable_hw_break(regs);

acquirelock:
	rcu_read_lock();
	/*
	 * Interrupts will be restored by the 'trap return' code, except when
	 * single stepping.
	 */
	local_irq_save(flags);

	cpu = ks->cpu;
	kgdb_info[cpu].debuggerinfo = regs;
	kgdb_info[cpu].task = current;
	kgdb_info[cpu].ret_state = 0;
	kgdb_info[cpu].irq_depth = hardirq_count() >> HARDIRQ_SHIFT;

	/* Make sure the above info reaches the primary CPU */
	smp_mb();

	if (exception_level == 1) {
		if (raw_spin_trylock(&dbg_master_lock))
			atomic_xchg(&kgdb_active, cpu);
		goto cpu_master_loop;
	}

	/*
	 * CPU will loop if it is a slave or request to become a kgdb
	 * master cpu and acquire the kgdb_active lock:
	 */
	while (1) {
cpu_loop:
		if (kgdb_info[cpu].exception_state & DCPU_NEXT_MASTER) {
			kgdb_info[cpu].exception_state &= ~DCPU_NEXT_MASTER;
			goto cpu_master_loop;
		} else if (kgdb_info[cpu].exception_state & DCPU_WANT_MASTER) {
			if (raw_spin_trylock(&dbg_master_lock)) {
				atomic_xchg(&kgdb_active, cpu);
				break;
			}
		} else if (kgdb_info[cpu].exception_state & DCPU_WANT_BT) {
			dump_stack();
			kgdb_info[cpu].exception_state &= ~DCPU_WANT_BT;
		} else if (kgdb_info[cpu].exception_state & DCPU_IS_SLAVE) {
			if (!raw_spin_is_locked(&dbg_slave_lock))
				goto return_normal;
		} else {
return_normal:
			/* Return to normal operation by executing any
			 * hw breakpoint fixup.
			 */
			if (arch_kgdb_ops.correct_hw_break)
				arch_kgdb_ops.correct_hw_break();
			if (trace_on)
				tracing_on();
			kgdb_info[cpu].debuggerinfo = NULL;
			kgdb_info[cpu].task = NULL;
			kgdb_info[cpu].exception_state &=
				~(DCPU_WANT_MASTER | DCPU_IS_SLAVE);
			kgdb_info[cpu].enter_kgdb--;
			smp_mb__before_atomic();
			atomic_dec(&slaves_in_kgdb);
			dbg_touch_watchdogs();
			local_irq_restore(flags);
			rcu_read_unlock();
			return 0;
		}
		cpu_relax();
	}

	/*
	 * For single stepping, try to only enter on the processor
	 * that was single stepping.  To guard against a deadlock, the
	 * kernel will only try for the value of sstep_tries before
	 * giving up and continuing on.
	 */
	if (atomic_read(&kgdb_cpu_doing_single_step) != -1 &&
	    (kgdb_info[cpu].task &&
	     kgdb_info[cpu].task->pid != kgdb_sstep_pid) && --sstep_tries) {
		atomic_set(&kgdb_active, -1);
		raw_spin_unlock(&dbg_master_lock);
		dbg_touch_watchdogs();
		local_irq_restore(flags);
		rcu_read_unlock();

		goto acquirelock;
	}

	if (!kgdb_io_ready(1)) {
		kgdb_info[cpu].ret_state = 1;
		goto kgdb_restore; /* No I/O connection, resume the system */
	}

	/*
	 * Don't enter if we have hit a removed breakpoint.
	 */
	if (kgdb_skipexception(ks->ex_vector, ks->linux_regs))
		goto kgdb_restore;

	atomic_inc(&ignore_console_lock_warning);

	/* Call the I/O driver's pre_exception routine */
	if (dbg_io_ops->pre_exception)
		dbg_io_ops->pre_exception();

	/*
	 * Get the passive CPU lock which will hold all the non-primary
	 * CPU in a spin state while the debugger is active
	 */
	if (!kgdb_single_step)
		raw_spin_lock(&dbg_slave_lock);

#ifdef CONFIG_SMP
	/* If send_ready set, slaves are already waiting */
	if (ks->send_ready)
		atomic_set(ks->send_ready, 1);

	/* Signal the other CPUs to enter kgdb_wait() */
	else if ((!kgdb_single_step) && kgdb_do_roundup)
		kgdb_roundup_cpus();
#endif

	/*
	 * Wait for the other CPUs to be notified and be waiting for us:
	 */
	time_left = MSEC_PER_SEC;
	while (kgdb_do_roundup && --time_left &&
	       (atomic_read(&masters_in_kgdb) + atomic_read(&slaves_in_kgdb)) !=
		   online_cpus)
		udelay(1000);
	if (!time_left)
		pr_crit("Timed out waiting for secondary CPUs.\n");

	/*
	 * At this point the primary processor is completely
	 * in the debugger and all secondary CPUs are quiescent
	 */
	dbg_deactivate_sw_breakpoints();
	kgdb_single_step = 0;
	kgdb_contthread = current;
	exception_level = 0;
	trace_on = tracing_is_on();
	if (trace_on)
		tracing_off();

	while (1) {
cpu_master_loop:
		if (dbg_kdb_mode) {
			kgdb_connected = 1;
			error = kdb_stub(ks);
			if (error == -1)
				continue;
			kgdb_connected = 0;
		} else {
			error = gdb_serial_stub(ks);
		}

		if (error == DBG_PASS_EVENT) {
			dbg_kdb_mode = !dbg_kdb_mode;
		} else if (error == DBG_SWITCH_CPU_EVENT) {
			kgdb_info[dbg_switch_cpu].exception_state |=
				DCPU_NEXT_MASTER;
			goto cpu_loop;
		} else {
			kgdb_info[cpu].ret_state = error;
			break;
		}
	}

	dbg_activate_sw_breakpoints();

	/* Call the I/O driver's post_exception routine */
	if (dbg_io_ops->post_exception)
		dbg_io_ops->post_exception();

	atomic_dec(&ignore_console_lock_warning);

	if (!kgdb_single_step) {
		raw_spin_unlock(&dbg_slave_lock);
		/* Wait till all the CPUs have quit from the debugger. */
		while (kgdb_do_roundup && atomic_read(&slaves_in_kgdb))
			cpu_relax();
	}

kgdb_restore:
	if (atomic_read(&kgdb_cpu_doing_single_step) != -1) {
		int sstep_cpu = atomic_read(&kgdb_cpu_doing_single_step);
		if (kgdb_info[sstep_cpu].task)
			kgdb_sstep_pid = kgdb_info[sstep_cpu].task->pid;
		else
			kgdb_sstep_pid = 0;
	}
	if (arch_kgdb_ops.correct_hw_break)
		arch_kgdb_ops.correct_hw_break();
	if (trace_on)
		tracing_on();

	kgdb_info[cpu].debuggerinfo = NULL;
	kgdb_info[cpu].task = NULL;
	kgdb_info[cpu].exception_state &=
		~(DCPU_WANT_MASTER | DCPU_IS_SLAVE);
	kgdb_info[cpu].enter_kgdb--;
	smp_mb__before_atomic();
	atomic_dec(&masters_in_kgdb);
	/* Free kgdb_active */
	atomic_set(&kgdb_active, -1);
	raw_spin_unlock(&dbg_master_lock);
	dbg_touch_watchdogs();
	local_irq_restore(flags);
	rcu_read_unlock();

	return kgdb_info[cpu].ret_state;
}