#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/base64.h>
#include <time.h>

#define AES_KEY_SIZE 32
#define IV_SIZE 16
#define HMAC_KEY_SIZE 32
#define HMAC_SIZE 32
#define TIMESTAMP_SIZE 8

typedef struct {
    unsigned char aes_key[AES_KEY_SIZE];
    unsigned char hmac_key[HMAC_KEY_SIZE];
} FernetKey;

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
    
    char *buff = malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    
    BIO_free_all(b64);
    return buff;
}

// Base64 decoding function
unsigned char* base64_decode(const char* input, int* output_length) {
    BIO *b64, *bmem;
    int length = strlen(input);
    unsigned char *buffer = malloc(length);
    
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new_mem_buf(input, length);
    bmem = BIO_push(b64, bmem);
    BIO_set_flags(bmem, BIO_FLAGS_BASE64_NO_NL);
    
    *output_length = BIO_read(bmem, buffer, length);
    BIO_free_all(bmem);
    
    return buffer;
}

// Generate Fernet key
FernetKey* generate_key() {
    FernetKey* key = malloc(sizeof(FernetKey));
    unsigned char raw_key[64];
    
    if (RAND_bytes(raw_key, 64) != 1) {
        free(key);
        return NULL;
    }
    
    memcpy(key->aes_key, raw_key, AES_KEY_SIZE);
    memcpy(key->hmac_key, raw_key + AES_KEY_SIZE, HMAC_KEY_SIZE);
    
    return key;
}

// Create key from base64 string
FernetKey* key_from_string(const char* key_str) {
    int decoded_len;
    unsigned char* decoded = base64_decode(key_str, &decoded_len);
    
    if (decoded_len != 64) {
        free(decoded);
        return NULL;
    }
    
    FernetKey* key = malloc(sizeof(FernetKey));
    memcpy(key->aes_key, decoded, AES_KEY_SIZE);
    memcpy(key->hmac_key, decoded + AES_KEY_SIZE, HMAC_KEY_SIZE);
    
    free(decoded);
    return key;
}

// Convert key to base64 string
char* key_to_string(FernetKey* key) {
    unsigned char raw_key[64];
    memcpy(raw_key, key->aes_key, AES_KEY_SIZE);
    memcpy(raw_key + AES_KEY_SIZE, key->hmac_key, HMAC_KEY_SIZE);
    
    return base64_encode(raw_key, 64);
}

// Encrypt function
char* encrypt_msg(const unsigned char* msg, int msg_len, FernetKey* key) {
    EVP_CIPHER_CTX *ctx;
    unsigned char iv[IV_SIZE];
    unsigned char timestamp[TIMESTAMP_SIZE];
    unsigned char *ciphertext;
    unsigned char *token;
    unsigned char hmac_result[HMAC_SIZE];
    unsigned int hmac_len;
    int len, ciphertext_len;
    
    // Generate random IV
    if (RAND_bytes(iv, IV_SIZE) != 1) return NULL;
    
    // Create timestamp (8 bytes, big endian)
    time_t current_time = time(NULL);
    for (int i = 7; i >= 0; i--) {
        timestamp[i] = current_time & 0xFF;
        current_time >>= 8;
    }
    
    // Allocate memory for ciphertext
    ciphertext = malloc(msg_len + AES_BLOCK_SIZE);
    
    // Create and initialize the context
    ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key->aes_key, iv);
    
    // Encrypt the message
    EVP_EncryptUpdate(ctx, ciphertext, &len, msg, msg_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Create token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
    int token_len = 1 + TIMESTAMP_SIZE + IV_SIZE + ciphertext_len + HMAC_SIZE;
    token = malloc(token_len);
    
    token[0] = 0x80; // Version byte
    memcpy(token + 1, timestamp, TIMESTAMP_SIZE);
    memcpy(token + 1 + TIMESTAMP_SIZE, iv, IV_SIZE);
    memcpy(token + 1 + TIMESTAMP_SIZE + IV_SIZE, ciphertext, ciphertext_len);
    
    // Calculate HMAC
    HMAC(EVP_sha256(), key->hmac_key, HMAC_KEY_SIZE, 
         token, token_len - HMAC_SIZE, hmac_result, &hmac_len);
    
    memcpy(token + token_len - HMAC_SIZE, hmac_result, HMAC_SIZE);
    
    // Encode to base64
    char* result = base64_encode(token, token_len);
    
    free(ciphertext);
    free(token);
    
    return result;
}

// Decrypt function
unsigned char* decrypt_msg(const char* encrypted_msg, FernetKey* key, int* output_len) {
    int token_len;
    unsigned char* token = base64_decode(encrypted_msg, &token_len);
    
    if (token_len < 1 + TIMESTAMP_SIZE + IV_SIZE + HMAC_SIZE) {
        free(token);
        return NULL;
    }
    
    // Verify HMAC
    unsigned char expected_hmac[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), key->hmac_key, HMAC_KEY_SIZE, 
         token, token_len - HMAC_SIZE, expected_hmac, &hmac_len);
    
    if (memcmp(expected_hmac, token + token_len - HMAC_SIZE, HMAC_SIZE) != 0) {
        free(token);
        return NULL;
    }
    
    // Extract components
    unsigned char* iv = token + 1 + TIMESTAMP_SIZE;
    unsigned char* ciphertext = token + 1 + TIMESTAMP_SIZE + IV_SIZE;
    int ciphertext_len = token_len - 1 - TIMESTAMP_SIZE - IV_SIZE - HMAC_SIZE;
    
    // Decrypt
    EVP_CIPHER_CTX *ctx;
    unsigned char *plaintext = malloc(ciphertext_len + AES_BLOCK_SIZE);
    int len, plaintext_len;
    
    ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key->aes_key, iv);
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    plaintext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    free(token);
    
    *output_len = plaintext_len;
    return plaintext;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    // Generate key
    FernetKey* key = generate_key();
    if (!key) {
        printf("Failed to generate key\n");
        return 1;
    }
    
    // Message to encrypt
    const unsigned char* msg = (unsigned char*)"a much longer message with punctuation!";
    int msg_len = strlen((char*)msg);
    
    // Encrypt
    char* encrypted = encrypt_msg(msg, msg_len, key);
    if (!encrypted) {
        printf("Encryption failed\n");
        free(key);
        return 1;
    }
    
    // Decrypt
    int decrypted_len;
    unsigned char* decrypted = decrypt_msg(encrypted, key, &decrypted_len);
    if (!decrypted) {
        printf("Decryption failed\n");
        free(encrypted);
        free(key);
        return 1;
    }
    
    // Print results
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %.*s\n", decrypted_len, decrypted);
    
    // Cleanup
    free(encrypted);
    free(decrypted);
    free(key);
    
    EVP_cleanup();
    
    return 0;
}