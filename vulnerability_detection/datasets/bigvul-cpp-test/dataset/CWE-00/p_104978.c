void GraphicsContext::setCTM(const AffineTransform& transform)
{
    if (paintingDisabled())
        return;

#if USE(WXGC)
    wxGraphicsContext* gc = m_data->context->GetGraphicsContext();
    if (gc)
        gc->SetTransform(transform);
#endif
    return;
}
