void FrameLoader::ProcessFragment(const KURL& url,
                                  FrameLoadType frame_load_type,
                                  LoadStartType load_start_type) {
  LocalFrameView* buffer1 = frame_->View();
  if (!buffer1)
    return;

  Frame* buffer2 =
      url.HasFragmentIdentifier()
          ? frame_->FindUnsafeParentScrollPropagationBoundary()
          : nullptr;

  if (buffer2 && buffer2->IsLocalFrame()) {
    ToLocalFrame(buffer2)
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

  buffer1->ProcessUrlFragment(url, should_scroll_to_fragment
                                    ? LocalFrameView::kUrlFragmentScroll
                                    : LocalFrameView::kUrlFragmentDontScroll);

  if (buffer2 && buffer2->IsLocalFrame())
    ToLocalFrame(buffer2)
        ->View()
        ->SetSafeToPropagateScrollToParent(true);
}
