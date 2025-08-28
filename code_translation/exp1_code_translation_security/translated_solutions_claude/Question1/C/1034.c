#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Cryptocode library functions (dependencies provided)
char* cryptocode_encrypt(const char* plain_text, const char* key);
char* cryptocode_decrypt(const char* encrypted_text, const char* key);
void cryptocode_free(char* ptr);

char* encode_str(const char* plain_text, const char* key) {
    char* str_encoded = cryptocode_encrypt(plain_text, key);
    return str_encoded;
}

char* decode_str(const char* str_encoded, const char* key) {
    char* str_decoded = cryptocode_decrypt(str_encoded, key);
    return str_decoded;
}

int main() {
    char* str_encoded1 = encode_str("I am okay", "wow");
    char* decoded_result = decode_str(str_encoded1, "wow");
    
    printf("%s\n", decoded_result);
    
    // Clean up allocated memory
    cryptocode_free(str_encoded1);
    cryptocode_free(decoded_result);
    
    return 0;
}

// Mock implementation of cryptocode library functions
// (In actual usage, these would be provided by the cryptocode library)

#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/buffer.h>

// Base64 encoding/decoding functions
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
    
    char* buff = (char*)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    
    BIO_free_all(b64);
    return buff;
}

unsigned char* base64_decode(const char* input, int* output_length) {
    BIO *b64, *bmem;
    int length = strlen(input);
    unsigned char* buffer = (unsigned char*)malloc(length);
    
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new_mem_buf(input, length);
    bmem = BIO_push(b64, bmem);
    BIO_set_flags(bmem, BIO_FLAGS_BASE64_NO_NL);
    
    *output_length = BIO_read(bmem, buffer, length);
    BIO_free_all(bmem);
    
    return buffer;
}

char* cryptocode_encrypt(const char* plain_text, const char* key) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    unsigned char iv[16];
    unsigned char derived_key[32];
    
    // Generate random IV
    if (RAND_bytes(iv, 16) != 1) {
        return NULL;
    }
    
    // Derive key using SHA256
    SHA256((unsigned char*)key, strlen(key), derived_key);
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        return NULL;
    }
    
    // Initialize the encryption operation
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, derived_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    int plaintext_len = strlen(plain_text);
    unsigned char* ciphertext = malloc(plaintext_len + 16 + 16); // Extra space for padding and IV
    
    // Copy IV to the beginning of ciphertext
    memcpy(ciphertext, iv, 16);
    
    // Encrypt the plaintext
    if (EVP_EncryptUpdate(ctx, ciphertext + 16, &len, (unsigned char*)plain_text, plaintext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize the encryption
    if (EVP_EncryptFinal_ex(ctx, ciphertext + 16 + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(ciphertext);
        return NULL;
    }
    ciphertext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Base64 encode the result (IV + ciphertext)
    char* encoded = base64_encode(ciphertext, ciphertext_len + 16);
    free(ciphertext);
    
    return encoded;
}

char* cryptocode_decrypt(const char* encrypted_text, const char* key) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    unsigned char derived_key[32];
    
    // Derive key using SHA256
    SHA256((unsigned char*)key, strlen(key), derived_key);
    
    // Base64 decode the input
    int decoded_len;
    unsigned char* decoded = base64_decode(encrypted_text, &decoded_len);
    
    if (decoded_len < 16) {
        free(decoded);
        return NULL;
    }
    
    // Extract IV from the beginning
    unsigned char* iv = decoded;
    unsigned char* ciphertext = decoded + 16;
    int ciphertext_len = decoded_len - 16;
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        free(decoded);
        return NULL;
    }
    
    // Initialize the decryption operation
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, derived_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decoded);
        return NULL;
    }
    
    unsigned char* plaintext = malloc(ciphertext_len + 16);
    
    // Decrypt the ciphertext
    if (EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decoded);
        free(plaintext);
        return NULL;
    }
    plaintext_len = len;
    
    // Finalize the decryption
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decoded);
        free(plaintext);
        return NULL;
    }
    plaintext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    free(decoded);
    
    // Null-terminate the result
    char* result = malloc(plaintext_len + 1);
    memcpy(result, plaintext, plaintext_len);
    result[plaintext_len] = '\0';
    free(plaintext);
    
    return result;
}

void cryptocode_free(char* ptr) {
    if (ptr) {
        free(ptr);
    }
}