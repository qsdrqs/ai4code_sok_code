void LayerTreeHost::AnimateLayers(base::TimeTicks monotonic_time) {
  std::unique_ptr<MutatorEvents> events = mutator_host_->CreateEvents();

  if (mutator_host_->TickAnimations(monotonic_time))
    mutator_host_->UpdateAnimationState(true, events.get());

  if (!events->IsEmpty())
    property_trees_.needs_rebuild = true;
}
