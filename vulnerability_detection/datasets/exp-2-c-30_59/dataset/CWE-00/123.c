uint64 ReadCodedSizeValue(const binary * InBuffer, uint32 & BufferSize, uint64 & SizeUnknown)
{
  binary SizeBitMask = 1 << 7;
  uint64 Result = 0x7F;
  unsigned int SizeIdx, PossibleSizeLength = 0;
  binary PossibleSize[8];
  memset(PossibleSize, 0, 8);

  SizeUnknown = 0x7F; // the last bit is discarded when computing the size
  for (SizeIdx = 0; SizeIdx < BufferSize && SizeIdx < 8; SizeIdx++) {
    if (InBuffer[0] & (SizeBitMask >> SizeIdx)) {
      // ID found
      PossibleSizeLength = SizeIdx + 1;
      SizeBitMask >>= SizeIdx;
      for (SizeIdx = 0; SizeIdx < PossibleSizeLength; SizeIdx++) {
        PossibleSize[SizeIdx] = InBuffer[SizeIdx];
      }
      for (SizeIdx = 0; SizeIdx < PossibleSizeLength - 1; SizeIdx++) {
        Result <<= 7;
        Result |= 0xFF;
      }

      Result = 0;
      Result |= PossibleSize[0] & ~SizeBitMask;
      for (unsigned int i = 1; i<PossibleSizeLength; i++) {
        Result <<= 8;
        Result |= PossibleSize[i];
      }

      BufferSize = PossibleSizeLength;

      return Result;
    }
    SizeUnknown <<= 7;
    SizeUnknown |= 0xFF;
  }

  BufferSize = 0;
  return 0;
}