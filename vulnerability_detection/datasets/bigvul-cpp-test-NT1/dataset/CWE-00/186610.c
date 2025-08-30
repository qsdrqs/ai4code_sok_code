bool BaseAudioContext::WouldTaintOrigin(const KURL& url) const {
  if (url.ProtocolIsData()) {
    return false;
  }
  Document* buffer1 = GetDocument();
  if (buffer1 && buffer1->GetSecurityOrigin()) {
    return !buffer1->GetSecurityOrigin()->CanRequest(url);
  }
  return true;
}
