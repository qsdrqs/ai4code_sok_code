#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // For Elliptic Curve functions
#include <openssl/ecdsa.h>   // For ECDSA signing
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/sha.h>     // For SHA256
#include <openssl/err.h>     // For error reporting

/**
 * @brief Generates an ECDSA key on the SECP256k1 curve and signs a message.
 *
 * This function mimics the behavior of the Python ecdsa library snippet.
 * It handles key generation, message hashing (SHA-256), signing, and
 * DER-encoding the signature.
 *
 * @param message The input message to sign.
 * @param message_len The length of the input message.
 * @param signature A pointer that will be updated to point to the newly allocated
 *                  buffer containing the DER-encoded signature. The caller is
 *                  responsible for freeing this memory with free().
 * @param sig_len A pointer to an unsigned int that will be updated with the
 *                length of the signature.
 * @return 1 on success, 0 on failure.
 */
int myfunction(const unsigned char *message, size_t message_len, unsigned char **signature, unsigned int *sig_len) {
    EC_KEY *eckey = NULL;
    ECDSA_SIG *sig = NULL;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int result = 0; // Default to failure

    // 1. Generate a new private key (sk) on the SECP256k1 curve.
    //    `EC_KEY_new_by_curve_name` creates a new EC_KEY object for the specified curve.
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Error: Failed to create EC_KEY object.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // Generate the actual private and public key pair.
    if (EC_KEY_generate_key(eckey) != 1) {
        fprintf(stderr, "Error: Failed to generate EC key pair.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 2. Hash the message. ECDSA signs the hash of the message, not the message itself.
    //    We'll use SHA-256, which is standard for this curve.
    if (!SHA256(message, message_len, hash)) {
        fprintf(stderr, "Error: Failed to compute SHA-256 hash of the message.\n");
        goto cleanup;
    }

    // 3. Sign the hash to produce the signature (sig).
    //    `ECDSA_do_sign` creates the signature as an ECDSA_SIG object.
    sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, eckey);
    if (sig == NULL) {
        fprintf(stderr, "Error: Failed to sign the hash.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 4. Convert the ECDSA_SIG object to a DER-encoded byte array.
    //    This is the standard format for transmitting signatures.
    *sig_len = i2d_ECDSA_SIG(sig, NULL); // Get the required buffer size
    if (*sig_len <= 0) {
        fprintf(stderr, "Error: Failed to determine signature size.\n");
        goto cleanup;
    }

    *signature = (unsigned char *)malloc(*sig_len);
    if (*signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for the signature.\n");
        goto cleanup;
    }

    // Perform the actual encoding into the allocated buffer.
    unsigned char *p = *signature; // A temporary pointer for the function
    if (i2d_ECDSA_SIG(sig, &p) <= 0) {
        fprintf(stderr, "Error: Failed to DER-encode the signature.\n");
        free(*signature); // Free the bad allocation
        *signature = NULL;
        goto cleanup;
    }

    // If we've reached this point, everything was successful.
    result = 1;

cleanup:
    // Free all allocated OpenSSL objects to prevent memory leaks.
    if (sig) ECDSA_SIG_free(sig);
    if (eckey) EC_KEY_free(eckey);

    return result;
}

// Helper function to print a byte array in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s", label);
    for (size_t i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Main function to demonstrate the usage of myfunction
int main() {
    // The message to be signed
    const char *message_str = "This is a test message for ECDSA signing.";
    const unsigned char *message = (const unsigned char *)message_str;
    size_t message_len = strlen(message_str);

    // Pointers to hold the output signature and its length
    unsigned char *signature = NULL;
    unsigned int sig_len = 0;

    printf("Signing message: \"%s\"\n", message_str);

    // Call the function to generate key and sign the message
    if (myfunction(message, message_len, &signature, &sig_len)) {
        printf("Successfully created signature.\n");
        printf("Signature length: %u bytes\n", sig_len);
        print_hex("Signature (hex): ", signature, sig_len);

        // IMPORTANT: The caller is responsible for freeing the signature memory.
        free(signature);
    } else {
        fprintf(stderr, "myfunction failed.\n");
        return 1; // Indicate error
    }

    return 0; // Indicate success
}