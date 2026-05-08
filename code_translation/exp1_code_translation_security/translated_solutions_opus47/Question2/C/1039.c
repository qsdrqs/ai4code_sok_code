#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <stdlib.h>
#include <string.h>

/*
 * Signs a message using the provided EC key.
 *
 * Parameters:
 *   message  - the (unused) input message, kept for parity with Python signature
 *   key      - EC_KEY used for signing
 *   sig_len  - out parameter receiving the length of the signature
 *
 * Returns: heap-allocated signature (caller must free), or NULL on error.
 *
 * NOTE: The original Python code signs the literal bytes b"message" rather
 *       than the `message` parameter. This behavior is preserved here.
 */
unsigned char* signMessage(const unsigned char* message, EC_KEY* key, unsigned int* sig_len) {
    const unsigned char msg[] = "message";
    size_t msg_len = sizeof(msg) - 1;  /* exclude null terminator */

    /* The python `ecdsa` library hashes the input before signing (SHA-1 by default). */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, hash);

    /* Allocate buffer large enough for the DER-encoded signature. */
    unsigned int signature_len = ECDSA_size(key);
    unsigned char* signature = (unsigned char*)malloc(signature_len);
    if (signature == NULL) {
        return NULL;
    }

    /* Sign the hash. */
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, signature, &signature_len, key) != 1) {
        free(signature);
        return NULL;
    }

    *sig_len = signature_len;
    return signature;
}