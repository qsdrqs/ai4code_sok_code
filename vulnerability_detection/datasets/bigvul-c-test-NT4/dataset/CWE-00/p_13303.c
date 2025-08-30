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

pdf14_ctx_new(gs_int_rect *rect, int n_chan, bool additive, gx_device *dev)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    pdf14_ctx *result;
    pdf14_buf *buf;
    gs_memory_t	*memory = dev->memory;
    bool has_tags = dev->graphics_type_tag & GS_DEVICE_ENCODES_TAGS;
    pdf14_device *pdev = (pdf14_device *)dev;

    result = gs_alloc_struct(memory, pdf14_ctx, &st_pdf14_ctx, "pdf14_ctx_new");
    if (result == NULL)
        return result;
    /* Note:  buffer creation expects alpha to be in number of channels */
    buf = pdf14_buf_new(rect, has_tags, false, false, false, n_chan + 1,
                        pdev->devn_params.page_spot_colors, memory);
    if (buf == NULL) {
        gs_free_object(memory, result, "pdf14_ctx_new");
        return NULL;
    }
    if_debug4m('v', memory,
               "[v]base buf: %d x %d, %d color channels, %d planes\n",
               buf->rect.q.x, buf->rect.q.y, buf->n_chan, buf->n_planes);
    if (buf->data != NULL) {
        if (buf->has_tags) {
            memset(buf->data, 0, buf->planestride * (buf->n_planes-1));
        } else {
            memset(buf->data, 0, buf->planestride * buf->n_planes);
        }
    }
    buf->saved = NULL;
    result->stack = buf;
    result->mask_stack = pdf14_mask_element_new(memory);
    result->mask_stack->rc_mask = pdf14_rcmask_new(memory);
    result->n_chan = n_chan;
    result->memory = memory;
    result->rect = *rect;
    result->additive = additive;
    result->smask_depth = 0;
    result->smask_blend = false;
    return result;
}
