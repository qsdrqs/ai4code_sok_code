int encrypt(
    const unsigned char *key, size_t keylen,
    const unsigned char *plaintext, size_t plaintext_len,
    unsigned char **nonce, unsigned char **tag, unsigned char **ciphertext
) {
    int ret;
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_aes_context aes_ctx;

    // Allocate memory for nonce, ciphertext, and tag
    *nonce = calloc(16, sizeof(unsigned char));
    if (!*nonce) {
        ret = -1;
        goto err;
    }

    *ciphertext = malloc(plaintext_len);
    if (!*ciphertext) {
        ret = -1;
        goto free_nonce;
    }

    *tag = malloc(16);
    if (!*tag) {
        ret = -1;
        goto free_ciphertext;
    }

    // Initialize entropy and CTR-DRBG for secure random number generation
    mbedtls_entropy_init(&entropy);
    mbedtls_ctr_drbg_init(&ctr_drbg);

    const char *pers = "aes_eax_encrypt";
    ret = mbedtls_ctr_drbg_seed(&ctr_drbg, mbedtls_entropy_func, &entropy,
                                (const unsigned char *)pers, strlen(pers));
    if (ret != 0) {
        goto free_all;
    }

    // Generate a 16-byte random nonce
    ret = mbedtls_ctr_drbg_random(&ctr_drbg, *nonce, 16);
    if (ret != 0) {
        goto free_all;
    }

    // Initialize AES context and set the encryption key
    mbedtls_aes_init(&aes_ctx);
    ret = mbedtls_aes_setkey_enc(&aes_ctx, key, keylen * 8);
    if (ret != 0) {
        goto aes_err;
    }

    // Perform EAX encryption
    ret = mbedtls_aes_eax_encrypt(&aes_ctx, *nonce, 16,
                                  NULL, 0,
                                  plaintext, plaintext_len,
                                  *ciphertext,
                                  *tag, 16);

aes_err:
    mbedtls_aes_free(&aes_ctx);
free_all:
    mbedtls_ctr_drbg_free(&ctr_drbg);
    mbedtls_entropy_free(&entropy);
    if (ret != 0) {
        free(*tag);
        free(*ciphertext);
        free(*nonce);
        *tag = *ciphertext = *nonce = NULL;
    }
    return ret;

free_ciphertext:
    free(*ciphertext);
free_nonce:
    free(*nonce);
err:
    return ret;
}