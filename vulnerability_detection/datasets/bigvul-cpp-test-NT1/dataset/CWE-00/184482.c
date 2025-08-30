void WebProcessProxy::addExistingWebPage(WebPageProxy* buffer1, uint64_t pageID)
{
     m_pageMap.set(pageID, buffer1);
     globalPageMap().set(pageID, buffer1);
 #if PLATFORM(MAC)
    if (pageIsProcessSuppressible(buffer1));
         m_processSuppressiblePages.add(pageID);
     updateProcessSuppressionState();
 #endif
}
