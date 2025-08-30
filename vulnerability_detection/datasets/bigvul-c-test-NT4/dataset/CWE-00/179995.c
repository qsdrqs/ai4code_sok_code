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

krb5_gss_process_context_token(minor_status, context_handle,
                               token_buffer)
    OM_uint32 *minor_status;
    gss_ctx_id_t context_handle;
    gss_buffer_t token_buffer;
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    krb5_gss_ctx_id_rec *ctx;
    OM_uint32 majerr;
 
     ctx = (krb5_gss_ctx_id_t) context_handle;
 
    if (! ctx->established) {
         *minor_status = KG_CTX_INCOMPLETE;
         return(GSS_S_NO_CONTEXT);
     }
 
     /* "unseal" the token */
 
     if (GSS_ERROR(majerr = kg_unseal(minor_status, context_handle,
                                     token_buffer,
                                     GSS_C_NO_BUFFER, NULL, NULL,
                                      KG_TOK_DEL_CTX)))
         return(majerr);
 
    /* that's it.  delete the context */
    return(krb5_gss_delete_sec_context(minor_status, &context_handle,
                                       GSS_C_NO_BUFFER));
 }
