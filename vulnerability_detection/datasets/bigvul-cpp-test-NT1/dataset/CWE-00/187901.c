void SoftAMR::onQueueFilled(OMX_U32 /* portIndex */) {
 List<BufferInfo *> &inQueue = getPortQueue(0);
 List<BufferInfo *> &outQueue = getPortQueue(1);

 if (mSignalledError || mOutputPortSettingsChange != NONE) {
 return;
 }

 while (!inQueue.empty() && !outQueue.empty()) {
 BufferInfo *buffer1 = *inQueue.begin();
        OMX_BUFFERHEADERTYPE *buffer2 = inInfo->mHeader;

 BufferInfo *buffer3 = *outQueue.begin();
        OMX_BUFFERHEADERTYPE *buffer4 = outInfo->mHeader;

 if (buffer2->nFlags & OMX_BUFFERFLAG_EOS) {
            inQueue.erase(inQueue.begin());
            buffer1->mOwnedByUs = false;
            notifyEmptyBufferDone(buffer2);

            buffer4->nFilledLen = 0;
            buffer4->nFlags = OMX_BUFFERFLAG_EOS;

            outQueue.erase(outQueue.begin());
            buffer3->mOwnedByUs = false;
            notifyFillBufferDone(buffer4);
 return;
 }

 if (buffer2->nOffset == 0) {
            mAnchorTimeUs = buffer2->nTimeStamp;
            mNumSamplesOutput = 0;
 }

 const uint8_t *buffer5 = inHeader->pBuffer + inHeader->nOffset;

         int32_t numBytesRead;
 
         if (mMode == MODE_NARROW) {
             numBytesRead =
                 AMRDecode(mState,
                   (Frame_Type_3GPP)((buffer5[0] >> 3) & 0x0f),
 (UWord8 *)&buffer5[1],
 reinterpret_cast<int16_t *>(buffer4->pBuffer),
                  MIME_IETF);

 if (numBytesRead == -1) {
                ALOGE("PV AMR decoder AMRDecode() call failed");

                notify(OMX_EventError, OMX_ErrorUndefined, 0, NULL);
                mSignalledError = true;

 return;
 }

 ++numBytesRead; // Include the frame type header byte.

 if (static_cast<size_t>(numBytesRead) > buffer2->nFilledLen) {

                notify(OMX_EventError, OMX_ErrorUndefined, 0, NULL);
                mSignalledError = true;


                 return;
             }
         } else {
             int16 mode = ((inputPtr[0] >> 3) & 0x0f);
 
             if (mode >= 10 && mode <= 13) {
                ALOGE("encountered illegal frame type %d in AMR WB content.",
                      mode);

                notify(OMX_EventError, OMX_ErrorUndefined, 0, NULL);
                mSignalledError = true;

 return;
 }

 size_t frameSize = getFrameSize(mode);
            CHECK_GE(buffer2->nFilledLen, frameSize);

 int16_t *buffer6 = (int16_t *)outHeader->pBuffer;

 if (mode >= 9) {
                memset(buffer6, 0, kNumSamplesPerFrameWB * sizeof(int16_t));
 } else if (mode < 9) {
 int16 frameType;
                RX_State_wb rx_state;
                mime_unsorting(
 const_cast<uint8_t *>(&buffer5[1]),
                        mInputSampleBuffer,
 &frameType, &mode, 1, &rx_state);

 int16_t numSamplesOutput;
                pvDecoder_AmrWb(
                        mode, mInputSampleBuffer,
                        buffer6,
 &numSamplesOutput,
                        mDecoderBuf, frameType, mDecoderCookie);

                CHECK_EQ((int)numSamplesOutput, (int)kNumSamplesPerFrameWB);

 for (int i = 0; i < kNumSamplesPerFrameWB; ++i) {
 /* Delete the 2 LSBs (14-bit output) */
                    buffer6[i] &= 0xfffC;
 }
 }

            numBytesRead = frameSize;
 }

        buffer2->nOffset += numBytesRead;
        buffer2->nFilledLen -= numBytesRead;

        buffer4->nFlags = 0;
        buffer4->nOffset = 0;

 if (mMode == MODE_NARROW) {
            buffer4->nFilledLen = kNumSamplesPerFrameNB * sizeof(int16_t);

            buffer4->nTimeStamp =
                mAnchorTimeUs
 + (mNumSamplesOutput * 1000000ll) / kSampleRateNB;

            mNumSamplesOutput += kNumSamplesPerFrameNB;
 } else {
            buffer4->nFilledLen = kNumSamplesPerFrameWB * sizeof(int16_t);

            buffer4->nTimeStamp =
                mAnchorTimeUs
 + (mNumSamplesOutput * 1000000ll) / kSampleRateWB;

            mNumSamplesOutput += kNumSamplesPerFrameWB;
 }

 if (buffer2->nFilledLen == 0) {
            buffer1->mOwnedByUs = false;
            inQueue.erase(inQueue.begin());
            buffer1 = NULL;
            notifyEmptyBufferDone(buffer2);
            buffer2 = NULL;
 }

        buffer3->mOwnedByUs = false;
        outQueue.erase(outQueue.begin());
        buffer3 = NULL;
        notifyFillBufferDone(buffer4);
        buffer4 = NULL;

 ++mInputBufferCount;
 }
}
