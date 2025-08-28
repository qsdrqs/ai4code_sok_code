#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Assuming these headers will be provided for ECDSA functionality
#include "ecdsa.h"
#include "private_key.h"
#include "public_key.h"
#include "signature.h"

// Function to generate ECDSA signed message
Signature* generate_ecdsa_signed_msg(const char* message, PrivateKey* privateKey) {
    return ecdsa_sign(message, privateKey);
}

int main() {
    // Generate Keys
    PrivateKey* privateKey = private_key_new();
    if (privateKey == NULL) {
        fprintf(stderr, "Failed to generate private key\n");
        return 1;
    }
    
    PublicKey* publicKey = private_key_get_public_key(privateKey);
    if (publicKey == NULL) {
        fprintf(stderr, "Failed to generate public key\n");
        private_key_free(privateKey);
        return 1;
    }
    
    const char* message = "My test message";
    
    // Generate signature
    Signature* signature = generate_ecdsa_signed_msg(message, privateKey);
    if (signature == NULL) {
        fprintf(stderr, "Failed to generate signature\n");
        private_key_free(privateKey);
        public_key_free(publicKey);
        return 1;
    }
    
    // Print signature (assuming there's a function to convert signature to string)
    char* signature_str = signature_to_string(signature);
    if (signature_str != NULL) {
        printf("%s\n", signature_str);
        free(signature_str);
    }
    
    // Cleanup
    signature_free(signature);
    private_key_free(privateKey);
    public_key_free(publicKey);
    
    return 0;
}