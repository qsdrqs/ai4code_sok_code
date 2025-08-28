#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdint.h>

// Crypto dependencies (would normally be linked as libraries)
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/aes.h>

// Base64 encoding/decoding functions
static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

char* base64_encode(const unsigned char* input, int length) {
    int encoded_length = 4 * ((length + 2) / 3);
    char* encoded = malloc(encoded_length + 1);
    
    int i, j;
    for (i = 0, j = 0; i < length;) {
        uint32_t a = i < length ? input[i++] : 0;
        uint32_t b = i < length ? input[i++] : 0;
        uint32_t c = i < length ? input[i++] : 0;
        uint32_t triple = (a << 16) + (b << 8) + c;
        
        encoded[j++] = base64_chars[(triple >> 18) & 63];
        encoded[j++] = base64_chars[(triple >> 12) & 63];
        encoded[j++] = base64_chars[(triple >> 6) & 63];
        encoded[j++] = base64_chars[triple & 63];
    }
    
    for (i = 0; i < (3 - length % 3) % 3; i++)
        encoded[encoded_length - 1 - i] = '=';
    
    encoded[encoded_length] = '\0';
    return encoded;
}

int base64_decode_char(char c) {
    if (c >= 'A' && c <= 'Z') return c - 'A';
    if (c >= 'a' && c <= 'z') return c - 'a' + 26;
    if (c >= '0' && c <= '9') return c - '0' + 52;
    if (c == '+') return 62;
    if (c == '/') return 63;
    return -1;
}

unsigned char* base64_decode(const char* input, int* output_length) {
    int input_length = strlen(input);
    *output_length = input_length / 4 * 3;
    
    if (input[input_length - 1] == '=') (*output_length)--;
    if (input[input_length - 2] == '=') (*output_length)--;
    
    unsigned char* decoded = malloc(*output_length);
    
    for (int i = 0, j = 0; i < input_length;) {
        uint32_t a = i < input_length ? base64_decode_char(input[i++]) : 0;
        uint32_t b = i < input_length ? base64_decode_char(input[i++]) : 0;
        uint32_t c = i < input_length ? base64_decode_char(input[i++]) : 0;
        uint32_t d = i < input_length ? base64_decode_char(input[i++]) : 0;
        
        uint32_t triple = (a << 18) + (b << 12) + (c << 6) + d;
        
        if (j < *output_length) decoded[j++] = (triple >> 16) & 255;
        if (j < *output_length) decoded[j++] = (triple >> 8) & 255;
        if (j < *output_length) decoded[j++] = triple & 255;
    }
    
    return decoded;
}

// Fernet key structure
typedef struct {
    unsigned char signing_key[16];
    unsigned char encryption_key[16];
} FernetKey;

// Generate Fernet key
char* generate_key() {
    unsigned char key_bytes[32];
    RAND_bytes(key_bytes, 32);
    return base64_encode(key_bytes, 32);
}

// Parse base64 key into FernetKey structure
FernetKey* parse_key(const char* key_b64) {
    int key_len;
    unsigned char* key_bytes = base64_decode(key_b64, &key_len);
    
    if (key_len != 32) {
        free(key_bytes);
        return NULL;
    }
    
    FernetKey* fkey = malloc(sizeof(FernetKey));
    memcpy(fkey->signing_key, key_bytes, 16);
    memcpy(fkey->encryption_key, key_bytes + 16, 16);
    
    free(key_bytes);
    return fkey;
}

// Encrypt function
char* encrypt_data(const unsigned char* input, int input_len, const char* key_b64) {
    FernetKey* fkey = parse_key(key_b64);
    if (!fkey) return NULL;
    
    // Generate IV
    unsigned char iv[16];
    RAND_bytes(iv, 16);
    
    // Get current timestamp
    uint64_t timestamp = (uint64_t)time(NULL);
    
    // Encrypt data
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fkey->encryption_key, iv);
    
    int cipher_len = input_len + AES_BLOCK_SIZE;
    unsigned char* ciphertext = malloc(cipher_len);
    int len;
    
    EVP_EncryptUpdate(ctx, ciphertext, &len, input, input_len);
    cipher_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    cipher_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Create token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
    int token_len = 1 + 8 + 16 + cipher_len + 32;
    unsigned char* token = malloc(token_len);
    
    token[0] = 0x80; // Version
    
    // Timestamp (big-endian)
    for (int i = 0; i < 8; i++) {
        token[1 + i] = (timestamp >> (56 - i * 8)) & 0xFF;
    }
    
    memcpy(token + 9, iv, 16);
    memcpy(token + 25, ciphertext, cipher_len);
    
    // Calculate HMAC
    unsigned int hmac_len;
    HMAC(EVP_sha256(), fkey->signing_key, 16, token, token_len - 32, token + token_len - 32, &hmac_len);
    
    char* result = base64_encode(token, token_len);
    
    free(fkey);
    free(ciphertext);
    free(token);
    
    return result;
}

// Decrypt function
unsigned char* decrypt_data(const char* token_b64, const char* key_b64, int* output_len) {
    FernetKey* fkey = parse_key(key_b64);
    if (!fkey) return NULL;
    
    int token_len;
    unsigned char* token = base64_decode(token_b64, &token_len);
    
    if (token_len < 57 || token[0] != 0x80) { // Minimum size check and version check
        free(fkey);
        free(token);
        return NULL;
    }
    
    // Verify HMAC
    unsigned char expected_hmac[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), fkey->signing_key, 16, token, token_len - 32, expected_hmac, &hmac_len);
    
    if (memcmp(expected_hmac, token + token_len - 32, 32) != 0) {
        free(fkey);
        free(token);
        return NULL;
    }
    
    // Extract components
    unsigned char* iv = token + 9;
    unsigned char* ciphertext = token + 25;
    int cipher_len = token_len - 57;
    
    // Decrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fkey->encryption_key, iv);
    
    unsigned char* plaintext = malloc(cipher_len);
    int len;
    
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, cipher_len);
    *output_len = len;
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    *output_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    free(fkey);
    free(token);
    
    return plaintext;
}

int main() {
    // Generate key
    char* key = generate_key();
    
    // Input data
    const unsigned char* input = (const unsigned char*)"super secret";
    int input_len = strlen((const char*)input);
    
    // Encrypt
    char* encrypted_input = encrypt_data(input, input_len, key);
    
    // Decrypt
    int decrypted_len;
    unsigned char* decrypted_input = decrypt_data(encrypted_input, key, &decrypted_len);
    
    // Print results
    printf("Original: %s\n", input);
    printf("Decrypted: %.*s\n", decrypted_len, decrypted_input);
    
    // Cleanup
    free(key);
    free(encrypted_input);
    free(decrypted_input);
    
    return 0;
}