#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/base64.h>
#include <openssl/rand.h>
#include <openssl/evp.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
    unsigned char iv[AES_BLOCK_SIZE];
} FernetKey;

// Function to generate a Fernet key
void generate_key(FernetKey* key) {
    RAND_bytes(key->key, 32);
}

// Function to encrypt input using Fernet
unsigned char* encrypt(const unsigned char* input, size_t input_len, const FernetKey* key) {
    // Create a Fernet token
    unsigned char* token = (unsigned char*)malloc(input_len + 128);
    if (!token) {
        return NULL;
    }

    // Timestamp (8 bytes)
    unsigned char timestamp[8];
    time_t now = time(NULL);
    memcpy(timestamp, &now, 8);

    // Version (1 byte)
    unsigned char version = 0x80;

    // Ciphertext
    AES_KEY aes_key;
    AES_set_encrypt_key(key->key, 128, &aes_key);
    unsigned char* ciphertext = (unsigned char*)malloc(input_len);
    if (!ciphertext) {
        free(token);
        return NULL;
    }
    AES_cbc_encrypt(input, ciphertext, input_len, &aes_key, key->iv, AES_ENCRYPT);

    // Create the Fernet token
    unsigned char* token_ptr = token;
    // Version
    *token_ptr++ = version;
    // Timestamp
    memcpy(token_ptr, timestamp, 8);
    token_ptr += 8;
    // Ciphertext length (4 bytes)
    uint32_t length = htonl(input_len);
    memcpy(token_ptr, &length, 4);
    token_ptr += 4;
    // HMAC (32 bytes)
    unsigned char hmac[32];
    unsigned char* hmac_input = (unsigned char*)malloc(41 + input_len);
    if (!hmac_input) {
        free(token);
        free(ciphertext);
        return NULL;
    }
    memcpy(hmac_input, key->key, 32);
    memcpy(hmac_input + 32, token, token_ptr - token);
    memcpy(hmac_input + 32 + token_ptr - token, ciphertext, input_len);
    EVP_MD_CTX* mdctx = EVP_MD_CTX_create();
    EVP_DigestInit_ex(mdctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(mdctx, hmac_input, 32 + token_ptr - token + input_len);
    EVP_DigestFinal_ex(mdctx, hmac, NULL);
    EVP_MD_CTX_destroy(mdctx);
    free(hmac_input);
    memcpy(token_ptr, hmac, 32);
    token_ptr += 32;
    // Base64 encode
    size_t encoded_len = base64_encode_alloc(token, token_ptr - token, &token);
    free(token);
    token = (unsigned char*)malloc(encoded_len);
    if (!token) {
        free(ciphertext);
        return NULL;
    }
    base64_encode_alloc(token_ptr - (token_ptr - token), token_ptr - token, &token);

    free(ciphertext);
    return token;
}

// Function to decrypt input using Fernet
unsigned char* decrypt(const unsigned char* input, size_t input_len, const FernetKey* key) {
    // Base64 decode
    size_t decoded_len;
    unsigned char* decoded_input = base64_decode(input, input_len, &decoded_len);
    if (!decoded_input) {
        return NULL;
    }

    // Check HMAC
    unsigned char hmac[32];
    unsigned char* hmac_input = (unsigned char*)malloc(decoded_len - 32 + 32);
    if (!hmac_input) {
        free(decoded_input);
        return NULL;
    }
    memcpy(hmac_input, key->key, 32);
    memcpy(hmac_input + 32, decoded_input, decoded_len - 32);
    EVP_MD_CTX* mdctx = EVP_MD_CTX_create();
    EVP_DigestInit_ex(mdctx, EVP_sha256(), NULL);
    EVP_DigestUpdate(mdctx, hmac_input, decoded_len);
    EVP_DigestFinal_ex(mdctx, hmac, NULL);
    EVP_MD_CTX_destroy(mdctx);
    free(hmac_input);
    if (memcmp(hmac, decoded_input + decoded_len - 32, 32) != 0) {
        free(decoded_input);
        return NULL;
    }

    // Extract timestamp, version, and ciphertext length
    unsigned char* decoded_input_ptr = decoded_input;
    unsigned char version = *decoded_input_ptr++;
    if (version != 0x80) {
        free(decoded_input);
        return NULL;
    }
    decoded_input_ptr += 8; // Skip timestamp
    uint32_t length = ntohl(*(uint32_t*)decoded_input_ptr);
    decoded_input_ptr += 4;

    // Decrypt ciphertext
    AES_KEY aes_key;
    AES_set_decrypt_key(key->key, 128, &aes_key);
    unsigned char* plaintext = (unsigned char*)malloc(length);
    if (!plaintext) {
        free(decoded_input);
        return NULL;
    }
    AES_cbc_encrypt(decoded_input_ptr, plaintext, length, &aes_key, key->iv, AES_DECRYPT);

    free(decoded_input);
    return plaintext;
}

int main() {
    FernetKey key;
    generate_key(&key);
    RAND_bytes(key.iv, AES_BLOCK_SIZE);

    const unsigned char* input = (const unsigned char*)"super secret";
    size_t input_len = strlen((const char*)input);

    unsigned char* encrypted_input = encrypt(input, input_len, &key);
    if (!encrypted_input) {
        printf("Encryption failed\n");
        return 1;
    }

    unsigned char* decrypted_input = decrypt(encrypted_input, strlen((const char*)encrypted_input), &key);
    if (!decrypted_input) {
        printf("Decryption failed\n");
        free(encrypted_input);
        return 1;
    }

    printf("Original input: %s\n", input);
    printf("Decrypted input: %s\n", decrypted_input);

    free(encrypted_input);
    free(decrypted_input);

    return 0;
}