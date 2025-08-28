#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/kdf.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/err.h>

// For network byte order conversions (big-endian)
#ifdef _WIN32
#include <winsock2.h>
#pragma comment(lib, "ws2_32.lib")
#else
#include <arpa/inet.h>
#endif

// --- Utility Functions ---

// A simple error handler for OpenSSL functions
void handle_openssl_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// Calculates the required buffer size for URL-safe Base64 encoding
size_t b64_encoded_size(size_t inlen) {
    return 4 * ((inlen + 2) / 3);
}

// Calculates the maximum possible buffer size for URL-safe Base64 decoding
size_t b64_decoded_size(const char *in) {
    return (strlen(in) * 3) / 4;
}

// URL-safe Base64 encoding
char* urlsafe_b64encode(const unsigned char* buffer, size_t length) {
    size_t b64_len = b64_encoded_size(length);
    char* b64_text = (char*)malloc(b64_len + 1);
    if (!b64_text) return NULL;

    EVP_EncodeBlock((unsigned char*)b64_text, buffer, length);

    // Convert to URL-safe variant
    for (size_t i = 0; i < b64_len; ++i) {
        if (b64_text[i] == '+') b64_text[i] = '-';
        if (b64_text[i] == '/') b64_text[i] = '_';
    }
    
    // The Python library often works with unpadded base64, so we remove padding
    size_t end = b64_len;
    while (end > 0 && b64_text[end-1] == '=') {
        end--;
    }
    b64_text[end] = '\0';

    return b64_text;
}

// URL-safe Base64 decoding
unsigned char* urlsafe_b64decode(const char* b64_text, size_t* out_len) {
    size_t len = strlen(b64_text);
    // Create a mutable copy to restore standard Base64 characters and padding
    char* standard_b64 = (char*)malloc(len + 4); // +4 for potential padding
    if (!standard_b64) return NULL;
    strcpy(standard_b64, b64_text);

    for (size_t i = 0; i < len; ++i) {
        if (standard_b64[i] == '-') standard_b64[i] = '+';
        if (standard_b64[i] == '_') standard_b64[i] = '/';
    }

    // Add padding if necessary
    size_t mod = len % 4;
    if (mod == 2) strcat(standard_b64, "==");
    else if (mod == 3) strcat(standard_b64, "=");
    
    size_t decoded_buf_size = b64_decoded_size(standard_b64);
    unsigned char* buffer = (unsigned char*)malloc(decoded_buf_size);
    if (!buffer) {
        free(standard_b64);
        return NULL;
    }

    int decoded_len = EVP_DecodeBlock(buffer, (const unsigned char*)standard_b64, strlen(standard_b64));
    if (decoded_len < 0) {
        free(buffer);
        free(standard_b64);
        return NULL;
    }
    
    *out_len = decoded_len;
    free(standard_b64);
    return buffer;
}


// --- Core Cryptography Functions (matching Python's logic) ---

/**
 * @brief Derives a secret key from a password and salt using PBKDF2-HMAC-SHA256.
 * @return A URL-safe Base64 encoded key. This matches the Python implementation.
 */
char* _derive_key(const char* password, const unsigned char* salt, int iterations) {
    unsigned char derived_key[32]; // Fernet keys are 32 bytes (256 bits)

    if (PKCS5_PBKDF2_HMAC(password, strlen(password), salt, 16, iterations, EVP_sha256(), 32, derived_key) != 1) {
        handle_openssl_errors();
    }

    // The Python function returns the b64-encoded version of the key
    return urlsafe_b64encode(derived_key, 32);
}

/**
 * @brief Implements Fernet's encryption logic.
 * @param message The plaintext message to encrypt.
 * @param b64_key The URL-safe Base64 encoded 32-byte key.
 * @return A URL-safe Base64 encoded Fernet token.
 */
