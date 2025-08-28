#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <openssl/md.h>
#include <openssl/pem.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to handle OpenSSL errors and abort execution
void handle_errors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

// Function to sign a message using ECDSA with SHA-1 and secp192r1 curve
unsigned char* sign(const char* message, EVP_PKEY* pkey, size_t* sig_len) {
    EVP_MD_CTX* mdctx = EVP_MD_CTX_new();
    if (!mdctx) handle_errors();

    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha1(), NULL, pkey) <= 0)
        handle_errors();

    if (EVP_DigestSignUpdate(mdctx, message, strlen(message)) <= 0)
        handle_errors();

    // Determine the required signature length
    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) <= 0)
        handle_errors();

    unsigned char* signature = OPENSSL_malloc(*sig_len);
    if (!signature) handle_errors();

    if (EVP_DigestSignFinal(mdctx, signature, sig_len) <= 0) {
        OPENSSL_free(signature);
        handle_errors();
    }

    EVP_MD_CTX_free(mdctx);
    return signature;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate EC key pair using secp192r1 (NIST P-192)
    EVP_PKEY_CTX* ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!ctx) handle_errors();

    if (EVP_PKEY_keygen_init(ctx) <= 0)
        handle_errors();

    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(ctx, NID_secp192r1) <= 0)
        handle_errors();

    EVP_PKEY* pkey = NULL;
    if (EVP_PKEY_keygen(ctx, &pkey) <= 0)
        handle_errors();

    EVP_PKEY_CTX_free(ctx);

    // Sign the message "something"
    size_t sig_len;
    unsigned char* signature = sign("something", pkey, &sig_len);

    // Print the signature in hexadecimal format
    printf("Signature: ");
    for (size_t i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    EVP_PKEY_free(pkey);
    OPENSSL_free(signature);
    EVP_cleanup();
    CRYPTO_cleanup_all_ex_data();
    ERR_free_strings();

    return 0;
}