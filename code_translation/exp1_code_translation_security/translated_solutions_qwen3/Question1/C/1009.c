#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mbedtls/aes.h"
#include "mbedtls/entropy.h"
#include "mbedtls/ctr_drbg.h"

// Structure to hold encrypted data (ciphertext + tag)
typedef struct {
    unsigned char *ciphertext;
    size_t ciphertext_len;
    unsigned char *tag;
    size_t tag_len;
} EncryptedData;

// Function to encrypt a string using AES in EAX mode
EncryptedData encodeString(const char *message, const unsigned char *key, const unsigned char *nonce) {
    EncryptedData out = {0};

    mbedtls_aes_context aes_ctx;
    mbedtls_aes_init(&aes_ctx);
    mbedtls_aes_setkey_enc(&aes_ctx, key, 128); // 128-bit key

    size_t input_len = strlen(message);
    out.ciphertext = malloc(input_len);
    out.tag = malloc(16);
    out.tag_len = 16;
    out.ciphertext_len = input_len;

    int ret = mbedtls_aes_crypt_eax_encrypt(&aes_ctx,
                                            nonce, 16,         // Nonce and its length
                                            NULL, 0,           // No additional authenticated data
                                            (const unsigned char *)message, input_len,
                                            out.ciphertext,
                                            out.tag, 16);      // Tag length

    if (ret != 0) {
        // Handle encryption error
        free(out.ciphertext);
        free(out.tag);
        out.ciphertext = NULL;
        out.tag = NULL;
    }

    mbedtls_aes_free(&aes_ctx);
    return out;
}

// Function to decrypt and verify using AES in EAX mode
char* decodeString(const EncryptedData *p, const unsigned char *key, const unsigned char *nonce) {
    mbedtls_aes_context aes_ctx;
    mbedtls_aes_init(&aes_ctx);
    mbedtls_aes_setkey_enc(&aes_ctx, key, 128); // Use same key setup as encryption

    size_t output_len = p->ciphertext_len;
    unsigned char *output = malloc(output_len + 1); // +1 for null terminator
    if (!output) {
        mbedtls_aes_free(&aes_ctx);
        return NULL;
    }

    int ret = mbedtls_aes_crypt_eax_decrypt(&aes_ctx,
                                            nonce, 16,
                                            NULL, 0,
                                            p->ciphertext, p->ciphertext_len,
                                            output,
                                            p->tag, 16);

    mbedtls_aes_free(&aes_ctx);

    if (ret != 0) {
        // Tag verification failed
        free(output);
        return NULL;
    }

    output[output_len] = '\0'; // Null-terminate the string
    return (char *)output;
}

int main() {
    const char *originalMessage = "secret data";
    printf("Original Message: %s\n", originalMessage);

    // Initialize entropy and CTR-DRBG for random generation
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_entropy_init(&entropy);
    mbedtls_ctr_drbg_init(&ctr_drbg);

    // Seed the random number generator
    mbedtls_ctr_drbg_seed(&ctr_drbg, mbedtls_entropy_func, &entropy, NULL, 0);

    // Generate 16-byte key and nonce
    unsigned char key[16];
    unsigned char nonce[16];
    mbedtls_ctr_drbg_random(&ctr_drbg, key, 16);
    mbedtls_ctr_drbg_random(&ctr_drbg, nonce, 16);

    // Encrypt the message
    EncryptedData ciphertext = encodeString(originalMessage, key, nonce);
    if (!ciphertext.ciphertext || !ciphertext.tag) {
        printf("Encryption failed.\n");
        goto cleanup;
    }

    // Decrypt the message
    char *decodedString = decodeString(&ciphertext, key, nonce);
    if (!decodedString) {
        printf("Decryption failed (tag verification error).\n");
        goto cleanup;
    }

    printf("Decrypted Message: %s\n", decodedString);

cleanup:
    // Free allocated memory
    free(ciphertext.ciphertext);
    free(ciphertext.tag);
    free(decodedString);

    // Clean up entropy and CTR-DRBG
    mbedtls_ctr_drbg_free(&ctr_drbg);
    mbedtls_entropy_free(&entropy);

    return 0;
}