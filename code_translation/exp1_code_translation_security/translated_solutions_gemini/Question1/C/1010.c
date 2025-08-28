/**
 * @file aes_gcm_example.c
 * @brief C implementation of AES encryption/decryption using OpenSSL.
 * 
 * This code translates a Python script that uses pycryptodome's AES in EAX mode.
 * Here, we use AES in GCM mode from the OpenSSL library. Both EAX and GCM are 
 * Authenticated Encryption with Associated Data (AEAD) modes, providing both
 * confidentiality and integrity.
 *
 * Dependencies: OpenSSL library (libssl and libcrypto)
 * Compilation: gcc -o aes_example aes_gcm_example.c -lssl -lcrypto
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Define constants for clarity
#define AES_KEY_SIZE 16    // 16 bytes = 128 bits
#define GCM_IV_SIZE 12     // Recommended size for GCM nonce/IV is 12 bytes (96 bits)
#define GCM_TAG_SIZE 16    // GCM authentication tag is 16 bytes (128 bits)

/**
 * @brief Utility function to handle OpenSSL errors.
 */
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

/**
 * @brief Function to generate a symmetric key.
 * This is equivalent to Python's os.urandom(16).
 * @param key Buffer to store the generated key.
 * @param key_len The length of the key to generate.
 * @return 1 on success, 0 on failure.
 */
int generate_key(unsigned char *key, int key_len) {
    if (!RAND_bytes(key, key_len)) {
        fprintf(stderr, "Error: Failed to generate random key.\n");
        return 0;
    }
    return 1;
}

/**
 * @brief Function to encrypt a string using a symmetric key (AES-128-GCM).
 * @param key The 16-byte symmetric key.
 * @param plaintext The data to encrypt.
 * @param plaintext_len The length of the plaintext.
 * @param iv The buffer to store the generated 12-byte nonce/IV.
 * @param ciphertext The buffer to store the resulting ciphertext.
 * @param tag The buffer to store the 16-byte authentication tag.
 * @return The length of the ciphertext, or -1 on failure.
 */
int encrypt_data(const unsigned char *key, const unsigned char *plaintext, int plaintext_len,
                 unsigned char *iv, unsigned char *ciphertext, unsigned char *tag) {
    EVP_CIPHER_CTX *ctx;
    int len = 0;
    int ciphertext_len = 0;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // 2. Generate a random IV (nonce)
    if (!RAND_bytes(iv, GCM_IV_SIZE)) {
        fprintf(stderr, "Error: Failed to generate random IV.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 3. Initialize the encryption operation with AES-128-GCM
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL)) {
        handleErrors();
    }

    // 4. Set the IV length (required for GCM)
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, GCM_IV_SIZE, NULL)) {
        handleErrors();
    }

    // 5. Provide the key and IV to the context
    if (1 != EVP_EncryptInit_ex(ctx, NULL, NULL, key, iv)) {
        handleErrors();
    }

    // 6. Encrypt the plaintext
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        handleErrors();
    }
    ciphertext_len = len;

    // 7. Finalize the encryption (any remaining ciphertext)
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        handleErrors();
    }
    ciphertext_len += len;

    // 8. Get the authentication tag
    if (1 != EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, GCM_TAG_SIZE, tag)) {
        handleErrors();
    }

    // 9. Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

/**
 * @brief Function to decrypt a string.
 * @param key The 16-byte symmetric key.
 * @param iv The 12-byte nonce/IV used for encryption.
 * @param ciphertext The encrypted data.
 * @param ciphertext_len The length of the ciphertext.
 * @param tag The 16-byte authentication tag.
 * @param plaintext The buffer to store the decrypted data.
 * @return The length of the decrypted plaintext, or -1 on verification failure.
 */
int decrypt_data(const unsigned char *key, const unsigned char *iv, const unsigned char *ciphertext, int ciphertext_len,
                 const unsigned char *tag, unsigned char *plaintext) {
    EVP_CIPHER_CTX *ctx;
    int len = 0;
    int plaintext_len = 0;
    int ret;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // 2. Initialize the decryption operation with AES-128-GCM
    if (!EVP_DecryptInit_ex(ctx, EVP_aes_128_gcm(), NULL, NULL, NULL)) {
        handleErrors();
    }

    // 3. Set the IV length
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, GCM_IV_SIZE, NULL)) {
        handleErrors();
    }

    // 4. Provide the key and IV
    if (!EVP_DecryptInit_ex(ctx, NULL, NULL, key, iv)) {
        handleErrors();
    }

    // 5. Decrypt the ciphertext
    if (!EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        handleErrors();
    }
    plaintext_len = len;

    // 6. Set the expected authentication tag. This is a crucial step for verification.
    if (!EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, GCM_TAG_SIZE, (void*)tag)) {
        handleErrors();
    }

    // 7. Finalize the decryption. This will fail if the tag does not match.
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    // 8. Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        // Success: tag matched
        plaintext_len += len;
        return plaintext_len;
    } else {
        // Failure: tag did not match, data is corrupt or tampered with
        return -1;
    }
}

/**
 * @brief Helper function to print byte arrays in hex format.
 */
void print_hex(const char* label, const unsigned char* data, int len) {
    printf("%s: ", label);
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Main function demonstrating the encryption and decryption process.
 */
int main(void) {
    // --- Example ---
    
    // Buffers for cryptographic data
    unsigned char key[AES_KEY_SIZE];
    unsigned char iv[GCM_IV_SIZE]; // IV is the nonce
    unsigned char tag[GCM_TAG_SIZE];
    
    // Plaintext and buffers for ciphertext/decrypted text
    const char *test_data_str = "Jim's test";
    unsigned char *plaintext = (unsigned char *)test_data_str;
    int plaintext_len = strlen((char *)plaintext);
    
    // Ciphertext buffer must be at least as long as plaintext
    unsigned char ciphertext[128]; 
    // Restored data buffer
    unsigned char restored_data[128];

    int ciphertext_len;
    int restored_data_len;

    // 1. Generate a key
    if (!generate_key(key, sizeof(key))) {
        return 1;
    }
    print_hex("Generated Key", key, sizeof(key));
    printf("Original Data: \"%s\"\n\n", plaintext);

    // 2. Encrypt the data
    ciphertext_len = encrypt_data(key, plaintext, plaintext_len, iv, ciphertext, tag);
    if (ciphertext_len < 0) {
        fprintf(stderr, "Encryption failed!\n");
        return 1;
    }
    printf("--- Encryption ---\n");
    print_hex("IV (Nonce)", iv, sizeof(iv));
    print_hex("Ciphertext", ciphertext, ciphertext_len);
    print_hex("Auth Tag", tag, sizeof(tag));
    printf("\n");

    // 3. Decrypt the data
    restored_data_len = decrypt_data(key, iv, ciphertext, ciphertext_len, tag, restored_data);
    if (restored_data_len < 0) {
        fprintf(stderr, "Decryption failed! The data may have been tampered with.\n");
        return 1;
    }
    
    // Null-terminate the restored string to print it
    restored_data[restored_data_len] = '\0';

    printf("--- Decryption ---\n");
    printf("Restored Data: \"%s\"\n", restored_data);

    // 4. Verify the result
    if (strcmp((char*)plaintext, (char*)restored_data) == 0) {
        printf("\nSuccess: Original and restored data match!\n");
    } else {
        printf("\nFailure: Data mismatch!\n");
    }

    return 0;
}