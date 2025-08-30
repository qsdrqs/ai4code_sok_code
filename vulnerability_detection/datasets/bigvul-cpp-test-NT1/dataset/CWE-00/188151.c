OMXNodeInstance::OMXNodeInstance(
        OMX *buffer1, const sp<IOMXObserver> &observer, const char *buffer2)
 : mOwner(owner),
      mNodeID(0),

       mHandle(NULL),
       mObserver(observer),
       mDying(false),
       mBufferIDCount(0)
 {
     mName = ADebug::GetDebugName(buffer2);
    DEBUG = ADebug::GetDebugLevelFromProperty(buffer2, "debug.stagefright.omx-debug");
    ALOGV("debug level for %s is %d", buffer2, DEBUG);
    DEBUG_BUMP = DEBUG;
    mNumPortBuffers[0] = 0;
    mNumPortBuffers[1] = 0;
    mDebugLevelBumpPendingBuffers[0] = 0;
    mDebugLevelBumpPendingBuffers[1] = 0;
    mMetadataType[0] = kMetadataBufferTypeInvalid;
    mMetadataType[1] = kMetadataBufferTypeInvalid;
    mSecureBufferType[0] = kSecureBufferTypeUnknown;
    mSecureBufferType[1] = kSecureBufferTypeUnknown;
    mIsSecure = AString(buffer2).endsWith(".secure");
}
