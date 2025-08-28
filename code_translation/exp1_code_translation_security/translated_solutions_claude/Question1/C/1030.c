#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/evp.h>

// Base64 encoding table
static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

// Base64 encoding function
char* base64_encode(const unsigned char* input, int length) {
    int encoded_length = 4 * ((length + 2) / 3);
    char* encoded = malloc(encoded_length + 1);
    if (!encoded) return NULL;
    
    int i, j;
    for (i = 0, j = 0; i < length;) {
        uint32_t a = i < length ? input[i++] : 0;
        uint32_t b = i < length ? input[i++] : 0;
        uint32_t c = i < length ? input[i++] : 0;
        uint32_t triple = (a << 16) + (b << 8) + c;
        
        encoded[j++] = base64_chars[(triple >> 18) & 63];
        encoded[j++] = base64_chars[(triple >> 12) & 63];
        encoded[j++] = base64_chars[(triple >> 6) & 63];
        encoded[j++] = base64_chars[triple & 63];
    }
    
    // Add padding
    for (i = 0; i < (3 - length % 3) % 3; i++) {
        encoded[encoded_length - 1 - i] = '=';
    }
    
    encoded[encoded_length] = '\0';
    return encoded;
}

// Base64 decoding function
unsigned char* base64_decode(const char* input, int* output_length) {
    int input_length = strlen(input);
    if (input_length % 4 != 0) return NULL;
    
    *output_length = input_length / 4 * 3;
    if (input[input_length - 1] == '=') (*output_length)--;
    if (input[input_length - 2] == '=') (*output_length)--;
    
    unsigned char* decoded = malloc(*output_length);
    if (!decoded) return NULL;
    
    // Create decode table
    int decode_table[256];
    for (int i = 0; i < 256; i++) decode_table[i] = -1;
    for (int i = 0; i < 64; i++) decode_table[(int)base64_chars[i]] = i;
    
    for (int i = 0, j = 0; i < input_length;) {
        uint32_t a = input[i] == '=' ? 0 & i++ : decode_table[(int)input[i++]];
        uint32_t b = input[i] == '=' ? 0 & i++ : decode_table[(int)input[i++]];
        uint32_t c = input[i] == '=' ? 0 & i++ : decode_table[(int)input[i++]];
        uint32_t d = input[i] == '=' ? 0 & i++ : decode_table[(int)input[i++]];
        uint32_t triple = (a << 18) + (b << 12) + (c << 6) + d;
        
        if (j < *output_length) decoded[j++] = (triple >> 16) & 0xFF;
        if (j < *output_length) decoded[j++] = (triple >> 8) & 0xFF;
        if (j < *output_length) decoded[j++] = triple & 0xFF;
    }
    
    return decoded;
}

// Generate secret key for AES cipher
char* generate_secret_key_for_AES_cipher() {
    const int AES_key_length = 16; // use larger value in production
    unsigned char secret_key[AES_key_length];
    
    // Generate random secret key
    if (RAND_bytes(secret_key, AES_key_length) != 1) {
        return NULL;
    }
    
    // Encode secret key
    char* encoded_secret_key = base64_encode(secret_key, AES_key_length);
    return encoded_secret_key;
}

