#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key, size_t private_key_len, size_t* signature_len) {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Set up the curve (SECP256k1)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return NULL;
    }

    // Set the private key
    BIGNUM* private_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!private_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(key);
        return NULL;
    }
    EC_KEY_set_private_key(key, private_bn);
    BN_free(private_bn);

    // Sign the message
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, key);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(key);
        return NULL;
    }

    // Get the signature length
    *signature_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        ECDSA_free(signature);
        EC_KEY_free(key);
        return NULL;
    }

    // Convert the signature to bytes
    ECDSA_SIG_to_bytes(signature_bytes, signature, *signature_len);

    // Clean up
    ECDSA_free(signature);
    EC_KEY_free(key);

    return signature_bytes;
}

int main() {
    // Example usage
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);
    const unsigned char* private_key = (const unsigned char*)"\x01\x02\x03\x04\x05\x06\x07\x08\x09\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x20\x21\x22\x23\x24\x25\x26\x27\x28\x29\x30\x31\x32"; // Replace with your private key
    size_t private_key_len = 32; // Replace with your private key length

    size_t signature_len;
    unsigned char* signature = sign_message(message, message_len, private_key, private_key_len, &signature_len);

    if (signature) {
        printf("Signature: ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        // Don't forget to free the signature
        free(signature);
    }

    return 0;
}