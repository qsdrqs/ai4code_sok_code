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

SProcXFixesCreatePointerBarrier(ClientPtr client)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    REQUEST(xXFixesCreatePointerBarrierReq);
    int i;
     int i;
     CARD16 *in_devices = (CARD16 *) &stuff[1];
 
     swaps(&stuff->length);
     swaps(&stuff->num_devices);
     REQUEST_FIXED_SIZE(xXFixesCreatePointerBarrierReq, pad_to_int32(stuff->num_devices));
    swaps(&stuff->x1);
    swaps(&stuff->y1);
    swaps(&stuff->x2);
    swaps(&stuff->y2);
    swapl(&stuff->directions);
    for (i = 0; i < stuff->num_devices; i++) {
        swaps(in_devices + i);
    }

    return ProcXFixesVector[stuff->xfixesReqType] (client);
}
