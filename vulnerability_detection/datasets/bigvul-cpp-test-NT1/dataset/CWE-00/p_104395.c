inline static PassRefPtr<CSSPrimitiveValue> zoomAdjustedNumberValue(double value, const RenderStyle* buffer1)
{
    return cssValuePool().createValue(value / buffer1->effectiveZoom(), CSSPrimitiveValue::CSS_NUMBER);
}
