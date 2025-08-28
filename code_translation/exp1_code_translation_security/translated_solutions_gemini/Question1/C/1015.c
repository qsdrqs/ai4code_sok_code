#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL Headers - The C "dependencies"
#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/rand.h>
#include <openssl/err.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

// Fernet constants
#define SIGNING_KEY_SIZE 16
#define ENCRYPTION_KEY_SIZE 16
#define IV_SIZE 16
#define HMAC_SIZE 32
#define VERSION_OFFSET 0
#define TIMESTAMP_OFFSET 1
#define IV_OFFSET 9
#define HMAC_OFFSET(ciphertext_len) (IV_OFFSET + IV_SIZE + ciphertext_len)

// Helper function for URL-safe Base64 encoding/decoding
// OpenSSL's default Base64 uses '+' and '/'. Fernet uses '-' and '_'.
char* base64_encode(const unsigned char* input, int length) {
    BIO *b64, *bmem;
    BUF_MEM *bptr;

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, input, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);

    char* buff = (char*)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    BIO_free_all(b64);

    // Convert to URL-safe variant
    for (int i = 0; i < bptr->length; i++) {
        if (buff[i] == '+') buff[i] = '-';
        if (buff[i] == '/') buff[i] = '_';
    }
    // Remove padding
    char* eq_ptr = strchr(buff, '=');
    if (eq_ptr) *eq_ptr = '\0';

    return buff;
}

unsigned char* base64_decode(const char* input, int* out_len) {
    // Add padding back if necessary and convert from URL-safe
    int len = strlen(input);
    char* temp_input = malloc(len + 4); // Extra space for padding
    strcpy(temp_input, input);
    for (int i = 0; i < len; i++) {
        if (temp_input[i] == '-') temp_input[i] = '+';
        if (temp_input[i] == '_') temp_input[i] = '/';
    }
    int padding = len % 4;
    if (padding) {
        strcat(temp_input, "====");
        temp_input[len + (4 - padding)] = '\0';
    }

    BIO *b64, *bmem;
    int decoded_len = (strlen(temp_input) * 3) / 4;
    unsigned char* buffer = (unsigned char*)malloc(decoded_len + 1);

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bmem = BIO_new_mem_buf(temp_input, -1);
    bmem = BIO_push(b64, bmem);

    *out_len = BIO_read(bmem, buffer, strlen(temp_input));
    buffer[*out_len] = '\0';

    BIO_free_all(bmem);
    free(temp_input);
    return buffer;
}


// Equivalent to Fernet.generate_key()
char* generate_key() {
    unsigned char key_bytes[32];
    if (RAND_bytes(key_bytes, sizeof(key_bytes)) != 1) {
        fprintf(stderr, "Error generating random key bytes.\n");
        return NULL;
    }
    return base64_encode(key_bytes, sizeof(key_bytes));
}

// Equivalent to fernet.encrypt()
char* encrypt_data(const unsigned char* data, int data_len, const char* b64_key) {
    // 1. Decode the base64 key
    int raw_key_len;
    unsigned char* raw_key = base64_decode(b64_key, &raw_key_len);
    if (raw_key_len != 32) {
        fprintf(stderr, "Invalid key length.\n");
        free(raw_key);
        return NULL;
    }
    unsigned char* signing_key = raw_key;
    unsigned char* encryption_key = raw_key + SIGNING_KEY_SIZE;

    // 2. Prepare Fernet payload components
    unsigned char version = 0x80;
    unsigned char iv[IV_SIZE];
    if (RAND_bytes(iv, sizeof(iv)) != 1) {
        fprintf(stderr, "Error generating IV.\n");
        free(raw_key);
        return NULL;
    }

    time_t current_time = time(NULL);
    unsigned char timestamp[8];
    for (int i = 0; i < 8; i++) {
        timestamp[i] = (current_time >> (56 - 8 * i)) & 0xFF;
    }

    // 3. Encrypt with AES-128-CBC
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    int ciphertext_len;
    int len;
    // AES block size is 16 bytes. We need space for padding.
    unsigned char* ciphertext = malloc(data_len + EVP_MAX_BLOCK_LENGTH);

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, data, data_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Create the message to be signed (HMAC)
    int hmac_msg_len = 1 + 8 + IV_SIZE + ciphertext_len;
    unsigned char* hmac_msg = malloc(hmac_msg_len);
    hmac_msg[VERSION_OFFSET] = version;
    memcpy(hmac_msg + TIMESTAMP_OFFSET, timestamp, 8);
    memcpy(hmac_msg + IV_OFFSET, iv, IV_SIZE);
    memcpy(hmac_msg + IV_OFFSET + IV_SIZE, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[HMAC_SIZE];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, SIGNING_KEY_SIZE, hmac_msg, hmac_msg_len, hmac_result, &hmac_len);

    // 6. Assemble the final token
    int final_token_len = hmac_msg_len + HMAC_SIZE;
    unsigned char* final_token = malloc(final_token_len);
    memcpy(final_token, hmac_msg, hmac_msg_len);
    memcpy(final_token + hmac_msg_len, hmac_result, HMAC_SIZE);

    // 7. Base64 encode the final token
    char* b64_token = base64_encode(final_token, final_token_len);

    // 8. Cleanup
    free(raw_key);
    free(ciphertext);
    free(hmac_msg);
    free(final_token);

    return b64_token;
}

