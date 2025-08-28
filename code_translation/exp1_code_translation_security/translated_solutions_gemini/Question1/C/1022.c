#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL Headers
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/err.h>

// Helper function to print OpenSSL errors
void handle_openssl_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// --- Base64 Helper Functions (URL-safe variant) ---

// Calculates the required length for a base64 encoded string
size_t b64_encoded_size(size_t inlen) {
    return ((inlen + 2) / 3) * 4;
}

// Calculates the maximum possible length for a base64 decoded buffer
size_t b64_decoded_size(const char *in) {
    size_t len = strlen(in);
    size_t padding = 0;
    if (len > 1 && in[len - 1] == '=') padding++;
    if (len > 2 && in[len - 2] == '=') padding++;
    return (len / 4) * 3 - padding;
}

// Encodes data to URL-safe Base64
int url_safe_b64_encode(const unsigned char *in, size_t in_len, char **out) {
    size_t out_len = b64_encoded_size(in_len);
    *out = malloc(out_len + 1);
    if (!*out) return -1;

    int written = EVP_EncodeBlock((unsigned char *)*out, in, in_len);
    if (written <= 0) {
        free(*out);
        return -1;
    }

    // Make it URL-safe: replace '+' with '-', '/' with '_'
    for (size_t i = 0; i < written; ++i) {
        if ((*out)[i] == '+') (*out)[i] = '-';
        if ((*out)[i] == '/') (*out)[i] = '_';
    }
    
    // Remove padding '='
    char *p = strchr(*out, '=');
    if (p) *p = '\0';

    return 0;
}

// Decodes data from URL-safe Base64
int url_safe_b64_decode(const char *in, unsigned char **out, size_t *out_len) {
    size_t in_len = strlen(in);
    // The input might be missing padding, so we create a temporary buffer and add it back.
    // A valid base64 string length is a multiple of 4.
    size_t padded_len = in_len + (in_len % 4 == 0 ? 0 : 4 - (in_len % 4));
    char *temp_in = malloc(padded_len + 1);
    if (!temp_in) return -1;

    strcpy(temp_in, in);

    // Make it standard Base64: replace '-' with '+', '_' with '/'
    for (size_t i = 0; i < in_len; ++i) {
        if (temp_in[i] == '-') temp_in[i] = '+';
        if (temp_in[i] == '_') temp_in[i] = '/';
    }

    // Add padding
    for (size_t i = in_len; i < padded_len; ++i) {
        temp_in[i] = '=';
    }
    temp_in[padded_len] = '\0';

    *out_len = b64_decoded_size(temp_in);
    *out = malloc(*out_len);
    if (!*out) {
        free(temp_in);
        return -1;
    }

    int written = EVP_DecodeBlock(*out, (const unsigned char *)temp_in, padded_len);
    free(temp_in);

    if (written < 0) {
        free(*out);
        return -1;
    }
    
    // The actual length might be less than the max calculated due to padding
    *out_len = written;
    return 0;
}


// --- Fernet Implementation ---

/**
 * Generates a 32-byte key suitable for Fernet.
 * The first 16 bytes are the signing key, the last 16 are the encryption key.
 * NOTE: The Python `Fernet.generate_key()` returns this as a base64 string.
 * Here, we work with the raw bytes for efficiency.
 */
void generate_fernet_key(unsigned char *key) {
    if (RAND_bytes(key, 32) != 1) {
        handle_openssl_errors();
    }
}

/**
 * Encrypts a message using the Fernet specification.
 *
 * @param plaintext The message to encrypt.
 * @param key The 32-byte Fernet key (16-byte sign key + 16-byte encrypt key).
 * @param encrypted_b64 A pointer to a char* that will hold the resulting URL-safe base64 token.
 *                      This buffer is allocated by the function and must be freed by the caller.
 * @return 0 on success, -1 on failure.
 */
int fernet_encrypt(const char *plaintext, const unsigned char *key, char **encrypted_b64) {
    const unsigned char *signing_key = key;
    const unsigned char *encryption_key = key + 16;

    // 1. Prepare the data to be encrypted
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) handle_openssl_errors();

    time_t current_time = time(NULL);
    
    size_t plaintext_len = strlen(plaintext);
    int block_size = 16;
    size_t padding_len = block_size - (plaintext_len % block_size);
    size_t padded_len = plaintext_len + padding_len;

    // Buffer for the data to be encrypted by AES
    size_t to_encrypt_len = padded_len;
    unsigned char *to_encrypt = malloc(to_encrypt_len);
    if (!to_encrypt) return -1;

    memcpy(to_encrypt, plaintext, plaintext_len);
    // Apply PKCS7 padding
    for (size_t i = 0; i < padding_len; ++i) {
        to_encrypt[plaintext_len + i] = (unsigned char)padding_len;
    }

    // 2. Encrypt using AES-128-CBC
    unsigned char *ciphertext = malloc(padded_len);
    if (!ciphertext) { free(to_encrypt); return -1; }
    int ciphertext_len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &ciphertext_len, to_encrypt, to_encrypt_len);
    EVP_CIPHER_CTX_free(ctx);
    free(to_encrypt);

    // 3. Prepare the data for HMAC signature
    // Format: Version (1) || Timestamp (8) || IV (16) || Ciphertext (variable)
    size_t hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char *hmac_data = malloc(hmac_data_len);
    if (!hmac_data) { free(ciphertext); return -1; }

    hmac_data[0] = 0x80; // Version byte
    uint64_t timestamp_be = htobe64((uint64_t)current_time); // Big-endian timestamp
    memcpy(hmac_data + 1, &timestamp_be, 8);
    memcpy(hmac_data + 1 + 8, iv, 16);
    memcpy(hmac_data + 1 + 8 + 16, ciphertext, ciphertext_len);
    free(ciphertext);

    // 4. Calculate HMAC-SHA256
    unsigned char hmac_result[32];
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, hmac_result, NULL);

    // 5. Concatenate everything for the final token
    // Format: HMAC_Data || HMAC_Signature
    size_t final_token_len = hmac_data_len + 32;
    unsigned char *final_token = malloc(final_token_len);
    if (!final_token) { free(hmac_data); return -1; }

    memcpy(final_token, hmac_data, hmac_data_len);
    memcpy(final_token + hmac_data_len, hmac_result, 32);
    free(hmac_data);

    // 6. Base64 encode the final token
    int result = url_safe_b64_encode(final_token, final_token_len, encrypted_b64);
    free(final_token);
    
    return result;
}

