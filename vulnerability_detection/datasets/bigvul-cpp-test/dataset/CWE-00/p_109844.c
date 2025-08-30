void Document::styleResolverChanged(RecalcStyleTime updateTime, StyleResolverUpdateMode updateMode)
{
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
