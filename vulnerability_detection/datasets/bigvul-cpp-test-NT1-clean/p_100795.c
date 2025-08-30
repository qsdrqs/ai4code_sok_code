bool HttpResponseHeaders::GetLastModifiedValue(Time* buffer1) const {
  return GetTimeValuedHeader("Last-Modified", buffer1);
}
