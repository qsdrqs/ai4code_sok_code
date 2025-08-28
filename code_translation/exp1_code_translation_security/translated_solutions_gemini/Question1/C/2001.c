#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

// Fernet constants
#define SIGNING_KEY_SIZE 16
#define ENCRYPTION_KEY_SIZE 16
#define IV_SIZE 16
#define HMAC_SIZE 32
#define VERSION_OFFSET 0
#define TIMESTAMP_OFFSET 1
#define IV_OFFSET 9
#define CIPHERTEXT_OFFSET 25

// Helper function to convert standard Base64 to URL-safe Base64
void base64_to_urlsafe(char *b64_str) {
    for (int i = 0; b64_str[i]; i++) {
        if (b64_str[i] == '+') b64_str[i] = '-';
        if (b64_str[i] == '/') b64_str[i] = '_';
        if (b64_str[i] == '=') b64_str[i] = '\0'; // Fernet spec removes padding
    }
}

// Helper function to convert URL-safe Base64 to standard Base64
void urlsafe_to_base64(char *urlsafe_str) {
    for (int i = 0; urlsafe_str[i]; i++) {
        if (urlsafe_str[i] == '-') urlsafe_str[i] = '+';
        if (urlsafe_str[i] == '_') urlsafe_str[i] = '/';
    }
    // Add back padding if necessary for standard decoders
    int len = strlen(urlsafe_str);
    int padding = len % 4;
    if (padding) {
        padding = 4 - padding;
        strncat(urlsafe_str, "====", padding);
    }
}

// Helper function for Base64 decoding
unsigned char* base64_decode(const char* input, int* out_len) {
    // Make a mutable copy to handle URL-safe conversion
    char* mutable_input = strdup(input);
    if (!mutable_input) return NULL;
    urlsafe_to_base64(mutable_input);

    BIO *bio, *b64;
    int decode_len = (strlen(mutable_input) * 3) / 4; // Estimate length
    unsigned char* buffer = (unsigned char*)malloc(decode_len + 1);
    if (!buffer) {
        free(mutable_input);
        return NULL;
    }

    bio = BIO_new_mem_buf(mutable_input, -1);
    b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    *out_len = BIO_read(bio, buffer, strlen(mutable_input));
    buffer[*out_len] = '\0';

    BIO_free_all(bio);
    free(mutable_input);
    return buffer;
}

// Helper function for Base64 encoding
char* base64_encode(const unsigned char* input, int length) {
    BIO *bio, *b64;
    BUF_MEM *bufferPtr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(bio, input, length);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &bufferPtr);

    // Create a null-terminated string from the buffer
    char *b64_text = (char *)malloc(bufferPtr->length + 1);
    if (!b64_text) {
        BIO_free_all(bio);
        return NULL;
    }
    memcpy(b64_text, bufferPtr->data, bufferPtr->length);
    b64_text[bufferPtr->length] = '\0';

    BIO_free_all(bio);
    
    // Convert to URL-safe variant
    base64_to_urlsafe(b64_text);

    return b64_text;
}


/**
 * @brief Encrypts a message using the Fernet specification.
 *
 * @param msg The plaintext message to encrypt.
 * @param key_b64 The URL-safe base64 encoded 32-byte key.
 * @param output A pointer that will be updated to point to the newly allocated,
 *               null-terminated, URL-safe base64 encoded token. The caller is
 *               responsible for freeing this memory.
 * @return The length of the output token, or -1 on error.
 */
