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

WebMouseEvent* BuildMouseEvent(const PP_InputEvent& event) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  WebMouseEvent* mouse_event = new WebMouseEvent();
  switch (event.type) {
    case PP_INPUTEVENT_TYPE_MOUSEDOWN:
      mouse_event->type = WebInputEvent::MouseDown;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEUP:
      mouse_event->type = WebInputEvent::MouseUp;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEMOVE:
      mouse_event->type = WebInputEvent::MouseMove;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEENTER:
      mouse_event->type = WebInputEvent::MouseEnter;
      break;
     case PP_INPUTEVENT_TYPE_MOUSELEAVE:
       mouse_event->type = WebInputEvent::MouseLeave;
       break;
     default:
       NOTREACHED();
   }
  mouse_event->timeStampSeconds = event.time_stamp;
  mouse_event->modifiers = event.u.mouse.modifier;
  mouse_event->button =
      static_cast<WebMouseEvent::Button>(event.u.mouse.button);
  mouse_event->x = static_cast<int>(event.u.mouse.x);
  mouse_event->y = static_cast<int>(event.u.mouse.y);
  mouse_event->clickCount = event.u.mouse.click_count;
  return mouse_event;
}
