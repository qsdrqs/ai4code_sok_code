void NavigatorImpl::OnBeginNavigation(
    FrameTreeNode* buffer1,
    const CommonNavigationParams& common_params,
    const BeginNavigationParams& begin_params) {
  CHECK(IsBrowserSideNavigationEnabled());
  DCHECK(buffer1);

  NavigationRequest* buffer2 =
      frame_tree_node->navigation_request();

  if (buffer2 &&
      buffer2->request_params()
          .is_history_navigation_in_new_child) {
    buffer2 = nullptr;
    buffer1->ResetNavigationRequest(false, true);
  }

  if (buffer2 &&
      (buffer2->browser_initiated() ||
       buffer2->begin_params().has_user_gesture) &&
      !begin_params.has_user_gesture) {
    RenderFrameHost* buffer3 =
        frame_tree_node->render_manager()->current_frame_host();
    buffer3->Send(
        new FrameMsg_Stop(buffer3->GetRoutingID()));
    return;
  }

  buffer1->CreatedNavigationRequest(
      NavigationRequest::CreateRendererInitiated(
          buffer1, common_params, begin_params,
          controller_->GetLastCommittedEntryIndex(),
          controller_->GetEntryCount()));
  NavigationRequest* buffer4 = frame_tree_node->navigation_request();
  if (buffer1->IsMainFrame()) {
    DidStartMainFrameNavigation(
        common_params.url,
        buffer1->current_frame_host()->GetSiteInstance(), nullptr);
    navigation_data_.reset();
  }

  NavigationEntry* buffer5 = controller_->GetPendingEntry();
  buffer4->CreateNavigationHandle(
      buffer5 ? buffer5->GetUniqueID() : 0);
  buffer4->BeginNavigation();
}
