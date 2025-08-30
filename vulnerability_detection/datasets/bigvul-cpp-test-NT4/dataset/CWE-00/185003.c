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

void DriveFileStreamReader::InitializeAfterGetFileContentByPathInitialized(
    const base::FilePath& drive_file_path,
    const InitializeCompletionCallback& callback,
    FileError error,
    scoped_ptr<DriveEntryProto> entry,
    const base::FilePath& local_cache_file_path) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DCHECK(BrowserThread::CurrentlyOn(BrowserThread::IO));

  if (error != FILE_ERROR_OK) {
    callback.Run(error, scoped_ptr<DriveEntryProto>());
    return;
  }
  DCHECK(entry);

  if (local_cache_file_path.empty()) {
     reader_proxy_.reset(
         new internal::NetworkReaderProxy(
            entry->file_info().size(),
             base::Bind(&internal::CancelGetFile,
                        drive_file_system_getter_, drive_file_path)));
     callback.Run(FILE_ERROR_OK, entry.Pass());
    return;
  }

  scoped_ptr<net::FileStream> file_stream(new net::FileStream(NULL));
  net::FileStream* file_stream_ptr = file_stream.get();
  net::CompletionCallback open_completion_callback = base::Bind(
      &DriveFileStreamReader::InitializeAfterLocalFileOpen,
      weak_ptr_factory_.GetWeakPtr(),
      callback,
      base::Passed(&entry),
      base::Passed(&file_stream));
  int result = file_stream_ptr->Open(
      local_cache_file_path,
      base::PLATFORM_FILE_OPEN | base::PLATFORM_FILE_READ |
      base::PLATFORM_FILE_ASYNC,
      open_completion_callback);

  if (result == net::ERR_IO_PENDING) {
    return;
  }

  open_completion_callback.Run(result);
}
