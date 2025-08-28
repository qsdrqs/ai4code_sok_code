#include <openssl/aes.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>

// Function to pad plaintext to a multiple of AES block size (16 bytes)
unsigned char* pad(const unsigned char* plaintext, int* padded_len) {
    int plaintext_len = strlen((const char*)plaintext);
    int padding = AES_BLOCK_SIZE - (plaintext_len % AES_BLOCK_SIZE);
    *padded_len = plaintext_len + padding;

    unsigned char* padded_text = (unsigned char*)malloc(*padded_len);
    memcpy(padded_text, plaintext, plaintext_len);

    // Add padding bytes
    for (int i = plaintext_len; i < *padded_len; i++) {
        padded_text[i] = padding;
    }

    return padded_text;
}

// Function to remove padding from decrypted plaintext
unsigned char* unpad(const unsigned char* padded_text, int padded_len, int* plaintext_len) {
    int padding = padded_text[padded_len - 1];
    *plaintext_len = padded_len - padding;

    unsigned char* plaintext = (unsigned char*)malloc(*plaintext_len + 1);
    memcpy(plaintext, padded_text, *plaintext_len);
    plaintext[*plaintext_len] = '\0'; // Null-terminate the string

    return plaintext;
}

// Function to encrypt plaintext using AES-CBC
unsigned char* encrypt(const unsigned char* key, int key_size, const unsigned char* plaintext, int* cipher_len) {
    unsigned char iv[AES_BLOCK_SIZE];
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error generating random IV\n");
        return NULL;
    }

    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, key_size * 8, &aes_key) < 0) {
        fprintf(stderr, "Error setting encryption key\n");
        return NULL;
    }

    int padded_len;
    unsigned char* padded_text = pad(plaintext, &padded_len);

    unsigned char* cipher_text = (unsigned char*)malloc(AES_BLOCK_SIZE + padded_len);
    memcpy(cipher_text, iv, AES_BLOCK_SIZE); // Prepend IV to the ciphertext

    AES_cbc_encrypt(padded_text, cipher_text + AES_BLOCK_SIZE, padded_len, &aes_key, iv, AES_ENCRYPT);

    free(padded_text);

    *cipher_len = AES_BLOCK_SIZE + padded_len;
    return cipher_text;
}

// Function to decrypt ciphertext using AES-CBC
unsigned char* decrypt(const unsigned char* key, int key_size, const unsigned char* ciphertext, int cipher_len, int* plaintext_len) {
    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, ciphertext, AES_BLOCK_SIZE); // Extract IV from the ciphertext

    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, key_size * 8, &aes_key) < 0) {
        fprintf(stderr, "Error setting decryption key\n");
        return NULL;
    }

    int padded_len = cipher_len - AES_BLOCK_SIZE;
    unsigned char* padded_text = (unsigned char*)malloc(padded_len);

    AES_cbc_encrypt(ciphertext + AES_BLOCK_SIZE, padded_text, padded_len, &aes_key, iv, AES_DECRYPT);

    unsigned char* plaintext = unpad(padded_text, padded_len, plaintext_len);

    free(padded_text);
    return plaintext;
}

// Example usage
int main() {
    const unsigned char key[16] = "thisisakey123456"; // 16-byte key
    const unsigned char plaintext[] = "Hello, World!";

    int cipher_len;
    unsigned char* cipher_text = encrypt(key, 16, plaintext, &cipher_len);

    if (cipher_text) {
        printf("Ciphertext (Base64): ");
        for (int i = 0; i < cipher_len; i++) {
            printf("%02x", cipher_text[i]);
        }
        printf("\n");

        int plaintext_len;
        unsigned char* decrypted_text = decrypt(key, 16, cipher_text, cipher_len, &plaintext_len);

        if (decrypted_text) {
            printf("Decrypted text: %s\n", decrypted_text);
            free(decrypted_text);
        }

        free(cipher_text);
    }

    return 0;
}