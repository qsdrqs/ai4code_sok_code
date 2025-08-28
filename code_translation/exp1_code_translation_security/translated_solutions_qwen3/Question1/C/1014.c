#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define AES_BLOCK_SIZE 16
#define PAD_BLOCK_SIZE 32

unsigned char *pad(const unsigned char *data, size_t len, size_t *padded_len) {
    size_t pad_val = PAD_BLOCK_SIZE - (len % PAD_BLOCK_SIZE);
    if (pad_val == 0)
        pad_val = PAD_BLOCK_SIZE;
    *padded_len = len + pad_val;
    unsigned char *padded = (unsigned char *)malloc(*padded_len);
    if (!padded)
        return NULL;
    memcpy(padded, data, len);
    memset(padded + len, pad_val, pad_val);
    return padded;
}

int unpad(const unsigned char *data, size_t len, unsigned char **unpadded, size_t *unpadded_len) {
    if (len == 0)
        return -1;
    size_t pad_val = data[len - 1];
    if (pad_val > PAD_BLOCK_SIZE || pad_val == 0 || len < pad_val)
        return -1;
    *unpadded_len = len - pad_val;
    *unpadded = (unsigned char *)malloc(*unpadded_len);
    if (!*unpadded)
        return -1;
    memcpy(*unpadded, data, *unpadded_len);
    return 0;
}

char *base64_encode(const unsigned char *data, size_t len) {
    BIO *bio, *b64;
    BUF_MEM *bufferPtr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL); // No newlines

    BIO_write(bio, data, len);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &bufferPtr);
    char *b64text = (char *)malloc(bufferPtr->length + 1);
    memcpy(b64text, bufferPtr->data, bufferPtr->length);
    b64text[bufferPtr->length] = '\0';

    BIO_free_all(bio);

    return b64text;
}

unsigned char *base64_decode(const char *data, size_t *out_len) {
    size_t in_len = strlen(data);
    *out_len = (in_len / 4) * 3 + 4; // Maximum possible decoded length
    unsigned char *out = (unsigned char *)malloc(*out_len);
    if (!out)
        return NULL;
    int decode_len = EVP_DecodeBlock(out, (const unsigned char *)data, in_len);
    if (decode_len < 0) {
        free(out);
        return NULL;
    }
    *out_len = decode_len;
    return out;
}

char *encrypt(const char *key_str, const char *raw) {
    // Generate key
    unsigned char key[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)key_str, strlen(key_str), key);

    // Pad raw data
    size_t raw_len = strlen(raw);
    size_t padded_len;
    unsigned char *padded = pad((unsigned char *)raw, raw_len, &padded_len);
    if (!padded) {
        return NULL;
    }

    // Generate IV
    unsigned char iv[AES_BLOCK_SIZE];
    if (!RAND_bytes(iv, AES_BLOCK_SIZE)) {
        free(padded);
        return NULL;
    }

    // Encrypt using AES-256-CBC
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);

    // Determine buffer size
    int ciphertext_len = padded_len;
    unsigned char *ciphertext = (unsigned char *)malloc(ciphertext_len);
    if (!ciphertext) {
        free(padded);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    int len;
    EVP_EncryptUpdate(ctx, ciphertext, &len, padded, padded_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + ciphertext_len, &len);
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    // Combine IV and ciphertext
    size_t encrypted_len = AES_BLOCK_SIZE + ciphertext_len;
    unsigned char *encrypted = (unsigned char *)malloc(encrypted_len);
    if (!encrypted) {
        free(ciphertext);
        return NULL;
    }
    memcpy(encrypted, iv, AES_BLOCK_SIZE);
    memcpy(encrypted + AES_BLOCK_SIZE, ciphertext, ciphertext_len);
    free(ciphertext);

    // Base64 encode
    char *encoded = base64_encode(encrypted, encrypted_len);
    free(encrypted);
    return encoded;
}

char *decrypt(const char *key_str, const char *encrypted_b64) {
    // Decode base64
    size_t decoded_len;
    unsigned char *decoded = base64_decode(encrypted_b64, &decoded_len);
    if (!decoded) {
        return NULL;
    }

    // Extract IV and ciphertext
    if (decoded_len < AES_BLOCK_SIZE) {
        free(decoded);
        return NULL;
    }
    const unsigned char *iv = decoded;
    const unsigned char *ciphertext = decoded + AES_BLOCK_SIZE;
    size_t ciphertext_len = decoded_len - AES_BLOCK_SIZE;

    // Generate key
    unsigned char key[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)key_str, strlen(key_str), key);

    // Decrypt
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_set_padding(ctx, 0);
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);

    size_t decrypted_len = ciphertext_len;
    unsigned char *decrypted = (unsigned char *)malloc(decrypted_len);
    if (!decrypted) {
        free(decoded);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    int len;
    EVP_DecryptUpdate(ctx, decrypted, &len, ciphertext, ciphertext_len);
    decrypted_len = len;
    EVP_DecryptFinal_ex(ctx, decrypted + decrypted_len, &len);
    decrypted_len += len;

    EVP_CIPHER_CTX_free(ctx);
    free(decoded);

    // Unpad
    unsigned char *unpadded;
    size_t unpadded_len;
    if (unpad(decrypted, decrypted_len, &unpadded, &unpadded_len) != 0) {
        free(decrypted);
        return NULL;
    }
    free(decrypted);

    // Convert to null-terminated string
    char *result = (char *)malloc(unpadded_len + 1);
    if (!result) {
        free(unpadded);
        return NULL;
    }
    memcpy(result, unpadded, unpadded_len);
    result[unpadded_len] = '\0';
    free(unpadded);
    return result;
}

int main() {
    const char *key = "mysecretpassword";
    const char *plaintext = "Secret Message A";

    char *encrypted = encrypt(key, plaintext);
    if (!encrypted) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    printf("Encrypted: %s\n", encrypted);

    char *decrypted = decrypt(key, encrypted);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed\n");
        free(encrypted);
        return 1;
    }

    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}