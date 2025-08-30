size_t WebLocalFrameImpl::CharacterIndexForPoint(
    const WebPoint& point_in_viewport) const {
  if (!GetFrame())
    return kNotFound;

  IntPoint point = GetFrame()->View()->ViewportToContents(point_in_viewport);
  HitTestResult result = GetFrame()->GetEventHandler().HitTestResultAtPoint(
      point, HitTestRequest::kReadOnly | HitTestRequest::kActive);
  const EphemeralRange range =
      GetFrame()->RangeForPoint(result.RoundedPointInInnerNodeFrame());
  if (range.IsNull())
    return kNotFound;
  Element* buffer1 =
      GetFrame()->Selection().RootEditableElementOrDocumentElement();
  DCHECK(buffer1);
  return PlainTextRange::Create(*buffer1, range).Start();
}
