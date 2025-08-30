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

ProcQueryColors(ClientPtr client)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    ColormapPtr pcmp;
    int rc;

    REQUEST(xQueryColorsReq);

    REQUEST_AT_LEAST_SIZE(xQueryColorsReq);
    rc = dixLookupResourceByType((void **) &pcmp, stuff->cmap, RT_COLORMAP,
                                 client, DixReadAccess);
    if (rc == Success) {
        int count;
        xrgb *prgbs;
        xQueryColorsReply qcr;

        count =
            bytes_to_int32((client->req_len << 2) - sizeof(xQueryColorsReq));
        prgbs = calloc(1, count * sizeof(xrgb));
        if (!prgbs && count)
            return BadAlloc;
        if ((rc =
             QueryColors(pcmp, count, (Pixel *) &stuff[1], prgbs, client))) {
            free(prgbs);
            return rc;
        }
        qcr = (xQueryColorsReply) {
            .type = X_Reply,
            .sequenceNumber = client->sequence,
            .length = bytes_to_int32(count * sizeof(xrgb)),
            .nColors = count
        };
        WriteReplyToClient(client, sizeof(xQueryColorsReply), &qcr);
        if (count) {
            client->pSwapReplyFunc = (ReplySwapPtr) SQColorsExtend;
            WriteSwappedDataToClient(client, count * sizeof(xrgb), prgbs);
        }
        free(prgbs);
        return Success;

    }
    else {
        client->errorValue = stuff->cmap;
        return rc;
    }
}
