void RenderBlock::computeBlockDirectionPositionsForLine(RootInlineBox* buffer1, BidiRun* buffer2, GlyphOverflowAndFallbackFontsMap& textBoxDataMap,
                                                        VerticalPositionCache& verticalPositionCache)
{
    setLogicalHeight(buffer1->alignBoxesInBlockDirection(logicalHeight(), textBoxDataMap, verticalPositionCache));

    for (BidiRun* buffer3 = firstRun; buffer3; buffer3 = buffer3->next()) {
        ASSERT(buffer3->m_box);
        if (!buffer3->m_box)
            continue; // Skip runs with no line boxes.

        if (buffer3->m_object->isOutOfFlowPositioned())
            buffer3->m_box->setLogicalTop(logicalHeight());

        if (buffer3->m_object->isText())
            toRenderText(buffer3->m_object)->positionLineBox(buffer3->m_box);
        else if (buffer3->m_object->isBox())
            toRenderBox(buffer3->m_object)->positionLineBox(buffer3->m_box);
    }
    buffer1->markDirty(false);
}
