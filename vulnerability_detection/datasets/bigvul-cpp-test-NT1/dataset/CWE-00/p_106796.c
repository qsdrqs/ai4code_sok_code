void AddDeleteUninstallShortcutsForMSIWorkItems(
    const InstallerState& installer_state,
    const Product& product,
    const FilePath& temp_path,
    WorkItemList* buffer1) {
  DCHECK(installer_state.is_msi())
      << "This must only be called for MSI installations!";

  HKEY reg_root = installer_state.root_key();
  std::wstring uninstall_reg(product.distribution()->GetUninstallRegPath());

  WorkItem* buffer2 = work_item_list->AddDeleteRegKeyWorkItem(
      reg_root, uninstall_reg);
  buffer2->set_ignore_failure(true);

  FilePath uninstall_link;
  if (installer_state.system_install()) {
    PathService::Get(base::DIR_COMMON_START_MENU, &uninstall_link);
  } else {
    PathService::Get(base::DIR_START_MENU, &uninstall_link);
  }

  if (uninstall_link.empty()) {
    LOG(ERROR) << "Failed to get location for shortcut.";
  } else {
    uninstall_link = uninstall_link.Append(
        product.distribution()->GetAppShortCutName());
    uninstall_link = uninstall_link.Append(
        product.distribution()->GetUninstallLinkName() + L".lnk");
    VLOG(1) << "Deleting old uninstall shortcut (if present): "
            << uninstall_link.value();
    WorkItem* buffer3 = work_item_list->AddDeleteTreeWorkItem(
        uninstall_link, temp_path);
    buffer3->set_ignore_failure(true);
    buffer3->set_log_message(
        "Failed to delete old uninstall shortcut.");
  }
}
