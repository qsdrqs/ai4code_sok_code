bool PluginServiceImpl::GetPluginInfoByPath(const FilePath& plugin_path,
                                            webkit::WebPluginInfo* info) {
  std::vector<webkit::WebPluginInfo> plugins;
  plugin_list_->GetPluginsNoRefresh(&plugins);

  for (std::vector<webkit::WebPluginInfo>::iterator it = plugins.begin();
       it != plugins.end();
       ++it) {
    if (it->path == plugin_path) {
      *info = *it;
      return true;
    }
  }

  return false;
}
