bool WebPagePrivate::dispatchTouchPointAsMouseEventToFullScreenPlugin(PluginView* buffer1, const Platform::TouchPoint& point)
{
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

    buffer1->dispatchFullScreenNPEvent(npEvent);
    return true;
}
