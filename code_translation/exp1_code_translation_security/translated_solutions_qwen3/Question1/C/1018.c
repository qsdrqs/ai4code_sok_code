#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <time.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

// Helper function declarations
int urlsafe_decode(const unsigned char* input, unsigned char* output, int max_output_len);
unsigned char* urlsafe_encode(const unsigned char* input, int length, int* out_len);

// PKCS7 padding
void pad_pkcs7(unsigned char* data, int data_len, int block_size) {
    int padding = block_size - (data_len % block_size);
    for (int i = 0; i < padding; ++i) {
        data[data_len + i] = padding;
    }
}

// PKCS7 unpadding
int unpad_pkcs7(unsigned char* data, int data_len, int block_size) {
    int padding = data[data_len - 1];
    if (padding < 1 || padding > block_size) return -1;

    for (int i = 0; i < padding; ++i) {
        if (data[data_len - 1 - i] != padding) return -1;
    }

    return data_len - padding;
}

// Convert host to big-endian 64-bit
uint64_t htobe64_custom(uint64_t host_64) {
    uint64_t be_64;
    unsigned char* p = (unsigned char*)&be_64;
    p[0] = (host_64 >> 56) & 0xFF;
    p[1] = (host_64 >> 48) & 0xFF;
    p[2] = (host_64 >> 40) & 0xFF;
    p[3] = (host_64 >> 32) & 0xFF;
    p[4] = (host_64 >> 24) & 0xFF;
    p[5] = (host_64 >> 16) & 0xFF;
    p[6] = (host_64 >> 8) & 0xFF;
    p[7] = host_64 & 0xFF;
    return be_64;
}

// Encrypt function
char* encrypt(const char* plaintext, const char* key_b64) {
    unsigned char raw_key[32];
    int decoded_len = urlsafe_decode((const unsigned char*)key_b64, raw_key, 32);
    if (decoded_len != 32) return NULL;

    unsigned char enc_key[16], auth_key[16];
    memcpy(enc_key, raw_key, 16);
    memcpy(auth_key, raw_key + 16, 16);

    // Timestamp (8 bytes, big-endian)
    uint64_t ts = htobe64_custom((uint64_t)time(NULL));
    unsigned char timestamp[8];
    memcpy(timestamp, &ts, 8);

    // IV
    unsigned char iv[16];
    if (RAND_bytes(iv, 16) != 1) return NULL;

    // Pad plaintext
    int pt_len = strlen(plaintext);
    int padded_len = (pt_len + 15) / 16 * 16;
    unsigned char* padded = (unsigned char*)malloc(padded_len);
    memcpy(padded, plaintext, pt_len);
    pad_pkcs7(padded, pt_len, 16);

    // Encrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    if (EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded);
        return NULL;
    }

    unsigned char* ciphertext = (unsigned char*)malloc(padded_len);
    int len;
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, padded, padded_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded);
        free(ciphertext);
        return NULL;
    }

    int ciphertext_len = len;
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(padded);
        free(ciphertext);
        return NULL;
    }

    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    // Prepare data to sign
    int data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char* data_to_sign = (unsigned char*)malloc(data_len);
    data_to_sign[0] = 0x80;
    memcpy(data_to_sign + 1, timestamp, 8);
    memcpy(data_to_sign + 1 + 8, iv, 16);
    memcpy(data_to_sign + 1 + 8 + 16, ciphertext, ciphertext_len);

    // Compute HMAC
    unsigned char hmac[32];
    unsigned int hmac_len;
    if (!HMAC(EVP_sha256(), auth_key, 16, data_to_sign, data_len, hmac, &hmac_len)) {
        free(data_to_sign);
        free(ciphertext);
        return NULL;
    }

    free(data_to_sign);

    // Combine all parts
    int output_len = 1 + 8 + 16 + ciphertext_len + 32;
    unsigned char* output = (unsigned char*)malloc(output_len);
    output[0] = 0x80;
    memcpy(output + 1, timestamp, 8);
    memcpy(output + 1 + 8, iv, 16);
    memcpy(output + 1 + 8 + 16, ciphertext, ciphertext_len);
    memcpy(output + output_len - 32, hmac, 32);

    free(ciphertext);

    // Base64 encode
    int b64_len;
    char* b64 = (char*)urlsafe_encode(output, output_len, &b64_len);
    free(output);
    return b64;
}

