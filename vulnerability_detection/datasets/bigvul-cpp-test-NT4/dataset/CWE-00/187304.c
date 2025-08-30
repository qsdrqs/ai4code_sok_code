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

bool SiteInstanceImpl::ShouldLockToOrigin(BrowserContext* browser_context,
                                          GURL site_url) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (RenderProcessHost::run_renderer_in_process())
    return false;

  if (!DoesSiteRequireDedicatedProcess(browser_context, site_url))
    return false;

   if (site_url.SchemeIs(content::kGuestScheme))
     return false;
 
  if (site_url.SchemeIs(content::kChromeUIScheme))
    return false;
  if (!GetContentClient()->browser()->ShouldLockToOrigin(browser_context,
                                                         site_url)) {
    return false;
  }

  return true;
}
