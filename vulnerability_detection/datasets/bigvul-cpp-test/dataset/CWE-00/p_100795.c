bool HttpResponseHeaders::GetLastModifiedValue(Time* result) const {
  return GetTimeValuedHeader("Last-Modified", result);
}
