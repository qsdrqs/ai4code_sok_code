#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/base64.h>
#include <openssl/rand.h>
#include <openssl/evp.h>

// Structure to hold Fernet key
typedef struct {
    unsigned char key[32];
    unsigned char iv[AES_BLOCK_SIZE];
} FernetKey;

// Function to generate a Fernet key
void generate_key(FernetKey *key) {
    RAND_bytes(key->key, 32);
}

// Function to encrypt a message using Fernet
unsigned char* encrypt(const unsigned char *msg, size_t msg_len, const FernetKey *key) {
    // Create a new AES context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, key->iv);

    // Calculate the encrypted message length
    int enc_len = 0;
    int final_len = 0;
    EVP_EncryptUpdate(ctx, NULL, &enc_len, msg, msg_len);
    enc_len += EVP_EncryptFinal_ex(ctx, NULL, &final_len);
    unsigned char *enc_msg = (unsigned char*)malloc(enc_len);

    // Perform encryption
    EVP_EncryptUpdate(ctx, enc_msg, &enc_len, msg, msg_len);
    EVP_EncryptFinal_ex(ctx, enc_msg + enc_len, &final_len);
    enc_len += final_len;

    EVP_CIPHER_CTX_free(ctx);

    // Base64 encode the encrypted message and IV
    unsigned char *iv_b64 = (unsigned char*)malloc(EVP_MAX_IV_LENGTH * 4 / 3 + 1);
    EVP_EncodeBlock(iv_b64, key->iv, AES_BLOCK_SIZE, &enc_len_iv);
    enc_len_iv = strlen((const char*)iv_b64);

    unsigned char *enc_msg_b64 = (unsigned char*)malloc((enc_len + enc_len_iv + 1) * 4 / 3 + 1);
    EVP_EncodeBlock(enc_msg_b64, enc_msg, enc_len, &enc_len_b64);
    enc_len_b64 = strlen((const char*)enc_msg_b64);

    // Concatenate the version, timestamp, IV, and encrypted message
    unsigned char *fernet_msg = (unsigned char*)malloc(1 + 8 + enc_len_iv + 1 + enc_len_b64 + 1);
    fernet_msg[0] = '0'; // Version
    for (int i = 0; i < 8; i++) {
        fernet_msg[i + 1] = '0' + (rand() % 10); // Random timestamp
    }
    fernet_msg[9] = '.';
    memcpy(fernet_msg + 10, iv_b64, enc_len_iv);
    fernet_msg[10 + enc_len_iv] = '.';
    memcpy(fernet_msg + 11 + enc_len_iv, enc_msg_b64, enc_len_b64);
    fernet_msg[11 + enc_len_iv + enc_len_b64] = '\0';

    free(enc_msg);
    free(iv_b64);
    free(enc_msg_b64);

    return fernet_msg;
}

// Function to decrypt a message using Fernet
unsigned char* decrypt(const unsigned char *fernet_msg, const FernetKey *key) {
    // Find the positions of the dots
    int dot_pos[2];
    dot_pos[0] = -1;
    dot_pos[1] = -1;
    for (int i = 0; fernet_msg[i] != '\0'; i++) {
        if (fernet_msg[i] == '.') {
            if (dot_pos[0] == -1) {
                dot_pos[0] = i;
            } else {
                dot_pos[1] = i;
                break;
            }
        }
    }

    // Extract IV and encrypted message
    int enc_len_iv = dot_pos[0] - 10;
    unsigned char *iv_b64 = (unsigned char*)malloc(enc_len_iv + 1);
    memcpy(iv_b64, fernet_msg + 10, enc_len_iv);
    iv_b64[enc_len_iv] = '\0';

    int enc_len_b64 = strlen(fernet_msg + dot_pos[1] + 1);
    unsigned char *enc_msg_b64 = (unsigned char*)malloc(enc_len_b64 + 1);
    memcpy(enc_msg_b64, fernet_msg + dot_pos[1] + 1, enc_len_b64);
    enc_msg_b64[enc_len_b64] = '\0';

    // Base64 decode IV and encrypted message
    int iv_len = 0;
    unsigned char *iv = (unsigned char*)malloc(AES_BLOCK_SIZE);
    EVP_DecodeBlock(iv, iv_b64, &iv_len);

    int enc_len = 0;
    unsigned char *enc_msg = (unsigned char*)malloc(EVP_MAX_BLOCK_LENGTH);
    EVP_DecodeBlock(enc_msg, enc_msg_b64, &enc_len);

    free(iv_b64);
    free(enc_msg_b64);

    // Create a new AES context
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key->key, iv);

    // Perform decryption
    int dec_len = 0;
    int final_len = 0;
    EVP_DecryptUpdate(ctx, NULL, &dec_len, enc_msg, enc_len);
    dec_len += EVP_DecryptFinal_ex(ctx, NULL, &final_len);
    unsigned char *dec_msg = (unsigned char*)malloc(dec_len);

    EVP_DecryptUpdate(ctx, dec_msg, &dec_len, enc_msg, enc_len);
    EVP_DecryptFinal_ex(ctx, dec_msg + dec_len, &final_len);
    dec_len += final_len;

    EVP_CIPHER_CTX_free(ctx);

    free(iv);
    free(enc_msg);

    return dec_msg;
}

int main() {
    // Initialize random number generator
    srand(time(NULL));

    // Generate a Fernet key
    FernetKey key;
    generate_key(&key);

    // Message to encrypt
    const char *msg = "a much longer message with punctuation!";
    unsigned char *enc_msg = encrypt((unsigned char*)msg, strlen(msg), &key);
    printf("Encrypted message: %s\n", enc_msg);

    // Decrypt the message
    unsigned char *dec_msg = decrypt(enc_msg, &key);
    printf("Decrypted message: %s\n", dec_msg);

    free(enc_msg);
    free(dec_msg);

    return 0;
}