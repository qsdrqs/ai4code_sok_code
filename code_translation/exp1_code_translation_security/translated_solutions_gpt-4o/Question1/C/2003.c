#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>

// Function to encode a string using AES key
void encode(const unsigned char *inp, const unsigned char *key, unsigned char *out) {
    AES_KEY encryptKey;
    AES_set_encrypt_key(key, 128, &encryptKey); // Set the encryption key (128-bit)
    AES_encrypt(inp, out, &encryptKey);        // Perform AES encryption
}

// Function to decode a string using AES key
void decode(const unsigned char *inp, const unsigned char *key, unsigned char *out) {
    AES_KEY decryptKey;
    AES_set_decrypt_key(key, 128, &decryptKey); // Set the decryption key (128-bit)
    AES_decrypt(inp, out, &decryptKey);        // Perform AES decryption
}

int main() {
    // Input and key (16 bytes each for AES-128)
    unsigned char inp[16] = "1111111111111111";
    unsigned char key[16] = "1111111111111111";

    // Buffers for encoded and decoded output
    unsigned char encoded[16];
    unsigned char decoded[16];

    // Encode the input
    encode(inp, key, encoded);

    // Decode the encoded data
    decode(encoded, key, decoded);

    // Verify that the decoded output matches the original input
    if (memcmp(inp, decoded, 16) == 0) {
        printf("Success: Decoded output matches the original input.\n");
    } else {
        printf("Error: Decoded output does not match the original input.\n");
    }

    return 0;
}