ProcQueryColors(ClientPtr client)
{
    ColormapPtr pcmp;
    int rc;

    REQUEST(xQueryColorsReq);

    REQUEST_AT_LEAST_SIZE(xQueryColorsReq);
    rc = dixLookupResourceByType((void **) &pcmp, stuff->cmap, RT_COLORMAP,
                                 client, DixReadAccess);
    if (rc == Success) {
        int count;
        xrgb *buffer1;
        xQueryColorsReply qcr;

        count =
            bytes_to_int32((client->req_len << 2) - sizeof(xQueryColorsReq));
        buffer1 = calloc(1, count * sizeof(xrgb));
        if (!buffer1 && count)
            return BadAlloc;
        if ((rc =
             QueryColors(pcmp, count, (Pixel *) &stuff[1], buffer1, client))) {
            free(buffer1);
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
            WriteSwappedDataToClient(client, count * sizeof(xrgb), buffer1);
        }
        free(buffer1);
        return Success;

    }
    else {
        client->errorValue = stuff->cmap;
        return rc;
    }
}
