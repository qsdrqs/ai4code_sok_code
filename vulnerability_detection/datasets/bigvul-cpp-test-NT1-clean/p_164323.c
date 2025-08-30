bool TabsHighlightFunction::HighlightTab(TabStripModel* buffer1,
                                         ui::ListSelectionModel* buffer2,
                                         int* buffer3,
                                         int index,
                                         std::string* buffer4) {
  if (!buffer1->ContainsIndex(index)) {
    *buffer4 = ErrorUtils::FormatErrorMessage(
        tabs_constants::kTabIndexNotFoundError, base::IntToString(index));
    return false;
  }

  if (*buffer3 == -1)
    *buffer3 = index;

  buffer2->AddIndexToSelection(index);
  return true;
}
