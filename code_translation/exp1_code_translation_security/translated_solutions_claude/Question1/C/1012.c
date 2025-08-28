#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/kdf.h>
#include <openssl/hmac.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <time.h>

// Fernet uses AES-128-CBC with HMAC-SHA256
#define FERNET_KEY_SIZE 32
#define AES_KEY_SIZE 16
#define HMAC_KEY_SIZE 16
#define IV_SIZE 16
#define TIMESTAMP_SIZE 8
#define HMAC_SIZE 32

// Global key (equivalent to the global 'key' in Python)
unsigned char fernet_key[FERNET_KEY_SIZE];

// Base64 encoding function
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
    
    char* result = malloc(bufferPtr->length + 1);
    memcpy(result, bufferPtr->data, bufferPtr->length);
    result[bufferPtr->length] = '\0';
    
    BIO_free_all(bio);
    return result;
}

// Base64 decoding function
unsigned char* base64_decode(const char* input, int* output_length) {
    BIO *bio, *b64;
    int decode_len = strlen(input);
    unsigned char* buffer = malloc(decode_len);
    
    bio = BIO_new_mem_buf(input, -1);
    b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);
    
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    *output_length = BIO_read(bio, buffer, decode_len);
    
    BIO_free_all(bio);
    return buffer;
}

// Generate Fernet key (equivalent to Fernet.generate_key())
void generate_fernet_key() {
    if (RAND_bytes(fernet_key, FERNET_KEY_SIZE) != 1) {
        fprintf(stderr, "Error generating random key\n");
        exit(1);
    }
}

// Get current timestamp
uint64_t get_timestamp() {
    return (uint64_t)time(NULL);
}

// Convert timestamp to big-endian bytes
void timestamp_to_bytes(uint64_t timestamp, unsigned char* bytes) {
    for (int i = 7; i >= 0; i--) {
        bytes[7-i] = (timestamp >> (i * 8)) & 0xFF;
    }
}

// Convert big-endian bytes to timestamp
uint64_t bytes_to_timestamp(const unsigned char* bytes) {
    uint64_t timestamp = 0;
    for (int i = 0; i < 8; i++) {
        timestamp = (timestamp << 8) | bytes[i];
    }
    return timestamp;
}

// Encrypt text (equivalent to encrypt_text function)
char* encrypt_text(const char* plain_text) {
    int plain_len = strlen(plain_text);
    
    // Generate IV
    unsigned char iv[IV_SIZE];
    if (RAND_bytes(iv, IV_SIZE) != 1) {
        fprintf(stderr, "Error generating IV\n");
        return NULL;
    }
    
    // Get timestamp
    uint64_t timestamp = get_timestamp();
    unsigned char timestamp_bytes[TIMESTAMP_SIZE];
    timestamp_to_bytes(timestamp, timestamp_bytes);
    
    // Encrypt with AES-128-CBC
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    // Use first 16 bytes of fernet_key for AES encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fernet_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Calculate padded length
    int padded_len = ((plain_len / 16) + 1) * 16;
    unsigned char* encrypted = malloc(padded_len);
    int len, ciphertext_len;
    
    if (EVP_EncryptUpdate(ctx, encrypted, &len, (unsigned char*)plain_text, plain_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(encrypted);
        return NULL;
    }
    ciphertext_len = len;
    
    if (EVP_EncryptFinal_ex(ctx, encrypted + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(encrypted);
        return NULL;
    }
    ciphertext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Create token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
    int token_len = 1 + TIMESTAMP_SIZE + IV_SIZE + ciphertext_len + HMAC_SIZE;
    unsigned char* token = malloc(token_len);
    
    int pos = 0;
    token[pos++] = 0x80; // Fernet version
    memcpy(token + pos, timestamp_bytes, TIMESTAMP_SIZE);
    pos += TIMESTAMP_SIZE;
    memcpy(token + pos, iv, IV_SIZE);
    pos += IV_SIZE;
    memcpy(token + pos, encrypted, ciphertext_len);
    pos += ciphertext_len;
    
    // Calculate HMAC using last 16 bytes of fernet_key
    unsigned int hmac_len;
    HMAC(EVP_sha256(), fernet_key + 16, HMAC_KEY_SIZE, token, pos, token + pos, &hmac_len);
    
    free(encrypted);
    
    // Base64 encode the token
    char* result = base64_encode(token, token_len);
    free(token);
    
    return result;
}

// Decrypt text (equivalent to decrypt_text function, with bug fix)
char* decrypt_text(const char* encrypted_text) {
    int token_len;
    unsigned char* token = base64_decode(encrypted_text, &token_len);
    
    if (token_len < 1 + TIMESTAMP_SIZE + IV_SIZE + 16 + HMAC_SIZE) {
        free(token);
        return NULL;
    }
    
    // Verify HMAC
    unsigned char expected_hmac[HMAC_SIZE];
    unsigned int hmac_len;
    int hmac_pos = token_len - HMAC_SIZE;
    
    HMAC(EVP_sha256(), fernet_key + 16, HMAC_KEY_SIZE, token, hmac_pos, expected_hmac, &hmac_len);
    
    if (memcmp(token + hmac_pos, expected_hmac, HMAC_SIZE) != 0) {
        free(token);
        return NULL;
    }
    
    // Extract components
    if (token[0] != 0x80) { // Check version
        free(token);
        return NULL;
    }
    
    unsigned char* timestamp_bytes = token + 1;
    unsigned char* iv = token + 1 + TIMESTAMP_SIZE;
    unsigned char* ciphertext = token + 1 + TIMESTAMP_SIZE + IV_SIZE;
    int ciphertext_len = hmac_pos - (1 + TIMESTAMP_SIZE + IV_SIZE);
    
    // Decrypt
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(token);
        return NULL;
    }
    
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fernet_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(token);
        return NULL;
    }
    
    unsigned char* plaintext = malloc(ciphertext_len + 16);
    int len, plaintext_len;
    
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(token);
        free(plaintext);
        return NULL;
    }
    plaintext_len = len;
    
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(token);
        free(plaintext);
        return NULL;
    }
    plaintext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    free(token);
    
    // Null terminate the result
    char* result = malloc(plaintext_len + 1);
    memcpy(result, plaintext, plaintext_len);
    result[plaintext_len] = '\0';
    
    free(plaintext);
    return result;
}

// Example usage
int main() {
    // Generate key (equivalent to key = Fernet.generate_key())
    generate_fernet_key();
    
    // Test encryption and decryption
    const char* original_text = "Hello, World!";
    
    char* encrypted = encrypt_text(original_text);
    if (encrypted) {
        printf("Encrypted: %s\n", encrypted);
        
        char* decrypted = decrypt_text(encrypted);
        if (decrypted) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    
    return 0;
}