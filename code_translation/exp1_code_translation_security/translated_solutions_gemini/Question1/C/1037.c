#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdint.h>

// OpenSSL Headers
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/err.h>

// --- Helper Functions for Base64 URL Safe Encoding/Decoding ---

// Calculates the decoded length of a Base64 string.
size_t b64_decoded_size(const char *in) {
    size_t len = strlen(in);
    size_t padding = 0;
    if (in[len-1] == '=' && in[len-2] == '=') padding = 2;
    else if (in[len-1] == '=') padding = 1;
    return (len * 3) / 4 - padding;
}

// Decodes a Base64 URL Safe string.
// The caller is responsible for freeing the returned buffer.
unsigned char* b64_url_decode(const char* input, size_t* out_len) {
    size_t input_len = strlen(input);
    // Create a temporary buffer to replace URL-safe chars with standard Base64 chars
    char* temp_b64 = malloc(input_len + 4); // +4 for padding
    if (!temp_b64) return NULL;
    strcpy(temp_b64, input);

    for (size_t i = 0; i < input_len; i++) {
        if (temp_b64[i] == '-') temp_b64[i] = '+';
        if (temp_b64[i] == '_') temp_b64[i] = '/';
    }

    // Add padding if necessary
    int mod_len = input_len % 4;
    if (mod_len == 2) strcat(temp_b64, "==");
    else if (mod_len == 3) strcat(temp_b64, "=");
    
    size_t decoded_len = b64_decoded_size(temp_b64);
    unsigned char* buffer = malloc(decoded_len);
    if (!buffer) {
        free(temp_b64);
        return NULL;
    }

    EVP_ENCODE_CTX *ctx = EVP_ENCODE_CTX_new();
    int len;
    EVP_DecodeInit(ctx);
    EVP_DecodeUpdate(ctx, buffer, &len, (unsigned char*)temp_b64, strlen(temp_b64));
    *out_len = len;
    EVP_DecodeFinal(ctx, buffer + len, &len);
    *out_len += len;
    
    EVP_ENCODE_CTX_free(ctx);
    free(temp_b64);
    return buffer;
}

// Encodes data into a Base64 URL Safe string.
// The caller is responsible for freeing the returned buffer.
char* b64_url_encode(const unsigned char* buffer, size_t length) {
    EVP_ENCODE_CTX *ctx = EVP_ENCODE_CTX_new();
    int b64_len = 4 * ((length + 2) / 3);
    char* b64_text = malloc(b64_len + 1);
    if (!b64_text) return NULL;

    int out_len;
    EVP_EncodeInit(ctx);
    EVP_EncodeUpdate(ctx, (unsigned char*)b64_text, &out_len, buffer, length);
    EVP_EncodeFinal(ctx, (unsigned char*)b64_text + out_len, &out_len);
    
    // Convert to URL-safe and remove padding
    for (int i = 0; b64_text[i] != '\0'; i++) {
        if (b64_text[i] == '+') b64_text[i] = '-';
        if (b64_text[i] == '/') b64_text[i] = '_';
        if (b64_text[i] == '=') {
            b64_text[i] = '\0'; // Truncate at padding
            break;
        }
    }
    
    EVP_ENCODE_CTX_free(ctx);
    return b64_text;
}


// --- Fernet Core Functions ---

// Generates a URL-safe Base64-encoded 32-byte key.
// The caller is responsible for freeing the returned string.
char* generate_key() {
    unsigned char key_bytes[32];
    if (RAND_bytes(key_bytes, sizeof(key_bytes)) != 1) {
        fprintf(stderr, "Error generating random key bytes.\n");
        return NULL;
    }
    return b64_url_encode(key_bytes, sizeof(key_bytes));
}

