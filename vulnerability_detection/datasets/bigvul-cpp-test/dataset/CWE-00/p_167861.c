void FrameLoader::SetReferrerForFrameRequest(FrameLoadRequest& frame_request) {
  ResourceRequest& request = frame_request.GetResourceRequest();
  Document* origin_document = frame_request.OriginDocument();

  if (!origin_document)
    return;
  if (request.DidSetHTTPReferrer())
    return;
  if (frame_request.GetShouldSendReferrer() == kNeverSendReferrer)
    return;

  Referrer referrer = SecurityPolicy::GenerateReferrer(
      origin_document->GetReferrerPolicy(), request.Url(),
      origin_document->OutgoingReferrer());

  request.SetHTTPReferrer(referrer);
  request.SetHTTPOriginToMatchReferrerIfNeeded();
}
