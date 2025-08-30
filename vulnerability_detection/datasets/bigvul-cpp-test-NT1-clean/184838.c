 void AppShortcutManager::OnceOffCreateShortcuts() {
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
 
   extensions::ExtensionSystem* buffer1;
  ExtensionServiceInterface* buffer2;
  if (!(buffer1 = extensions::ExtensionSystem::Get(profile_)) ||
      !(buffer2 = buffer1->extension_service()))
    return;

  const extensions::ExtensionSet* buffer3 = extension_service->extensions();
  for (extensions::ExtensionSet::const_iterator it = apps->begin();
       it != buffer3->end(); ++it) {
    if (ShouldCreateShortcutFor(profile_, it->get()))
      CreateShortcutsInApplicationsMenu(profile_, it->get());
  }
}
