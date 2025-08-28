#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// Structure to hold AES context
typedef struct {
    unsigned char key[AES_KEY_SIZE_128 / 8];
    AES_KEY aes_key;
} AES_CTX;

// Function to generate a random key
void generate_key(unsigned char *key) {
    if (RAND_bytes(key, AES_KEY_SIZE_128 / 8) != 1) {
        printf("Error generating random key\n");
        exit(1);
    }
}

// Function to encrypt a message using AES
void encrypt(unsigned char *message, int message_len, unsigned char *key, unsigned char *enc_message, int *enc_message_len) {
    AES_KEY aes_key;
    AES_set_encrypt_key(key, 128, &aes_key);

    // Calculate the required buffer size for encryption
    *enc_message_len = message_len + AES_BLOCK_SIZE;
    unsigned char *iv = (unsigned char *)malloc(AES_BLOCK_SIZE);
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        printf("Error generating random IV\n");
        exit(1);
    }

    // Copy IV to the beginning of the encrypted message
    memcpy(enc_message, iv, AES_BLOCK_SIZE);
    *enc_message_len += AES_BLOCK_SIZE; // Update length to include IV

    // Perform AES CBC encryption
    AES_cbc_encrypt(message, enc_message + AES_BLOCK_SIZE, message_len, &aes_key, iv, AES_ENCRYPT);

    free(iv);
}

// Function to decrypt a message using AES
void decrypt(unsigned char *enc_message, int enc_message_len, unsigned char *key, unsigned char *dec_message, int *dec_message_len) {
    AES_KEY aes_key;
    AES_set_decrypt_key(key, 128, &aes_key);

    // Extract IV from the encrypted message
    unsigned char *iv = enc_message;
    enc_message += AES_BLOCK_SIZE;
    enc_message_len -= AES_BLOCK_SIZE;

    // Perform AES CBC decryption
    AES_cbc_encrypt(enc_message, dec_message, enc_message_len, &aes_key, iv, AES_DECRYPT);

    *dec_message_len = enc_message_len;
}

int main() {
    const char *message = "the solutions are here somewhere";
    printf("Secret Message: %s\n", message);

    // Generate a random key
    unsigned char key[AES_KEY_SIZE_128 / 8];
    generate_key(key);

    // Encrypt the message
    int message_len = strlen(message);
    int enc_message_len;
    unsigned char *enc_message = (unsigned char *)malloc(message_len + AES_BLOCK_SIZE * 2);
    encrypt((unsigned char *)message, message_len, key, enc_message, &enc_message_len);
    printf("Encrypted Message: ");
    for (int i = 0; i < enc_message_len; i++) {
        printf("%02x", enc_message[i]);
    }
    printf("\n");

    // Decrypt the message
    int dec_message_len;
    unsigned char *dec_message = (unsigned char *)malloc(message_len + AES_BLOCK_SIZE);
    decrypt(enc_message, enc_message_len, key, dec_message, &dec_message_len);
    dec_message[dec_message_len] = '\0'; // Null-terminate the string
    printf("Decrypted Message: %s\n", dec_message);

    free(enc_message);
    free(dec_message);

    return 0;
}