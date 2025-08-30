#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void LinkChangeSerializerMarkupAccumulator::appendAttribute(StringBuilder& result, Element* element, const Attribute& attribute, Namespaces* namespaces)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    if (m_replaceLinks && element->isURLAttribute(attribute) && !element->isJavaScriptURLAttribute(attribute)) {
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
    MarkupAccumulator::appendAttribute(result, element, attribute, namespaces);
}
