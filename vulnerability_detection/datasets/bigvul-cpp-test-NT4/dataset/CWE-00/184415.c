#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

PrintPreviewUI::PrintPreviewUI(content::WebUI* web_ui)
     : ConstrainedWebDialogUI(web_ui),
       initial_preview_start_time_(base::TimeTicks::Now()),
       handler_(NULL),
       source_is_modifiable_(true),
       tab_closed_(false) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  Profile* profile = Profile::FromWebUI(web_ui);
  ChromeURLDataManager::AddDataSource(profile, new PrintPreviewDataSource());

   handler_ = new PrintPreviewHandler();
   web_ui->AddMessageHandler(handler_);
 
  preview_ui_addr_str_ = GetPrintPreviewUIAddress();
  g_print_preview_request_id_map.Get().Set(preview_ui_addr_str_, -1);
 }
