#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to generate a random ECDSA signing key
EC_KEY* generate_ecdsa_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        printf("Failed to generate ECDSA key\n");
        return NULL;
    }

    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate ECDSA key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign a message using an ECDSA signing key
unsigned char* sign(const unsigned char* message, size_t message_len, EC_KEY* key, size_t* signature_len) {
    const EC_GROUP* group = EC_KEY_get0_group(key);
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, group);
    if (!sig) {
        printf("Failed to sign message\n");
        return NULL;
    }

    *signature_len = ECDSA_size(key);
    unsigned char* signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        printf("Memory allocation failed\n");
        ECDSA_SIG_free(sig);
        return NULL;
    }

    ECDSA_SIG_get0(sig, (const unsigned char**)&signature[0], NULL);

    ECDSA_SIG_free(sig);
    return signature;
}

// Function to verify a signed message
int verify(const unsigned char* message, size_t message_len, const unsigned char* signature, size_t signature_len, EC_KEY* key) {
    ECDSA_SIG* sig = ECDSA_SIG_new();
    if (!sig) {
        printf("Failed to create ECDSA signature\n");
        return 0;
    }

    if (!ECDSA_SIG_set0(sig, (unsigned char*)signature, (unsigned char*)signature)) {
        printf("Failed to set ECDSA signature\n");
        ECDSA_SIG_free(sig);
        return 0;
    }

    int result = ECDSA_do_verify(message, message_len, sig, EC_KEY_get0_group(key), EC_KEY_get0_public_key(key));
    ECDSA_SIG_free(sig);
    return result;
}

int main() {
    // Generate ECDSA signing key
    EC_KEY* sk = generate_ecdsa_key();
    if (!sk) {
        return 1;
    }

    // Get verifying key
    EC_KEY* vk = EC_KEY_dup(sk);
    if (!vk) {
        printf("Failed to duplicate ECDSA key\n");
        EC_KEY_free(sk);
        return 1;
    }

    // Sign message
    const unsigned char* message = (const unsigned char*)"helloworld";
    size_t message_len = strlen((const char*)message);
    size_t signature_len;
    unsigned char* signed_message = sign(message, message_len, sk, &signature_len);
    if (!signed_message) {
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        return 1;
    }

    // Verify signed message
    int verification_result = verify(message, message_len, signed_message, signature_len, vk);
    printf("Verification result: %d\n", verification_result);

    // Clean up
    free(signed_message);
    EC_KEY_free(sk);
    EC_KEY_free(vk);

    return 0;
}