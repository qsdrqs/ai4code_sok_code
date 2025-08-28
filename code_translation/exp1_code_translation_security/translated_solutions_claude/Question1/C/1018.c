#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <openssl/aes.h>
#include <openssl/err.h>
#include <openssl/buffer.h>
#include <time.h>

#define AES_KEY_SIZE 32
#define HMAC_KEY_SIZE 32
#define IV_SIZE 16
#define HMAC_SIZE 32
#define TIMESTAMP_SIZE 8

// Structure to hold encrypted data
typedef struct {
    unsigned char *data;
    size_t length;
} EncryptedData;

// Base64 encoding function
char* base64_encode(const unsigned char* input, int length) {
    BIO *bmem, *b64;
    BUF_MEM *bptr;
    
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(b64, input, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);
    
    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    
    BIO_free_all(b64);
    return buff;
}

// Base64 decoding function
unsigned char* base64_decode(const char* input, int *output_length) {
    BIO *b64, *bmem;
    int length = strlen(input);
    unsigned char *buffer = (unsigned char *)malloc(length);
    
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new_mem_buf(input, length);
    bmem = BIO_push(b64, bmem);
    BIO_set_flags(bmem, BIO_FLAGS_BASE64_NO_NL);
    
    *output_length = BIO_read(bmem, buffer, length);
    BIO_free_all(bmem);
    
    return buffer;
}

// Derive encryption and HMAC keys from Fernet key
void derive_keys(const unsigned char* fernet_key, unsigned char* enc_key, unsigned char* hmac_key) {
    // Fernet key is 32 bytes: first 16 for signing, last 16 for encryption
    // We'll use SHA256 to derive proper length keys
    unsigned char temp[32];
    int decoded_len;
    unsigned char* decoded_key = base64_decode((const char*)fernet_key, &decoded_len);
    
    // First 16 bytes for HMAC key derivation
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(mdctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(mdctx, decoded_key, 16);
    EVP_DigestFinal_ex(mdctx, hmac_key, NULL);
    
    // Last 16 bytes for encryption key derivation
    EVP_DigestInit_ex(mdctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(mdctx, decoded_key + 16, 16);
    EVP_DigestFinal_ex(mdctx, enc_key, NULL);
    
    EVP_MD_CTX_free(mdctx);
    free(decoded_key);
}

// Get current timestamp
uint64_t get_timestamp() {
    return (uint64_t)time(NULL);
}

// Encrypt function
char* encrypt(const char* input, const unsigned char* key) {
    unsigned char enc_key[AES_KEY_SIZE];
    unsigned char hmac_key[HMAC_KEY_SIZE];
    unsigned char iv[IV_SIZE];
    unsigned char *ciphertext;
    unsigned char *token;
    int ciphertext_len, token_len;
    uint64_t timestamp;
    
    // Derive keys
    derive_keys(key, enc_key, hmac_key);
    
    // Generate random IV
    if (RAND_bytes(iv, IV_SIZE) != 1) {
        return NULL;
    }
    
    // Get timestamp
    timestamp = get_timestamp();
    
    // Encrypt the input
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, enc_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    int input_len = strlen(input);
    ciphertext = malloc(input_len + AES_BLOCK_SIZE);
    int len;
    
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)input, input_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        return NULL;
    }
    ciphertext_len = len;
    
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        return NULL;
    }
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    
    // Create token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
    token_len = 1 + TIMESTAMP_SIZE + IV_SIZE + ciphertext_len + HMAC_SIZE;
    token = malloc(token_len);
    
    int pos = 0;
    token[pos++] = 0x80; // Version byte
    
    // Add timestamp (big-endian)
    for (int i = 7; i >= 0; i--) {
        token[pos++] = (timestamp >> (i * 8)) & 0xFF;
    }
    
    // Add IV
    memcpy(token + pos, iv, IV_SIZE);
    pos += IV_SIZE;
    
    // Add ciphertext
    memcpy(token + pos, ciphertext, ciphertext_len);
    pos += ciphertext_len;
    
    // Calculate HMAC
    unsigned char hmac[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), hmac_key, HMAC_KEY_SIZE, token, pos, hmac, &hmac_len);
    
    // Add HMAC to token
    memcpy(token + pos, hmac, HMAC_SIZE);
    
    // Base64 encode the token
    char* result = base64_encode(token, token_len);
    
    free(ciphertext);
    free(token);
    
    return result;
}

// Decrypt function
char* decrypt(const char* input, const unsigned char* key) {
    unsigned char enc_key[AES_KEY_SIZE];
    unsigned char hmac_key[HMAC_KEY_SIZE];
    unsigned char *token;
    unsigned char *plaintext;
    int token_len, plaintext_len;
    
    // Derive keys
    derive_keys(key, enc_key, hmac_key);
    
    // Base64 decode the input
    token = base64_decode(input, &token_len);
    if (!token) return NULL;
    
    // Verify minimum token length
    if (token_len < 1 + TIMESTAMP_SIZE + IV_SIZE + HMAC_SIZE) {
        free(token);
        return NULL;
    }
    
    // Verify HMAC
    unsigned char expected_hmac[HMAC_SIZE];
    unsigned int hmac_len;
    int data_len = token_len - HMAC_SIZE;
    
    HMAC(EVP_sha256(), hmac_key, HMAC_KEY_SIZE, token, data_len, expected_hmac, &hmac_len);
    
    if (memcmp(expected_hmac, token + data_len, HMAC_SIZE) != 0) {
        free(token);
        return NULL; // HMAC verification failed
    }
    
    // Extract components
    int pos = 1; // Skip version byte
    pos += TIMESTAMP_SIZE; // Skip timestamp
    
    unsigned char *iv = token + pos;
    pos += IV_SIZE;
    
    unsigned char *ciphertext = token + pos;
    int ciphertext_len = data_len - pos;
    
    // Decrypt
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(token);
        return NULL;
    }
    
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, enc_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(token);
        return NULL;
    }
    
    plaintext = malloc(ciphertext_len + AES_BLOCK_SIZE);
    int len;
    
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
    
    // Null-terminate the result
    plaintext = realloc(plaintext, plaintext_len + 1);
    plaintext[plaintext_len] = '\0';
    
    return (char*)plaintext;
}

// Example usage
int main() {
    // Example key (base64 encoded 32-byte key)
    unsigned char key[] = "your-base64-encoded-fernet-key-here=";
    
    char* message = "Hello, World!";
    
    // Encrypt
    char* encrypted = encrypt(message, key);
    if (encrypted) {
        printf("Encrypted: %s\n", encrypted);
        
        // Decrypt
        char* decrypted = decrypt(encrypted, key);
        if (decrypted) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        free(encrypted);
    }
    
    return 0;
}