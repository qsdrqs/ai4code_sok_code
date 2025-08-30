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

void BackendImpl::CleanupCache() {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
