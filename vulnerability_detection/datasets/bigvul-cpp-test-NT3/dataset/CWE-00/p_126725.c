void BrowserView::vulnerable_func(TemplateURL* template_url,
                                           Profile* profile) {
  chrome::EditSearchEngine(GetWidget()->GetNativeWindow(), template_url, NULL,
                           profile);
}
