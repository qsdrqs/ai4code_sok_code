#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a random ECDSA private key
EC_KEY* generate_private_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to generate private key\n");
        return NULL;
    }

    BIGNUM* private_key = BN_new();
    if (!private_key) {
        printf("Failed to allocate private key\n");
        EC_KEY_free(key);
        return NULL;
    }

    // Generate a random private key
    if (!BN_rand_range(private_key, EC_get_order(EC_KEY_get0_group(key), NULL, NULL))) {
        printf("Failed to generate random private key\n");
        BN_free(private_key);
        EC_KEY_free(key);
        return NULL;
    }

    EC_KEY_set_private_key(key, private_key);
    BN_free(private_key);

    return key;
}

// Function to sign a message using the provided private key
unsigned char* sign(const char* message, EC_KEY* key, int* signature_len) {
    const unsigned char* message_bytes = (const unsigned char*)message;
    size_t message_len = strlen(message);

    ECDSA_SIG* signature = ECDSA_do_sign(message_bytes, message_len, key);
    if (!signature) {
        printf("Failed to generate signature\n");
        return NULL;
    }

    // Get the signature length
    *signature_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        printf("Failed to allocate signature memory\n");
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert the signature to bytes
    if (!ECDSA_SIG_to_bytes(signature_bytes, *signature_len, signature)) {
        printf("Failed to convert signature to bytes\n");
        free(signature_bytes);
        ECDSA_SIG_free(signature);
        return NULL;
    }

    ECDSA_SIG_free(signature);

    return signature_bytes;
}

int main() {
    // Generate a random private key
    EC_KEY* key = generate_private_key();
    if (!key) {
        return 1;
    }

    // Sign a message
    const char* message = "something";
    int signature_len;
    unsigned char* signature = sign(message, key, &signature_len);
    if (!signature) {
        EC_KEY_free(key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (int i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    free(signature);
    EC_KEY_free(key);

    return 0;
}