// Decrypt function
char* decrypt(const char* ciphertext_b64, const char* key_b64) {
    unsigned char decoded[1024]; // Assuming max size
    int decoded_len = urlsafe_decode((const unsigned char*)ciphertext_b64, decoded, 1024);
    if (decoded_len < 57) return NULL;

    if (decoded[0] != 0x80) return NULL;

    unsigned char* timestamp = decoded + 1;
    unsigned char* iv = decoded + 1 + 8;
    int ct_len = decoded_len - (1 + 8 + 16 + 32);
    if (ct_len < 0) return NULL;

    unsigned char* ct = decoded + 1 + 8 + 16;
    unsigned char* received_hmac = decoded + decoded_len - 32;

    // Decode key
    unsigned char raw_key[32];
    if (urlsafe_decode((const unsigned char*)key_b64, raw_key, 32) != 32) return NULL;

    unsigned char enc_key[16], auth_key[16];
    memcpy(enc_key, raw_key, 16);
    memcpy(auth_key, raw_key + 16, 16);

    // Compute HMAC
    int data_to_sign_len = 1 + 8 + 16 + ct_len;
    unsigned char* data_to_sign = (unsigned char*)malloc(data_to_sign_len);
    memcpy(data_to_sign, decoded, data_to_sign_len);

    unsigned char hmac[32];
    unsigned int hmac_len;
    if (!HMAC(EVP_sha256(), auth_key, 16, data_to_sign, data_to_sign_len, hmac, &hmac_len)) {
        free(data_to_sign);
        return NULL;
    }

    free(data_to_sign);

    if (CRYPTO_memcmp(hmac, received_hmac, 32) != 0) return NULL;

    // Decrypt
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;

    if (EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }

    unsigned char* decrypted = (unsigned char*)malloc(ct_len);
    int len;
    if (EVP_DecryptUpdate(ctx, decrypted, &len, ct, ct_len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted);
        return NULL;
    }

    int decrypted_len = len;
    if (EVP_DecryptFinal_ex(ctx, decrypted + len, &len) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        free(decrypted);
        return NULL;
    }

    decrypted_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // Unpad
    int unpadded_len = unpad_pkcs7(decrypted, decrypted_len, 16);
    if (unpadded_len < 0) {
        free(decrypted);
        return NULL;
    }

    char* result = (char*)malloc(unpadded_len + 1);
    memcpy(result, decrypted, unpadded_len);
    result[unpadded_len] = '\0';
    free(decrypted);
    return result;
}

// Base64 URL-safe decode
int urlsafe_decode(const unsigned char* input, unsigned char* output, int max_output_len) {
    int len = strlen((char*)input);
    unsigned char* tmp = (unsigned char*)malloc(len + 5);
    memcpy(tmp, input, len);
    for (int i = 0; i < len; ++i) {
        if (tmp[i] == '-') tmp[i] = '+';
        else if (tmp[i] == '_') tmp[i] = '/';
    }
    int pad_len = (4 - (len % 4)) % 4;
    for (int i = 0; i < pad_len; ++i) {
        tmp[len + i] = '=';
    }
    tmp[len + pad_len] = '\0';

    int decoded_len = EVP_DecodeBlock(output, tmp, strlen((char*)tmp));
    free(tmp);
    if (decoded_len > max_output_len) return -1;
    return decoded_len;
}

// Base64 URL-safe encode
unsigned char* urlsafe_encode(const unsigned char* input, int length, int* out_len) {
    int b64_len = 4 * ((length + 2) / 3);
    unsigned char* b64 = (unsigned char*)malloc(b64_len + 1);
    EVP_EncodeBlock(b64, input, length);
    b64[b64_len] = '\0';

    int pad = 0;
    for (int i = 0; i < b64_len; ++i) {
        if (b64[i] == '+') b64[i] = '-';
        else if (b64[i] == '/') b64[i] = '_';
        else if (b64[i] == '=') pad++;
    }

    *out_len = b64_len - pad;
    unsigned char* result = (unsigned char*)realloc(b64, *out_len + 1);
    strncpy((char*)result, (char*)b64, *out_len + 1);
    free(b64);
    return result;
}