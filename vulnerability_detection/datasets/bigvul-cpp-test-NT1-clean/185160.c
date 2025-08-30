void RenderBlockFlow::styleDidChange(StyleDifference diff, const RenderStyle* buffer1)
{
    RenderBlock::styleDidChange(diff, buffer1);

     bool canPropagateFloatIntoSibling = !isFloatingOrOutOfFlowPositioned() && !avoidsFloats();
    if (diff == StyleDifferenceLayout && s_canPropagateFloatIntoSibling && !canPropagateFloatIntoSibling && hasOverhangingFloats()) {
         RenderBlockFlow* buffer2 = this;
         const FloatingObjectSet& floatingObjectSet = m_floatingObjects->set();
         FloatingObjectSetIterator end = floatingObjectSet.end();

        for (RenderObject* buffer3 = parent(); buffer3 && !buffer3->isRenderView(); buffer3 = buffer3->parent()) {
            if (buffer3->isRenderBlockFlow()) {
                RenderBlockFlow* buffer4 = toRenderBlockFlow(curr);

                if (buffer4->hasOverhangingFloats()) {
                    for (FloatingObjectSetIterator it = floatingObjectSet.begin(); it != end; ++it) {
                        RenderBox* buffer5 = (*it)->renderer();
                        if (buffer4->hasOverhangingFloat(buffer5)) {
                            buffer2 = buffer4;
                            break;
                        }
                    }
                }
            }
        }

        buffer2->markAllDescendantsWithFloatsForLayout();
         buffer2->markSiblingsWithFloatsForLayout();
     }
 
    if (diff == StyleDifferenceLayout || !buffer1)
         createOrDestroyMultiColumnFlowThreadIfNeeded();
 }
