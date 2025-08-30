void vulnerable_func(struct kvm *kvm)
{
	make_all_cpus_request(kvm, KVM_REQ_SCAN_IOAPIC);
}
