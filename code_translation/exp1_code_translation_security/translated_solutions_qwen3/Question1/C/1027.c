char *decrypt(const unsigned char *ciphertext, size_t ciphertext_len, const unsigned char *key, size_t key_len, size_t *out_len) {
    if (!ciphertext || !key || !out_len) return NULL;

    // Check ciphertext length is multiple of block size
    if (ciphertext_len % AES_BLOCK_SIZE != 0) {
        return NULL;
    }

    // Setup AES key
    AES_KEY aes_key;
    int bits;
    if (key_len == 16) bits = 128;
    else if (key_len == 24) bits = 192;
    else if (key_len == 32) bits = 256;
    else {
        return NULL; // Invalid key length
    }

    if (AES_set_decrypt_key(key, bits, &aes_key) < 0) {
        return NULL;
    }

    // Setup IV (all zeros)
    unsigned char iv[AES_BLOCK_SIZE] = {0};

    // Allocate decrypted buffer
    unsigned char *decrypted = (unsigned char *)malloc(ciphertext_len);
    if (!decrypted) return NULL;

    // Decrypt
    AES_cbc_encrypt(ciphertext, decrypted, ciphertext_len, &aes_key, iv, AES_DECRYPT);

    // Unpad
    unsigned char padding_length = decrypted[ciphertext_len - 1];
    if (padding_length == 0 || padding_length > AES_BLOCK_SIZE) {
        free(decrypted);
        return NULL;
    }

    // Validate padding
    for (size_t i = 0; i < padding_length; i++) {
        if (decrypted[ciphertext_len - padding_length + i] != padding_length) {
            free(decrypted);
            return NULL;
        }
    }

    size_t message_len = ciphertext_len - padding_length;
    char *message = (char *)malloc(message_len + 1);
    if (!message) {
        free(decrypted);
        return NULL;
    }

    memcpy(message, decrypted, message_len);
    message[message_len] = '\0'; // Null-terminate

    free(decrypted);
    *out_len = message_len;
    return message;
}