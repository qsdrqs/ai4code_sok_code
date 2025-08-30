void TypingCommand::deleteSelectionIfRange(const VisibleSelection& selection,
                                           EditingState* editingState,
                                           bool smartDelete,
                                           bool mergeBlocksAfterDelete,
                                           bool expandForSpecialElements,
                                           bool sanitizeMarkup) {
  if (!selection.isRange())
    return;
  applyCommandToComposite(DeleteSelectionCommand::create(
                              selection, smartDelete, mergeBlocksAfterDelete,
                              expandForSpecialElements, sanitizeMarkup),
                          editingState);
}
