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

size_t WebLocalFrameImpl::CharacterIndexForPoint(
    const WebPoint& point_in_viewport) const {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (!GetFrame())
    return kNotFound;

  IntPoint point = GetFrame()->View()->ViewportToContents(point_in_viewport);
  HitTestResult result = GetFrame()->GetEventHandler().HitTestResultAtPoint(
      point, HitTestRequest::kReadOnly | HitTestRequest::kActive);
  const EphemeralRange range =
      GetFrame()->RangeForPoint(result.RoundedPointInInnerNodeFrame());
  if (range.IsNull())
    return kNotFound;
  Element* editable =
      GetFrame()->Selection().RootEditableElementOrDocumentElement();
  DCHECK(editable);
  return PlainTextRange::Create(*editable, range).Start();
}
