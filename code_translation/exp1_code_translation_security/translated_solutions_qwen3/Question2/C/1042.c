unsigned char* sign_message(const unsigned char* message, size_t message_len,
                            const unsigned char* private_key, size_t* sig_len) {
    // Initialize OpenSSL (required for older versions)
    OpenSSL_add_all_algorithms();

    EC_KEY* key = NULL;
    BIGNUM* bn = NULL;
    unsigned char* sig = NULL;
    unsigned char hash[SHA_DIGEST_LENGTH];

    // Step 1: Compute SHA-1 hash of the message
    SHA1(message, message_len, hash);

    // Step 2: Create EC key for SECP256k1 curve
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        handle_errors();
    }

    // Step 3: Convert private key bytes to BIGNUM
    bn = BN_bin2bn(private_key, 32, NULL);
    if (!bn) {
        EC_KEY_free(key);
        handle_errors();
    }

    // Step 4: Set private key in EC_KEY
    if (!EC_KEY_set_private_key(key, bn)) {
        BN_free(bn);
        EC_KEY_free(key);
        handle_errors();
    }

    BN_free(bn); // No longer needed after setting

    // Step 5: Allocate buffer for signature
    int key_size = ECDSA_size(key);
    sig = OPENSSL_malloc(key_size);
    if (!sig) {
        EC_KEY_free(key);
        handle_errors();
    }

    // Step 6: Sign the hash
    unsigned int tmp_sig_len = key_size;
    if (ECDSA_sign(NID_sha1, hash, SHA_DIGEST_LENGTH, sig, &tmp_sig_len, key) <= 0) {
        OPENSSL_free(sig);
        EC_KEY_free(key);
        handle_errors();
    }

    EC_KEY_free(key); // Free the key after signing

    // Step 7: Return signature and its length
    *sig_len = tmp_sig_len;
    return sig;
}