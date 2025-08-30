MagickExport void *ResizeQuantumMemory(void *memory,const size_t count,
  const size_t quantum)
{
  size_t
    size;

  size=count*quantum;
  if ((count == 0) || (quantum != (size/count)))
    {
      memory=RelinquishMagickMemory(memory);
      errno=ENOMEM;
      return((void *) NULL);
    }
  return(ResizeMagickMemory(memory,size));
}