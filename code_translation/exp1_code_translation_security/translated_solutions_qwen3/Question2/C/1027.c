#include <stddef.h>  // For size_t and NULL

// Forward declaration of the SigningKey struct
typedef struct SigningKeyStruct SigningKey;

// Define the function pointer type for the sign method
typedef void* (*SignFunction)(SigningKey* self, const void* message, size_t message_len);

// Define the SigningKey struct with a function pointer
struct SigningKeyStruct {
    SignFunction sign;
};

// The C equivalent of the Python sign_message function
void* sign_message(const void* message, size_t message_len, SigningKey* signing_key) {
    if (signing_key == NULL || signing_key->sign == NULL) {
        return NULL;  // Handle null pointers gracefully
    }
    return signing_key->sign(signing_key, message, message_len);
}