// Function 1: Encrypt message
char* encrypt_message(const char* private_msg, const char* encoded_secret_key, char padding_character) {
    // Decode the encoded secret key
    int secret_key_len;
    unsigned char* secret_key = base64_decode(encoded_secret_key, &secret_key_len);
    if (!secret_key) return NULL;
    
    // Pad the private message
    int msg_len = strlen(private_msg);
    int padded_len = msg_len + ((16 - msg_len) % 16);
    char* padded_private_msg = malloc(padded_len + 1);
    if (!padded_private_msg) {
        free(secret_key);
        return NULL;
    }
    
    strcpy(padded_private_msg, private_msg);
    for (int i = msg_len; i < padded_len; i++) {
        padded_private_msg[i] = padding_character;
    }
    padded_private_msg[padded_len] = '\0';
    
    // Create AES cipher and encrypt
    AES_KEY aes_key;
    AES_set_encrypt_key(secret_key, 128, &aes_key);
    
    unsigned char* encrypted_msg = malloc(padded_len);
    if (!encrypted_msg) {
        free(secret_key);
        free(padded_private_msg);
        return NULL;
    }
    
    // Encrypt in 16-byte blocks
    for (int i = 0; i < padded_len; i += 16) {
        AES_encrypt((unsigned char*)padded_private_msg + i, encrypted_msg + i, &aes_key);
    }
    
    // Encode encrypted message
    char* encoded_encrypted_msg = base64_encode(encrypted_msg, padded_len);
    
    // Cleanup
    free(secret_key);
    free(padded_private_msg);
    free(encrypted_msg);
    
    return encoded_encrypted_msg;
}

// Function 2: Decrypt message
char* decrypt_message(const char* encoded_encrypted_msg, const char* encoded_secret_key, char padding_character) {
    // Decode the encoded encrypted message and encoded secret key
    int secret_key_len;
    unsigned char* secret_key = base64_decode(encoded_secret_key, &secret_key_len);
    if (!secret_key) return NULL;
    
    int encrypted_msg_len;
    unsigned char* encrypted_msg = base64_decode(encoded_encrypted_msg, &encrypted_msg_len);
    if (!encrypted_msg) {
        free(secret_key);
        return NULL;
    }
    
    // Create AES cipher and decrypt
    AES_KEY aes_key;
    AES_set_decrypt_key(secret_key, 128, &aes_key);
    
    unsigned char* decrypted_msg = malloc(encrypted_msg_len + 1);
    if (!decrypted_msg) {
        free(secret_key);
        free(encrypted_msg);
        return NULL;
    }
    
    // Decrypt in 16-byte blocks
    for (int i = 0; i < encrypted_msg_len; i += 16) {
        AES_decrypt(encrypted_msg + i, decrypted_msg + i, &aes_key);
    }
    decrypted_msg[encrypted_msg_len] = '\0';
    
    // Unpad the decrypted message
    char* unpadded_private_msg = malloc(encrypted_msg_len + 1);
    if (!unpadded_private_msg) {
        free(secret_key);
        free(encrypted_msg);
        free(decrypted_msg);
        return NULL;
    }
    
    strcpy(unpadded_private_msg, (char*)decrypted_msg);
    
    // Remove padding characters from the end
    int len = strlen(unpadded_private_msg);
    while (len > 0 && unpadded_private_msg[len - 1] == padding_character) {
        unpadded_private_msg[len - 1] = '\0';
        len--;
    }
    
    // Cleanup
    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);
    
    return unpadded_private_msg;
}

####### BEGIN HERE #######

int main() {
    const char* private_msg = "\n My test string\n";
    char padding_character = '{';
    
    char* secret_key = generate_secret_key_for_AES_cipher();
    if (!secret_key) {
        printf("Failed to generate secret key\n");
        return 1;
    }
    
    char* encrypted_msg = encrypt_message(private_msg, secret_key, padding_character);
    if (!encrypted_msg) {
        printf("Failed to encrypt message\n");
        free(secret_key);
        return 1;
    }
    
    char* decrypted_msg = decrypt_message(encrypted_msg, secret_key, padding_character);
    if (!decrypted_msg) {
        printf("Failed to decrypt message\n");
        free(secret_key);
        free(encrypted_msg);
        return 1;
    }
    
    printf("   Secret Key: %s - (%lu)\n", secret_key, strlen(secret_key));
    printf("Encrypted Msg: %s - (%lu)\n", encrypted_msg, strlen(encrypted_msg));
    printf("Decrypted Msg: %s - (%lu)\n", decrypted_msg, strlen(decrypted_msg));
    
    // Cleanup
    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);
    
    return 0;
}