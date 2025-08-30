WebMouseEvent* BuildMouseEvent(const PP_InputEvent& event) {
  WebMouseEvent* buffer1 = new WebMouseEvent();
  switch (event.type) {
    case PP_INPUTEVENT_TYPE_MOUSEDOWN:
      buffer1->type = WebInputEvent::MouseDown;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEUP:
      buffer1->type = WebInputEvent::MouseUp;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEMOVE:
      buffer1->type = WebInputEvent::MouseMove;
      break;
    case PP_INPUTEVENT_TYPE_MOUSEENTER:
      buffer1->type = WebInputEvent::MouseEnter;
      break;
     case PP_INPUTEVENT_TYPE_MOUSELEAVE:
       buffer1->type = WebInputEvent::MouseLeave;
       break;
     default:
       NOTREACHED();
   }
  buffer1->timeStampSeconds = event.time_stamp;
  buffer1->modifiers = event.u.mouse.modifier;
  buffer1->button =
      static_cast<WebMouseEvent::Button>(event.u.mouse.button);
  buffer1->x = static_cast<int>(event.u.mouse.x);
  buffer1->y = static_cast<int>(event.u.mouse.y);
  buffer1->clickCount = event.u.mouse.click_count;
  return buffer1;
}
