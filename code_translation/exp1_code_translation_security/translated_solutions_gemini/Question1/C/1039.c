#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL Headers
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <openssl/aes.h>

// Helper function to perform URL-safe Base64 encoding
char* url_safe_base64_encode(const unsigned char* input, int length) {
    const int pl = 4 * ((length + 2) / 3);
    char* output = malloc(pl + 1);
    if (!output) return NULL;

    int out_len = EVP_EncodeBlock((unsigned char*)output, input, length);
    if (out_len == -1) {
        free(output);
        return NULL;
    }

    // Make it URL-safe
    for (int i = 0; i < out_len; i++) {
        if (output[i] == '+') output[i] = '-';
        if (output[i] == '/') output[i] = '_';
    }
    
    // Remove padding
    for (int i = out_len - 1; i >= 0 && output[i] == '='; i--) {
        output[i] = '\0';
        out_len--;
    }
    output[out_len] = '\0';

    return output;
}

// Helper function to perform URL-safe Base64 decoding
unsigned char* url_safe_base64_decode(const char* input, int* out_len) {
    int len = strlen(input);
    char* temp_input = malloc(len + 4); // +4 for padding
    if (!temp_input) return NULL;
    strcpy(temp_input, input);

    // Replace URL-safe characters back to standard Base64
    for (int i = 0; i < len; i++) {
        if (temp_input[i] == '-') temp_input[i] = '+';
        if (temp_input[i] == '_') temp_input[i] = '/';
    }

    // Add padding if necessary
    int mod = len % 4;
    if (mod == 2) strcat(temp_input, "==");
    else if (mod == 3) strcat(temp_input, "=");
    
    int padded_len = strlen(temp_input);
    unsigned char* output = malloc(padded_len); // Max possible size
    if (!output) {
        free(temp_input);
        return NULL;
    }

    *out_len = EVP_DecodeBlock(output, (const unsigned char*)temp_input, padded_len);
    free(temp_input);

    if (*out_len == -1) {
        free(output);
        return NULL;
    }

    return output;
}

/**
 * @brief Encrypts a message using the Fernet specification.
 *
 * @param message The plaintext message to encrypt.
 * @param key The URL-safe Base64 encoded 32-byte key.
 * @param encrypted_message A pointer to a char* that will be allocated and filled with the result.
 *                          The caller is responsible for freeing this memory.
 * @return 0 on success, -1 on failure.
 */
int encrypt_message(const char* message, const char* key, char** encrypted_message) {
    int ret = -1;
    unsigned char* raw_key = NULL;
    unsigned char* iv = NULL;
    unsigned char* ciphertext = NULL;
    unsigned char* hmac_buffer = NULL;
    unsigned char* final_buffer = NULL;
    EVP_CIPHER_CTX* ctx = NULL;

    // 1. Decode the key
    int raw_key_len;
    raw_key = url_safe_base64_decode(key, &raw_key_len);
    if (!raw_key || raw_key_len != 32) {
        fprintf(stderr, "Error: Invalid Fernet key.\n");
        goto cleanup;
    }
    unsigned char* signing_key = raw_key;
    unsigned char* encryption_key = raw_key + 16;

    // 2. Get current time (64-bit big-endian)
    uint64_t timestamp = time(NULL);
    unsigned char timestamp_bytes[8];
    for (int i = 0; i < 8; i++) {
        timestamp_bytes[i] = (timestamp >> (56 - 8 * i)) & 0xFF;
    }

    // 3. Generate a random 16-byte IV
    iv = malloc(AES_BLOCK_SIZE);
    if (!iv || !RAND_bytes(iv, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error: Failed to generate IV.\n");
        goto cleanup;
    }

    // 4. Encrypt the message (AES-128-CBC with PKCS7 padding)
    int message_len = strlen(message);
    int ciphertext_len;
    int len;
    ciphertext = malloc(message_len + AES_BLOCK_SIZE); // Room for padding
    if (!ciphertext) goto cleanup;

    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) goto cleanup;

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char*)message, message_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;

    // 5. Create the buffer for HMAC
    size_t hmac_buffer_len = 1 + 8 + AES_BLOCK_SIZE + ciphertext_len;
    hmac_buffer = malloc(hmac_buffer_len);
    if (!hmac_buffer) goto cleanup;

    hmac_buffer[0] = 0x80; // Version byte
    memcpy(hmac_buffer + 1, timestamp_bytes, 8);
    memcpy(hmac_buffer + 1 + 8, iv, AES_BLOCK_SIZE);
    memcpy(hmac_buffer + 1 + 8 + AES_BLOCK_SIZE, ciphertext, ciphertext_len);

    // 6. Calculate HMAC-SHA256
    unsigned char hmac_result[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_buffer, hmac_buffer_len, hmac_result, &hmac_len);

    // 7. Concatenate everything for the final token
    size_t final_buffer_len = hmac_buffer_len + hmac_len;
    final_buffer = malloc(final_buffer_len);
    if (!final_buffer) goto cleanup;
    memcpy(final_buffer, hmac_buffer, hmac_buffer_len);
    memcpy(final_buffer + hmac_buffer_len, hmac_result, hmac_len);

    // 8. Base64 encode the final result
    *encrypted_message = url_safe_base64_encode(final_buffer, final_buffer_len);
    if (!*encrypted_message) goto cleanup;

    ret = 0; // Success

