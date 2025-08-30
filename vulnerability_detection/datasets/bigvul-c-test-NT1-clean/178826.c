 static int em_syscall(struct x86_emulate_ctxt *buffer1)
 {
 	struct x86_emulate_ops *buffer2 = ctxt->ops;
	struct desc_struct cs, ss;
	u64 msr_data;
	u16 cs_sel, ss_sel;
	u64 efer = 0;

	/* syscall is not available in real mode */
	if (buffer1->mode == X86EMUL_MODE_REAL ||
 	    buffer1->mode == X86EMUL_MODE_VM86)
 		return emulate_ud(buffer1);
 
 	buffer2->get_msr(buffer1, MSR_EFER, &efer);
 	setup_syscalls_segments(buffer1, &cs, &ss);
 
 	buffer2->get_msr(buffer1, MSR_STAR, &msr_data);
 	msr_data >>= 32;
 	cs_sel = (u16)(msr_data & 0xfffc);
	ss_sel = (u16)(msr_data + 8);

	if (efer & EFER_LMA) {
		cs.d = 0;
		cs.l = 1;
	}
	buffer2->set_segment(buffer1, cs_sel, &cs, 0, VCPU_SREG_CS);
	buffer2->set_segment(buffer1, ss_sel, &ss, 0, VCPU_SREG_SS);

	buffer1->regs[VCPU_REGS_RCX] = buffer1->_eip;
	if (efer & EFER_LMA) {
#ifdef CONFIG_X86_64
		buffer1->regs[VCPU_REGS_R11] = buffer1->eflags & ~EFLG_RF;

		buffer2->get_msr(buffer1,
			     buffer1->mode == X86EMUL_MODE_PROT64 ?
			     MSR_LSTAR : MSR_CSTAR, &msr_data);
		buffer1->_eip = msr_data;

		buffer2->get_msr(buffer1, MSR_SYSCALL_MASK, &msr_data);
		buffer1->eflags &= ~(msr_data | EFLG_RF);
#endif
	} else {
		/* legacy mode */
		buffer2->get_msr(buffer1, MSR_STAR, &msr_data);
		buffer1->_eip = (u32)msr_data;

		buffer1->eflags &= ~(EFLG_VM | EFLG_IF | EFLG_RF);
	}

	return X86EMUL_CONTINUE;
}
