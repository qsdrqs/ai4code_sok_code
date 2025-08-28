#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

// Function to sign a message
unsigned char* sign(const unsigned char* msg, size_t msg_len, const EC_KEY* priv_key, size_t* signature_len) {
    // Create a new ECDSA signature
    ECDSA_SIG* signature = ECDSA_do_sign(msg, msg_len, priv_key);
    if (!signature) {
        return NULL;
    }

    // Get the signature length
    *signature_len = ECDSA_size(priv_key);

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert the signature to bytes
    ECDSA_SIG_to_bytes(signature_bytes, signature);

    // Free the ECDSA signature
    ECDSA_SIG_free(signature);

    return signature_bytes;
}

int main() {
    // Generate a private key
    EC_KEY* priv_key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!priv_key) {
        printf("Failed to generate private key\n");
        return 1;
    }

    // Set the private key
    BIGNUM* priv_bn = BN_new();
    BN_hex2bn(&priv_bn, "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
    EC_KEY_set_private_key(priv_key, priv_bn);
    BN_free(priv_bn);

    // Set the message
    const char* msg = "Hello, World!";
    size_t msg_len = strlen(msg);

    // Sign the message
    size_t signature_len;
    unsigned char* signature = sign((const unsigned char*)msg, msg_len, priv_key, &signature_len);
    if (!signature) {
        printf("Failed to sign message\n");
        EC_KEY_free(priv_key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free the signature and private key
    free(signature);
    EC_KEY_free(priv_key);

    return 0;
}