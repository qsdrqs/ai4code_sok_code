#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/fernet.h>
#include <base64.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
} fernet_key_t;

// Function to derive a secret key from a given password and salt
unsigned char* _derive_key(const unsigned char* password, size_t password_len,
                           const unsigned char* salt, size_t salt_len,
                           int iterations, size_t* key_len) {
    EVP_MD_CTX* ctx = EVP_MD_CTX_create();
    EVP_PBE_scrypt(password, password_len, salt, salt_len, iterations, 1, 32, NULL, key_len, ctx);
    unsigned char* key = (unsigned char*)malloc(*key_len);
    EVP_PBE_scrypt(password, password_len, salt, salt_len, iterations, 1, 32, key, key_len, ctx);
    EVP_MD_CTX_destroy(ctx);
    return key;
}

// Function to encrypt a message
unsigned char* encrypt(const char* message, const char* password, int iterations, size_t* token_len) {
    // Generate random salt
    unsigned char salt[16];
    FILE* urandom = fopen("/dev/urandom", "r");
    fread(salt, 1, 16, urandom);
    fclose(urandom);

    // Derive key
    size_t key_len;
    unsigned char* key = _derive_key((unsigned char*)password, strlen(password),
                                     salt, 16, iterations, &key_len);

    // Create Fernet object
    fernet_key_t fernet_key;
    memcpy(fernet_key.key, key, 32);
    free(key);

    // Encrypt message
    unsigned char* encrypted_message = (unsigned char*)malloc(strlen(message) + 1);
    strcpy((char*)encrypted_message, message);
    size_t encrypted_len = strlen((char*)encrypted_message);
    unsigned char* fernet_encrypted = fernet_encrypt(fernet_key.key, encrypted_message, encrypted_len, NULL);

    // Prepare token
    size_t token_size = 16 + 4 + base64_encoded_size(fernet_encrypted);
    unsigned char* token = (unsigned char*)malloc(token_size);
    memcpy(token, salt, 16);
    uint32_t iter = htonl(iterations);
    memcpy(token + 16, &iter, 4);
    size_t b64_len;
    unsigned char* b64_token = base64_encode_alloc(fernet_encrypted, base64_encoded_size(fernet_encrypted), &b64_len);
    memcpy(token + 20, b64_token, b64_len);
    free(b64_token);
    free(fernet_encrypted);

    *token_len = token_size;
    return token;
}

// Function to decrypt a token
unsigned char* decrypt(const unsigned char* token, size_t token_len, const char* password, size_t* decrypted_len) {
    // Extract salt, iterations, and encrypted token
    unsigned char salt[16];
    memcpy(salt, token, 16);
    uint32_t iterations = ntohl(*(uint32_t*)(token + 16));
    size_t b64_len = token_len - 20;
    unsigned char* b64_token = (unsigned char*)malloc(b64_len + 1);
    memcpy(b64_token, token + 20, b64_len);
    b64_token[b64_len] = '\0';

    // Derive key
    size_t key_len;
    unsigned char* key = _derive_key((unsigned char*)password, strlen(password),
                                     salt, 16, iterations, &key_len);

    // Create Fernet object
    fernet_key_t fernet_key;
    memcpy(fernet_key.key, key, 32);
    free(key);

    // Decrypt token
    size_t decoded_len;
    unsigned char* decoded_token = base64_decode_alloc(b64_token, &decoded_len);
    free(b64_token);
    unsigned char* decrypted_message = fernet_decrypt(fernet_key.key, decoded_token, decoded_len, decrypted_len);
    free(decoded_token);

    return decrypted_message;
}

int main() {
    const char* message = "nice123";
    const char* password = "password123";
    int iterations = 100000;

    size_t token_len;
    unsigned char* token = encrypt(message, password, iterations, &token_len);

    printf("Token: ");
    for (size_t i = 0; i < token_len; i++) {
        printf("%02x", token[i]);
    }
    printf("\n");

    size_t decrypted_len;
    unsigned char* decrypted_message = decrypt(token, token_len, password, &decrypted_len);

    printf("Decrypted Message: %.*s\n", (int)decrypted_len, decrypted_message);

    free(token);
    free(decrypted_message);

    return 0;
}