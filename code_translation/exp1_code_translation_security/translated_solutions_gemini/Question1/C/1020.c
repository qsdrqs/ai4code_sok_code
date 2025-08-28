#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/bio.h>
#include <openssl/buffer.h>

// --- Helper Function for Base64 Encoding/Decoding ---

// Base64 encodes a given input string.
// The caller is responsible for freeing the returned string.
char *base64_encode(const unsigned char *input, int length) {
    BIO *bio, *b64;
    BUF_MEM *bufferPtr;

    b64 = BIO_new(BIO_f_base64());
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);

    // Ignore newlines - write everything in one line
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    BIO_write(bio, input, length);
    BIO_flush(bio);

    BIO_get_mem_ptr(bio, &bufferPtr);
    // +1 for the null terminator
    char *buff = (char *)malloc(bufferPtr->length + 1);
    memcpy(buff, bufferPtr->data, bufferPtr->length);
    buff[bufferPtr->length] = 0;

    BIO_free_all(bio);
    return buff;
}

// Base64 decodes a given input string.
// The caller is responsible for freeing the returned buffer.
// The length of the decoded data is returned via the output_length pointer.
unsigned char *base64_decode(const char *input, int *output_length) {
    BIO *bio, *b64;
    int decode_len = strlen(input);
    // Allocate a buffer large enough for the decoded data
    unsigned char *buffer = (unsigned char*)malloc(decode_len);

    bio = BIO_new_mem_buf(input, -1);
    b64 = BIO_new(BIO_f_base64());
    bio = BIO_push(b64, bio);

    // Ignore newlines
    BIO_set_flags(bio, BIO_FLAGS_BASE64_NO_NL);
    *output_length = BIO_read(bio, buffer, decode_len);

    BIO_free_all(bio);
    return buffer;
}


// --- Core Encryption/Decryption Functions ---

// Selects the appropriate EVP_CIPHER based on key size in bits
const EVP_CIPHER* get_evp_cipher(int key_size_bits) {
    switch (key_size_bits) {
        case 128: return EVP_aes_128_cbc();
        case 192: return EVP_aes_192_cbc();
        case 256: return EVP_aes_256_cbc();
        default: return NULL; // Invalid key size
    }
}

/**
 * @brief Encrypts plaintext using AES-CBC.
 *
 * @param key The symmetric key.
 * @param key_size_bits The key size in bits (128, 192, or 256).
 * @param plaintext The data to encrypt.
 * @param plaintext_len The length of the plaintext.
 * @return A base64 encoded string of [IV + Ciphertext]. The caller must free this string.
 *         Returns NULL on failure.
 */
char* encrypt_aes(const unsigned char* key, int key_size_bits, const unsigned char* plaintext, int plaintext_len) {
    const EVP_CIPHER* cipher_type = get_evp_cipher(key_size_bits);
    if (!cipher_type) {
        fprintf(stderr, "Error: Invalid key size.\n");
        return NULL;
    }

    // 1. Generate a random IV
    unsigned char iv[AES_BLOCK_SIZE];
    if (!RAND_bytes(iv, sizeof(iv))) {
        fprintf(stderr, "Error: RAND_bytes failed to generate IV.\n");
        return NULL;
    }

    EVP_CIPHER_CTX *ctx;
    int len;
    int ciphertext_len;

    // Create and initialise the context
    if (!(ctx = EVP_CIPHER_CTX_new())) return NULL;

    // Initialise the encryption operation.
    if (1 != EVP_EncryptInit_ex(ctx, cipher_type, NULL, key, iv)) return NULL;

    // Provide the message to be encrypted, and obtain the encrypted output.
    // EVP_EncryptUpdate can be called multiple times if necessary
    unsigned char* ciphertext = malloc(plaintext_len + AES_BLOCK_SIZE);
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len)) {
        free(ciphertext);
        return NULL;
    }
    ciphertext_len = len;

    // Finalise the encryption.
    if (1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len)) {
        free(ciphertext);
        return NULL;
    }
    ciphertext_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    // 2. Prepend IV to ciphertext
    int combined_len = AES_BLOCK_SIZE + ciphertext_len;
    unsigned char* combined_data = malloc(combined_len);
    memcpy(combined_data, iv, AES_BLOCK_SIZE);
    memcpy(combined_data + AES_BLOCK_SIZE, ciphertext, ciphertext_len);
    free(ciphertext);

    // 3. Base64 encode the combined (IV + ciphertext)
    char* b64_text = base64_encode(combined_data, combined_len);
    free(combined_data);

    return b64_text;
}

