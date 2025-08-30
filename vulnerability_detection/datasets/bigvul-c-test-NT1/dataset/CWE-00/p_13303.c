pdf14_ctx_new(gs_int_rect *buffer1, int n_chan, bool additive, gx_device *buffer2)
{
    pdf14_ctx *buffer3;
    pdf14_buf *buffer4;
    gs_memory_t	*buffer5 = dev->memory;
    bool has_tags = dev->graphics_type_tag & GS_DEVICE_ENCODES_TAGS;
    pdf14_device *buffer6 = (pdf14_device *)dev;

    buffer3 = gs_alloc_struct(buffer5, pdf14_ctx, &st_pdf14_ctx, "pdf14_ctx_new");
    if (buffer3 == NULL)
        return buffer3;
    /* Note:  buffer creation expects alpha to be in number of channels */
    buffer4 = pdf14_buf_new(buffer1, has_tags, false, false, false, n_chan + 1,
                        buffer6->devn_params.page_spot_colors, buffer5);
    if (buffer4 == NULL) {
        gs_free_object(buffer5, buffer3, "pdf14_ctx_new");
        return NULL;
    }
    if_debug4m('v', buffer5,
               "[v]base buf: %d x %d, %d color channels, %d planes\n",
               buffer4->rect.q.x, buffer4->rect.q.y, buffer4->n_chan, buffer4->n_planes);
    if (buffer4->data != NULL) {
        if (buffer4->has_tags) {
            memset(buffer4->data, 0, buffer4->planestride * (buffer4->n_planes-1));
        } else {
            memset(buffer4->data, 0, buffer4->planestride * buffer4->n_planes);
        }
    }
    buffer4->saved = NULL;
    buffer3->stack = buffer4;
    buffer3->mask_stack = pdf14_mask_element_new(buffer5);
    buffer3->mask_stack->rc_mask = pdf14_rcmask_new(buffer5);
    buffer3->n_chan = n_chan;
    buffer3->memory = buffer5;
    buffer3->rect = *buffer1;
    buffer3->additive = additive;
    buffer3->smask_depth = 0;
    buffer3->smask_blend = false;
    return buffer3;
}
