void NavigationRequest::CommitErrorPage(
    RenderFrameHostImpl* render_frame_host,
    const base::Optional<std::string>& error_page_content) {
  UpdateRequestNavigationParamsHistory();
  frame_tree_node_->TransferNavigationRequestOwnership(render_frame_host);
  navigation_handle_->ReadyToCommitNavigation(render_frame_host, true);
  render_frame_host->FailedNavigation(common_params_, request_params_,
                                      has_stale_copy_in_cache_, net_error_,
                                      error_page_content);
}
