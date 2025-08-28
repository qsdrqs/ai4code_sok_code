#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <time.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Error handling
void handle_openssl_error() {
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

// Generate a 32-byte Fernet key
void generate_key(unsigned char key[32]) {
    if (RAND_bytes(key, 32) != 1) handle_openssl_error();
}

// Convert uint64_t to big-endian bytes
void uint64_to_be(uint64_t value, unsigned char *bytes) {
    for (int i = 0; i < 8; ++i) {
        bytes[i] = (value >> (56 - i * 8)) & 0xFF;
    }
}

// Add PKCS#7 padding
size_t add_padding(unsigned char **data, size_t data_len) {
    size_t padding_len = 16 - (data_len % 16);
    unsigned char *padded = realloc(*data, data_len + padding_len);
    if (!padded) return 0;
    *data = padded;
    memset(*data + data_len, padding_len, padding_len);
    return data_len + padding_len;
}

// AES-128-CBC encryption
unsigned char* encrypt_aes_cbc(const unsigned char *plaintext, size_t len, const unsigned char *key, const unsigned char *iv, size_t *out_len) {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    EVP_CIPHER_CTX_set_padding(ctx, 0); // Manual padding

    size_t encrypted_size = len + EVP_CIPHER_block_size(EVP_aes_128_cbc());
    unsigned char *encrypted = malloc(encrypted_size);
    if (!encrypted) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    int out_len1 = 0, out_len2 = 0;
    if (EVP_EncryptUpdate(ctx, encrypted, &out_len1, plaintext, len) != 1) {
        free(encrypted);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    if (EVP_EncryptFinal_ex(ctx, encrypted + out_len1, &out_len2) != 1) {
        free(encrypted);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    *out_len = out_len1 + out_len2;
    EVP_CIPHER_CTX_free(ctx);
    return encrypted;
}

// Base64url encode
char* base64url_encode(const unsigned char *data, size_t len) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL | BIO_FLAGS_BASE64_NO_PAD);
    BIO *bio = BIO_new(BIO_s_mem());
    BIO_push(b64, bio);
    BIO_write(b64, data, len);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);
    char *encoded = malloc(bptr->length + 1);
    memcpy(encoded, bptr->data, bptr->length);
    encoded[bptr->length] = '\0';

    BIO_free_all(b64);

    // Replace '+' and '/' with '-' and '_'
    for (int i = 0; encoded[i]; ++i) {
        if (encoded[i] == '+') encoded[i] = '-';
        else if (encoded[i] == '/') encoded[i] = '_';
    }

    return encoded;
}

// Encrypt function
char* encrypt(const char *data, const unsigned char *key) {
    size_t data_len = strlen(data);
    unsigned char *plaintext = malloc(data_len);
    memcpy(plaintext, data, data_len);

    // Split key
    unsigned char enc_key[16], auth_key[16];
    memcpy(enc_key, key, 16);
    memcpy(auth_key, key + 16, 16);

    // Generate IV
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) handle_openssl_error();

    // Timestamp
    time_t timestamp = time(NULL);
    unsigned char ts_bytes[8];
    uint64_to_be((uint64_t)timestamp, ts_bytes);

    // PKCS7 padding
    size_t padded_len = add_padding(&plaintext, data_len);
    if (padded_len == 0) {
        free(plaintext);
        return NULL;
    }

    // AES encryption
    size_t encrypted_len;
    unsigned char *ciphertext = encrypt_aes_cbc(plaintext, padded_len, enc_key, iv, &encrypted_len);
    free(plaintext);
    if (!ciphertext) return NULL;

    // Build encrypted message: version + timestamp + IV + ciphertext
    size_t encrypted_message_len = 1 + 8 + 16 + encrypted_len;
    unsigned char *encrypted_message = malloc(encrypted_message_len);
    if (!encrypted_message) {
        free(ciphertext);
        return NULL;
    }

    encrypted_message[0] = 0x80; // Fernet version
    memcpy(encrypted_message + 1, ts_bytes, 8);
    memcpy(encrypted_message + 1 + 8, iv, 16);
    memcpy(encrypted_message + 1 + 8 + 16, ciphertext, encrypted_len);
    free(ciphertext);

    // Compute HMAC
    unsigned int hmac_len = 32;
    unsigned char hmac[32];
    if (!HMAC(EVP_sha256(), auth_key, 16, encrypted_message, encrypted_message_len, hmac, &hmac_len)) {
        free(encrypted_message);
        return NULL;
    }

    // Create token: encrypted_message + hmac
    size_t token_len = encrypted_message_len + hmac_len;
    unsigned char *token = malloc(token_len);
    if (!token) {
        free(encrypted_message);
        return NULL;
    }

    memcpy(token, encrypted_message, encrypted_message_len);
    memcpy(token + encrypted_message_len, hmac, hmac_len);
    free(encrypted_message);

    // Base64url encode
    char *encoded = base64url_encode(token, token_len);
    free(token);
    return encoded;
}

