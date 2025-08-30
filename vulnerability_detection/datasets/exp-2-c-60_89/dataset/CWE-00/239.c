void vrend_renderer_blit(struct vrend_context *ctx,
                         uint32_t dst_handle, uint32_t src_handle,
                         const struct pipe_blit_info *info)
{
   struct vrend_resource *src_res, *dst_res;
   src_res = vrend_renderer_ctx_res_lookup(ctx, src_handle);
   dst_res = vrend_renderer_ctx_res_lookup(ctx, dst_handle);

   if (!src_res) {
      report_context_error(ctx, VIRGL_ERROR_CTX_ILLEGAL_RESOURCE, src_handle);
      return;
   }
   if (!dst_res) {
      report_context_error(ctx, VIRGL_ERROR_CTX_ILLEGAL_RESOURCE, dst_handle);
      return;
   }

   if (ctx->in_error)
      return;

   if (info->render_condition_enable == false)
      vrend_pause_render_condition(ctx, true);

   VREND_DEBUG(dbg_blit, ctx, "BLIT: rc:%d scissor:%d filter:%d alpha:%d mask:0x%x\n"
                                   "  From %s(%s) ms:%d [%d, %d, %d]+[%d, %d, %d] lvl:%d\n"
                                   "  To   %s(%s) ms:%d [%d, %d, %d]+[%d, %d, %d] lvl:%d\n",
                                   info->render_condition_enable, info->scissor_enable,
                                   info->filter, info->alpha_blend, info->mask,
                                   util_format_name(src_res->base.format),
                                   util_format_name(info->src.format),
                                   src_res->base.nr_samples,
                                   info->src.box.x, info->src.box.y, info->src.box.z,
                                   info->src.box.width, info->src.box.height, info->src.box.depth,
                                   info->src.level,
                                   util_format_name(dst_res->base.format),
                                   util_format_name(info->dst.format),
                                   dst_res->base.nr_samples,
                                   info->dst.box.x, info->dst.box.y, info->dst.box.z,
                                   info->dst.box.width, info->dst.box.height, info->dst.box.depth,
                                   info->dst.level);

   /* The Gallium blit function can be called for a general blit that may
    * scale, convert the data, and apply some rander states, or it is called via
    * glCopyImageSubData. If the src or the dst image are equal, or the two
    * images formats are the same, then Galliums such calles are redirected
    * to resource_copy_region, in this case and if no render states etx need
    * to be applied, forward the call to glCopyImageSubData, otherwise do a
    * normal blit. */
   if (has_feature(feat_copy_image) &&
       (!info->render_condition_enable || !ctx->sub->cond_render_gl_mode) &&
       format_is_copy_compatible(info->src.format,info->dst.format, false) &&
       !info->scissor_enable && (info->filter == PIPE_TEX_FILTER_NEAREST) &&
       !info->alpha_blend && (info->mask == PIPE_MASK_RGBA) &&
       src_res->base.nr_samples == dst_res->base.nr_samples &&
       info->src.box.width == info->dst.box.width &&
       info->src.box.height == info->dst.box.height &&
       info->src.box.depth == info->dst.box.depth) {
      VREND_DEBUG(dbg_blit, ctx,  "  Use glCopyImageSubData\n");
      vrend_copy_sub_image(src_res, dst_res, info->src.level, &info->src.box,
                           info->dst.level, info->dst.box.x, info->dst.box.y,
                           info->dst.box.z);
   } else {
      VREND_DEBUG(dbg_blit, ctx, "  Use blit_int\n");
      vrend_renderer_blit_int(ctx, src_res, dst_res, info);
   }

   if (info->render_condition_enable == false)
      vrend_pause_render_condition(ctx, false);
}