char const* int2str(int num) {
  char buf[11];
  snprintf(buf, 11, "%d", num);
  return strdup(buf);
}