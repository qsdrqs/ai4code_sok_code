void ParamTraits<base::FileDescriptor>::Log(const param_type& p,
                                            std::string* buffer1) {
  if (p.auto_close) {
    buffer1->append(StringPrintf("FD(%d auto-close)", p.fd));
  } else {
    buffer1->append(StringPrintf("FD(%d)", p.fd));
  }
}
