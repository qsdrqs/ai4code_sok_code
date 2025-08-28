#include <stdio.h>
#include <string.h>
#include <assert.h>

// OpenSSL headers for all dependencies
#include <openssl/ec.h>      // For Elliptic Curve functions (EC_KEY, etc.)
#include <openssl/ecdsa.h>   // For ECDSA signing and verification
#include <openssl/obj_mac.h> // For NID_X9_62_prime192v1
#include <openssl/sha.h>     // For SHA256 hashing
#include <openssl/err.h>     // For error reporting

// A helper function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

/**
 * @brief Signs a message using the provided EC private key.
 *
 * @param msg The message to sign.
 * @param msg_len The length of the message.
 * @param sk The EC_KEY object containing the private key.
 * @param sig_out A pointer to an unsigned char buffer that will be allocated
 *                and will contain the signature. The caller must free this buffer.
 * @return The length of the signature in bytes, or 0 on failure.
 */
unsigned int sign_message(const unsigned char* msg, size_t msg_len, EC_KEY* sk, unsigned char** sig_out) {
    // 1. Hash the message (Python's ecdsa library does this implicitly)
    // We'll use SHA-256, a common and secure choice.
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256(msg, msg_len, digest)) {
        fprintf(stderr, "Failed to hash the message.\n");
        return 0;
    }

    // 2. Create a buffer to hold the signature
    // ECDSA_size() returns the maximum possible size for a signature.
    unsigned int sig_len = ECDSA_size(sk);
    *sig_out = (unsigned char*)malloc(sig_len);
    if (*sig_out == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return 0;
    }

    // 3. Sign the hash
    if (!ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, *sig_out, &sig_len, sk)) {
        fprintf(stderr, "Failed to sign the message.\n");
        free(*sig_out);
        *sig_out = NULL;
        handle_errors();
        return 0;
    }

    return sig_len;
}

/**
 * @brief Verifies a signature against a message using the EC public key.
 *
 * @param msg The message that was signed.
 * @param msg_len The length of the message.
 * @param sk The EC_KEY object containing the public key.
 * @param signature The signature to verify.
 * @param sig_len The length of the signature.
 * @return 1 for a valid signature, 0 for an invalid signature, -1 for an error.
 */
int verify_signature(const unsigned char* msg, size_t msg_len, EC_KEY* sk, const unsigned char* signature, unsigned int sig_len) {
    // 1. Hash the message (must use the same algorithm as signing)
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256(msg, msg_len, digest)) {
        fprintf(stderr, "Failed to hash the message for verification.\n");
        return -1;
    }

    // 2. Verify the signature against the hash
    // The EC_KEY object `sk` contains the public key needed for verification.
    int result = ECDSA_verify(0, digest, SHA256_DIGEST_LENGTH, signature, sig_len, sk);
    
    // ECDSA_verify returns 1 for success, 0 for failure, -1 for error.
    return result;
}


int main() {
    // In Python: sk = SigningKey.generate() # uses NIST192p
    // The OpenSSL name for NIST192p is NID_X9_62_prime192v1
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (sk == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        handle_errors();
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate EC key pair.\n");
        handle_errors();
    }
    printf("Successfully generated ECDSA key pair (NIST192p).\n");

    // The EC_KEY object `sk` now holds both the private and public key (verifying_key).

    // The message to be signed
    const unsigned char* message = (const unsigned char*)"message";
    size_t message_len = strlen((const char*)message);

    // In Python: sign(b"message", sk)
    unsigned char* signature = NULL;
    unsigned int signature_len = sign_message(message, message_len, sk, &signature);

    if (signature_len == 0) {
        fprintf(stderr, "Signing failed.\n");
        EC_KEY_free(sk);
        return 1;
    }
    printf("Successfully signed message. Signature length: %u bytes.\n", signature_len);

    // In Python: assert vk.verify(signature, b"message")
    int verification_status = verify_signature(message, message_len, sk, signature, signature_len);

    // The assert statement
    assert(verification_status == 1);

    if (verification_status == 1) {
        printf("Assertion successful: Signature verified correctly!\n");
    } else if (verification_status == 0) {
        printf("Assertion failed: Signature is invalid.\n");
    } else {
        printf("Assertion failed: An error occurred during verification.\n");
        handle_errors();
    }

    // --- Cleanup ---
    // Free all allocated resources
    free(signature);
    EC_KEY_free(sk);
    
    // Clean up OpenSSL error strings
    ERR_free_strings();

    printf("All resources cleaned up.\n");

    return 0;
}