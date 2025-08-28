#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Create a new EC key pair
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(ec_key) != 1) {
        fprintf(stderr, "Error generating EC key\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    // Message to be signed
    const char *message = "Test message";
    unsigned char *signature = NULL;
    unsigned int sig_len;

    // Sign the message
    sig_len = ECDSA_size(ec_key);
    signature = OPENSSL_malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    if (ECDSA_sign(0, (const unsigned char *)message, strlen(message), signature, &sig_len, ec_key) != 1) {
        fprintf(stderr, "Error signing message\n");
        OPENSSL_free(signature);
        EC_KEY_free(ec_key);
        return 1;
    }

    // Verify the signature
    int verify_status = ECDSA_verify(0, (const unsigned char *)message, strlen(message), signature, sig_len, ec_key);
    if (verify_status == 1) {
        printf("Signature is valid\n");
    } else {
        printf("Signature is invalid\n");
    }

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(ec_key);
    EVP_cleanup();

    return 0;
}