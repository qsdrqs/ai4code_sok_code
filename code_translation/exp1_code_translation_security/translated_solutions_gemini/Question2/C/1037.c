#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // for EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // for ECDSA_sign, ECDSA_verify
#include <openssl/obj_mac.h> // for NID_secp384r1
#include <openssl/sha.h>     // for SHA384
#include <openssl/err.h>     // for error reporting

// Forward declarations of our functions
EC_KEY* create_new_key(void);
ECDSA_SIG* signMessage(EC_KEY* key, const unsigned char* message, size_t message_len);
void handle_openssl_error(void);

int main() {
    // This corresponds to: sk = SigningKey.generate(curve=NIST384p)
    EC_KEY *key = create_new_key();
    if (!key) {
        fprintf(stderr, "Failed to create key.\n");
        return 1;
    }
    printf("Successfully generated a new ECDSA key for NIST P-384.\n");

    // The message to be signed
    const unsigned char *message = (const unsigned char *)"testing";
    size_t message_len = strlen((const char *)message);

    // This corresponds to: signature = signMessage(sk, b"testing")
    ECDSA_SIG *signature = signMessage(key, message, message_len);
    if (!signature) {
        fprintf(stderr, "Failed to sign the message.\n");
        EC_KEY_free(key);
        return 1;
    }
    printf("Message signed successfully.\n");

    // In OpenSSL, the EC_KEY object holds both the private and public keys.
    // The verify function automatically uses the public part of the key.
    // This corresponds to: vk.verify(signature, b"testing")

    // To verify, we must first hash the message again.
    unsigned char digest[SHA384_DIGEST_LENGTH];
    if (!SHA384(message, message_len, digest)) {
        fprintf(stderr, "Failed to compute message digest for verification.\n");
        handle_openssl_error();
        ECDSA_SIG_free(signature);
        EC_KEY_free(key);
        return 1;
    }

    // ECDSA_do_verify returns 1 for a valid signature, 0 for an invalid one, and -1 on error.
    int verify_status = ECDSA_do_verify(digest, SHA384_DIGEST_LENGTH, signature, key);
    if (verify_status == -1) {
        fprintf(stderr, "Error during verification.\n");
        handle_openssl_error();
    }

    // This corresponds to: print(vk.verify(signature, b"testing"))
    printf("Verification result: %s\n", (verify_status == 1) ? "Success" : "Failure");

    // Cleanup: Free all allocated resources
    ECDSA_SIG_free(signature);
    EC_KEY_free(key);

    return (verify_status == 1) ? 0 : 1;
}

/**
 * @brief Creates a new ECDSA key pair on the NIST P-384 curve.
 * This is equivalent to SigningKey.generate(curve=NIST384p).
 *
 * @return A pointer to an EC_KEY object, or NULL on failure.
 *         The caller is responsible for freeing the key with EC_KEY_free().
 */
EC_KEY* create_new_key(void) {
    // NID_secp384r1 is the OpenSSL identifier for the NIST P-384 curve.
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (key == NULL) {
        handle_openssl_error();
        return NULL;
    }

    if (EC_KEY_generate_key(key) != 1) {
        handle_openssl_error();
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

/**
 * @brief Signs a message using the provided private key.
 * This is equivalent to the Python signMessage function.
 *
 * @param key The EC_KEY object containing the private key.
 * @param message The message to sign.
 * @param message_len The length of the message.
 * @return A pointer to an ECDSA_SIG object, or NULL on failure.
 *         The caller is responsible for freeing the signature with ECDSA_SIG_free().
 */
ECDSA_SIG* signMessage(EC_KEY* key, const unsigned char* message, size_t message_len) {
    // ECDSA operates on a hash of the message, not the message itself.
    // For P-384, the corresponding hash function is SHA-384.
    unsigned char digest[SHA384_DIGEST_LENGTH];
    if (!SHA384(message, message_len, digest)) {
        fprintf(stderr, "Failed to compute message digest for signing.\n");
        handle_openssl_error();
        return NULL;
    }

    // Sign the digest
    ECDSA_SIG *signature = ECDSA_do_sign(digest, SHA384_DIGEST_LENGTH, key);
    if (signature == NULL) {
        handle_openssl_error();
    }

    return signature;
}

/**
 * @brief A utility function to print OpenSSL error messages.
 */
void handle_openssl_error(void) {
    ERR_print_errors_fp(stderr);
}