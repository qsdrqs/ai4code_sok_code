#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/rand.h>
#include <base64.h>

// Function to generate a random secret key for AES cipher
unsigned char* generate_secret_key_for_AES_cipher(int* key_length) {
    *key_length = 16; // AES key length must be either 16, 24, or 32 bytes long
    unsigned char* secret_key = (unsigned char*)malloc(*key_length);
    if (RAND_bytes(secret_key, *key_length) != 1) {
        printf("Error generating random bytes\n");
        exit(1);
    }
    return secret_key;
}

// Function to encrypt a message
unsigned char* encrypt_message(const unsigned char* private_msg, int private_msg_len, const unsigned char* encoded_secret_key, int encoded_secret_key_len, char padding_character, int* encrypted_msg_len) {
    // Decode the encoded secret key
    unsigned char* secret_key = base64_decode(encoded_secret_key, encoded_secret_key_len, NULL);
    if (secret_key == NULL) {
        printf("Error decoding secret key\n");
        exit(1);
    }

    // Create an AES cipher
    AES_KEY aes_key;
    if (AES_set_encrypt_key(secret_key, 128, &aes_key) != 0) {
        printf("Error setting AES encrypt key\n");
        exit(1);
    }
    free(secret_key);

    // Pad the private message
    int padded_private_msg_len = private_msg_len + (16 - (private_msg_len % 16));
    unsigned char* padded_private_msg = (unsigned char*)malloc(padded_private_msg_len);
    memcpy(padded_private_msg, private_msg, private_msg_len);
    for (int i = private_msg_len; i < padded_private_msg_len; i++) {
        padded_private_msg[i] = padding_character;
    }

    // Encrypt the padded message
    unsigned char* encrypted_msg = (unsigned char*)malloc(padded_private_msg_len);
    AES_cbc_encrypt(padded_private_msg, encrypted_msg, padded_private_msg_len, &aes_key, NULL, AES_ENCRYPT);
    free(padded_private_msg);

    // Encode the encrypted message
    char* encoded_encrypted_msg = base64_encode(encrypted_msg, padded_private_msg_len);
    if (encoded_encrypted_msg == NULL) {
        printf("Error encoding encrypted message\n");
        exit(1);
    }
    *encrypted_msg_len = strlen(encoded_encrypted_msg);
    free(encrypted_msg);

    return (unsigned char*)encoded_encrypted_msg;
}

// Function to decrypt a message
unsigned char* decrypt_message(const unsigned char* encoded_encrypted_msg, int encoded_encrypted_msg_len, const unsigned char* encoded_secret_key, int encoded_secret_key_len, char padding_character, int* decrypted_msg_len) {
    // Decode the encoded encrypted message and encoded secret key
    unsigned char* encrypted_msg = base64_decode((const char*)encoded_encrypted_msg, encoded_encrypted_msg_len, NULL);
    if (encrypted_msg == NULL) {
        printf("Error decoding encrypted message\n");
        exit(1);
    }
    unsigned char* secret_key = base64_decode((const char*)encoded_secret_key, encoded_secret_key_len, NULL);
    if (secret_key == NULL) {
        printf("Error decoding secret key\n");
        exit(1);
    }

    // Create an AES cipher
    AES_KEY aes_key;
    if (AES_set_decrypt_key(secret_key, 128, &aes_key) != 0) {
        printf("Error setting AES decrypt key\n");
        exit(1);
    }
    free(secret_key);

    // Decrypt the encrypted message
    int encrypted_msg_len = strlen((const char*)encrypted_msg);
    unsigned char* decrypted_msg = (unsigned char*)malloc(encrypted_msg_len);
    AES_cbc_encrypt(encrypted_msg, decrypted_msg, encrypted_msg_len, &aes_key, NULL, AES_DECRYPT);
    free(encrypted_msg);

    // Unpad the decrypted message
    int unpadded_private_msg_len = 0;
    for (int i = encrypted_msg_len - 1; i >= 0; i--) {
        if (decrypted_msg[i] == padding_character) {
            continue;
        }
        unpadded_private_msg_len = i + 1;
        break;
    }
    *decrypted_msg_len = unpadded_private_msg_len;

    // Trim the decrypted message
    unsigned char* unpadded_private_msg = (unsigned char*)malloc(unpadded_private_msg_len);
    memcpy(unpadded_private_msg, decrypted_msg, unpadded_private_msg_len);
    free(decrypted_msg);

    return unpadded_private_msg;
}

int main() {
    const char* private_msg = "My test string";
    int private_msg_len = strlen(private_msg);
    char padding_character = '{';

    int secret_key_len;
    unsigned char* secret_key = generate_secret_key_for_AES_cipher(&secret_key_len);
    char* encoded_secret_key = base64_encode(secret_key, secret_key_len);
    free(secret_key);

    int encrypted_msg_len;
    unsigned char* encrypted_msg = encrypt_message((const unsigned char*)private_msg, private_msg_len, (const unsigned char*)encoded_secret_key, strlen(encoded_secret_key), padding_character, &encrypted_msg_len);

    int decrypted_msg_len;
    unsigned char* decrypted_msg = decrypt_message(encrypted_msg, encrypted_msg_len, (const unsigned char*)encoded_secret_key, strlen(encoded_secret_key), padding_character, &decrypted_msg_len);

    printf("   Secret Key: %s - (%d)\n", encoded_secret_key, strlen(encoded_secret_key));
    printf("Encrypted Msg: %s - (%d)\n", encrypted_msg, encrypted_msg_len);
    printf("Decrypted Msg: %s - (%d)\n", decrypted_msg, decrypted_msg_len);

    free((void*)encrypted_msg);
    free((void*)decrypted_msg);
    free(encoded_secret_key);

    return 0;
}