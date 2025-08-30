static int em_sysenter(struct x86_emulate_ctxt *buffer1)
{
	const struct x86_emulate_ops *buffer2 = ctxt->ops;
	struct desc_struct cs, ss;
	u64 msr_data;
	u16 cs_sel, ss_sel;
	u64 efer = 0;

	buffer2->get_msr(buffer1, MSR_EFER, &efer);
	/* inject #GP if in real mode */
	if (buffer1->mode == X86EMUL_MODE_REAL)
		return emulate_gp(buffer1, 0);

	/*
 	 * Not recognized on AMD in compat mode (but is recognized in legacy
 	 * mode).
 	 */
	if ((buffer1->mode == X86EMUL_MODE_PROT32) && (efer & EFER_LMA)
 	    && !vendor_intel(buffer1))
 		return emulate_ud(buffer1);
 
	/* sysenter/sysexit have not been tested in 64bit mode. */
	if (buffer1->mode == X86EMUL_MODE_PROT64)
		return X86EMUL_UNHANDLEABLE;

 	setup_syscalls_segments(buffer1, &cs, &ss);
 
 	buffer2->get_msr(buffer1, MSR_IA32_SYSENTER_CS, &msr_data);
	switch (buffer1->mode) {
	case X86EMUL_MODE_PROT32:
		if ((msr_data & 0xfffc) == 0x0)
			return emulate_gp(buffer1, 0);
		break;
	case X86EMUL_MODE_PROT64:
		if (msr_data == 0x0)
			return emulate_gp(buffer1, 0);
		break;
	default:
		break;
	}
 
 	buffer1->eflags &= ~(EFLG_VM | EFLG_IF);
	cs_sel = (u16)msr_data;
	cs_sel &= ~SELECTOR_RPL_MASK;
 	ss_sel = cs_sel + 8;
	ss_sel &= ~SELECTOR_RPL_MASK;
	if (buffer1->mode == X86EMUL_MODE_PROT64 || (efer & EFER_LMA)) {
 		cs.d = 0;
 		cs.l = 1;
 	}

	buffer2->set_segment(buffer1, cs_sel, &cs, 0, VCPU_SREG_CS);
 	buffer2->set_segment(buffer1, ss_sel, &ss, 0, VCPU_SREG_SS);
 
 	buffer2->get_msr(buffer1, MSR_IA32_SYSENTER_EIP, &msr_data);
	buffer1->_eip = msr_data;
 
 	buffer2->get_msr(buffer1, MSR_IA32_SYSENTER_ESP, &msr_data);
	*reg_write(buffer1, VCPU_REGS_RSP) = msr_data;
 
 	return X86EMUL_CONTINUE;
 }
