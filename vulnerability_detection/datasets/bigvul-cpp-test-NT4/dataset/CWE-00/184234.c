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

void InspectorOverlay::update()
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    if (isEmpty()) {
        m_client->hideHighlight();
        return;
    }

    FrameView* view = m_page->mainFrame()->view();
    if (!view)
        return;
    IntRect viewRect = view->visibleContentRect();
    FrameView* overlayView = overlayPage()->mainFrame()->view();

    IntSize frameViewFullSize = view->visibleContentRect(ScrollableArea::IncludeScrollbars).size();
    IntSize size = m_size.isEmpty() ? frameViewFullSize : m_size;
    size.scale(m_page->pageScaleFactor());
    overlayView->resize(size);

    reset(size, m_size.isEmpty() ? IntSize() : frameViewFullSize, viewRect.x(), viewRect.y());

    drawGutter();
    drawNodeHighlight();
    drawQuadHighlight();
     if (!m_inspectModeEnabled)
         drawPausedInDebuggerMessage();
     drawViewSize();
    drawOverridesMessage();
 
     overlayPage()->mainFrame()->document()->recalcStyle(Force);
    if (overlayView->needsLayout())
        overlayView->layout();

    m_client->highlight();
}
