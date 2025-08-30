void Dispatcher::OnShouldSuspend(const std::string& extension_id,
                                 uint64_t sequence_id) {
  RenderThread::Get()->Send(
      new ExtensionHostMsg_ShouldSuspendAck(extension_id, sequence_id));
}
