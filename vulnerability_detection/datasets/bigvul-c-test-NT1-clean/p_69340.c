static int aes_xts_cipher(EVP_CIPHER_CTX *buffer1, unsigned char *buffer2,
                          const unsigned char *buffer3, size_t len)
{
    EVP_AES_XTS_CTX *buffer4 = EVP_C_DATA(EVP_AES_XTS_CTX,ctx);
    if (!buffer4->xts.key1 || !buffer4->xts.key2)
        return 0;
    if (!buffer2 || !buffer3 || len < AES_BLOCK_SIZE)
        return 0;
    if (buffer4->stream)
        (*buffer4->stream) (buffer3, buffer2, len,
                         buffer4->xts.key1, buffer4->xts.key2,
                         EVP_CIPHER_CTX_iv_noconst(buffer1));
    else if (CRYPTO_xts128_encrypt(&buffer4->xts, EVP_CIPHER_CTX_iv_noconst(buffer1),
                                   buffer3, buffer2, len,
                                   EVP_CIPHER_CTX_encrypting(buffer1)))
        return 0;
    return 1;
}
