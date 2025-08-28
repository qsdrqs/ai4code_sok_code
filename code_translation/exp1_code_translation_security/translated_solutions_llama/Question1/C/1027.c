#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/err.h>
#include <errno.h>

// Function to handle OpenSSL errors
void handle_openssl_error() {
    unsigned long err_code;
    char err_str[256];

    while ((err_code = ERR_get_error()) != 0) {
        ERR_error_string_n(err_code, err_str, 256);
        fprintf(stderr, "%s\n", err_str);
    }
}

// Function to pad a message
unsigned char* pad(unsigned char* message, int message_len, int block_size) {
    int padded_len = (message_len / block_size + 1) * block_size;
    unsigned char* padded_message = (unsigned char*)malloc(padded_len);
    if (!padded_message) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }

    memcpy(padded_message, message, message_len);
    for (int i = message_len; i < padded_len; i++) {
        padded_message[i] = block_size - (message_len % block_size);
    }

    return padded_message;
}

// Function to unpad a message
unsigned char* unpad(unsigned char* message, int message_len, int block_size) {
    int unpadded_len = message_len - message[message_len - 1];
    unsigned char* unpadded_message = (unsigned char*)malloc(unpadded_len);
    if (!unpadded_message) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }

    memcpy(unpadded_message, message, unpadded_len);

    return unpadded_message;
}

// Function to encrypt a message
unsigned char* encrypt(unsigned char* message, int message_len, unsigned char* key) {
    // Pad message
    unsigned char* padded_message = pad(message, message_len, AES_BLOCK_SIZE);
    int padded_len = (message_len / AES_BLOCK_SIZE + 1) * AES_BLOCK_SIZE;

    // Create a random initialization vector (IV)
    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        handle_openssl_error();
        free(padded_message);
        exit(EXIT_FAILURE);
    }

    // Create AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, 128, &aes_key) != 0) {
        handle_openssl_error();
        free(padded_message);
        exit(EXIT_FAILURE);
    }

    // Encrypt message
    unsigned char* ciphertext = (unsigned char*)malloc(padded_len);
    if (!ciphertext) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        free(padded_message);
        exit(EXIT_FAILURE);
    }
    AES_cbc_encrypt(padded_message, ciphertext, padded_len, &aes_key, iv, AES_ENCRYPT);

    // Combine IV and ciphertext
    unsigned char* encrypted_data = (unsigned char*)malloc(padded_len + AES_BLOCK_SIZE);
    if (!encrypted_data) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        free(padded_message);
        free(ciphertext);
        exit(EXIT_FAILURE);
    }
    memcpy(encrypted_data, iv, AES_BLOCK_SIZE);
    memcpy(encrypted_data + AES_BLOCK_SIZE, ciphertext, padded_len);

    free(padded_message);
    free(ciphertext);

    return encrypted_data;
}

// Function to decrypt a message
unsigned char* decrypt(unsigned char* encrypted_data, int encrypted_len, unsigned char* key) {
    if (encrypted_len < AES_BLOCK_SIZE) {
        fprintf(stderr, "Invalid encrypted data length\n");
        exit(EXIT_FAILURE);
    }

    // Extract IV and ciphertext
    unsigned char* iv = encrypted_data;
    unsigned char* ciphertext = encrypted_data + AES_BLOCK_SIZE;
    int ciphertext_len = encrypted_len - AES_BLOCK_SIZE;

    // Create AES context
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, 128, &aes_key) != 0) {
        handle_openssl_error();
        exit(EXIT_FAILURE);
    }

    // Decrypt message
    unsigned char* decrypted_padded_message = (unsigned char*)malloc(ciphertext_len);
    if (!decrypted_padded_message) {
        fprintf(stderr, "Memory allocation failed: %s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    AES_cbc_encrypt(ciphertext, decrypted_padded_message, ciphertext_len, &aes_key, iv, AES_DECRYPT);

    // Unpad message
    unsigned char* decrypted_message = unpad(decrypted_padded_message, ciphertext_len, AES_BLOCK_SIZE);

    free(decrypted_padded_message);

    return decrypted_message;
}

int main() {
    // Example usage
    unsigned char key[16] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    unsigned char message[] = "Hello, World!";
    int message_len = strlen((char*)message);

    unsigned char* encrypted_data = encrypt(message, message_len, key);
    int encrypted_len = AES_BLOCK_SIZE + ((message_len / AES_BLOCK_SIZE + 1) * AES_BLOCK_SIZE);

    printf("Encrypted data: ");
    for (int i = 0; i < encrypted_len; i++) {
        printf("%02x", encrypted_data[i]);
    }
    printf("\n");

    unsigned char* decrypted_message = decrypt(encrypted_data, encrypted_len, key);

    printf("Decrypted message: %s\n", decrypted_message);

    free(encrypted_data);
    free(decrypted_message);

    return 0;
}