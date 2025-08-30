bool TabsHighlightFunction::HighlightTab(TabStripModel* tabstrip,
                                         ui::ListSelectionModel* selection,
                                         int* active_index,
                                         int index,
                                         std::string* error) {
  if (!tabstrip->ContainsIndex(index)) {
    *error = ErrorUtils::FormatErrorMessage(
        tabs_constants::kTabIndexNotFoundError, base::IntToString(index));
    return false;
  }

  if (*active_index == -1)
    *active_index = index;

  selection->AddIndexToSelection(index);
  return true;
}
