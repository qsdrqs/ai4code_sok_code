#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Function to sign and verify a message
int sign_message(EC_KEY *sk, const char *message) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    unsigned char *signature = NULL;
    unsigned int sig_len;
    int verify_status = 0;

    // Hash the message using SHA-256
    SHA256((unsigned char *)message, strlen(message), hash);

    // Sign the hash
    sig_len = ECDSA_size(sk);
    signature = (unsigned char *)malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return 0;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &sig_len, sk) == 0) {
        fprintf(stderr, "Failed to sign the message\n");
        free(signature);
        return 0;
    }

    // Verify the signature
    verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, sk);

    // Free the allocated memory
    free(signature);

    return verify_status;
}

int main() {
    // Generate a new EC key pair using the SECP256k1 curve
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (sk == NULL) {
        fprintf(stderr, "Failed to create EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(sk) == 0) {
        fprintf(stderr, "Failed to generate EC key\n");
        EC_KEY_free(sk);
        return 1;
    }

    // Message to sign
    const char *message = "..";

    // Sign and verify the message
    if (sign_message(sk, message)) {
        printf("Signature verified successfully\n");
    } else {
        printf("Signature verification failed\n");
    }

    // Free the EC key
    EC_KEY_free(sk);

    return 0;
}