/**
 * Decrypts a Fernet token.
 *
 * @param encrypted_b64 The URL-safe base64 token to decrypt.
 * @param key The 32-byte Fernet key.
 * @param decrypted A pointer to a char* that will hold the resulting plaintext.
 *                  This buffer is allocated by the function and must be freed by the caller.
 * @return 0 on success, -1 on failure (e.g., bad signature, bad padding).
 */
int fernet_decrypt(const char *encrypted_b64, const unsigned char *key, char **decrypted) {
    const unsigned char *signing_key = key;
    const unsigned char *encryption_key = key + 16;

    // 1. Base64 decode the token
    unsigned char *token_data;
    size_t token_len;
    if (url_safe_b64_decode(encrypted_b64, &token_data, &token_len) != 0) {
        fprintf(stderr, "Error: Base64 decoding failed.\n");
        return -1;
    }

    // Minimum length: 1 (version) + 8 (ts) + 16 (iv) + 16 (1 block) + 32 (hmac)
    if (token_len < 1 + 8 + 16 + 16 + 32) {
        fprintf(stderr, "Error: Token is too short.\n");
        free(token_data);
        return -1;
    }

    // 2. Verify HMAC signature
    size_t hmac_data_len = token_len - 32;
    unsigned char *hmac_data = token_data;
    unsigned char *hmac_signature = token_data + hmac_data_len;

    unsigned char calculated_hmac[32];
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, calculated_hmac, NULL);

    if (CRYPTO_memcmp(hmac_signature, calculated_hmac, 32) != 0) {
        fprintf(stderr, "Error: Invalid signature.\n");
        free(token_data);
        return -1;
    }

    // 3. Extract components from the token
    // We don't check the version (0x80) or timestamp here, but a production
    // system should check the timestamp against a TTL (time-to-live).
    unsigned char *iv = hmac_data + 1 + 8;
    unsigned char *ciphertext = hmac_data + 1 + 8 + 16;
    size_t ciphertext_len = hmac_data_len - (1 + 8 + 16);

    if (ciphertext_len % 16 != 0) {
        fprintf(stderr, "Error: Ciphertext length is not a multiple of block size.\n");
        free(token_data);
        return -1;
    }

    // 4. Decrypt using AES-128-CBC
    unsigned char *plaintext_padded = malloc(ciphertext_len);
    if (!plaintext_padded) { free(token_data); return -1; }
    int decrypted_len;

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    // Disable auto-padding, we will handle it manually
    EVP_CIPHER_CTX_set_padding(ctx, 0); 

    if (EVP_DecryptUpdate(ctx, plaintext_padded, &decrypted_len, ciphertext, ciphertext_len) != 1) {
        handle_openssl_errors(); // Decryption failed
    }
    
    int final_len;
    // Finalize should not write any more data if padding is disabled
    if (EVP_DecryptFinal_ex(ctx, plaintext_padded + decrypted_len, &final_len) != 1) {
        // This can happen with incorrect padding
        fprintf(stderr, "Error: Failed to finalize decryption (likely bad padding).\n");
        free(plaintext_padded);
        free(token_data);
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    decrypted_len += final_len;
    EVP_CIPHER_CTX_free(ctx);
    free(token_data);

    // 5. Remove PKCS7 padding
    if (decrypted_len == 0) {
        fprintf(stderr, "Error: Decrypted data is empty.\n");
        free(plaintext_padded);
        return -1;
    }
    size_t padding_len = plaintext_padded[decrypted_len - 1];
    if (padding_len == 0 || padding_len > decrypted_len) {
        fprintf(stderr, "Error: Invalid padding value.\n");
        free(plaintext_padded);
        return -1;
    }
    
    size_t plaintext_len = decrypted_len - padding_len;
    *decrypted = malloc(plaintext_len + 1);
    if (!*decrypted) { free(plaintext_padded); return -1; }

    memcpy(*decrypted, plaintext_padded, plaintext_len);
    (*decrypted)[plaintext_len] = '\0';
    free(plaintext_padded);

    return 0;
}


int main() {
    // Equivalent to: ex_key = Fernet.generate_key()
    // We use the raw 32 bytes directly.
    unsigned char ex_key[32];
    generate_fernet_key(ex_key);

    const char *message = "Hello World!";
    char *encrypted = NULL;
    char *decrypted = NULL;

    // Equivalent to: encrypted = encrypt("Hello World!", ex_key)
    if (fernet_encrypt(message, ex_key, &encrypted) == 0) {
        printf("Encrypted: %s\n", encrypted);

        // Equivalent to: decrypted = decrypt(encrypted, ex_key)
        if (fernet_decrypt(encrypted, ex_key, &decrypted) == 0) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        } else {
            fprintf(stderr, "Decryption failed!\n");
        }
        free(encrypted);
    } else {
        fprintf(stderr, "Encryption failed!\n");
    }

    return 0;
}