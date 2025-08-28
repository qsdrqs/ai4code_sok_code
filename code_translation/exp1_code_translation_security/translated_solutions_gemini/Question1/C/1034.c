#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>

/*
 ============================================================================
 DEPENDENCIES: Base64 and Crypto Implementation
 
 In Python, `cryptocode` handles encryption and encoding. In C, we must
 provide these functions ourselves. Below are self-contained implementations
 for Base64 and a simple XOR cipher to replicate the Python code's behavior.
 ============================================================================
*/

// --- Base64 Implementation (Public Domain) ---
// This is a standard, portable C implementation for Base64.

static char encoding_table[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                                'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                                'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                                'w', 'x', 'y', 'z', '0', '1', '2', '3',
                                '4', '5', '6', '7', '8', '9', '+', '/'};
static char *decoding_table = NULL;
static int mod_table[] = {0, 2, 1};

// Helper function to build the decoding table
void build_decoding_table() {
    decoding_table = malloc(256);
    for (int i = 0; i < 64; i++)
        decoding_table[(unsigned char) encoding_table[i]] = i;
}

// Encodes binary data into a Base64 string
char *base64_encode(const unsigned char *data, size_t input_length, size_t *output_length) {
    *output_length = 4 * ((input_length + 2) / 3);
    char *encoded_data = malloc(*output_length + 1); // +1 for null terminator
    if (encoded_data == NULL) return NULL;

    for (int i = 0, j = 0; i < input_length;) {
        uint32_t octet_a = i < input_length ? data[i++] : 0;
        uint32_t octet_b = i < input_length ? data[i++] : 0;
        uint32_t octet_c = i < input_length ? data[i++] : 0;
        uint32_t triple = (octet_a << 0x10) + (octet_b << 0x08) + octet_c;

        encoded_data[j++] = encoding_table[(triple >> 3 * 6) & 0x3F];
        encoded_data[j++] = encoding_table[(triple >> 2 * 6) & 0x3F];
        encoded_data[j++] = encoding_table[(triple >> 1 * 6) & 0x3F];
        encoded_data[j++] = encoding_table[(triple >> 0 * 6) & 0x3F];
    }

    for (int i = 0; i < mod_table[input_length % 3]; i++)
        encoded_data[*output_length - 1 - i] = '=';
        
    encoded_data[*output_length] = '\0';
    return encoded_data;
}

// Decodes a Base64 string into binary data
unsigned char *base64_decode(const char *data, size_t input_length, size_t *output_length) {
    if (decoding_table == NULL) build_decoding_table();
    if (input_length % 4 != 0) return NULL;

    *output_length = input_length / 4 * 3;
    if (data[input_length - 1] == '=') (*output_length)--;
    if (data[input_length - 2] == '=') (*output_length)--;

    unsigned char *decoded_data = malloc(*output_length + 1); // +1 for null terminator
    if (decoded_data == NULL) return NULL;

    for (int i = 0, j = 0; i < input_length;) {
        uint32_t sextet_a = data[i] == '=' ? 0 & i++ : decoding_table[(unsigned char)data[i++]];
        uint32_t sextet_b = data[i] == '=' ? 0 & i++ : decoding_table[(unsigned char)data[i++]];
        uint32_t sextet_c = data[i] == '=' ? 0 & i++ : decoding_table[(unsigned char)data[i++]];
        uint32_t sextet_d = data[i] == '=' ? 0 & i++ : decoding_table[(unsigned char)data[i++]];
        uint32_t triple = (sextet_a << 3 * 6) + (sextet_b << 2 * 6) + (sextet_c << 1 * 6) + (sextet_d << 0 * 6);

        if (j < *output_length) decoded_data[j++] = (triple >> 2 * 8) & 0xFF;
        if (j < *output_length) decoded_data[j++] = (triple >> 1 * 8) & 0xFF;
        if (j < *output_length) decoded_data[j++] = (triple >> 0 * 8) & 0xFF;
    }
    
    decoded_data[*output_length] = '\0';
    return decoded_data;
}