// Base64url decode
size_t base64url_decode(const char *encoded, unsigned char **data) {
    size_t encoded_len = strlen(encoded);
    char *s = malloc(encoded_len + 5); // Add space for padding
    if (!s) return 0;
    strcpy(s, encoded);

    // Replace '-' and '_' with '+' and '/'
    for (int i = 0; s[i]; ++i) {
        if (s[i] == '-') s[i] = '+';
        else if (s[i] == '_') s[i] = '/';
    }

    // Add padding
    int missing_padding = 4 - (encoded_len % 4);
    if (missing_padding < 4) {
        for (int i = 0; i < missing_padding; ++i) {
            s[encoded_len + i] = '=';
        }
        s[encoded_len + missing_padding] = '\0';
    }

    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *bio = BIO_new_mem_buf(s, -1);
    BIO_push(b64, bio);

    size_t decoded_len = 1024;
    *data = malloc(decoded_len);
    decoded_len = BIO_read(b64, *data, decoded_len);
    BIO_free_all(b64);
    free(s);

    return decoded_len;
}

// Decrypt function
char* decrypt(const char *token, const unsigned char *key) {
    unsigned char *decoded_token;
    size_t decoded_len = base64url_decode(token, &decoded_token);
    if (decoded_len <= 0) return NULL;

    // Split key
    unsigned char enc_key[16], auth_key[16];
    memcpy(enc_key, key, 16);
    memcpy(auth_key, key + 16, 16);

    // Check version
    if (decoded_len < 1 || decoded_token[0] != 0x80) {
        free(decoded_token);
        return NULL;
    }

    // Check HMAC
    size_t encrypted_message_len = decoded_len - 32;
    const unsigned char *encrypted_message = decoded_token;
    const unsigned char *hmac_received = decoded_token + encrypted_message_len;

    unsigned int hmac_len = 32;
    unsigned char hmac_calculated[EVP_MAX_MD_SIZE];
    if (!HMAC(EVP_sha256(), auth_key, 16, encrypted_message, encrypted_message_len, hmac_calculated, &hmac_len)) {
        free(decoded_token);
        return NULL;
    }

    if (CRYPTO_memcmp(hmac_calculated, hmac_received, 32) != 0) {
        free(decoded_token);
        return NULL;
    }

    // Extract IV and ciphertext
    const unsigned char *iv = encrypted_message + 1 + 8;
    const unsigned char *ciphertext = encrypted_message + 1 + 8 + 16;
    size_t ciphertext_len = encrypted_message_len - (1 + 8 + 16);

    // Decrypt
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) {
        free(decoded_token);
        return NULL;
    }

    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decoded_token);
        return NULL;
    }

    EVP_CIPHER_CTX_set_padding(ctx, 0); // Manual padding

    size_t decrypted_size = ciphertext_len;
    unsigned char *decrypted = malloc(decrypted_size);
    if (!decrypted) {
        EVP_CIPHER_CTX_free(ctx);
        free(decoded_token);
        return NULL;
    }

    int len1 = 0, len2 = 0;
    if (EVP_DecryptUpdate(ctx, decrypted, &len1, ciphertext, ciphertext_len) != 1) {
        free(decrypted);
        EVP_CIPHER_CTX_free(ctx);
        free(decoded_token);
        return NULL;
    }

    if (EVP_DecryptFinal_ex(ctx, decrypted + len1, &len2) != 1) {
        free(decrypted);
        EVP_CIPHER_CTX_free(ctx);
        free(decoded_token);
        return NULL;
    }

    int decrypted_len = len1 + len2;
    EVP_CIPHER_CTX_free(ctx);
    free(decoded_token);

    // Remove PKCS#7 padding
    int padding_len = decrypted[decrypted_len - 1];
    if (padding_len <= 0 || padding_len > 16) {
        free(decrypted);
        return NULL;
    }

    decrypted_len -= padding_len;
    char *plaintext = realloc(decrypted, decrypted_len + 1);
    plaintext[decrypted_len] = '\0';
    return plaintext;
}

// Main function
int main() {
    unsigned char key[32];
    generate_key(key);

    const char *data = "/safedir/test.txt";
    char *encrypted = encrypt(data, key);
    if (!encrypted) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    char *decrypted = decrypt(encrypted, key);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed\n");
        free(encrypted);
        return 1;
    }

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}