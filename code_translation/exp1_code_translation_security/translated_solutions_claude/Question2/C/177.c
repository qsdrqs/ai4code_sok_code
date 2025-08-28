#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Forward declaration of the signature structure
typedef struct {
    unsigned char* data;
    size_t length;
} Signature;

// Forward declaration of the private key structure
typedef struct {
    unsigned char* key_data;
    size_t key_length;
    // Function pointer for the sign method
    Signature (*sign)(struct PrivateKey* self, const unsigned char* message, size_t message_len);
} PrivateKey;

// Function that mimics the Python sign function
Signature sign(const unsigned char* m, size_t message_len, PrivateKey* sk) {
    return sk->sign(sk, m, message_len);
}

// Helper function to free signature memory
void free_signature(Signature* sig) {
    if (sig && sig->data) {
        free(sig->data);
        sig->data = NULL;
        sig->length = 0;
    }
}