long ContentEncoding::ParseContentEncodingEntry(long long start,
                                                long long size,
                                                 IMkvReader* buffer1) {
   assert(buffer1);
 
 long long pos = start;
 const long long stop = start + size;

 int compression_count = 0;
 int encryption_count = 0;

 
   while (pos < stop) {
     long long id, size;
    const long status = ParseElementHeader(pReader,
                                           pos,
                                           stop,
                                           id,
                                           size);
    if (status < 0)  //error
       return status;
 
     if (id == 0x1034)  // ContentCompression ID
 ++compression_count;


     if (id == 0x1035)  // ContentEncryption ID
       ++encryption_count;
 
    pos += size;  //consume payload
     assert(pos <= stop);
   }
 
 if (compression_count <= 0 && encryption_count <= 0)
 return -1;

 
   if (compression_count > 0) {
     compression_entries_ =
        new (std::nothrow) ContentCompression*[compression_count];
     if (!compression_entries_)
       return -1;
     compression_entries_end_ = compression_entries_;
 }

 
   if (encryption_count > 0) {
     encryption_entries_ =
        new (std::nothrow) ContentEncryption*[encryption_count];
     if (!encryption_entries_) {
      delete [] compression_entries_;
       return -1;
     }
     encryption_entries_end_ = encryption_entries_;
 }


   pos = start;
   while (pos < stop) {
     long long id, size;
    long status = ParseElementHeader(pReader,
                                     pos,
                                     stop,
                                     id,
                                     size);
    if (status < 0)  //error
       return status;
 
     if (id == 0x1031) {
      encoding_order_ = UnserializeUInt(buffer1, pos, size);
 } else if (id == 0x1032) {
      encoding_scope_ = UnserializeUInt(buffer1, pos, size);
 if (encoding_scope_ < 1)
 return -1;
 } else if (id == 0x1033) {
      encoding_type_ = UnserializeUInt(buffer1, pos, size);

     } else if (id == 0x1034) {
       ContentCompression* const buffer2 =
        new (std::nothrow) ContentCompression();
       if (!buffer2)
         return -1;
 
      status = ParseCompressionEntry(pos, size, buffer1, buffer2);
 if (status) {
 delete buffer2;
 return status;
 }
 *compression_entries_end_++ = buffer2;
 } else if (id == 0x1035) {
 ContentEncryption* const buffer3 =
 new (std::nothrow) ContentEncryption();
 if (!buffer3)
 return -1;

      status = ParseEncryptionEntry(pos, size, buffer1, buffer3);
 if (status) {
 delete buffer3;
 return status;
 }

       *encryption_entries_end_++ = buffer3;
     }
 
    pos += size;  //consume payload
     assert(pos <= stop);
   }
 
  assert(pos == stop);

   return 0;
 }
