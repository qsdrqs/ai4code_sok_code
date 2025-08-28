#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Default Time-To-Live for a token, as used by the Python library
#define FERNET_TTL 60

// Helper function to perform URL-safe base64 encoding
// The caller is responsible for freeing the returned string.
char *url_safe_base64_encode(const unsigned char *input, int length) {
    const int pl = 4 * ((length + 2) / 3);
    char *output = malloc(pl + 1);
    if (output == NULL) {
        fprintf(stderr, "Failed to allocate memory for base64 encoding\n");
        return NULL;
    }

    int ol = EVP_EncodeBlock((unsigned char *)output, input, length);
    if (ol < 0) {
        free(output);
        fprintf(stderr, "EVP_EncodeBlock failed\n");
        return NULL;
    }

    for (int i = 0; i < ol; i++) {
        if (output[i] == '+') {
            output[i] = '-';
        } else if (output[i] == '/') {
            output[i] = '_';
        } else if (output[i] == '=') {
            // Fernet spec does not use padding in its base64
            output[i] = '\0';
            break;
        }
    }
    output[pl] = '\0';
    return output;
}

// Helper function to perform URL-safe base64 decoding
// The caller is responsible for freeing the returned buffer.
unsigned char *url_safe_base64_decode(const char *input, int *out_len) {
    int len = strlen(input);
    char *buffer = malloc(len + 1);
    if (buffer == NULL) {
        fprintf(stderr, "Failed to allocate memory for base64 decoding buffer\n");
        return NULL;
    }
    strcpy(buffer, input);

    for (int i = 0; i < len; i++) {
        if (buffer[i] == '-') {
            buffer[i] = '+';
        } else if (buffer[i] == '_') {
            buffer[i] = '/';
        }
    }

    // Add padding if necessary
    int mod_table[] = {0, 2, 1};
    int padding = mod_table[len % 4];
    if (padding) {
        strncat(buffer, "==", padding);
    }

    int decoded_len = 3 * (strlen(buffer) / 4) - padding;
    unsigned char *output = malloc(decoded_len + 1);
    if (output == NULL) {
        fprintf(stderr, "Failed to allocate memory for base64 decoding\n");
        free(buffer);
        return NULL;
    }

    *out_len = EVP_DecodeBlock(output, (const unsigned char *)buffer, strlen(buffer));
    free(buffer);

    if (*out_len < 0) {
        free(output);
        fprintf(stderr, "EVP_DecodeBlock failed\n");
        return NULL;
    }
    
    // Adjust length to actual decoded size
    *out_len = decoded_len;
    return output;
}


/**
 * @brief Encrypts a message using the Fernet specification.
 *
 * @param plaintext The input string to encrypt.
 * @param b64_key The URL-safe base64 encoded 32-byte key.
 * @param encrypted_len A pointer to store the length of the output encrypted message.
 * @return A newly allocated, URL-safe base64 encoded encrypted message. The caller must free this memory.
 *         Returns NULL on failure.
 */
char *encrypt(const char *plaintext, const char *b64_key, size_t *encrypted_len) {
    // 1. Decode the key
    int key_len;
    unsigned char *key = url_safe_base64_decode(b64_key, &key_len);
    if (key == NULL || key_len != 32) {
        fprintf(stderr, "Invalid key provided.\n");
        if (key) free(key);
        return NULL;
    }
    unsigned char *signing_key = key;
    unsigned char *encryption_key = key + 16;

    // 2. Prepare Fernet fields
    unsigned char version = 0x80;
    unsigned char iv[16];
    
    // Generate timestamp (64-bit big-endian)
    uint64_t timestamp = time(NULL);
    unsigned char ts_bytes[8];
    for (int i = 0; i < 8; i++) {
        ts_bytes[i] = (timestamp >> (56 - 8 * i)) & 0xFF;
    }

    // Generate random IV
    if (RAND_bytes(iv, sizeof(iv)) != 1) {
        fprintf(stderr, "Failed to generate random IV.\n");
        free(key);
        return NULL;
    }

    // 3. Encrypt the plaintext (AES-128-CBC with PKCS7 padding)
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int plaintext_len = strlen(plaintext);
    int ciphertext_len;
    int len;
    // Max ciphertext length is plaintext_len + block_size
    unsigned char *ciphertext = malloc(plaintext_len + EVP_MAX_BLOCK_LENGTH);
    if (!ciphertext) {
        fprintf(stderr, "Failed to allocate memory for ciphertext.\n");
        free(key);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char *)plaintext, plaintext_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Create the data to be signed (HMAC)
    size_t hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char *hmac_data = malloc(hmac_data_len);
    if (!hmac_data) {
        fprintf(stderr, "Failed to allocate memory for HMAC data.\n");
        free(key);
        free(ciphertext);
        return NULL;
    }
    
    memcpy(hmac_data, &version, 1);
    memcpy(hmac_data + 1, ts_bytes, 8);
    memcpy(hmac_data + 9, iv, 16);
    memcpy(hmac_data + 25, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, hmac_result, &hmac_len);
    free(hmac_data);

    // 6. Concatenate everything to create the final token
    size_t final_token_len = hmac_data_len + hmac_len;
    unsigned char *final_token = malloc(final_token_len);
    if (!final_token) {
        fprintf(stderr, "Failed to allocate memory for final token.\n");
        free(key);
        free(ciphertext);
        return NULL;
    }

    memcpy(final_token, &version, 1);
    memcpy(final_token + 1, ts_bytes, 8);
    memcpy(final_token + 9, iv, 16);
    memcpy(final_token + 25, ciphertext, ciphertext_len);
    memcpy(final_token + 25 + ciphertext_len, hmac_result, hmac_len);
    
    free(ciphertext);
    free(key);

    // 7. Base64 encode the final token
    char *b64_token = url_safe_base64_encode(final_token, final_token_len);
    *encrypted_len = strlen(b64_token);
    
    free(final_token);
    return b64_token;
}

