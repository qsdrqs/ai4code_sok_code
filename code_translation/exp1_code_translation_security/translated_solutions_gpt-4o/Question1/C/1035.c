#include <openssl/evp.h>
#include <openssl/rand.h>
#include <stdio.h>
#include <string.h>

#define AES_KEYLEN 16  // AES key length in bytes
#define AES_BLOCK_SIZE 16

void handleErrors(void) {
    fprintf(stderr, "An error occurred\n");
    exit(1);
}

int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,
            unsigned char *ciphertext, unsigned char *tag, unsigned char *nonce) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Generate a random nonce
    if (!RAND_bytes(nonce, AES_BLOCK_SIZE)) handleErrors();

    // Initialize the encryption operation
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL))
        handleErrors();

    // Set the nonce
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, nonce)) handleErrors();

    // Provide the message to be encrypted, and obtain the encrypted output
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handleErrors();
    ciphertext_len = len;

    // Finalize the encryption
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) handleErrors();
    ciphertext_len += len;

    // Get the tag
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, 16, tag))
        handleErrors();

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

int decrypt(unsigned char *ciphertext, int ciphertext_len, unsigned char *tag,
            unsigned char *key, unsigned char *nonce, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    int ret;

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    // Initialize the decryption operation
    if (!EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL))
        handleErrors();

    // Set the nonce
    if (!EVP_DecryptInit_ex(ctx, NULL, NULL, key, nonce)) handleErrors();

    // Provide the message to be decrypted, and obtain the plaintext output
    if (!EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();
    plaintext_len = len;

    // Set expected tag value
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, 16, tag))
        handleErrors();

    // Finalize the decryption
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        // Success
        plaintext_len += len;
        return plaintext_len;
    } else {
        // Verification failed
        return -1;
    }
}

int main(void) {
    unsigned char key[AES_KEYLEN] = "Sixteen byte key";
    unsigned char plaintext[] = "message";
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];
    unsigned char tag[16];
    unsigned char nonce[AES_BLOCK_SIZE];

    int decryptedtext_len, ciphertext_len;

    // Encrypt the plaintext
    ciphertext_len = encrypt(plaintext, strlen((char *)plaintext), key, ciphertext, tag, nonce);

    // Decrypt the ciphertext
    decryptedtext_len = decrypt(ciphertext, ciphertext_len, tag, key, nonce, decryptedtext);

    if (decryptedtext_len < 0) {
        printf("Decryption failed\n");
    } else {
        decryptedtext[decryptedtext_len] = '\0';
        printf("Decrypted text is: %s\n", decryptedtext);
    }

    return 0;
}