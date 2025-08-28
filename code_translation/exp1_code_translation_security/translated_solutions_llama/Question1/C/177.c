#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

// Structure to hold ciphertext and nonce
typedef struct {
    unsigned char* ciphertext;
    size_t ciphertext_len;
    unsigned char* nonce;
    size_t nonce_len;
} encrypted_data;

// Structure to hold encrypted data for decryption
typedef struct {
    unsigned char* ciphertext;
    size_t ciphertext_len;
    unsigned char* nonce;
    size_t nonce_len;
} decrypt_data;

// Function to encrypt data using AES in EAX mode
encrypted_data* encrypt(const unsigned char* message, size_t message_len, const unsigned char* sk, size_t sk_len) {
    // Generate a random 16-byte nonce
    unsigned char nonce[16];
    RAND_bytes(nonce, 16);

    // Create a new AES context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc_hmac_sha1(), NULL, sk, sk_len);

    // Set EAX mode
    EVP_CIPHER_CTX_set_padding(ctx, 0); // No padding
    EVP_CIPHER_CTX_set_mode(ctx, EVP_MODE_EAX);

    // Set nonce
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_EAX_IVLEN, 16, NULL);
    EVP_EncryptInit_ex(ctx, NULL, NULL, NULL, nonce);

    // Allocate memory for ciphertext
    size_t ciphertext_len = message_len;
    unsigned char* ciphertext = (unsigned char*)malloc(ciphertext_len);

    // Encrypt the message
    int len;
    EVP_EncryptUpdate(ctx, ciphertext, &len, message, message_len);
    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);

    // Get the tag
    unsigned char* tag = (unsigned char*)malloc(16);
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_EAX_GET_TAG, 16, tag);

    // Free the context
    EVP_CIPHER_CTX_free(ctx);

    // Create and return encrypted data structure
    encrypted_data* ed = (encrypted_data*)malloc(sizeof(encrypted_data));
    ed->ciphertext = ciphertext;
    ed->ciphertext_len = ciphertext_len + len;
    ed->nonce = (unsigned char*)malloc(16);
    memcpy(ed->nonce, nonce, 16);
    ed->nonce_len = 16;

    return ed;
}

// Function to decrypt data using AES in EAX mode
unsigned char* decrypt(decrypt_data* cn, const unsigned char* sk, size_t sk_len) {
    // Create a new AES context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc_hmac_sha1(), NULL, sk, sk_len);

    // Set EAX mode
    EVP_CIPHER_CTX_set_padding(ctx, 0); // No padding
    EVP_CIPHER_CTX_set_mode(ctx, EVP_MODE_EAX);

    // Set nonce
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_EAX_IVLEN, cn->nonce_len, NULL);
    EVP_DecryptInit_ex(ctx, NULL, NULL, NULL, cn->nonce);

    // Allocate memory for plaintext
    size_t plaintext_len = cn->ciphertext_len;
    unsigned char* plaintext = (unsigned char*)malloc(plaintext_len);

    // Decrypt the ciphertext
    int len;
    EVP_DecryptUpdate(ctx, plaintext, &len, cn->ciphertext, cn->ciphertext_len);

    // Set the tag
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_EAX_SET_TAG, 16, cn->ciphertext + cn->ciphertext_len - 16);

    // Finalize decryption
    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    // Free the context
    EVP_CIPHER_CTX_free(ctx);

    return plaintext;
}

int main() {
    // Example usage
    const unsigned char* sk = (const unsigned char*)"secret_key_256bit";
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);
    size_t sk_len = strlen((const char*)sk);

    encrypted_data* ed = encrypt(message, message_len, sk, sk_len);

    decrypt_data* cn = (decrypt_data*)malloc(sizeof(decrypt_data));
    cn->ciphertext = ed->ciphertext;
    cn->ciphertext_len = ed->ciphertext_len;
    cn->nonce = ed->nonce;
    cn->nonce_len = ed->nonce_len;

    unsigned char* plaintext = decrypt(cn, sk, sk_len);

    printf("Decrypted message: %s\n", plaintext);

    // Free allocated memory
    free(ed->ciphertext);
    free(ed->nonce);
    free(ed);
    free(cn);
    free(plaintext);

    return 0;
}