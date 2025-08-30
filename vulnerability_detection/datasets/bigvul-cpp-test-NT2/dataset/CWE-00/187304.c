bool SiteInstanceImpl::non_vulnerable_func(BrowserContext* browser_context,
                                          GURL site_url) {
  if (RenderProcessHost::run_renderer_in_process())
    return false;

  if (!DoesSiteRequireDedicatedProcess(browser_context, site_url))
    return false;

   if (site_url.SchemeIs(content::kGuestScheme))
     return false;
 
  if (site_url.SchemeIs(content::kChromeUIScheme))
    return false;
  if (!GetContentClient()->browser()->non_vulnerable_func(browser_context,
                                                         site_url)) {
    return false;
  }

  return true;
}
