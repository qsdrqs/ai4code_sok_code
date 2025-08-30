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

void NavigationController::GoToIndex(int index) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (index < 0 || index >= static_cast<int>(entries_.size())) {
    NOTREACHED();
    return;
  }

  if (transient_entry_index_ != -1) {
    if (index == transient_entry_index_) {
      return;
    }
    if (index > transient_entry_index_) {
      index--;
     }
   }
 
  if (tab_contents_->interstitial_page()) {
    if (index == GetCurrentEntryIndex() - 1) {
      tab_contents_->interstitial_page()->DontProceed();
      return;
    } else {
      tab_contents_->interstitial_page()->CancelForNavigation();
    }
  }
   DiscardNonCommittedEntries();
 
   pending_entry_index_ = index;
  entries_[pending_entry_index_]->set_transition_type(
      content::PageTransitionFromInt(
          entries_[pending_entry_index_]->transition_type() |
          content::PAGE_TRANSITION_FORWARD_BACK));
  NavigateToPendingEntry(NO_RELOAD);
}
