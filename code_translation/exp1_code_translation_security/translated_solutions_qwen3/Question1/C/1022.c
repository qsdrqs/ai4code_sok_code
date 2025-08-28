int decrypt(const char *encrypted, const unsigned char key[32], char **decrypted) {
    int len = strlen(encrypted);
    int pad = (4 - (len % 4)) % 4;
    char *b64 = malloc(len + pad + 1);
    if (!b64) return -1;
    strcpy(b64, encrypted);
    for (int i = 0; i < pad; i++) strcat(b64, "=");

    for (int i = 0; b64[i]; i++) {
        if (b64[i] == '-') b64[i] = '+';
        else if (b64[i] == '_') b64[i] = '/';
    }

    int decoded_len = EVP_DecodeBlock((unsigned char *)b64, (const unsigned char *)encrypted, len);
    if (decoded_len <= 0) {
        free(b64);
        return -1;
    }

    if (decoded_len < 57) {
        free(b64);
        return -1;
    }

    uint8_t header = ((uint8_t *)b64)[0];
    if (header != 0x80) {
        free(b64);
        return -1;
    }

    uint8_t *token = (uint8_t *)b64;
    uint8_t *timestamp_bytes = token + 1;
    uint8_t *iv = token + 9;
    uint8_t *ciphertext = token + 25;
    uint8_t *hmac = token + decoded_len - 32;

    size_t ciphertext_len = decoded_len - 25 - 32;

    const unsigned char *enc_key = key;
    const unsigned char *sig_key = key + 16;
    unsigned char expected_hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC_CTX *hmac_ctx = HMAC_CTX_new();
    HMAC_Init_ex(hmac_ctx, sig_key, 16, EVP_sha256(), NULL);
    HMAC_Update(hmac_ctx, &header, 1);
    HMAC_Update(hmac_ctx, timestamp_bytes, 8);
    HMAC_Update(hmac_ctx, iv, 16);
    HMAC_Update(hmac_ctx, ciphertext, ciphertext_len);
    HMAC_Final(hmac_ctx, expected_hmac, &hmac_len);
    HMAC_CTX_free(hmac_ctx);

    if (CRYPTO_memcmp(hmac, expected_hmac, 32) != 0) {
        free(b64);
        return -1;
    }

    AES_KEY aes_key;
    AES_set_decrypt_key(enc_key, 128, &aes_key);
    size_t padded_len = ciphertext_len;
    unsigned char *padded = malloc(padded_len);
    if (!padded) {
        free(b64);
        return -1;
    }
    AES_cbc_encrypt(ciphertext, padded, padded_len, &aes_key, iv, AES_DECRYPT);

    uint8_t pad_val = padded[padded_len - 1];
    if (pad_val > 16 || pad_val == 0) {
        free(padded);
        free(b64);
        return -1;
    }

    int valid = 1;
    for (size_t i = 0; i < pad_val; i++) {
        if (padded[padded_len - pad_val + i] != pad_val) {
            valid = 0;
            break;
        }
    }
    if (!valid) {
        free(padded);
        free(b64);
        return -1;
    }

    size_t plain_len = padded_len - pad_val;
    *decrypted = malloc(plain_len + 1);
    if (!*decrypted) {
        free(padded);
        free(b64);
        return -1;
    }
    memcpy(*decrypted, padded, plain_len);
    (*decrypted)[plain_len] = '\0';

    free(padded);
    free(b64);
    return 0;
}