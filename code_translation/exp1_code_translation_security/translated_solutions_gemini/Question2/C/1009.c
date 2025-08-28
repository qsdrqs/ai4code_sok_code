#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers for Elliptic Curve Cryptography and SHA hashing
#include <openssl/ec.h>      // For EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // For ECDSA_sign, ECDSA_verify
#include <openssl/obj_mac.h> // For NID_secp384r1
#include <openssl/sha.h>     // For SHA384_DIGEST_LENGTH, SHA384

/**
 * @brief Signs a message using a given ECDSA private key.
 *
 * Note: The Python ecdsa library automatically hashes the message before signing.
 * We must do this explicitly in OpenSSL. For a P-384 key, SHA-384 is the
 * appropriate hash function.
 *
 * @param sk The EC_KEY object containing the private key.
 * @param message The message to be signed.
 * @param message_len The length of the message.
 * @param signature A pointer to a buffer that will hold the signature. The caller
 *                  is responsible for freeing this buffer using OPENSSL_free().
 * @param sig_len A pointer to a variable that will hold the length of the signature.
 * @return 1 on success, 0 on failure.
 */
int sign(EC_KEY *sk, const unsigned char *message, size_t message_len, unsigned char **signature, unsigned int *sig_len) {
    // 1. Hash the message
    unsigned char digest[SHA384_DIGEST_LENGTH];
    if (SHA384(message, message_len, digest) == NULL) {
        fprintf(stderr, "Error: Failed to hash the message.\n");
        return 0;
    }

    // 2. Allocate memory for the signature
    // ECDSA_size gives the maximum possible size for a signature
    *signature = OPENSSL_malloc(ECDSA_size(sk));
    if (*signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature.\n");
        return 0;
    }

    // 3. Sign the digest
    if (ECDSA_sign(0, digest, SHA384_DIGEST_LENGTH, *signature, sig_len, sk) != 1) {
        fprintf(stderr, "Error: Failed to sign the message digest.\n");
        OPENSSL_free(*signature);
        *signature = NULL;
        return 0;
    }

    return 1; // Success
}

/**
 * @brief Verifies an ECDSA signature.
 *
 * @param vk The EC_KEY object containing the public key.
 * @param message The original message.
 * @param message_len The length of the message.
 * @param signature The signature to verify.
 * @param sig_len The length of the signature.
 * @return 1 if the signature is valid, 0 if it is invalid, and -1 on error.
 */
int verify(EC_KEY *vk, const unsigned char *message, size_t message_len, const unsigned char *signature, unsigned int sig_len) {
    // 1. Hash the message
    unsigned char digest[SHA384_DIGEST_LENGTH];
    if (SHA384(message, message_len, digest) == NULL) {
        fprintf(stderr, "Error: Failed to hash the message for verification.\n");
        return -1;
    }

    // 2. Verify the signature against the digest
    // ECDSA_verify uses the public key part of the EC_KEY object.
    return ECDSA_verify(0, digest, SHA384_DIGEST_LENGTH, signature, sig_len, vk);
}


int main() {
    // In Python: message = b"hehehe"
    const unsigned char *message = (const unsigned char *)"hehehe";
    size_t message_len = strlen((const char *)message);

    EC_KEY *sk = NULL;
    unsigned char *sig = NULL;
    unsigned int sig_len = 0;

    // In Python: sk = SigningKey.generate(curve=NIST384p)
    // NIST384p corresponds to the NID_secp384r1 curve in OpenSSL.
    printf("Generating ECDSA key pair for curve NIST P-384 (secp384r1)...\n");
    sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (sk == NULL) {
        fprintf(stderr, "Error: Failed to create EC_KEY object.\n");
        return 1;
    }

    if (EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Error: Failed to generate EC key pair.\n");
        EC_KEY_free(sk);
        return 1;
    }
    printf("Key generation successful.\n");

    // In Python: sig = sign(sk, message)
    printf("Signing the message...\n");
    if (!sign(sk, message, message_len, &sig, &sig_len)) {
        fprintf(stderr, "Signing operation failed.\n");
        EC_KEY_free(sk);
        return 1;
    }
    printf("Signing successful. Signature length: %u bytes.\n", sig_len);

    // In Python: print(sk.verifying_key.verify(sig, message))
    printf("Verifying the signature...\n");
    int verify_status = verify(sk, message, message_len, sig, sig_len);

    if (verify_status == 1) {
        // The Python code prints "True", so we print "true" for consistency.
        printf("true\n");
    } else if (verify_status == 0) {
        printf("false\n");
    } else {
        fprintf(stderr, "Error during verification process.\n");
    }

    // --- Cleanup ---
    // Free all allocated resources
    if (sig != NULL) {
        OPENSSL_free(sig);
    }
    if (sk != NULL) {
        EC_KEY_free(sk);
    }

    return 0;
}