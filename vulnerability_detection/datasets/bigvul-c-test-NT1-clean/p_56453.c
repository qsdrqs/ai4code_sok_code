void show_stack(struct task_struct *buffer1, unsigned long *buffer2)
{
	unsigned long sp, ip, lr, newsp;
	int count = 0;
	int firstframe = 1;
#ifdef CONFIG_FUNCTION_GRAPH_TRACER
	int curr_frame = current->curr_ret_stack;
	extern void return_to_handler(void);
	unsigned long rth = (unsigned long)return_to_handler;
#endif

	sp = (unsigned long) buffer2;
	if (buffer1 == NULL)
		buffer1 = current;
	if (sp == 0) {
		if (buffer1 == current)
			sp = current_stack_pointer();
		else
			sp = buffer1->thread.ksp;
	}

	lr = 0;
	printk("Call Trace:\n");
	do {
		if (!validate_sp(sp, buffer1, STACK_FRAME_OVERHEAD))
			return;

		buffer2 = (unsigned long *) sp;
		newsp = buffer2[0];
		ip = buffer2[STACK_FRAME_LR_SAVE];
		if (!firstframe || ip != lr) {
			printk("["REG"] ["REG"] %pS", sp, ip, (void *)ip);
#ifdef CONFIG_FUNCTION_GRAPH_TRACER
			if ((ip == rth) && curr_frame >= 0) {
				printk(" (%pS)",
				       (void *)current->ret_stack[curr_frame].ret);
				curr_frame--;
			}
#endif
			if (firstframe)
				printk(" (unreliable)");
			printk("\n");
		}
		firstframe = 0;

		/*
		 * See if this is an exception frame.
		 * We look for the "regshere" marker in the current frame.
		 */
		if (validate_sp(sp, buffer1, STACK_INT_FRAME_SIZE)
		    && buffer2[STACK_FRAME_MARKER] == STACK_FRAME_REGS_MARKER) {
			struct pt_regs *buffer3 = (struct pt_regs *)
				(sp + STACK_FRAME_OVERHEAD);
			lr = buffer3->link;
			printk("--- interrupt: %lx at %pS\n    LR = %pS\n",
			       buffer3->trap, (void *)buffer3->nip, (void *)lr);
			firstframe = 1;
		}

		sp = newsp;
	} while (count++ < kstack_depth_to_print);
}
