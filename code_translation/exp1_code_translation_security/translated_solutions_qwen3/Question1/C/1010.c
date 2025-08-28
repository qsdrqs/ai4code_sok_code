#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

// Include the tiny-AES-c library inline
#include "aes.h"

// Generate a 16-byte symmetric key using /dev/urandom
void generate_key(unsigned char *key) {
    int fd = open("/dev/urandom", O_RDONLY);
    if (fd == -1) {
        perror("Failed to open /dev/urandom");
        exit(EXIT_FAILURE);
    }
    if (read(fd, key, 16) != 16) {
        perror("Failed to read from /dev/urandom");
        close(fd);
        exit(EXIT_FAILURE);
    }
    close(fd);
}

// Encrypt a string using AES in EAX mode
void encrypt(const unsigned char *key, const char *data, unsigned char **ciphertext, unsigned char **nonce) {
    size_t data_len = strlen(data);
    *nonce = malloc(16);
    *ciphertext = malloc(data_len);
    unsigned char tag[16];

    // Generate a random nonce
    int fd = open("/dev/urandom", O_RDONLY);
    if (fd == -1) {
        perror("Failed to open /dev/urandom");
        exit(EXIT_FAILURE);
    }
    if (read(fd, *nonce, 16) != 16) {
        perror("Failed to read nonce");
        close(fd);
        exit(EXIT_FAILURE);
    }
    close(fd);

    // Encrypt the data
    AES_EAX_ctx ctx;
    AES_EAX_init(&ctx, key, 16, *nonce, 16);
    AES_EAX_encrypt(&ctx, (const uint8_t *)data, *ciphertext, data_len);
    AES_EAX_digest(&ctx, tag, 16);  // Generate the tag, but do not return it
}

// Decrypt a string using AES in EAX mode
void decrypt(const unsigned char *key, const unsigned char *nonce, const unsigned char *ciphertext, size_t ciphertext_len, char **data) {
    *data = malloc(ciphertext_len + 1);  // +1 for null terminator
    AES_EAX_ctx ctx;
    AES_EAX_init(&ctx, key, 16, nonce, 16);
    AES_EAX_decrypt(&ctx, ciphertext, (uint8_t *)*data, ciphertext_len);
    (*data)[ciphertext_len] = '\0';  // Null-terminate the string
}

// Example usage
int main() {
    unsigned char key[16];
    generate_key(key);

    const char *test_data = "Jim's test";
    unsigned char *ciphertext, *nonce;

    // Encrypt
    encrypt(key, test_data, &ciphertext, &nonce);

    // Decrypt
    char *restored_data;
    decrypt(key, nonce, ciphertext, strlen(test_data), &restored_data);

    // Output result
    printf("Decrypted: %s\n", restored_data);

    // Clean up
    free(ciphertext);
    free(nonce);
    free(restored_data);

    return 0;
}