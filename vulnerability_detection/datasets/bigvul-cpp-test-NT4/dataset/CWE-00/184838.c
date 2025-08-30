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

void AppShortcutManager::OnceOffCreateShortcuts() {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  bool was_enabled = prefs_->GetBoolean(prefs::kAppShortcutsHaveBeenCreated);
#if defined(OS_MACOSX)
  bool is_now_enabled = apps::IsAppShimsEnabled();
#else
  bool is_now_enabled = true;
#endif  // defined(OS_MACOSX)
  if (was_enabled != is_now_enabled)
    prefs_->SetBoolean(prefs::kAppShortcutsHaveBeenCreated, is_now_enabled);
  if (was_enabled || !is_now_enabled)
     return;
 
   extensions::ExtensionSystem* extension_system;
  ExtensionServiceInterface* extension_service;
  if (!(extension_system = extensions::ExtensionSystem::Get(profile_)) ||
      !(extension_service = extension_system->extension_service()))
    return;

  const extensions::ExtensionSet* apps = extension_service->extensions();
  for (extensions::ExtensionSet::const_iterator it = apps->begin();
       it != apps->end(); ++it) {
    if (ShouldCreateShortcutFor(profile_, it->get()))
      CreateShortcutsInApplicationsMenu(profile_, it->get());
  }
}
