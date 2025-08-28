#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Structure to represent AES cipher
typedef struct {
    unsigned char* key;
    int blk_sz;
} AEScipher;

// Function to create a new AES cipher
AEScipher* aes_cipher_new(unsigned char* key, int blk_sz) {
    AEScipher* cipher = (AEScipher*) malloc(sizeof(AEScipher));
    if (cipher == NULL) {
        return NULL;
    }
    cipher->key = (unsigned char*) malloc(strlen((const char*) key) + 1);
    if (cipher->key == NULL) {
        free(cipher);
        return NULL;
    }
    strcpy((char*) cipher->key, (const char*) key);
    cipher->blk_sz = blk_sz;
    return cipher;
}

// Function to free AES cipher
void aes_cipher_free(AEScipher* cipher) {
    if (cipher != NULL) {
        free(cipher->key);
        free(cipher);
    }
}

// Function to encrypt a message using AES
unsigned char* aes_encrypt(AEScipher* cipher, const unsigned char* msg, int* len) {
    // Calculate padding length
    int pad_len = cipher->blk_sz - (strlen((const char*) msg) % cipher->blk_sz);

    // Create padded message
    int msg_len = strlen((const char*) msg) + pad_len;
    unsigned char* padded_msg = (unsigned char*) malloc(msg_len + 1);
    if (padded_msg == NULL) {
        return NULL;
    }
    strcpy((char*) padded_msg, (const char*) msg);
    for (int i = 0; i < pad_len; i++) {
        padded_msg[msg_len - 1 - i] = pad_len;
    }
    padded_msg[msg_len] = '\0';

    // Encryption
    AES_KEY aes_key;
    if (AES_set_encrypt_key(cipher->key, 128, &aes_key) != 0) {
        free(padded_msg);
        return NULL;
    }

    unsigned char* encrypted_msg = (unsigned char*) malloc(msg_len);
    if (encrypted_msg == NULL) {
        free(padded_msg);
        return NULL;
    }
    AES_cbc_encrypt(padded_msg, encrypted_msg, msg_len, &aes_key, NULL, AES_ENCRYPT);

    // Update length
    *len = msg_len;

    free(padded_msg);
    return encrypted_msg;
}

// Function to decrypt a ciphertext using AES
unsigned char* aes_decrypt(AEScipher* cipher, const unsigned char* ciphertext, int len) {
    // Decryption
    AES_KEY aes_key;
    if (AES_set_decrypt_key(cipher->key, 128, &aes_key) != 0) {
        return NULL;
    }

    unsigned char* decrypted_msg = (unsigned char*) malloc(len);
    if (decrypted_msg == NULL) {
        return NULL;
    }
    AES_cbc_encrypt((unsigned char*) ciphertext, decrypted_msg, len, &aes_key, NULL, AES_DECRYPT);

    // Remove padding
    int pad_len = decrypted_msg[len - 1];
    unsigned char* unpadded_msg = (unsigned char*) malloc(len - pad_len);
    if (unpadded_msg == NULL) {
        free(decrypted_msg);
        return NULL;
    }
    strncpy((char*) unpadded_msg, (const char*) decrypted_msg, len - pad_len);
    unpadded_msg[len - pad_len] = '\0';

    free(decrypted_msg);
    return unpadded_msg;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Create AES cipher
    unsigned char key[] = "your_secret_key";
    AEScipher* cipher = aes_cipher_new(key, 16);

    // Encrypt a message
    const unsigned char* msg = "Hello, World!";
    int encrypted_len;
    unsigned char* encrypted_msg = aes_encrypt(cipher, msg, &encrypted_len);
    if (encrypted_msg != NULL) {
        printf("Encrypted message: ");
        for (int i = 0; i < encrypted_len; i++) {
            printf("%02x", encrypted_msg[i]);
        }
        printf("\n");
        free(encrypted_msg);
    }

    // Decrypt the ciphertext
    unsigned char* decrypted_msg = aes_decrypt(cipher, encrypted_msg, encrypted_len);
    if (decrypted_msg != NULL) {
        printf("Decrypted message: %s\n", decrypted_msg);
        free(decrypted_msg);
    }

    // Free AES cipher
    aes_cipher_free(cipher);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}