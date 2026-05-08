// Compile with: gcc -o program program.c -ltomcrypt
// Requires libtomcrypt to be installed

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tomcrypt.h>

// Struct to hold ciphertext and tag (equivalent to Python's list [ciphertext, tag])
typedef struct {
    unsigned char *ciphertext;
    unsigned long ciphertext_len;
    unsigned char tag[16];
    unsigned long tag_len;
} EncryptedData;

EncryptedData encodeString(const char* message, 
                            const unsigned char* key, 
                            const unsigned char* nonce) {
    EncryptedData result;
    int cipher_idx = find_cipher("aes");
    unsigned long msg_len = strlen(message);
    
    // Allocate memory for ciphertext (same size as plaintext in EAX mode)
    result.ciphertext = (unsigned char*)malloc(msg_len);
    result.ciphertext_len = msg_len;
    result.tag_len = 16;
    
    // Encrypt and authenticate in one call (equivalent to encrypt_and_digest)
    eax_encrypt_authenticate_memory(cipher_idx,
        key, 16,
        nonce, 16,
        NULL, 0,  // no associated data (header)
        (const unsigned char*)message, msg_len,
        result.ciphertext,
        result.tag, &result.tag_len);
    
    return result;
}

char* decodeString(EncryptedData p,
                   const unsigned char* key,
                   const unsigned char* nonce) {
    int cipher_idx = find_cipher("aes");
    int stat;
    
    // Allocate memory for plaintext (+1 for null terminator)
    char* plaintext = (char*)malloc(p.ciphertext_len + 1);
    
    // Decrypt and verify (equivalent to decrypt_and_verify)
    eax_decrypt_verify_memory(cipher_idx,
        key, 16,
        nonce, 16,
        NULL, 0,  // no associated data (header)
        p.ciphertext, p.ciphertext_len,
        (unsigned char*)plaintext,
        p.tag, p.tag_len,
        &stat);
    
    // Null-terminate the plaintext for string handling (equivalent to decode())
    plaintext[p.ciphertext_len] = '\0';
    return plaintext;
}

int main() {
    const char* originalMessage = "secret data";
    unsigned char nonce[16];
    unsigned char key[16];
    
    // Register AES cipher with libtomcrypt
    register_cipher(&aes_desc);
    
    // Generate random bytes (equivalent to get_random_bytes)
    FILE* urandom = fopen("/dev/urandom", "rb");
    fread(nonce, 1, 16, urandom);
    fread(key, 1, 16, urandom);
    fclose(urandom);
    
    printf("%s\n", originalMessage);
    
    EncryptedData ciphertext = encodeString(originalMessage, key, nonce);
    char* decodedString = decodeString(ciphertext, key, nonce);
    
    printf("%s\n", decodedString);
    
    // Free allocated memory
    free(decodedString);
    free(ciphertext.ciphertext);
    
    return 0;
}