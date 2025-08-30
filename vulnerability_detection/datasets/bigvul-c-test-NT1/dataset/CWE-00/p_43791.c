iakerb_gss_wrap_iov_length(OM_uint32 *buffer1,
                           gss_ctx_id_t context_handle, int conf_req_flag,
                           gss_qop_t qop_req, int *buffer2,
                           gss_iov_buffer_desc *buffer3, int iov_count)
{
    iakerb_ctx_id_t ctx = (iakerb_ctx_id_t)context_handle;

    if (ctx->gssc == GSS_C_NO_CONTEXT)
        return GSS_S_NO_CONTEXT;

    return krb5_gss_wrap_iov_length(buffer1, ctx->gssc, conf_req_flag,
                                    qop_req, buffer2, buffer3, iov_count);
}
