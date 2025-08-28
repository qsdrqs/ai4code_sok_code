#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/err.h>

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate EC key pair
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (ec_key == NULL) handleErrors();

    if (EC_KEY_generate_key(ec_key) != 1) handleErrors();

    // Get the private key
    const BIGNUM *priv_key = EC_KEY_get0_private_key(ec_key);
    if (priv_key == NULL) handleErrors();

    // Get the public key
    const EC_POINT *pub_key = EC_KEY_get0_public_key(ec_key);
    if (pub_key == NULL) handleErrors();

    // Message to be signed
    const char *message = "My test message";
    unsigned char *signature = NULL;
    unsigned int sig_len;

    // Sign the message
    sig_len = ECDSA_size(ec_key);
    signature = OPENSSL_malloc(sig_len);
    if (signature == NULL) handleErrors();

    if (ECDSA_sign(0, (const unsigned char *)message, strlen(message), signature, &sig_len, ec_key) != 1) {
        handleErrors();
    }

    // Print the signature in hex format
    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(ec_key);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}