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

void WebPagePrivate::setPreventsScreenDimming(bool keepAwake)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
