#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <openssl/err.h>

// Forward declarations of our helper functions
EVP_PKEY* generate_key();
unsigned char* sign_message(EVP_PKEY *sk, const char *m, size_t *signature_len);
int verify_signature(EVP_PKEY *vk, const char *m, const unsigned char *signature, size_t signature_len);
void test();

/**
 * @brief Corresponds to Python's `SigningKey.generate()`
 * Generates a new ECDSA private/public key pair on the secp256r1 curve.
 *
 * @return A pointer to an EVP_PKEY structure containing the key pair, or NULL on failure.
 *         The caller is responsible for freeing this object using EVP_PKEY_free().
 */
EVP_PKEY* generate_key() {
    EVP_PKEY *pkey = NULL;
    EVP_PKEY_CTX *pctx = NULL;

    // 1. Create a context for the key generation
    pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) {
        fprintf(stderr, "Error: Could not create PKEY context.\n");
        ERR_print_errors_fp(stderr);
        return NULL;
    }

    // 2. Initialize the context for key generation
    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        fprintf(stderr, "Error: Could not initialize keygen context.\n");
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    // 3. Set the elliptic curve to use (secp256r1 is a common standard)
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_X9_62_prime256v1) <= 0) {
        fprintf(stderr, "Error: Could not set curve NID.\n");
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    // 4. Generate the key
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) {
        fprintf(stderr, "Error: Could not generate key.\n");
        ERR_print_errors_fp(stderr);
        EVP_PKEY_CTX_free(pctx);
        return NULL;
    }

    printf("Successfully generated ECDSA key.\n");
    EVP_PKEY_CTX_free(pctx);
    return pkey;
}


/**
 * @brief Corresponds to Python's `sign` function.
 * Signs a message using the provided private key.
 *
 * @param sk The private key (EVP_PKEY object).
 * @param m The message string to sign.
 * @param signature_len A pointer to a size_t variable to store the length of the signature.
 * @return A dynamically allocated buffer containing the signature, or NULL on failure.
 *         The caller is responsible for freeing this buffer.
 */
unsigned char* sign_message(EVP_PKEY *sk, const char *m, size_t *signature_len) {
    EVP_MD_CTX *md_ctx = NULL;
    unsigned char *signature = NULL;
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];

    // 1. Hash the message (ECDSA signs the hash, not the raw message)
    if (!SHA256((const unsigned char*)m, strlen(m), msg_hash)) {
        fprintf(stderr, "Error: Failed to hash the message.\n");
        return NULL;
    }

    // 2. Create a context for signing
    md_ctx = EVP_MD_CTX_new();
    if (!md_ctx) {
        fprintf(stderr, "Error: Could not create MD context.\n");
        goto cleanup;
    }

    // 3. Initialize the signing operation
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, sk) <= 0) {
        fprintf(stderr, "Error: Could not initialize digest signing.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 4. Get the required buffer size for the signature
    if (EVP_DigestSign(md_ctx, NULL, signature_len, msg_hash, SHA256_DIGEST_LENGTH) <= 0) {
        // This call is expected to fail with some OpenSSL versions, but it sets the length.
        // A more robust way is to get max size. Let's do that instead.
        *signature_len = EVP_PKEY_size(sk);
    }

    signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        fprintf(stderr, "Error: Malloc failed for signature.\n");
        goto cleanup;
    }

    // 5. Perform the signing
    if (EVP_DigestSign(md_ctx, signature, signature_len, msg_hash, SHA256_DIGEST_LENGTH) <= 0) {
        fprintf(stderr, "Error: Could not perform digest signing.\n");
        ERR_print_errors_fp(stderr);
        free(signature);
        signature = NULL;
        goto cleanup;
    }

    printf("Successfully signed message.\n");

cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    return signature;
}

/**
 * @brief Corresponds to Python's `vk.verify`.
 * Verifies a signature against a message and a public key.
 *
 * @param vk The public key (EVP_PKEY object).
 * @param m The message string that was supposedly signed.
 * @param signature The signature to verify.
 * @param signature_len The length of the signature.
 * @return 1 for successful verification, 0 for failed verification, -1 for an error.
 */
int verify_signature(EVP_PKEY *vk, const char *m, const unsigned char *signature, size_t signature_len) {
    EVP_MD_CTX *md_ctx = NULL;
    unsigned char msg_hash[SHA256_DIGEST_LENGTH];
    int result = -1; // Default to error

    // 1. Hash the message
    if (!SHA256((const unsigned char*)m, strlen(m), msg_hash)) {
        fprintf(stderr, "Error: Failed to hash the message for verification.\n");
        return -1;
    }

    // 2. Create a context for verification
    md_ctx = EVP_MD_CTX_new();
    if (!md_ctx) {
        fprintf(stderr, "Error: Could not create MD context for verification.\n");
        goto cleanup;
    }

    // 3. Initialize the verification operation
    if (EVP_DigestVerifyInit(md_ctx, NULL, EVP_sha256(), NULL, vk) <= 0) {
        fprintf(stderr, "Error: Could not initialize digest verification.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 4. Perform the verification
    result = EVP_DigestVerify(md_ctx, signature, signature_len, msg_hash, SHA256_DIGEST_LENGTH);
    // result == 1 -> Success
    // result == 0 -> Failure (signature does not match)
    // result < 0  -> Error during operation

cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    return result;
}


/**
 * @brief Corresponds to the Python `test` function.
 * Runs the full generate-sign-verify cycle.
 */
void test() {
    EVP_PKEY *sk = NULL; // In Python: sk = SigningKey.generate()
    // The verifying key (vk) is part of the same sk object in OpenSSL.

    unsigned char *signature = NULL;
    size_t signature_len = 0;

    const char *message_to_sign = "hello world";
    const char *message_to_verify = "hello worfld"; // Note the intentional typo

    // Generate key
    sk = generate_key();
    if (!sk) {
        return; // Error already printed
    }

    // Sign the correct message
    signature = sign_message(sk, message_to_sign, &signature_len);
    if (!signature) {
        goto cleanup;
    }

    // Verify the signature against the INCORRECT message
    printf("\nAttempting to verify signature with message: \"%s\"\n", message_to_verify);
    int verification_result = verify_signature(sk, message_to_verify, signature, signature_len);

    // The Python code would raise a BadSignatureError, which stops execution.
    // Here, we check the return code and print the result.
    if (verification_result == 1) {
        printf("Verification successful. (This should not happen!)\n");
    } else if (verification_result == 0) {
        // This is the expected outcome
        printf("Verification FAILED as expected. The signature is not valid for the given message.\n");
    } else {
        fprintf(stderr, "An error occurred during verification.\n");
        ERR_print_errors_fp(stderr);
    }

cleanup:
    // Free all allocated resources
    if (sk) EVP_PKEY_free(sk);
    if (signature) free(signature);
}

int main() {
    // The test function encapsulates the entire logic.
    test();
    return 0;
}