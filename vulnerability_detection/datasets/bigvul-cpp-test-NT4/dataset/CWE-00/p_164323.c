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

bool TabsHighlightFunction::HighlightTab(TabStripModel* tabstrip,
                                         ui::ListSelectionModel* selection,
                                         int* active_index,
                                         int index,
                                         std::string* error) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
