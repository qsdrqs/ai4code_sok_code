#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/err.h>
#include <openssl/objects.h>
#include <openssl/pem.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to handle OpenSSL errors and abort the program
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// Function to sign a message using the private key
int sign_data(EVP_PKEY *pkey, const char *msg, size_t msg_len, unsigned char **sig) {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    if (!ctx) handle_errors();

    if (EVP_SignInit_ex(ctx, EVP_sha1(), NULL) <= 0) handle_errors();
    if (EVP_SignUpdate(ctx, msg, msg_len) <= 0) handle_errors();

    // Get the maximum size of the signature
    int sig_len = EVP_PKEY_size(pkey);
    *sig = OPENSSL_malloc(sig_len);
    if (!*sig) handle_errors();

    if (EVP_SignFinal(ctx, *sig, (unsigned int *)&sig_len, pkey) <= 0) handle_errors();

    EVP_MD_CTX_free(ctx);
    return sig_len;
}

// Function to verify a signature using the public key
int verify_data(EVP_PKEY *pkey, const char *msg, size_t msg_len, const unsigned char *sig, int sig_len) {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    if (!ctx) handle_errors();

    if (EVP_VerifyInit_ex(ctx, EVP_sha1(), NULL) <= 0) handle_errors();
    if (EVP_VerifyUpdate(ctx, msg, msg_len) <= 0) handle_errors();

    int ok = EVP_VerifyFinal(ctx, (unsigned char *)sig, sig_len, pkey);
    EVP_MD_CTX_free(ctx);
    return ok;
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();

    // Create a context for EC key generation
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!ctx) handle_errors();

    if (EVP_PKEY_keygen_init(ctx) <= 0) handle_errors();

    // Set the curve to SECP256k1 (used by default in Python's ecdsa)
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(ctx, NID_secp256k1) <= 0) handle_errors();

    EVP_PKEY *pkey = NULL;
    if (EVP_PKEY_keygen(ctx, &pkey) <= 0) handle_errors();

    EVP_PKEY_CTX_free(ctx);

    // Sign the message "hello world"
    const char *message = "hello world";
    size_t message_len = strlen(message);
    unsigned char *signature;
    int signature_len = sign_data(pkey, message, message_len, &signature);

    printf("Signature length: %d\n", signature_len);

    // Try to verify the signature with a different message ("hello worfld")
    const char *wrong_message = "hello worfld";
    size_t wrong_len = strlen(wrong_message);
    int verify_result = verify_data(pkey, wrong_message, wrong_len, signature, signature_len);

    printf("Verification result: %d\n", verify_result);

    // Clean up
    OPENSSL_free(signature);
    EVP_PKEY_free(pkey);

    return 0;
}