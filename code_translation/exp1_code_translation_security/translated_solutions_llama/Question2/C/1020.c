#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a random ECDSA key pair
EC_KEY* generate_ecdsa_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
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

// Function to sign a message using the provided ECDSA key
int sign_message(EC_KEY* key, const unsigned char* message, size_t message_len, unsigned char** sig, size_t* sig_len) {
    const EC_GROUP* group = EC_KEY_get0_group(key);
    ECDSA_SIG* ecdsa_sig = ECDSA_do_sign(message, message_len, group);
    if (!ecdsa_sig) {
        printf("Failed to sign message\n");
        return 0;
    }

    *sig_len = ECDSA_size(key);
    *sig = (unsigned char*)malloc(*sig_len);
    if (!*sig) {
        printf("Failed to allocate memory for signature\n");
        ECDSA_free(ecdsa_sig);
        return 0;
    }

    unsigned char* sig_ptr = *sig;
    int len = ECDSA_sign_asn1(EC_KEY_get0_group(key), ECDSA_SIG_get0_r(ecdsa_sig), ECDSA_SIG_get0_s(ecdsa_sig), *sig_len, &sig_ptr);
    if (len != 1) {
        printf("Failed to encode signature\n");
        free(*sig);
        ECDSA_free(ecdsa_sig);
        return 0;
    }

    ECDSA_free(ecdsa_sig);
    return 1;
}

// Function to verify a message using the provided ECDSA key and signature
int verify_message(EC_KEY* key, const unsigned char* message, size_t message_len, const unsigned char* sig, size_t sig_len) {
    ECDSA_SIG* ecdsa_sig = ECDSA_SIG_new();
    if (!ecdsa_sig) {
        printf("Failed to create ECDSA signature\n");
        return 0;
    }

    int len = ECDSA_verify_asn1(EC_KEY_get0_group(key), message, message_len, sig, sig_len, ecdsa_sig);
    ECDSA_SIG_free(ecdsa_sig);
    return len == 1;
}

int main() {
    // Generate ECDSA key pair
    EC_KEY* key = generate_ecdsa_key();
    if (!key) {
        return 1;
    }

    // Sign a message
    const char* message = "..";
    unsigned char* sig;
    size_t sig_len;
    if (!sign_message(key, (const unsigned char*)message, strlen(message), &sig, &sig_len)) {
        EC_KEY_free(key);
        return 1;
    }

    // Verify the signature
    int verified = verify_message(key, (const unsigned char*)message, strlen(message), sig, sig_len);
    printf("Verification result: %d\n", verified);

    // Clean up
    free(sig);
    EC_KEY_free(key);
    return 0;
}