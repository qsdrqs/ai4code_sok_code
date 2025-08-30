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

static void addToBlockedList(sqlite3 *db){
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  sqlite3 **pp;
  assertMutexHeld();
  for(
    pp=&sqlite3BlockedList;
    *pp && (*pp)->xUnlockNotify!=db->xUnlockNotify;
    pp=&(*pp)->pNextBlocked
  );
  db->pNextBlocked = *pp;
  *pp = db;
}
