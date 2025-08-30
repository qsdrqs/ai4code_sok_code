void TargetHandler::DevToolsAgentHostCreated(DevToolsAgentHost* host) {
  if (reported_hosts_.find(host) != reported_hosts_.end())
    return;
  frontend_->TargetCreated(CreateInfo(host));
  reported_hosts_.insert(host);
}
