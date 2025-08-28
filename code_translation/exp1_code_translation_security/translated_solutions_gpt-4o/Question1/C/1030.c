#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/rand.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>

#define AES_KEY_LENGTH 16
#define BLOCK_SIZE 16

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

unsigned char* generate_secret_key_for_AES_cipher() {
    unsigned char* secret_key = (unsigned char*)malloc(AES_KEY_LENGTH);
    if (!RAND_bytes(secret_key, AES_KEY_LENGTH)) {
        handleErrors();
    }
    return secret_key;
}

unsigned char* encrypt_message(const char* private_msg, unsigned char* secret_key, char padding_character, int* encrypted_msg_len) {
    EVP_CIPHER_CTX* ctx;
    int len;
    int ciphertext_len;
    int private_msg_len = strlen(private_msg);
    int padded_len = private_msg_len + (BLOCK_SIZE - (private_msg_len % BLOCK_SIZE));

    unsigned char* padded_private_msg = (unsigned char*)malloc(padded_len + 1);
    strcpy((char*)padded_private_msg, private_msg);
    memset(padded_private_msg + private_msg_len, padding_character, padded_len - private_msg_len);
    padded_private_msg[padded_len] = '\0';

    unsigned char* encrypted_msg = (unsigned char*)malloc(padded_len + BLOCK_SIZE);

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_EncryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, secret_key, NULL))
        handleErrors();

    if (1 != EVP_EncryptUpdate(ctx, encrypted_msg, &len, padded_private_msg, padded_len))
        handleErrors();
    ciphertext_len = len;

    if (1 != EVP_EncryptFinal_ex(ctx, encrypted_msg + len, &len)) handleErrors();
    ciphertext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    *encrypted_msg_len = ciphertext_len;
    free(padded_private_msg);
    return encrypted_msg;
}

char* decrypt_message(unsigned char* encrypted_msg, int encrypted_msg_len, unsigned char* secret_key, char padding_character) {
    EVP_CIPHER_CTX* ctx;
    int len;
    int plaintext_len;
    unsigned char* decrypted_msg = (unsigned char*)malloc(encrypted_msg_len + 1);

    if (!(ctx = EVP_CIPHER_CTX_new())) handleErrors();

    if (1 != EVP_DecryptInit_ex(ctx, EVP_aes_128_ecb(), NULL, secret_key, NULL))
        handleErrors();

    if (1 != EVP_DecryptUpdate(ctx, decrypted_msg, &len, encrypted_msg, encrypted_msg_len))
        handleErrors();
    plaintext_len = len;

    if (1 != EVP_DecryptFinal_ex(ctx, decrypted_msg + len, &len)) handleErrors();
    plaintext_len += len;

    EVP_CIPHER_CTX_free(ctx);

    decrypted_msg[plaintext_len] = '\0';

    // Remove padding
    for (int i = plaintext_len - 1; i >= 0; i--) {
        if (decrypted_msg[i] == padding_character) {
            decrypted_msg[i] = '\0';
        } else {
            break;
        }
    }

    return (char*)decrypted_msg;
}

int main(void) {
    ERR_load_crypto_strings();
    OpenSSL_add_all_algorithms();
    OPENSSL_config(NULL);

    const char* private_msg = " My test string";
    char padding_character = '{';

    unsigned char* secret_key = generate_secret_key_for_AES_cipher();
    int encrypted_msg_len;
    unsigned char* encrypted_msg = encrypt_message(private_msg, secret_key, padding_character, &encrypted_msg_len);
    char* decrypted_msg = decrypt_message(encrypted_msg, encrypted_msg_len, secret_key, padding_character);

    printf("   Secret Key: ");
    for (int i = 0; i < AES_KEY_LENGTH; i++) {
        printf("%02x", secret_key[i]);
    }
    printf(" - (%d)\n", AES_KEY_LENGTH);

    printf("Encrypted Msg: ");
    for (int i = 0; i < encrypted_msg_len; i++) {
        printf("%02x", encrypted_msg[i]);
    }
    printf(" - (%d)\n", encrypted_msg_len);

    printf("Decrypted Msg: %s - (%ld)\n", decrypted_msg, strlen(decrypted_msg));

    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);

    EVP_cleanup();
    ERR_free_strings();

    return 0;
}