void CameraSource::signalBufferReturned(MediaBuffer *buffer1) {
    ALOGV("signalBufferReturned: %p", buffer1->data());
 Mutex::Autolock autoLock(mLock);

     for (List<sp<IMemory> >::iterator it = mFramesBeingEncoded.begin();
          it != mFramesBeingEncoded.end(); ++it) {
         if ((*it)->pointer() ==  buffer1->data()) {
             releaseOneRecordingFrame((*it));
             mFramesBeingEncoded.erase(it);
             ++mNumFramesEncoded;
            buffer1->setObserver(0);
            buffer1->release();
            mFrameCompleteCondition.signal();
 return;
 }
 }
    CHECK(!"signalBufferReturned: bogus buffer");
}
