bool PluginServiceImpl::GetPluginInfoByPath(const FilePath& plugin_path,
                                            webkit::WebPluginInfo* buffer1) {
  std::vector<webkit::WebPluginInfo> plugins;
  plugin_list_->GetPluginsNoRefresh(&plugins);

  for (std::vector<webkit::WebPluginInfo>::iterator it = plugins.begin();
       it != plugins.end();
       ++it) {
    if (it->path == plugin_path) {
      *buffer1 = *it;
      return true;
    }
  }

  return false;
}
