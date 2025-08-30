#include <stdio.h>
#include <stdlib.h>
#include <string.h>
XineramaXvShmPutImage(ClientPtr client)
 {
     REQUEST(xvShmPutImageReq);
     PanoramiXRes *draw, *gc, *port;
    Bool send_event = stuff->send_event;
     Bool isRoot;
     int result, i, x, y;
 
     REQUEST_SIZE_MATCH(xvShmPutImageReq);
 
     result = dixLookupResourceByClass((void **) &draw, stuff->drawable,
                                       XRC_DRAWABLE, client, DixWriteAccess);
     if (result != Success)
    result = dixLookupResourceByType((void **) &gc, stuff->gc,
                                     XRT_GC, client, DixReadAccess);
    if (result != Success)
        return result;

    result = dixLookupResourceByType((void **) &port, stuff->port,
                                     XvXRTPort, client, DixReadAccess);
    if (result != Success)
        return result;

    isRoot = (draw->type == XRT_WINDOW) && draw->u.win.root;

    x = stuff->drw_x;
    y = stuff->drw_y;

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

FOR_NSCREENS_BACKWARD(i) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

        if (port->info[i].id) {
            stuff->drawable = draw->info[i].id;
            stuff->port = port->info[i].id;
            stuff->gc = gc->info[i].id;
            stuff->drw_x = x;
            stuff->drw_y = y;
            if (isRoot) {
                stuff->drw_x -= screenInfo.screens[i]->x;
                stuff->drw_y -= screenInfo.screens[i]->y;
            }
            stuff->send_event = (send_event && !i) ? 1 : 0;

            result = ProcXvShmPutImage(client);
        }
    }
    return result;
}
