  ResizeLock(aura::RootWindow* root_window,
             const gfx::Size new_size,
             bool defer_compositor_lock)
      : root_window_(root_window),
        new_size_(new_size),
        compositor_lock_(defer_compositor_lock ?
                         NULL :
                         root_window_->compositor()->GetCompositorLock()),
        weak_ptr_factory_(this),
        defer_compositor_lock_(defer_compositor_lock) {
    root_window_->HoldMouseMoves();

    BrowserThread::PostDelayedTask(
        BrowserThread::UI, FROM_HERE,
        base::Bind(&RenderWidgetHostViewAura::ResizeLock::CancelLock,
                   weak_ptr_factory_.GetWeakPtr()),
        base::TimeDelta::FromMilliseconds(kResizeLockTimeoutMs));
  }
