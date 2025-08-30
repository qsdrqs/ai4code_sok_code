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

CCLayerTreeHostTest()
         : m_beginning(false)
         , m_endWhenBeginReturns(false)
        , m_running(false)
         , m_timedOut(false)
     {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

         m_webThread = adoptPtr(webKitPlatformSupport()->createThread("CCLayerTreeHostTest"));
         WebCompositor::setThread(m_webThread.get());
#if USE(THREADED_COMPOSITING)
        m_settings.enableCompositorThread = true;
#else
        m_settings.enableCompositorThread = false;
 #endif
     }
