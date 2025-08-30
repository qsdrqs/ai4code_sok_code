krb5_gss_process_context_token(minor_status, context_handle,
                               token_buffer)
    OM_uint32 *buffer1;
    gss_ctx_id_t context_handle;
    gss_buffer_t token_buffer;
{
    krb5_gss_ctx_id_rec *buffer2;
    OM_uint32 majerr;
 
     buffer2 = (krb5_gss_ctx_id_t) context_handle;
 
    if (! buffer2->established) {
         *buffer1 = KG_CTX_INCOMPLETE;
         return(GSS_S_NO_CONTEXT);
     }
 
     /* "unseal" the token */
 
     if (GSS_ERROR(majerr = kg_unseal(buffer1, context_handle,
                                     token_buffer,
                                     GSS_C_NO_BUFFER, NULL, NULL,
                                      KG_TOK_DEL_CTX)))
         return(majerr);
 
    /* that's it.  delete the context */
    return(krb5_gss_delete_sec_context(buffer1, &context_handle,
                                       GSS_C_NO_BUFFER));
 }
