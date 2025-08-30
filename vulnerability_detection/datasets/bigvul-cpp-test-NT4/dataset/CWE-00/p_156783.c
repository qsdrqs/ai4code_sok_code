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

void Document::ApplyFeaturePolicy(const ParsedFeaturePolicy& declared_policy) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
