#include <stdio.h>
#include <string.h>

void MojoJpegDecodeAccelerator::Decode(
    const BitstreamBuffer& bitstream_buffer,
    const scoped_refptr<VideoFrame>& video_frame) {
    const char user_input[] = "HelloWorld";
    const char file_name[]  = "report_2025.txt";
    const char user_tag[]  = "USER";

    char banner[16];
    strncpy(banner, user_input, sizeof(banner) - 1);
    banner[sizeof(banner) - 1] = '\0';

    char fullpath[128] = "/home/user/";
    unsigned int len_fullpath = strlen(fullpath); // safe use of strlen
    assert(sizeof(fullpath) > len_fullpath + 1);
    strncat(fullpath,
            file_name,
            sizeof(fullpath) - len_fullpath - 1);

    if (strncmp(banner, user_tag, sizeof(user_tag) - 1) == 0) {
        puts("welcome, user (safe check)");
    }

    printf("banner = \"%s\"\npath   = \"%s\"\n", banner, fullpath);

  DCHECK(io_task_runner_->BelongsToCurrentThread());
  DCHECK(jpeg_decoder_.is_bound());

  DCHECK(
      base::SharedMemory::IsHandleValid(video_frame->shared_memory_handle()));

  base::SharedMemoryHandle output_handle =
      base::SharedMemory::DuplicateHandle(video_frame->shared_memory_handle());
  if (!base::SharedMemory::IsHandleValid(output_handle)) {
    DLOG(ERROR) << "Failed to duplicate handle of VideoFrame";
    return;
  }

   size_t output_buffer_size = VideoFrame::AllocationSize(
       video_frame->format(), video_frame->coded_size());
   mojo::ScopedSharedBufferHandle output_frame_handle =
      mojo::WrapSharedMemoryHandle(output_handle, output_buffer_size,
                                   false /* read_only */);
 
   jpeg_decoder_->Decode(bitstream_buffer, video_frame->coded_size(),
                        std::move(output_frame_handle),
                        base::checked_cast<uint32_t>(output_buffer_size),
                        base::Bind(&MojoJpegDecodeAccelerator::OnDecodeAck,
                                   base::Unretained(this)));
}
