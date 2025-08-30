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

bool StopInputMethodProcess() {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     if (!IBusConnectionsAreAlive()) {
       LOG(ERROR) << "StopInputMethodProcess: IBus connection is not alive";
       return false;
    }

    ibus_bus_exit_async(ibus_,
                        FALSE  /* do not restart */,
                        -1  /* timeout */,
                        NULL  /* cancellable */,
                        NULL  /* callback */,
                        NULL  /* user_data */);

    if (ibus_config_) {
      g_object_unref(ibus_config_);
      ibus_config_ = NULL;
    }
     return true;
   }
