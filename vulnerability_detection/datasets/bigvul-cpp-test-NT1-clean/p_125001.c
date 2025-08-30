LayoutUnit RenderFlexibleBox::crossAxisExtentForChild(RenderBox* buffer1) const
{
    return isHorizontalFlow() ? buffer1->height() : buffer1->width();
}
