#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/evp.h>
#include <openssl/err.h>

// A helper function to handle OpenSSL errors
void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

// A utility function to print data in hex format (for viewing ciphertext)
void print_hex(const char* label, const unsigned char* data, int len) {
    printf("%s: ", label);
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Encrypts a message using a pre-initialized EVP_CIPHER_CTX.
 * @param ctx The cipher context, already initialized for encryption.
 * @param plaintext The message to encrypt.
 * @param plaintext_len The length of the message.
 * @param ciphertext Buffer where the encrypted output will be written.
 * @return The length of the generated ciphertext.
 */
int encrypt_string(EVP_CIPHER_CTX *ctx, const unsigned char *plaintext, int plaintext_len, unsigned char *ciphertext) {
    int len;
    int ciphertext_len;

    // Perform the encryption. EVP_EncryptUpdate can be called multiple times
    // if the data is streamed.
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        handleErrors();
    }
    ciphertext_len = len;

    // Finalize the encryption. For CFB mode, this typically doesn't add
    // any extra data, but it's required to clean up the context.
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        handleErrors();
    }
    ciphertext_len += len;

    return ciphertext_len;
}

/**
 * @brief Decrypts a ciphertext using a pre-initialized EVP_CIPHER_CTX.
 * @param ctx The cipher context, already initialized for decryption.
 * @param ciphertext The encrypted data to decrypt.
 * @param ciphertext_len The length of the encrypted data.
 * @param plaintext Buffer where the decrypted output will be written.
 * @return The length of the recovered plaintext.
 */
int decrypt_string(EVP_CIPHER_CTX *ctx, const unsigned char *ciphertext, int ciphertext_len, unsigned char *plaintext) {
    int len;
    int plaintext_len;

    // Perform the decryption.
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        handleErrors();
    }
    plaintext_len = len;

    // Finalize the decryption.
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        handleErrors();
    }
    plaintext_len += len;

    return plaintext_len;
}

// Border line since we are sending an obj that is derived from a key rather a plaintext key
int main(void) {
    // Key and IV must be 16 bytes for AES-128.
    // In C, these are represented as arrays of unsigned characters.
    unsigned char *key = (unsigned char *)"This is a key123";
    unsigned char *iv = (unsigned char *)"This is an IV456";
    
    // The message to be encrypted.
    unsigned char *message = (unsigned char *)"The answer is no";
    int message_len = strlen((const char*)message);

    // Buffers for the ciphertext and the decrypted text.
    // The ciphertext buffer should be slightly larger than the plaintext to
    // account for any block padding (though CFB mode doesn't use padding).
    unsigned char ciphertext[128];
    unsigned char decrypted_text[128];

    int ciphertext_len;
    int decrypted_len;

    // In C with OpenSSL, the "object" from the Python code is an EVP_CIPHER_CTX.
    // It holds the state of the cipher (key, IV, mode, etc.).
    EVP_CIPHER_CTX *enc_ctx, *dec_ctx;

    // Create and initialize the encryption context object (obj1 in Python)
    if (!(enc_ctx = EVP_CIPHER_CTX_new())) handleErrors();
    // Initialize the encryption operation.
    // We use AES-128 in CFB mode.
    if (1 != EVP_EncryptInit_ex(enc_ctx, EVP_aes_128_cfb(), NULL, key, iv)) {
        handleErrors();
    }

    // Create and initialize the decryption context object (obj2 in Python)
    if (!(dec_ctx = EVP_CIPHER_CTX_new())) handleErrors();
    // Initialize the decryption operation with the same key and IV.
    if (1 != EVP_DecryptInit_ex(dec_ctx, EVP_aes_128_cfb(), NULL, key, iv)) {
        handleErrors();
    }

    // Encrypt the message using the encryption context
    ciphertext_len = encrypt_string(enc_ctx, message, message_len, ciphertext);
    printf("Original Message: %s\n", message);
    print_hex("Ciphertext (hex)", ciphertext, ciphertext_len);

    // Decrypt the message using the decryption context
    decrypted_len = decrypt_string(dec_ctx, ciphertext, ciphertext_len, decrypted_text);
    
    // Add a null terminator to the decrypted string so it can be printed
    decrypted_text[decrypted_len] = '\0';
    printf("Decrypted Message: %s\n", decrypted_text);

    // Clean up the contexts
    EVP_CIPHER_CTX_free(enc_ctx);
    EVP_CIPHER_CTX_free(dec_ctx);

    return 0;
}