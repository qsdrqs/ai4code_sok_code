#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Function to handle AES encryption
unsigned char* encrypt(const unsigned char* k, const char* m, int* ct_len) {
    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(k, 256, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES encryption key\n");
        exit(1);
    }

    // Calculate the length of the input message
    int m_len = strlen(m);

    // Allocate memory for the encrypted message
    unsigned char* ct = (unsigned char*)malloc(m_len + AES_BLOCK_SIZE);
    if (ct == NULL) {
        fprintf(stderr, "Failed to allocate memory for encrypted message\n");
        exit(1);
    }

    // Initialize the IV (Initialization Vector)
    unsigned char iv[AES_BLOCK_SIZE];
    memset(iv, 0, AES_BLOCK_SIZE);

    // Encrypt the message
    AES_cbc_encrypt((const unsigned char*)m, ct, m_len, &aes_key, iv, AES_ENCRYPT);

    // Update the length of the encrypted message
    *ct_len = m_len + AES_BLOCK_SIZE;

    return ct;
}

// Function to handle AES decryption
char* decrypt(const unsigned char* k, const unsigned char* ct, int ct_len) {
    // Initialize AES context
    AES_KEY aes_key;
    if (AES_set_decrypt_key(k, 256, &aes_key) != 0) {
        fprintf(stderr, "Failed to set AES decryption key\n");
        exit(1);
    }

    // Allocate memory for the decrypted message
    char* m = (char*)malloc(ct_len);
    if (m == NULL) {
        fprintf(stderr, "Failed to allocate memory for decrypted message\n");
        exit(1);
    }

    // Initialize the IV (Initialization Vector)
    unsigned char iv[AES_BLOCK_SIZE];
    memset(iv, 0, AES_BLOCK_SIZE);

    // Decrypt the message
    AES_cbc_encrypt(ct, (unsigned char*)m, ct_len, &aes_key, iv, AES_DECRYPT);

    return m;
}

int main() {
    // Define a round-trip test function
    void round_trip_test() {
        const char* key = "This_key_for_demo_purposes_only!";
        const char* message = "Text may be any length you wish, no padding is required";

        // Encrypt the message
        int ct_len;
        unsigned char* ct = encrypt((const unsigned char*)key, message, &ct_len);

        // Decrypt the message
        char* decrypted_message = decrypt((const unsigned char*)key, ct, ct_len);

        // Print the decrypted message
        printf("%s\n", decrypted_message);

        // Free allocated memory
        free(ct);
        free(decrypted_message);
    }

    // Run the round-trip test
    round_trip_test();

    return 0;
}