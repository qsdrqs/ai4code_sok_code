void RenderFrameImpl::vulnerable_func(const base::string16& javascript) {
  JavaScriptExecuteRequest(javascript, base::DoNothing());
}
