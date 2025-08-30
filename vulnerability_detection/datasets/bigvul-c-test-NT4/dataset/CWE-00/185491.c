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

bool CanRendererHandleEvent(const ui::MouseEvent* event) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (event->type() == ui::ET_MOUSE_CAPTURE_CHANGED)
    return false;

#if defined(OS_WIN)
  switch (event->native_event().message) {
    case WM_XBUTTONDOWN:
    case WM_XBUTTONUP:
     case WM_XBUTTONDBLCLK:
     case WM_NCMOUSELEAVE:
     case WM_NCMOUSEMOVE:
     case WM_NCXBUTTONDOWN:
     case WM_NCXBUTTONUP:
     case WM_NCXBUTTONDBLCLK:
      return false;
    default:
      break;
  }
#endif
  return true;
}
