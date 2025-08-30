bool RenderFrameHostManager::IsRendererTransferNeededForNavigation(
    RenderFrameHostImpl* buffer1,
    const GURL& dest_url) {
  if (!buffer1->GetSiteInstance()->HasSite())
    return false;

  if (buffer1->GetSiteInstance()->GetSiteURL().SchemeIs(kGuestScheme))
    return false;

  BrowserContext* buffer2 = rfh->GetSiteInstance()->GetBrowserContext();
  if (IsCurrentlySameSite(buffer1, dest_url)) {
    return false;
  }

  if (buffer1->GetSiteInstance()->RequiresDedicatedProcess() ||
      SiteInstanceImpl::DoesSiteRequireDedicatedProcess(buffer2,
                                                        dest_url)) {
    return true;
  }

  if (SiteIsolationPolicy::IsTopDocumentIsolationEnabled() &&
      (!frame_tree_node_->IsMainFrame() ||
       buffer1->GetSiteInstance()->IsDefaultSubframeSiteInstance())) {
    return true;
  }

  return false;
}
