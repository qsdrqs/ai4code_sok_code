#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/kdf.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <openssl/aes.h>

#define KEY_SIZE 32
#define IV_SIZE 16
#define TAG_SIZE 16
#define SALT_SIZE 16

typedef struct {
    unsigned char *data;
    size_t length;
} EncryptedMessage;

// Base64 encoding/decoding functions
static const char base64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

char* base64_encode(const unsigned char* input, size_t length) {
    size_t output_length = 4 * ((length + 2) / 3);
    char* encoded = malloc(output_length + 1);
    if (!encoded) return NULL;
    
    for (size_t i = 0, j = 0; i < length;) {
        uint32_t octet_a = i < length ? input[i++] : 0;
        uint32_t octet_b = i < length ? input[i++] : 0;
        uint32_t octet_c = i < length ? input[i++] : 0;
        uint32_t triple = (octet_a << 0x10) + (octet_b << 0x08) + octet_c;
        
        encoded[j++] = base64_chars[(triple >> 3 * 6) & 0x3F];
        encoded[j++] = base64_chars[(triple >> 2 * 6) & 0x3F];
        encoded[j++] = base64_chars[(triple >> 1 * 6) & 0x3F];
        encoded[j++] = base64_chars[(triple >> 0 * 6) & 0x3F];
    }
    
    for (int i = 0; i < (3 - length % 3) % 3; i++)
        encoded[output_length - 1 - i] = '=';
    
    encoded[output_length] = '\0';
    return encoded;
}

unsigned char* base64_decode(const char* input, size_t* output_length) {
    size_t input_length = strlen(input);
    if (input_length % 4 != 0) return NULL;
    
    *output_length = input_length / 4 * 3;
    if (input[input_length - 1] == '=') (*output_length)--;
    if (input[input_length - 2] == '=') (*output_length)--;
    
    unsigned char* decoded = malloc(*output_length);
    if (!decoded) return NULL;
    
    for (size_t i = 0, j = 0; i < input_length;) {
        uint32_t sextet_a = input[i] == '=' ? 0 & i++ : strchr(base64_chars, input[i++]) - base64_chars;
        uint32_t sextet_b = input[i] == '=' ? 0 & i++ : strchr(base64_chars, input[i++]) - base64_chars;
        uint32_t sextet_c = input[i] == '=' ? 0 & i++ : strchr(base64_chars, input[i++]) - base64_chars;
        uint32_t sextet_d = input[i] == '=' ? 0 & i++ : strchr(base64_chars, input[i++]) - base64_chars;
        
        uint32_t triple = (sextet_a << 3 * 6) + (sextet_b << 2 * 6) + (sextet_c << 1 * 6) + (sextet_d << 0 * 6);
        
        if (j < *output_length) decoded[j++] = (triple >> 2 * 8) & 0xFF;
        if (j < *output_length) decoded[j++] = (triple >> 1 * 8) & 0xFF;
        if (j < *output_length) decoded[j++] = (triple >> 0 * 8) & 0xFF;
    }
    
    return decoded;
}

// Derive key from base64 key (similar to Fernet key derivation)
int derive_key(const char* base64_key, unsigned char* derived_key) {
    size_t key_len;
    unsigned char* key_bytes = base64_decode(base64_key, &key_len);
    if (!key_bytes || key_len != KEY_SIZE) {
        if (key_bytes) free(key_bytes);
        return 0;
    }
    
    memcpy(derived_key, key_bytes, KEY_SIZE);
    free(key_bytes);
    return 1;
}

