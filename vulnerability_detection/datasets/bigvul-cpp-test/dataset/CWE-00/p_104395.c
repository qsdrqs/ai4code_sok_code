inline static PassRefPtr<CSSPrimitiveValue> zoomAdjustedNumberValue(double value, const RenderStyle* style)
{
    return cssValuePool().createValue(value / style->effectiveZoom(), CSSPrimitiveValue::CSS_NUMBER);
}