char* fernet_encrypt(const char* message, const char* b64_key) {
    // 1. Decode the key and split it
    size_t raw_key_len;
    unsigned char* raw_key = urlsafe_b64decode(b64_key, &raw_key_len);
    if (raw_key_len != 32) {
        fprintf(stderr, "Invalid key length after decoding.\n");
        free(raw_key);
        return NULL;
    }
    unsigned char signing_key[16];
    unsigned char encryption_key[16];
    memcpy(signing_key, raw_key, 16);
    memcpy(encryption_key, raw_key + 16, 16);
    free(raw_key);

    // 2. Prepare Fernet fields
    unsigned char version = 0x80;
    uint64_t timestamp = htonll((uint64_t)time(NULL)); // Big-endian timestamp
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) handle_openssl_errors();

    // 3. Encrypt the message (AES-128-CBC with PKCS7 padding)
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    int len;
    int ciphertext_len;
    size_t message_len = strlen(message);
    unsigned char* ciphertext = (unsigned char*)malloc(message_len + EVP_MAX_BLOCK_LENGTH);

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, (const unsigned char*)message, message_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Create the data to be signed (HMAC)
    size_t hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char* hmac_data = (unsigned char*)malloc(hmac_data_len);
    memcpy(hmac_data, &version, 1);
    memcpy(hmac_data + 1, &timestamp, 8);
    memcpy(hmac_data + 1 + 8, iv, 16);
    memcpy(hmac_data + 1 + 8 + 16, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, hmac_result, &hmac_len);
    free(hmac_data);

    // 6. Concatenate the final Fernet token payload
    size_t final_payload_len = hmac_data_len + hmac_len;
    unsigned char* final_payload = (unsigned char*)malloc(final_payload_len);
    memcpy(final_payload, &version, 1);
    memcpy(final_payload + 1, &timestamp, 8);
    memcpy(final_payload + 1 + 8, iv, 16);
    memcpy(final_payload + 1 + 8 + 16, ciphertext, ciphertext_len);
    memcpy(final_payload + 1 + 8 + 16 + ciphertext_len, hmac_result, hmac_len);
    free(ciphertext);

    // 7. Base64 encode the final payload
    char* b64_token = urlsafe_b64encode(final_payload, final_payload_len);
    free(final_payload);

    return b64_token;
}

/**
 * @brief Implements Fernet's decryption logic.
 * @param b64_token The URL-safe Base64 encoded Fernet token.
 * @param b64_key The URL-safe Base64 encoded 32-byte key.
 * @return The decrypted plaintext message (must be freed by caller).
 */
char* fernet_decrypt(const char* b64_token, const char* b64_key) {
    // 1. Decode key and split
    size_t raw_key_len;
    unsigned char* raw_key = urlsafe_b64decode(b64_key, &raw_key_len);
    if (raw_key_len != 32) { free(raw_key); return NULL; }
    unsigned char signing_key[16], encryption_key[16];
    memcpy(signing_key, raw_key, 16);
    memcpy(encryption_key, raw_key + 16, 16);
    free(raw_key);

    // 2. Decode the token
    size_t token_len;
    unsigned char* token = urlsafe_b64decode(b64_token, &token_len);
    if (token_len < 1 + 8 + 16 + 32) { free(token); return NULL; } // Basic sanity check

    // 3. Parse the token
    unsigned char version = token[0];
    if (version != 0x80) { free(token); return NULL; }
    // We don't check the timestamp TTL to match the Python example
    unsigned char* iv = token + 1 + 8;
    size_t ciphertext_len = token_len - (1 + 8 + 16 + 32);
    unsigned char* ciphertext = iv + 16;
    unsigned char* provided_hmac = ciphertext + ciphertext_len;

    // 4. Verify HMAC
    size_t data_to_verify_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char calculated_hmac[32];
    HMAC(EVP_sha256(), signing_key, 16, token, data_to_verify_len, calculated_hmac, NULL);

    if (CRYPTO_memcmp(provided_hmac, calculated_hmac, 32) != 0) {
        free(token);
        return NULL; // HMAC verification failed!
    }

    // 5. Decrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    char* plaintext = (char*)malloc(ciphertext_len + 1);
    int len, plaintext_len;

    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    if (EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len) <= 0) {
        // Padding error or other decryption failure
        free(token);
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    plaintext_len += len;
    plaintext[plaintext_len] = '\0';

    EVP_CIPHER_CTX_free(ctx);
    free(token);
    return plaintext;
}


