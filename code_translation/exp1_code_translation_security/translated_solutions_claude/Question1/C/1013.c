Here's the C translation of the Python code. This implementation uses OpenSSL for cryptographic functions and provides equivalent functionality:

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <time.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <openssl/aes.h>

// Base64 encoding/decoding functions
static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

int base64_encode(const unsigned char* input, int length, char* output) {
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
    }
    
    output[j] = '\0';
    return j;
}

int base64_decode(const char* input, unsigned char* output) {
    int in_len = strlen(input);
    int i = 0, j = 0, in = 0;
    unsigned char char_array_4[4], char_array_3[3];
    
    while (in_len-- && input[in] != '=') {
        char c = input[in++];
        int val = -1;
        
        if (c >= 'A' && c <= 'Z') val = c - 'A';
        else if (c >= 'a' && c <= 'z') val = c - 'a' + 26;
        else if (c >= '0' && c <= '9') val = c - '0' + 52;
        else if (c == '-') val = 62;
        else if (c == '_') val = 63;
        
        if (val == -1) continue;
        
        char_array_4[i++] = val;
        if (i == 4) {
            char_array_3[0] = (char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4);
            char_array_3[1] = ((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2);
            char_array_3[2] = ((char_array_4[2] & 0x3) << 6) + char_array_4[3];
            
            for (i = 0; i < 3; i++)
                output[j++] = char_array_3[i];
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
            output[j++] = char_array_3[k];
    }
    
    return j;
}

// PBKDF2 key derivation
int derive_key(const unsigned char* password, int password_len, 
               const unsigned char* salt, int salt_len, 
               int iterations, unsigned char* key) {
    return PKCS5_PBKDF2_HMAC((const char*)password, password_len, 
                             salt, salt_len, iterations, 
                             EVP_sha256(), 32, key);
}

// Fernet-like encryption/decryption
typedef struct {
    unsigned char key[32];
} FernetKey;

int fernet_encrypt(FernetKey* fkey, const unsigned char* plaintext, int plaintext_len,
                   unsigned char* ciphertext, int* ciphertext_len) {
    EVP_CIPHER_CTX* ctx;
    int len;
    int final_len;
    
    // Generate random IV (16 bytes for AES-128-CBC)
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) return 0;
    
    // Create timestamp (8 bytes, big endian)
    uint64_t timestamp = (uint64_t)time(NULL);
    unsigned char ts_bytes[8];
    for (int i = 0; i < 8; i++) {
        ts_bytes[7-i] = (timestamp >> (i * 8)) & 0xFF;
    }
    
    ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return 0;
    
    // Initialize encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fkey->key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    
    // Copy version byte (0x80), timestamp, and IV to output
    ciphertext[0] = 0x80;
    memcpy(ciphertext + 1, ts_bytes, 8);
    memcpy(ciphertext + 9, iv, 16);
    
    // Encrypt the plaintext
    if (EVP_EncryptUpdate(ctx, ciphertext + 25, &len, plaintext, plaintext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    *ciphertext_len = len;
    
    if (EVP_EncryptFinal_ex(ctx, ciphertext + 25 + len, &final_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    *ciphertext_len += final_len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Add HMAC (32 bytes)
    unsigned char hmac_key[16];
    memcpy(hmac_key, fkey->key + 16, 16);
    
    unsigned int hmac_len;
    HMAC(EVP_sha256(), hmac_key, 16, ciphertext, 25 + *ciphertext_len, 
         ciphertext + 25 + *ciphertext_len, &hmac_len);
    
    *ciphertext_len += 25 + 32; // version + timestamp + iv + encrypted_data + hmac
    return 1;
}

int fernet_decrypt(FernetKey* fkey, const unsigned char* ciphertext, int ciphertext_len,
                   unsigned char* plaintext, int* plaintext_len) {
    if (ciphertext_len < 57) return 0; // Minimum size check
    
    // Verify HMAC
    unsigned char hmac_key[16];
    memcpy(hmac_key, fkey->key + 16, 16);
    
    unsigned char computed_hmac[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), hmac_key, 16, ciphertext, ciphertext_len - 32, 
         computed_hmac, &hmac_len);
    
    if (memcmp(computed_hmac, ciphertext + ciphertext_len - 32, 32) != 0) {
        return 0; // HMAC verification failed
    }
    
    // Extract IV and encrypted data
    const unsigned char* iv = ciphertext + 9;
    const unsigned char* encrypted_data = ciphertext + 25;
    int encrypted_len = ciphertext_len - 57;
    
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return 0;
    
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, fkey->key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    
    int len, final_len;
    if (EVP_DecryptUpdate(ctx, plaintext, &len, encrypted_data, encrypted_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &final_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return 0;
    }
    
    *plaintext_len = len + final_len;
    EVP_CIPHER_CTX_free(ctx);
    return 1;
}

// Main encryption function
int encrypt_message(const char* message, const char* password, char* output) {
    // Generate random salt
    unsigned char salt[16];
    if (RAND_bytes(salt, 16) != 1) return 0;
    
    int iterations = 100000;
    
    // Derive key
    unsigned char derived_key[32];
    if (!derive_key((const unsigned char*)password, strlen(password), 
                    salt, 16, iterations, derived_key)) {
        return 0;
    }
    
    // Base64 encode the derived key
    char b64_key[64];
    base64_encode(derived_key, 32, b64_key);
    
    // Decode it back for Fernet key (simulating Python's b64d(b64e()))
    unsigned char fernet_key_bytes[32];
    base64_decode(b64_key, fernet_key_bytes);
    
    FernetKey fkey;
    memcpy(fkey.key, fernet_key_bytes, 32);
    
    // Encrypt message
    unsigned char ciphertext[1024];
    int ciphertext_len;
    if (!fernet_encrypt(&fkey, (const unsigned char*)message, strlen(message), 
                        ciphertext, &ciphertext_len)) {
        return 0;
    }
    
    // Prepare final output: salt + iterations + ciphertext
    unsigned char final_data[1024];
    memcpy(final_data, salt, 16);
    
    // Convert iterations to big-endian 4 bytes
    final_data[16] = (iterations >> 24) & 0xFF;
    final_data[17] = (iterations >> 16) & 0xFF;
    final_data[18] = (iterations >> 8) & 0xFF;
    final_data[19] = iterations & 0xFF;
    
    memcpy(final_data + 20, ciphertext, ciphertext_len);
    
    // Base64 encode the final result
    base64_encode(final_data, 20 + ciphertext_len, output);
    return 1;
}

// Main decryption function
int decrypt_message(const char* token, const char* password, char* output) {
    // Base64 decode the token
    unsigned char decoded[1024];
    int decoded_len = base64_decode(token, decoded);
    
    if (decoded_len < 20) return 0;
    
    // Extract salt, iterations, and encrypted data
    unsigned char salt[16];
    memcpy(salt, decoded, 16);
    
    int iterations = (decoded[16] << 24) | (decoded[17] << 16) | 
                     (decoded[18] << 8) | decoded[19];
    
    // Derive key
    unsigned char derived_key[32];
    if (!derive_key((const unsigned char*)password, strlen(password), 
                    salt, 16, iterations, derived_key)) {
        return 0;
    }
    
    // Base64 encode then decode the key (matching Python behavior)
    char b64_key[64];
    base64_encode(derived_key, 32, b64_key);
    
    unsigned char fernet_key_bytes[32];
    base64_decode(b64_key, fernet_key_bytes);
    
    FernetKey fkey;
    memcpy(fkey.key, fernet_key_bytes, 32);
    
    // Base64 encode the ciphertext part for Fernet decryption
    char b64_ciphertext[1024];
    base64_encode(decoded + 20, decoded_len - 20, b64_ciphertext);
    
    // Decode it back
    unsigned char ciphertext[1024];
    int ciphertext_len = base64_decode(b64_ciphertext, ciphertext);
    
    // Decrypt
    unsigned char plaintext[1024];
    int plaintext_len;
    if (!fernet_decrypt(&fkey, ciphertext, ciphertext_len, plaintext, &plaintext_len)) {
        return 0;
    }
    
    memcpy(output, plaintext, plaintext_len);
    output[plaintext_len] = '\0';
    return 1;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    char encrypted[2048];
    char decrypted[1024];
    
    // Test the functions
    if (encrypt_message("nice123", "password123", encrypted)) {
        printf("Encrypted: %s\n", encrypted);
        
        if (decrypt_message(encrypted, "password123", decrypted)) {
            printf("Decrypted: %s\n", decrypted);
        } else {
            printf("Decryption failed\n");
        }
    } else {
        printf("Encryption failed\n");
    }
    
    // Cleanup
    EVP_cleanup();