#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>

// Function to handle AES encoding
unsigned char* encode(const unsigned char* inp, const unsigned char* key, int inp_len, int* out_len) {
    // Initialize AES context
    AES_KEY aes_key;
    AES_set_encrypt_key(key, 128, &aes_key);

    // Calculate output length (padded to block size)
    int block_size = AES_BLOCK_SIZE;
    *out_len = (inp_len + block_size - 1) / block_size * block_size;

    // Allocate output buffer
    unsigned char* out = (unsigned char*)malloc(*out_len);
    if (out == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }

    // Perform AES encryption
    AES_cbc_encrypt(inp, out, inp_len, &aes_key, NULL, AES_ENCRYPT);

    return out;
}

// Function to handle AES decoding
unsigned char* decode(const unsigned char* inp, const unsigned char* key, int inp_len, int* out_len) {
    // Initialize AES context
    AES_KEY aes_key;
    AES_set_decrypt_key(key, 128, &aes_key);

    // Allocate output buffer
    *out_len = inp_len;
    unsigned char* out = (unsigned char*)malloc(*out_len);
    if (out == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }

    // Perform AES decryption
    AES_cbc_encrypt(inp, out, inp_len, &aes_key, NULL, AES_DECRYPT);

    return out;
}

int main() {
    // Input and key
    const char* inp_str = "1111111111111111";
    const char* key_str = "1111111111111111";
    int inp_len = strlen(inp_str);
    int key_len = strlen(key_str);

    // Convert input and key to unsigned char arrays
    unsigned char inp[inp_len];
    unsigned char key[key_len];
    strcpy((char*)inp, inp_str);
    strcpy((char*)key, key_str);

    // Encode input
    int encoded_len;
    unsigned char* encoded = encode(inp, key, inp_len, &encoded_len);

    // Decode encoded input
    int decoded_len;
    unsigned char* decoded = decode(encoded, key, encoded_len, &decoded_len);

    // Check if decoded matches original input
    if (strncmp((char*)decoded, (char*)inp, inp_len) == 0) {
        printf("Decoded matches original input\n");
    } else {
        printf("Decoded does not match original input\n");
    }

    // Free allocated memory
    free(encoded);
    free(decoded);

    return 0;
}