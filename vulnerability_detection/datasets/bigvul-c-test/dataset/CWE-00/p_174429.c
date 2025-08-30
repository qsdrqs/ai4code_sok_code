static int aacDecoder_drcReadCompression (
    HANDLE_FDK_BITSTREAM  bs,
 CDrcPayload *pDrcBs,
    UINT                  payloadPosition )
{
 int  bitCnt = 0;
 int  dmxLevelsPresent, extensionPresent, compressionPresent;
 int  coarseGrainTcPresent, fineGrainTcPresent;

 /* Move to the beginning of the DRC payload field */
 FDKpushBiDirectional(bs, FDKgetValidBits(bs)-payloadPosition);

 /* Sanity checks */
 if ( FDKgetValidBits(bs) < 24 ) {
 return 0;
 }

 /* Check sync word */
 if (FDKreadBits(bs, 8) != DVB_ANC_DATA_SYNC_BYTE) {
 return 0;
 }

 /* Evaluate bs_info field */ 
 if (FDKreadBits(bs, 2) != 3) { /* mpeg_audio_type */
 /* No MPEG-4 audio data */
 return 0;
 }
 FDKreadBits(bs, 2); /* dolby_surround_mode */
  pDrcBs->presMode = FDKreadBits(bs, 2); /* presentation_mode */
 FDKreadBits(bs, 1); /* stereo_downmix_mode */
 if (FDKreadBits(bs, 1) != 0) { /* reserved, set to 0 */
 return 0;
 }

 /* Evaluate ancillary_data_status */
 if (FDKreadBits(bs, 3) != 0) { /* reserved, set to 0 */
 return 0;
 }
  dmxLevelsPresent = FDKreadBits(bs, 1); /* downmixing_levels_MPEG4_status */
  extensionPresent = FDKreadBits(bs, 1); /* ancillary_data_extension_status; */
  compressionPresent   = FDKreadBits(bs, 1); /* audio_coding_mode_and_compression status */
  coarseGrainTcPresent = FDKreadBits(bs, 1); /* coarse_grain_timecode_status */
  fineGrainTcPresent   = FDKreadBits(bs, 1); /* fine_grain_timecode_status */
  bitCnt += 24;

 if (dmxLevelsPresent) {
 FDKreadBits(bs, 8); /* downmixing_levels_MPEG4 */
    bitCnt += 8;
 }

 /* audio_coding_mode_and_compression_status */
 if (compressionPresent)
 {
    UCHAR compressionOn, compressionValue;

 /* audio_coding_mode */
 if ( FDKreadBits(bs, 7) != 0 ) { /* The reserved bits shall be set to "0". */
 return 0;
 }
    compressionOn    = (UCHAR)FDKreadBits(bs, 1); /* compression_on */
    compressionValue = (UCHAR)FDKreadBits(bs, 8); /* Compression_value */
    bitCnt += 16;

 if ( compressionOn ) {
 /* A compression value is available so store the data just like MPEG DRC data */
      pDrcBs->channelData.numBands    = 1; /* One band ... */
      pDrcBs->channelData.drcValue[0] =  compressionValue; /* ... with one value ... */
      pDrcBs->channelData.bandTop[0] = (1024 >> 2) - 1; /* ... comprising the whole spectrum. */
      pDrcBs->pceInstanceTag          = -1; /* Not present */
      pDrcBs->progRefLevel            = -1; /* Not present */
      pDrcBs->channelData.drcDataType =  DVB_DRC_ANC_DATA; /* Set DRC payload type to DVB. */
 } else {
 /* No compression value available */
 /* CAUTION: It is not clearly defined by standard how to react in this situation. */
 /* Turn down the compression value to aprox. 0dB */
      pDrcBs->channelData.numBands    = 1; /* One band ... */
      pDrcBs->channelData.drcValue[0] = 0x80; /* ... with aprox. 0dB ... */
      pDrcBs->channelData.bandTop[0] = (1024 >> 2) - 1; /* ... comprising the whole spectrum. */
      pDrcBs->channelData.drcDataType =  DVB_DRC_ANC_DATA; /* Set DRC payload type to DVB. */

 /* If compression_on field is set to "0" the compression_value field shall be "0000 0000". */
 if (compressionValue != 0) {
 return 0;
 }
 }
 }

 /* Read timecodes if available just to get the right amount of bits. */
 if (coarseGrainTcPresent) {
 FDKreadBits(bs, 16); /* coarse_grain_timecode */
    bitCnt += 16;
 }
 if (fineGrainTcPresent) {
 FDKreadBits(bs, 16); /* fine_grain_timecode */
    bitCnt += 16;
 }

 /* Read extension just to get the right amount of bits. */
 if (extensionPresent) {
 int  extBits = 8;

 FDKreadBits(bs, 1); /* reserved, set to 0 */
 if (FDKreadBits(bs, 1)) extBits += 8; /* ext_downmixing_levels_status */
 if (FDKreadBits(bs, 1)) extBits += 16; /* ext_downmixing_global_gains_status */
 if (FDKreadBits(bs, 1)) extBits += 8; /* ext_downmixing_lfe_level_status */

 FDKpushFor(bs, extBits - 4); /* skip the extension payload remainder. */
    bitCnt += extBits;
 }

 return (bitCnt);
}
