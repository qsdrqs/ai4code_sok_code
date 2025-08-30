void BackendImpl::CleanupCache() {
  DCHECK(background_queue_.BackgroundIsCurrentSequence());
  Trace("Backend Cleanup");
  eviction_.Stop();
  timer_.reset();

  if (init_) {
    StoreStats();
    if (data_)
      data_->header.crash = 0;

    if (user_flags_ & kNoRandom) {
      File::WaitForPendingIO(&num_pending_io_);
      DCHECK(!num_refs_);
    } else {
      File::DropPendingIO();
    }
  }
  block_files_.CloseFiles();
  FlushIndex();
  index_ = NULL;
  ptr_factory_.InvalidateWeakPtrs();
  done_.Signal();
}
