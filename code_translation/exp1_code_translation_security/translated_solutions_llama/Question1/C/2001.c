#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
} FernetKey;

// Function to generate a Fernet key
void generate_fernet_key(FernetKey* key) {
    RAND_bytes(key->key, 32);
}

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
int encrypt(const unsigned char* msg, size_t msg_len, const FernetKey* key, unsigned char** ciphertext, size_t* ciphertext_len) {
    // Generate a random 128-bit IV.
    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);

    // Calculate the length of the ciphertext (IV + encrypted msg + padding).
    *ciphertext_len = AES_BLOCK_SIZE + (msg_len + AES_BLOCK_SIZE - 1) / AES_BLOCK_SIZE * AES_BLOCK_SIZE;

    // Allocate memory for the ciphertext.
    *ciphertext = (unsigned char*)malloc(*ciphertext_len);
    if (*ciphertext == NULL) {
        return 0; // Memory allocation failed.
    }

    // Copy the IV to the beginning of the ciphertext.
    memcpy(*ciphertext, iv, AES_BLOCK_SIZE);

    // Initialize AES encryption context.
    AES_KEY aes_key;
    AES_set_encrypt_key(key->key, 128, &aes_key);

    // Encrypt the message.
    unsigned char* encrypted_msg = *ciphertext + AES_BLOCK_SIZE;
    AES_cbc_encrypt(msg, encrypted_msg, msg_len, &aes_key, iv, AES_ENCRYPT);

    // Calculate HMAC.
    unsigned char hmac[EVP_MD_size(EVP_sha256())];
    unsigned char* hmac_input = (unsigned char*)malloc(*ciphertext_len + 1);
    if (hmac_input == NULL) {
        free(*ciphertext);
        return 0; // Memory allocation failed.
    }
    memcpy(hmac_input, *ciphertext, *ciphertext_len);
    HMAC(EVP_sha256(), key->key + 32, 32, hmac_input, *ciphertext_len, hmac, NULL);
    free(hmac_input);

    // Append HMAC to the ciphertext.
    memcpy(*ciphertext + *ciphertext_len, hmac, EVP_MD_size(EVP_sha256()));
    *ciphertext_len += EVP_MD_size(EVP_sha256());

    return 1; // Encryption successful.
}

// Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
int decrypt(const unsigned char* ciphertext, size_t ciphertext_len, const FernetKey* key, unsigned char** msg, size_t* msg_len) {
    // Check if the ciphertext is long enough to contain IV, encrypted msg, and HMAC.
    if (ciphertext_len <= AES_BLOCK_SIZE + EVP_MD_size(EVP_sha256())) {
        return 0; // Ciphertext too short.
    }

    // Extract IV, encrypted msg, and HMAC.
    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, ciphertext, AES_BLOCK_SIZE);
    size_t encrypted_msg_len = ciphertext_len - AES_BLOCK_SIZE - EVP_MD_size(EVP_sha256());
    unsigned char* encrypted_msg = (unsigned char*)ciphertext + AES_BLOCK_SIZE;

    // Verify HMAC.
    unsigned char hmac[EVP_MD_size(EVP_sha256())];
    unsigned char* hmac_input = (unsigned char*)malloc(ciphertext_len - EVP_MD_size(EVP_sha256()));
    if (hmac_input == NULL) {
        return 0; // Memory allocation failed.
    }
    memcpy(hmac_input, ciphertext, ciphertext_len - EVP_MD_size(EVP_sha256()));
    HMAC(EVP_sha256(), key->key + 32, 32, hmac_input, ciphertext_len - EVP_MD_size(EVP_sha256()), hmac, NULL);
    free(hmac_input);
    if (memcmp(hmac, ciphertext + ciphertext_len - EVP_MD_size(EVP_sha256()), EVP_MD_size(EVP_sha256())) != 0) {
        return 0; // HMAC verification failed.
    }

    // Initialize AES decryption context.
    AES_KEY aes_key;
    AES_set_decrypt_key(key->key, 128, &aes_key);

    // Decrypt the message.
    *msg_len = encrypted_msg_len;
    *msg = (unsigned char*)malloc(*msg_len);
    if (*msg == NULL) {
        return 0; // Memory allocation failed.
    }
    AES_cbc_encrypt(encrypted_msg, *msg, encrypted_msg_len, &aes_key, iv, AES_DECRYPT);

    // Remove padding.
    size_t padding_len = (*msg)[*msg_len - 1];
    if (padding_len > *msg_len) {
        free(*msg);
        return 0; // Invalid padding.
    }
    *msg_len -= padding_len;

    return 1; // Decryption successful.
}

int main() {
    FernetKey key;
    generate_fernet_key(&key);

    const char* msg = "Hello, World!";
    size_t msg_len = strlen(msg);

    unsigned char* ciphertext;
    size_t ciphertext_len;
    if (!encrypt((unsigned char*)msg, msg_len, &key, &ciphertext, &ciphertext_len)) {
        printf("Encryption failed.\n");
        return 1;
    }

    unsigned char* decrypted_msg;
    size_t decrypted_msg_len;
    if (!decrypt(ciphertext, ciphertext_len, &key, &decrypted_msg, &decrypted_msg_len)) {
        printf("Decryption failed.\n");
        free(ciphertext);
        return 1;
    }

    printf("Decrypted message: %.*s\n", (int)decrypted_msg_len, decrypted_msg);

    free(ciphertext);
    free(decrypted_msg);

    return 0;
}