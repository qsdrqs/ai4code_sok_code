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

static int ne2000_buffer_full(NE2000State *s)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     int avail, index, boundary;
 
     index = s->curpag << 8;
     boundary = s->boundary << 8;
     if (index < boundary)
        return 1;
    return 0;
}
