#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/bio.h>
#include <openssl/b64.h>
#include <time.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Replace '+' and '/' with URL-safe characters and remove padding
void to_url_safe_base64(char *str) {
    while (*str) {
        if (*str == '+') *str = '-';
        else if (*str == '/') *str = '_';
        else if (*str == '=') *str = '\0';
        str++;
    }
}

// Generate a Fernet-style key (32 random bytes, base64 URL-safe encoded)
char* generate_key() {
    unsigned char key[32];
    RAND_bytes(key, 32);

    int b64_len = 45;
    char *b64_key = (char*)malloc(b64_len + 1);
    EVP_EncodeBlock(b64_key, key, 32);
    to_url_safe_base64(b64_key);
    return b64_key;
}

// Add PKCS7 padding
int add_pkcs7_padding(unsigned char *data, int data_len, int block_size) {
    int padding_len = block_size - (data_len % block_size);
    for (int i = 0; i < padding_len; i++) {
        data[data_len + i] = padding_len;
    }
    return data_len + padding_len;
}

// Encrypt using Fernet-like scheme
char* encrypt_text(const char *plain_text, const char *key_str) {
    // Decode the base64 key_str
    size_t key_str_len = strlen(key_str);
    size_t padded_len = key_str_len + (4 - (key_str_len % 4)) % 4;
    char *key_padded = (char*)malloc(padded_len + 1);
    strcpy(key_padded, key_str);
    for (size_t i = key_str_len; i < padded_len; i++) {
        key_padded[i] = '=';
    }
    key_padded[padded_len] = '\0';

    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *mem = BIO_new(BIO_s_mem());
    BIO_write(mem, key_padded, padded_len);
    BIO_push(b64, mem);
    unsigned char decoded_key[32];
    int decoded_len = BIO_read(b64, decoded_key, 32);
    BIO_free_all(b64);
    free(key_padded);
    if (decoded_len != 32) {
        return NULL;
    }

    unsigned char enc_key[16], hmac_key[16];
    memcpy(enc_key, decoded_key, 16);
    memcpy(hmac_key, decoded_key + 16, 16);

    // Timestamp (64-bit big-endian)
    uint64_t ts = (uint64_t)time(NULL);
    uint8_t timestamp[8];
    for (int i = 0; i < 8; i++) {
        timestamp[i] = (ts >> (56 - i * 8)) & 0xFF;
    }

    // Generate IV
    unsigned char iv[16];
    RAND_bytes(iv, 16);

    // Prepare plaintext with PKCS7 padding
    int pt_len = strlen(plain_text);
    int padded_pt_len = (pt_len + 15) & ~15;
    unsigned char *padded_pt = (unsigned char*)malloc(padded_pt_len);
    memcpy(padded_pt, plain_text, pt_len);
    int padding_len = padded_pt_len - pt_len;
    for (int i = 0; i < padding_len; i++) {
        padded_pt[pt_len + i] = padding_len;
    }

    // AES-CBC encryption
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int cipher_len = 0, len;
    unsigned char *ciphertext = (unsigned char*)malloc(padded_pt_len);
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, padded_pt, padded_pt_len);
    cipher_len += len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    cipher_len += len;
    EVP_CIPHER_CTX_free(ctx);
    free(padded_pt);

    // Create HMAC data: version (0x80), timestamp, IV, ciphertext
    int hmac_data_len = 1 + 8 + 16 + cipher_len;
    unsigned char *hmac_data = (unsigned char*)malloc(hmac_data_len);
    hmac_data[0] = 0x80;
    memcpy(hmac_data + 1, timestamp, 8);
    memcpy(hmac_data + 1 + 8, iv, 16);
    memcpy(hmac_data + 1 + 8 + 16, ciphertext, cipher_len);
    free(ciphertext);

    // Compute HMAC-SHA256
    unsigned char hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), hmac_key, 16, hmac_data, hmac_data_len, hmac, &hmac_len);
    free(hmac_data);

    // Build token: hmac_data + hmac (32 bytes)
    int token_len = hmac_data_len + 32;
    unsigned char *token = (unsigned char*)malloc(token_len);
    memcpy(token, hmac_data, hmac_data_len);
    memcpy(token + hmac_data_len, hmac, 32);
    free(hmac_data);

    // Base64 URL-safe encode token
    BIO *b64_enc = BIO_new(BIO_f_base64());
    BIO_set_flags(b64_enc, BIO_FLAGS_BASE64_NO_NL);
    BIO *b64_mem = BIO_new(BIO_s_mem());
    BIO_push(b64_enc, b64_mem);
    BIO_write(b64_enc, token, token_len);
    BIO_flush(b64_enc);
    BUF_MEM *b64_ptr;
    BIO_get_mem_ptr(b64_enc, &b64_ptr);
    char *encrypted_b64 = (char*)malloc(b64_ptr->length + 1);
    memcpy(encrypted_b64, b64_ptr->data, b64_ptr->length);
    encrypted_b64[b64_ptr->length] = '\0';
    BIO_free_all(b64_enc);
    free(token);

    // Convert to URL-safe and remove padding
    to_url_safe_base64(encrypted_b64);

    return encrypted_b64;
}

