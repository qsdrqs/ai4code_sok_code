#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

static void vmx_complete_atomic_exit(struct vcpu_vmx *vmx)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	u32 exit_intr_info;

	if (!(vmx->exit_reason == EXIT_REASON_MCE_DURING_VMENTRY
	      || vmx->exit_reason == EXIT_REASON_EXCEPTION_NMI))
		return;

	vmx->exit_intr_info = vmcs_read32(VM_EXIT_INTR_INFO);
	exit_intr_info = vmx->exit_intr_info;

	/* Handle machine checks before interrupts are enabled */
	if (is_machine_check(exit_intr_info))
 		kvm_machine_check();
 
 	/* We need to handle NMIs before interrupts are enabled */
	if ((exit_intr_info & INTR_INFO_INTR_TYPE_MASK) == INTR_TYPE_NMI_INTR &&
	    (exit_intr_info & INTR_INFO_VALID_MASK)) {
 		kvm_before_handle_nmi(&vmx->vcpu);
 		asm("int $2");
 		kvm_after_handle_nmi(&vmx->vcpu);
	}
}
