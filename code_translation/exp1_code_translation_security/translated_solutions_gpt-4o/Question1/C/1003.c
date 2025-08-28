#include <openssl/aes.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt data using AES in ECB mode
void encrypt_bytes_aes(const unsigned char *data, const unsigned char *key, unsigned char *encrypted_data, size_t data_len) {
    AES_KEY encrypt_key;

    // Set the encryption key
    AES_set_encrypt_key(key, 128, &encrypt_key);

    // Encrypt the data in blocks of AES_BLOCK_SIZE (16 bytes)
    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE) {
        AES_ecb_encrypt(data + i, encrypted_data + i, &encrypt_key, AES_ENCRYPT);
    }
}

// Function to decrypt data using AES in ECB mode
void decrypt_bytes_aes(const unsigned char *data, const unsigned char *key, unsigned char *decrypted_data, size_t data_len) {
    AES_KEY decrypt_key;

    // Set the decryption key
    AES_set_decrypt_key(key, 128, &decrypt_key);

    // Decrypt the data in blocks of AES_BLOCK_SIZE (16 bytes)
    for (size_t i = 0; i < data_len; i += AES_BLOCK_SIZE) {
        AES_ecb_encrypt(data + i, decrypted_data + i, &decrypt_key, AES_DECRYPT);
    }
}

// Example usage
int main() {
    // Example 16-byte key (128-bit key)
    unsigned char key[16] = "examplekey123456";

    // Example plaintext (must be a multiple of 16 bytes for AES ECB mode)
    unsigned char plaintext[16] = "exampleplaintext";

    // Buffers for encrypted and decrypted data
    unsigned char encrypted_data[16];
    unsigned char decrypted_data[16];

    // Encrypt the plaintext
    encrypt_bytes_aes(plaintext, key, encrypted_data, sizeof(plaintext));

    // Decrypt the encrypted data
    decrypt_bytes_aes(encrypted_data, key, decrypted_data, sizeof(encrypted_data));

    // Print the results
    printf("Plaintext: %s\n", plaintext);
    printf("Encrypted: ");
    for (size_t i = 0; i < sizeof(encrypted_data); i++) {
        printf("%02x", encrypted_data[i]);
    }
    printf("\n");
    printf("Decrypted: %s\n", decrypted_data);

    return 0;
}