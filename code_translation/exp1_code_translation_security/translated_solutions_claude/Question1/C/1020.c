#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>

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

// PKCS7 padding function
unsigned char* pad(const unsigned char* plaintext, int plaintext_len, int* padded_len) {
    int block_size = 16; // AES block size
    int padding_len = block_size - (plaintext_len % block_size);
    *padded_len = plaintext_len + padding_len;
    
    unsigned char* padded = malloc(*padded_len);
    memcpy(padded, plaintext, plaintext_len);
    
    // Add PKCS7 padding
    for (int i = plaintext_len; i < *padded_len; i++) {
        padded[i] = padding_len;
    }
    
    return padded;
}

// PKCS7 unpadding function
unsigned char* unpad(const unsigned char* padded_text, int padded_len, int* unpadded_len) {
    if (padded_len == 0) return NULL;
    
    int padding_len = padded_text[padded_len - 1];
    *unpadded_len = padded_len - padding_len;
    
    unsigned char* unpadded = malloc(*unpadded_len + 1);
    memcpy(unpadded, padded_text, *unpadded_len);
    unpadded[*unpadded_len] = '\0';
    
    return unpadded;
}

// Encrypt function
char* encrypt(const unsigned char* key, int key_size, const unsigned char* plaintext) {
    unsigned char iv[16];
    int plaintext_len = strlen((char*)plaintext);
    int padded_len;
    
    // Generate random IV
    if (RAND_bytes(iv, 16) != 1) {
        return NULL;
    }
    
    // Pad the plaintext
    unsigned char* padded_txt = pad(plaintext, plaintext_len, &padded_len);
    
    // Initialize AES encryption
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    
    // Encrypt
    unsigned char* cipher_txt = malloc(padded_len);
    int cipher_len;
    int final_len;
    
    EVP_EncryptUpdate(ctx, cipher_txt, &cipher_len, padded_txt, padded_len);
    EVP_EncryptFinal_ex(ctx, cipher_txt + cipher_len, &final_len);
    cipher_len += final_len;
    
    // Combine IV and ciphertext
    unsigned char* iv_cipher = malloc(16 + cipher_len);
    memcpy(iv_cipher, iv, 16);
    memcpy(iv_cipher + 16, cipher_txt, cipher_len);
    
    // Base64 encode
    char* result = base64_encode(iv_cipher, 16 + cipher_len);
    
    // Cleanup
    EVP_CIPHER_CTX_free(ctx);
    free(padded_txt);
    free(cipher_txt);
    free(iv_cipher);
    
    return result;
}

// Decrypt function
unsigned char* decrypt(const unsigned char* key, int key_size, const char* ciphertext) {
    int decoded_len;
    unsigned char* decoded = base64_decode(ciphertext, &decoded_len);
    
    // Extract IV and ciphertext
    unsigned char iv[16];
    memcpy(iv, decoded, 16);
    
    unsigned char* cipher_data = decoded + 16;
    int cipher_len = decoded_len - 16;
    
    // Initialize AES decryption
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    
    // Decrypt
    unsigned char* plaintext = malloc(cipher_len);
    int plain_len;
    int final_len;
    
    EVP_DecryptUpdate(ctx, plaintext, &plain_len, cipher_data, cipher_len);
    EVP_DecryptFinal_ex(ctx, plaintext + plain_len, &final_len);
    plain_len += final_len;
    
    // Unpad
    int unpadded_len;
    unsigned char* result = unpad(plaintext, plain_len, &unpadded_len);
    
    // Cleanup
    EVP_CIPHER_CTX_free(ctx);
    free(decoded);
    free(plaintext);
    
    return result;
}

// Example usage
int main() {
    // Example key (32 bytes for AES-256)
    unsigned char key[32] = "this_is_a_32_byte_key_for_aes256";
    const char* message = "Hello, World! This is a test message.";
    
    // Encrypt
    char* encrypted = encrypt(key, 32, (unsigned char*)message);
    printf("Encrypted: %s\n", encrypted);
    
    // Decrypt
    unsigned char* decrypted = decrypt(key, 32, encrypted);
    printf("Decrypted: %s\n", decrypted);
    
    // Cleanup
    free(encrypted);
    free(decrypted);
    
    return 0;
}