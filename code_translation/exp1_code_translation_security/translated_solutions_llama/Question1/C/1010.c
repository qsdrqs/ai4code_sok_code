#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// Structure to hold AES encryption data
typedef struct {
    unsigned char *key;
    unsigned char *nonce;
    unsigned char *ciphertext;
    unsigned char *tag;
} aes_data;

// Function to generate a symmetric key
unsigned char* generate_key() {
    unsigned char *key = (unsigned char*) malloc(16 * sizeof(unsigned char));
    if (RAND_bytes(key, 16) != 1) {
        printf("Error generating random key\n");
        exit(1);
    }
    return key;
}

// Function to encrypt a string using a symmetric key
aes_data* encrypt(unsigned char *key, const char *data) {
    aes_data *aes = (aes_data*) malloc(sizeof(aes_data));
    aes->key = key;
    aes->nonce = (unsigned char*) malloc(16 * sizeof(unsigned char));
    if (RAND_bytes(aes->nonce, 16) != 1) {
        printf("Error generating random nonce\n");
        exit(1);
    }

    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, 128, &aes_key) != 0) {
        printf("Error setting AES encrypt key\n");
        exit(1);
    }

    // Convert data to bytes and calculate length
    int data_len = strlen(data);
    unsigned char *data_bytes = (unsigned char*) malloc((data_len + 16) * sizeof(unsigned char));
    memset(data_bytes, 0, data_len + 16);
    memcpy(data_bytes, data, data_len);

    // Allocate memory for ciphertext and tag
    aes->ciphertext = (unsigned char*) malloc((data_len + 16) * sizeof(unsigned char));
    aes->tag = (unsigned char*) malloc(16 * sizeof(unsigned char));

    // Perform AES encryption
    AES_cbc_encrypt(data_bytes, aes->ciphertext, data_len + 16, &aes_key, aes->nonce, AES_ENCRYPT);

    // Free temporary data
    free(data_bytes);

    return aes;
}

// Function to decrypt a string
unsigned char* decrypt(unsigned char *key, unsigned char *nonce, unsigned char *ciphertext, int len) {
    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, 128, &aes_key) != 0) {
        printf("Error setting AES decrypt key\n");
        exit(1);
    }

    // Allocate memory for decrypted data
    unsigned char *decrypted_data = (unsigned char*) malloc(len * sizeof(unsigned char));

    // Perform AES decryption
    AES_cbc_encrypt(ciphertext, decrypted_data, len, &aes_key, nonce, AES_DECRYPT);

    return decrypted_data;
}

int main() {
    // Generate a symmetric key
    unsigned char *key = generate_key();

    // Test data
    const char *test_data = "Jim's test";

    // Encrypt the test data
    aes_data *aes = encrypt(key, test_data);

    // Decrypt the test data
    unsigned char *restored_data = decrypt(key, aes->nonce, aes->ciphertext, strlen(test_data) + 16);

    // Print the restored data
    printf("%s\n", restored_data);

    // Free allocated memory
    free(key);
    free(aes->nonce);
    free(aes->ciphertext);
    free(aes->tag);
    free(restored_data);
    free(aes);

    return 0;
}