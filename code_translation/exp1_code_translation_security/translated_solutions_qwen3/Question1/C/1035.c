void encrypt(const unsigned char *key, const unsigned char *plaintext, int plaintext_len,
             unsigned char **ciphertext, int *ciphertext_len,
             unsigned char **tag,
             unsigned char **nonce) {
    EVP_CIPHER_CTX *ctx;
    int iv_len;

    const EVP_CIPHER *cipher = EVP_aes_128_eax();

    ctx = EVP_CIPHER_CTX_new();
    if (!ctx)
        handleErrors();

    // Determine IV (nonce) length
    if (!EVP_EncryptInit_ex(ctx, cipher, NULL, NULL, NULL))
        handleErrors();

    iv_len = EVP_CIPHER_CTX_iv_length(ctx);

    // Generate random nonce
    *nonce = OPENSSL_malloc(iv_len);
    if (!*nonce || !RAND_bytes(*nonce, iv_len))
        handleErrors();

    // Initialize encryption with key and nonce
    if (!EVP_EncryptInit_ex(ctx, cipher, NULL, key, *nonce))
        handleErrors();

    // Allocate ciphertext buffer with extra space for block alignment
    *ciphertext = OPENSSL_malloc(plaintext_len + EVP_CIPHER_block_size(cipher));
    if (!*ciphertext)
        handleErrors();

    // Encrypt data
    if (!EVP_EncryptUpdate(ctx, *ciphertext, ciphertext_len, plaintext, plaintext_len))
        handleErrors();

    // Finalize encryption
    int final_len;
    if (!EVP_EncryptFinal_ex(ctx, *ciphertext + *ciphertext_len, &final_len))
        handleErrors();

    *ciphertext_len += final_len;

    // Get tag
    *tag = OPENSSL_malloc(16);
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_GET_TAG, 16, *tag))
        handleErrors();

    EVP_CIPHER_CTX_free(ctx);
}