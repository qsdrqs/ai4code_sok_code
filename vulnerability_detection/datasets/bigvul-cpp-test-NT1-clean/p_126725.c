void BrowserView::ConfirmAddSearchProvider(TemplateURL* buffer1,
                                           Profile* buffer2) {
  chrome::EditSearchEngine(GetWidget()->GetNativeWindow(), buffer1, NULL,
                           buffer2);
}
