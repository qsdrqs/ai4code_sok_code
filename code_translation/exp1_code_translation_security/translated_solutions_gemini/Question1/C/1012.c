#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// OpenSSL headers
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/err.h>

// --- Global Key (as in the Python example) ---
// A 32-byte key for Fernet (16 for signing, 16 for encryption)
unsigned char g_fernet_key[32];

// --- Helper Functions ---

// A utility function to print OpenSSL errors
void handle_openssl_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// Converts a 64-bit integer to a big-endian byte array
void uint64_to_be(uint64_t val, unsigned char *buf) {
    buf[0] = (val >> 56) & 0xFF;
    buf[1] = (val >> 48) & 0xFF;
    buf[2] = (val >> 40) & 0xFF;
    buf[3] = (val >> 32) & 0xFF;
    buf[4] = (val >> 24) & 0xFF;
    buf[5] = (val >> 16) & 0xFF;
    buf[6] = (val >> 8) & 0xFF;
    buf[7] = val & 0xFF;
}

// Converts a big-endian byte array to a 64-bit integer
uint64_t be_to_uint64(const unsigned char *buf) {
    return ((uint64_t)buf[0] << 56) | ((uint64_t)buf[1] << 48) |
           ((uint64_t)buf[2] << 40) | ((uint64_t)buf[3] << 32) |
           ((uint64_t)buf[4] << 24) | ((uint64_t)buf[5] << 16) |
           ((uint64_t)buf[6] << 8)  | (uint64_t)buf[7];
}

// Base64 URL-safe encoding
char* base64_url_safe_encode(const unsigned char *input, int length) {
    // Standard Base64 encoding
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(b64, input, length);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(b64, &bptr);

    char *buff = (char *)malloc(bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;

    BIO_free_all(b64);

    // Make it URL-safe
    for (int i = 0; i < bptr->length; i++) {
        if (buff[i] == '+') buff[i] = '-';
        if (buff[i] == '/') buff[i] = '_';
    }
    
    // Remove padding
    char *padding = strchr(buff, '=');
    if (padding) *padding = '\0';

    return buff;
}

// Base64 URL-safe decoding
unsigned char* base64_url_safe_decode(const char *input, int *out_len) {
    int len = strlen(input);
    char *padded_input = (char *)malloc(len + 4);
    strcpy(padded_input, input);

    // Make it standard Base64
    for (int i = 0; i < len; i++) {
        if (padded_input[i] == '-') padded_input[i] = '+';
        if (padded_input[i] == '_') padded_input[i] = '/';
    }

    // Add padding if necessary
    int mod = len % 4;
    if (mod == 2) strcat(padded_input, "==");
    else if (mod == 3) strcat(padded_input, "=");

    // Standard Base64 decoding
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *bmem = BIO_new_mem_buf(padded_input, -1);
    b64 = BIO_push(b64, bmem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);

    int padded_len = strlen(padded_input);
    unsigned char *buffer = (unsigned char *)malloc(padded_len);
    *out_len = BIO_read(b64, buffer, padded_len);

    BIO_free_all(b64);
    free(padded_input);

    return buffer;
}


// --- Core Fernet Functions ---

// Equivalent to Fernet.generate_key()
void generate_key() {
    if (!RAND_bytes(g_fernet_key, sizeof(g_fernet_key))) {
        handle_openssl_errors();
    }
}

// Equivalent to f.encrypt()
char* encrypt_text(const char* plain_text) {
    // 1. Get signing and encryption keys
    const unsigned char *signing_key = g_fernet_key;
    const unsigned char *encryption_key = g_fernet_key + 16;

    // 2. Prepare metadata
    unsigned char version = 0x80;
    unsigned char timestamp_bytes[8];
    uint64_to_be((uint64_t)time(NULL), timestamp_bytes);
    
    unsigned char iv[16];
    if (!RAND_bytes(iv, sizeof(iv))) handle_openssl_errors();

    // 3. Encrypt the plaintext (AES-128-CBC)
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    int len;
    int ciphertext_len;
    int plaintext_len = strlen(plain_text);
    
    // Max possible ciphertext length for malloc
    unsigned char *ciphertext = malloc(plaintext_len + EVP_MAX_BLOCK_LENGTH);

    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, (unsigned char*)plain_text, plaintext_len);
    ciphertext_len = len;
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);
    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);

    // 4. Concatenate data for HMAC
    int hmac_data_len = 1 + 8 + 16 + ciphertext_len;
    unsigned char *hmac_data = malloc(hmac_data_len);
    memcpy(hmac_data, &version, 1);
    memcpy(hmac_data + 1, timestamp_bytes, 8);
    memcpy(hmac_data + 9, iv, 16);
    memcpy(hmac_data + 25, ciphertext, ciphertext_len);

    // 5. Calculate HMAC-SHA256
    unsigned char hmac_result[32];
    unsigned int hmac_len;
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, hmac_result, &hmac_len);

    // 6. Concatenate final token
    int final_token_len = hmac_data_len + hmac_len;
    unsigned char *final_token = malloc(final_token_len);
    memcpy(final_token, hmac_data, hmac_data_len);
    memcpy(final_token + hmac_data_len, hmac_result, hmac_len);

    // 7. Base64 URL-safe encode the result
    char *encoded_text = base64_url_safe_encode(final_token, final_token_len);

    // 8. Cleanup
    free(ciphertext);
    free(hmac_data);
    free(final_token);

    return encoded_text;
}

