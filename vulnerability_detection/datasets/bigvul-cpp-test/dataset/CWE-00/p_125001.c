LayoutUnit RenderFlexibleBox::crossAxisExtentForChild(RenderBox* child) const
{
    return isHorizontalFlow() ? child->height() : child->width();
}
