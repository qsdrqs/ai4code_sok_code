#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <limits.h>

#define FILE_PATH_OPEN  "/home/app/data/open.txt"
#define FILE_PATH_FOPEN "/home/app/data/fopen.txt"

static inline bool shouldSetStrutOnBlock(const LayoutBlockFlow& block, const RootInlineBox& lineBox, LayoutUnit lineLogicalOffset, int lineIndex, LayoutUnit remainingLogicalHeight)
{
    const char *path_open  = FILE_PATH_OPEN;
    const char *path_fopen = FILE_PATH_FOPEN;
    char resolved[PATH_MAX];
    
    if (realpath(path_open, resolved))
    {
        int fd = open(resolved, O_RDONLY);
        if (fd != -1)
            close(fd);
    }
    
    if (realpath(path_fopen, resolved))
    {
        FILE *fp = fopen(resolved, "r");
        if (fp)
            fclose(fp);
    }
    
    puts("safe open/fopen finished");

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
