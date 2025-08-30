EbmlElement * EbmlElement::FindNextID(IOCallback & DataStream, const EbmlCallbacks & ClassInfos, uint64 MaxDataSize)
{
  binary PossibleId[4];
  int PossibleID_Length = 0;
  binary PossibleSize[8]; // we don't support size stored in more than 64 bits
  uint32 PossibleSizeLength = 0;
  uint64 SizeUnknown;
  uint64 SizeFound;
  bool bElementFound = false;

  binary BitMask;
  uint64 aElementPosition, aSizePosition;
  while (!bElementFound) {
    // read ID
    aElementPosition = DataStream.getFilePointer();
    uint32 ReadSize = 0;
    BitMask = 1 << 7;
    while (1) {
      ReadSize += DataStream.read(&PossibleId[PossibleID_Length], 1);
      if (ReadSize == uint32(PossibleID_Length)) {
        return NULL; // no more data ?
      }
      if (++PossibleID_Length > 4) {
        return NULL; // we don't support element IDs over class D
      }
      if (PossibleId[0] & BitMask) {
        // this is the last octet of the ID
        // check wether that's the one we're looking for
/*      if (PossibleID == EBML_INFO_ID(ClassInfos)) {
          break;
        } else {
          /// \todo This element should be skipped (use a context ?)
        }*/
        bElementFound = true; /// \todo not exactly the one we're looking for
        break;
      }
      BitMask >>= 1;
    }

    // read the data size
    aSizePosition = DataStream.getFilePointer();
    uint32 _SizeLength;
    do {
      if (PossibleSizeLength >= 8)
        // Size is larger than 8 bytes
        return NULL;

      ReadSize += DataStream.read(&PossibleSize[PossibleSizeLength++], 1);
      _SizeLength = PossibleSizeLength;
      SizeFound = ReadCodedSizeValue(&PossibleSize[0], _SizeLength, SizeUnknown);
    } while (_SizeLength == 0);
  }

  EbmlElement *Result = NULL;
  EbmlId PossibleID(PossibleId, PossibleID_Length);
  if (PossibleID == EBML_INFO_ID(ClassInfos)) {
    // the element is the one expected
    Result = &EBML_INFO_CREATE(ClassInfos);
  } else {
    /// \todo find the element in the context
    Result = new (std::nothrow) EbmlDummy(PossibleID);
    if(Result == NULL)
      return NULL;
  }

  Result->SetSizeLength(PossibleSizeLength);

  Result->Size = SizeFound;

  if (!Result->ValidateSize() || (SizeFound != SizeUnknown && MaxDataSize < Result->Size)) {
    delete Result;
    return NULL;
  }

  // check if the size is not all 1s
  if (SizeFound == SizeUnknown) {
    // Size of this element is unknown
    // only possible for Master elements
    if (!Result->SetSizeInfinite()) {
      /// \todo the element is not allowed to be infinite
      delete Result;
      return NULL;
    }
  } else Result->SetSizeInfinite(false);
  Result->ElementPosition = aElementPosition;
  Result->SizePosition = aSizePosition;

  return Result;
}