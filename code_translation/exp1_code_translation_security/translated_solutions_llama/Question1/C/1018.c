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
void generate_fernet_key(FernetKey* key) {
    RAND_bytes(key->key, 32);
}

// Function to encrypt input using Fernet
unsigned char* encrypt(const unsigned char* input, size_t input_len, const FernetKey* key) {
    // Token version and timestamp (4 bytes for version and 8 bytes for timestamp)
    unsigned char token[4 + 8 + input_len + 32];
    memset(token, 0, sizeof(token));

    // Version
    token[0] = 0x80;

    // Timestamp
    uint64_t timestamp = 0; // For simplicity, using 0 here
    memcpy(token + 4, &timestamp, 8);

    // IV (128 bits)
    unsigned char iv[16];
    RAND_bytes(iv, 16);
    memcpy(token + 4 + 8, iv, 16);

    // Encrypt input
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, iv);
    EVP_EncryptUpdate(ctx, token + 4 + 8 + 16, NULL, input, input_len, NULL);
    EVP_EncryptFinal_ex(ctx, token + 4 + 8 + 16 + input_len, NULL);
    EVP_CIPHER_CTX_free(ctx);

    // HMAC (SHA256)
    unsigned char hmac[32];
    unsigned char* data = token;
    size_t data_len = 4 + 8 + 16 + input_len;
    unsigned char* mac_key = key->key + 16;
    HMAC(EVP_sha256(), mac_key, 16, data, data_len, hmac, NULL);

    // Base64 encode
    size_t encoded_len = base64_encode_alloc(token, 4 + 8 + 16 + input_len + 32, &encoded_len);
    unsigned char* encoded = malloc(encoded_len);
    base64_encode(token, 4 + 8 + 16 + input_len + 32, encoded);

    return encoded;
}

// Function to decrypt input using Fernet
unsigned char* decrypt(const unsigned char* input, size_t input_len, const FernetKey* key) {
    // Base64 decode
    size_t decoded_len = (input_len * 3 + 3) / 4;
    unsigned char* decoded = malloc(decoded_len);
    base64_decode(input, input_len, decoded);

    // Check HMAC
    unsigned char hmac[32];
    unsigned char* mac_key = key->key + 16;
    HMAC(EVP_sha256(), mac_key, 16, decoded, decoded_len - 32, hmac, NULL);
    if (memcmp(decoded + decoded_len - 32, hmac, 32) != 0) {
        printf("HMAC verification failed\n");
        free(decoded);
        return NULL;
    }

    // Extract IV and encrypted data
    unsigned char iv[16];
    memcpy(iv, decoded + 4 + 8, 16);
    size_t encrypted_len = decoded_len - 4 - 8 - 16 - 32;

    // Decrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, iv);
    unsigned char* decrypted = malloc(encrypted_len);
    EVP_DecryptUpdate(ctx, decrypted, NULL, decoded + 4 + 8 + 16, encrypted_len, NULL);
    EVP_DecryptFinal_ex(ctx, decrypted + encrypted_len, NULL);
    EVP_CIPHER_CTX_free(ctx);

    free(decoded);
    return decrypted;
}

int main() {
    FernetKey key;
    generate_fernet_key(&key);

    const char* input_str = "Hello, World!";
    size_t input_len = strlen(input_str);
    unsigned char* input = (unsigned char*) input_str;

    unsigned char* encrypted = encrypt(input, input_len, &key);
    printf("Encrypted: %s\n", encrypted);

    unsigned char* decrypted = decrypt(encrypted, strlen((char*) encrypted), &key);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);

    return 0;
}