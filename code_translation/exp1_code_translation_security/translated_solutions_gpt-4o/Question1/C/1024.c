#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// Function to encrypt a string
void encrypt_string(const unsigned char *message, unsigned char *ciphertext, AES_KEY *aes_key, unsigned char *iv) {
    AES_cfb128_encrypt(message, ciphertext, strlen((const char *)message), aes_key, iv, NULL, AES_ENCRYPT);
}

// Function to decrypt a string
void decrypt_string(const unsigned char *ciphertext, unsigned char *plaintext, AES_KEY *aes_key, unsigned char *iv) {
    AES_cfb128_encrypt(ciphertext, plaintext, strlen((const char *)ciphertext), aes_key, iv, NULL, AES_DECRYPT);
}

int main() {
    // Key and IV
    unsigned char key[16] = "This is a key123"; // 16 bytes for AES-128
    unsigned char iv[16] = "This is an IV456"; // 16 bytes for IV

    // Message to encrypt
    const unsigned char *message = (unsigned char *)"The answer is no";

    // Buffers for ciphertext and decrypted text
    unsigned char ciphertext[128];
    unsigned char decryptedtext[128];

    // AES key structures
    AES_KEY encrypt_key, decrypt_key;

    // Initialize AES keys
    AES_set_encrypt_key(key, 128, &encrypt_key);
    AES_set_decrypt_key(key, 128, &decrypt_key);

    // Encrypt the message
    unsigned char iv_enc[16];
    memcpy(iv_enc, iv, 16); // Copy IV because it will be modified during encryption
    encrypt_string(message, ciphertext, &encrypt_key, iv_enc);

    // Decrypt the ciphertext
    unsigned char iv_dec[16];
    memcpy(iv_dec, iv, 16); // Copy IV because it will be modified during decryption
    decrypt_string(ciphertext, decryptedtext, &decrypt_key, iv_dec);

    // Null-terminate the decrypted text
    decryptedtext[strlen((const char *)message)] = '\0';

    // Print results
    printf("Original message: %s\n", message);
    printf("Encrypted message: ");
    for (int i = 0; i < strlen((const char *)message); i++) {
        printf("%02x", ciphertext[i]);
    }
    printf("\n");
    printf("Decrypted message: %s\n", decryptedtext);

    return 0;
}