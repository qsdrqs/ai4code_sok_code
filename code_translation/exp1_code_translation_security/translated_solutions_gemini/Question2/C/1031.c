#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/sha.h>
#include <openssl/err.h>

/**
 * @brief Handles OpenSSL errors by printing them to stderr and exiting.
 */
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

/**
 * @brief Loads an RSA private key from a PEM-formatted string.
 *
 * @param key_pem The null-terminated string containing the PEM private key.
 * @return A pointer to an EVP_PKEY structure, or NULL on failure.
 */
EVP_PKEY* load_private_key(const char* key_pem) {
    // Create a new BIO to read the key from memory
    BIO* bio = BIO_new_mem_buf(key_pem, -1);
    if (bio == NULL) {
        fprintf(stderr, "Failed to create BIO for private key\n");
        return NULL;
    }

    // Read the private key from the BIO
    // The password callback is NULL because the key is not encrypted.
    EVP_PKEY* pkey = PEM_read_bio_PrivateKey(bio, NULL, NULL, NULL);
    if (pkey == NULL) {
        fprintf(stderr, "Failed to read private key\n");
        ERR_print_errors_fp(stderr);
    }

    // Free the BIO resource
    BIO_free(bio);
    return pkey;
}

/**
 * @brief Signs a message using a private key (RSA-SHA256 with PKCS1_v1_5 padding).
 *
 * This function replicates the behavior of the Python `sign` function.
 *
 * @param msg The message data to sign.
 * @param msg_len The length of the message data.
 * @param pkey The private key (EVP_PKEY*) to use for signing.
 * @param sig A pointer that will be updated to point to the newly allocated signature buffer.
 *            The caller is responsible for freeing this buffer with free().
 * @param sig_len A pointer to a size_t that will be updated with the length of the signature.
 * @return 1 on success, 0 on failure.
 */
int sign_message(const unsigned char* msg, size_t msg_len, EVP_PKEY* pkey, unsigned char** sig, size_t* sig_len) {
    EVP_MD_CTX* md_ctx = NULL;
    int ret = 0;

    // 1. Create a message digest context
    if (!(md_ctx = EVP_MD_CTX_new())) {
        fprintf(stderr, "EVP_MD_CTX_new failed\n");
        goto cleanup;
    }

    // 2. Initialize the signing operation.
    // This specifies the hash algorithm (SHA256) and the private key.
    // OpenSSL will automatically use PKCS#1 v1.5 padding for RSA signing.
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, pkey) <= 0) {
        fprintf(stderr, "EVP_DigestSignInit failed\n");
        goto cleanup;
    }

    // 3. Provide the message to be hashed and signed
    if (EVP_DigestSignUpdate(md_ctx, msg, msg_len) <= 0) {
        fprintf(stderr, "EVP_DigestSignUpdate failed\n");
        goto cleanup;
    }

    // 4. Finalize the signing. First, get the required buffer size.
    if (EVP_DigestSignFinal(md_ctx, NULL, sig_len) <= 0) {
        fprintf(stderr, "EVP_DigestSignFinal (for size) failed\n");
        goto cleanup;
    }

    // 5. Allocate memory for the signature
    if (!(*sig = malloc(*sig_len))) {
        fprintf(stderr, "malloc failed\n");
        goto cleanup;
    }

    // 6. Get the actual signature
    if (EVP_DigestSignFinal(md_ctx, *sig, sig_len) <= 0) {
        fprintf(stderr, "EVP_DigestSignFinal (for signature) failed\n");
        free(*sig);
        *sig = NULL;
        goto cleanup;
    }

    // Success
    ret = 1;

cleanup:
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    if (ret == 0) {
        handle_errors(); // Print detailed OpenSSL errors on failure
    }
    return ret;
}

// --- Main function for demonstration ---
int main() {
    // A sample 2048-bit RSA private key in PEM format
    const char* priv_key_pem =
        "-----BEGIN RSA PRIVATE KEY-----\n"
        "MIIEowIBAAKCAQEAy8Dbv8prN40EGSv1dGg6GgY4VzIQbQy4L2h2dJgP0gT3dJgP\n"
        "0gT3dJgP0gT3dJgP0gT3dJgP0gT3dJgP0gT3dJgP0gT3dJgP0gT3dJgP0gT3dJgP\n"

        "// ... (rest of a valid private key) ... //\n"
        
        "-----END RSA PRIVATE KEY-----\n";

    // The message to be signed
    const unsigned char* msg = (const unsigned char*)"This is a test message for signing.";
    size_t msg_len = strlen((const char*)msg);

    // Pointers for the results
    EVP_PKEY* pkey = NULL;
    unsigned char* signature = NULL;
    size_t signature_len = 0;

    // Load OpenSSL error strings
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();

    // Load the private key
    pkey = load_private_key(priv_key_pem);
    if (!pkey) {
        return EXIT_FAILURE;
    }

    printf("Message to sign: \"%s\"\n", msg);

    // Call the signing function
    if (sign_message(msg, msg_len, pkey, &signature, &signature_len)) {
        printf("Successfully signed message.\n");
        printf("Signature is %zu bytes long.\n", signature_len);
        printf("Signature (hex): ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        fprintf(stderr, "Failed to sign message.\n");
    }

    // --- Cleanup ---
    if (pkey) EVP_PKEY_free(pkey);
    if (signature) free(signature);
    
    // Clean up OpenSSL
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}