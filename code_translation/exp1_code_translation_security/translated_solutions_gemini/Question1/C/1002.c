#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Define key and IV sizes for AES-256
#define AES_256_KEY_SIZE 32
#define AES_BLOCK_SIZE 16

// A helper function to handle OpenSSL errors
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

/**
 * @brief Encrypts plaintext using AES-256-CBC.
 *
 * @param plaintext The data to encrypt.
 * @param plaintext_len The length of the plaintext data.
 * @param key The 32-byte encryption key.
 * @param iv The 16-byte initialization vector.
 * @param ciphertext_len A pointer to an integer where the length of the resulting ciphertext will be stored.
 * @return A dynamically allocated buffer containing the ciphertext. The caller is responsible for freeing this buffer.
 */
unsigned char* encrypt_aes(const unsigned char *plaintext, int plaintext_len, const unsigned char *key, const unsigned char *iv, int *ciphertext_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int final_len;

    // The ciphertext can be up to one block larger than the plaintext due to padding
    unsigned char *ciphertext = malloc(plaintext_len + AES_BLOCK_SIZE);
    if (!ciphertext) {
        fprintf(stderr, "ERROR: malloc failed\n");
        abort();
    }

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // Initialize the encryption operation.
    // For AES-256-CBC, the cipher is EVP_aes_256_cbc()
    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    // Provide the message to be encrypted, and obtain the encrypted output.
    // EVP_EncryptUpdate can be called multiple times if the message is large.
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        handleErrors();
    }
    *ciphertext_len = len;

    // Finalize the encryption. This handles any remaining data and padding.
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &final_len)) {
        handleErrors();
    }
    *ciphertext_len += final_len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext;
}

/**
 * @brief Decrypts ciphertext using AES-256-CBC.
 *
 * @param ciphertext The data to decrypt.
 * @param ciphertext_len The length of the ciphertext data.
 * @param key The 32-byte decryption key.
 * @param iv The 16-byte initialization vector.
 * @param decrypted_len A pointer to an integer where the length of the resulting plaintext will be stored.
 * @return A dynamically allocated buffer containing the plaintext. The caller is responsible for freeing this buffer.
 */
unsigned char* decrypt_aes(const unsigned char *ciphertext, int ciphertext_len, const unsigned char *key, const unsigned char *iv, int *decrypted_len) {
    EVP_CIPHER_CTX *ctx;
    int len;
    int final_len;

    // The decrypted text will be at most the size of the ciphertext
    unsigned char *decrypted_text = malloc(ciphertext_len);
    if (!decrypted_text) {
        fprintf(stderr, "ERROR: malloc failed\n");
        abort();
    }

    // Create and initialize the context
    if (!(ctx = EVP_CIPHER_CTX_new())) {
        handleErrors();
    }

    // Initialize the decryption operation.
    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv)) {
        handleErrors();
    }

    // Provide the message to be decrypted, and obtain the plaintext output.
    if (1 != EVP_DecryptUpdate(ctx, decrypted_text, &len, ciphertext, ciphertext_len)) {
        handleErrors();
    }
    *decrypted_len = len;

    // Finalize the decryption. This will remove the padding.
    // A successful finalization for decryption means the padding was valid.
    if (1 != EVP_DecryptFinal_ex(ctx, decrypted_text + len, &final_len)) {
        handleErrors();
    }
    *decrypted_len += final_len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return decrypted_text;
}

/**
 * @brief Replicates the test function from the Python script.
 */
void test() {
    // Plaintext message
    const unsigned char *data = (const unsigned char *)"a secret message";
    int data_len = strlen((const char *)data);

    // Buffers for key and IV
    unsigned char key[AES_256_KEY_SIZE];
    unsigned char iv[AES_BLOCK_SIZE];

    // Generate a random key and IV (equivalent to os.urandom)
    if (1 != RAND_bytes(key, sizeof(key))) {
        handleErrors();
    }
    if (1 != RAND_bytes(iv, sizeof(iv))) {
        handleErrors();
    }

    printf("Plaintext: %s\n", data);
    printf("Plaintext length: %d\n\n", data_len);

    // Encrypt the data
    int ciphertext_len;
    unsigned char *ciphertext = encrypt_aes(data, data_len, key, iv, &ciphertext_len);
    printf("Ciphertext generated (length: %d)\n", ciphertext_len);

    // Decrypt the data
    int decrypted_len;
    unsigned char *decrypted_text = decrypt_aes(ciphertext, ciphertext_len, key, iv, &decrypted_len);
    printf("Decrypted text generated (length: %d)\n", decrypted_len);
    
    // Null-terminate the decrypted string for printing (optional, for demonstration)
    decrypted_text[decrypted_len] = '\0';
    printf("Decrypted text: %s\n\n", decrypted_text);

    // Assert that the decrypted text matches the original plaintext
    assert(decrypted_len == data_len);
    assert(memcmp(data, decrypted_text, data_len) == 0);

    printf("SUCCESS: Decrypted data matches original data.\n");

    // Free all dynamically allocated memory
    free(ciphertext);
    free(decrypted_text);
}

int main(void) {
    // Load the human-readable error strings for libcrypto
    ERR_load_crypto_strings();
    
    // Run the test
    test();

    // Clean up the error strings
    ERR_free_strings();

    return 0;
}