#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function to sign a message
unsigned char* sign(EC_KEY* sk, const char* message, unsigned int* sig_len) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned char* signature = NULL;

    // Hash the message using SHA-256
    SHA256((unsigned char*)message, strlen(message), hash);

    // Allocate memory for the signature
    signature = (unsigned char*)malloc(ECDSA_size(sk));
    if (!signature) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    // Sign the hash
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, sk)) {
        fprintf(stderr, "Failed to sign the message\n");
        free(signature);
        return NULL;
    }

    return signature;
}

// Function to test signing and verification
void test() {
    EC_KEY* sk = NULL;
    EC_KEY* vk = NULL;
    unsigned char* signature = NULL;
    unsigned int sig_len = 0;
    const char* message = "hello world";
    const char* tampered_message = "hello worfld";

    // Generate a new EC key pair
    sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!sk) {
        fprintf(stderr, "Failed to create EC key\n");
        return;
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(sk);
        return;
    }

    // Sign the message
    signature = sign(sk, message, &sig_len);
    if (!signature) {
        EC_KEY_free(sk);
        return;
    }

    // Get the public key (verifying key)
    vk = EC_KEY_dup(sk);
    if (!vk) {
        fprintf(stderr, "Failed to duplicate EC key\n");
        free(signature);
        EC_KEY_free(sk);
        return;
    }

    // Verify the signature with the correct message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)message, strlen(message), hash);
    if (ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, vk) == 1) {
        printf("Signature verified successfully for the correct message\n");
    } else {
        printf("Failed to verify signature for the correct message\n");
    }

    // Verify the signature with a tampered message
    SHA256((unsigned char*)tampered_message, strlen(tampered_message), hash);
    if (ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, vk) == 1) {
        printf("Signature verified successfully for the tampered message\n");
    } else {
        printf("Failed to verify signature for the tampered message\n");
    }

    // Clean up
    free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
}

int main() {
    test();
    return 0;
}