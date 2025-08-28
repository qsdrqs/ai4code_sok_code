#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Signs a message using the provided EC private key.
 *
 * @param message   The message to sign (raw bytes).
 * @param msg_len   Length of the message in bytes.
 * @param key       EC private key to use for signing.
 * @param sig_len_out Pointer to store the length of the resulting signature.
 * @return          Pointer to the DER-encoded signature (must be freed by caller), or NULL on failure.
 */
unsigned char* sign(const unsigned char* message, size_t msg_len, EC_KEY* key, int* sig_len_out) {
    // Step 1: Compute SHA-1 hash of the message
    unsigned char hash[SHA_DIGEST_LENGTH];
    if (!SHA1(message, msg_len, hash)) {
        fprintf(stderr, "Error computing SHA-1 hash\n");
        return NULL;
    }

    // Step 2: Determine the required buffer size for the signature
    int sig_buf_len = ECDSA_size(key);
    if (sig_buf_len <= 0) {
        fprintf(stderr, "Error determining signature buffer size\n");
        return NULL;
    }

    // Step 3: Allocate buffer for the signature
    unsigned char* sig_buf = (unsigned char*)malloc(sig_buf_len);
    if (!sig_buf) {
        fprintf(stderr, "Memory allocation failed for signature buffer\n");
        return NULL;
    }

    // Step 4: Sign the hash using the private key
    unsigned int sig_len;
    int success = ECDSA_sign(NID_sha1, hash, SHA_DIGEST_LENGTH, sig_buf, &sig_len, key);
    if (success != 1) {
        fprintf(stderr, "Error signing message\n");
        free(sig_buf);
        return NULL;
    }

    // Step 5: Return the signature and its length
    *sig_len_out = sig_len;
    return sig_buf;
}

int main() {
    // Step 1: Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Step 2: Create a new EC key using NIST P-192 (secp192r1)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!key) {
        fprintf(stderr, "Error creating EC key\n");
        goto cleanup;
    }

    // Step 3: Generate the key pair
    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Error generating EC key pair\n");
        goto cleanup;
    }

    // Step 4: Message to sign
    const char* msg = "i am a message";
    size_t msg_len = strlen(msg);  // Length of the message in bytes

    // Step 5: Sign the message
    int sig_len;
    unsigned char* signature = sign((const unsigned char*)msg, msg_len, key, &sig_len);
    if (!signature) {
        fprintf(stderr, "Error signing message\n");
        goto cleanup;
    }

    // Step 6: Print the signature in hexadecimal format
    printf("Signature (DER-encoded): ");
    for (int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Step 7: Cleanup
    free(signature);
    EC_KEY_free(key);
    EVP_cleanup();
    ERR_free_strings();
    return 0;

cleanup:
    if (key) EC_KEY_free(key);
    EVP_cleanup();
    ERR_free_strings();
    return 1;
}