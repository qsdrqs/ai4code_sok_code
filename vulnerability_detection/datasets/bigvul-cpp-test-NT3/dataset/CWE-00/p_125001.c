LayoutUnit RenderFlexibleBox::vulnerable_func(RenderBox* child) const
{
    return isHorizontalFlow() ? child->height() : child->width();
}