// Equivalent to f.decrypt()
char* decrypt_text(const char* encrypted_text) {
    // 1. Get signing and encryption keys
    const unsigned char *signing_key = g_fernet_key;
    const unsigned char *encryption_key = g_fernet_key + 16;

    // 2. Base64 URL-safe decode the input
    int decoded_len;
    unsigned char *decoded_token = base64_url_safe_decode(encrypted_text, &decoded_len);
    if (!decoded_token) {
        fprintf(stderr, "Error: Base64 decoding failed.\n");
        return NULL;
    }

    // Minimum length check: version(1) + timestamp(8) + iv(16) + hmac(32) = 57
    if (decoded_len < 57) {
        fprintf(stderr, "Error: Invalid token length.\n");
        free(decoded_token);
        return NULL;
    }

    // 3. Separate HMAC and data
    int hmac_data_len = decoded_len - 32;
    unsigned char *hmac_data = decoded_token;
    const unsigned char *received_hmac = decoded_token + hmac_data_len;

    // 4. Verify HMAC
    unsigned char calculated_hmac[32];
    HMAC(EVP_sha256(), signing_key, 16, hmac_data, hmac_data_len, calculated_hmac, NULL);

    if (CRYPTO_memcmp(received_hmac, calculated_hmac, 32) != 0) {
        fprintf(stderr, "Error: HMAC verification failed (invalid signature).\n");
        free(decoded_token);
        return NULL;
    }

    // 5. Verify version and timestamp (optional TTL check)
    if (hmac_data[0] != 0x80) {
        fprintf(stderr, "Error: Invalid token version.\n");
        free(decoded_token);
        return NULL;
    }
    // uint64_t timestamp = be_to_uint64(hmac_data + 1);
    // time_t current_time = time(NULL);
    // if (current_time > timestamp + 60) { // Example: 60 second TTL
    //     fprintf(stderr, "Error: Token has expired.\n");
    //     free(decoded_token);
    //     return NULL;
    // }

    // 6. Extract IV and ciphertext
    const unsigned char *iv = hmac_data + 9;
    const unsigned char *ciphertext = hmac_data + 25;
    int ciphertext_len = hmac_data_len - 25;

    // 7. Decrypt the ciphertext
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    char *decrypted_text = malloc(ciphertext_len + 1); // Allocate enough space
    int len;
    int decrypted_len;

    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, encryption_key, iv);
    EVP_DecryptUpdate(ctx, (unsigned char*)decrypted_text, &len, ciphertext, ciphertext_len);
    decrypted_len = len;
    if (EVP_DecryptFinal_ex(ctx, (unsigned char*)decrypted_text + len, &len) <= 0) {
        fprintf(stderr, "Error: Decryption failed (likely bad padding).\n");
        free(decoded_token);
        free(decrypted_text);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    decrypted_len += len;
    decrypted_text[decrypted_len] = '\0'; // Null-terminate the result

    // 8. Cleanup
    EVP_CIPHER_CTX_free(ctx);
    free(decoded_token);

    return decrypted_text;
}


// --- Main function to demonstrate usage ---
int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();

    // Equivalent to: key = Fernet.generate_key()
    generate_key();
    printf("Generated a 32-byte Fernet key.\n\n");

    const char *original_text = "This is a secret message.";
    printf("Original Text:    %s\n", original_text);

    // Encrypt the text
    char *encrypted = encrypt_text(original_text);
    if (encrypted) {
        printf("Encrypted Token:  %s\n", encrypted);

        // Decrypt the text
        char *decrypted = decrypt_text(encrypted);
        if (decrypted) {
            printf("Decrypted Text:   %s\n", decrypted);

            // Verify correctness
            if (strcmp(original_text, decrypted) == 0) {
                printf("\nSuccess: Decrypted text matches the original.\n");
            } else {
                printf("\nFailure: Decrypted text does not match the original.\n");
            }
            free(decrypted);
        }
        free(encrypted);
    }

    // Clean up OpenSSL
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}