#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>  // For NID_secp384r1 and NID_sha384
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/evp.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to handle OpenSSL errors and abort
void handleErrors() {
    ERR_print_errors_fp(stderr);
    abort();
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate EC key pair using NIST P-384 (secp384r1)
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) handleErrors();

    if (!EC_KEY_generate_key(key)) handleErrors();

    // Message to sign
    const char *message = "hehehe";
    size_t message_len = strlen(message);

    // Compute SHA-384 hash of the message
    unsigned char digest[SHA384_DIGEST_LENGTH];
    SHA384((const unsigned char *)message, message_len, digest);

    // Sign the digest
    unsigned int signature_len;
    unsigned char *signature = OPENSSL_malloc(ECDSA_size(key));
    if (!signature) handleErrors();

    if (!ECDSA_sign(NID_sha384, digest, SHA384_DIGEST_LENGTH, signature, &signature_len, key)) {
        handleErrors();
    }

    // Verify the signature
    int verify_result = ECDSA_verify(NID_sha384, digest, SHA384_DIGEST_LENGTH, signature, signature_len, key);
    if (verify_result == 1) {
        printf("Verification succeeded.\n");
    } else if (verify_result == 0) {
        printf("Verification failed.\n");
    } else {
        handleErrors();
    }

    // Cleanup
    OPENSSL_free(signature);
    EC_KEY_free(key);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}