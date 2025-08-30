FileStream::FileStream(FILE *fA, Guint startA, GBool limitedA,
		       Guint lengthA, Object *dictA):
    BaseStream(dictA, lengthA) {
  f = fA;
  start = startA;
  limited = limitedA;
  length = lengthA;
  bufPtr = bufEnd = buf;
  bufPos = start;
  savePos = 0;
  saved = gFalse;
}
