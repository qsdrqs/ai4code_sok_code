char* encrypt(const unsigned char* message, size_t message_len, const unsigned char* key) {
    unsigned char aes_key[16], hmac_key[16];
    memcpy(aes_key, key, 16);
    memcpy(hmac_key, key + 16, 16);

    // Timestamp (64-bit big-endian)
    uint64_t timestamp = time(NULL);
    unsigned char be_timestamp[8];
    be_timestamp[0] = (timestamp >> 56) & 0xFF;
    be_timestamp[1] = (timestamp >> 48) & 0xFF;
    be_timestamp[2] = (timestamp >> 40) & 0xFF;
    be_timestamp[3] = (timestamp >> 32) & 0xFF;
    be_timestamp[4] = (timestamp >> 24) & 0xFF;
    be_timestamp[5] = (timestamp >> 16) & 0xFF;
    be_timestamp[6] = (timestamp >> 8) & 0xFF;
    be_timestamp[7] = timestamp & 0xFF;

    // Generate IV
    unsigned char iv[AES_BLOCK_SIZE];
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) return NULL;

    // PKCS#7 padding
    size_t padded_len = (message_len + AES_BLOCK_SIZE) & ~ (size_t)(AES_BLOCK_SIZE - 1);
    unsigned char* padded = (unsigned char*)malloc(padded_len);
    if (!padded) return NULL;
    memcpy(padded, message, message_len);
    size_t pad_byte = padded_len - message_len;
    memset(padded + message_len, pad_byte, pad_byte);

    // AES encryption
    AES_KEY aeskey;
    if (AES_set_encrypt_key(aes_key, 128, &aeskey) < 0) {
        free(padded);
        return NULL;
    }
    unsigned char* ciphertext = (unsigned char*)malloc(padded_len);
    if (!ciphertext) {
        free(padded);
        return NULL;
    }
    AES_cbc_encrypt(padded, ciphertext, padded_len, &aeskey, iv, AES_ENCRYPT);
    free(padded);

    // HMAC-SHA256
    unsigned char hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC_CTX* ctx = HMAC_CTX_new();
    HMAC_Init_ex(ctx, hmac_key, 16, EVP_sha256(), NULL);
    unsigned char version = 0x00;
    HMAC_Update(ctx, &version, 1);
    HMAC_Update(ctx, be_timestamp, 8);
    HMAC_Update(ctx, iv, AES_BLOCK_SIZE);
    HMAC_Update(ctx, ciphertext, padded_len);
    HMAC_Final(ctx, hmac, &hmac_len);
    HMAC_CTX_free(ctx);
    free(ciphertext);

    // Build token
    size_t token_len = 1 + 8 + AES_BLOCK_SIZE + padded_len + hmac_len;
    unsigned char* token = (unsigned char*)malloc(token_len);
    if (!token) return NULL;
    size_t offset = 0;
    memcpy(token + offset, &version, 1); offset += 1;
    memcpy(token + offset, be_timestamp, 8); offset += 8;
    memcpy(token + offset, iv, AES_BLOCK_SIZE); offset += 16;
    memcpy(token + offset, ciphertext, padded_len); offset += padded_len;
    memcpy(token + offset, hmac, hmac_len); offset += 32;

    // Base64url encode
    char* encrypted = base64url_encode(token, token_len);
    free(token);
    return encrypted;
}