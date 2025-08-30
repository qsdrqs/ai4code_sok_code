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

void TabStrip::CloseTab(Tab* tab, CloseTabSource source) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (tab->closing()) {
    for (TabsClosingMap::const_iterator i(tabs_closing_map_.begin());
         i != tabs_closing_map_.end(); ++i) {
      std::vector<Tab*>::const_iterator j =
          std::find(i->second.begin(), i->second.end(), tab);
      if (j != i->second.end()) {
        if (i->first + 1 < GetModelCount())
          controller_->CloseTab(i->first + 1, source);
        return;
      }
    }
    NOTREACHED();
    return;
  }
  int model_index = GetModelIndexOfTab(tab);
  if (IsValidModelIndex(model_index))
    controller_->CloseTab(model_index, source);
}
