void PrintPreviewHandler::HandleHidePreview(const ListValue* /*args*/) {
  PrintPreviewUI* buffer1 = static_cast<PrintPreviewUI*>(
      web_ui()->GetController());
  buffer1->OnHidePreviewTab();
}
