void SynchronousCompositorImpl::UpdateNeedsBeginFrames() {
  rwhva_->OnSetNeedsBeginFrames(is_active_ && renderer_needs_begin_frames_);
}
