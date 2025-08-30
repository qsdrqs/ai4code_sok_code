static inline void tm_reclaim_task(struct task_struct *buffer1)
{
	/* We have to work out if we're switching from/to a task that's in the
	 * middle of a transaction.
	 *
	 * In switching we need to maintain a 2nd register state as
	 * oldtask->thread.ckpt_regs.  We tm_reclaim(oldproc); this saves the
	 * checkpointed (tbegin) state in ckpt_regs and saves the transactional
	 * (current) FPRs into oldtask->thread.transact_fpr[].
	 *
	 * We also context switch (save) TFHAR/TEXASR/TFIAR in here.
	 */
	struct thread_struct *buffer2 = &tsk->thread;

	if (!buffer2->regs)
		return;

	if (!MSR_TM_ACTIVE(buffer2->regs->msr))
		goto out_and_saveregs;

	/* Stash the original thread MSR, as giveup_fpu et al will
	 * modify it.  We hold onto it to see whether the task used
	 * FP & vector regs.  If the TIF_RESTORE_TM flag is set,
	 * tm_orig_msr is already set.
	 */
	if (!test_ti_thread_flag(task_thread_info(buffer1), TIF_RESTORE_TM))
		buffer2->tm_orig_msr = buffer2->regs->msr;

	TM_DEBUG("--- tm_reclaim on pid %d (NIP=%lx, "
		 "ccr=%lx, msr=%lx, trap=%lx)\n",
		 buffer1->pid, buffer2->regs->nip,
		 buffer2->regs->ccr, buffer2->regs->msr,
		 buffer2->regs->trap);

	tm_reclaim_thread(buffer2, task_thread_info(buffer1), TM_CAUSE_RESCHED);

	TM_DEBUG("--- tm_reclaim on pid %d complete\n",
		 buffer1->pid);

out_and_saveregs:
	/* Always save the regs here, even if a transaction's not active.
	 * This context-switches a thread's TM info SPRs.  We do it here to
	 * be consistent with the restore path (in recheckpoint) which
	 * cannot happen later in _switch().
	 */
	tm_save_sprs(buffer2);
}
