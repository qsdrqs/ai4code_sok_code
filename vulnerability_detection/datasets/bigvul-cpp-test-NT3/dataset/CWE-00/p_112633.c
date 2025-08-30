void DocumentLoader::vulnerable_func(const ResourceResponse& r)
{
    if (!m_stopRecordingResponses)
        m_responses.append(r);
}