int encrypt_fernet(const unsigned char *msg, const char *key_b64, char **output) {
    int key_len;
    unsigned char *raw_key = base64_decode(key_b64, &key_len);
    if (!raw_key || key_len != 32) {
        fprintf(stderr, "Error: Invalid Fernet key.\n");
        free(raw_key);
        return -1;
    }

    unsigned char *signing_key = raw_key;
    unsigned char *encryption_key = raw_key + SIGNING_KEY_SIZE;

    // 1. Prepare payload components
    unsigned char version = 0x80;
    unsigned char iv[IV_SIZE];
    if (RAND_bytes(iv, sizeof(iv)) != 1) {
        fprintf(stderr, "Error: Failed to generate random IV.\n");
        free(raw_key);
        return -1;
    }

    uint64_t timestamp = time(NULL);
    unsigned char timestamp_bytes[8];
    for (int i = 0; i < 8; i++) {
        timestamp_bytes[i] = (timestamp >> (56 - 8 * i)) & 0xFF;
    }

    // 2. Encrypt (AES-128-CBC)
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int ciphertext_len;
    int len;
    int msg_len = strlen((const char*)msg);
    unsigned char *ciphertext = malloc(msg_len + EVP_MAX_BLOCK_LENGTH);
    if (!ciphertext) {
        free(raw_key);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, msg, msg_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 3. Assemble data for HMAC
    int pre_hmac_len = 1 + 8 + IV_SIZE + ciphertext_len;
    unsigned char *pre_hmac_data = malloc(pre_hmac_len);
    if (!pre_hmac_data) {
        free(raw_key);
        free(ciphertext);
        return -1;
    }
    
    memcpy(pre_hmac_data + VERSION_OFFSET, &version, 1);
    memcpy(pre_hmac_data + TIMESTAMP_OFFSET, timestamp_bytes, 8);
    memcpy(pre_hmac_data + IV_OFFSET, iv, IV_SIZE);
    memcpy(pre_hmac_data + CIPHERTEXT_OFFSET, ciphertext, ciphertext_len);

    // 4. Calculate HMAC-SHA256
    unsigned char hmac_result[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, SIGNING_KEY_SIZE, pre_hmac_data, pre_hmac_len, hmac_result, &hmac_len);

    // 5. Assemble final payload
    int final_payload_len = pre_hmac_len + hmac_len;
    unsigned char *final_payload = malloc(final_payload_len);
    if (!final_payload) {
        free(raw_key);
        free(ciphertext);
        free(pre_hmac_data);
        return -1;
    }
    memcpy(final_payload, pre_hmac_data, pre_hmac_len);
    memcpy(final_payload + pre_hmac_len, hmac_result, hmac_len);

    // 6. Base64 encode the final payload
    *output = base64_encode(final_payload, final_payload_len);
    int output_len = strlen(*output);

    // Cleanup
    free(raw_key);
    free(ciphertext);
    free(pre_hmac_data);
    free(final_payload);

    return output_len;
}

/**
 * @brief Decrypts a Fernet token.
 *
 * @param token The URL-safe base64 encoded token to decrypt.
 * @param key_b64 The URL-safe base64 encoded 32-byte key.
 * @param ttl The time-to-live in seconds. If the token is older than this,
 *            decryption will fail. Use 0 to disable TTL check.
 * @param output A pointer that will be updated to point to the newly allocated,
 *               null-terminated, decrypted plaintext. The caller is responsible
 *               for freeing this memory.
 * @return The length of the decrypted plaintext, or -1 on error.
 */
int decrypt_fernet(const char *token, const char *key_b64, int ttl, unsigned char **output) {
    int key_len;
    unsigned char *raw_key = base64_decode(key_b64, &key_len);
    if (!raw_key || key_len != 32) {
        fprintf(stderr, "Error: Invalid Fernet key.\n");
        free(raw_key);
        return -1;
    }

    unsigned char *signing_key = raw_key;
    unsigned char *encryption_key = raw_key + SIGNING_KEY_SIZE;

    // 1. Base64 decode the token
    int decoded_len;
    unsigned char *decoded_token = base64_decode(token, &decoded_len);
    if (!decoded_token) {
        fprintf(stderr, "Error: Failed to decode token.\n");
        free(raw_key);
        return -1;
    }

    if (decoded_len < CIPHERTEXT_OFFSET + HMAC_SIZE) {
        fprintf(stderr, "Error: Token is too short.\n");
        free(raw_key);
        free(decoded_token);
        return -1;
    }

    // 2. Verify HMAC
    int data_to_verify_len = decoded_len - HMAC_SIZE;
    unsigned char *data_to_verify = decoded_token;
    unsigned char *received_hmac = decoded_token + data_to_verify_len;

    unsigned char calculated_hmac[HMAC_SIZE];
    HMAC(EVP_sha256(), signing_key, SIGNING_KEY_SIZE, data_to_verify, data_to_verify_len, calculated_hmac, NULL);

    if (CRYPTO_memcmp(received_hmac, calculated_hmac, HMAC_SIZE) != 0) {
        fprintf(stderr, "Error: Invalid signature.\n");
        free(raw_key);
        free(decoded_token);
        return -1;
    }

    // 3. Parse payload and check constraints
    if (decoded_token[VERSION_OFFSET] != 0x80) {
        fprintf(stderr, "Error: Invalid token version.\n");
        free(raw_key);
        free(decoded_token);
        return -1;
    }

    uint64_t timestamp = 0;
    for (int i = 0; i < 8; i++) {
        timestamp = (timestamp << 8) | decoded_token[TIMESTAMP_OFFSET + i];
    }

    if (ttl > 0) {
        uint64_t current_time = time(NULL);
        if (timestamp + ttl < current_time) {
            fprintf(stderr, "Error: Token has expired.\n");
            free(raw_key);
            free(decoded_token);
            return -1;
        }
    }

    // 4. Decrypt (AES-128-CBC)
    unsigned char *iv = decoded_token + IV_OFFSET;
    unsigned char *ciphertext = decoded_token + CIPHERTEXT_OFFSET;
    int ciphertext_len = data_to_verify_len - CIPHERTEXT_OFFSET;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    *output = malloc(ciphertext_len + 1); // Plaintext can't be larger than ciphertext
    if (!*output) {
        free(raw_key);
        free(decoded_token);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    int plaintext_len;
    int len;

    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, *output, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, *output + len, &len) <= 0) {
        fprintf(stderr, "Error: Decryption failed (check padding/key).\n");
        free(raw_key);
        free(decoded_token);
        free(*output);
        *output = NULL;
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len += len;
    (*output)[plaintext_len] = '\0';

    // Cleanup
    EVP_CIPHER_CTX_free(ctx);
    free(raw_key);
    free(decoded_token);

    return plaintext_len;
}

// --- Main function for demonstration ---
int main() {
    // In a real application, generate a key once and store it securely.
    // This is a valid Fernet key for demonstration purposes.
    const char *key_b64 = "h0H_Vp6hn9yK-pG-j2Sss_aD4b5pTDUu2v_2L8A1_3s=";
    const unsigned char *message = (const unsigned char *)"This is a secret message.";

    printf("Original message: %s\n", message);
    printf("Using key: %s\n\n", key_b64);

    // --- Encryption ---
    char *encrypted_token = NULL;
    int token_len = encrypt_fernet(message, key_b64, &encrypted_token);

    if (token_len > 0) {
        printf("Encrypted token: %s\n\n", encrypted_token);

        // --- Decryption ---
        unsigned char *decrypted_message = NULL;
        // Set TTL to 60 seconds. Change to 0 to disable TTL check.
        int decrypted_len = decrypt_fernet(encrypted_token, key_b64, 60, &decrypted_message);

        if (decrypted_len > 0) {
            printf("Decrypted message: %s\n", decrypted_message);

            // Verification
            if (strcmp((const char*)message, (const char*)decrypted_message) == 0) {
                printf("\nSuccess: Original and decrypted messages match.\n");
            } else {
                printf("\nFailure: Messages do not match.\n");
            }
            free(decrypted_message);
        } else {
            fprintf(stderr, "Decryption failed.\n");
        }
        free(encrypted_token);
    } else {
        fprintf(stderr, "Encryption failed.\n");
    }

    return 0;
}