// Equivalent to fernet.decrypt()
unsigned char* decrypt_data(const char* token, const char* b64_key, int* decrypted_len) {
    // 1. Decode the base64 key
    int raw_key_len;
    unsigned char* raw_key = base64_decode(b64_key, &raw_key_len);
    if (raw_key_len != 32) {
        fprintf(stderr, "Invalid key length.\n");
        free(raw_key);
        return NULL;
    }
    unsigned char* signing_key = raw_key;
    unsigned char* encryption_key = raw_key + ENCRYPTION_KEY_SIZE;

    // 2. Decode the base64 token
    int decoded_token_len;
    unsigned char* decoded_token = base64_decode(token, &decoded_token_len);
    if (!decoded_token) {
        fprintf(stderr, "Invalid token encoding.\n");
        free(raw_key);
        return NULL;
    }

    // 3. Verify HMAC
    int ciphertext_len = decoded_token_len - (1 + 8 + IV_SIZE + HMAC_SIZE);
    if (ciphertext_len < 0) {
        fprintf(stderr, "Invalid token: too short.\n");
        goto error;
    }
    
    unsigned char* received_hmac = decoded_token + HMAC_OFFSET(ciphertext_len);
    int hmac_msg_len = HMAC_OFFSET(ciphertext_len);
    
    unsigned char calculated_hmac[HMAC_SIZE];
    unsigned int calculated_hmac_len;
    HMAC(EVP_sha256(), signing_key, SIGNING_KEY_SIZE, decoded_token, hmac_msg_len, calculated_hmac, &calculated_hmac_len);

    if (CRYPTO_memcmp(received_hmac, calculated_hmac, HMAC_SIZE) != 0) {
        fprintf(stderr, "Invalid token: HMAC signature does not match.\n");
        goto error;
    }

    // 4. (Optional but good practice) Verify timestamp. Fernet default TTL is 60s.
    // We will skip this for a direct translation, but in production, you should check it.

    // 5. Decrypt with AES-128-CBC
    unsigned char* iv = decoded_token + IV_OFFSET;
    unsigned char* ciphertext = decoded_token + IV_OFFSET + IV_SIZE;
    unsigned char* plaintext = malloc(ciphertext_len + 1); // +1 for null terminator
    int len;

    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, plaintext, decrypted_len, ciphertext, ciphertext_len);
    len = *decrypted_len;
    if (EVP_DecryptFinal_ex(ctx, plaintext + len, &len) != 1) {
        fprintf(stderr, "Failed to decrypt: Invalid padding.\n");
        ERR_print_errors_fp(stderr);
        free(plaintext);
        goto error;
    }
    *decrypted_len += len;
    plaintext[*decrypted_len] = '\0'; // Null-terminate for string operations

    EVP_CIPHER_CTX_free(ctx);
    free(raw_key);
    free(decoded_token);
    return plaintext;

error:
    free(raw_key);
    free(decoded_token);
    return NULL;
}

// Main function to demonstrate usage
int main() {
    // In the Python code, main() calls encrypt with a hardcoded path and a new key,
    // but doesn't do anything with the result.
    // This C main function provides a more complete demonstration.

    // Generate a key
    char* key = generate_key();
    if (!key) return 1;
    printf("Generated Key: %s\n\n", key);

    // Data to encrypt
    const char* plaintext_str = "This is a secret message.";
    printf("Plaintext: %s\n", plaintext_str);

    // Encrypt the data
    char* encrypted_token = encrypt_data((const unsigned char*)plaintext_str, strlen(plaintext_str), key);
    if (!encrypted_token) {
        free(key);
        return 1;
    }
    printf("Encrypted Token: %s\n\n", encrypted_token);

    // Decrypt the data
    int decrypted_len;
    unsigned char* decrypted_text = decrypt_data(encrypted_token, key, &decrypted_len);
    if (!decrypted_text) {
        free(key);
        free(encrypted_token);
        return 1;
    }
    printf("Decrypted Text: %s\n", decrypted_text);
    printf("Decrypted Length: %d\n", decrypted_len);

    // Cleanup
    free(key);
    free(encrypted_token);
    free(decrypted_text);

    return 0;
}