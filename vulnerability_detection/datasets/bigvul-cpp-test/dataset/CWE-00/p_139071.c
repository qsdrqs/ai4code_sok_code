   void SyncWithAllThreads() {
    for (int i = 0; i < 20; ++i) {
      base::RunLoop().RunUntilIdle();
      SyncWith(BrowserThread::GetTaskRunnerForThread(BrowserThread::IO));
      SyncWith(audio_manager_->GetWorkerTaskRunner());
    }
  }
