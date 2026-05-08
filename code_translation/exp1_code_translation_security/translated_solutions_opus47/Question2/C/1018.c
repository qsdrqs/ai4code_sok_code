#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdlib.h>

unsigned char* sign(const char* msg, EC_KEY* key, unsigned int* sig_len) {
    const unsigned char message[] = "message";
    size_t message_len = sizeof(message) - 1;  // exclude null terminator
    
    // Hash the message with SHA-1 (default hash in Python's ecdsa library)
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);
    
    // Allocate buffer for the signature
    *sig_len = ECDSA_size(key);
    unsigned char* sig = (unsigned char*)malloc(*sig_len);
    if (sig == NULL) return NULL;
    
    // Sign the hash
    if (ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, sig, sig_len, key) != 1) {
        free(sig);
        return NULL;
    }
    
    return sig;
}