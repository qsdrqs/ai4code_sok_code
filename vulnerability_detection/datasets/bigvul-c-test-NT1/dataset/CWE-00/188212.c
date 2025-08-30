OMX_ERRORTYPE SoftAACEncoder2::internalGetParameter(
        OMX_INDEXTYPE index, OMX_PTR params) {
 switch (index) {
 case OMX_IndexParamAudioPortFormat:
 {

             OMX_AUDIO_PARAM_PORTFORMATTYPE *buffer1 =
                 (OMX_AUDIO_PARAM_PORTFORMATTYPE *)params;
 
             if (buffer1->nPortIndex > 1) {
                 return OMX_ErrorUndefined;
             }

 if (buffer1->nIndex > 0) {
 return OMX_ErrorNoMore;
 }

            buffer1->eEncoding =
 (buffer1->nPortIndex == 0)
 ? OMX_AUDIO_CodingPCM : OMX_AUDIO_CodingAAC;

 return OMX_ErrorNone;
 }

 case OMX_IndexParamAudioAac:
 {

             OMX_AUDIO_PARAM_AACPROFILETYPE *buffer2 =
                 (OMX_AUDIO_PARAM_AACPROFILETYPE *)params;
 
             if (buffer2->nPortIndex != 1) {
                 return OMX_ErrorUndefined;
             }

            buffer2->nBitRate = mBitRate;
            buffer2->nAudioBandWidth = 0;
            buffer2->nAACtools = 0;
            buffer2->nAACERtools = 0;
            buffer2->eAACProfile = (OMX_AUDIO_AACPROFILETYPE) mAACProfile;
            buffer2->eAACStreamFormat = OMX_AUDIO_AACStreamFormatMP4FF;
            buffer2->eChannelMode = OMX_AUDIO_ChannelModeStereo;

            buffer2->nChannels = mNumChannels;
            buffer2->nSampleRate = mSampleRate;
            buffer2->nFrameLength = 0;

 switch (mSBRMode) {
 case 1: // sbr on
 switch (mSBRRatio) {
 case 0:
                    buffer2->nAACtools |= OMX_AUDIO_AACToolAndroidSSBR;
                    buffer2->nAACtools |= OMX_AUDIO_AACToolAndroidDSBR;
 break;
 case 1:
                    buffer2->nAACtools |= OMX_AUDIO_AACToolAndroidSSBR;
                    buffer2->nAACtools &= ~OMX_AUDIO_AACToolAndroidDSBR;
 break;
 case 2:
                    buffer2->nAACtools &= ~OMX_AUDIO_AACToolAndroidSSBR;
                    buffer2->nAACtools |= OMX_AUDIO_AACToolAndroidDSBR;
 break;
 default:
                    ALOGE("invalid SBR ratio %d", mSBRRatio);
                    TRESPASS();
 }
 break;
 case 0: // sbr off
 case -1: // sbr undefined
                buffer2->nAACtools &= ~OMX_AUDIO_AACToolAndroidSSBR;
                buffer2->nAACtools &= ~OMX_AUDIO_AACToolAndroidDSBR;
 break;
 default:
                ALOGE("invalid SBR mode %d", mSBRMode);
                TRESPASS();
 }



 return OMX_ErrorNone;
 }

 case OMX_IndexParamAudioPcm:
 {

             OMX_AUDIO_PARAM_PCMMODETYPE *buffer3 =
                 (OMX_AUDIO_PARAM_PCMMODETYPE *)params;
 
             if (buffer3->nPortIndex != 0) {
                 return OMX_ErrorUndefined;
             }

            buffer3->eNumData = OMX_NumericalDataSigned;
            buffer3->eEndian = OMX_EndianBig;
            buffer3->bInterleaved = OMX_TRUE;
            buffer3->nBitPerSample = 16;
            buffer3->ePCMMode = OMX_AUDIO_PCMModeLinear;
            buffer3->eChannelMapping[0] = OMX_AUDIO_ChannelLF;
            buffer3->eChannelMapping[1] = OMX_AUDIO_ChannelRF;

            buffer3->nChannels = mNumChannels;
            buffer3->nSamplingRate = mSampleRate;

 return OMX_ErrorNone;
 }

 default:
 return SimpleSoftOMXComponent::internalGetParameter(index, params);
 }
}
