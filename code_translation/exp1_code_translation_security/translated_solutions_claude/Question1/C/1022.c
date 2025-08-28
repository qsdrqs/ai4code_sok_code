#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/kdf.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <time.h>

#define FERNET_KEY_SIZE 32
#define FERNET_IV_SIZE 16
#define FERNET_TIMESTAMP_SIZE 8
#define FERNET_HMAC_SIZE 32
#define FERNET_VERSION 0x80

// Base64 encoding/decoding functions
static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

int base64_encode(const unsigned char *input, int length, char *output) {
    int i = 0, j = 0;
    unsigned char char_array_3[3];
    unsigned char char_array_4[4];
    
    while (length--) {
        char_array_3[i++] = *(input++);
        if (i == 3) {
            char_array_4[0] = (char_array_3[0] & 0xfc) >> 2;
            char_array_4[1] = ((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4);
            char_array_4[2] = ((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6);
            char_array_4[3] = char_array_3[2] & 0x3f;
            
            for (i = 0; i < 4; i++)
                output[j++] = base64_chars[char_array_4[i]];
            i = 0;
        }
    }
    
    if (i) {
        for (int k = i; k < 3; k++)
            char_array_3[k] = '\0';
            
        char_array_4[0] = (char_array_3[0] & 0xfc) >> 2;
        char_array_4[1] = ((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4);
        char_array_4[2] = ((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6);
        char_array_4[3] = char_array_3[2] & 0x3f;
        
        for (int k = 0; k < i + 1; k++)
            output[j++] = base64_chars[char_array_4[k]];
            
        while (i++ < 3)
            output[j++] = '=';
    }
    
    output[j] = '\0';
    return j;
}

int base64_decode(const char *input, unsigned char *output) {
    int in_len = strlen(input);
    int i = 0, j = 0;
    unsigned char char_array_4[4], char_array_3[3];
    
    while (in_len-- && (input[j] != '=')) {
        char c = input[j++];
        int val = 0;
        
        if (c >= 'A' && c <= 'Z') val = c - 'A';
        else if (c >= 'a' && c <= 'z') val = c - 'a' + 26;
        else if (c >= '0' && c <= '9') val = c - '0' + 52;
        else if (c == '+') val = 62;
        else if (c == '/') val = 63;
        else continue;
        
        char_array_4[i++] = val;
        
        if (i == 4) {
            char_array_3[0] = (char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4);
            char_array_3[1] = ((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2);
            char_array_3[2] = ((char_array_4[2] & 0x3) << 6) + char_array_4[3];
            
            for (i = 0; i < 3; i++)
                output[j - 4 + i] = char_array_3[i];
            i = 0;
        }
    }
    
    if (i) {
        for (int k = i; k < 4; k++)
            char_array_4[k] = 0;
            
        char_array_3[0] = (char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4);
        char_array_3[1] = ((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2);
        char_array_3[2] = ((char_array_4[2] & 0x3) << 6) + char_array_4[3];
        
        for (int k = 0; k < i - 1; k++)
            output[j - 4 + k] = char_array_3[k];
    }
    
    return j - 4 + (i > 0 ? i - 1 : 0);
}

// Generate Fernet key
void generate_key(unsigned char *key) {
    RAND_bytes(key, FERNET_KEY_SIZE);
}

// Get current timestamp
uint64_t get_timestamp() {
    return (uint64_t)time(NULL);
}

// Convert timestamp to big-endian bytes
void timestamp_to_bytes(uint64_t timestamp, unsigned char *bytes) {
    for (int i = 7; i >= 0; i--) {
        bytes[7-i] = (timestamp >> (i * 8)) & 0xFF;
    }
}

// Convert big-endian bytes to timestamp
uint64_t bytes_to_timestamp(const unsigned char *bytes) {
    uint64_t timestamp = 0;
    for (int i = 0; i < 8; i++) {
        timestamp = (timestamp << 8) | bytes[i];
    }
    return timestamp;
}

// Derive encryption and signing keys from Fernet key
void derive_keys(const unsigned char *fernet_key, unsigned char *enc_key, unsigned char *sign_key) {
    // First 16 bytes for signing key, last 16 bytes for encryption key
    memcpy(sign_key, fernet_key, 16);
    memcpy(enc_key, fernet_key + 16, 16);
}

// Encrypt function
char* encrypt(const char *inp, const unsigned char *key) {
    unsigned char enc_key[16], sign_key[16];
    derive_keys(key, enc_key, sign_key);
    
    // Generate IV
    unsigned char iv[FERNET_IV_SIZE];
    RAND_bytes(iv, FERNET_IV_SIZE);
    
    // Get timestamp
    uint64_t timestamp = get_timestamp();
    unsigned char timestamp_bytes[FERNET_TIMESTAMP_SIZE];
    timestamp_to_bytes(timestamp, timestamp_bytes);
    
    // Encrypt the message
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    
    int input_len = strlen(inp);
    unsigned char *ciphertext = malloc(input_len + 16); // Extra space for padding
    int len, ciphertext_len;
    
    EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)inp, input_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Create the token: version + timestamp + iv + ciphertext
    int token_len = 1 + FERNET_TIMESTAMP_SIZE + FERNET_IV_SIZE + ciphertext_len;
    unsigned char *token = malloc(token_len);
    
    token[0] = FERNET_VERSION;
    memcpy(token + 1, timestamp_bytes, FERNET_TIMESTAMP_SIZE);
    memcpy(token + 1 + FERNET_TIMESTAMP_SIZE, iv, FERNET_IV_SIZE);
    memcpy(token + 1 + FERNET_TIMESTAMP_SIZE + FERNET_IV_SIZE, ciphertext, ciphertext_len);
    
    // Calculate HMAC
    unsigned char hmac[FERNET_HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), sign_key, 16, token, token_len, hmac, &hmac_len);
    
    // Create final token with HMAC
    unsigned char *final_token = malloc(token_len + FERNET_HMAC_SIZE);
    memcpy(final_token, token, token_len);
    memcpy(final_token + token_len, hmac, FERNET_HMAC_SIZE);
    
    // Base64 encode
    char *encoded = malloc((token_len + FERNET_HMAC_SIZE) * 2);
    base64_encode(final_token, token_len + FERNET_HMAC_SIZE, encoded);
    
    free(ciphertext);
    free(token);
    free(final_token);
    
    return encoded;
}

// Decrypt function
char* decrypt(const char *inp, const unsigned char *key) {
    unsigned char enc_key[16], sign_key[16];
    derive_keys(key, enc_key, sign_key);
    
    // Base64 decode
    unsigned char *decoded = malloc(strlen(inp));
    int decoded_len = base64_decode(inp, decoded);
    
    if (decoded_len < 1 + FERNET_TIMESTAMP_SIZE + FERNET_IV_SIZE + FERNET_HMAC_SIZE) {
        free(decoded);
        return NULL;
    }
    
    // Extract HMAC and verify
    int token_len = decoded_len - FERNET_HMAC_SIZE;
    unsigned char *token = decoded;
    unsigned char *received_hmac = decoded + token_len;
    
    unsigned char calculated_hmac[FERNET_HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), sign_key, 16, token, token_len, calculated_hmac, &hmac_len);
    
    if (memcmp(received_hmac, calculated_hmac, FERNET_HMAC_SIZE) != 0) {
        free(decoded);
        return NULL; // HMAC verification failed
    }
    
    // Extract components
    if (token[0] != FERNET_VERSION) {
        free(decoded);
        return NULL; // Invalid version
    }
    
    unsigned char *timestamp_bytes = token + 1;
    unsigned char *iv = token + 1 + FERNET_TIMESTAMP_SIZE;
    unsigned char *ciphertext = token + 1 + FERNET_TIMESTAMP_SIZE + FERNET_IV_SIZE;
    int ciphertext_len = token_len - 1 - FERNET_TIMESTAMP_SIZE - FERNET_IV_SIZE;
    
    // Decrypt
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    
    unsigned char *plaintext = malloc(ciphertext_len + 16);
    int len, plaintext_len;
    
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);
    plaintext_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    plaintext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Null terminate the result
    char *result = malloc(plaintext_len + 1);
    memcpy(result, plaintext, plaintext_len);
    result[plaintext_len] = '\0';
    
    free(decoded);
    free(plaintext);
    
    return result;
}

int main() {
    // Generate key
    unsigned char ex_key[FERNET_KEY_SIZE];
    generate_key(ex_key);
    
    // Encrypt
    char *encrypted = encrypt("Hello World!", ex_key);
    printf("%s\n", encrypted);
    
    // Decrypt
    char *decrypted = decrypt(encrypted, ex_key);
    printf("%s\n", decrypted);
    
    // Cleanup
    free(encrypted);
    free(decrypted);
    
    return 0;
}