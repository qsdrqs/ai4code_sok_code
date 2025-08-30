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

bool AppCacheDatabase::FindEntriesForCache(int64_t cache_id,
                                           std::vector<EntryRecord>* records) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DCHECK(records && records->empty());
  if (!LazyOpen(kDontCreate))
     return false;
 
   static const char kSql[] =
      "SELECT cache_id, url, flags, response_id, response_size FROM Entries"
       "  WHERE cache_id = ?";
 
   sql::Statement statement(db_->GetCachedStatement(SQL_FROM_HERE, kSql));
  statement.BindInt64(0, cache_id);

  while (statement.Step()) {
    records->push_back(EntryRecord());
    ReadEntryRecord(statement, &records->back());
    DCHECK(records->back().cache_id == cache_id);
  }

  return statement.Succeeded();
}