cleanup:
    if (ctx) EVP_CIPHER_CTX_free(ctx);
    if (raw_key) free(raw_key);
    if (iv) free(iv);
    if (ciphertext) free(ciphertext);
    if (hmac_buffer) free(hmac_buffer);
    if (final_buffer) free(final_buffer);
    return ret;
}

/**
 * @brief Decrypts a Fernet token.
 *
 * @param encrypted_message The Fernet token to decrypt.
 * @param key The URL-safe Base64 encoded 32-byte key.
 * @param decrypted_message A pointer to a char* that will be allocated and filled with the result.
 *                          The caller is responsible for freeing this memory.
 * @return 0 on success, -1 on failure (e.g., bad signature, invalid format).
 */
int decrypt_message(const char* encrypted_message, const char* key, char** decrypted_message) {
    int ret = -1;
    unsigned char* raw_key = NULL;
    unsigned char* decoded_token = NULL;
    char* plaintext = NULL;
    EVP_CIPHER_CTX* ctx = NULL;

    // 1. Decode the key
    int raw_key_len;
    raw_key = url_safe_base64_decode(key, &raw_key_len);
    if (!raw_key || raw_key_len != 32) {
        fprintf(stderr, "Error: Invalid Fernet key.\n");
        goto cleanup;
    }
    unsigned char* signing_key = raw_key;
    unsigned char* encryption_key = raw_key + 16;

    // 2. Base64 decode the token
    int decoded_len;
    decoded_token = url_safe_base64_decode(encrypted_message, &decoded_len);
    if (!decoded_token) {
        fprintf(stderr, "Error: Invalid Base64 in token.\n");
        goto cleanup;
    }

    // Minimum length: version(1) + timestamp(8) + iv(16) + hmac(32) = 57
    if (decoded_len < 57) {
        fprintf(stderr, "Error: Token is too short.\n");
        goto cleanup;
    }

    // 3. Verify HMAC
    size_t hmac_data_len = decoded_len - 32;
    unsigned char* provided_hmac = decoded_token + hmac_data_len;
    
    unsigned char calculated_hmac[EVP_MAX_MD_SIZE];
    unsigned int calculated_hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, decoded_token, hmac_data_len, calculated_hmac, &calculated_hmac_len);

    if (CRYPTO_memcmp(provided_hmac, calculated_hmac, 32) != 0) {
        fprintf(stderr, "Error: Invalid signature.\n");
        goto cleanup;
    }

    // 4. Check version byte
    if (decoded_token[0] != 0x80) {
        fprintf(stderr, "Error: Unknown token version.\n");
        goto cleanup;
    }

    // Optional: Check timestamp (Python's default TTL is 60s)
    // uint64_t timestamp = 0;
    // for (int i = 0; i < 8; i++) {
    //     timestamp = (timestamp << 8) | decoded_token[1 + i];
    // }
    // if (time(NULL) > timestamp + 60) {
    //     fprintf(stderr, "Error: Token has expired.\n");
    //     goto cleanup;
    // }

    // 5. Decrypt
    unsigned char* iv = decoded_token + 1 + 8;
    unsigned char* ciphertext = decoded_token + 1 + 8 + AES_BLOCK_SIZE;
    int ciphertext_len = hmac_data_len - (1 + 8 + AES_BLOCK_SIZE);

    plaintext = malloc(ciphertext_len + 1); // Max possible size
    if (!plaintext) goto cleanup;

    int plaintext_len;
    int len;

    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) goto cleanup;

    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len) <= 0) {
        // This usually means bad padding, i.e., incorrect key
        fprintf(stderr, "Error: Decryption failed. Check if the key is correct.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }
    plaintext_len += len;
    plaintext[plaintext_len] = '\0';

    *decrypted_message = plaintext;
    plaintext = NULL; // Transfer ownership to caller
    ret = 0; // Success

cleanup:
    if (ctx) EVP_CIPHER_CTX_free(ctx);
    if (raw_key) free(raw_key);
    if (decoded_token) free(decoded_token);
    if (plaintext) free(plaintext); // Free only if not transferred
    return ret;
}

// Main function to demonstrate usage
int main() {
    // In a real application, generate this once and store it securely.
    // Python: from cryptography.fernet import Fernet; Fernet.generate_key()
    const char* fernet_key = "a2V5X2Zvcl90cmFuc2xhdGlvbl9leGFtcGxlXzEyMw=="; // A valid 32-byte key, base64 encoded

    const char* original_message = "This is a secret message for the C translation.";
    char* encrypted = NULL;
    char* decrypted = NULL;

    printf("Original Message: %s\n", original_message);
    printf("Fernet Key: %s\n\n", fernet_key);

    // --- ENCRYPTION ---
    if (encrypt_message(original_message, fernet_key, &encrypted) == 0) {
        printf("Encrypted Token: %s\n\n", encrypted);

        // --- DECRYPTION ---
        if (decrypt_message(encrypted, fernet_key, &decrypted) == 0) {
            printf("Decrypted Message: %s\n\n", decrypted);

            // --- VERIFICATION ---
            if (strcmp(original_message, decrypted) == 0) {
                printf("SUCCESS: Decrypted message matches the original.\n");
            } else {
                printf("FAILURE: Decrypted message does NOT match the original.\n");
            }
            free(decrypted);
        } else {
            fprintf(stderr, "Decryption failed.\n");
        }
        free(encrypted);
    } else {
        fprintf(stderr, "Encryption failed.\n");
    }

    return 0;
}