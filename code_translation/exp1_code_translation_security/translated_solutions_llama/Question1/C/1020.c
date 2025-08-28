#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <openssl/aes.h>
#include <openssl/rand.h>

// Function to generate a random 16-byte IV
void generate_iv(unsigned char *iv) {
    if (RAND_bytes(iv, 16) != 1) {
        printf("Error generating random IV\n");
        exit(1);
    }
}

// Function to pad plaintext to a multiple of the block size (16 bytes for AES)
unsigned char *pad(unsigned char *plaintext, int plaintext_len, int *padded_len) {
    int block_size = 16;
    int padding_len = block_size - (plaintext_len % block_size);
    *padded_len = plaintext_len + padding_len;

    unsigned char *padded_txt = (unsigned char *)malloc(*padded_len);
    if (!padded_txt) {
        printf("Error allocating memory for padded text\n");
        exit(1);
    }

    memcpy(padded_txt, plaintext, plaintext_len);
    for (int i = 0; i < padding_len; i++) {
        padded_txt[plaintext_len + i] = padding_len;
    }

    return padded_txt;
}

// Function to unpad decrypted text
unsigned char *unpad(unsigned char *decrypted_txt, int decrypted_len) {
    int padding_len = decrypted_txt[decrypted_len - 1];

    if (padding_len < 1 || padding_len > 16) {
        printf("Invalid padding\n");
        exit(1);
    }

    int unpadded_len = decrypted_len - padding_len;
    unsigned char *unpadded_txt = (unsigned char *)malloc(unpadded_len);
    if (!unpadded_txt) {
        printf("Error allocating memory for unpadded text\n");
        exit(1);
    }

    memcpy(unpadded_txt, decrypted_txt, unpadded_len);

    return unpadded_txt;
}

// Function to encode base64
void base64_encode(unsigned char *input, int input_len, char **output) {
    static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    int output_len = ((input_len + 2) / 3) * 4;
    *output = (char *)malloc(output_len + 1);
    if (!*output) {
        printf("Error allocating memory for base64 encoded output\n");
        exit(1);
    }

    int i = 0, j = 0;
    while (i < input_len) {
        int val = (input[i++] << 16);
        if (i < input_len) {
            val |= (input[i++] << 8);
        } else {
            val |= 0xFF00;
        }
        if (i < input_len) {
            val |= input[i++];
        } else {
            val |= 0xFF;
        }

        (*output)[j++] = base64_chars[(val & 0x00FC0000) >> 18];
        (*output)[j++] = base64_chars[(val & 0x0003F000) >> 12];
        if (i < input_len) {
            (*output)[j++] = base64_chars[(val & 0x00000FC0) >> 6];
        } else {
            (*output)[j++] = '=';
        }
        if (i < input_len) {
            (*output)[j++] = base64_chars[val & 0x0000003F];
        } else {
            (*output)[j++] = '=';
        }
    }
    (*output)[output_len] = '\0';
}

// Function to decode base64
unsigned char *base64_decode(char *input, int *output_len) {
    static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    int input_len = strlen(input);
    int output_buf_len = (input_len * 3) / 4;
    unsigned char *output_buf = (unsigned char *)malloc(output_buf_len);
    if (!output_buf) {
        printf("Error allocating memory for base64 decoded output\n");
        exit(1);
    }

    int i = 0, j = 0;
    while (i < input_len) {
        int val = 0;
        for (int k = 0; k < 4; k++) {
            if (input[i] == '=') {
                break;
            }
            int idx = -1;
            for (int l = 0; l < 64; l++) {
                if (base64_chars[l] == input[i]) {
                    idx = l;
                    break;
                }
            }
            if (idx == -1) {
                printf("Invalid base64 character\n");
                exit(1);
            }
            val = (val << 6) | idx;
            i++;
        }

        output_buf[j++] = (val >> 16) & 0xFF;
        if (i < input_len && input[i] != '=') {
            output_buf[j++] = (val >> 8) & 0xFF;
        }
        if (i < input_len && input[i] != '=') {
            output_buf[j++] = val & 0xFF;
        }
    }

    *output_len = j;
    return output_buf;
}

