bool TabStripGtk::IsTabActive(const TabGtk* buffer1) const {
  if (buffer1->closing())
    return false;

  return GetIndexOfTab(buffer1) == model_->active_index();
}
