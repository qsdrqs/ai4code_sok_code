OMX_ERRORTYPE SoftAMR::internalGetParameter(
        OMX_INDEXTYPE index, OMX_PTR params) {
 switch (index) {
 case OMX_IndexParamAudioAmr:
 {

             OMX_AUDIO_PARAM_AMRTYPE *buffer1 =
                 (OMX_AUDIO_PARAM_AMRTYPE *)params;
 
             if (buffer1->nPortIndex != 0) {
                 return OMX_ErrorUndefined;
             }

            buffer1->nChannels = 1;
            buffer1->eAMRDTXMode = OMX_AUDIO_AMRDTXModeOff;
            buffer1->eAMRFrameFormat = OMX_AUDIO_AMRFrameFormatFSF;

 if (!isConfigured()) {
                buffer1->nBitRate = 0;
                buffer1->eAMRBandMode = OMX_AUDIO_AMRBandModeUnused;
 } else {
                buffer1->nBitRate = 0;
                buffer1->eAMRBandMode =
                    mMode == MODE_NARROW
 ? OMX_AUDIO_AMRBandModeNB0 : OMX_AUDIO_AMRBandModeWB0;
 }

 return OMX_ErrorNone;
 }

 case OMX_IndexParamAudioPcm:
 {

             OMX_AUDIO_PARAM_PCMMODETYPE *buffer2 =
                 (OMX_AUDIO_PARAM_PCMMODETYPE *)params;
 
             if (buffer2->nPortIndex != 1) {
                 return OMX_ErrorUndefined;
             }

            buffer2->nChannels = 1;
            buffer2->eNumData = OMX_NumericalDataSigned;
            buffer2->eEndian = OMX_EndianBig;
            buffer2->bInterleaved = OMX_TRUE;
            buffer2->nBitPerSample = 16;

            buffer2->nSamplingRate =
 (mMode == MODE_NARROW) ? kSampleRateNB : kSampleRateWB;

            buffer2->ePCMMode = OMX_AUDIO_PCMModeLinear;
            buffer2->eChannelMapping[0] = OMX_AUDIO_ChannelLF;
            buffer2->eChannelMapping[1] = OMX_AUDIO_ChannelRF;

 return OMX_ErrorNone;
 }

 default:
 return SimpleSoftOMXComponent::internalGetParameter(index, params);
 }
}