// Function to encrypt plaintext using a symmetric key
unsigned char *encrypt(unsigned char *key, int key_size, unsigned char *plaintext, int plaintext_len, int *ciphertext_len) {
    // Generate a random 16-byte IV
    unsigned char iv[16];
    generate_iv(iv);

    // Pad plaintext to a multiple of the block size
    int padded_len;
    unsigned char *padded_txt = pad(plaintext, plaintext_len, &padded_len);

    // Create an AES cipher context
    AES_KEY aes_key;
    if (AES_set_encrypt_key(key, key_size * 8, &aes_key) != 0) {
        printf("Error setting AES encrypt key\n");
        exit(1);
    }

    // Encrypt padded plaintext
    unsigned char *ciphertext = (unsigned char *)malloc(padded_len);
    if (!ciphertext) {
        printf("Error allocating memory for ciphertext\n");
        exit(1);
    }
    AES_cbc_encrypt(padded_txt, ciphertext, padded_len, &aes_key, iv, AES_ENCRYPT);

    // Prepend IV to ciphertext
    unsigned char *iv_ciphertext = (unsigned char *)malloc(16 + padded_len);
    if (!iv_ciphertext) {
        printf("Error allocating memory for IV and ciphertext\n");
        exit(1);
    }
    memcpy(iv_ciphertext, iv, 16);
    memcpy(iv_ciphertext + 16, ciphertext, padded_len);

    // Base64 encode IV and ciphertext
    char *base64_encoded;
    base64_encode(iv_ciphertext, 16 + padded_len, &base64_encoded);

    // Free allocated memory
    free(padded_txt);
    free(ciphertext);
    free(iv_ciphertext);

    *ciphertext_len = strlen(base64_encoded);
    return (unsigned char *)base64_encoded;
}

// Function to decrypt ciphertext using a symmetric key
unsigned char *decrypt(unsigned char *key, int key_size, unsigned char *ciphertext, int *plaintext_len) {
    // Base64 decode ciphertext
    int decoded_len;
    unsigned char *decoded_ciphertext = base64_decode((char *)ciphertext, &decoded_len);

    // Extract IV and ciphertext
    unsigned char *iv = decoded_ciphertext;
    unsigned char *ciphertext_buf = decoded_ciphertext + 16;
    int ciphertext_len = decoded_len - 16;

    // Create an AES cipher context
    AES_KEY aes_key;
    if (AES_set_decrypt_key(key, key_size * 8, &aes_key) != 0) {
        printf("Error setting AES decrypt key\n");
        exit(1);
    }

    // Decrypt ciphertext
    unsigned char *decrypted_txt = (unsigned char *)malloc(ciphertext_len);
    if (!decrypted_txt) {
        printf("Error allocating memory for decrypted text\n");
        exit(1);
    }
    AES_cbc_encrypt(ciphertext_buf, decrypted_txt, ciphertext_len, &aes_key, iv, AES_DECRYPT);

    // Unpad decrypted text
    unsigned char *unpadded_txt = unpad(decrypted_txt, ciphertext_len);

    // Free allocated memory
    free(decrypted_txt);
    free(decoded_ciphertext);

    *plaintext_len = strlen((char *)unpadded_txt);
    return unpadded_txt;
}

int main() {
    // Example usage
    unsigned char key[32] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                             0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F,
                             0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17,
                             0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F};
    unsigned char plaintext[] = "Hello, World!";
    int key_size = 32;
    int ciphertext_len;
    unsigned char *ciphertext = encrypt(key, key_size, plaintext, strlen((char *)plaintext), &ciphertext_len);
    printf("Ciphertext: %s\n", ciphertext);

    int plaintext_len;
    unsigned char *decrypted_txt = decrypt(key, key_size, ciphertext, &plaintext_len);
    printf("Decrypted text: %s\n", decrypted_txt);

    free(ciphertext);
    free(decrypted_txt);

    return 0;
}