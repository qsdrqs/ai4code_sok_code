#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Structure to hold encrypted data
typedef struct {
    unsigned char nonce[AES_BLOCK_SIZE];
    unsigned char tag[AES_BLOCK_SIZE];
    unsigned char* ciphertext;
    size_t ciphertext_len;
} encrypted_data;

// Function to encrypt a given string using a given symmetric key
encrypted_data* encrypt(const unsigned char* key, const unsigned char* plaintext, size_t plaintext_len) {
    // Initialize variables
    AES_KEY aes_key;
    unsigned char nonce[AES_BLOCK_SIZE];
    unsigned char* ciphertext = NULL;
    unsigned char tag[AES_BLOCK_SIZE];
    encrypted_data* data = NULL;

    // Check if key length is valid
    if (AES_set_encrypt_key(key, 128, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES key\n");
        goto cleanup;
    }

    // Generate a random nonce
    if (RAND_bytes(nonce, AES_BLOCK_SIZE) != 1) {
        fprintf(stderr, "Failed to generate random nonce\n");
        goto cleanup;
    }

    // Initialize ciphertext length
    size_t ciphertext_len = plaintext_len;
    if ((ciphertext = malloc(ciphertext_len)) == NULL) {
        fprintf(stderr, "Failed to allocate memory for ciphertext\n");
        goto cleanup;
    }

    // Encrypt plaintext
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, nonce) != 1) {
        fprintf(stderr, "Failed to initialize EVP encryption context\n");
        goto cleanup;
    }

    int out_len;
    if (EVP_EncryptUpdate(ctx, ciphertext, &out_len, plaintext, plaintext_len) != 1) {
        fprintf(stderr, "Failed to encrypt plaintext\n");
        goto cleanup;
    }

    // Finalize encryption
    int final_len;
    if (EVP_EncryptFinal_ex(ctx, ciphertext + out_len, &final_len) != 1) {
        fprintf(stderr, "Failed to finalize encryption\n");
        goto cleanup;
    }

    // Compute tag using HMAC
    unsigned char hmac_key[AES_BLOCK_SIZE];
    memcpy(hmac_key, key, AES_BLOCK_SIZE);
    unsigned char hmac[AES_BLOCK_SIZE];
    unsigned char* hmac_input = malloc(nonce + AES_BLOCK_SIZE + ciphertext_len);
    memcpy(hmac_input, nonce, AES_BLOCK_SIZE);
    memcpy(hmac_input + AES_BLOCK_SIZE, ciphertext, ciphertext_len);
    unsigned int hmac_len = AES_BLOCK_SIZE;
    HMAC(EVP_sha256(), hmac_key, AES_BLOCK_SIZE, hmac_input, AES_BLOCK_SIZE + ciphertext_len, hmac, &hmac_len);

    // Create encrypted data structure
    data = malloc(sizeof(encrypted_data));
    memcpy(data->nonce, nonce, AES_BLOCK_SIZE);
    memcpy(data->tag, hmac, AES_BLOCK_SIZE);
    data->ciphertext = ciphertext;
    data->ciphertext_len = ciphertext_len + out_len + final_len;

cleanup:
    EVP_CIPHER_CTX_free(ctx);
    if (data == NULL) {
        free(ciphertext);
    }
    return data;
}

// Function to decrypt a given string using a given symmetric key
unsigned char* decrypt(const unsigned char* key, encrypted_data* data) {
    // Initialize variables
    AES_KEY aes_key;
    unsigned char* plaintext = NULL;

    // Check if key length is valid
    if (AES_set_decrypt_key(key, 128, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES key\n");
        goto cleanup;
    }

    // Decrypt ciphertext
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, data->nonce) != 1) {
        fprintf(stderr, "Failed to initialize EVP decryption context\n");
        goto cleanup;
    }

    plaintext = malloc(data->ciphertext_len);
    int out_len;
    if (EVP_DecryptUpdate(ctx, plaintext, &out_len, data->ciphertext, data->ciphertext_len) != 1) {
        fprintf(stderr, "Failed to decrypt ciphertext\n");
        goto cleanup;
    }

    // Finalize decryption
    int final_len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + out_len, &final_len) != 1) {
        fprintf(stderr, "Failed to finalize decryption\n");
        goto cleanup;
    }

    // Verify tag using HMAC
    unsigned char hmac_key[AES_BLOCK_SIZE];
    memcpy(hmac_key, key, AES_BLOCK_SIZE);
    unsigned char hmac[AES_BLOCK_SIZE];
    unsigned char* hmac_input = malloc(AES_BLOCK_SIZE + data->ciphertext_len);
    memcpy(hmac_input, data->nonce, AES_BLOCK_SIZE);
    memcpy(hmac_input + AES_BLOCK_SIZE, data->ciphertext, data->ciphertext_len);
    unsigned int hmac_len = AES_BLOCK_SIZE;
    HMAC(EVP_sha256(), hmac_key, AES_BLOCK_SIZE, hmac_input, AES_BLOCK_SIZE + data->ciphertext_len, hmac, &hmac_len);

    if (memcmp(hmac, data->tag, AES_BLOCK_SIZE) != 0) {
        fprintf(stderr, "HMAC verification failed\n");
        goto cleanup;
    }

cleanup:
    EVP_CIPHER_CTX_free(ctx);
    return plaintext;
}

int main() {
    // Example usage
    unsigned char key[AES_BLOCK_SIZE];
    RAND_bytes(key, AES_BLOCK_SIZE);

    const char* plaintext = "Hello, World!";
    unsigned char* plaintext_bytes = (unsigned char*) plaintext;
    size_t plaintext_len = strlen(plaintext);

    encrypted_data* encrypted = encrypt(key, plaintext_bytes, plaintext_len);
    if (encrypted == NULL) {
        return 1;
    }

    unsigned char* decrypted = decrypt(key, encrypted);
    if (decrypted == NULL) {
        free(encrypted->ciphertext);
        free(encrypted);
        return 1;
    }

    printf("Decrypted: %s\n", decrypted);

    free(encrypted->ciphertext);
    free(encrypted);
    free(decrypted);

    return 0;
}