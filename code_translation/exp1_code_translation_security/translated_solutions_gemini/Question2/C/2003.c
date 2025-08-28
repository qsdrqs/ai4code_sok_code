#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers for Elliptic Curve Cryptography and Hashing
#include <openssl/ec.h>      // For EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // For ECDSA_sign, ECDSA_SIG, etc.
#include <openssl/obj_mac.h> // For NID_X9_62_prime256v1
#include <openssl/sha.h>     // For SHA256
#include <openssl/err.h>     // For error reporting

// A helper function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// A helper function to print a byte array in hexadecimal format
void print_hex(const unsigned char* data, size_t len) {
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Signs a message using a given ECDSA private key.
 *
 * @param inp The input message string to sign.
 * @param key The EC_KEY object containing the private key.
 * @param sig_len A pointer to an unsigned int that will be populated with the signature's length.
 * @return A dynamically allocated buffer containing the DER-encoded signature.
 *         The caller is responsible for freeing this buffer. Returns NULL on failure.
 */
unsigned char* sign_message(const char* inp, EC_KEY* key, unsigned int* sig_len) {
    // 1. Hash the input message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)inp, strlen(inp), hash);

    // 2. Sign the hash
    // The signature is returned in a DER-encoded format.
    // We first determine the required buffer size.
    *sig_len = ECDSA_size(key);
    unsigned char* signature = (unsigned char*)malloc(*sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        return NULL;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, key) != 1) {
        free(signature);
        handle_errors(); // ECDSA_sign failed
        return NULL;
    }

    return signature;
}

int main() {
    // The Python equivalent of: sk = SigningKey.generate()
    // In OpenSSL, this is a multi-step process.

    EC_KEY *sk = NULL;
    int ret;

    // 1. Create a new EC_KEY object.
    // We use NID_X9_62_prime256v1, also known as prime256v1 or P-256, a very common curve.
    sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (sk == NULL) {
        handle_errors();
    }

    // 2. Generate the private and public key pair.
    ret = EC_KEY_generate_key(sk);
    if (ret != 1) {
        handle_errors();
    }

    printf("Successfully generated ECDSA key.\n");

    // The Python equivalent of: sign("Hello world", sk)
    const char* message = "Hello world";
    unsigned char* signature = NULL;
    unsigned int sig_len = 0;

    signature = sign_message(message, sk, &sig_len);

    if (signature) {
        // The Python equivalent of: print(signature)
        printf("Message to sign: \"%s\"\n", message);
        printf("Signature (hex): ");
        print_hex(signature, sig_len);

        // --- Cleanup ---
        free(signature); // Free the memory allocated in sign_message
    }

    EC_KEY_free(sk); // Free the EC_KEY object

    // Optional: Clean up OpenSSL's error strings table
    ERR_free_strings();

    return 0;
}