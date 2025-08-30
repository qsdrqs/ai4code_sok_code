iakerb_gss_wrap_iov_length(OM_uint32 *minor_status,
                           gss_ctx_id_t context_handle, int conf_req_flag,
                           gss_qop_t qop_req, int *conf_state,
                           gss_iov_buffer_desc *iov, int iov_count)
{
    iakerb_ctx_id_t ctx = (iakerb_ctx_id_t)context_handle;

    if (ctx->gssc == GSS_C_NO_CONTEXT)
        return GSS_S_NO_CONTEXT;

    return krb5_gss_wrap_iov_length(minor_status, ctx->gssc, conf_req_flag,
                                    qop_req, conf_state, iov, iov_count);
}
