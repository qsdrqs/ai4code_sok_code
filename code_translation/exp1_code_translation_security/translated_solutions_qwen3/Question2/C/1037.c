#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>
#include <openssl/evp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to sign a message using the private key
unsigned char* signMessage(EC_KEY* key, const char* message, size_t* sig_len) {
    // Hash the message using SHA-384
    unsigned char hash[SHA384_DIGEST_LENGTH];
    size_t message_len = strlen(message);
    SHA384((const unsigned char*)message, message_len, hash);

    // Allocate memory for the signature
    *sig_len = ECDSA_size(key);
    unsigned char* sig = malloc(*sig_len);
    if (!sig) {
        return NULL;
    }

    // Sign the hash
    if (!ECDSA_sign(0, hash, SHA384_DIGEST_LENGTH, sig, (unsigned int*)sig_len, key)) {
        free(sig);
        return NULL;
    }

    return sig;
}

int main() {
    // Initialize OpenSSL (optional in newer versions, but safe to include)
    OpenSSL_add_all_algorithms();

    // Generate private key using NIST P-384 (secp384r1)
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!sk) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(sk);
        return 1;
    }

    // Extract the public key (verifying key)
    EC_KEY* vk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!vk) {
        fprintf(stderr, "Failed to create verifying EC_KEY\n");
        EC_KEY_free(sk);
        return 1;
    }

    const EC_POINT* pub_key = EC_KEY_get0_public_key(sk);
    if (!pub_key || !EC_KEY_set_public_key(vk, pub_key)) {
        fprintf(stderr, "Failed to set public key\n");
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        return 1;
    }

    // Sign the message
    const char* message = "testing";
    size_t sig_len;
    unsigned char* signature = signMessage(sk, message, &sig_len);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        return 1;
    }

    // Hash the message again for verification
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384((const unsigned char*)message, strlen(message), hash);

    // Verify the signature
    int verify_result = ECDSA_verify(0, hash, SHA384_DIGEST_LENGTH, signature, sig_len, vk);
    if (verify_result == 1) {
        printf("Verification succeeded\n");
    } else if (verify_result == 0) {
        printf("Verification failed\n");
    } else {
        fprintf(stderr, "Verification error\n");
    }

    // Clean up
    free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);

    return 0;
}