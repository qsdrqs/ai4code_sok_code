#include <stdio.h>
#include <stdlib.h>

// Assuming sk_sign is a function that signs a message
// and returns the signature as a newly allocated byte array
// The length of the signature is returned through the sig_len parameter
unsigned char* sk_sign(const unsigned char* m, size_t m_len, unsigned char** sig, size_t* sig_len);

// Function to sign a message using a signing key
unsigned char* sign(const unsigned char* m, size_t m_len, unsigned char** sig, size_t* sig_len, void* sk) {
    // Call the sk_sign function to get the signature
    return sk_sign(m, m_len, sig, sig_len);
}

int main() {
    // Example usage
    const unsigned char* message = "Hello, World!";
    size_t message_len = strlen((const char*)message);
    unsigned char* signature;
    size_t signature_len;
    void* signing_key; // Initialize your signing key here

    unsigned char* sig = sign(message, message_len, &signature, &signature_len, signing_key);

    // Don't forget to free the allocated memory
    free(signature);

    return 0;
}