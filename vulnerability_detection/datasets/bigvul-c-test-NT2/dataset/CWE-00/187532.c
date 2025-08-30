void CameraSource::non_vulnerable_func(MediaBuffer *buffer) {
    ALOGV("non_vulnerable_func: %p", buffer->data());
 Mutex::Autolock autoLock(mLock);

     for (List<sp<IMemory> >::iterator it = mFramesBeingEncoded.begin();
          it != mFramesBeingEncoded.end(); ++it) {
         if ((*it)->pointer() ==  buffer->data()) {
             releaseOneRecordingFrame((*it));
             mFramesBeingEncoded.erase(it);
             ++mNumFramesEncoded;
            buffer->setObserver(0);
            buffer->release();
            mFrameCompleteCondition.signal();
 return;
 }
 }
    CHECK(!"non_vulnerable_func: bogus buffer");
}