// --- Top-level functions matching the Python script ---

/**
 * @brief Encrypts a message with a password, generating a salt internally.
 * @return The final encrypted token, Base64 encoded.
 */
char* encrypt_message(const char* message, const char* password, int iterations) {
    // 1. Generate salt
    unsigned char salt[16];
    if (RAND_bytes(salt, 16) != 1) handle_openssl_errors();

    // 2. Derive key
    char* key = _derive_key(password, salt, iterations);

    // 3. Use Fernet to encrypt the message
    char* fernet_token = fernet_encrypt(message, key);
    free(key);

    // 4. The Python code decodes the fernet token before concatenating
    size_t fernet_payload_len;
    unsigned char* fernet_payload = urlsafe_b64decode(fernet_token, &fernet_payload_len);
    free(fernet_token);

    // 5. Convert iterations to 4 bytes, big-endian
    uint32_t iter_be = htonl((uint32_t)iterations);

    // 6. Concatenate: salt + iterations + fernet_payload
    size_t final_blob_len = 16 + 4 + fernet_payload_len;
    unsigned char* final_blob = (unsigned char*)malloc(final_blob_len);
    memcpy(final_blob, salt, 16);
    memcpy(final_blob + 16, &iter_be, 4);
    memcpy(final_blob + 16 + 4, fernet_payload, fernet_payload_len);
    free(fernet_payload);

    // 7. Base64 encode the final result
    char* final_token = urlsafe_b64encode(final_blob, final_blob_len);
    free(final_blob);

    return final_token;
}

/**
 * @brief Decrypts a token using a password.
 * @return The decrypted plaintext message.
 */
char* decrypt_token(const char* token, const char* password) {
    // 1. Decode the outer token
    size_t decoded_len;
    unsigned char* decoded = urlsafe_b64decode(token, &decoded_len);
    if (decoded_len < 20) { free(decoded); return NULL; }

    // 2. Extract components
    unsigned char* salt = decoded; // First 16 bytes
    uint32_t iter_be;
    memcpy(&iter_be, decoded + 16, 4); // Next 4 bytes
    int iterations = ntohl(iter_be);
    unsigned char* fernet_payload = decoded + 20;
    size_t fernet_payload_len = decoded_len - 20;

    // 3. Derive the key using the extracted salt and iterations
    char* key = _derive_key(password, salt, iterations);

    // 4. The Python code re-encodes the inner payload to create the Fernet token
    char* fernet_token = urlsafe_b64encode(fernet_payload, fernet_payload_len);
    free(decoded); // We are done with the decoded blob

    // 5. Decrypt using the Fernet token and derived key
    char* plaintext = fernet_decrypt(fernet_token, key);
    
    free(key);
    free(fernet_token);

    return plaintext;
}


// --- Main Function ---

int main() {
    // On Windows, initialize Winsock for htonl/ntohl
    #ifdef _WIN32
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        fprintf(stderr, "WSAStartup failed.\n");
        return 1;
    }
    #endif

    const char* message = "nice123";
    const char* password = "password123";

    printf("Original message: %s\n", message);
    printf("Password: %s\n\n", password);

    // Encrypt
    char* token = encrypt_message(message, password, 100000);
    if (token) {
        printf("Encrypted Token:\n%s\n\n", token);

        // Decrypt
        char* decrypted_message = decrypt_token(token, password);
        if (decrypted_message) {
            printf("Decrypted message: %s\n", decrypted_message);

            // Verification
            if (strcmp(message, decrypted_message) == 0) {
                printf("SUCCESS: Decrypted message matches original.\n");
            } else {
                printf("FAILURE: Decrypted message does not match original.\n");
            }
            free(decrypted_message);
        } else {
            fprintf(stderr, "Decryption failed!\n");
        }
        free(token);
    } else {
        fprintf(stderr, "Encryption failed!\n");
    }

    // Clean up OpenSSL
    EVP_cleanup();
    ERR_free_strings();

    #ifdef _WIN32
    WSACleanup();
    #endif

    return 0;
}