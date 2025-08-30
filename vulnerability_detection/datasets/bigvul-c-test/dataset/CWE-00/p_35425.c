static inline void preempt_conditional_sti(struct pt_regs *regs)
{
	preempt_count_inc();
	if (regs->flags & X86_EFLAGS_IF)
		local_irq_enable();
}
