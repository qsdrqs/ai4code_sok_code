#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/base64.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
    unsigned char iv[AES_BLOCK_SIZE];
} FernetKey;

// Function to generate a Fernet key
void generate_fernet_key(FernetKey* key) {
    RAND_bytes(key->key, 32);
}

// Function to encrypt input using Fernet
unsigned char* encrypt(const unsigned char* inp, size_t inp_len, const FernetKey* key) {
    // Create a new AES context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, key->iv);

    // Calculate the encrypted length
    int enc_len;
    EVP_EncryptUpdate(ctx, NULL, &enc_len, inp, inp_len);

    // Allocate memory for the encrypted data
    unsigned char* enc_data = (unsigned char*)malloc(enc_len + EVP_MAX_BLOCK_LENGTH);
    int final_len;

    // Perform the encryption
    EVP_EncryptUpdate(ctx, enc_data, &final_len, inp, inp_len);
    EVP_EncryptFinal_ex(ctx, enc_data + final_len, &enc_len);
    final_len += enc_len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);

    // Base64 encode the encrypted data
    size_t base64_len = (final_len * 4 + 2) / 3 + 1;
    unsigned char* base64_data = (unsigned char*)malloc(base64_len);
    BIO* b64 = BIO_new(BIO_f_base64());
    BIO* bio = BIO_new(BIO_memory());
    bio = BIO_push(b64, bio);
    BIO_write(bio, enc_data, final_len);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &base64_data);
    BIO_free_all(bio);

    // Free the encrypted data
    free(enc_data);

    // Return the base64 encoded encrypted data
    return base64_data;
}

// Function to decrypt input using Fernet
unsigned char* decrypt(const unsigned char* inp, size_t inp_len, const FernetKey* key) {
    // Base64 decode the input
    BIO* b64 = BIO_new(BIO_f_base64());
    BIO* bio = BIO_new(BIO_memory());
    bio = BIO_push(b64, bio);
    BIO_write(bio, inp, inp_len);
    BIO_flush(bio);

    // Get the decoded length
    BUF_MEM* bufferPtr;
    BIO_get_mem_ptr(bio, &bufferPtr);
    unsigned char* dec_data = (unsigned char*)malloc(bufferPtr->length);
    memcpy(dec_data, bufferPtr->data, bufferPtr->length);
    BIO_free_all(bio);

    // Create a new AES context
    EVP_CIPHER_CTX* ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, key->iv);

    // Calculate the decrypted length
    int dec_len;
    EVP_DecryptUpdate(ctx, NULL, &dec_len, dec_data, bufferPtr->length);

    // Perform the decryption
    unsigned char* decrypted_data = (unsigned char*)malloc(dec_len + EVP_MAX_BLOCK_LENGTH);
    int final_len;
    EVP_DecryptUpdate(ctx, decrypted_data, &final_len, dec_data, bufferPtr->length);
    EVP_DecryptFinal_ex(ctx, decrypted_data + final_len, &dec_len);
    final_len += dec_len;

    // Clean up
    EVP_CIPHER_CTX_free(ctx);
    free(dec_data);

    // Return the decrypted data
    return decrypted_data;
}

int main() {
    // Generate a Fernet key
    FernetKey ex_key;
    generate_fernet_key(&ex_key);

    // Set the IV (Initialization Vector) for encryption
    unsigned char iv[AES_BLOCK_SIZE];
    RAND_bytes(iv, AES_BLOCK_SIZE);
    memcpy(ex_key.iv, iv, AES_BLOCK_SIZE);

    // Input string
    const char* inp = "Hello World!";

    // Encrypt the input
    unsigned char* encrypted = encrypt((unsigned char*)inp, strlen(inp), &ex_key);
    printf("Encrypted: %s\n", encrypted);

    // Decrypt the encrypted data
    unsigned char* decrypted = decrypt(encrypted, strlen((char*)encrypted), &ex_key);
    printf("Decrypted: %s\n", decrypted);

    // Free the allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}