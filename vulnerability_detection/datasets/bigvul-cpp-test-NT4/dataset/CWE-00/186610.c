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

bool BaseAudioContext::WouldTaintOrigin(const KURL& url) const {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (url.ProtocolIsData()) {
    return false;
  }
  Document* document = GetDocument();
  if (document && document->GetSecurityOrigin()) {
    return !document->GetSecurityOrigin()->CanRequest(url);
  }
  return true;
}
