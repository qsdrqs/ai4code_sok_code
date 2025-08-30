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

void Document::styleResolverChanged(RecalcStyleTime updateTime, StyleResolverUpdateMode updateMode)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    if (!confusingAndOftenMisusedAttached() || (!m_didCalculateStyleResolver && !haveStylesheetsLoaded())) {
        m_styleResolver.clear();
        return;
    }
    m_didCalculateStyleResolver = true;

    bool needsRecalc = m_styleEngine->updateActiveStyleSheets(updateMode);

    if (didLayoutWithPendingStylesheets() && !m_styleEngine->hasPendingSheets()) {
        m_pendingSheetLayout = IgnoreLayoutWithPendingSheets;
        renderView()->repaintViewAndCompositedLayers();
    }

    if (!needsRecalc)
        return;

    m_evaluateMediaQueriesOnStyleRecalc = true;
    setNeedsStyleRecalc();

    if (updateTime == RecalcStyleImmediately)
        updateStyleIfNeeded();
}
