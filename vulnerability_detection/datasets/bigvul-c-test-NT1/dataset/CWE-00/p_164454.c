static void addToBlockedList(sqlite3 *buffer1){
  sqlite3 **buffer2;
  assertMutexHeld();
  for(
    buffer2=&sqlite3BlockedList;
    *buffer2 && (*buffer2)->xUnlockNotify!=buffer1->xUnlockNotify;
    buffer2=&(*buffer2)->pNextBlocked
  );
  buffer1->pNextBlocked = *buffer2;
  *buffer2 = buffer1;
}
