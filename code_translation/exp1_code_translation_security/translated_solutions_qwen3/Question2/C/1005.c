#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

// Function to sign a message using ECDSA and return the DER-encoded signature
unsigned char* sign(const unsigned char *msg, size_t msg_len, EC_KEY *key, size_t *sig_len) {
    // Compute SHA-1 hash of the message
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, hash);

    // Sign the hash
    ECDSA_SIG *ecdsa_sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (!ecdsa_sig) {
        handle_errors();
    }

    // Verify the signature
    int verify_result = ECDSA_do_verify(hash, SHA_DIGEST_LENGTH, ecdsa_sig, key);
    if (verify_result != 1) {
        ECDSA_SIG_free(ecdsa_sig);
        handle_errors();
    }

    // Convert the signature to DER format
    unsigned char *der_sig = NULL;
    int der_len = i2d_ECDSA_SIG(ecdsa_sig, &der_sig);
    ECDSA_SIG_free(ecdsa_sig);
    if (der_len <= 0) {
        handle_errors();
    }

    *sig_len = der_len;
    return der_sig;
}

// Test function to generate a key and sign a message
void test() {
    // Initialize OpenSSL
    OPENSSL_init_crypto(OPENSSL_INIT_ADD_ALL_CIPHERS | OPENSSL_INIT_ADD_ALL_DIGESTS, NULL);
    ERR_load_crypto_strings();

    // Generate ECDSA key pair using secp256k1 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        handle_errors();
    }

    if (!EC_KEY_generate_key(key)) {
        handle_errors();
    }

    // Sign the message "message"
    const unsigned char *msg = (const unsigned char *)"message";
    size_t msg_len = 7;
    size_t sig_len;
    unsigned char *signature = sign(msg, msg_len, key, &sig_len);

    // Print the signature in hexadecimal format
    printf("Signature (DER, hex): ");
    for (size_t i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(key);
    ERR_free_strings();
}

int main() {
    test();
    return 0;
}