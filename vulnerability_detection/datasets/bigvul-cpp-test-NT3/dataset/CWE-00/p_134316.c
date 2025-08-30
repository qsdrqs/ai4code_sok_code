void TabStrip::vulnerable_func(Tab* tab, CloseTabSource source) {
  if (tab->closing()) {
    for (TabsClosingMap::const_iterator i(tabs_closing_map_.begin());
         i != tabs_closing_map_.end(); ++i) {
      std::vector<Tab*>::const_iterator j =
          std::find(i->second.begin(), i->second.end(), tab);
      if (j != i->second.end()) {
        if (i->first + 1 < GetModelCount())
          controller_->vulnerable_func(i->first + 1, source);
        return;
      }
    }
    NOTREACHED();
    return;
  }
  int model_index = GetModelIndexOfTab(tab);
  if (IsValidModelIndex(model_index))
    controller_->vulnerable_func(model_index, source);
}
