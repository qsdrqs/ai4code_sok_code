#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// A helper function to handle OpenSSL errors
void handleErrors(void) {
    fprintf(stderr, "An error occurred in an OpenSSL function.\n");
    // You can add more detailed error printing here if needed, e.g., ERR_print_errors_fp(stderr);
    abort();
}

/**
 * @brief Encrypts a plaintext message using AES-256-CBC.
 *
 * This function replicates the Python 'encrypt' logic.
 * 1. A random 16-byte IV is generated.
 * 2. The plaintext is encrypted using the provided key and the generated IV.
 *    OpenSSL handles PKCS#7 padding automatically.
 * 3. The final ciphertext is a concatenation of [IV][encrypted_data].
 *
 * @param plaintext The message to encrypt (null-terminated string).
 * @param key A 32-byte (256-bit) key.
 * @param ciphertext A pointer that will be updated to point to the newly allocated ciphertext.
 *                   The caller is responsible for freeing this memory.
 * @return The total length of the ciphertext (IV + encrypted data). Returns -1 on failure.
 */
int encrypt_c(const unsigned char *plaintext, const unsigned char *key, unsigned char **ciphertext) {
    int plaintext_len = strlen((char *)plaintext);
    int len;
    int ciphertext_len;

    // The IV is always 16 bytes for AES
    unsigned char iv[AES_BLOCK_SIZE];

    // Create and initialize the context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        handleErrors();
    }

    // Generate a random IV
    if (RAND_bytes(iv, sizeof(iv)) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return -1; // Failed to create random IV
    }

    // Initialize the encryption operation.
    // Using AES-256 in CBC mode.
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    // Allocate memory for ciphertext. The final ciphertext will be IV + encrypted_message.
    // EVP_EncryptUpdate can be called multiple times, but for simplicity, we do it once.
    // The buffer needs to be at least plaintext_len + block_size.
    *ciphertext = malloc(AES_BLOCK_SIZE + plaintext_len + AES_BLOCK_SIZE);
    if (!*ciphertext) {
        EVP_CIPHER_CTX_free(ctx);
        return -1; // Malloc failed
    }

    // Prepend the IV to the ciphertext buffer
    memcpy(*ciphertext, iv, AES_BLOCK_SIZE);

    // Provide the message to be encrypted, and obtain the encrypted output.
    // EVP_EncryptUpdate can be called multiple times if the message is large.
    if (1 != EVP_EncryptUpdate(ctx, *ciphertext + AES_BLOCK_SIZE, &len, plaintext, plaintext_len)) {
        handleErrors();
    }
    ciphertext_len = len;

    // Finalize the encryption. This handles any remaining data and padding.
    if (1 != EVP_EncryptFinal_ex(ctx, *ciphertext + AES_BLOCK_SIZE + len, &len)) {
        handleErrors();
    }
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return AES_BLOCK_SIZE + ciphertext_len;
}

/**
 * @brief Decrypts a ciphertext using AES-256-CBC.
 *
 * This function replicates the Python 'decrypt' logic.
 * 1. It assumes the first 16 bytes of the ciphertext is the IV.
 * 2. It decrypts the rest of the ciphertext using the key and the extracted IV.
 * 3. OpenSSL handles PKCS#7 unpadding automatically.
 *
 * @param ciphertext The data to decrypt, formatted as [IV][encrypted_data].
 * @param ciphertext_len The total length of the ciphertext.
 * @param key A 32-byte (256-bit) key.
 * @param plaintext A pointer that will be updated to point to the newly allocated plaintext.
 *                  The caller is responsible for freeing this memory.
 * @return The length of the decrypted plaintext. Returns -1 on failure.
 */
int decrypt_c(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, unsigned char **plaintext) {
    int len;
    int plaintext_len;

    // The IV is the first 16 bytes of the ciphertext
    if (ciphertext_len < AES_BLOCK_SIZE) {
        fprintf(stderr, "Ciphertext is too short to contain an IV.\n");
        return -1;
    }
    unsigned char *iv = (unsigned char *)ciphertext;

    // Create and initialize the context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        handleErrors();
    }

    // Initialize the decryption operation.
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    // Allocate memory for the plaintext. The decrypted message will be at most as long as the
    // ciphertext (minus the IV).
    *plaintext = malloc(ciphertext_len);
    if (!*plaintext) {
        EVP_CIPHER_CTX_free(ctx);
        return -1; // Malloc failed
    }

    // Provide the message to be decrypted, and obtain the plaintext output.
    // The actual ciphertext starts after the IV.
    if (1 != EVP_DecryptUpdate(ctx, *plaintext, &len, ciphertext + AES_BLOCK_SIZE, ciphertext_len - AES_BLOCK_SIZE)) {
        handleErrors();
    }
    plaintext_len = len;

    // Finalize the decryption. This will check and remove the padding.
    // If the padding is incorrect, this function will fail.
    if (1 != EVP_DecryptFinal_ex(ctx, *plaintext + len, &len)) {
        // Error usually means invalid key or corrupted ciphertext (including padding)
        free(*plaintext);
        *plaintext = NULL;
        EVP_CIPHER_CTX_free(ctx);
        fprintf(stderr, "Decryption failed. Check key or ciphertext integrity.\n");
        return -1;
    }
    plaintext_len += len;

    // Add a null terminator to be safe, making it a valid C string
    (*plaintext)[plaintext_len] = '\0';

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

// --- Main function for demonstration ---
int main(void) {
    // A 256-bit (32-byte) key.
    // In a real application, this should be securely generated and stored.
    unsigned char key[] = "01234567890123456789012345678901";

    // The message to be encrypted
    unsigned char *message = (unsigned char *)"This is a secret message.";

    unsigned char *ciphertext = NULL;
    unsigned char *decrypted_message = NULL;

    printf("Original Message: %s\n", message);

    // Encrypt the message
    int ciphertext_len = encrypt_c(message, key, &ciphertext);

    if (ciphertext_len != -1) {
        printf("Encrypted successfully. Ciphertext length: %d\n", ciphertext_len);
        // Note: Printing raw ciphertext can produce garbage on the terminal.
        // A common practice is to Base64 encode it for transport/storage.
    } else {
        fprintf(stderr, "Encryption failed.\n");
        return 1;
    }

    // Decrypt the message
    int decrypted_len = decrypt_c(ciphertext, ciphertext_len, key, &decrypted_message);

    if (decrypted_len != -1) {
        printf("Decrypted successfully.\n");
        printf("Decrypted Message: %s\n", decrypted_message);
    } else {
        fprintf(stderr, "Decryption failed.\n");
        free(ciphertext);
        return 1;
    }

    // Verification
    if (strcmp((char *)message, (char *)decrypted_message) == 0) {
        printf("\nSuccess: Original and decrypted messages match.\n");
    } else {
        printf("\nFailure: Messages do not match.\n");
    }

    // Free all allocated memory
    free(ciphertext);
    free(decrypted_message);

    return 0;
}