#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Function to handle OpenSSL errors
void handle_openssl_error() {
    unsigned long err_code;
    char err_str[256];

    while ((err_code = ERR_get_error()) != 0) {
        ERR_error_string_n(err_code, err_str, 256);
        fprintf(stderr, "%s\n", err_str);
    }
}

// Function to encrypt data using AES in ECB mode
unsigned char* encrypt_bytes_aes(const unsigned char* data, size_t data_len, const unsigned char* key, size_t key_len, size_t* encrypted_len) {
    // Check if key length is valid for AES
    if (key_len != 16 && key_len != 24 && key_len != 32) {
        fprintf(stderr, "Invalid key length. Key must be 128, 192, or 256 bits.\n");
        return NULL;
    }

    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, key_len * 8, &aes_key) != 0) {
        handle_openssl_error();
        return NULL;
    }

    // Calculate encrypted data length
    *encrypted_len = data_len;
    if (data_len % 16 != 0) {
        *encrypted_len += 16 - (data_len % 16);
    }

    // Allocate memory for encrypted data
    unsigned char* encrypted_data = (unsigned char*)malloc(*encrypted_len);
    if (!encrypted_data) {
        fprintf(stderr, "Memory allocation failed.\n");
        return NULL;
    }

    // Pad data to a multiple of the block size (16 bytes for AES)
    unsigned char* padded_data = (unsigned char*)malloc(data_len + 16);
    if (!padded_data) {
        free(encrypted_data);
        fprintf(stderr, "Memory allocation failed.\n");
        return NULL;
    }
    memcpy(padded_data, data, data_len);
    for (size_t i = data_len; i < *encrypted_len; i++) {
        padded_data[i] = 16 - (data_len % 16);
    }

    // Encrypt padded data
    for (size_t i = 0; i < *encrypted_len; i += 16) {
        AES_encrypt(padded_data + i, encrypted_data + i, &aes_key);
    }

    free(padded_data);

    return encrypted_data;
}

// Function to decrypt data using AES in ECB mode
unsigned char* decrypt_bytes_aes(const unsigned char* data, size_t data_len, const unsigned char* key, size_t key_len, size_t* decrypted_len) {
    // Check if key length is valid for AES
    if (key_len != 16 && key_len != 24 && key_len != 32) {
        fprintf(stderr, "Invalid key length. Key must be 128, 192, or 256 bits.\n");
        return NULL;
    }

    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, key_len * 8, &aes_key) != 0) {
        handle_openssl_error();
        return NULL;
    }

    // Decrypt data
    unsigned char* decrypted_data = (unsigned char*)malloc(data_len);
    if (!decrypted_data) {
        fprintf(stderr, "Memory allocation failed.\n");
        return NULL;
    }
    for (size_t i = 0; i < data_len; i += 16) {
        AES_decrypt(data + i, decrypted_data + i, &aes_key);
    }

    // Remove padding
    *decrypted_len = data_len;
    if (decrypted_data[data_len - 1] <= 16) {
        *decrypted_len -= decrypted_data[data_len - 1];
    }

    return decrypted_data;
}

int main() {
    // Example usage
    unsigned char key[32] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x30, 0x31};
    unsigned char data[16] = {0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};

    size_t encrypted_len;
    unsigned char* encrypted_data = encrypt_bytes_aes(data, 16, key, 32, &encrypted_len);
    if (encrypted_data) {
        printf("Encrypted data: ");
        for (size_t i = 0; i < encrypted_len; i++) {
            printf("%02x", encrypted_data[i]);
        }
        printf("\n");

        size_t decrypted_len;
        unsigned char* decrypted_data = decrypt_bytes_aes(encrypted_data, encrypted_len, key, 32, &decrypted_len);
        if (decrypted_data) {
            printf("Decrypted data: ");
            for (size_t i = 0; i < decrypted_len; i++) {
                printf("%02x", decrypted_data[i]);
            }
            printf("\n");

            free(decrypted_data);
        }

        free(encrypted_data);
    }

    return 0;
}