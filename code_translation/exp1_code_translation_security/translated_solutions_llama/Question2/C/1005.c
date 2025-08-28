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

    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate ECDSA key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign a message using ECDSA
unsigned char* sign(const unsigned char* msg, size_t msg_len, EC_KEY* key, size_t* sig_len) {
    const EC_GROUP* group = EC_KEY_get0_group(key);
    ECDSA_SIG* sig = ECDSA_do_sign(msg, msg_len, group, EC_KEY_get0_private_key(key), NULL);
    if (!sig) {
        printf("Failed to generate ECDSA signature\n");
        return NULL;
    }

    *sig_len = ECDSA_size(key);
    unsigned char* signature = (unsigned char*)malloc(*sig_len);
    if (!signature) {
        printf("Memory allocation failed\n");
        ECDSA_free(sig);
        return NULL;
    }

    ECDSA_get0_signature(sig, (const unsigned char**)&signature);

    ECDSA_free(sig);
    return signature;
}

// Function to verify an ECDSA signature
int verify(const unsigned char* msg, size_t msg_len, const unsigned char* signature, size_t sig_len, EC_KEY* key) {
    ECDSA_SIG* sig = ECDSA_SIG_new();
    if (!sig) {
        printf("Failed to create ECDSA signature object\n");
        return 0;
    }

    if (!d2i_ECDSA_SIG(&sig, &signature, sig_len)) {
        printf("Failed to parse ECDSA signature\n");
        ECDSA_SIG_free(sig);
        return 0;
    }

    int result = ECDSA_do_verify(msg, msg_len, sig, EC_KEY_get0_public_key(key), NULL);
    ECDSA_SIG_free(sig);
    return result;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Generate a new ECDSA key pair
    EC_KEY* key = generate_ecdsa_key();
    if (!key) {
        return 1;
    }

    // Sign a message
    const unsigned char* msg = (const unsigned char*)"message";
    size_t msg_len = strlen((const char*)msg);
    size_t sig_len;
    unsigned char* signature = sign(msg, msg_len, key, &sig_len);
    if (!signature) {
        EC_KEY_free(key);
        return 1;
    }

    // Verify the signature
    int verified = verify(msg, msg_len, signature, sig_len, key);
    if (verified == 1) {
        printf("Signature verified successfully\n");
    } else {
        printf("Signature verification failed\n");
    }

    // Clean up
    free(signature);
    EC_KEY_free(key);
    EVP_cleanup();

    return 0;
}