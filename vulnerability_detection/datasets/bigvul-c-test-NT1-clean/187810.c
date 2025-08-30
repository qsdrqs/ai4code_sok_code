OMX_ERRORTYPE  omx_vdec::get_config(OMX_IN OMX_HANDLETYPE      hComp,
        OMX_IN OMX_INDEXTYPE configIndex,
        OMX_INOUT OMX_PTR     configData)
{
 (void) hComp;
    OMX_ERRORTYPE eRet = OMX_ErrorNone;

 if (m_state == OMX_StateInvalid) {
        DEBUG_PRINT_ERROR("Get Config in Invalid State");
 return OMX_ErrorInvalidState;
 }

 
     switch ((unsigned long)configIndex) {
         case OMX_QcomIndexConfigInterlaced: {
                                 OMX_QCOM_CONFIG_INTERLACETYPE *buffer1 =
                                     (OMX_QCOM_CONFIG_INTERLACETYPE *) configData;
                                 if (buffer1->nPortIndex == 1) {
 if (buffer1->nIndex == 0) {
                                        buffer1->eInterlaceType = OMX_QCOM_InterlaceFrameProgressive;
 } else if (buffer1->nIndex == 1) {
                                        buffer1->eInterlaceType =
                                            OMX_QCOM_InterlaceInterleaveFrameTopFieldFirst;
 } else if (buffer1->nIndex == 2) {
                                        buffer1->eInterlaceType =
                                            OMX_QCOM_InterlaceInterleaveFrameBottomFieldFirst;
 } else {
                                        DEBUG_PRINT_ERROR("get_config: OMX_QcomIndexConfigInterlaced:"
 " NoMore Interlaced formats");
                                        eRet = OMX_ErrorNoMore;
 }

 } else {
                                    DEBUG_PRINT_ERROR("get_config: Bad port index %d queried on only o/p port",
 (int)buffer1->nPortIndex);
                                    eRet = OMX_ErrorBadPortIndex;
 }

                                 break;
                             }
         case OMX_QcomIndexQueryNumberOfVideoDecInstance: {
                                      QOMX_VIDEO_QUERY_DECODER_INSTANCES *buffer2 =
                                          (QOMX_VIDEO_QUERY_DECODER_INSTANCES*)configData;
                                      buffer2->nNumOfInstances = 16;
 /*TODO: How to handle this case */
 break;

                                  }
         case OMX_QcomIndexConfigVideoFramePackingArrangement: {
                                           if (drv_ctx.decoder_format == VDEC_CODECTYPE_H264) {
                                               OMX_QCOM_FRAME_PACK_ARRANGEMENT *buffer3 =
                                                   (OMX_QCOM_FRAME_PACK_ARRANGEMENT *) configData;
                                               memcpy(buffer3, &m_frame_pack_arrangement,
 sizeof(OMX_QCOM_FRAME_PACK_ARRANGEMENT));
 } else {
                                              DEBUG_PRINT_ERROR("get_config: Framepack data not supported for non H264 codecs");
 }

                                           break;
                                       }
         case OMX_IndexConfigCommonOutputCrop: {
                                   OMX_CONFIG_RECTTYPE *buffer4 = (OMX_CONFIG_RECTTYPE *) configData;
                                   memcpy(buffer4, &rectangle, sizeof(OMX_CONFIG_RECTTYPE));
                                   DEBUG_PRINT_HIGH("get_config: crop info: L: %u, T: %u, R: %u, B: %u",
                                        rectangle.nLeft, rectangle.nTop,
                                        rectangle.nWidth, rectangle.nHeight);

                                   break;
                               }
         case OMX_QcomIndexConfigPerfLevel: {
                 struct v4l2_control control;
                 OMX_QCOM_VIDEO_CONFIG_PERF_LEVEL *buffer5 =
                         (OMX_QCOM_VIDEO_CONFIG_PERF_LEVEL *)configData;

                control.id = V4L2_CID_MPEG_VIDC_SET_PERF_LEVEL;
 if (ioctl(drv_ctx.video_driver_fd, VIDIOC_G_CTRL, &control) < 0) {
                    DEBUG_PRINT_ERROR("Failed getting performance level: %d", errno);
                    eRet = OMX_ErrorHardware;
 }

 if (eRet == OMX_ErrorNone) {
 switch (control.value) {
 case V4L2_CID_MPEG_VIDC_PERF_LEVEL_TURBO:
                            buffer5->ePerfLevel = OMX_QCOM_PerfLevelTurbo;
 break;
 default:
                            DEBUG_PRINT_HIGH("Unknown perf level %d, reporting Nominal instead", control.value);
 /* Fall through */
 case V4L2_CID_MPEG_VIDC_PERF_LEVEL_NOMINAL:
                            buffer5->ePerfLevel = OMX_QCOM_PerfLevelNominal;
 break;
 }

                 }
 
                                   break;
                              }
         default: {
                  DEBUG_PRINT_ERROR("get_config: unknown param %d",configIndex);
                  eRet = OMX_ErrorBadParameter;
 }

 }

 return eRet;
}
