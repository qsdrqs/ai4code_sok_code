#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

// Function to generate a random 32-byte key
void generate_key(unsigned char *key) {
    if (RAND_bytes(key, 32) != 1) {
        fprintf(stderr, "Failed to generate random key\n");
        exit(1);
    }
}

// Function to encrypt data using AES-128-CBC
int encrypt(const char *input_file, const char *output_file, const unsigned char *key) {
    // Initialize variables
    FILE *in_file = fopen(input_file, "rb");
    FILE *out_file = fopen(output_file, "wb");
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();

    if (!in_file || !out_file || !ctx) {
        fprintf(stderr, "Failed to open files or allocate memory\n");
        return 1;
    }

    // Set up AES-128-CBC encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, NULL) != 1) {
        fprintf(stderr, "Failed to initialize encryption\n");
        return 1;
    }

    // Read input file and encrypt
    unsigned char buffer[4096];
    int len;
    while ((len = fread(buffer, 1, 4096, in_file)) > 0) {
        int encrypted_len;
        if (EVP_EncryptUpdate(ctx, NULL, &encrypted_len, buffer, len) != 1) {
            fprintf(stderr, "Failed to encrypt data\n");
            return 1;
        }
        unsigned char *encrypted_buffer = malloc(encrypted_len);
        if (!encrypted_buffer) {
            fprintf(stderr, "Failed to allocate memory for encrypted buffer\n");
            return 1;
        }
        if (EVP_EncryptUpdate(ctx, encrypted_buffer, &encrypted_len, buffer, len) != 1) {
            fprintf(stderr, "Failed to encrypt data\n");
            free(encrypted_buffer);
            return 1;
        }
        fwrite(encrypted_buffer, 1, encrypted_len, out_file);
        free(encrypted_buffer);
    }

    // Finalize encryption
    int encrypted_len;
    if (EVP_EncryptFinal_ex(ctx, NULL, &encrypted_len) != 1) {
        fprintf(stderr, "Failed to finalize encryption\n");
        return 1;
    }

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    fclose(in_file);
    fclose(out_file);

    return 0;
}

// Function to decrypt data using AES-128-CBC
int decrypt(const char *input_file, const char *output_file, const unsigned char *key) {
    // Initialize variables
    FILE *in_file = fopen(input_file, "rb");
    FILE *out_file = fopen(output_file, "wb");
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();

    if (!in_file || !out_file || !ctx) {
        fprintf(stderr, "Failed to open files or allocate memory\n");
        return 1;
    }

    // Set up AES-128-CBC decryption
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, NULL) != 1) {
        fprintf(stderr, "Failed to initialize decryption\n");
        return 1;
    }

    // Read input file and decrypt
    unsigned char buffer[4096];
    int len;
    while ((len = fread(buffer, 1, 4096, in_file)) > 0) {
        int decrypted_len;
        if (EVP_DecryptUpdate(ctx, NULL, &decrypted_len, buffer, len) != 1) {
            fprintf(stderr, "Failed to decrypt data\n");
            return 1;
        }
        unsigned char *decrypted_buffer = malloc(decrypted_len);
        if (!decrypted_buffer) {
            fprintf(stderr, "Failed to allocate memory for decrypted buffer\n");
            return 1;
        }
        if (EVP_DecryptUpdate(ctx, decrypted_buffer, &decrypted_len, buffer, len) != 1) {
            fprintf(stderr, "Failed to decrypt data\n");
            free(decrypted_buffer);
            return 1;
        }
        fwrite(decrypted_buffer, 1, decrypted_len, out_file);
        free(decrypted_buffer);
    }

    // Finalize decryption
    int decrypted_len;
    if (EVP_DecryptFinal_ex(ctx, NULL, &decrypted_len) != 1) {
        fprintf(stderr, "Failed to finalize decryption\n");
        return 1;
    }

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    fclose(in_file);
    fclose(out_file);

    return 0;
}

int main() {
    // Generate a random 32-byte key
    unsigned char key[32];
    generate_key(key);

    // Encrypt a file
    const char *input_file = "/safedir/test.txt";
    const char *encrypted_file = "/safedir/test.txt.enc";
    if (encrypt(input_file, encrypted_file, key) != 0) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    // Decrypt the file
    const char *decrypted_file = "/safedir/test.txt.dec";
    if (decrypt(encrypted_file, decrypted_file, key) != 0) {
        fprintf(stderr, "Decryption failed\n");
        return 1;
    }

    return 0;
}