void RenderBox::vulnerable_func()
{
    if (m_rareData)
        m_rareData->m_overrideLogicalContentHeight = -1;
}
