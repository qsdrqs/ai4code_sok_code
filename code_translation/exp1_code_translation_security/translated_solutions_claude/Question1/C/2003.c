#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <assert.h>

int encode(const unsigned char* inp, int inp_len, const unsigned char* key, unsigned char* out) {
    /**
     * Encodes data using an AES key in ECB mode.
     * Returns the length of encrypted data, or -1 on error.
     */
    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialize the context
    if(!(ctx = EVP_CIPHER_CTX_new())) {
        return -1;
    }

    // Initialize the encryption operation with AES-128-ECB
    if(1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, key, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // Disable padding for ECB mode to match Python's behavior
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    // Provide the message to be encrypted, and obtain the encrypted output
    if(1 != EVP_EncryptUpdate(ctx, out, &len, inp, inp_len)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len = len;

    // Finalize the encryption
    if(1 != EVP_EncryptFinal_ex(ctx, out + len, &len)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}

int decode(const unsigned char* inp, int inp_len, const unsigned char* key, unsigned char* out) {
    /**
     * Decodes data using an AES key in ECB mode.
     * Returns the length of decrypted data, or -1 on error.
     */
    EVP_CIPHER_CTX *ctx;
    int len;
    int plaintext_len;

    // Create and initialize the context
    if(!(ctx = EVP_CIPHER_CTX_new())) {
        return -1;
    }

    // Initialize the decryption operation with AES-128-ECB
    if(1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, key, NULL)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }

    // Disable padding for ECB mode to match Python's behavior
    EVP_CIPHER_CTX_set_padding(ctx, 0);

    // Provide the message to be decrypted, and obtain the plaintext output
    if(1 != EVP_DecryptUpdate(ctx, out, &len, inp, inp_len)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len = len;

    // Finalize the decryption
    if(1 != EVP_DecryptFinal_ex(ctx, out + len, &len)) {
        EVP_CIPHER_CTX_free(ctx);
        return -1;
    }
    plaintext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}

int main() {
    unsigned char inp[] = "1111111111111111";
    unsigned char key[] = "1111111111111111";
    unsigned char encrypted[16];
    unsigned char decrypted[16];
    
    int inp_len = 16;  // Length of input data
    
    // Encode the input
    int encrypted_len = encode(inp, inp_len, key, encrypted);
    if (encrypted_len == -1) {
        printf("Encryption failed\n");
        return 1;
    }
    
    // Decode the encrypted data
    int decrypted_len = decode(encrypted, encrypted_len, key, decrypted);
    if (decrypted_len == -1) {
        printf("Decryption failed\n");
        return 1;
    }
    
    // Verify that decode(encode(inp, key), key) == inp
    assert(decrypted_len == inp_len);
    assert(memcmp(decrypted, inp, inp_len) == 0);
    
    printf("Test passed: decode(encode(inp, key), key) == inp\n");
    
    return 0;
}