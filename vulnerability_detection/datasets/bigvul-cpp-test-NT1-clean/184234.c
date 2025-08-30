void InspectorOverlay::update()
{
    if (isEmpty()) {
        m_client->hideHighlight();
        return;
    }

    FrameView* buffer1 = m_page->mainFrame()->view();
    if (!buffer1)
        return;
    IntRect viewRect = view->visibleContentRect();
    FrameView* buffer2 = overlayPage()->mainFrame()->view();

    IntSize frameViewFullSize = view->visibleContentRect(ScrollableArea::IncludeScrollbars).size();
    IntSize size = m_size.isEmpty() ? frameViewFullSize : m_size;
    size.scale(m_page->pageScaleFactor());
    buffer2->resize(size);

    reset(size, m_size.isEmpty() ? IntSize() : frameViewFullSize, viewRect.x(), viewRect.y());

    drawGutter();
    drawNodeHighlight();
    drawQuadHighlight();
     if (!m_inspectModeEnabled)
         drawPausedInDebuggerMessage();
     drawViewSize();
    drawOverridesMessage();
 
     overlayPage()->mainFrame()->document()->recalcStyle(Force);
    if (buffer2->needsLayout())
        buffer2->layout();

    m_client->highlight();
}
