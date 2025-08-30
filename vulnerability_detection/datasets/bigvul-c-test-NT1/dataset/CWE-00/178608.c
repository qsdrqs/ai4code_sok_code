XineramaXvShmPutImage(ClientPtr client)
 {
     REQUEST(xvShmPutImageReq);
     PanoramiXRes *buffer1, *buffer2, *buffer3;
    Bool send_event = stuff->send_event;
     Bool isRoot;
     int result, i, x, y;
 
     REQUEST_SIZE_MATCH(xvShmPutImageReq);
 
     result = dixLookupResourceByClass((void **) &buffer1, stuff->drawable,
                                       XRC_DRAWABLE, client, DixWriteAccess);
     if (result != Success)
    result = dixLookupResourceByType((void **) &buffer2, stuff->gc,
                                     XRT_GC, client, DixReadAccess);
    if (result != Success)
        return result;

    result = dixLookupResourceByType((void **) &buffer3, stuff->port,
                                     XvXRTPort, client, DixReadAccess);
    if (result != Success)
        return result;

    isRoot = (buffer1->type == XRT_WINDOW) && buffer1->u.win.root;

    x = stuff->drw_x;
    y = stuff->drw_y;

    FOR_NSCREENS_BACKWARD(i) {
        if (buffer3->info[i].id) {
            stuff->drawable = buffer1->info[i].id;
            stuff->port = buffer3->info[i].id;
            stuff->gc = buffer2->info[i].id;
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
