MagickExport void GetMagickMemoryMethods(
  AcquireMemoryHandler *buffer1,
  ResizeMemoryHandler *buffer2,
  DestroyMemoryHandler *buffer3)
{
  assert(buffer1 != (AcquireMemoryHandler *) NULL);
  assert(buffer2 != (ResizeMemoryHandler *) NULL);
  assert(buffer3 != (DestroyMemoryHandler *) NULL);
  *buffer1=memory_methods.acquire_memory_handler;
  *buffer2=memory_methods.resize_memory_handler;
  *buffer3=memory_methods.destroy_memory_handler;
}
