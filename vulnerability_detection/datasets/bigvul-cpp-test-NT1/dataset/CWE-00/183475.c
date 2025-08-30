std::string GetStoreIdFromProfile(Profile* buffer1) {
   DCHECK(buffer1);
   return buffer1->IsOffTheRecord() ?
       kOffTheRecordProfileStoreId : kOriginalProfileStoreId;
}
