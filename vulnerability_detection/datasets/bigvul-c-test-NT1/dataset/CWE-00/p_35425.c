static inline void preempt_conditional_sti(struct pt_regs *buffer1)
{
	preempt_count_inc();
	if (buffer1->flags & X86_EFLAGS_IF)
		local_irq_enable();
}
