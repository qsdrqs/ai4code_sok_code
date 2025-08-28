#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

int encodeString(const char *message, unsigned char *key, unsigned char *nonce, unsigned char **ciphertext, unsigned char **tag) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Initialize the encryption operation
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) handleErrors();

    // Set the nonce
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) handleErrors();
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce)) handleErrors();

    // Provide the message to be encrypted, and obtain the encrypted output
    *ciphertext = (unsigned char *)malloc(strlen(message) + EVP_CIPHER_block_size(EVP_aes_128_eax()));
    if (1 != EVP_EncryptUpdate(ctx, *ciphertext, &len, (unsigned char *)message, strlen(message))) handleErrors();
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, *ciphertext + len, &len)) handleErrors();
    ciphertext_len += len;

    // Get the tag
    *tag = (unsigned char *)malloc(16);
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_GET_TAG, 16, *tag)) handleErrors();

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

int decodeString(unsigned char *ciphertext, int ciphertext_len, unsigned char *tag, unsigned char *key, unsigned char *nonce, char **plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    int ret;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Initialize the decryption operation
    if (!EVP_DecryptInit_ex(ctx, EVP_aes_128_eax(), NULL, NULL, NULL)) handleErrors();

    // Set the nonce
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_IVLEN, 16, NULL)) handleErrors();
    if (!EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce)) handleErrors();

    // Provide the message to be decrypted, and obtain the plaintext output
    *plaintext = (char *)malloc(ciphertext_len + 1);
    if (!EVP_DecryptUpdate(ctx, (unsigned char *)*plaintext, &len, ciphertext, ciphertext_len)) handleErrors();
    plaintext_len = len;

    // Set expected tag value
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_AEAD_SET_TAG, 16, tag)) handleErrors();

    // Finalize the decryption
    ret = EVP_DecryptFinal_ex(ctx, (unsigned char *)*plaintext + len, &len);

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        plaintext_len += len;
        (*plaintext)[plaintext_len] = '\0'; // Null-terminate the string
        return plaintext_len;
    } else {
        // Verification failed
        free(*plaintext);
        return -1;
    }
}

int main(void) {
    const char *originalMessage = "secret data";
    unsigned char key[16];
    unsigned char nonce[16];
    unsigned char *ciphertext;
    unsigned char *tag;
    char *decodedString;
    int ciphertext_len;

    // Generate random key and nonce
    if (!RAND_bytes(key, sizeof(key)) || !RAND_bytes(nonce, sizeof(nonce))) {
        fprintf(stderr, "Error generating random bytes.\n");
        return 1;
    }

    printf("Original Message: %s\n", originalMessage);

    // Encode the message
    ciphertext_len = encodeString(originalMessage, key, nonce, &ciphertext, &tag);

    // Decode the message
    if (decodeString(ciphertext, ciphertext_len, tag, key, nonce, &decodedString) >= 0) {
        printf("Decoded Message: %s\n", decodedString);
        free(decodedString);
    } else {
        fprintf(stderr, "Decryption failed.\n");
    }

    // Free allocated memory
    free(ciphertext);
    free(tag);

    return 0;
}