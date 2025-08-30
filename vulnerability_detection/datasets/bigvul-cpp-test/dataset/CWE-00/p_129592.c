SVGMarkerOrientType LayoutSVGResourceMarker::orientType() const
{
    return toSVGMarkerElement(element())->orientType()->currentValue()->enumValue();
}
