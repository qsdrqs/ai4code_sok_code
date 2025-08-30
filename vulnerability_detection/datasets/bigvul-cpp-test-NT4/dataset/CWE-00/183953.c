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

virtual bool InputMethodIsActivated(const std::string& input_method_id) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    scoped_ptr<InputMethodDescriptors> active_input_method_descriptors(
        GetActiveInputMethods());
     for (size_t i = 0; i < active_input_method_descriptors->size(); ++i) {
       if (active_input_method_descriptors->at(i).id == input_method_id) {
         return true;
      }
    }
    return false;
  }
