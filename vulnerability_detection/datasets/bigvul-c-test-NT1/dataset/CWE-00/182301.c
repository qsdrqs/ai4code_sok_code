static char *rfc2047_decode_word(const char *buffer1, size_t len, enum ContentEncoding enc)
{
  const char *buffer2 = s;
  const char *buffer3 = s + len;

  if (enc == ENCQUOTEDPRINTABLE)
  {
    struct Buffer buf = { 0 };
    for (; buffer2 < buffer3; ++buffer2)
    {
      if (*buffer2 == '_')
      {
        mutt_buffer_addch(&buf, ' ');
      }
      else if ((*buffer2 == '=') && (!(buffer2[1] & ~127) && hexval(buffer2[1]) != -1) &&
               (!(buffer2[2] & ~127) && hexval(buffer2[2]) != -1))
      {
        mutt_buffer_addch(&buf, (hexval(buffer2[1]) << 4) | hexval(buffer2[2]));
        buffer2 += 2;
      }
      else
      {
        mutt_buffer_addch(&buf, *buffer2);
      }
    }
    mutt_buffer_addch(&buf, '\0');
    return buf.data;
   }
   else if (enc == ENCBASE64)
   {
    char *buffer4 = mutt_mem_malloc(3 * len / 4 + 1);
    int dlen = mutt_b64_decode(out, it);
     if (dlen == -1)
     {
       FREE(&buffer4);
      return NULL;
    }
    buffer4[dlen] = '\0';
    return buffer4;
  }

  assert(0); /* The enc parameter has an invalid value */
  return NULL;
}
