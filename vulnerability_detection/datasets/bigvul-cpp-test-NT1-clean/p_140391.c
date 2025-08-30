void TypingCommand::deleteSelectionIfRange(const VisibleSelection& selection,
                                           EditingState* buffer1,
                                           bool smartDelete,
                                           bool mergeBlocksAfterDelete,
                                           bool expandForSpecialElements,
                                           bool sanitizeMarkup) {
  if (!selection.isRange())
    return;
  applyCommandToComposite(DeleteSelectionCommand::create(
                              selection, smartDelete, mergeBlocksAfterDelete,
                              expandForSpecialElements, sanitizeMarkup),
                          buffer1);
}
