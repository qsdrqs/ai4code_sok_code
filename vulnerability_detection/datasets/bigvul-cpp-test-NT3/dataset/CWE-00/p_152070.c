void RenderFrameHostImpl::vulnerable_func() {
  navigation_request_.reset();
  same_document_navigation_request_.reset();
  navigation_requests_.clear();
}
