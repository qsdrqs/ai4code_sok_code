#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// Function to encrypt a message using AES in CTR mode
unsigned char* encrypt(const unsigned char* key, const char* plaintext, int* ciphertext_len) {
    AES_KEY aes_key;
    unsigned char iv[AES_BLOCK_SIZE];
    unsigned char* ciphertext;
    int plaintext_len = strlen(plaintext);

    // Allocate memory for ciphertext
    ciphertext = (unsigned char*)malloc(plaintext_len);

    // Generate a random IV
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error generating random IV\n");
        exit(1);
    }

    // Set up AES key for encryption
    if (AES_set_encrypt_key(key, 256, &aes_key) < 0) {
        fprintf(stderr, "Error setting encryption key\n");
        exit(1);
    }

    // Encrypt the plaintext
    AES_ctr128_encrypt((unsigned char*)plaintext, ciphertext, plaintext_len, &aes_key, iv, NULL, NULL);

    // Return the ciphertext and its length
    *ciphertext_len = plaintext_len;
    return ciphertext;
}

// Function to decrypt a ciphertext using AES in CTR mode
char* decrypt(const unsigned char* key, const unsigned char* ciphertext, int ciphertext_len) {
    AES_KEY aes_key;
    unsigned char iv[AES_BLOCK_SIZE];
    char* plaintext;

    // Allocate memory for plaintext
    plaintext = (char*)malloc(ciphertext_len + 1);

    // Generate a random IV (same as used during encryption)
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error generating random IV\n");
        exit(1);
    }

    // Set up AES key for decryption
    if (AES_set_encrypt_key(key, 256, &aes_key) < 0) {
        fprintf(stderr, "Error setting decryption key\n");
        exit(1);
    }

    // Decrypt the ciphertext
    AES_ctr128_encrypt(ciphertext, (unsigned char*)plaintext, ciphertext_len, &aes_key, iv, NULL, NULL);

    // Null-terminate the plaintext
    plaintext[ciphertext_len] = '\0';
    return plaintext;
}

// Test function
void test() {
    void round_trip_test() {
        const char* key = "This_key_for_demo_purposes_only!";
        const char* message = "Text may be any length you wish, no padding is required";
        int ciphertext_len;

        // Encrypt the message
        unsigned char* ciphertext = encrypt((unsigned char*)key, message, &ciphertext_len);

        // Decrypt the ciphertext
        char* decrypted_message = decrypt((unsigned char*)key, ciphertext, ciphertext_len);

        // Print the decrypted message
        printf("%s\n", decrypted_message);

        // Free allocated memory
        free(ciphertext);
        free(decrypted_message);
    }

    round_trip_test();
}

int main() {
    test();
    return 0;
}