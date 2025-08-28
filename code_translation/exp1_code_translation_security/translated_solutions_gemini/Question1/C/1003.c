#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/aes.h>

// A utility function to handle OpenSSL errors
void handle_openssl_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// Determines the correct OpenSSL AES-ECB cipher based on key length
const EVP_CIPHER* get_aes_ecb_cipher(int key_length_bytes) {
    if (key_length_bytes == 16) { // AES-128
        return EVP_aes_128_ecb();
    } else if (key_length_bytes == 24) { // AES-192
        return EVP_aes_192_ecb();
    } else if (key_length_bytes == 32) { // AES-256
        return EVP_aes_256_ecb();
    }
    return NULL; // Unsupported key size
}

/**
 * @brief Encrypts data using AES in ECB mode.
 *
 * @param plaintext The data to encrypt.
 * @param plaintext_len The length of the plaintext data.
 * @param key The encryption key.
 * @param key_len The length of the key (must be 16, 24, or 32 bytes).
 * @param ciphertext_len A pointer to an integer where the length of the resulting ciphertext will be stored.
 * @return A dynamically allocated buffer containing the ciphertext. The caller is responsible for freeing this memory.
 *         Returns NULL on failure.
 */
unsigned char* encrypt_bytes_aes(const unsigned char* plaintext, int plaintext_len, const unsigned char* key, int key_len, int* ciphertext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    unsigned char *ciphertext;

    // Select the appropriate cipher based on the key length
    const EVP_CIPHER* cipher = get_aes_ecb_cipher(key_len);
    if (!cipher) {
        fprintf(stderr, "Error: Invalid key length: %d bytes. Must be 16, 24, or 32.\n", key_len);
        return NULL;
    }

    // Create and initialise the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handle_openssl_errors();
    }

    // Initialise the encryption operation.
    // IMPORTANT: IV is not used in ECB mode, so it's NULL.
    if (1 != EVP_EncryptInit_ex(ctx, cipher, NULL, key, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        handle_openssl_errors();
    }
    
    // EVP_EncryptUpdate can be called multiple times if the data is large.
    // The buffer for ciphertext needs to be large enough for the encrypted data plus the final block.
    // A safe size is plaintext_len + block_size. AES block size is 16 bytes.
    ciphertext = (unsigned char*)malloc(plaintext_len + AES_BLOCK_SIZE);
    if (!ciphertext) {
        fprintf(stderr, "Error: malloc failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Provide the message to be encrypted, and obtain the encrypted output.
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        handle_openssl_errors();
    }
    *ciphertext_len = len;

    // Finalise the encryption. This will handle padding.
    // Further ciphertext bytes may be written at this stage.
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        handle_openssl_errors();
    }
    *ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext;
}

/**
 * @brief Decrypts data using AES in ECB mode.
 *
 * @param ciphertext The data to decrypt.
 * @param ciphertext_len The length of the ciphertext data.
 * @param key The decryption key.
 * @param key_len The length of the key (must be 16, 24, or 32 bytes).
 * @param plaintext_len A pointer to an integer where the length of the resulting plaintext will be stored.
 * @return A dynamically allocated buffer containing the plaintext. The caller is responsible for freeing this memory.
 *         Returns NULL on failure.
 */
unsigned char* decrypt_bytes_aes(const unsigned char* ciphertext, int ciphertext_len, const unsigned char* key, int key_len, int* plaintext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    unsigned char *plaintext;

    // Select the appropriate cipher based on the key length
    const EVP_CIPHER* cipher = get_aes_ecb_cipher(key_len);
    if (!cipher) {
        fprintf(stderr, "Error: Invalid key length: %d bytes. Must be 16, 24, or 32.\n", key_len);
        return NULL;
    }

    // Create and initialise the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handle_openssl_errors();
    }

    // Initialise the decryption operation.
    if (1 != EVP_DecryptInit_ex(ctx, cipher, NULL, key, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        handle_openssl_errors();
    }

    // The plaintext buffer must be at least as large as the ciphertext.
    plaintext = (unsigned char*)malloc(ciphertext_len);
    if (!plaintext) {
        fprintf(stderr, "Error: malloc failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // Provide the message to be decrypted, and obtain the plaintext output.
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        handle_openssl_errors();
    }
    *plaintext_len = len;

    // Finalise the decryption. This will remove the padding.
    // If the padding is invalid, this function will fail.
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        fprintf(stderr, "Error: Decryption failed. Check the key or data integrity (bad padding).\n");
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        // Load error strings to get a more descriptive error
        ERR_print_errors_fp(stderr);
        return NULL;
    }
    *plaintext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext;
}

// Helper function to print byte arrays in hex format
void print_hex(const char* title, const unsigned char* data, int len) {
    printf("%s (%d bytes):\n", title, len);
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // A 128-bit (16-byte) key
    unsigned char key[] = "0123456789abcdef"; // 16 bytes

    // Data to be encrypted. Note it's not a multiple of 16.
    // The library will handle PKCS#7 padding automatically.
    unsigned char data[] = "This is a top secret message.";
    int data_len = strlen((char*)data);

    printf("Original Data: \"%s\"\n\n", data);

    // --- Encryption ---
    int ciphertext_len;
    unsigned char* ciphertext = encrypt_bytes_aes(data, data_len, key, sizeof(key) - 1, &ciphertext_len);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed.\n");
        return 1;
    }
    print_hex("Ciphertext (Hex)", ciphertext, ciphertext_len);

    // --- Decryption ---
    int decrypted_len;
    unsigned char* decrypted_text = decrypt_bytes_aes(ciphertext, ciphertext_len, key, sizeof(key) - 1, &decrypted_len);
    if (!decrypted_text) {
        fprintf(stderr, "Decryption failed.\n");
        free(ciphertext);
        return 1;
    }

    // Add a null terminator to print as a string
    decrypted_text[decrypted_len] = '\0';
    printf("Decrypted Data: \"%s\"\n", decrypted_text);

    // --- Verification ---
    if (data_len == decrypted_len && memcmp(data, decrypted_text, data_len) == 0) {
        printf("\nSuccess: Decrypted data matches original data.\n");
    } else {
        printf("\nFailure: Decrypted data does not match original data.\n");
    }

    // --- Cleanup ---
    free(ciphertext);
    free(decrypted_text);

    return 0;
}