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

void VideoCaptureImpl::OnBufferCreated(int32_t buffer_id,
                                       mojo::ScopedSharedBufferHandle handle) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DVLOG(1) << __func__ << " buffer_id: " << buffer_id;
  DCHECK(io_thread_checker_.CalledOnValidThread());
  DCHECK(handle.is_valid());
 
   base::SharedMemoryHandle memory_handle;
   size_t memory_size = 0;
  bool read_only_flag = false;
 
   const MojoResult result = mojo::UnwrapSharedMemoryHandle(
      std::move(handle), &memory_handle, &memory_size, &read_only_flag);
   DCHECK_EQ(MOJO_RESULT_OK, result);
   DCHECK_GT(memory_size, 0u);
 
   std::unique_ptr<base::SharedMemory> shm(
       new base::SharedMemory(memory_handle, true /* read_only */));
   if (!shm->Map(memory_size)) {
    DLOG(ERROR) << "OnBufferCreated: Map failed.";
    return;
  }
  const bool inserted =
      client_buffers_
          .insert(std::make_pair(buffer_id,
                                 new ClientBuffer(std::move(shm), memory_size)))
          .second;
  DCHECK(inserted);
}
