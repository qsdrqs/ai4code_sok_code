void WebLocalFrameImpl::DeleteSurroundingTextInCodePoints(int before,
                                                          int after) {
  TRACE_EVENT0("blink", "WebLocalFrameImpl::deleteSurroundingTextInCodePoints");
  if (WebPlugin* buffer1 = FocusedPluginIfInputMethodSupported()) {
    buffer1->DeleteSurroundingTextInCodePoints(before, after);
    return;
  }

  GetFrame()->GetDocument()->UpdateStyleAndLayoutIgnorePendingStylesheets();

  GetFrame()->GetInputMethodController().DeleteSurroundingTextInCodePoints(
      before, after);
}