/**
 * @brief Decrypts ciphertext using AES-CBC.
 *
 * @param key The symmetric key.
 * @param key_size_bits The key size in bits (128, 192, or 256).
 * @param b64_ciphertext The base64 encoded string of [IV + Ciphertext].
 * @param decrypted_len A pointer to an int where the length of the decrypted data will be stored.
 * @return The original plaintext. The caller must free this buffer.
 *         Returns NULL on failure.
 */
unsigned char* decrypt_aes(const unsigned char* key, int key_size_bits, const char* b64_ciphertext, int* decrypted_len) {
    const EVP_CIPHER* cipher_type = get_evp_cipher(key_size_bits);
    if (!cipher_type) {
        fprintf(stderr, "Error: Invalid key size.\n");
        return NULL;
    }

    // 1. Base64 decode the input
    int decoded_len;
    unsigned char* decoded_data = base64_decode(b64_ciphertext, &decoded_len);
    if (decoded_data == NULL) {
        fprintf(stderr, "Error: Base64 decoding failed.\n");
        return NULL;
    }

    if (decoded_len < AES_BLOCK_SIZE) {
        fprintf(stderr, "Error: Decoded data is too short to contain an IV.\n");
        free(decoded_data);
        return NULL;
    }

    // 2. Extract IV and ciphertext
    unsigned char* iv = decoded_data; // First 16 bytes
    unsigned char* ciphertext = decoded_data + AES_BLOCK_SIZE;
    int ciphertext_len = decoded_len - AES_BLOCK_SIZE;

    EVP_CIPHER_CTX *ctx;
    int len;

    // Create and initialise the context
    if (!(ctx = EVP_CIPHER_CTX_new())) return NULL;

    // Initialise the decryption operation.
    if (1 != EVP_DecryptInit_ex(ctx, cipher_type, NULL, key, iv)) return NULL;

    // Provide the message to be decrypted, and obtain the plaintext output.
    unsigned char* plaintext = malloc(ciphertext_len);
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len)) {
        free(plaintext);
        free(decoded_data);
        return NULL;
    }
    *decrypted_len = len;

    // Finalise the decryption. This will remove padding and fail if padding is incorrect.
    if (1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len)) {
        fprintf(stderr, "Error: Decryption failed. Check key or ciphertext integrity.\n");
        free(plaintext);
        free(decoded_data);
        EVP_CIPHER_CTX_free(ctx);
        return NULL;
    }
    *decrypted_len += len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    free(decoded_data); // This frees the IV and ciphertext pointers as well

    return plaintext;
}


// --- Main function to demonstrate usage ---
int main() {
    // Use a 256-bit key (32 bytes)
    const int KEY_SIZE_BITS = 256;
    unsigned char key[32]; // AES-256 key
    
    // For a real application, use a secure key management system.
    // For this example, we'll generate a random key.
    if (!RAND_bytes(key, sizeof(key))) {
        fprintf(stderr, "Failed to generate random key.\n");
        return 1;
    }

    const char *original_plaintext_str = "This is a secret message that needs to be encrypted.";
    unsigned char *original_plaintext = (unsigned char *)original_plaintext_str;
    int plaintext_len = strlen(original_plaintext_str);

    printf("Original Plaintext: %s\n\n", original_plaintext);

    // Encrypt
    char* b64_ciphertext = encrypt_aes(key, KEY_SIZE_BITS, original_plaintext, plaintext_len);
    if (!b64_ciphertext) {
        fprintf(stderr, "Encryption failed.\n");
        return 1;
    }
    printf("Base64 Encoded Ciphertext:\n%s\n\n", b64_ciphertext);

    // Decrypt
    int decrypted_len;
    unsigned char* decrypted_text = decrypt_aes(key, KEY_SIZE_BITS, b64_ciphertext, &decrypted_len);
    if (!decrypted_text) {
        fprintf(stderr, "Decryption failed.\n");
        free(b64_ciphertext);
        return 1;
    }

    // Print decrypted text (ensure it's null-terminated for printing)
    printf("Decrypted Plaintext:\n%.*s\n", decrypted_len, decrypted_text);

    // Verify correctness
    if (plaintext_len == decrypted_len && memcmp(original_plaintext, decrypted_text, plaintext_len) == 0) {
        printf("\nSuccess: Decrypted text matches original plaintext.\n");
    } else {
        printf("\nFailure: Decrypted text does not match original plaintext.\n");
    }

    // Clean up allocated memory
    free(b64_ciphertext);
    free(decrypted_text);

    return 0;
}