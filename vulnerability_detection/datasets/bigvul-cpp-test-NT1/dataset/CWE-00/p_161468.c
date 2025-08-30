void TargetHandler::DevToolsAgentHostCreated(DevToolsAgentHost* buffer1) {
  if (reported_hosts_.find(buffer1) != reported_hosts_.end())
    return;
  frontend_->TargetCreated(CreateInfo(buffer1));
  reported_hosts_.insert(buffer1);
}
