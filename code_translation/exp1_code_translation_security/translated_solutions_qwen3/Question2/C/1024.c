#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/bn.h>
#include <openssl/evp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to handle OpenSSL errors and exit
void handle_openssl_error() {
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

// Function to generate ECDSA signature
unsigned char* generate_ecdsa_signature(const char* message, EC_KEY* key, unsigned int* sig_len) {
    // Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), hash);

    // Determine required signature buffer size
    *sig_len = ECDSA_size(key);
    unsigned char* sig = malloc(*sig_len);
    if (!sig) {
        return NULL;
    }

    // Sign the hash
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, sig, sig_len, key) <= 0) {
        free(sig);
        return NULL;
    }

    return sig;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate ECDSA private key using secp256k1 curve
    EC_KEY* privateKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!privateKey || !EC_KEY_generate_key(privateKey)) {
        handle_openssl_error();
    }

    // Generate public key from private key (as in Python)
    EC_KEY* publicKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!publicKey || !EC_KEY_set_public_key(publicKey, EC_KEY_get0_public_key(privateKey))) {
        handle_openssl_error();
    }

    // Message to sign
    const char* message = "My test message";

    // Generate the ECDSA signature
    unsigned int sig_len;
    unsigned char* signature = generate_ecdsa_signature(message, privateKey, &sig_len);
    if (!signature) {
        handle_openssl_error();
    }

    // Print the signature in hexadecimal format
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Cleanup
    free(signature);
    EC_KEY_free(privateKey);
    EC_KEY_free(publicKey);
    EVP_cleanup();

    return 0;
}