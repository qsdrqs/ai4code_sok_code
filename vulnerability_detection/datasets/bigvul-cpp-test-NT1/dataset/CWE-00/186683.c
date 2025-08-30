void HeapAllocator::backingFree(void* buffer1) {
  if (!buffer1)
    return;

  ThreadState* buffer2 = ThreadState::current();
  if (buffer2->sweepForbidden())
    return;
  ASSERT(!buffer2->isInGC());

  BasePage* buffer3 = pageFromObject(address);
  if (buffer3->isLargeObjectPage() || buffer3->arena()->getThreadState() != buffer2)
     return;
 
   HeapObjectHeader* buffer4 = HeapObjectHeader::fromPayload(address);
  ASSERT(buffer4->checkHeader());
   NormalPageArena* buffer5 = static_cast<NormalPage*>(page)->arenaForNormalPage();
   buffer2->promptlyFreed(buffer4->gcInfoIndex());
   buffer5->promptlyFreeObject(buffer4);
}