// Encrypts data using the Fernet specification.
// Returns a URL-safe Base64-encoded token.
// The caller is responsible for freeing the returned string.
char* encrypt_fernet(const unsigned char* input, size_t input_len, const char* b64_key) {
    // 1. Decode the key and split it
    size_t key_len;
    unsigned char* key = b64_url_decode(b64_key, &key_len);
    if (!key || key_len != 32) {
        fprintf(stderr, "Invalid Fernet key.\n");
        free(key);
        return NULL;
    }
    unsigned char* signing_key = key;
    unsigned char* encryption_key = key + 16;

    // 2. Prepare metadata: version, timestamp, IV
    unsigned char version = 0x80;
    uint64_t timestamp = htobe64((uint64_t)time(NULL)); // Host to Big-Endian
    unsigned char iv[16];
    if (RAND_bytes(iv, sizeof(iv)) != 1) {
        fprintf(stderr, "Error generating IV.\n");
        free(key);
        return NULL;
    }

    // 3. Encrypt the plaintext using AES-128-CBC
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int ciphertext_len;
    int len;
    // AES-128-CBC requires padding, so buffer must be larger
    size_t max_ciphertext_len = input_len + EVP_MAX_BLOCK_LENGTH;
    unsigned char* ciphertext = malloc(max_ciphertext_len);
    if (!ciphertext) {
        free(key);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, input, input_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Concatenate data for HMAC
    size_t pre_hmac_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char* pre_hmac_data = malloc(pre_hmac_len);
    if (!pre_hmac_data) {
        free(key);
        free(ciphertext);
        return NULL;
    }
    
    size_t offset = 0;
    memcpy(pre_hmac_data + offset, &version, 1); offset += 1;
    memcpy(pre_hmac_data + offset, &timestamp, 8); offset += 8;
    memcpy(pre_hmac_data + offset, iv, 16); offset += 16;
    memcpy(pre_hmac_data + offset, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, pre_hmac_data, pre_hmac_len, hmac_result, &hmac_len);

    // 6. Concatenate everything for the final token
    size_t final_token_len = pre_hmac_len + hmac_len;
    unsigned char* final_token_data = malloc(final_token_len);
    if (!final_token_data) {
        free(key);
        free(ciphertext);
        free(pre_hmac_data);
        return NULL;
    }
    memcpy(final_token_data, pre_hmac_data, pre_hmac_len);
    memcpy(final_token_data + pre_hmac_len, hmac_result, hmac_len);

    // 7. Base64 URL encode the final result
    char* b64_token = b64_url_encode(final_token_data, final_token_len);

    // 8. Cleanup
    free(key);
    free(ciphertext);
    free(pre_hmac_data);
    free(final_token_data);

    return b64_token;
}

// Decrypts a Fernet token.
// Returns the original plaintext.
// The caller is responsible for freeing the returned buffer.
unsigned char* decrypt_fernet(const char* b64_token, const char* b64_key, size_t* decrypted_len) {
    // 1. Decode key and token
    size_t key_len;
    unsigned char* key = b64_url_decode(b64_key, &key_len);
    if (!key || key_len != 32) {
        fprintf(stderr, "Invalid Fernet key.\n");
        free(key);
        return NULL;
    }
    unsigned char* signing_key = key;
    unsigned char* encryption_key = key + 16;

    size_t token_data_len;
    unsigned char* token_data = b64_url_decode(b64_token, &token_data_len);
    if (!token_data) {
        fprintf(stderr, "Invalid token encoding.\n");
        free(key);
        return NULL;
    }

    // 2. Verify HMAC signature
    if (token_data_len < 1 + 8 + 16 + 32) { // Min length check
        fprintf(stderr, "Token too short to be valid.\n");
        goto error_exit;
    }
    size_t hmac_offset = token_data_len - 32;
    unsigned char* received_hmac = token_data + hmac_offset;
    unsigned char calculated_hmac[EVP_MAX_MD_SIZE];
    unsigned int calculated_hmac_len;

    HMAC(EVP_sha256(), signing_key, 16, token_data, hmac_offset, calculated_hmac, &calculated_hmac_len);

    if (CRYPTO_memcmp(received_hmac, calculated_hmac, 32) != 0) {
        fprintf(stderr, "Invalid signature.\n");
        goto error_exit;
    }

    // 3. Extract IV and ciphertext
    unsigned char* iv = token_data + 1 + 8;
    unsigned char* ciphertext = token_data + 1 + 8 + 16;
    size_t ciphertext_len = hmac_offset - (1 + 8 + 16);

    // 4. Decrypt the ciphertext
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char* decrypted_buffer = malloc(ciphertext_len); // Decrypted text is <= ciphertext
    if (!decrypted_buffer) {
        EVP_CIPHER_CTX_free(ctx);
        goto error_exit;
    }
    int len;

    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, decrypted_buffer, &len, ciphertext, ciphertext_len);
    *decrypted_len = len;
    if (EVP_DecryptFinal_ex(ctx, decrypted_buffer + len, &len) <= 0) {
        fprintf(stderr, "Decryption failed. Bad padding or ciphertext.\n");
        ERR_print_errors_fp(stderr);
        free(decrypted_buffer);
        EVP_CIPHER_CTX_free(ctx);
        goto error_exit;
    }
    *decrypted_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 5. Cleanup and return
    free(key);
    free(token_data);
    return decrypted_buffer;

error_exit:
    free(key);
    free(token_data);
    return NULL;
}

// --- Main function to replicate Python script ---

int main() {
    // In Python: key = Fernet.generate_key()
    char* key = generate_key();
    if (!key) {
        return 1;
    }
    printf("Generated Key: %s\n\n", key);

    // In Python: input = b'super secret'
    const char* input_str = "super secret";
    printf("Original Input: %s\n", input_str);

    // In Python: encryptedInput = encrypt(input, key)
    char* encryptedInput = encrypt_fernet((const unsigned char*)input_str, strlen(input_str), key);
    if (!encryptedInput) {
        free(key);
        return 1;
    }
    printf("Encrypted Token: %s\n", encryptedInput);

    // In Python: decryptedInput = decrypt(encryptedInput, key)
    size_t decrypted_len;
    unsigned char* decryptedInput = decrypt_fernet(encryptedInput, key, &decrypted_len);
    if (!decryptedInput) {
        free(key);
        free(encryptedInput);
        return 1;
    }
    
    // Null-terminate the decrypted string for printing
    char* decrypted_str = malloc(decrypted_len + 1);
    memcpy(decrypted_str, decryptedInput, decrypted_len);
    decrypted_str[decrypted_len] = '\0';

    printf("Decrypted Output: %s\n\n", decrypted_str);

    // Compare original and decrypted for verification
    if (strcmp(input_str, decrypted_str) == 0) {
        printf("Success: Original and decrypted messages match.\n");
    } else {
        printf("Failure: Messages do not match!\n");
    }

    // Clean up all allocated memory
    free(key);
    free(encryptedInput);
    free(decryptedInput);
    free(decrypted_str);

    return 0;
}