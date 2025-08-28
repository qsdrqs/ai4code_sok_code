#include <openssl/aes.h>
#include <string.h>
#include <stdio.h>

// Increment the counter block's lower 64 bits (treated as big-endian)
void increment_counter(unsigned char counter[16]) {
    for (int i = 15; i >= 8; i--) {
        counter[i]++;
        if (counter[i] != 0) {
            break;
        }
    }
}

// AES-256 CTR encryption function
void aes_256_ctr_encrypt(const unsigned char *key, const unsigned char *input, unsigned char *output, size_t len) {
    AES_KEY encrypt_key;
    AES_set_encrypt_key(key, 256, &encrypt_key);

    unsigned char counter[16] = {0}; // 8-byte nonce (0), 8-byte counter (0 initially)
    unsigned char encrypted_counter[16];
    size_t remaining = len;
    size_t offset = 0;

    while (remaining > 0) {
        increment_counter(counter); // Increment counter before each block

        AES_encrypt(counter, encrypted_counter, &encrypt_key);

        size_t block_size = (remaining > 16) ? 16 : remaining;

        for (size_t i = 0; i < block_size; ++i) {
            output[offset + i] = encrypted_counter[i] ^ input[offset + i];
        }

        remaining -= block_size;
        offset += block_size;
    }
}

// Decryption is the same as encryption in CTR mode
void aes_256_ctr_decrypt(const unsigned char *key, const unsigned char *input, unsigned char *output, size_t len) {
    aes_256_ctr_encrypt(key, input, output, len);
}

int main() {
    // Test vector from Python code
    unsigned char key[32] = "This_key_for_demo_purposes_only!"; // 32-byte key
    unsigned char plaintext[] = "Text may be any length you wish, no padding is required";
    size_t plaintext_len = strlen((char*)plaintext);

    // Encrypt
    size_t ciphertext_len = plaintext_len;
    unsigned char ciphertext[ciphertext_len];
    aes_256_ctr_encrypt(key, plaintext, ciphertext, plaintext_len);

    // Decrypt
    unsigned char decrypted[plaintext_len + 1]; // +1 for null terminator
    aes_256_ctr_decrypt(key, ciphertext, decrypted, ciphertext_len);
    decrypted[plaintext_len] = '\0'; // Null-terminate the decrypted string

    // Print result
    printf("%s\n", decrypted);

    return 0;
}