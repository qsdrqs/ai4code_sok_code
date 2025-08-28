#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>      // For Elliptic Curve functions
#include <openssl/ecdsa.h>   // For ECDSA signing
#include <openssl/obj_mac.h> // For NID_X9_62_prime192v1
#include <openssl/sha.h>     // For SHA1 hashing
#include <openssl/err.h>     // For error reporting

/*
 * @brief Signs a message using a given ECDSA private key.
 *
 * @param message The message data to sign.
 * @param message_len The length of the message data.
 * @param key The EC_KEY object containing the private key.
 * @param signature A pointer to a buffer that will be allocated to hold the signature.
 *                  The caller is responsible for freeing this with OPENSSL_free().
 * @param sig_len A pointer to an unsigned int that will hold the length of the signature.
 * @return 1 on success, 0 on failure.
 */
int sign_message(const unsigned char* message, size_t message_len, EC_KEY* key, unsigned char** signature, unsigned int* sig_len) {
    // ECDSA signs a hash of the message, not the message itself.
    // The python ecdsa library uses SHA-1 by default for this curve.
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);

    // Allocate space for the signature.
    // The maximum size for a DER-encoded ECDSA signature is 72 bytes.
    *signature = OPENSSL_malloc(ECDSA_size(key));
    if (*signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return 0;
    }

    // Sign the hash
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, *signature, sig_len, key) != 1) {
        fprintf(stderr, "Failed to sign message.\n");
        ERR_print_errors_fp(stderr);
        OPENSSL_free(*signature);
        *signature = NULL;
        return 0;
    }

    return 1;
}

/*
 * @brief Prints a byte array in hexadecimal format.
 */
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    EC_KEY *key = NULL;
    unsigned char *signature = NULL;
    unsigned int sig_len = 0;

    // 1. Generate a new private key
    // The Python library comment mentions it uses NIST192p by default.
    // In OpenSSL, this curve is identified by NID_X9_62_prime192v1.
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        ERR_print_errors_fp(stderr);
        return 1;
    }

    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Failed to generate EC key.\n");
        ERR_print_errors_fp(stderr);
        EC_KEY_free(key);
        return 1;
    }

    // 2. Define the message to sign
    const char* message_str = "i am a message";

    // 3. Call the sign function
    printf("Signing message: \"%s\"\n", message_str);
    if (!sign_message((const unsigned char*)message_str, strlen(message_str), key, &signature, &sig_len)) {
        fprintf(stderr, "Signing operation failed.\n");
        EC_KEY_free(key);
        return 1;
    }

    // 4. Print the signature
    // The Python code prints raw bytes, which is not readable.
    // We will print the signature in hexadecimal for display purposes.
    print_hex("Signature (hex)", signature, sig_len);
    printf("Signature length: %u bytes\n", sig_len);


    // --- Cleanup ---
    // In C, we must manually free all allocated memory.
    if (signature != NULL) {
        OPENSSL_free(signature);
    }
    if (key != NULL) {
        EC_KEY_free(key);
    }

    // Clean up OpenSSL error strings
    ERR_free_strings();

    return 0;
}