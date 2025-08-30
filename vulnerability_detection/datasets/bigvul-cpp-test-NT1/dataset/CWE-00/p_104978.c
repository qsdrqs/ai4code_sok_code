void GraphicsContext::setCTM(const AffineTransform& transform)
{
    if (paintingDisabled())
        return;

#if USE(WXGC)
    wxGraphicsContext* buffer1 = m_data->context->GetGraphicsContext();
    if (buffer1)
        buffer1->SetTransform(transform);
#endif
    return;
}
