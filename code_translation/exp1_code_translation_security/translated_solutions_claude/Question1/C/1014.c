#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>

#define BLOCK_SIZE 32
#define AES_BLOCK_SIZE 16
#define SHA256_DIGEST_LENGTH 32

typedef struct {
    unsigned char key[SHA256_DIGEST_LENGTH];
    int bs;
} AESCipher;

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
    
    char* buff = (char*)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    
    BIO_free_all(b64);
    return buff;
}

// Base64 decoding function
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

// Initialize AESCipher with key
AESCipher* aes_cipher_init(const char* key) {
    AESCipher* cipher = (AESCipher*)malloc(sizeof(AESCipher));
    cipher->bs = BLOCK_SIZE;
    
    // Generate SHA256 hash of the key
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, key, strlen(key));
    SHA256_Final(cipher->key, &sha256);
    
    return cipher;
}

// Padding function (PKCS7 padding)
char* pad_string(const char* input, int* padded_length) {
    int input_len = strlen(input);
    int pad_len = BLOCK_SIZE - (input_len % BLOCK_SIZE);
    *padded_length = input_len + pad_len;
    
    char* padded = (char*)malloc(*padded_length + 1);
    strcpy(padded, input);
    
    for (int i = input_len; i < *padded_length; i++) {
        padded[i] = (char)pad_len;
    }
    padded[*padded_length] = '\0';
    
    return padded;
}

// Unpadding function
char* unpad_string(const unsigned char* input, int input_length) {
    if (input_length == 0) return NULL;
    
    int pad_len = input[input_length - 1];
    int unpadded_length = input_length - pad_len;
    
    char* unpadded = (char*)malloc(unpadded_length + 1);
    memcpy(unpadded, input, unpadded_length);
    unpadded[unpadded_length] = '\0';
    
    return unpadded;
}

// Encrypt function
char* aes_encrypt(AESCipher* cipher, const char* plaintext) {
    // Pad the input
    int padded_length;
    char* padded_text = pad_string(plaintext, &padded_length);
    
    // Generate random IV
    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        free(padded_text);
        return NULL;
    }
    
    // Encrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, cipher->key, iv);
    
    unsigned char* ciphertext = (unsigned char*)malloc(padded_length + AES_BLOCK_SIZE);
    int len;
    int ciphertext_len;
    
    EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)padded_text, padded_length);
    ciphertext_len = len;
    
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Combine IV + ciphertext
    unsigned char* result = (unsigned char*)malloc(AES_BLOCK_SIZE + ciphertext_len);
    memcpy(result, iv, AES_BLOCK_SIZE);
    memcpy(result + AES_BLOCK_SIZE, ciphertext, ciphertext_len);
    
    // Base64 encode
    char* encoded = base64_encode(result, AES_BLOCK_SIZE + ciphertext_len);
    
    free(padded_text);
    free(ciphertext);
    free(result);
    
    return encoded;
}

// Decrypt function
char* aes_decrypt(AESCipher* cipher, const char* ciphertext) {
    // Base64 decode
    int decoded_length;
    unsigned char* decoded = base64_decode(ciphertext, &decoded_length);
    
    // Extract IV and ciphertext
    unsigned char* iv = decoded;
    unsigned char* encrypted_data = decoded + AES_BLOCK_SIZE;
    int encrypted_length = decoded_length - AES_BLOCK_SIZE;
    
    // Decrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, cipher->key, iv);
    
    unsigned char* plaintext = (unsigned char*)malloc(encrypted_length + AES_BLOCK_SIZE);
    int len;
    int plaintext_len;
    
    EVP_DecryptUpdate(ctx, plaintext, &len, encrypted_data, encrypted_length);
    plaintext_len = len;
    
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);
    plaintext_len += len;
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Unpad
    char* result = unpad_string(plaintext, plaintext_len);
    
    free(decoded);
    free(plaintext);
    
    return result;
}

// Free AESCipher
void aes_cipher_free(AESCipher* cipher) {
    free(cipher);
}

// Example usage (commented out like in Python)
/*
int main() {
    const char* key = "mysecretpassword";
    AESCipher* cipher = aes_cipher_init(key);
    
    char* encrypted = aes_encrypt(cipher, "Secret Message A");
    char* decrypted = aes_decrypt(cipher, encrypted);
    
    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);
    
    free(encrypted);
    free(decrypted);
    aes_cipher_free(cipher);
    
    return 0;
}
*/