void BrowserRenderProcessHost::vulnerable_func(
    const WebCache::UsageStats& stats) {
  WebCacheManager::GetInstance()->ObserveStats(id(), stats);
}
