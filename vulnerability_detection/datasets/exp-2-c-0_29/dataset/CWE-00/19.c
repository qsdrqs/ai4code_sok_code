static void kvm_steal_time_set_preempted(struct kvm_vcpu *vcpu)
{
	struct gfn_to_hva_cache *ghc = &vcpu->arch.st.cache;
	struct kvm_steal_time __user *st;
	struct kvm_memslots *slots;
	static const u8 preempted = KVM_VCPU_PREEMPTED;

	if (!(vcpu->arch.st.msr_val & KVM_MSR_ENABLED))
		return;

	if (vcpu->arch.st.preempted)
		return;

	/* This happens on process exit */
	if (unlikely(current->mm != vcpu->kvm->mm))
		return;

	slots = kvm_memslots(vcpu->kvm);

	if (unlikely(slots->generation != ghc->generation ||
		     kvm_is_error_hva(ghc->hva) || !ghc->memslot))
		return;

	st = (struct kvm_steal_time __user *)ghc->hva;
	BUILD_BUG_ON(sizeof(st->preempted) != sizeof(preempted));

	if (!copy_to_user_nofault(&st->preempted, &preempted, sizeof(preempted)))
		vcpu->arch.st.preempted = KVM_VCPU_PREEMPTED;

	mark_page_dirty_in_slot(vcpu->kvm, ghc->memslot, gpa_to_gfn(ghc->gpa));
}