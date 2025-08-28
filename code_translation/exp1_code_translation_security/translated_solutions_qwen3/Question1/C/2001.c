unsigned char* encrypt(const unsigned char* msg, size_t msg_len, const unsigned char* key, size_t* encrypted_len) {
    if (!msg || !key || !encrypted_len) return NULL;

    // Split the key into encryption and signing keys
    unsigned char enc_key[ENC_KEY_SIZE];
    unsigned char sig_key[SIG_KEY_SIZE];
    memcpy(enc_key, key, ENC_KEY_SIZE);
    memcpy(sig_key, key + ENC_KEY_SIZE, SIG_KEY_SIZE);

    // Generate a random IV
    unsigned char iv[IV_SIZE];
    if (!RAND_bytes(iv, IV_SIZE)) return NULL;

    // Apply PKCS7 padding
    size_t padding_len = AES_BLOCK_SIZE - (msg_len % AES_BLOCK_SIZE);
    size_t padded_len = msg_len + padding_len;
    unsigned char* padded = malloc(padded_len);
    if (!padded) return NULL;
    memcpy(padded, msg, msg_len);
    memset(padded + msg_len, padding_len, padding_len);

    // AES-CBC encryption
    AES_KEY aes_key;
    if (AES_set_encrypt_key(enc_key, 128, &aes_key) < 0) {
        free(padded);
        return NULL;
    }

    unsigned char* encrypted = malloc(padded_len);
    if (!encrypted) {
        free(padded);
        return NULL;
    }

    unsigned char enc_iv[IV_SIZE];
    memcpy(enc_iv, iv, IV_SIZE);
    AES_cbc_encrypt(padded, encrypted, padded_len, &aes_key, enc_iv, AES_ENCRYPT);

    free(padded);

    // Create timestamp (64-bit big-endian)
    uint64_t now = time(NULL);
    unsigned char timestamp[TIMESTAMP_SIZE];
    for (int i = 0; i < TIMESTAMP_SIZE; i++) {
        timestamp[i] = (now >> (56 - i * 8)) & 0xFF;
    }

    // Prepare data to sign: version (0x80), timestamp, IV, encrypted data
    size_t data_to_sign_len = 1 + TIMESTAMP_SIZE + IV_SIZE + padded_len;
    unsigned char* data_to_sign = malloc(data_to_sign_len);
    if (!data_to_sign) {
        free(encrypted);
        return NULL;
    }

    data_to_sign[0] = 0x80; // Version
    memcpy(data_to_sign + 1, timestamp, TIMESTAMP_SIZE);
    memcpy(data_to_sign + 1 + TIMESTAMP_SIZE, iv, IV_SIZE);
    memcpy(data_to_sign + 25, encrypted, padded_len); // 1 + 8 + 16 = 25

    free(encrypted);

    // Compute HMAC-SHA256
    unsigned char hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), sig_key, SIG_KEY_SIZE, data_to_sign, data_to_sign_len, hmac, &hmac_len);

    // Allocate memory for the final token
    *encrypted_len = data_to_sign_len + HMAC_SIZE;
    unsigned char* token = malloc(*encrypted_len);
    if (!token) {
        free(data_to_sign);
        return NULL;
    }

    // Copy data and HMAC into the token
    memcpy(token, data_to_sign, data_to_sign_len);
    memcpy(token + data_to_sign_len, hmac, HMAC_SIZE);

    free(data_to_sign);
    return token;
}