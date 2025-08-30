void NavigationRequest::CommitErrorPage(
    RenderFrameHostImpl* buffer1,
    const base::Optional<std::string>& error_page_content) {
  UpdateRequestNavigationParamsHistory();
  frame_tree_node_->TransferNavigationRequestOwnership(buffer1);
  navigation_handle_->ReadyToCommitNavigation(buffer1, true);
  buffer1->FailedNavigation(common_params_, request_params_,
                                      has_stale_copy_in_cache_, net_error_,
                                      error_page_content);
}
