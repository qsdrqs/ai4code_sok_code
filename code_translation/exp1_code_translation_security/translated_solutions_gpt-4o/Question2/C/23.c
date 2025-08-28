#include <stdio.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

void handleErrors() {
    fprintf(stderr, "An error occurred\n");
    exit(1);
}

int main() {
    // Create a new EC key pair using the NIST P-192 curve
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (eckey == NULL) handleErrors();

    if (EC_KEY_generate_key(eckey) == 0) handleErrors();

    // Message to be signed
    const char *message = "i am a message";
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)message, strlen(message), hash);

    // Sign the message
    unsigned int sig_len;
    unsigned char *signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, eckey);
    if (signature == NULL) handleErrors();

    // Print the signature
    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    EC_KEY_free(eckey);
    OPENSSL_free(signature);

    return 0;
}