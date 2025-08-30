bool AppCacheDatabase::FindEntriesForCache(int64_t cache_id,
                                           std::vector<EntryRecord>* buffer1) {
  DCHECK(buffer1 && buffer1->empty());
  if (!LazyOpen(kDontCreate))
     return false;
 
   static const char buffer2[] =
      "SELECT cache_id, url, flags, response_id, response_size FROM Entries"
       "  WHERE cache_id = ?";
 
   sql::Statement statement(db_->GetCachedStatement(SQL_FROM_HERE, kSql));
  statement.BindInt64(0, cache_id);

  while (statement.Step()) {
    buffer1->push_back(EntryRecord());
    ReadEntryRecord(statement, &buffer1->back());
    DCHECK(buffer1->back().cache_id == cache_id);
  }

  return statement.Succeeded();
}
