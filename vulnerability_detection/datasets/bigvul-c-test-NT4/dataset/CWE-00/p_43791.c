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

iakerb_gss_wrap_iov_length(OM_uint32 *minor_status,
                           gss_ctx_id_t context_handle, int conf_req_flag,
                           gss_qop_t qop_req, int *conf_state,
                           gss_iov_buffer_desc *iov, int iov_count)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    iakerb_ctx_id_t ctx = (iakerb_ctx_id_t)context_handle;

    if (ctx->gssc == GSS_C_NO_CONTEXT)
        return GSS_S_NO_CONTEXT;

    return krb5_gss_wrap_iov_length(minor_status, ctx->gssc, conf_req_flag,
                                    qop_req, conf_state, iov, iov_count);
}
