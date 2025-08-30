void RenderFrameImpl::ExecuteJavaScript(const base::string16& javascript) {
  JavaScriptExecuteRequest(javascript, base::DoNothing());
}
