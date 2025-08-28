#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to sign a message using a given ECDSA signing key
unsigned char* sign(const unsigned char* message, size_t message_len, const unsigned char* private_key, size_t private_key_len, size_t* signature_len) {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Create a new ECDSA key
    EC_KEY* ecdsa_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ecdsa_key) {
        fprintf(stderr, "Failed to create ECDSA key\n");
        return NULL;
    }

    // Set the private key
    BIGNUM* private_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!private_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    if (!EC_KEY_set_private_key(ecdsa_key, private_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        BN_free(private_bn);
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    BN_free(private_bn);

    // Sign the message
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, ecdsa_key);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    // Get the signature length
    *signature_len = ECDSA_size(ecdsa_key);

    // Allocate memory for the signature
    unsigned char* signed_message = (unsigned char*)malloc(*signature_len);
    if (!signed_message) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        ECDSA_free(signature);
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    // Get the signature
    if (!ECDSA_get0_signature(signature, NULL, (const unsigned char**)&signed_message)) {
        fprintf(stderr, "Failed to get signature\n");
        free(signed_message);
        ECDSA_free(signature);
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    // Clean up
    ECDSA_free(signature);
    EC_KEY_free(ecdsa_key);

    return signed_message;
}

int main() {
    // Example usage
    const unsigned char* message = (const unsigned char*)"Hello, World!";
    size_t message_len = strlen((const char*)message);
    const unsigned char* private_key = "\x01\x02\x03\x04\x05\x06\x07\x08\x09\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x20\x21\x22\x23\x24\x25\x26\x27\x28\x29\x30\x31\x32"; // Replace with your private key
    size_t private_key_len = 32;
    size_t signature_len;

    unsigned char* signed_message = sign(message, message_len, private_key, private_key_len, &signature_len);
    if (signed_message) {
        printf("Signed message: ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signed_message[i]);
        }
        printf("\n");

        // Don't forget to free the signed message
        free(signed_message);
    }

    return 0;
}