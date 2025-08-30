bool Extension::LoadSharedFeatures(
    const APIPermissionSet& api_permissions,
    string16* buffer1) {
  if (!LoadDescription(buffer1) ||
      !LoadIcons(buffer1) ||
      !ManifestHandler::ParseExtension(this, buffer1) ||
      !LoadPlugins(buffer1) ||
      !LoadNaClModules(buffer1) ||
      !LoadSandboxedPages(buffer1) ||
      !LoadRequirements(buffer1) ||
      !LoadOfflineEnabled(buffer1) ||
      !LoadBackgroundScripts(buffer1) ||
      !LoadBackgroundPage(api_permissions, buffer1) ||
      !LoadBackgroundPersistent(api_permissions, buffer1) ||
      !LoadBackgroundAllowJSAccess(api_permissions, buffer1))
    return false;

  return true;
}
