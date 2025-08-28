#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

// Helper function to handle OpenSSL errors
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

// Helper function for Base64 encoding
char* base64_encode(const unsigned char* input, int length) {
    BIO *bio, *b64;
    BUF_MEM *bufferPtr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL); // No newlines
    BIO_write(bio, input, length);
    BIO_flush(bio);

    BIO_get_mem_ptr(bio, &bufferPtr);
    char* output = (char*)malloc(bufferPtr->length + 1);
    if (!output) handleErrors();
    
    memcpy(output, bufferPtr->data, bufferPtr->length);
    output[bufferPtr->length] = '\0';

    BIO_free_all(bio);
    return output;
}

// Helper function for Base64 decoding
unsigned char* base64_decode(const char* input, int* out_len) {
    BIO *bio, *b64;
    int input_len = strlen(input);
    unsigned char* buffer = (unsigned char*)malloc(input_len);
    if (!buffer) handleErrors();

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new_mem_buf(input, -1);
    bio = BIO_push(b64, bio);

    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL); // No newlines
    *out_len = BIO_read(bio, buffer, input_len);

    BIO_free_all(bio);
    return buffer;
}

// Generates a Fernet-compatible key (32 random bytes, base64 encoded)
char* generate_key() {
    unsigned char key_bytes[32];
    if (RAND_bytes(key_bytes, sizeof(key_bytes)) != 1) {
        handleErrors();
    }
    return base64_encode(key_bytes, sizeof(key_bytes));
}

/**
 * @brief Encrypt a message using a Fernet key.
 * @param message The plaintext message string.
 * @param b64_key The base64-encoded Fernet key.
 * @return A newly allocated string with the base64-encoded encrypted token. The caller must free this memory.
 */
char* encrypt_message(const char* message, const char* b64_key) {
    // 1. Decode the key
    int key_len;
    unsigned char* key = base64_decode(b64_key, &key_len);
    if (key_len != 32) {
        fprintf(stderr, "Invalid key size.\n");
        free(key);
        return NULL;
    }
    unsigned char* signing_key = key;
    unsigned char* encryption_key = key + 16;

    // 2. Prepare components for the token
    unsigned char version = 0x80;
    unsigned char iv[16];
    if (RAND_bytes(iv, sizeof(iv)) != 1) handleErrors();

    uint64_t timestamp = time(NULL);
    unsigned char timestamp_bytes[8];
    for (int i = 0; i < 8; i++) {
        timestamp_bytes[i] = (timestamp >> (56 - 8 * i)) & 0xFF;
    }

    // 3. Encrypt the message using AES-128-CBC
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;
    int message_len = strlen(message);
    unsigned char* ciphertext = malloc(message_len + EVP_MAX_BLOCK_LENGTH);

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv)) handleErrors();
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)message, message_len)) handleErrors();
    ciphertext_len = len;
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) handleErrors();
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Concatenate data for HMAC
    int hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char* hmac_data = malloc(hmac_data_len);
    memcpy(hmac_data, &version, 1);
    memcpy(hmac_data + 1, timestamp_bytes, 8);
    memcpy(hmac_data + 9, iv, 16);
    memcpy(hmac_data + 25, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, hmac_result, &hmac_len);

    // 6. Assemble the final token
    int final_token_len = hmac_data_len + hmac_len;
    unsigned char* final_token = malloc(final_token_len);
    memcpy(final_token, hmac_data, hmac_data_len);
    memcpy(final_token + hmac_data_len, hmac_result, hmac_len);

    // 7. Base64 encode the final token
    char* b64_final_token = base64_encode(final_token, final_token_len);

    // 8. Cleanup
    free(key);
    free(ciphertext);
    free(hmac_data);
    free(final_token);

    return b64_final_token;
}

/**
 * @brief Decrypt a Fernet token.
 * @param encMessage The base64-encoded encrypted token.
 * @param b64_key The base64-encoded Fernet key.
 * @return A newly allocated string with the decrypted message. The caller must free this memory. Returns NULL on failure (e.g., bad signature, wrong key).
 */
char* decrypt_message(const char* encMessage, const char* b64_key) {
    // 1. Decode the key
    int key_len;
    unsigned char* key = base64_decode(b64_key, &key_len);
    if (key_len != 32) {
        fprintf(stderr, "Invalid key size.\n");
        free(key);
        return NULL;
    }
    unsigned char* signing_key = key;
    unsigned char* encryption_key = key + 16;

    // 2. Base64 decode the encrypted message
    int decoded_len;
    unsigned char* decoded_token = base64_decode(encMessage, &decoded_len);

    // 3. Extract components and validate
    if (decoded_len < (1 + 8 + 16 + 32)) { // Version + Timestamp + IV + HMAC
        fprintf(stderr, "Invalid token length.\n");
        free(key);
        free(decoded_token);
        return NULL;
    }

    unsigned char version = decoded_token[0];
    if (version != 0x80) {
        fprintf(stderr, "Invalid token version.\n");
        free(key);
        free(decoded_token);
        return NULL;
    }

    // Note: A production implementation should also check the timestamp for freshness.
    // uint64_t timestamp = ...; if (time(NULL) - timestamp > TTL) { fail; }

    unsigned char* iv = decoded_token + 9;
    int ciphertext_len = decoded_len - (1 + 8 + 16 + 32);
    unsigned char* ciphertext = decoded_token + 25;
    unsigned char* received_hmac = decoded_token + 25 + ciphertext_len;

    // 4. Recalculate HMAC and verify signature
    int hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char* hmac_data = decoded_token; // The data to sign is the start of the token
    unsigned char calculated_hmac[32];
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, calculated_hmac, NULL);

    if (CRYPTO_memcmp(received_hmac, calculated_hmac, 32) != 0) {
        fprintf(stderr, "HMAC verification failed. Token may be tampered or key is wrong.\n");
        free(key);
        free(decoded_token);
        return NULL;
    }

    // 5. Decrypt the ciphertext
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;
    unsigned char* plaintext = malloc(ciphertext_len + 1); // +1 for null terminator

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv)) handleErrors();
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) handleErrors();
    plaintext_len = len;
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        // This can fail if padding is incorrect
        fprintf(stderr, "Decryption failed. Likely incorrect padding.\n");
        free(key);
        free(decoded_token);
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    plaintext_len += len;
    plaintext[plaintext_len] = '\0'; // Null-terminate the result
    EVP_CIPHER_CTX_free(ctx);

    // 6. Cleanup
    free(key);
    free(decoded_token);

    return (char*)plaintext;
}

int main() {
    const char* message = "the solutions are here somewhere";
    printf("Secret Message: %s\n", message);

    // In Python: key = Fernet.generate_key()
    char* key = generate_key();
    printf("Generated Key: %s\n\n", key);

    // In Python: encMessage = encrypt(message)
    char* encMessage = encrypt_message(message, key);
    if (encMessage) {
        printf("Encrypted Token: %s\n\n", encMessage);

        // In Python: decMessage = decrypt(encMessage)
        char* decMessage = decrypt_message(encMessage, key);
        if (decMessage) {
            printf("Decrypted Message: %s\n", decMessage);
            free(decMessage);
        }
        free(encMessage);
    }

    free(key);

    return 0;
}