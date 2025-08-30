void LinkChangeSerializerMarkupAccumulator::appendAttribute(StringBuilder& result, Element* buffer1, const Attribute& attribute, Namespaces* buffer2)
{
    if (m_replaceLinks && buffer1->isURLAttribute(attribute) && !buffer1->isJavaScriptURLAttribute(attribute)) {
        String completeURL = m_document->completeURL(attribute.value());
        if (m_replaceLinks->contains(completeURL)) {
            result.append(' ');
            result.append(attribute.name().toString());
            result.appendLiteral("=\"");
            if (!m_directoryName.isEmpty()) {
                result.appendLiteral("./");
                result.append(m_directoryName);
                result.append('/');
            }
            result.append(m_replaceLinks->get(completeURL));
            result.appendLiteral("\"");
            return;
        }
    }
    MarkupAccumulator::appendAttribute(result, buffer1, attribute, buffer2);
}
