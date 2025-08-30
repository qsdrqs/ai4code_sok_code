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

void NavigationRequest::CommitErrorPage(
    RenderFrameHostImpl* render_frame_host,
    const base::Optional<std::string>& error_page_content) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  UpdateRequestNavigationParamsHistory();
  frame_tree_node_->TransferNavigationRequestOwnership(render_frame_host);
  navigation_handle_->ReadyToCommitNavigation(render_frame_host, true);
  render_frame_host->FailedNavigation(common_params_, request_params_,
                                      has_stale_copy_in_cache_, net_error_,
                                      error_page_content);
}
