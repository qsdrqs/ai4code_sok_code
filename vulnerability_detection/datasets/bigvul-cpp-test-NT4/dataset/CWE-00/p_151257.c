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

String ScheduledNavigationReasonToProtocol(ScheduledNavigation::Reason reason) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  using ReasonEnum =
      protocol::Page::FrameScheduledNavigationNotification::ReasonEnum;
  switch (reason) {
    case ScheduledNavigation::Reason::kFormSubmissionGet:
      return ReasonEnum::FormSubmissionGet;
    case ScheduledNavigation::Reason::kFormSubmissionPost:
      return ReasonEnum::FormSubmissionPost;
    case ScheduledNavigation::Reason::kHttpHeaderRefresh:
      return ReasonEnum::HttpHeaderRefresh;
    case ScheduledNavigation::Reason::kFrameNavigation:
      return ReasonEnum::ScriptInitiated;
    case ScheduledNavigation::Reason::kMetaTagRefresh:
      return ReasonEnum::MetaTagRefresh;
    case ScheduledNavigation::Reason::kPageBlock:
      return ReasonEnum::PageBlockInterstitial;
    case ScheduledNavigation::Reason::kReload:
      return ReasonEnum::Reload;
    default:
      NOTREACHED();
  }
  return ReasonEnum::Reload;
}
