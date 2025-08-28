#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/crypto.h>
#include <openssl/aes.h>
#include <openssl/modes.h>
#include <openssl/err.h>

// Function to generate a random byte array
void generate_random_bytes(unsigned char* buffer, size_t length) {
    if (RAND_bytes(buffer, length) != 1) {
        fprintf(stderr, "Failed to generate random bytes\n");
        exit(1);
    }
}

// Function to encrypt data using AES-CBC
int encrypt(const unsigned char* data, size_t data_len, const unsigned char* key, const unsigned char* iv, unsigned char* ct) {
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, 256, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES encrypt key\n");
        return 1;
    }

    AES_cbc_encrypt(data, ct, data_len, &aes_key, iv, AES_ENCRYPT);
    return 0;
}

// Function to decrypt data using AES-CBC
int decrypt(const unsigned char* ct, size_t ct_len, const unsigned char* key, const unsigned char* iv, unsigned char* pt) {
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, 256, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES decrypt key\n");
        return 1;
    }

    AES_cbc_encrypt(ct, pt, ct_len, &aes_key, iv, AES_DECRYPT);
    return 0;
}

// Test function
int test() {
    const char* data = "a secret message";
    size_t data_len = strlen(data);
    unsigned char key[32];
    unsigned char iv[16];
    unsigned char e[data_len + AES_BLOCK_SIZE]; // Ensure enough space for padding
    unsigned char d[data_len + AES_BLOCK_SIZE];

    generate_random_bytes(key, 32);
    generate_random_bytes(iv, 16);

    if (encrypt((const unsigned char*)data, data_len, key, iv, e) != 0) {
        return 1;
    }

    if (decrypt(e, AES_BLOCK_SIZE + data_len, key, iv, d) != 0) {
        return 1;
    }

    // Remove padding (assuming PKCS#7 padding)
    size_t pt_len = data_len;
    if (d[pt_len - 1] > 0) {
        pt_len -= d[pt_len - 1];
    }

    if (memcmp(d, data, pt_len) != 0) {
        fprintf(stderr, "Decrypted data does not match original data\n");
        return 1;
    }

    return 0;
}

int main() {
    OpenSSL_add_all_algorithms();
    if (test() != 0) {
        return 1;
    }
    EVP_cleanup();
    return 0;
}