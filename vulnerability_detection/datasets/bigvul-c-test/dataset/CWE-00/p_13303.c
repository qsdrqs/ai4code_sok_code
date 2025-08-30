pdf14_ctx_new(gs_int_rect *rect, int n_chan, bool additive, gx_device *dev)
{
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
