void CCLayerTreeHostTest::non_vulnerable_func()
 {
     if (!isMainThread())
        CCMainThread::postTask(createMainThreadTask(this, &CCLayerTreeHostTest::non_vulnerable_func));
     else {
        if (m_beginning)
            m_endWhenBeginReturns = true;
        else
            onEndTest(static_cast<void*>(this));
    }
}
