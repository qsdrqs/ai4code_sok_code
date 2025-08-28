#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <base64.h>

// Structure to hold AES cipher context
typedef struct {
    unsigned char key[32];
    int block_size;
} AESCipher;

// Function to generate SHA-256 hash of a given string
void sha256(const unsigned char *str, unsigned char *hash) {
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, str, strlen((const char *)str));
    SHA256_Final(hash, &sha256);
}

// Function to pad a given string
unsigned char *pad(const unsigned char *str, int block_size) {
    int len = strlen((const char *)str);
    int padding_len = block_size - (len % block_size);
    unsigned char *padded_str = (unsigned char *)malloc(len + padding_len);
    memcpy(padded_str, str, len);
    for (int i = 0; i < padding_len; i++) {
        padded_str[len + i] = padding_len;
    }
    return padded_str;
}

// Function to unpad a given string
unsigned char *unpad(const unsigned char *str, int block_size) {
    int len = strlen((const char *)str);
    int padding_len = str[len - 1];
    unsigned char *unpadded_str = (unsigned char *)malloc(len - padding_len);
    memcpy(unpadded_str, str, len - padding_len);
    return unpadded_str;
}

// Function to create a new AES cipher context
AESCipher *aes_cipher_new(const char *key) {
    AESCipher *cipher = (AESCipher *)malloc(sizeof(AESCipher));
    cipher->block_size = AES_BLOCK_SIZE;
    unsigned char hash[SHA256_DIGEST_LENGTH];
    sha256((const unsigned char *)key, hash);
    memcpy(cipher->key, hash, 32);
    return cipher;
}

// Function to encrypt a given string using AES
char *aes_encrypt(AESCipher *cipher, const char *str) {
    unsigned char *padded_str = pad((const unsigned char *)str, cipher->block_size);
    int len = strlen((const char *)padded_str);
    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);
    AES_KEY aes_key;
    AES_set_encrypt_key(cipher->key, 256, &aes_key);
    unsigned char *encrypted_str = (unsigned char *)malloc(len + AES_BLOCK_SIZE);
    AES_cbc_encrypt(padded_str, encrypted_str + AES_BLOCK_SIZE, len, &aes_key, iv, AES_ENCRYPT);
    // Prepend IV to encrypted string
    memcpy(encrypted_str, iv, AES_BLOCK_SIZE);
    // Base64 encode encrypted string
    char *base64_str = base64_encode(encrypted_str, len + AES_BLOCK_SIZE);
    free(padded_str);
    free(encrypted_str);
    return base64_str;
}

// Function to decrypt a given string using AES
char *aes_decrypt(AESCipher *cipher, const char *enc_str) {
    // Base64 decode encrypted string
    char *decoded_str = base64_decode(enc_str, NULL);
    int len = strlen(decoded_str);
    unsigned char *iv = (unsigned char *)malloc(AES_BLOCK_SIZE);
    memcpy(iv, decoded_str, AES_BLOCK_SIZE);
    unsigned char *encrypted_str = (unsigned char *)malloc(len - AES_BLOCK_SIZE);
    memcpy(encrypted_str, decoded_str + AES_BLOCK_SIZE, len - AES_BLOCK_SIZE);
    free(decoded_str);
    AES_KEY aes_key;
    AES_set_decrypt_key(cipher->key, 256, &aes_key);
    int padded_len = len - AES_BLOCK_SIZE;
    unsigned char *decrypted_str = (unsigned char *)malloc(padded_len);
    AES_cbc_encrypt(encrypted_str, decrypted_str, padded_len, &aes_key, iv, AES_DECRYPT);
    free(encrypted_str);
    free(iv);
    // Unpad decrypted string
    unsigned char *unpadded_str = unpad(decrypted_str, cipher->block_size);
    free(decrypted_str);
    return (char *)unpadded_str;
}

int main() {
    const char *key = "mysecretpassword";
    AESCipher *cipher = aes_cipher_new(key);
    const char *str = "Secret Message A";
    char *encrypted_str = aes_encrypt(cipher, str);
    printf("Encrypted: %s\n", encrypted_str);
    char *decrypted_str = aes_decrypt(cipher, encrypted_str);
    printf("Decrypted: %s\n", decrypted_str);
    free(encrypted_str);
    free(decrypted_str);
    free(cipher);
    return 0;
}