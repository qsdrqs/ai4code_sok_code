void giveup_altivec_maybe_transactional(struct task_struct *tsk)
{
	/*
	 * If we are saving the current thread's registers, and the
	 * thread is in a transactional state, set the TIF_RESTORE_TM
	 * bit so that we know to restore the registers before
	 * returning to userspace.
	 */
	if (tsk == current && tsk->thread.regs &&
	    MSR_TM_ACTIVE(tsk->thread.regs->msr) &&
	    !test_thread_flag(TIF_RESTORE_TM)) {
		tsk->thread.ckpt_regs.msr = tsk->thread.regs->msr;
		set_thread_flag(TIF_RESTORE_TM);
	}

	giveup_altivec(tsk);
}