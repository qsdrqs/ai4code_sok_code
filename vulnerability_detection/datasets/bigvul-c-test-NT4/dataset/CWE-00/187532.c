#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void CameraSource::signalBufferReturned(MediaBuffer *buffer) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    ALOGV("signalBufferReturned: %p", buffer->data());
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
    CHECK(!"signalBufferReturned: bogus buffer");
}
