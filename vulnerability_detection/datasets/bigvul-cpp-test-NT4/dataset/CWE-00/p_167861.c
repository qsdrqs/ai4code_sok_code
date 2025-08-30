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

void FrameLoader::SetReferrerForFrameRequest(FrameLoadRequest& frame_request) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
