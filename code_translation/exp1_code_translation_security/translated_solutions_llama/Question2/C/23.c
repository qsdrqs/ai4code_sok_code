#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a new ECDSA key pair
EC_KEY* generate_ecdsa_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to create ECDSA key\n");
        return NULL;
    }

    // Generate a new private key
    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate ECDSA key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign a message using an ECDSA key
unsigned char* sign_message(const unsigned char* message, size_t message_len, EC_KEY* key) {
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, key);
    if (!sig) {
        printf("Failed to sign message\n");
        return NULL;
    }

    // Get the signature length
    size_t sig_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(sig_len);
    if (!signature) {
        printf("Failed to allocate memory for signature\n");
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Get the signature
    ECDSA_get0_signature(sig, (const unsigned char**)&signature);

    ECDSA_SIG_free(sig);

    return signature;
}

int main() {
    // Generate a new ECDSA key pair
    EC_KEY* key = generate_ecdsa_key();
    if (!key) {
        return 1;
    }

    // Define the message to sign
    const unsigned char* message = (const unsigned char*)"i am a message";
    size_t message_len = strlen((const char*)message);

    // Sign the message
    unsigned char* signature = sign_message(message, message_len, key);
    if (!signature) {
        EC_KEY_free(key);
        return 1;
    }

    // Print the signature (in hex format)
    printf("Signature: ");
    for (size_t i = 0; i < ECDSA_size(key); i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free resources
    free(signature);
    EC_KEY_free(key);

    return 0;
}