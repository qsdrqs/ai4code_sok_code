#include <stdio.h>
#include <string.h>

// Simple XOR encryption/decryption function
void xor_encrypt_decrypt(char *input, char *key, char *output) {
    size_t len = strlen(input);
    size_t key_len = strlen(key);
    for (size_t i = 0; i < len; i++) {
        output[i] = input[i] ^ key[i % key_len];
    }
    output[len] = '\0'; // Null-terminate the output string
}

int main() {
    char plain_text[] = "I am okay";
    char key[] = "wow";
    char str_encoded[100]; // Buffer to hold the encoded string
    char str_decoded[100]; // Buffer to hold the decoded string

    // Encode the string
    xor_encrypt_decrypt(plain_text, key, str_encoded);
    printf("Encoded: %s\n", str_encoded);

    // Decode the string
    xor_encrypt_decrypt(str_encoded, key, str_decoded);
    printf("Decoded: %s\n", str_decoded);

    return 0;
}