// Decrypt function
char* decrypt_text(const char *encrypted_text, const char *key_str) {
    // Decode encrypted_text from URL-safe base64
    size_t enc_len = strlen(encrypted_text);
    size_t padded_len = enc_len + (4 - (enc_len % 4)) % 4;
    char *enc_padded = (char*)malloc(padded_len + 1);
    strcpy(enc_padded, encrypted_text);
    for (size_t i = enc_len; i < padded_len; i++) {
        enc_padded[i] = '=';
    }
    enc_padded[padded_len] = '\0';

    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *mem = BIO_new(BIO_s_mem());
    BIO_write(mem, enc_padded, padded_len);
    BIO_push(b64, mem);
    size_t decoded_max_len = 48 + (padded_len * 0.75);
    unsigned char *decoded_token = (unsigned char*)malloc(decoded_max_len);
    int token_len = BIO_read(b64, decoded_token, decoded_max_len);
    BIO_free_all(b64);
    free(enc_padded);
    if (token_len <= 0) {
        free(decoded_token);
        return NULL;
    }

    // Check version byte
    if (decoded_token[0] != 0x80) {
        free(decoded_token);
        return NULL;
    }

    // Extract HMAC (last 32 bytes)
    unsigned char hmac_received[32];
    memcpy(hmac_received, decoded_token + token_len - 32, 32);

    // Decode the key_str to enc_key and hmac_key
    size_t key_str_len = strlen(key_str);
    padded_len = key_str_len + (4 - (key_str_len % 4)) % 4;
    char *key_padded = (char*)malloc(padded_len + 1);
    strcpy(key_padded, key_str);
    for (size_t i = key_str_len; i < padded_len; i++) {
        key_padded[i] = '=';
    }
    key_padded[padded_len] = '\0';

    BIO *key_b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(key_b64, BIO_FLAGS_BASE64_NO_NL);
    BIO *key_mem = BIO_new(BIO_s_mem());
    BIO_write(key_mem, key_padded, padded_len);
    BIO_push(key_b64, key_mem);
    unsigned char decoded_key[32];
    int decoded_key_len = BIO_read(key_b64, decoded_key, 32);
    BIO_free_all(key_b64);
    free(key_padded);
    if (decoded_key_len != 32) {
        return NULL;
    }

    unsigned char enc_key[16], hmac_key[16];
    memcpy(enc_key, decoded_key, 16);
    memcpy(hmac_key, decoded_key + 16, 16);

    // Recalculate HMAC on decoded_token without HMAC part
    int hmac_data_len = token_len - 32;
    unsigned char hmac_computed[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), hmac_key, 16, decoded_token, hmac_data_len, hmac_computed, &hmac_len);

    // Compare HMAC
    if (CRYPTO_memcmp(hmac_computed, hmac_received, 32) != 0) {
        free(decoded_token);
        return NULL;
    }

    // Parse IV and ciphertext
    unsigned char *iv = decoded_token + 1 + 8;
    int ciphertext_len = token_len - (1 + 8 + 16 + 32);
    unsigned char *ciphertext = iv + 16;

    // Decrypt AES-CBC
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int pt_len, len;
    unsigned char *decrypted_pt = (unsigned char*)malloc(ciphertext_len);
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    EVP_DecryptUpdate(ctx, decrypted_pt, &len, ciphertext, ciphertext_len);
    pt_len = len;
    EVP_DecryptFinal_ex(ctx, decrypted_pt + len, &len);
    pt_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // Remove PKCS7 padding
    int pad_val = decrypted_pt[pt_len - 1];
    if (pad_val < 1 || pad_val > 16) {
        free(decrypted_pt);
        return NULL;
    }
    pt_len -= pad_val;

    // Convert to null-terminated string
    char *plaintext = (char*)malloc(pt_len + 1);
    memcpy(plaintext, decrypted_pt, pt_len);
    plaintext[pt_len] = '\0';
    free(decrypted_pt);
    free(decoded_token);
    return plaintext;
}

int main() {
    // Generate key
    char *key = generate_key();

    // Example usage
    const char *message = "Secret message";
    char *encrypted = encrypt_text(message, key);
    char *decrypted = decrypt_text(encrypted, key);

    printf("Encrypted: %s\n", encrypted);
    printf("Decrypted: %s\n", decrypted);

    // Free allocated memory
    free(key);
    free(encrypted);
    free(decrypted);

    return 0;
}