static int load_state_from_tss32(struct x86_emulate_ctxt *buffer1,
				 struct tss_segment_32 *buffer2)
{
	int ret;
	u8 cpl;

	if (buffer1->ops->set_cr(buffer1, 3, buffer2->cr3))
		return emulate_gp(buffer1, 0);
	buffer1->_eip = buffer2->eip;
	buffer1->eflags = buffer2->eflags | 2;

	/* General purpose registers */
	*reg_write(buffer1, VCPU_REGS_RAX) = buffer2->eax;
	*reg_write(buffer1, VCPU_REGS_RCX) = buffer2->ecx;
	*reg_write(buffer1, VCPU_REGS_RDX) = buffer2->edx;
	*reg_write(buffer1, VCPU_REGS_RBX) = buffer2->ebx;
	*reg_write(buffer1, VCPU_REGS_RSP) = buffer2->esp;
	*reg_write(buffer1, VCPU_REGS_RBP) = buffer2->ebp;
	*reg_write(buffer1, VCPU_REGS_RSI) = buffer2->esi;
	*reg_write(buffer1, VCPU_REGS_RDI) = buffer2->edi;

	/*
	 * SDM says that segment selectors are loaded before segment
	 * descriptors.  This is important because CPL checks will
	 * use CS.RPL.
	 */
	set_segment_selector(buffer1, buffer2->ldt_selector, VCPU_SREG_LDTR);
	set_segment_selector(buffer1, buffer2->es, VCPU_SREG_ES);
	set_segment_selector(buffer1, buffer2->cs, VCPU_SREG_CS);
	set_segment_selector(buffer1, buffer2->ss, VCPU_SREG_SS);
	set_segment_selector(buffer1, buffer2->ds, VCPU_SREG_DS);
	set_segment_selector(buffer1, buffer2->fs, VCPU_SREG_FS);
	set_segment_selector(buffer1, buffer2->gs, VCPU_SREG_GS);

	/*
	 * If we're switching between Protected Mode and VM86, we need to make
	 * sure to update the mode before loading the segment descriptors so
	 * that the selectors are interpreted correctly.
	 */
	if (buffer1->eflags & X86_EFLAGS_VM) {
		buffer1->mode = X86EMUL_MODE_VM86;
		cpl = 3;
	} else {
		buffer1->mode = X86EMUL_MODE_PROT32;
		cpl = buffer2->cs & 3;
	}

	/*
 	 * Now load segment descriptors. If fault happenes at this stage
 	 * it is handled in a context of new task
 	 */
	ret = __load_segment_descriptor(buffer1, buffer2->ldt_selector, VCPU_SREG_LDTR, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->es, VCPU_SREG_ES, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->cs, VCPU_SREG_CS, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->ss, VCPU_SREG_SS, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->ds, VCPU_SREG_DS, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->fs, VCPU_SREG_FS, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
	ret = __load_segment_descriptor(buffer1, buffer2->gs, VCPU_SREG_GS, cpl, true);
 	if (ret != X86EMUL_CONTINUE)
 		return ret;
 
	return X86EMUL_CONTINUE;
}
