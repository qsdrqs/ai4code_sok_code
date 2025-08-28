#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Define constants for key, IV, and tag sizes for AES-128-GCM
#define AES_KEY_SIZE 16  // 128 bits
#define GCM_IV_SIZE 12   // Recommended size for GCM
#define GCM_TAG_SIZE 16  // GCM tag is 128 bits

// Function to print OpenSSL errors and exit
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

/**
 * Encrypts a message using AES-128-GCM.
 * This function is analogous to the Python `encrypt` function.
 *
 * @param plaintext The message to encrypt.
 * @param plaintext_len The length of the message.
 * @param key The 16-byte encryption key.
 * @param iv The 12-byte initialization vector (nonce).
 * @param ciphertext Buffer where the encrypted data will be written.
 * @param tag Buffer where the 16-byte authentication tag will be written.
 * @return The length of the ciphertext, or -1 on failure.
 */
int encrypt_gcm(const unsigned char *plaintext, int plaintext_len,
                const unsigned char *key, const unsigned char *iv,
                unsigned char *ciphertext, unsigned char *tag) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // 2. Initialize the encryption operation.
    // For AES-128-GCM, we use EVP_aes_128_gcm().
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL)) {
        handleErrors();
    }

    // 3. Set the IV length. GCM recommends 12 bytes.
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, GCM_IV_SIZE, NULL)) {
        handleErrors();
    }

    // 4. Initialize the key and IV
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, iv)) {
        handleErrors();
    }

    // 5. Provide the message to be encrypted, and obtain the encrypted output.
    // EVP_EncryptUpdate can be called multiple times if the message is large.
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        handleErrors();
    }
    ciphertext_len = len;

    // 6. Finalize the encryption.
    // This call produces no more ciphertext, but it is required.
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        handleErrors();
    }
    ciphertext_len += len;

    // 7. Get the authentication tag.
    // The tag is critical for verifying the integrity of the message on decryption.
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, GCM_TAG_SIZE, tag)) {
        handleErrors();
    }

    // 8. Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

/**
 * Decrypts a message using AES-128-GCM.
 * This function is analogous to the Python `decrypt` function.
 *
 * @param ciphertext The encrypted data.
 * @param ciphertext_len The length of the encrypted data.
 * @param key The 16-byte encryption key.
 * @param iv The 12-byte initialization vector (nonce).
 * @param tag The 16-byte authentication tag.
 * @param plaintext Buffer where the decrypted message will be written.
 * @return The length of the plaintext, or -1 on verification failure.
 */
int decrypt_gcm(const unsigned char *ciphertext, int ciphertext_len,
                const unsigned char *key, const unsigned char *iv,
                const unsigned char *tag, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    int ret;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // 2. Initialize the decryption operation.
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL)) {
        handleErrors();
    }

    // 3. Set the IV length.
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, GCM_IV_SIZE, NULL)) {
        handleErrors();
    }

    // 4. Initialize the key and IV
    if (1 != EVP_DecryptInit_ex(ctx, NULL, NULL, key, iv)) {
        handleErrors();
    }

    // 5. Provide the message to be decrypted, and obtain the plaintext output.
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        handleErrors();
    }
    plaintext_len = len;

    // 6. Set the expected authentication tag.
    // This must be done *before* finalizing the decryption.
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, GCM_TAG_SIZE, (void *)tag)) {
        handleErrors();
    }

    // 7. Finalize the decryption.
    // A positive return value indicates success, anything else is a failure.
    // The tag is automatically verified in this step.
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    // 8. Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        // Success: tag verified.
        plaintext_len += len;
        return plaintext_len;
    } else {
        // Failure: tag did not verify.
        return -1;
    }
}

int main(void) {
    // The key from the Python example
    unsigned char *key = (unsigned char *)"Sixteen byte key";

    // The message to encrypt
    unsigned char *plaintext = (unsigned char *)"message";
    int plaintext_len = strlen((char *)plaintext);

    // Buffers for the output
    unsigned char *iv;
    unsigned char *tag;
    unsigned char *ciphertext;
    unsigned char *decrypted_text;

    int ciphertext_len;
    int decrypted_len;

    // Allocate memory for IV, tag, and buffers
    iv = malloc(GCM_IV_SIZE);
    tag = malloc(GCM_TAG_SIZE);
    // Ciphertext can be up to block size larger, but for GCM it's same size
    ciphertext = malloc(plaintext_len);
    decrypted_text = malloc(plaintext_len + 1); // +1 for null terminator

    if (!iv || !tag || !ciphertext || !decrypted_text) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // --- ENCRYPTION ---
    // Generate a random IV (nonce)
    if (1 != RAND_bytes(iv, GCM_IV_SIZE)) {
        handleErrors();
    }

    printf("Encrypting the message...\n");
    ciphertext_len = encrypt_gcm(plaintext, plaintext_len, key, iv, ciphertext, tag);
    if (ciphertext_len < 0) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    printf("Ciphertext is %d bytes long.\n", ciphertext_len);
    // Note: Printing binary data directly can produce garbage.
    // For real applications, you would encode this (e.g., Base64).

    // --- DECRYPTION ---
    printf("\nDecrypting the message...\n");
    decrypted_len = decrypt_gcm(ciphertext, ciphertext_len, key, iv, tag, decrypted_text);

    if (decrypted_len < 0) {
        printf("Decryption failed. The message may have been tampered with.\n");
    } else {
        // Add a null terminator to the decrypted text to treat it as a string
        decrypted_text[decrypted_len] = '\0';
        printf("Decrypted text: %s\n", decrypted_text);

        // Verify that the round trip was successful
        if (memcmp(plaintext, decrypted_text, plaintext_len) == 0) {
            printf("\nSuccess: Original and decrypted messages match.\n");
        } else {
            printf("\nFailure: Original and decrypted messages do NOT match.\n");
        }
    }

    // --- CLEANUP ---
    free(iv);
    free(tag);
    free(ciphertext);
    free(decrypted_text);

    return 0;
}