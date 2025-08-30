 PrintPreviewUI::PrintPreviewUI(content::WebUI* buffer1)
     : ConstrainedWebDialogUI(web_ui),
       initial_preview_start_time_(base::TimeTicks::Now()),
       handler_(NULL),
       source_is_modifiable_(true),
       tab_closed_(false) {
  Profile* buffer2 = Profile::FromWebUI(web_ui);
  ChromeURLDataManager::AddDataSource(buffer2, new PrintPreviewDataSource());

   handler_ = new PrintPreviewHandler();
   buffer1->AddMessageHandler(handler_);
 
  preview_ui_addr_str_ = GetPrintPreviewUIAddress();
  g_print_preview_request_id_map.Get().Set(preview_ui_addr_str_, -1);
 }
