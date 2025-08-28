#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/bn.h>

// Function to sign a message using a given ECDSA private key
unsigned char* sign_message(const char* message, const unsigned char* private_key_hex, unsigned int* sig_len) {
    // Create a new EC_KEY object with the SECP256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object\n");
        return NULL;
    }

    // Convert the private key from hex to BIGNUM
    BIGNUM* priv_key_bn = BN_new();
    if (BN_hex2bn(&priv_key_bn, (const char*)private_key_hex) == 0) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Set the private key
    if (EC_KEY_set_private_key(ec_key, priv_key_bn) == 0) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(ec_key);
        BN_free(priv_key_bn);
        return NULL;
    }

    // Hash the message using SHA-256
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), message_hash);

    // Sign the message hash
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(ec_key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EC_KEY_free(ec_key);
        BN_free(priv_key_bn);
        return NULL;
    }

    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, signature, sig_len, ec_key) == 0) {
        fprintf(stderr, "Failed to sign message\n");
        free(signature);
        EC_KEY_free(ec_key);
        BN_free(priv_key_bn);
        return NULL;
    }

    // Clean up
    EC_KEY_free(ec_key);
    BN_free(priv_key_bn);

    return signature;
}

int main() {
    // Test function
    const char* message = "Hello, world!";
    const unsigned char* private_key_hex = (const unsigned char*)"18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
    unsigned int sig_len = 0;

    unsigned char* signature = sign_message(message, private_key_hex, &sig_len);
    if (signature != NULL) {
        printf("Signature: ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        free(signature);
    }

    return 0;
}