String ScheduledNavigationReasonToProtocol(ScheduledNavigation::Reason reason) {
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
