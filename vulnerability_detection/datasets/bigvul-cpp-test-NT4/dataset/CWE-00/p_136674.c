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

void FrameLoader::ProcessFragment(const KURL& url,
                                  FrameLoadType frame_load_type,
                                  LoadStartType load_start_type) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  LocalFrameView* view = frame_->View();
  if (!view)
    return;

  Frame* boundary_frame =
      url.HasFragmentIdentifier()
          ? frame_->FindUnsafeParentScrollPropagationBoundary()
          : nullptr;

  if (boundary_frame && boundary_frame->IsLocalFrame()) {
    ToLocalFrame(boundary_frame)
        ->View()
        ->SetSafeToPropagateScrollToParent(false);
  }

  bool should_scroll_to_fragment =
      (load_start_type == kNavigationWithinSameDocument &&
       !IsBackForwardLoadType(frame_load_type)) ||
      (!GetDocumentLoader()->GetInitialScrollState().did_restore_from_history &&
       !(GetDocumentLoader()->GetHistoryItem() &&
         GetDocumentLoader()->GetHistoryItem()->ScrollRestorationType() ==
             kScrollRestorationManual));

  view->ProcessUrlFragment(url, should_scroll_to_fragment
                                    ? LocalFrameView::kUrlFragmentScroll
                                    : LocalFrameView::kUrlFragmentDontScroll);

  if (boundary_frame && boundary_frame->IsLocalFrame())
    ToLocalFrame(boundary_frame)
        ->View()
        ->SetSafeToPropagateScrollToParent(true);
}
