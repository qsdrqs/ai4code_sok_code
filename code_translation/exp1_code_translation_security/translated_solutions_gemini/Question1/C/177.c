#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

// Define standard lengths for AES-256-GCM
#define AES_KEY_LEN 32   // 256 bits
#define GCM_NONCE_LEN 12 // 96 bits is recommended for GCM
#define GCM_TAG_LEN 16   // 128 bits

// A struct to hold all parts of the encrypted data, similar to the Python tuple.
// We include the authentication tag, which is essential for security.
typedef struct {
    unsigned char *ciphertext;
    size_t ciphertext_len;
    unsigned char *nonce;
    size_t nonce_len;
    unsigned char *tag;
    size_t tag_len;
} EncryptedResult;

// Helper function to free the memory allocated for the EncryptedResult
void free_encrypted_result(EncryptedResult *data) {
    if (data) {
        free(data->ciphertext);
        free(data->nonce);
        free(data->tag);
        free(data);
    }
}

/**
 * @brief Encrypts plaintext using AES-256-GCM.
 *
 * Corresponds to the Python `encrypt` function.
 *
 * @param m The plaintext message to encrypt.
 * @param m_len The length of the plaintext message.
 * @param sk The 256-bit (32-byte) secret key.
 * @return A pointer to an EncryptedResult struct containing the ciphertext, nonce, and tag.
 *         Returns NULL on failure. The caller is responsible for freeing this struct
 *         using free_encrypted_result().
 */
EncryptedResult* encrypt_c(const unsigned char *m, size_t m_len, const unsigned char *sk) {
    // 1. Allocate the result struct and its members
    EncryptedResult *result = malloc(sizeof(EncryptedResult));
    if (!result) return NULL;

    result->ciphertext = malloc(m_len);
    result->nonce = malloc(GCM_NONCE_LEN);
    result->tag = malloc(GCM_TAG_LEN);

    if (!result->ciphertext || !result->nonce || !result->tag) {
        free_encrypted_result(result);
        return NULL;
    }
    result->nonce_len = GCM_NONCE_LEN;
    result->tag_len = GCM_TAG_LEN;

    // 2. Create and initialize the context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free_encrypted_result(result);
        return NULL;
    }

    // 3. Generate a random nonce (IV)
    if (RAND_bytes(result->nonce, result->nonce_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free_encrypted_result(result);
        return NULL;
    }

    // 4. Initialize encryption with AES-256-GCM
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, sk, result->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free_encrypted_result(result);
        return NULL;
    }

    // 5. Encrypt the plaintext
    int len;
    if (EVP_EncryptUpdate(ctx, result->ciphertext, &len, m, m_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free_encrypted_result(result);
        return NULL;
    }
    result->ciphertext_len = len;

    // 6. Finalize the encryption (not strictly necessary for GCM but good practice)
    if (EVP_EncryptFinal_ex(ctx, result->ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free_encrypted_result(result);
        return NULL;
    }
    result->ciphertext_len += len;

    // 7. Get the authentication tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, result->tag_len, result->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free_encrypted_result(result);
        return NULL;
    }

    // 8. Clean up and return
    EVP_CIPHER_CTX_free(ctx);
    return result;
}

/**
 * @brief Decrypts ciphertext using AES-256-GCM.
 *
 * Corresponds to the Python `decrypt` function.
 *
 * @param cn A pointer to the EncryptedResult struct containing ciphertext, nonce, and tag.
 * @param sk The 256-bit (32-byte) secret key.
 * @param decrypted_len A pointer to a size_t variable where the length of the decrypted
 *                      plaintext will be stored.
 * @return A pointer to the decrypted plaintext. Returns NULL if decryption or authentication
 *         fails. The caller is responsible for freeing this buffer.
 */
unsigned char* decrypt_c(const EncryptedResult *cn, const unsigned char *sk, size_t *decrypted_len) {
    // 1. Create and initialize the context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    // 2. Initialize decryption with AES-256-GCM
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, sk, cn->nonce) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // 3. Allocate buffer for decrypted plaintext
    unsigned char *plaintext = malloc(cn->ciphertext_len);
    if (!plaintext) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    // 4. Provide the ciphertext to be decrypted
    int len;
    if (EVP_DecryptUpdate(ctx, plaintext, &len, cn->ciphertext, cn->ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }
    *decrypted_len = len;

    // 5. Set the expected authentication tag. This is a CRITICAL step.
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, cn->tag_len, (void*)cn->tag) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        return NULL;
    }

    // 6. Finalize decryption. This will fail if the tag does not match.
    // A return value of 1 means success, 0 means failure (tag mismatch).
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(plaintext);
        printf("Decryption failed: Authentication tag mismatch.\n");
        return NULL;
    }
    *decrypted_len += len;

    // 7. Clean up and return
    EVP_CIPHER_CTX_free(ctx);
    return plaintext;
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// --- Main function to demonstrate usage ---
int main() {
    // A sample 256-bit key and plaintext
    unsigned char sk[AES_KEY_LEN];
    unsigned char m[] = "This is a top secret message.";

    // In a real application, use a secure key derivation function (like PBKDF2 or Argon2)
    // or a secure random generator. For this example, we'll just fill it.
    if (RAND_bytes(sk, sizeof(sk)) != 1) {
        fprintf(stderr, "Failed to generate random key.\n");
        return 1;
    }

    printf("--- Encryption ---\n");
    print_hex("Plaintext", m, strlen((char*)m));
    print_hex("Secret Key", sk, sizeof(sk));

    // Encrypt the message
    EncryptedResult *encrypted_data = encrypt_c(m, strlen((char*)m), sk);
    if (!encrypted_data) {
        fprintf(stderr, "Encryption failed.\n");
        return 1;
    }

    print_hex("Nonce (IV)", encrypted_data->nonce, encrypted_data->nonce_len);
    print_hex("Auth Tag", encrypted_data->tag, encrypted_data->tag_len);
    print_hex("Ciphertext", encrypted_data->ciphertext, encrypted_data->ciphertext_len);
    printf("\n");

    // Decrypt the message
    printf("--- Decryption (Correct) ---\n");
    size_t decrypted_len;
    unsigned char *decrypted_m = decrypt_c(encrypted_data, sk, &decrypted_len);

    if (decrypted_m) {
        printf("Decryption successful!\n");
        printf("Recovered Plaintext: %.*s\n", (int)decrypted_len, decrypted_m);
        free(decrypted_m);
    } else {
        fprintf(stderr, "Decryption failed.\n");
    }
    printf("\n");

    // --- Tampering Demo ---
    printf("--- Decryption (Tampered) ---\n");
    printf("Tampering with the first byte of the ciphertext...\n");
    encrypted_data->ciphertext[0] ^= 0xFF; // Flip all bits of the first byte

    print_hex("Tampered Ciphertext", encrypted_data->ciphertext, encrypted_data->ciphertext_len);

    decrypted_m = decrypt_c(encrypted_data, sk, &decrypted_len);
    if (!decrypted_m) {
        printf("As expected, decryption failed due to tampering.\n");
    } else {
        // This block should not be reached
        fprintf(stderr, "ERROR: Tampered data was decrypted successfully!\n");
        free(decrypted_m);
    }

    // Clean up allocated memory
    free_encrypted_result(encrypted_data);

    return 0;
}