#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/evp.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// Function prototypes
char* b64encode(const unsigned char *data, int len);
int b64decode(const char *str, unsigned char **data);
char* generate_secret_key_for_AES_cipher();
char* encrypt_message(const char* private_msg, const char* encoded_secret_key, char padding_character);
char* decrypt_message(const char* encoded_encrypted_msg, const char* encoded_secret_key, char padding_character);

// Base64 encode - returns null-terminated string
char* b64encode(const unsigned char *data, int len) {
    int encoded_len = ((len + 2) / 3) * 4 + 1; // +1 for null terminator
    char *str = malloc(encoded_len);
    if (!str) return NULL;
    EVP_EncodeBlock(str, data, len);
    return str;
}

// Base64 decode - returns decoded data length or -1 on error
int b64decode(const char *str, unsigned char **data) {
    int len = strlen(str);
    int decoded_len = EVP_DecodeLength(str);
    if (decoded_len < 0) {
        *data = NULL;
        return -1;
    }
    *data = malloc(decoded_len);
    if (!*data) return -1;
    int out_len = EVP_DecodeBlock(*data, str, len);
    if (out_len < 0) {
        free(*data);
        *data = NULL;
        return -1;
    }
    return out_len;
}

// Generate a 16-byte secret key and return base64 encoded
char* generate_secret_key_for_AES_cipher() {
    unsigned char secret_key[16];
    RAND_bytes(secret_key, 16);
    return b64encode(secret_key, 16);
}

// Encrypt message
char* encrypt_message(const char* private_msg, const char* encoded_secret_key, char padding_character) {
    // Decode secret key
    unsigned char *secret_key;
    int secret_key_len = b64decode(encoded_secret_key, &secret_key);
    if (secret_key_len != 16) {
        if (secret_key) free(secret_key);
        return NULL;
    }

    // Calculate pad length
    int msg_len = strlen(private_msg);
    int pad_len = (16 - (msg_len % 16)) % 16;

    // Create padded message
    char *padded_msg = malloc(msg_len + pad_len + 1);
    if (!padded_msg) {
        free(secret_key);
        return NULL;
    }
    strcpy(padded_msg, private_msg);
    memset(padded_msg + msg_len, padding_character, pad_len);
    padded_msg[msg_len + pad_len] = '\0';

    // Encrypt
    AES_KEY aes_key;
    AES_set_encrypt_key(secret_key, 128, &aes_key);
    int encrypted_len = msg_len + pad_len;
    unsigned char *encrypted_data = malloc(encrypted_len);
    if (!encrypted_data) {
        free(secret_key);
        free(padded_msg);
        return NULL;
    }

    for (int i = 0; i < encrypted_len; i += 16) {
        AES_encrypt((const unsigned char*)padded_msg + i, encrypted_data + i, &aes_key);
    }

    // Base64 encode encrypted data
    char *encoded_encrypted_msg = b64encode(encrypted_data, encrypted_len);

    // Cleanup
    free(secret_key);
    free(padded_msg);
    free(encrypted_data);

    return encoded_encrypted_msg;
}

// Decrypt message
char* decrypt_message(const char* encoded_encrypted_msg, const char* encoded_secret_key, char padding_character) {
    // Decode secret key and encrypted message
    unsigned char *secret_key;
    int secret_key_len = b64decode(encoded_secret_key, &secret_key);
    if (secret_key_len != 16) {
        if (secret_key) free(secret_key);
        return NULL;
    }

    unsigned char *encrypted_data;
    int encrypted_data_len = b64decode(encoded_encrypted_msg, &encrypted_data);
    if (encrypted_data_len < 0 || encrypted_data_len % 16 != 0) {
        free(secret_key);
        if (encrypted_data) free(encrypted_data);
        return NULL;
    }

    // Decrypt
    AES_KEY aes_key;
    AES_set_decrypt_key(secret_key, 128, &aes_key);
    int num_blocks = encrypted_data_len / 16;
    unsigned char *decrypted_data = malloc(encrypted_data_len);
    if (!decrypted_data) {
        free(secret_key);
        free(encrypted_data);
        return NULL;
    }

    for (int i = 0; i < num_blocks; i++) {
        AES_decrypt(encrypted_data + (i * 16), decrypted_data + (i * 16), &aes_key);
    }

    // Convert to string and add null terminator
    char *decrypted_str = malloc(encrypted_data_len + 1);
    if (!decrypted_str) {
        free(secret_key);
        free(encrypted_data);
        free(decrypted_data);
        return NULL;
    }
    memcpy(decrypted_str, decrypted_data, encrypted_data_len);
    decrypted_str[encrypted_data_len] = '\0'; // Null terminate

    // Unpad
    int len = encrypted_data_len;
    while (len > 0 && decrypted_str[len - 1] == padding_character) {
        len--;
    }
    decrypted_str[len] = '\0';

    // Cleanup
    free(secret_key);
    free(encrypted_data);
    free(decrypted_data);

    return decrypted_str;
}

int main() {
    const char *private_msg = "\n My test string";
    char padding_character = '{';

    char *secret_key = generate_secret_key_for_AES_cipher();
    if (!secret_key) {
        fprintf(stderr, "Failed to generate secret key\n");
        return 1;
    }

    char *encrypted_msg = encrypt_message(private_msg, secret_key, padding_character);
    if (!encrypted_msg) {
        fprintf(stderr, "Encryption failed\n");
        free(secret_key);
        return 1;
    }

    char *decrypted_msg = decrypt_message(encrypted_msg, secret_key, padding_character);
    if (!decrypted_msg) {
        fprintf(stderr, "Decryption failed\n");
        free(secret_key);
        free(encrypted_msg);
        return 1;
    }

    printf("   Secret Key: %s - (%d)\n", secret_key, (int)strlen(secret_key));
    printf("Encrypted Msg: %s - (%d)\n", encrypted_msg, (int)strlen(encrypted_msg));
    printf("Decrypted Msg: %s - (%d)\n", decrypted_msg, (int)strlen(decrypted_msg));

    // Free allocated memory
    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);

    return 0;
}