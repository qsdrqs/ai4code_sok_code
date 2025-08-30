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
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    root_window_->HoldMouseMoves();

    BrowserThread::PostDelayedTask(
        BrowserThread::UI, FROM_HERE,
        base::Bind(&RenderWidgetHostViewAura::ResizeLock::CancelLock,
                   weak_ptr_factory_.GetWeakPtr()),
        base::TimeDelta::FromMilliseconds(kResizeLockTimeoutMs));
  }
