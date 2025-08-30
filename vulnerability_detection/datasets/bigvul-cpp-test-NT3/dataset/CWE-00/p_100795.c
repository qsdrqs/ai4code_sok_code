bool HttpResponseHeaders::vulnerable_func(Time* result) const {
  return GetTimeValuedHeader("Last-Modified", result);
}
