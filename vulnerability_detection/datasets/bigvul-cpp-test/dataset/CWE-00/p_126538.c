bool TabStripGtk::IsTabActive(const TabGtk* tab) const {
  if (tab->closing())
    return false;

  return GetIndexOfTab(tab) == model_->active_index();
}