/**
 * @brief Decrypts a Fernet token.
 *
 * @param encrypted_message The URL-safe base64 encoded Fernet token.
 * @param b64_key The URL-safe base64 encoded 32-byte key.
 * @param decrypted_len A pointer to store the length of the output decrypted string.
 * @return A newly allocated, null-terminated decrypted string. The caller must free this memory.
 *         Returns NULL on failure (e.g., invalid signature, expired token, bad format).
 */
char *decrypt(const char *encrypted_message, const char *b64_key, size_t *decrypted_len) {
    // 1. Decode the key
    int key_len;
    unsigned char *key = url_safe_base64_decode(b64_key, &key_len);
    if (key == NULL || key_len != 32) {
        fprintf(stderr, "Invalid key provided.\n");
        if (key) free(key);
        return NULL;
    }
    unsigned char *signing_key = key;
    unsigned char *encryption_key = key + 16;

    // 2. Base64 decode the encrypted message
    int token_len;
    unsigned char *token = url_safe_base64_decode(encrypted_message, &token_len);
    if (token == NULL) {
        fprintf(stderr, "Failed to decode message.\n");
        free(key);
        return NULL;
    }

    // 3. Check minimum length (Version + Timestamp + IV + HMAC)
    if (token_len < (1 + 8 + 16 + 32)) {
        fprintf(stderr, "Invalid token length.\n");
        free(key);
        free(token);
        return NULL;
    }

    // 4. Extract components
    unsigned char version = token[0];
    unsigned char *ts_bytes = token + 1;
    unsigned char *iv = token + 9;
    size_t ciphertext_len = token_len - (1 + 8 + 16 + 32);
    unsigned char *ciphertext = token + 25;
    unsigned char *provided_hmac = token + 25 + ciphertext_len;

    // 5. Verify HMAC
    size_t hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char *hmac_data = token; // The data to sign is the start of the token
    unsigned char calculated_hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, calculated_hmac, &hmac_len);

    if (CRYPTO_memcmp(provided_hmac, calculated_hmac, 32) != 0) {
        fprintf(stderr, "Invalid signature.\n");
        free(key);
        free(token);
        return NULL;
    }

    // 6. Verify version and timestamp
    if (version != 0x80) {
        fprintf(stderr, "Invalid token version.\n");
        free(key);
        free(token);
        return NULL;
    }

    uint64_t received_timestamp = 0;
    for (int i = 0; i < 8; i++) {
        received_timestamp = (received_timestamp << 8) | ts_bytes[i];
    }
    
    if (time(NULL) > (received_timestamp + FERNET_TTL)) {
        fprintf(stderr, "Token has expired.\n");
        free(key);
        free(token);
        return NULL;
    }

    // 7. Decrypt the ciphertext
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    char *decrypted_text = malloc(ciphertext_len + 1); // Can't be larger than ciphertext
    if (!decrypted_text) {
        fprintf(stderr, "Failed to allocate memory for decrypted text.\n");
        free(key);
        free(token);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    int len;
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, (unsigned char *)decrypted_text, &len, ciphertext, ciphertext_len);
    *decrypted_len = len;
    
    // Finalize decryption (handles PKCS7 padding removal)
    if (EVP_DecryptFinal_ex(ctx, (unsigned char *)decrypted_text + len, &len) <= 0) {
        fprintf(stderr, "Failed to decrypt: Invalid padding or ciphertext.\n");
        ERR_print_errors_fp(stderr);
        free(key);
        free(token);
        free(decrypted_text);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *decrypted_len += len;
    decrypted_text[*decrypted_len] = '\0'; // Null-terminate the string

    EVP_CIPHER_CTX_free(ctx);
    free(key);
    free(token);

    return decrypted_text;
}


// --- Main function for demonstration ---
int main() {
    // In a real application, load a key from a secure location.
    // This key was generated with Python's `Fernet.generate_key()`
    const char *key_b64 = "l1KVYb4LdIe2YpBwU-hZALq_CgS2aV_u4S2pXyF8z8A=";
    const char *message = "This is a secret message.";

    printf("Original message: %s\n", message);
    printf("Using key: %s\n\n", key_b64);

    // Encrypt
    size_t encrypted_len;
    char *encrypted_message = encrypt(message, key_b64, &encrypted_len);

    if (encrypted_message) {
        printf("Encrypted (len %zu): %s\n", encrypted_len, encrypted_message);

        // Decrypt
        size_t decrypted_len;
        char *decrypted_message = decrypt(encrypted_message, key_b64, &decrypted_len);

        if (decrypted_message) {
            printf("Decrypted (len %zu): %s\n\n", decrypted_len, decrypted_message);

            // Verify
            if (strcmp(message, decrypted_message) == 0) {
                printf("SUCCESS: Decrypted message matches original.\n");
            } else {
                printf("FAILURE: Decrypted message does NOT match original.\n");
            }
            free(decrypted_message);
        } else {
            fprintf(stderr, "Decryption failed.\n");
        }
        free(encrypted_message);
    } else {
        fprintf(stderr, "Encryption failed.\n");
    }

    return 0;
}