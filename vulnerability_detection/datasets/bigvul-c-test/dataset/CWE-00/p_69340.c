static int aes_xts_cipher(EVP_CIPHER_CTX *ctx, unsigned char *out,
                          const unsigned char *in, size_t len)
{
    EVP_AES_XTS_CTX *xctx = EVP_C_DATA(EVP_AES_XTS_CTX,ctx);
    if (!xctx->xts.key1 || !xctx->xts.key2)
        return 0;
    if (!out || !in || len < AES_BLOCK_SIZE)
        return 0;
    if (xctx->stream)
        (*xctx->stream) (in, out, len,
                         xctx->xts.key1, xctx->xts.key2,
                         EVP_CIPHER_CTX_iv_noconst(ctx));
    else if (CRYPTO_xts128_encrypt(&xctx->xts, EVP_CIPHER_CTX_iv_noconst(ctx),
                                   in, out, len,
                                   EVP_CIPHER_CTX_encrypting(ctx)))
        return 0;
    return 1;
}
