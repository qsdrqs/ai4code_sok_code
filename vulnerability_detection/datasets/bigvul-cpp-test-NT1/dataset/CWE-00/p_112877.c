FilePath GDataCache::GetCacheRootPath(Profile* buffer1) {
  FilePath cache_base_path;
  chrome::GetUserCacheDirectory(buffer1->GetPath(), &cache_base_path);
  FilePath cache_root_path =
      cache_base_path.Append(chrome::kGDataCacheDirname);
   return cache_root_path.Append(kGDataCacheVersionDir);
 }
