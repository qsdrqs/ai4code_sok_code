bool Extension::LoadSharedFeatures(
    const APIPermissionSet& api_permissions,
    string16* error) {
  if (!LoadDescription(error) ||
      !LoadIcons(error) ||
      !ManifestHandler::ParseExtension(this, error) ||
      !LoadPlugins(error) ||
      !LoadNaClModules(error) ||
      !LoadSandboxedPages(error) ||
      !LoadRequirements(error) ||
      !LoadOfflineEnabled(error) ||
      !LoadBackgroundScripts(error) ||
      !LoadBackgroundPage(api_permissions, error) ||
      !LoadBackgroundPersistent(api_permissions, error) ||
      !LoadBackgroundAllowJSAccess(api_permissions, error))
    return false;

  return true;
}
