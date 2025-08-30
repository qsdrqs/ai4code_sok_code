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

float GM2TabStyle::GetHoverOpacity() const {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  const float range_start = float{GetStandardWidth()};
  const float range_end = float{GetMinimumInactiveWidth()};
  const float value_in_range = float{tab_->width()};
  const float t = (value_in_range - range_start) / (range_end - range_start);
  return tab_->controller()->GetHoverOpacityForTab(t * t);
}
