 void SyncManager::SyncInternal::non_vulnerable_func() {
   DCHECK(thread_checker_.CalledOnValidThread());
  connection_manager()->CheckServerReachable();
 }