EncryptedMessage* encrypt(const char* message, const char* key) {
    unsigned char derived_key[KEY_SIZE];
    if (!derive_key(key, derived_key)) {
        return NULL;
    }
    
    size_t message_len = strlen(message);
    unsigned char iv[IV_SIZE];
    unsigned char tag[TAG_SIZE];
    
    // Generate random IV
    if (RAND_bytes(iv, IV_SIZE) != 1) {
        return NULL;
    }
    
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    // Initialize encryption
    if (EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Set IV length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, IV_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Initialize key and IV
    if (EVP_EncryptInit_ex(ctx, NULL, NULL, derived_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Encrypt the message
    unsigned char* ciphertext = malloc(message_len);
    int len;
    int ciphertext_len;
    
    if (EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)message, message_len) != 1) {
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    ciphertext_len = len;
    
    // Finalize encryption
    if (EVP_EncryptFinal_ex(ctx, ciphertext + len, &len) != 1) {
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    ciphertext_len += len;
    
    // Get the tag
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, TAG_SIZE, tag) != 1) {
        free(ciphertext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    EVP_CIPHER_CTX_free(ctx);
    
    // Create the encrypted message structure (IV + ciphertext + tag)
    EncryptedMessage* enc_msg = malloc(sizeof(EncryptedMessage));
    enc_msg->length = IV_SIZE + ciphertext_len + TAG_SIZE;
    enc_msg->data = malloc(enc_msg->length);
    
    memcpy(enc_msg->data, iv, IV_SIZE);
    memcpy(enc_msg->data + IV_SIZE, ciphertext, ciphertext_len);
    memcpy(enc_msg->data + IV_SIZE + ciphertext_len, tag, TAG_SIZE);
    
    free(ciphertext);
    return enc_msg;
}

char* decrypt(const EncryptedMessage* enc_message, const char* key) {
    unsigned char derived_key[KEY_SIZE];
    if (!derive_key(key, derived_key)) {
        return NULL;
    }
    
    if (enc_message->length < IV_SIZE + TAG_SIZE) {
        return NULL;
    }
    
    // Extract IV, ciphertext, and tag
    unsigned char* iv = enc_message->data;
    unsigned char* ciphertext = enc_message->data + IV_SIZE;
    size_t ciphertext_len = enc_message->length - IV_SIZE - TAG_SIZE;
    unsigned char* tag = enc_message->data + IV_SIZE + ciphertext_len;
    
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return NULL;
    
    // Initialize decryption
    if (EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Set IV length
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, IV_SIZE, NULL) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Initialize key and IV
    if (EVP_DecryptInit_ex(ctx, NULL, NULL, derived_key, iv) != 1) {
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Decrypt the message
    char* plaintext = malloc(ciphertext_len + 1);
    int len;
    int plaintext_len;
    
    if (EVP_DecryptUpdate(ctx, (unsigned char*)plaintext, &len, ciphertext, ciphertext_len) != 1) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    plaintext_len = len;
    
    // Set expected tag value
    if (EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, TAG_SIZE, tag) != 1) {
        free(plaintext);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    
    // Finalize decryption and verify tag
    int ret = EVP_DecryptFinal_ex(ctx, (unsigned char*)plaintext + len, &len);
    EVP_CIPHER_CTX_free(ctx);
    
    if (ret <= 0) {
        free(plaintext);
        return NULL;
    }
    
    plaintext_len += len;
    plaintext[plaintext_len] = '\0';
    
    return plaintext;
}

// Helper function to create EncryptedMessage from base64 string (for compatibility)
EncryptedMessage* create_encrypted_message_from_base64(const char* base64_data) {
    size_t data_len;
    unsigned char* data = base64_decode(base64_data, &data_len);
    if (!data) return NULL;
    
    EncryptedMessage* msg = malloc(sizeof(EncryptedMessage));
    msg->data = data;
    msg->length = data_len;
    return msg;
}

// Helper function to convert EncryptedMessage to base64 string
char* encrypted_message_to_base64(const EncryptedMessage* msg) {
    return base64_encode(msg->data, msg->length);
}

// Cleanup function
void free_encrypted_message(EncryptedMessage* msg) {
    if (msg) {
        if (msg->data) free(msg->data);
        free(msg);
    }
}

// Example usage
int main() {
    // Generate a random key (in practice, you'd use Fernet.generate_key() equivalent)
    unsigned char key_bytes[KEY_SIZE];
    RAND_bytes(key_bytes, KEY_SIZE);
    char* key = base64_encode(key_bytes, KEY_SIZE);
    
    const char* message = "Hello, World!";
    
    // Encrypt
    EncryptedMessage* encrypted = encrypt(message, key);
    if (encrypted) {
        char* encrypted_b64 = encrypted_message_to_base64(encrypted);
        printf("Encrypted: %s\n", encrypted_b64);
        
        // Decrypt
        char* decrypted = decrypt(encrypted, key);
        if (decrypted) {
            printf("Decrypted: %s\n", decrypted);
            free(decrypted);
        }
        
        free(encrypted_b64);
        free_encrypted_message(encrypted);
    }
    
    free(key);
    return 0;
}