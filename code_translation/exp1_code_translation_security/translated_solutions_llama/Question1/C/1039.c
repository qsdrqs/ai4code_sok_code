#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/rand.h>
#include <base64.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
} FernetKey;

// Function to generate a Fernet key
void generate_fernet_key(FernetKey* fernet_key) {
    if (RAND_bytes(fernet_key->key, 32) != 1) {
        fprintf(stderr, "Failed to generate random bytes\n");
        exit(1);
    }
}

// Function to encrypt a message using Fernet
unsigned char* encrypt_message(const unsigned char* message, size_t message_len, const FernetKey* fernet_key) {
    // Token version and timestamp (8 bytes)
    unsigned char token[4 + 8 + message_len + 32];
    memset(token, 0, sizeof(token));

    // Version (1 byte) and timestamp (7 bytes)
    token[0] = 0x80; // Version
    if (RAND_bytes(token + 1, 7) != 1) {
        fprintf(stderr, "Failed to generate random bytes\n");
        exit(1);
    }

    // Copy the message
    memcpy(token + 4 + 8, message, message_len);

    // HMAC-SHA256 of the token (without HMAC)
    unsigned char hmac[32];
    unsigned char* token_without_hmac = malloc(4 + 8 + message_len);
    memcpy(token_without_hmac, token, 4 + 8 + message_len);
    EVP_MD_CTX* md_ctx = EVP_MD_CTX_create();
    EVP_DigestInit_ex(md_ctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(md_ctx, fernet_key->key, 32);
    EVP_DigestUpdate(md_ctx, token_without_hmac, 4 + 8 + message_len);
    EVP_DigestFinal_ex(md_ctx, hmac, NULL);
    EVP_MD_CTX_destroy(md_ctx);
    free(token_without_hmac);

    // Copy HMAC to the token
    memcpy(token + 4 + 8 + message_len, hmac, 32);

    // Encrypt the token using AES-128-CBC
    unsigned char iv[AES_BLOCK_SIZE];
    if (RAND_bytes(iv, AES_BLOCK_SIZE) != 1) {
        fprintf(stderr, "Failed to generate random IV\n");
        exit(1);
    }

    AES_KEY aes_key;
    AES_set_encrypt_key(fernet_key->key, 128, &aes_key);
    unsigned char* encrypted_token = malloc(AES_BLOCK_SIZE + 4 + 8 + message_len + 32);
    AES_cbc_encrypt(token, encrypted_token + AES_BLOCK_SIZE, 4 + 8 + message_len + 32, &aes_key, iv, AES_ENCRYPT);

    // Prepend IV and base64 encode
    memcpy(encrypted_token, iv, AES_BLOCK_SIZE);
    size_t encoded_len = base64_encode_alloc(encrypted_token, AES_BLOCK_SIZE + 4 + 8 + message_len + 32, &encrypted_token);
    free(encrypted_token);

    return encrypted_token;
}

// Function to decrypt a message using Fernet
unsigned char* decrypt_message(const unsigned char* message, size_t message_len, const FernetKey* fernet_key) {
    // Base64 decode
    unsigned char* decoded_message = malloc(message_len);
    size_t decoded_len = base64_decode(message, message_len, &decoded_message);

    // Extract IV
    unsigned char iv[AES_BLOCK_SIZE];
    memcpy(iv, decoded_message, AES_BLOCK_SIZE);

    // Decrypt using AES-128-CBC
    AES_KEY aes_key;
    AES_set_decrypt_key(fernet_key->key, 128, &aes_key);
    unsigned char* token = malloc(decoded_len - AES_BLOCK_SIZE);
    AES_cbc_encrypt(decoded_message + AES_BLOCK_SIZE, token, decoded_len - AES_BLOCK_SIZE, &aes_key, iv, AES_DECRYPT);
    free(decoded_message);

    // Verify HMAC-SHA256
    unsigned char hmac[32];
    unsigned char* token_without_hmac = malloc(decoded_len - AES_BLOCK_SIZE - 32);
    memcpy(token_without_hmac, token, decoded_len - AES_BLOCK_SIZE - 32);
    EVP_MD_CTX* md_ctx = EVP_MD_CTX_create();
    EVP_DigestInit_ex(md_ctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(md_ctx, fernet_key->key, 32);
    EVP_DigestUpdate(md_ctx, token_without_hmac, decoded_len - AES_BLOCK_SIZE - 32);
    EVP_DigestFinal_ex(md_ctx, hmac, NULL);
    EVP_MD_CTX_destroy(md_ctx);
    free(token_without_hmac);

    if (memcmp(hmac, token + decoded_len - AES_BLOCK_SIZE - 32, 32) != 0) {
        fprintf(stderr, "HMAC verification failed\n");
        exit(1);
    }

    // Extract and return the message
    unsigned char* result = malloc(decoded_len - AES_BLOCK_SIZE - 32 - 8 - 4);
    memcpy(result, token + 8 + 4, decoded_len - AES_BLOCK_SIZE - 32 - 8 - 4);
    free(token);

    return result;
}

int main() {
    FernetKey fernet_key;
    generate_fernet_key(&fernet_key);

    const char* message = "Hello, World!";
    unsigned char* encrypted_message = encrypt_message((unsigned char*)message, strlen(message), &fernet_key);
    printf("Encrypted message: %s\n", encrypted_message);

    unsigned char* decrypted_message = decrypt_message(encrypted_message, strlen((char*)encrypted_message), &fernet_key);
    printf("Decrypted message: %s\n", decrypted_message);

    free(encrypted_message);
    free(decrypted_message);

    return 0;
}