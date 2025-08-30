void WebPagePrivate::setPreventsScreenDimming(bool keepAwake)
{
    if (keepAwake) {
        if (!m_preventIdleDimmingCount)
            m_client->setPreventsScreenIdleDimming(true);
        m_preventIdleDimmingCount++;
    } else if (m_preventIdleDimmingCount > 0) {
        m_preventIdleDimmingCount--;
        if (!m_preventIdleDimmingCount)
            m_client->setPreventsScreenIdleDimming(false);
    } else
        ASSERT_NOT_REACHED(); // SetPreventsScreenIdleDimming(false) called too many times.
}
