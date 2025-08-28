#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/aes.h>
#include <openssl/evp.h>

// Structure to hold AES context (similar to Python's AES object)
typedef struct {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];
    int mode;
} AES_Object;

// Function to create new AES object (equivalent to AES.new())
AES_Object* AES_new(const char* key, int mode, const char* iv) {
    AES_Object* obj = malloc(sizeof(AES_Object));
    if (!obj) return NULL;
    
    // Copy key and IV (truncate or pad to 16 bytes)
    memset(obj->key, 0, 16);
    memset(obj->iv, 0, 16);
    strncpy((char*)obj->key, key, 16);
    strncpy((char*)obj->iv, iv, 16);
    
    obj->mode = mode;
    obj->ctx = EVP_CIPHER_CTX_new();
    
    return obj;
}

// Function to free AES object
void AES_free(AES_Object* obj) {
    if (obj) {
        if (obj->ctx) {
            EVP_CIPHER_CTX_free(obj->ctx);
        }
        free(obj);
    }
}

// Encrypt function
int encrypt_string(const char* message, AES_Object* obj, unsigned char* output) {
    int len;
    int ciphertext_len;
    
    // Create a copy of IV since CFB mode modifies it
    unsigned char iv_copy[16];
    memcpy(iv_copy, obj->iv, 16);
    
    // Initialize encryption
    if (1 != EVP_EncryptInit_ex(obj->ctx, EVP_aes_128_cfb(), NULL, obj->key, iv_copy)) {
        return -1;
    }
    
    // Encrypt the message
    if (1 != EVP_EncryptUpdate(obj->ctx, output, &len, (unsigned char*)message, strlen(message))) {
        return -1;
    }
    ciphertext_len = len;
    
    // Finalize encryption
    if (1 != EVP_EncryptFinal_ex(obj->ctx, output + len, &len)) {
        return -1;
    }
    ciphertext_len += len;
    
    return ciphertext_len;
}

// Decrypt function
int decrypt_string(const unsigned char* ciphertext, int ciphertext_len, AES_Object* obj, char* output) {
    int len;
    int plaintext_len;
    
    // Create a copy of IV since CFB mode modifies it
    unsigned char iv_copy[16];
    memcpy(iv_copy, obj->iv, 16);
    
    // Initialize decryption
    if (1 != EVP_DecryptInit_ex(obj->ctx, EVP_aes_128_cfb(), NULL, obj->key, iv_copy)) {
        return -1;
    }
    
    // Decrypt the ciphertext
    if (1 != EVP_DecryptUpdate(obj->ctx, (unsigned char*)output, &len, ciphertext, ciphertext_len)) {
        return -1;
    }
    plaintext_len = len;
    
    // Finalize decryption
    if (1 != EVP_DecryptFinal_ex(obj->ctx, (unsigned char*)output + len, &len)) {
        return -1;
    }
    plaintext_len += len;
    
    // Null terminate the string
    output[plaintext_len] = '\0';
    
    return plaintext_len;
}

// Define mode constant (equivalent to AES.MODE_CFB)
#define AES_MODE_CFB 1

int main() {
    // Create AES objects (equivalent to AES.new())
    AES_Object* obj1 = AES_new("This is a key123", AES_MODE_CFB, "This is an IV456");
    AES_Object* obj2 = AES_new("This is a key123", AES_MODE_CFB, "This is an IV456");
    
    if (!obj1 || !obj2) {
        printf("Failed to create AES objects\n");
        return 1;
    }
    
    const char* message = "The answer is no";
    unsigned char encrypted[256];
    char decrypted[256];
    
    // Encrypt the message
    int enc_len = encrypt_string(message, obj1, encrypted);
    if (enc_len < 0) {
        printf("Encryption failed\n");
        AES_free(obj1);
        AES_free(obj2);
        return 1;
    }
    
    // Decrypt the message
    int dec_len = decrypt_string(encrypted, enc_len, obj2, decrypted);
    if (dec_len < 0) {
        printf("Decryption failed\n");
        AES_free(obj1);
        AES_free(obj2);
        return 1;
    }
    
    printf("Original message: %s\n", message);
    printf("Decrypted message: %s\n", decrypted);
    
    // Clean up
    AES_free(obj1);
    AES_free(obj2);
    
    return 0;
}