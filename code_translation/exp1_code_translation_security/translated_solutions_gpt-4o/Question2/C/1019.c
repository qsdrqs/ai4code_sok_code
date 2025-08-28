#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function to sign a string using ECDSA
unsigned char* sign_string(EC_KEY* private_key, const unsigned char* data, size_t data_len, unsigned int* sig_len) {
    // Hash the data using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(data, data_len, hash);

    // Sign the hash using the private key
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(private_key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, private_key) == 0) {
        fprintf(stderr, "Failed to sign data\n");
        free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Generate a new EC key pair using the SECP384R1 curve
    EC_KEY* private_key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (private_key == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(private_key) == 0) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(private_key);
        return 1;
    }

    // Data to be signed
    const char* data = "hello world";
    size_t data_len = strlen(data);

    // Sign the data
    unsigned int sig_len = 0;
    unsigned char* signature = sign_string(private_key, (const unsigned char*)data, data_len, &sig_len);
    if (signature == NULL) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Print the signature in hexadecimal format
    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    free(signature);
    EC_KEY_free(private_key);

    return 0;
}