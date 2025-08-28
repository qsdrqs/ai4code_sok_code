#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/rand.h>

// Structure to hold the Fernet key
typedef struct {
    unsigned char key[32];
    unsigned char iv[16];
} FernetKey;

// Function to generate a Fernet key
int generate_fernet_key(FernetKey *key) {
    if (RAND_bytes(key->key, 32) != 1) {
        return 0;
    }
    // For Fernet, IV is a fixed value (first 128 bits of the key)
    memcpy(key->iv, key->key, 16);
    return 1;
}

// Function to encrypt text using Fernet
unsigned char* encrypt_text(const unsigned char *plain_text, size_t plain_text_len, FernetKey *key) {
    EVP_CIPHER_CTX *ctx;
    unsigned char *encrypted_text;
    int encrypted_text_len;

    // Allocate memory for the encrypted text
    encrypted_text = (unsigned char*)malloc(plain_text_len + EVP_MAX_BLOCK_LENGTH);
    if (!encrypted_text) {
        return NULL;
    }

    // Initialize the EVP context
    ctx = EVP_CIPHER_CTX_new();
    if (!EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, key->iv)) {
        EVP_CIPHER_CTX_free(ctx);
        free(encrypted_text);
        return NULL;
    }

    // Encrypt the plain text
    if (!EVP_EncryptUpdate(ctx, encrypted_text, &encrypted_text_len, plain_text, plain_text_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(encrypted_text);
        return NULL;
    }

    int final_len;
    if (!EVP_EncryptFinal_ex(ctx, encrypted_text + encrypted_text_len, &final_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(encrypted_text);
        return NULL;
    }

    encrypted_text_len += final_len;

    EVP_CIPHER_CTX_free(ctx);

    // Reallocate memory to the actual size of the encrypted text
    encrypted_text = realloc(encrypted_text, encrypted_text_len);
    if (!encrypted_text) {
        return NULL;
    }

    return encrypted_text;
}

// Function to decrypt text using Fernet
unsigned char* decrypt_text(const unsigned char *encrypted_text, size_t encrypted_text_len, FernetKey *key) {
    EVP_CIPHER_CTX *ctx;
    unsigned char *decrypted_text;
    int decrypted_text_len;

    // Allocate memory for the decrypted text
    decrypted_text = (unsigned char*)malloc(encrypted_text_len);
    if (!decrypted_text) {
        return NULL;
    }

    // Initialize the EVP context
    ctx = EVP_CIPHER_CTX_new();
    if (!EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, key->iv)) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted_text);
        return NULL;
    }

    // Decrypt the encrypted text
    if (!EVP_DecryptUpdate(ctx, decrypted_text, &decrypted_text_len, encrypted_text, encrypted_text_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted_text);
        return NULL;
    }

    int final_len;
    if (!EVP_DecryptFinal_ex(ctx, decrypted_text + decrypted_text_len, &final_len)) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted_text);
        return NULL;
    }

    decrypted_text_len += final_len;

    EVP_CIPHER_CTX_free(ctx);

    // Reallocate memory to the actual size of the decrypted text
    decrypted_text = realloc(decrypted_text, decrypted_text_len);
    if (!decrypted_text) {
        return NULL;
    }

    return decrypted_text;
}

int main() {
    FernetKey key;
    if (!generate_fernet_key(&key)) {
        printf("Failed to generate Fernet key\n");
        return 1;
    }

    const char *plain_text = "Hello, World!";
    size_t plain_text_len = strlen(plain_text);

    unsigned char *encrypted_text = encrypt_text((unsigned char*)plain_text, plain_text_len, &key);
    if (!encrypted_text) {
        printf("Failed to encrypt text\n");
        return 1;
    }

    printf("Encrypted text: ");
    for (size_t i = 0; i < strlen((char*)encrypted_text); i++) {
        printf("%c", encrypted_text[i]);
    }
    printf("\n");

    unsigned char *decrypted_text = decrypt_text(encrypted_text, strlen((char*)encrypted_text), &key);
    if (!decrypted_text) {
        printf("Failed to decrypt text\n");
        free(encrypted_text);
        return 1;
    }

    printf("Decrypted text: %s\n", decrypted_text);

    free(encrypted_text);
    free(decrypted_text);

    return 0;
}