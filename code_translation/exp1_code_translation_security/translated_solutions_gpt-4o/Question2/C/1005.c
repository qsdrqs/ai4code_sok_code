#include <stdio.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

void handleErrors() {
    fprintf(stderr, "An error occurred\n");
    exit(1);
}

unsigned char* sign(const unsigned char* msg, size_t msg_len, EC_KEY* key, unsigned int* sig_len) {
    unsigned char* signature = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];

    // Hash the message
    if (!SHA256(msg, msg_len, hash)) {
        handleErrors();
    }

    // Allocate memory for the signature
    signature = OPENSSL_malloc(ECDSA_size(key));
    if (signature == NULL) {
        handleErrors();
    }

    // Sign the hash
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, key)) {
        handleErrors();
    }

    // Verify the signature
    if (ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, *sig_len, key) != 1) {
        handleErrors();
    }

    return signature;
}

void test() {
    EC_KEY* sk = NULL;
    unsigned char* signature = NULL;
    unsigned int sig_len;
    const char* message = "message";

    // Generate a new EC key pair
    sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (sk == NULL) {
        handleErrors();
    }

    if (!EC_KEY_generate_key(sk)) {
        handleErrors();
    }

    // Sign the message
    signature = sign((const unsigned char*)message, strlen(message), sk, &sig_len);

    // Print the signature in hex format
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(sk);
}

int main() {
    test();
    return 0;
}