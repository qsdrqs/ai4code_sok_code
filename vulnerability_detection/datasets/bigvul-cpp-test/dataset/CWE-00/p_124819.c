void RenderBox::clearOverrideLogicalContentHeight()
{
    if (m_rareData)
        m_rareData->m_overrideLogicalContentHeight = -1;
}
