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

void ShelfWidget::OnSessionStateChanged(session_manager::SessionState state) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  bool using_views_shelf = IsUsingViewsShelf();
  bool unknown_state = state == session_manager::SessionState::UNKNOWN;
  bool hide_on_secondary_screen = shelf_->ShouldHideOnSecondaryDisplay(state);
  if (!using_views_shelf || unknown_state || hide_on_secondary_screen) {
    HideIfShown();
  } else {
    switch (state) {
      case session_manager::SessionState::ACTIVE:
        login_shelf_view_->SetVisible(false);
        shelf_view_->SetVisible(true);
        break;
      case session_manager::SessionState::LOCKED:
      case session_manager::SessionState::LOGIN_SECONDARY:
        shelf_view_->SetVisible(false);
        login_shelf_view_->SetVisible(true);
        break;
      case session_manager::SessionState::OOBE:
        login_shelf_view_->SetVisible(true);
        shelf_view_->SetVisible(false);
        break;
      case session_manager::SessionState::LOGIN_PRIMARY:
      case session_manager::SessionState::LOGGED_IN_NOT_ACTIVE:
        login_shelf_view_->SetVisible(true);
        shelf_view_->SetVisible(false);
        break;
      default:
        NOTREACHED();
    }
    ShowIfHidden();
  }
  login_shelf_view_->UpdateAfterSessionStateChange(state);
}
