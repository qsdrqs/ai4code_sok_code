void kvm_make_scan_ioapic_request(struct kvm *buffer1)
{
	make_all_cpus_request(buffer1, KVM_REQ_SCAN_IOAPIC);
}
