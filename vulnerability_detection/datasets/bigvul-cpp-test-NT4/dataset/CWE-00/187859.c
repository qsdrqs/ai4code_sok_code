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

void CuePoint::TrackPosition::Parse(IMkvReader* pReader, long long start_,
                                     long long size_) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

   const long long stop = start_ + size_;
   long long pos = start_;

  m_track = -1;
  m_pos = -1;
  m_block = 1; // default


   while (pos < stop) {
     long len;
 
    const long long id = ReadUInt(pReader, pos, len);
    assert(id >= 0);  // TODO
    assert((pos + len) <= stop);
 
     pos += len;  // consume ID
 
     const long long size = ReadUInt(pReader, pos, len);
    assert(size >= 0);
    assert((pos + len) <= stop);
 
     pos += len;  // consume Size field
    assert((pos + size) <= stop);
 
     if (id == 0x77)  // CueTrack ID
       m_track = UnserializeUInt(pReader, pos, size);

 else if (id == 0x71) // CueClusterPos ID
      m_pos = UnserializeUInt(pReader, pos, size);

 else if (id == 0x1378) // CueBlockNumber

       m_block = UnserializeUInt(pReader, pos, size);
 
     pos += size;  // consume payload
    assert(pos <= stop);
   }
 
  assert(m_pos >= 0);
  assert(m_track > 0);
 }
