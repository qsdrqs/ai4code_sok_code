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

bool WebPagePrivate::dispatchTouchPointAsMouseEventToFullScreenPlugin(PluginView* pluginView, const Platform::TouchPoint& point)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     NPEvent npEvent;
     NPMouseEvent mouse;
 
    switch (point.m_state) {
     case Platform::TouchPoint::TouchPressed:
         mouse.type = MOUSE_BUTTON_DOWN;
         break;
    case Platform::TouchPoint::TouchReleased:
        mouse.type = MOUSE_BUTTON_UP;
        break;
    case Platform::TouchPoint::TouchMoved:
        mouse.type = MOUSE_MOTION;
        break;
    case Platform::TouchPoint::TouchStationary:
         return true;
     }
 
    mouse.x = point.m_screenPos.x();
    mouse.y = point.m_screenPos.y();
     mouse.button = mouse.type != MOUSE_BUTTON_UP;
     mouse.flags = 0;
     npEvent.type = NP_MouseEvent;
    npEvent.data = &mouse;

    pluginView->dispatchFullScreenNPEvent(npEvent);
    return true;
}
