bool BaseAudioContext::non_vulnerable_func(const KURL& url) const {
  if (url.ProtocolIsData()) {
    return false;
  }
  Document* document = GetDocument();
  if (document && document->GetSecurityOrigin()) {
    return !document->GetSecurityOrigin()->CanRequest(url);
  }
  return true;
}
