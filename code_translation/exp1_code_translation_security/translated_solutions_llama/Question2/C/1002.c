#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>

// Function to generate a private key
EC_KEY* generate_private_key() {
    // Create a new EC_KEY object
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) {
        printf("Failed to create EC_KEY object\n");
        return NULL;
    }

    // Generate a private key
    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate private key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign data using ECDSA
unsigned char* sign(EC_KEY* key, const unsigned char* data, size_t data_len, size_t* sig_len) {
    // Create a new ECDSA signature object
    ECDSA_SIG* sig = ECDSA_do_sign(data, data_len, key);
    if (!sig) {
        printf("Failed to create ECDSA signature\n");
        return NULL;
    }

    // Get the signature length
    *sig_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(*sig_len);
    if (!signature) {
        printf("Failed to allocate memory for signature\n");
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Get the signature
    if (!ECDSA_SIG_to_bytes(signature, *sig_len, sig)) {
        printf("Failed to get ECDSA signature\n");
        free(signature);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    ECDSA_SIG_free(sig);

    return signature;
}

int main() {
    // Generate a private key
    EC_KEY* private_key = generate_private_key();
    if (!private_key) {
        return 1;
    }

    // Data to be signed
    const unsigned char* data = (const unsigned char*)"this is some data I'd like to sign";
    size_t data_len = strlen((const char*)data);

    // Sign the data
    size_t sig_len;
    unsigned char* signature = sign(private_key, data, data_len, &sig_len);
    if (!signature) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free resources
    free(signature);
    EC_KEY_free(private_key);

    return 0;
}