void kvm_make_scan_ioapic_request(struct kvm *kvm)
{
	make_all_cpus_request(kvm, KVM_REQ_SCAN_IOAPIC);
}
