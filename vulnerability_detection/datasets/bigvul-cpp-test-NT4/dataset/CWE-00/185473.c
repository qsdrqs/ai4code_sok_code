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

static inline bool shouldSetStrutOnBlock(const LayoutBlockFlow& block, const RootInlineBox& lineBox, LayoutUnit lineLogicalOffset, int lineIndex, LayoutUnit remainingLogicalHeight)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    bool wantsStrutOnBlock = false;
    if (!block.style()->hasAutoOrphans() && block.style()->orphans() >= lineIndex) {
        wantsStrutOnBlock = true;
    } else if (lineBox == block.firstRootBox() && lineLogicalOffset == block.borderAndPaddingBefore()) {
        LayoutUnit lineHeight = lineBox.lineBottomWithLeading() - lineBox.lineTopWithLeading();
        LayoutUnit totalLogicalHeight = lineHeight + std::max<LayoutUnit>(0, lineLogicalOffset);
        LayoutUnit pageLogicalHeightAtNewOffset = block.pageLogicalHeightForOffset(lineLogicalOffset + remainingLogicalHeight);
         if (totalLogicalHeight < pageLogicalHeightAtNewOffset)
             wantsStrutOnBlock = true;
     }
    if (!wantsStrutOnBlock || block.isOutOfFlowPositioned())
        return false;
    LayoutBlock* containingBlock = block.containingBlock();
    return containingBlock && containingBlock->isLayoutBlockFlow();
 }
