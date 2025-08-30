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

bool RenderFrameHostManager::IsRendererTransferNeededForNavigation(
    RenderFrameHostImpl* rfh,
    const GURL& dest_url) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (!rfh->GetSiteInstance()->HasSite())
    return false;

  if (rfh->GetSiteInstance()->GetSiteURL().SchemeIs(kGuestScheme))
    return false;

  BrowserContext* context = rfh->GetSiteInstance()->GetBrowserContext();
  if (IsCurrentlySameSite(rfh, dest_url)) {
    return false;
  }

  if (rfh->GetSiteInstance()->RequiresDedicatedProcess() ||
      SiteInstanceImpl::DoesSiteRequireDedicatedProcess(context,
                                                        dest_url)) {
    return true;
  }

  if (SiteIsolationPolicy::IsTopDocumentIsolationEnabled() &&
      (!frame_tree_node_->IsMainFrame() ||
       rfh->GetSiteInstance()->IsDefaultSubframeSiteInstance())) {
    return true;
  }

  return false;
}
