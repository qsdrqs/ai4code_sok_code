#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key_hex, size_t* signature_len) {
    EC_KEY *eckey = NULL;
    unsigned char *signature = NULL;
    BIGNUM *priv_bn = NULL;

    // Create a new EC_KEY object with the SECP256k1 curve
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        return NULL;
    }

    // Convert the private key from hex string to BIGNUM
    priv_bn = BN_new();
    if (BN_hex2bn(&priv_bn, (const char*)private_key_hex) == 0) {
        fprintf(stderr, "Error converting private key to BIGNUM\n");
        EC_KEY_free(eckey);
        return NULL;
    }

    // Set the private key for the EC_KEY object
    if (EC_KEY_set_private_key(eckey, priv_bn) == 0) {
        fprintf(stderr, "Error setting private key\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Allocate memory for the signature
    signature = (unsigned char*)malloc(ECDSA_size(eckey));
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Sign the message
    if (ECDSA_sign(0, message, message_len, signature, (unsigned int*)signature_len, eckey) == 0) {
        fprintf(stderr, "Error signing message\n");
        free(signature);
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return NULL;
    }

    // Clean up
    EC_KEY_free(eckey);
    BN_free(priv_bn);

    return signature;
}

int main() {
    const char* message = "Hello, World!";
    const char* private_key_hex = "your_private_key_in_hex"; // Replace with your actual private key in hex
    size_t signature_len;
    unsigned char* signature;

    // Sign the message
    signature = sign_message((const unsigned char*)message, strlen(message), (const unsigned char*)private_key_hex, &signature_len);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        return 1;
    }

    // Print the signature in hex format
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free the allocated signature memory
    free(signature);

    return 0;
}