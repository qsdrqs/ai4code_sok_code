#include <stdio.h>
#include <stdbool.h>
#include "ecdsa.h" // Include the ECDSA library header file

/**
 * Function that signs a given message using an ECDSA signing key.
 * 
 * @param ecdsa_key: ECDSA signing key
 * @param message: Message to be signed
 * @param signature: Buffer to store the generated signature
 * @return: 0 on success, non-zero on failure
 */
int sign(ECDSA_SigningKey *ecdsa_key, const char *message, unsigned char *signature, size_t *signature_len) {
    // Use the ECDSA library's signing function
    if (ecdsa_sign(ecdsa_key, message, strlen(message), signature, signature_len) != 0) {
        fprintf(stderr, "Error: Signing failed.\n");
        return -1;
    }
    return 0;
}

/**
 * Function that verifies a given signature using an ECDSA verification key.
 * 
 * @param ecdsa_key: ECDSA verification key
 * @param message: Message to be verified
 * @param signature: Signature to be verified
 * @param signature_len: Length of the signature
 * @return: true if the signature is valid, false otherwise
 */
bool verify(ECDSA_VerificationKey *ecdsa_key, const char *message, const unsigned char *signature, size_t signature_len) {
    // Use the ECDSA library's verification function
    if (ecdsa_verify(ecdsa_key, message, strlen(message), signature, signature_len) == 1) {
        return true; // Signature is valid
    } else {
        return false; // Signature is invalid
    }
}