// Helper to clean up the decoding table
void base64_cleanup() {
    free(decoding_table);
}


// --- Simple XOR Cipher Implementation ---
// This cipher encrypts data by XORing it with a key.
// Applying the same function twice with the same key decrypts the data.
void xor_cipher(unsigned char *data, size_t data_len, const char *key) {
    size_t key_len = strlen(key);
    if (key_len == 0) return; // Cannot encrypt with an empty key

    for (size_t i = 0; i < data_len; i++) {
        data[i] = data[i] ^ key[i % key_len];
    }
}


/*
 ============================================================================
 C TRANSLATION OF THE PYTHON CODE
 ============================================================================
*/

/**
 * @brief Encrypts a string using XOR cipher and then encodes it with Base64.
 *
 * @param plain_text The string to encrypt.
 * @param key The encryption key.
 * @return A dynamically allocated, Base64-encoded string. The caller must free this memory.
 */
char* encode_str(const char* plain_text, const char* key) {
  // 1. Create a mutable copy of the plain_text to perform encryption on.
  size_t text_len = strlen(plain_text);
  unsigned char* data_to_encrypt = malloc(text_len + 1);
  if (!data_to_encrypt) return NULL;
  strcpy((char*)data_to_encrypt, plain_text);

  // 2. Encrypt the data in-place using XOR cipher.
  xor_cipher(data_to_encrypt, text_len, key);

  // 3. Encode the encrypted binary data into a Base64 string.
  size_t encoded_len;
  char* str_encoded = base64_encode(data_to_encrypt, text_len, &encoded_len);

  // 4. Clean up the temporary encrypted data buffer.
  free(data_to_encrypt);

  return str_encoded;
}

/**
 * @brief Decodes a Base64 string and then decrypts it using XOR cipher.
 *
 * @param str_encoded The Base64-encoded string to decrypt.
 * @param key The decryption key.
 * @return A dynamically allocated, plain-text string. The caller must free this memory.
 */
char* decode_str(const char* str_encoded, const char* key) {
  // 1. Decode the Base64 string back into its binary (encrypted) form.
  size_t encoded_len = strlen(str_encoded);
  size_t decoded_len;
  unsigned char* data_to_decrypt = base64_decode(str_encoded, encoded_len, &decoded_len);
  if (!data_to_decrypt) return NULL;

  // 2. Decrypt the data in-place using the same XOR cipher.
  // XORing a second time with the same key restores the original data.
  xor_cipher(data_to_decrypt, decoded_len, key);

  // The result is the original plain text.
  return (char*)data_to_decrypt;
}

// Main function to demonstrate the usage, equivalent to the Python script.
int main() {
    const char* original_string = "I am okay";
    const char* secret_key = "wow";

    printf("Original string: %s\n", original_string);
    printf("Key: %s\n\n", secret_key);

    // Encode the string
    char* str_encoded1 = encode_str(original_string, secret_key);
    if (str_encoded1) {
        printf("Encoded (XOR + Base64): %s\n", str_encoded1);
    } else {
        printf("Encoding failed.\n");
        return 1;
    }

    // Decode the string
    char* str_decoded1 = decode_str(str_encoded1, secret_key);
    if (str_decoded1) {
        printf("Decoded string: %s\n", str_decoded1);
    } else {
        printf("Decoding failed.\n");
        free(str_encoded1); // Clean up even if decoding fails
        return 1;
    }

    // --- Final check and cleanup ---
    if (strcmp(original_string, str_decoded1) == 0) {
        printf("\nSuccess: The decoded string matches the original.\n");
    } else {
        printf("\nError: The decoded string does NOT match the original.\n");
    }

    // IMPORTANT: Free all dynamically allocated memory
    free(str_encoded1);
    free(str_decoded1);
    base64_cleanup(); // Free the memory used by the Base64 decoding table

    return 0;
}