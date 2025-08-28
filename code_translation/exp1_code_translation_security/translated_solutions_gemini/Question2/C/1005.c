#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // for EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // for ECDSA_sign, ECDSA_verify
#include <openssl/obj_mac.h> // for NID_X9_62_prime256v1
#include <openssl/sha.h>     // for SHA256
#include <openssl/err.h>     // for error handling

// --- Helper function to handle OpenSSL errors ---
void handle_errors() {
    fprintf(stderr, "An OpenSSL error occurred:\n");
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

/**
 * @brief Signs a message using ECDSA and verifies the signature.
 *
 * @param msg The message to sign.
 * @param msg_len The length of the message.
 * @param key The EC_KEY object containing the private key.
 * @param sig A pointer to a buffer that will be allocated and filled with the signature.
 *            The caller is responsible for freeing this buffer.
 * @param sig_len A pointer to a size_t variable that will be filled with the signature length.
 * @return 1 on success, 0 on failure.
 */
int sign(const unsigned char *msg, size_t msg_len, EC_KEY *key, unsigned char **sig, size_t *sig_len) {
    // Step 1: Hash the message using SHA-256
    // ECDSA operates on a fixed-size hash of the message.
    unsigned char hash[SHA256_DIGEST_LENGTH];
    if (!SHA256(msg, msg_len, hash)) {
        fprintf(stderr, "Failed to compute SHA-256 hash.\n");
        return 0;
    }

    // Step 2: Sign the hash
    // The signature is returned in a DER-encoded format.
    // We first determine the maximum possible size for the signature.
    unsigned int der_sig_len = ECDSA_size(key);
    unsigned char *der_sig = (unsigned char *)malloc(der_sig_len);
    if (!der_sig) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return 0;
    }

    // ECDSA_sign returns 1 on success
    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, der_sig, &der_sig_len, key) != 1) {
        free(der_sig);
        fprintf(stderr, "Failed to sign the hash.\n");
        handle_errors(); // Print OpenSSL specific error
        return 0;
    }

    // Step 3: Verify the signature (equivalent to Python's 'assert')
    // The public key is implicitly part of the 'key' object.
    // ECDSA_verify returns 1 for a valid signature, 0 for invalid, and -1 on error.
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, der_sig, der_sig_len, key);
    if (verify_status != 1) {
        free(der_sig);
        if (verify_status == 0) {
            fprintf(stderr, "Assertion failed: Signature did not verify right after creation!\n");
        } else {
            fprintf(stderr, "Error during verification.\n");
            handle_errors();
        }
        return 0;
    }
    printf("Self-verification successful.\n");

    // Step 4: Return the signature
    *sig = der_sig;
    *sig_len = der_sig_len;

    return 1; // Success
}

/**
 * @brief Demonstrates the signing process.
 */
void test() {
    // --- Key Generation (equivalent to ecdsa.SigningKey.generate()) ---
    // Use a standard curve, NID_X9_62_prime256v1 (also known as secp256r1 or P-256)
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (sk == NULL) {
        handle_errors();
    }
    if (EC_KEY_generate_key(sk) != 1) {
        handle_errors();
    }
    printf("Successfully generated a new ECDSA key.\n");

    // --- Message to be signed ---
    const char *msg_str = "message";
    const unsigned char *msg = (const unsigned char *)msg_str;
    size_t msg_len = strlen(msg_str);

    // --- Call the sign function ---
    unsigned char *signature = NULL;
    size_t signature_len = 0;

    if (sign(msg, msg_len, sk, &signature, &signature_len)) {
        printf("Signature created successfully. Length: %zu bytes.\n", signature_len);
        printf("Signature (hex): ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        fprintf(stderr, "Signing process failed.\n");
    }

    // --- Cleanup ---
    free(signature); // Free the signature buffer allocated in sign()
    EC_KEY_free(sk); // Free the key
}

int main() {
    // Load the error strings for a more descriptive error reporting
    ERR_load_crypto_strings();

    test();

    // Clean up the error strings
    ERR_free_strings();

    return 0;
}