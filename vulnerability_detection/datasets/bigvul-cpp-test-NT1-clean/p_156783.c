void Document::ApplyFeaturePolicy(const ParsedFeaturePolicy& declared_policy) {
  FeaturePolicy* buffer1 = nullptr;
  ParsedFeaturePolicy container_policy;

  if (frame_) {
    if (!frame_->IsMainFrame()) {
      buffer1 =
          frame_->Tree().Parent()->GetSecurityContext()->GetFeaturePolicy();
    }
    if (frame_->Owner())
      container_policy = frame_->Owner()->ContainerPolicy();
  }

  InitializeFeaturePolicy(declared_policy, container_policy,
                          buffer1);

  is_vertical_scroll_enforced_ =
      RuntimeEnabledFeatures::ExperimentalProductivityFeaturesEnabled() &&
      !GetFeaturePolicy()->IsFeatureEnabled(
          mojom::FeaturePolicyFeature::kVerticalScroll);
}
