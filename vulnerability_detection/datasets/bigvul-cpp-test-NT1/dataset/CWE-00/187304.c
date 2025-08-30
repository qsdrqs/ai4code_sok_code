bool SiteInstanceImpl::ShouldLockToOrigin(BrowserContext* buffer1,
                                          GURL site_url) {
  if (RenderProcessHost::run_renderer_in_process())
    return false;

  if (!DoesSiteRequireDedicatedProcess(buffer1, site_url))
    return false;

   if (site_url.SchemeIs(content::kGuestScheme))
     return false;
 
  if (site_url.SchemeIs(content::kChromeUIScheme))
    return false;
  if (!GetContentClient()->browser()->ShouldLockToOrigin(buffer1,
                                                         site_url)) {
    return false;
  }

  return true;
}
