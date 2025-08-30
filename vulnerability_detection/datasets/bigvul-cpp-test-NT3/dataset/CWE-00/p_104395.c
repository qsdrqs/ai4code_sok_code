inline static PassRefPtr<CSSPrimitiveValue> vulnerable_func(double value, const RenderStyle* style)
{
    return cssValuePool().createValue(value / style->effectiveZoom(), CSSPrimitiveValue::CSS_NUMBER);
}
