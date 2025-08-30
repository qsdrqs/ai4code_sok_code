#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

bool Extension::LoadSharedFeatures(
    const APIPermissionSet& api_permissions,
    string16* error) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
