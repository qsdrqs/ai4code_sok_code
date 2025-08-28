#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Function to sign a message using a given ECDSA signing key
int sign_message(const unsigned char *message, size_t message_len, EC_KEY *eckey, unsigned char **signature, unsigned int *sig_len) {
    // Create a SHA256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Sign the hash
    *signature = OPENSSL_malloc(ECDSA_size(eckey));
    if (*signature == NULL) {
        return 0;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, *signature, sig_len, eckey) == 0) {
        OPENSSL_free(*signature);
        return 0;
    }

    return 1;
}

// Function to verify a signed message
int verify_signature(const unsigned char *message, size_t message_len, const unsigned char *signature, unsigned int sig_len, EC_KEY *eckey) {
    // Create a SHA256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Verify the signature
    return ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, eckey);
}

int main() {
    // Generate a new ECDSA key pair
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Error creating EC Key\n");
        return 1;
    }

    if (EC_KEY_generate_key(eckey) == 0) {
        fprintf(stderr, "Error generating EC Key\n");
        EC_KEY_free(eckey);
        return 1;
    }

    // Message to be signed
    const unsigned char *message = (unsigned char *)"helloworld";
    size_t message_len = strlen((const char *)message);

    // Sign the message
    unsigned char *signature = NULL;
    unsigned int sig_len = 0;
    if (!sign_message(message, message_len, eckey, &signature, &sig_len)) {
        fprintf(stderr, "Error signing message\n");
        EC_KEY_free(eckey);
        return 1;
    }

    // Verify the signature
    if (verify_signature(message, message_len, signature, sig_len, eckey)) {
        printf("Signature is valid\n");
    } else {
        printf("Signature is invalid\n");
    }

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(eckey);

    return 0;
}