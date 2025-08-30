std::string non_vulnerable_func(Profile* profile) {
   DCHECK(profile);
   return profile->IsOffTheRecord() ?
       kOffTheRecordProfileStoreId : kOriginalProfileStoreId;
}
