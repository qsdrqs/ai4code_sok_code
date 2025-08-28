#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/aes.h>
#include <sys/stat.h>
#include <unistd.h>

#define KEY_SIZE 32
#define IV_SIZE 16
#define BUFFER_SIZE 1024

// Structure to hold encrypted data and its length
typedef struct {
    unsigned char* data;
    int length;
} EncryptedData;

// Generate a random key (equivalent to Fernet.generate_key())
void generate_key(unsigned char* key) {
    if (RAND_bytes(key, KEY_SIZE) != 1) {
        fprintf(stderr, "Error generating random key\n");
        exit(1);
    }
}

// Encrypt function
EncryptedData encrypt(const unsigned char* data, int data_len, const unsigned char* key) {
    EVP_CIPHER_CTX* ctx;
    EncryptedData result;
    unsigned char iv[IV_SIZE];
    int len;
    int ciphertext_len;
    
    // Generate random IV
    if (RAND_bytes(iv, IV_SIZE) != 1) {
        fprintf(stderr, "Error generating IV\n");
        exit(1);
    }
    
    // Allocate memory for encrypted data (IV + ciphertext)
    result.data = malloc(IV_SIZE + data_len + AES_BLOCK_SIZE);
    if (!result.data) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    
    // Copy IV to the beginning of result
    memcpy(result.data, iv, IV_SIZE);
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        exit(1);
    }
    
    // Initialize encryption operation
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Error initializing encryption\n");
        exit(1);
    }
    
    // Encrypt the data
    if (EVP_EncryptUpdate(ctx, result.data + IV_SIZE, &len, data, data_len) != 1) {
        fprintf(stderr, "Error during encryption\n");
        exit(1);
    }
    ciphertext_len = len;
    
    // Finalize encryption
    if (EVP_EncryptFinal_ex(ctx, result.data + IV_SIZE + len, &len) != 1) {
        fprintf(stderr, "Error finalizing encryption\n");
        exit(1);
    }
    ciphertext_len += len;
    
    result.length = IV_SIZE + ciphertext_len;
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

// Decrypt function
EncryptedData decrypt(const unsigned char* encrypted_data, int encrypted_len, const unsigned char* key) {
    EVP_CIPHER_CTX* ctx;
    EncryptedData result;
    unsigned char iv[IV_SIZE];
    int len;
    int plaintext_len;
    
    if (encrypted_len < IV_SIZE) {
        fprintf(stderr, "Invalid encrypted data length\n");
        exit(1);
    }
    
    // Extract IV from encrypted data
    memcpy(iv, encrypted_data, IV_SIZE);
    
    // Allocate memory for decrypted data
    result.data = malloc(encrypted_len - IV_SIZE + AES_BLOCK_SIZE);
    if (!result.data) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    
    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        fprintf(stderr, "Error creating cipher context\n");
        exit(1);
    }
    
    // Initialize decryption operation
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv) != 1) {
        fprintf(stderr, "Error initializing decryption\n");
        exit(1);
    }
    
    // Decrypt the data
    if (EVP_DecryptUpdate(ctx, result.data, &len, encrypted_data + IV_SIZE, encrypted_len - IV_SIZE) != 1) {
        fprintf(stderr, "Error during decryption\n");
        exit(1);
    }
    plaintext_len = len;
    
    // Finalize decryption
    if (EVP_DecryptFinal_ex(ctx, result.data + len, &len) != 1) {
        fprintf(stderr, "Error finalizing decryption\n");
        exit(1);
    }
    plaintext_len += len;
    
    result.length = plaintext_len;
    
    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    
    return result;
}

// Read file content
unsigned char* read_file(const char* filename, long* file_size) {
    FILE* file = fopen(filename, "rb");
    if (!file) {
        fprintf(stderr, "Error opening file: %s\n", filename);
        return NULL;
    }
    
    fseek(file, 0, SEEK_END);
    *file_size = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    unsigned char* buffer = malloc(*file_size);
    if (!buffer) {
        fprintf(stderr, "Memory allocation failed\n");
        fclose(file);
        return NULL;
    }
    
    fread(buffer, 1, *file_size, file);
    fclose(file);
    
    return buffer;
}

// Encrypt file (modified to match the Python function signature)
void encrypt_file(const char* filename, const unsigned char* key) {
    long file_size;
    unsigned char* file_data = read_file(filename, &file_size);
    
    if (!file_data) {
        fprintf(stderr, "Failed to read file: %s\n", filename);
        return;
    }
    
    EncryptedData encrypted = encrypt(file_data, file_size, key);
    
    // Write encrypted data to a new file
    char encrypted_filename[256];
    snprintf(encrypted_filename, sizeof(encrypted_filename), "%s.encrypted", filename);
    
    FILE* encrypted_file = fopen(encrypted_filename, "wb");
    if (encrypted_file) {
        fwrite(encrypted.data, 1, encrypted.length, encrypted_file);
        fclose(encrypted_file);
        printf("File encrypted and saved as: %s\n", encrypted_filename);
    } else {
        fprintf(stderr, "Error creating encrypted file\n");
    }
    
    // Clean up
    free(file_data);
    free(encrypted.data);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    // Generate a key
    unsigned char key[KEY_SIZE];
    generate_key(key);
    
    // Print key in hex format for reference
    printf("Generated key: ");
    for (int i = 0; i < KEY_SIZE; i++) {
        printf("%02x", key[i]);
    }
    printf("\n");
    
    // Encrypt the file
    encrypt_file("/safedir/test.txt", key);
    
    // Clean up OpenSSL
    EVP_cleanup();
    
    return 0;
}