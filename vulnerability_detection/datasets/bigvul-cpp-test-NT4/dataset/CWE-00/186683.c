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

void HeapAllocator::backingFree(void* address) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (!address)
    return;

  ThreadState* state = ThreadState::current();
  if (state->sweepForbidden())
    return;
  ASSERT(!state->isInGC());

  BasePage* page = pageFromObject(address);
  if (page->isLargeObjectPage() || page->arena()->getThreadState() != state)
     return;
 
   HeapObjectHeader* header = HeapObjectHeader::fromPayload(address);
  ASSERT(header->checkHeader());
   NormalPageArena* arena = static_cast<NormalPage*>(page)->arenaForNormalPage();
   state->promptlyFreed(header->gcInfoIndex());
   arena->promptlyFreeObject(header);
}
