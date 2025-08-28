#include <openssl/rand.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/crypto.h> // for CRYPTO_memcmp

// ================== Base64 URL-safe Encoding/Decoding ==================

char* base64url_encode(const unsigned char* data, size_t len, int padding) {
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    if (!padding) {
        BIO_set_flags(b64, BIO_FLAGS_NONTRANSLATING);
    }
    BIO *bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);
    BIO_write(bio, data, len);
    BIO_flush(bio);
    BUF_MEM *bptr;
    BIO_get_mem_ptr(bio, &bptr);
    char *buff = (char*)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = '\0';
    BIO_free_all(bio);

    // Convert to URL-safe
    char *ch;
    while ((ch = strchr(buff, '+'))) *ch = '-';
    while ((ch = strchr(buff, '/'))) *ch = '_';
    if (!padding) {
        ch = buff + strlen(buff) - 1;
        while (ch >= buff && *ch == '=') --ch;
        *(ch + 1) = '\0';
    }
    return buff;
}

size_t base64url_decode(const char* b64str, unsigned char** out) {
    char* decodedStr = strdup(b64str);
    char* ch;
    while ((ch = strchr(decodedStr, '-'))) *ch = '+';
    while ((ch = strchr(decodedStr, '_'))) *ch = '/';

    size_t len = strlen(decodedStr);
    size_t pad_len = (4 - (len % 4)) % 4;
    decodedStr = realloc(decodedStr, len + pad_len + 1);
    memset(decodedStr + len, '=', pad_len);
    decodedStr[len + pad_len] = '\0';

    BIO *bio = BIO_new_mem_buf(decodedStr, -1);
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bio = BIO_push(b64, bio);

    size_t decoded_len = strlen(decodedStr) * 3 / 4 + 1;
    *out = (unsigned char*)malloc(decoded_len);
    decoded_len = BIO_read(bio, *out, decoded_len);
    BIO_free_all(bio);
    free(decodedStr);
    return decoded_len;
}

// ================== Fernet Encryption ==================

unsigned char* encrypt(const char* message, const unsigned char* key, size_t* encrypted_len) {
    unsigned char enc_key[16], sign_key[16];
    memcpy(enc_key, key, 16);
    memcpy(sign_key, key + 16, 16);

    unsigned char iv[16];
    RAND_bytes(iv, 16);

    size_t msg_len = strlen(message);
    size_t pad_len = 16 - (msg_len % 16);
    size_t padded_len = msg_len + pad_len;
    unsigned char* padded = (unsigned char*)calloc(1, padded_len);
    memcpy(padded, message, msg_len);
    memset(padded + msg_len, pad_len, pad_len);

    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, enc_key, iv);
    size_t encrypted_sz;
    int final_len;
    unsigned char* encrypted = (unsigned char*)malloc(padded_len + 16);
    EVP_EncryptUpdate(ctx, encrypted, &encrypted_sz, padded, padded_len);
    EVP_EncryptFinal_ex(ctx, encrypted + encrypted_sz, &final_len);
    size_t cipher_len = encrypted_sz + final_len;
    EVP_CIPHER_CTX_free(ctx);
    free(padded);

    HMAC_CTX* hmac_ctx = HMAC_CTX_new();
    HMAC_Init_ex(hmac_ctx, sign_key, 16, EVP_sha256(), NULL);
    unsigned char version = 0x00;
    HMAC_Update(hmac_ctx, &version, 1);
    HMAC_Update(hmac_ctx, iv, 16);
    HMAC_Update(hmac_ctx, encrypted, cipher_len);
    unsigned char hmac[EVP_MAX_MD_SIZE];
    unsigned int hmac_len;
    HMAC_Final(hmac_ctx, hmac, &hmac_len);
    HMAC_CTX_free(hmac_ctx);

    size_t assembled_len = 1 + 16 + cipher_len + hmac_len;
    unsigned char* assembled = (unsigned char*)malloc(assembled_len);
    assembled[0] = version;
    memcpy(assembled + 1, iv, 16);
    memcpy(assembled + 17, encrypted, cipher_len);
    memcpy(assembled + 17 + cipher_len, hmac, hmac_len);
    free(encrypted);

    char* encoded = base64url_encode(assembled, assembled_len, 0);
    *encrypted_len = strlen(encoded);
    free(assembled);
    return (unsigned char*)encoded;
}

// ================== Fernet Decryption ==================

char* decrypt(const unsigned char* encrypted_data, size_t encrypted_len, const unsigned char* key) {
    unsigned char* decoded;
    size_t decoded_len = base64url_decode((char*)encrypted_data, &decoded);

    if (decoded_len < 1 + 16 + 32) {
        free(decoded);
        return NULL;
    }

    unsigned char version = decoded[0];
    if (version != 0x00) {
        free(decoded);
        return NULL;
    }

    unsigned char* iv = decoded + 1;
    unsigned char* ciphertext = decoded + 1 + 16;
    size_t ciphertext_len = decoded_len - 1 - 16 - 32;
    unsigned char* received_hmac = decoded + decoded_len - 32;

    HMAC_CTX* hmac_ctx = HMAC_CTX_new();
    HMAC_Init_ex(hmac_ctx, key + 16, 16, EVP_sha256(), NULL);
    HMAC_Update(hmac_ctx, decoded, 1 + 16 + ciphertext_len);
    unsigned char expected_hmac[EVP_MAX_MD_SIZE];
    unsigned int expected_hmac_len;
    HMAC_Final(hmac_ctx, expected_hmac, &expected_hmac_len);
    HMAC_CTX_free(hmac_ctx);

    if (CRYPTO_memcmp(expected_hmac, received_hmac, 32) != 0) {
        free(decoded);
        return NULL;
    }

    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv);
    size_t decrypted_len = ciphertext_len + 16;
    unsigned char* decrypted = (unsigned char*)malloc(decrypted_len);
    int out_len;
    EVP_DecryptUpdate(ctx, decrypted, &out_len, ciphertext, ciphertext_len);
    decrypted_len = out_len;
    EVP_DecryptFinal_ex(ctx, decrypted + out_len, &out_len);
    decrypted_len += out_len;
    EVP_CIPHER_CTX_free(ctx);

    int pad_len = decrypted[decrypted_len - 1];
    if (pad_len < 1 || pad_len > 16) {
        free(decrypted);
        free(decoded);
        return NULL;
    }

    for (int i = 0; i < pad_len; i++) {
        if (decrypted[decrypted_len - 1 - i] != pad_len) {
            free(decrypted);
            free(decoded);
            return NULL;
        }
    }

    decrypted_len -= pad_len;
    decrypted = realloc(decrypted, decrypted_len + 1);
    decrypted[decrypted_len] = '\0';

    free(decoded);
    return (char*)decrypted;
}

// ================== Main Function ==================

int main() {
    // Generate a 32-byte symmetric key
    unsigned char key[32];
    RAND_bytes(key, 32);

    const char* message = "the solutions are here somewhere";
    printf("Secret Message: %s\n", message);

    size_t enc_len;
    unsigned char* encMessage = encrypt(message, key, &enc_len);
    printf("Encrypted Message: %s\n", encMessage);

    char* decMessage = decrypt(encMessage, enc_len, key);
    printf("Decrypted Message: %s\n", decMessage);

    free(encMessage);
    free(decMessage);

    return 0;
}