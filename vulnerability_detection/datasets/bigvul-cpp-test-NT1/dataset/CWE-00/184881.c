void WebContentsImpl::DidFailProvisionalLoadWithError(
    RenderViewHost* buffer1,
    const ViewHostMsg_DidFailProvisionalLoadWithError_Params& params) {
  VLOG(1) << "Failed Provisional Load: " << params.url.possibly_invalid_spec()
          << ", error_code: " << params.error_code
          << ", error_description: " << params.error_description
          << ", is_main_frame: " << params.is_main_frame
          << ", showing_repost_interstitial: " <<
            params.showing_repost_interstitial
          << ", frame_id: " << params.frame_id;
  GURL validated_url(params.url);
  RenderProcessHost* buffer2 =
      render_view_host->GetProcess();
  RenderViewHost::FilterURL(buffer2, false, &validated_url);

  if (net::ERR_ABORTED == params.error_code) {
    if (ShowingInterstitialPage()) {
      LOG(WARNING) << "Discarding message during interstitial.";
       return;
     }
 
     render_manager_.RendererAbortedProvisionalLoad(buffer1);
   }
 
   FOR_EACH_OBSERVER(WebContentsObserver,
                     observers_,
                     DidFailProvisionalLoad(params.frame_id,
                                           params.is_main_frame,
                                           validated_url,
                                           params.error_code,
                                           params.error_description,
                                           buffer1));
}
