#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/err.h>

// Function to encode a string using AES encryption
char* encode_str(const char* plain_text, const char* key) {
    // Initialize variables
    int len = strlen(plain_text);
    int encoded_len = len + AES_BLOCK_SIZE;
    char* str_encoded = (char*)malloc(encoded_len);

    // Create a new AES context
    AES_KEY aes_key;
    AES_set_encrypt_key((const unsigned char*)key, 128, &aes_key);

    // Pad the plain text to a multiple of the block size
    char* padded_text = (char*)malloc(len + AES_BLOCK_SIZE);
    memcpy(padded_text, plain_text, len);
    for (int i = len; i < encoded_len; i++) {
        padded_text[i] = AES_BLOCK_SIZE - (len % AES_BLOCK_SIZE);
    }

    // Encrypt the padded text
    unsigned char* encrypted_text = (unsigned char*)malloc(encoded_len);
    for (int i = 0; i < encoded_len; i += AES_BLOCK_SIZE) {
        AES_cbc_encrypt((const unsigned char*)padded_text + i, encrypted_text + i, AES_BLOCK_SIZE, &aes_key, (unsigned char*)key, AES_ENCRYPT);
    }

    // Convert the encrypted text to a hexadecimal string
    for (int i = 0; i < encoded_len; i++) {
        sprintf(str_encoded + (i * 2), "%02x", encrypted_text[i]);
    }

    // Free allocated memory
    free(padded_text);
    free(encrypted_text);

    return str_encoded;
}

// Function to decode a string using AES decryption
char* decode_str(const char* str_encoded, const char* key) {
    // Initialize variables
    int len = strlen(str_encoded);
    int decoded_len = len / 2;
    char* str_decoded = (char*)malloc(decoded_len);

    // Convert the hexadecimal string to an encrypted byte array
    unsigned char* encrypted_text = (unsigned char*)malloc(decoded_len);
    for (int i = 0; i < decoded_len; i++) {
        sscanf(str_encoded + (i * 2), "%2hhx", &encrypted_text[i]);
    }

    // Create a new AES context
    AES_KEY aes_key;
    AES_set_decrypt_key((const unsigned char*)key, 128, &aes_key);

    // Decrypt the encrypted text
    char* decrypted_text = (char*)malloc(decoded_len);
    for (int i = 0; i < decoded_len; i += AES_BLOCK_SIZE) {
        AES_cbc_encrypt(encrypted_text + i, (unsigned char*)decrypted_text + i, AES_BLOCK_SIZE, &aes_key, (unsigned char*)key, AES_DECRYPT);
    }

    // Remove padding from the decrypted text
    for (int i = decoded_len - 1; i >= 0; i--) {
        if (decrypted_text[i] <= AES_BLOCK_SIZE && decrypted_text[i] > 0) {
            decoded_len = i + 1;
            break;
        }
    }

    // Copy the decrypted text to the result
    memcpy(str_decoded, decrypted_text, decoded_len);
    str_decoded[decoded_len] = '\0';

    // Free allocated memory
    free(encrypted_text);
    free(decrypted_text);

    return str_decoded;
}

int main() {
    const char* plain_text = "I am okay";
    const char* key = "wow";

    // Encode the plain text
    char* str_encoded = encode_str(plain_text, key);
    printf("Encoded string: %s\n", str_encoded);

    // Decode the encoded string
    char* str_decoded = decode_str(str_encoded, key);
    printf("Decoded string: %s\n", str_decoded);

    // Free allocated memory
    free(str_encoded);
    free(str_decoded);

    return 0;
}