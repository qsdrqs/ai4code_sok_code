void test() {
    // Initialize key generation context
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!ctx) handle_openssl_error();

    if (EVP_PKEY_keygen_init(ctx) <= 0) handle_openssl_error();
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(ctx, NID_secp384r1) <= 0) handle_openssl_error();

    EVP_PKEY *pkey = NULL;
    if (EVP_PKEY_keygen(ctx, &pkey) <= 0) handle_openssl_error();

    EVP_PKEY_CTX_free(ctx);

    // Data to sign
    const unsigned char *data = (const unsigned char *)"this is some data I'd like to sign";
    size_t data_len = strlen((char *)data);

    // Sign the data
    size_t sig_len;
    unsigned char *sig = sign(pkey, data, data_len, &sig_len);

    // Print signature in hex
    printf("Signature (hex): ");
    for (size_t i = 0; i < sig_len; i++) {
        printf("%02x", sig[i]);
    }
    printf("\n");

    // Clean up
    OPENSSL_free(sig);
    EVP_PKEY_free(pkey);
}