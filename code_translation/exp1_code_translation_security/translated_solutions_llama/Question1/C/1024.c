#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Function to handle AES encryption
void encrypt_string(const unsigned char *message, unsigned char *encrypted, int message_len, AES_KEY *aes_key, unsigned char *iv) {
    AES_cfb128_encrypt(message, encrypted, message_len, aes_key, iv, AES_ENCRYPT);
}

// Function to handle AES decryption
void decrypt_string(const unsigned char *encrypted, unsigned char *decrypted, int encrypted_len, AES_KEY *aes_key, unsigned char *iv) {
    AES_cfb128_encrypt(encrypted, decrypted, encrypted_len, aes_key, iv, AES_DECRYPT);
}

int main() {
    // Define the key and IV
    const char *key = "This is a key123";
    const char *iv = "This is an IV456";

    // Convert key and IV to unsigned char arrays
    unsigned char key_bytes[16];
    unsigned char iv_bytes[16];
    strncpy((char *)key_bytes, key, 16);
    strncpy((char *)iv_bytes, iv, 16);

    // Initialize AES key
    AES_KEY aes_key;
    AES_set_encrypt_key(key_bytes, 128, &aes_key);

    // Define the message
    const char *message = "The answer is no";
    int message_len = strlen(message);

    // Encrypt the message
    unsigned char encrypted[256];
    encrypt_string((unsigned char *)message, encrypted, message_len, &aes_key, iv_bytes);

    // Decrypt the encrypted message
    unsigned char decrypted[256];
    decrypt_string(encrypted, decrypted, message_len, &aes_key, iv_bytes);

    // Print the results
    printf("Original Message: %s\n", message);
    printf("Encrypted Message: ");
    for (int i = 0; i < message_len; i++) {
        printf("%02x", encrypted[i]);
    }
    printf("\n");

    decrypted[message_len] = '\0'; // Null-terminate the decrypted string
    printf("Decrypted Message: %s\n", decrypted);

    return 0;
}