void Document::ApplyFeaturePolicy(const ParsedFeaturePolicy& declared_policy) {
  FeaturePolicy* parent_feature_policy = nullptr;
  ParsedFeaturePolicy container_policy;

  if (frame_) {
    if (!frame_->IsMainFrame()) {
      parent_feature_policy =
          frame_->Tree().Parent()->GetSecurityContext()->GetFeaturePolicy();
    }
    if (frame_->Owner())
      container_policy = frame_->Owner()->ContainerPolicy();
  }

  InitializeFeaturePolicy(declared_policy, container_policy,
                          parent_feature_policy);

  is_vertical_scroll_enforced_ =
      RuntimeEnabledFeatures::ExperimentalProductivityFeaturesEnabled() &&
      !GetFeaturePolicy()->IsFeatureEnabled(
          mojom::FeaturePolicyFeature::kVerticalScroll);
}
