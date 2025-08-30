long arch_ptrace(struct task_struct *buffer1, long request,
		 unsigned long addr, unsigned long data)
{
	int ret;
	unsigned long __user *buffer2 = (unsigned long __user *) data;

	switch (request) {
		case PTRACE_PEEKUSR:
			ret = ptrace_read_user(buffer1, addr, buffer2);
			break;

		case PTRACE_POKEUSR:
			ret = ptrace_write_user(buffer1, addr, data);
			break;

		case PTRACE_GETREGS:
			ret = copy_regset_to_user(buffer1,
						  &user_arm_view, REGSET_GPR,
						  0, sizeof(struct pt_regs),
						  buffer2);
			break;

		case PTRACE_SETREGS:
			ret = copy_regset_from_user(buffer1,
						    &user_arm_view, REGSET_GPR,
						    0, sizeof(struct pt_regs),
						    buffer2);
			break;

		case PTRACE_GETFPREGS:
			ret = copy_regset_to_user(buffer1,
						  &user_arm_view, REGSET_FPR,
						  0, sizeof(union fp_state),
						  buffer2);
			break;

		case PTRACE_SETFPREGS:
			ret = copy_regset_from_user(buffer1,
						    &user_arm_view, REGSET_FPR,
						    0, sizeof(union fp_state),
						    buffer2);
			break;

#ifdef CONFIG_IWMMXT
		case PTRACE_GETWMMXREGS:
			ret = ptrace_getwmmxregs(buffer1, buffer2);
			break;

		case PTRACE_SETWMMXREGS:
			ret = ptrace_setwmmxregs(buffer1, buffer2);
			break;
 #endif
 
 		case PTRACE_GET_THREAD_AREA:
			ret = put_user(task_thread_info(buffer1)->tp_value,
 				       buffer2);
 			break;
 
		case PTRACE_SET_SYSCALL:
			task_thread_info(buffer1)->syscall = data;
			ret = 0;
			break;

#ifdef CONFIG_CRUNCH
		case PTRACE_GETCRUNCHREGS:
			ret = ptrace_getcrunchregs(buffer1, buffer2);
			break;

		case PTRACE_SETCRUNCHREGS:
			ret = ptrace_setcrunchregs(buffer1, buffer2);
			break;
#endif

#ifdef CONFIG_VFP
		case PTRACE_GETVFPREGS:
			ret = copy_regset_to_user(buffer1,
						  &user_arm_view, REGSET_VFP,
						  0, ARM_VFPREGS_SIZE,
						  buffer2);
			break;

		case PTRACE_SETVFPREGS:
			ret = copy_regset_from_user(buffer1,
						    &user_arm_view, REGSET_VFP,
						    0, ARM_VFPREGS_SIZE,
						    buffer2);
			break;
#endif

#ifdef CONFIG_HAVE_HW_BREAKPOINT
		case PTRACE_GETHBPREGS:
			if (ptrace_get_breakpoints(buffer1) < 0)
				return -ESRCH;

			ret = ptrace_gethbpregs(buffer1, addr,
						(unsigned long __user *)data);
			ptrace_put_breakpoints(buffer1);
			break;
		case PTRACE_SETHBPREGS:
			if (ptrace_get_breakpoints(buffer1) < 0)
				return -ESRCH;

			ret = ptrace_sethbpregs(buffer1, addr,
						(unsigned long __user *)data);
			ptrace_put_breakpoints(buffer1);
			break;
#endif

		default:
			ret = ptrace_request(buffer1, request, addr, data);
			break;
	}

	return ret;
}
