#include <openssl/aes.h>
#include <openssl/rand.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

// Function to pad the message to a multiple of AES block size
unsigned char* pad(const unsigned char* message, int message_len, int block_size, int* padded_len) {
    int padding = block_size - (message_len % block_size);
    *padded_len = message_len + padding;

    unsigned char* padded_message = (unsigned char*)malloc(*padded_len);
    memcpy(padded_message, message, message_len);

    // Add padding bytes
    for (int i = message_len; i < *padded_len; i++) {
        padded_message[i] = padding;
    }

    return padded_message;
}

// Function to unpad the message
unsigned char* unpad(const unsigned char* padded_message, int padded_len, int* unpadded_len) {
    int padding = padded_message[padded_len - 1];
    *unpadded_len = padded_len - padding;

    unsigned char* unpadded_message = (unsigned char*)malloc(*unpadded_len);
    memcpy(unpadded_message, padded_message, *unpadded_len);

    return unpadded_message;
}

// Function to encrypt a message
unsigned char* encrypt(const char* message, const unsigned char* key, unsigned char* iv, int* ciphertext_len) {
    int message_len = strlen(message);
    int padded_len;
    unsigned char* padded_message = pad((unsigned char*)message, message_len, AES_BLOCK_SIZE, &padded_len);

    // Allocate memory for ciphertext
    unsigned char* ciphertext = (unsigned char*)malloc(padded_len);

    // Create AES cipher context
    AES_KEY encrypt_key;
    AES_set_encrypt_key(key, 128, &encrypt_key);

    // Encrypt the message
    AES_cbc_encrypt(padded_message, ciphertext, padded_len, &encrypt_key, iv, AES_ENCRYPT);

    *ciphertext_len = padded_len;

    free(padded_message);
    return ciphertext;
}

// Function to decrypt a ciphertext
char* decrypt(const unsigned char* ciphertext, int ciphertext_len, const unsigned char* key, unsigned char* iv) {
    // Allocate memory for decrypted message
    unsigned char* decrypted_message = (unsigned char*)malloc(ciphertext_len);

    // Create AES cipher context
    AES_KEY decrypt_key;
    AES_set_decrypt_key(key, 128, &decrypt_key);

    // Decrypt the ciphertext
    AES_cbc_encrypt(ciphertext, decrypted_message, ciphertext_len, &decrypt_key, iv, AES_DECRYPT);

    // Unpad the decrypted message
    int unpadded_len;
    unsigned char* unpadded_message = unpad(decrypted_message, ciphertext_len, &unpadded_len);

    // Convert to string
    char* message = (char*)malloc(unpadded_len + 1);
    memcpy(message, unpadded_message, unpadded_len);
    message[unpadded_len] = '\0';

    free(decrypted_message);
    free(unpadded_message);
    return message;
}

int main() {
    // Example usage
    const char* message = "Hello, World!";
    const unsigned char key[16] = "thisisakey123456"; // 16-byte key
    unsigned char iv[AES_BLOCK_SIZE];

    // Generate random IV
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) {
        fprintf(stderr, "Error generating random IV\n");
        return 1;
    }

    // Encrypt the message
    int ciphertext_len;
    unsigned char* ciphertext = encrypt(message, key, iv, &ciphertext_len);

    printf("Ciphertext: ");
    for (int i = 0; i < ciphertext_len; i++) {
        printf("%02x", ciphertext[i]);
    }
    printf("\n");

    // Decrypt the ciphertext
    char* decrypted_message = decrypt(ciphertext, ciphertext_len, key, iv);

    printf("Decrypted message: %s\n", decrypted_message);

    // Free allocated memory
    free(ciphertext);
    free(decrypted_message);

    return 0;
}