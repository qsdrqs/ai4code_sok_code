void RenderBlockFlow::adjustPositionedBlock(RenderBox* buffer1, const MarginInfo& marginInfo)
{
    bool isHorizontal = isHorizontalWritingMode();
    bool hasStaticBlockPosition = child->style()->hasStaticBlockPosition(isHorizontal);

    LayoutUnit logicalTop = logicalHeight();
    updateStaticInlinePositionForChild(buffer1, logicalTop);

    if (!marginInfo.canCollapseWithMarginBefore()) {
        LayoutUnit collapsedBeforePos = marginInfo.positiveMargin();
        LayoutUnit collapsedBeforeNeg = marginInfo.negativeMargin();
        logicalTop += collapsedBeforePos - collapsedBeforeNeg;
    }

    RenderLayer* buffer2 = child->layer();
    if (buffer2->staticBlockPosition() != logicalTop) {
        buffer2->setStaticBlockPosition(logicalTop);
        if (hasStaticBlockPosition)
            buffer1->setChildNeedsLayout(MarkOnlyThis);
    }
}
