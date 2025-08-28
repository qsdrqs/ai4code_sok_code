#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

// Define constants for clarity and consistency.
// AES-256 uses a 32-byte (256-bit) key.
#define AES_256_KEY_SIZE 32
// GCM uses a 12-byte (96-bit) nonce for best performance and security.
#define GCM_NONCE_SIZE 12
// GCM produces a 16-byte (128-bit) authentication tag.
#define GCM_TAG_SIZE 16

/**
 * @brief Encrypts a plaintext using AES-256-GCM.
 *
 * @param key The 32-byte symmetric key.
 * @param plaintext The data to encrypt.
 * @param plaintext_len The length of the plaintext.
 * @param nonce A buffer to store the generated 12-byte nonce.
 * @param ciphertext A buffer to store the resulting ciphertext. Must be at least plaintext_len bytes.
 * @param tag A buffer to store the generated 16-byte authentication tag.
 * @return The length of the ciphertext on success, or -1 on failure.
 */
int encrypt_aes_gcm(const unsigned char *key,
                    const unsigned char *plaintext, int plaintext_len,
                    unsigned char *nonce,
                    unsigned char *ciphertext,
                    unsigned char *tag) {

    EVP_CIPHER_CTX *ctx = NULL;
    int len = 0;
    int ciphertext_len = 0;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error: EVP_CIPHER_CTX_new failed.\n");
        return -1;
    }

    // 2. Generate a random nonce (IV)
    if (RAND_bytes(nonce, GCM_NONCE_SIZE) != 1) {
        fprintf(stderr, "Error: RAND_bytes failed to generate nonce.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 3. Initialize the encryption operation with AES-256-GCM
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, key, nonce) != 1) {
        fprintf(stderr, "Error: EVP_EncryptInit_ex failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 4. Provide the message to be encrypted
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len) != 1) {
        fprintf(stderr, "Error: EVP_EncryptUpdate failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len = len;

    // 5. Finalize the encryption. This is important for some modes but for GCM,
    // it mainly finalizes the state. The output length is usually 0 here.
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        fprintf(stderr, "Error: EVP_EncryptFinal_ex failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len += len;

    // 6. Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, GCM_TAG_SIZE, tag) != 1) {
        fprintf(stderr, "Error: EVP_CTRL_GCM_GET_TAG failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 7. Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

/**
 * @brief Decrypts a ciphertext using AES-256-GCM.
 *
 * @param key The 32-byte symmetric key.
 * @param nonce The 12-byte nonce used for encryption.
 * @param ciphertext The ciphertext to decrypt.
 * @param ciphertext_len The length of the ciphertext.
 * @param tag The 16-byte authentication tag to verify.
 * @param plaintext A buffer to store the decrypted plaintext. Must be at least ciphertext_len bytes.
 * @return The length of the decrypted plaintext on success (tag is valid), or -1 on failure (tag is invalid).
 */
int decrypt_aes_gcm(const unsigned char *key,
                    const unsigned char *nonce,
                    const unsigned char *ciphertext, int ciphertext_len,
                    const unsigned char *tag,
                    unsigned char *plaintext) {

    EVP_CIPHER_CTX *ctx = NULL;
    int len = 0;
    int plaintext_len = 0;
    int ret;

    // 1. Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error: EVP_CIPHER_CTX_new failed.\n");
        return -1;
    }

    // 2. Initialize the decryption operation with AES-256-GCM
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, key, nonce) != 1) {
        fprintf(stderr, "Error: EVP_DecryptInit_ex failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 3. Provide the message to be decrypted
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        fprintf(stderr, "Error: EVP_DecryptUpdate failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len = len;

    // 4. Set the expected authentication tag. This must be done *before* finalizing.
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, GCM_TAG_SIZE, (void *)tag) != 1) {
        fprintf(stderr, "Error: EVP_CTRL_GCM_SET_TAG failed.\n");
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // 5. Finalize the decryption. A positive return value indicates success,
    // meaning the tag was successfully verified.
    ret = EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    // 6. Clean up
    EVP_CIPHER_CTX_free(ctx);

    if (ret > 0) {
        // Success: Tag verified
        plaintext_len += len;
        return plaintext_len;
    } else {
        // Failure: Tag verification failed
        fprintf(stderr, "Decryption failed: Authentication tag is invalid.\n");
        return -1;
    }
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, int len) {
    printf("%s: ", label);
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // A hardcoded 32-byte key for demonstration purposes.
    // In a real application, this should be securely generated and managed.
    unsigned char key[AES_256_KEY_SIZE] = "0123456789abcdef0123456789abcdef";

    // The plaintext message to encrypt
    unsigned char plaintext[] = "This is a secret message that needs to be encrypted.";
    int plaintext_len = strlen((char *)plaintext);

    // Buffers to hold the output of the encryption
    unsigned char nonce[GCM_NONCE_SIZE];
    unsigned char tag[GCM_TAG_SIZE];
    // Ciphertext will have the same length as the plaintext in GCM mode
    unsigned char ciphertext[sizeof(plaintext)];

    printf("--- Encryption ---\n");
    printf("Plaintext: %s\n", plaintext);

    // Encrypt the data
    int ciphertext_len = encrypt_aes_gcm(key, plaintext, plaintext_len, nonce, ciphertext, tag);

    if (ciphertext_len == -1) {
        fprintf(stderr, "Encryption failed!\n");
        return 1;
    }

    printf("Encryption successful.\n");
    print_hex("Nonce (IV)", nonce, GCM_NONCE_SIZE);
    print_hex("Tag", tag, GCM_TAG_SIZE);
    print_hex("Ciphertext", ciphertext, ciphertext_len);
    printf("\n");

    // --- Decryption ---
    printf("--- Decryption ---\n");

    // Buffer to hold the decrypted text
    unsigned char decrypted_text[sizeof(plaintext)];

    // Decrypt the data
    int decrypted_len = decrypt_aes_gcm(key, nonce, ciphertext, ciphertext_len, tag, decrypted_text);

    if (decrypted_len == -1) {
        fprintf(stderr, "Decryption failed!\n");
        return 1;
    }

    // Add a null terminator to the decrypted string to print it safely
    decrypted_text[decrypted_len] = '\0';

    printf("Decryption successful.\n");
    printf("Decrypted Text: %s\n", decrypted_text);

    // Verify that the decrypted text matches the original plaintext
    if (strcmp((char*)plaintext, (char*)decrypted_text) == 0) {
        printf("\nSuccess: Original plaintext and decrypted text match.\n");
    } else {
        printf("\nFailure: Mismatch between original and decrypted text.\n");
    }

    return 0;
}