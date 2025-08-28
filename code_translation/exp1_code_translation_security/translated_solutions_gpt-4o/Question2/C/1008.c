#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

/*
 * Sign message using ECDSA key
 */
unsigned char* sign_message(const char* message, const char* private_key_hex, unsigned int* sig_len) {
    EC_KEY *eckey = NULL;
    unsigned char *signature = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    BIGNUM *priv_bn = NULL;

    // Create a new EC key object with the SECP256k1 curve
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Failed to create new EC Key\n");
        return NULL;
    }

    // Convert the private key from hex string to BIGNUM
    priv_bn = BN_new();
    if (BN_hex2bn(&priv_bn, private_key_hex) == 0) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(eckey);
        return NULL;
    }

    // Set the private key
    if (EC_KEY_set_private_key(eckey, priv_bn) == 0) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Hash the message using SHA-256
    SHA256((unsigned char*)message, strlen(message), hash);

    // Allocate memory for the signature
    signature = OPENSSL_malloc(ECDSA_size(eckey));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Sign the hash
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, eckey) == 0) {
        fprintf(stderr, "Failed to sign message\n");
        OPENSSL_free(signature);
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Clean up
    EC_KEY_free(eckey);
    BN_free(priv_bn);

    return signature;
}

int main() {
    const char* message = "hello world";
    const char* private_key_hex = "12345678901234567890123456789012";
    unsigned int sig_len;
    unsigned char* signature = sign_message(message, private_key_hex, &sig_len);

    if (signature != NULL) {
        printf("Signature: ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        OPENSSL_free(signature);
    }

    return 0;
}