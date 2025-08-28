#include <openssl/ecdsa.h>
#include <openssl/err.h>
#include <string.h>

/**
 * Signs a message with a given signing key.
 *
 * @param message The message to be signed.
 * @param message_len The length of the message.
 * @param signing_key The signing key (ECDSA key).
 * @param signature_len A pointer to store the length of the generated signature.
 *
 * @return The generated signature or NULL on error.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, 
                            const EC_KEY* signing_key, size_t* signature_len) {
    // Create a new ECDSA signature
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, signing_key);
    if (!sig) {
        ERR_print_errors_fp(stderr);
        return NULL;
    }

    // Get the size of the signature
    *signature_len = ECDSA_size(signing_key);

    // Allocate memory for the signature
    unsigned char* signature = malloc(*signature_len);
    if (!signature) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Convert the ECDSA signature to a byte string
    if (!ECDSA_SIG_to_bytes(signature, *signature_len, sig)) {
        ERR_print_errors_fp(stderr);
        free(signature);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Free the ECDSA signature
    ECDSA_SIG_free(sig);

    return signature;
}

// Example usage
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Create a new ECDSA key
    EC_KEY* signing_key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!signing_key) {
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // Generate a private key
    if (!EC_KEY_generate_key(signing_key)) {
        ERR_print_errors_fp(stderr);
        EC_KEY_free(signing_key);
        return 1;
    }

    // Define a message to be signed
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);

    // Sign the message
    size_t signature_len;
    unsigned char* signature = sign_message(message, message_len, signing_key, &signature_len);
    if (!signature) {
        EC_KEY_free(signing_key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    free(signature);
    EC_KEY_free(signing_key);
    EVP_cleanup();

    return 0;
}