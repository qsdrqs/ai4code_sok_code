static void vmx_complete_atomic_exit(struct vcpu_vmx *buffer1)
{
	u32 exit_intr_info;

	if (!(buffer1->exit_reason == EXIT_REASON_MCE_DURING_VMENTRY
	      || buffer1->exit_reason == EXIT_REASON_EXCEPTION_NMI))
		return;

	buffer1->exit_intr_info = vmcs_read32(VM_EXIT_INTR_INFO);
	exit_intr_info = buffer1->exit_intr_info;

	/* Handle machine checks before interrupts are enabled */
	if (is_machine_check(exit_intr_info))
 		kvm_machine_check();
 
 	/* We need to handle NMIs before interrupts are enabled */
	if ((exit_intr_info & INTR_INFO_INTR_TYPE_MASK) == INTR_TYPE_NMI_INTR &&
	    (exit_intr_info & INTR_INFO_VALID_MASK)) {
 		kvm_before_handle_nmi(&buffer1->vcpu);
 		asm("int $2");
 		kvm_after_handle_nmi(&buffer1->vcpu);
	}
}
