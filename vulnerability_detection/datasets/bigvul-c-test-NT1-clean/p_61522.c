static void RemoveResolutionFromResourceBlock(StringInfo *buffer1)
{
  register const unsigned char
    *buffer2;

  size_t
    length;

  unsigned char
    *buffer3;

  unsigned int
    count,
    long_sans;

  unsigned short
    id,
    short_sans;

  length=GetStringInfoLength(buffer1);
  if (length < 16)
    return;
  buffer3=GetStringInfoDatum(buffer1);
  for (buffer2=buffer3; (buffer2 >= buffer3) && (buffer2 < (buffer3+length-16)); )
  {
    register unsigned char
      *buffer4;

    ssize_t
      cnt;

    buffer4=(unsigned char *) buffer2;
    if (LocaleNCompare((const char *) buffer2,"8BIM",4) != 0)
      return;
    buffer2=PushLongPixel(MSBEndian,buffer2,&long_sans);
    buffer2=PushShortPixel(MSBEndian,buffer2,&id);
    buffer2=PushShortPixel(MSBEndian,buffer2,&short_sans);
    buffer2=PushLongPixel(MSBEndian,buffer2,&count);
    cnt=PSDQuantum(count);
    if (cnt < 0)
      return;
    if ((id == 0x000003ed) && (cnt < (ssize_t) (length-12)))
      {
        (void) CopyMagickMemory(buffer4,buffer4+cnt+12,length-(cnt+12)-(buffer4-buffer3));
        SetStringInfoLength(buffer1,length-(cnt+12));
        break;
      }
    buffer2+=count;
    if ((count & 0x01) != 0)
      buffer2++;
  }
}
