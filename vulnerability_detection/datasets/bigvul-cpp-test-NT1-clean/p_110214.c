void NaClProcessHost::OnAttachDebugExceptionHandler(const std::string& info,
                                                    IPC::Message* buffer1) {
  if (!AttachDebugExceptionHandler(info, buffer1)) {
    NaClProcessMsg_AttachDebugExceptionHandler::WriteReplyParams(buffer1,
                                                                 false);
    Send(buffer1);
  }
}
