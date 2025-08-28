#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

// Simplified ECDSA structures and constants
#define SECP256K1_PRIVATE_KEY_SIZE 32
#define SECP256K1_SIGNATURE_SIZE 64
#define SECP256K1_PUBLIC_KEY_SIZE 64

typedef struct {
    uint8_t data[SECP256K1_PRIVATE_KEY_SIZE];
} secp256k1_private_key_t;

typedef struct {
    uint8_t data[SECP256K1_SIGNATURE_SIZE];
} secp256k1_signature_t;

typedef struct {
    secp256k1_private_key_t private_key;
} ecdsa_signing_key_t;

// Simplified ECDSA implementation (in real code, use a proper crypto library like libsecp256k1)
// This is a mock implementation for demonstration purposes
int ecdsa_create_signing_key(ecdsa_signing_key_t* key, const uint8_t* private_key_data, size_t key_len) {
    if (key_len > SECP256K1_PRIVATE_KEY_SIZE) {
        return -1; // Error: key too long
    }
    
    memset(key->private_key.data, 0, SECP256K1_PRIVATE_KEY_SIZE);
    memcpy(key->private_key.data, private_key_data, key_len);
    return 0; // Success
}

// Mock signing function (in real implementation, this would perform actual ECDSA signing)
int ecdsa_sign(const ecdsa_signing_key_t* key, const uint8_t* message, size_t message_len, secp256k1_signature_t* signature) {
    // This is a simplified mock implementation
    // In a real implementation, you would use proper ECDSA signing algorithm
    
    // For demonstration, we'll create a deterministic "signature" based on message and key
    memset(signature->data, 0, SECP256K1_SIGNATURE_SIZE);
    
    // Simple hash-like operation (NOT cryptographically secure - just for demo)
    for (size_t i = 0; i < message_len && i < SECP256K1_SIGNATURE_SIZE; i++) {
        signature->data[i] = message[i] ^ key->private_key.data[i % SECP256K1_PRIVATE_KEY_SIZE];
    }
    
    return 0; // Success
}

/**
 * Function sign_message
 * 
 * Input: message and ECDSA key
 * Output: message signed using the provided key
 */
int sign_message(const uint8_t* message, size_t message_len, 
                 const uint8_t* private_key, size_t private_key_len, 
                 secp256k1_signature_t* signature) {
    
    // Create a new ECDSA key
    ecdsa_signing_key_t key;
    if (ecdsa_create_signing_key(&key, private_key, private_key_len) != 0) {
        return -1; // Error creating key
    }
    
    // Sign the message
    if (ecdsa_sign(&key, message, message_len, signature) != 0) {
        return -1; // Error signing message
    }
    
    return 0; // Success
}

// Helper function to print signature in hex format
void print_signature(const secp256k1_signature_t* signature) {
    printf("Signature: ");
    for (int i = 0; i < SECP256K1_SIGNATURE_SIZE; i++) {
        printf("%02x", signature->data[i]);
    }
    printf("\n");
}

int main() {
    const char* message = "hello";
    const char* private_key_str = "qwertyuiopasdfghjklzxcvbnmqwerty";
    
    secp256k1_signature_t signature;
    
    int result = sign_message((const uint8_t*)message, strlen(message),
                             (const uint8_t*)private_key_str, strlen(private_key_str),
                             &signature);
    
    if (result == 0) {
        print_signature(&signature);
    } else {
        printf("Error signing message\n");
    }
    
    return 0;
}