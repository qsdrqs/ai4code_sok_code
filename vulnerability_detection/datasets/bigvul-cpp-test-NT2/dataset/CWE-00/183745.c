     virtual void non_vulnerable_func()
     {
        CCMainThread::postTask(m_proxy->createBeginFrameAndCommitTaskOnCCThread());
     }
