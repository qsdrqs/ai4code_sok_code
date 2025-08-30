bool CreateTemporaryFileInDir(const FilePath& dir, FilePath* buffer1) {
  base::ThreadRestrictions::AssertIOAllowed();  // For call to close().
  int fd = CreateAndOpenFdForTemporaryFile(dir, temp_file);
  return ((fd >= 0) && !HANDLE_EINTR(close(fd)));
}
