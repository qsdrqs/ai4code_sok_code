#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const char* message, const char* private_key_hex) {
    // Define the curve (SECP256k1)
    const EC_CURVE_NAME curve_name = NID_secp256k1;

    // Convert private key from hex to BIGNUM
    BIGNUM* private_key_bn = BN_new();
    BN_hex2bn(&private_key_bn, private_key_hex);

    // Create an EC_KEY object
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(curve_name);
    if (!ec_key) {
        BN_free(private_key_bn);
        return NULL;
    }

    // Set the private key
    EC_KEY_set_private_key(ec_key, private_key_bn);

    // Free BIGNUM
    BN_free(private_key_bn);

    // Sign the message
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(ec_key));
    unsigned int signature_len;
    if (!ECDSA_sign(0, (const unsigned char*)message, strlen(message), signature, &signature_len, ec_key)) {
        EC_KEY_free(ec_key);
        free(signature);
        return NULL;
    }

    // Free EC_KEY
    EC_KEY_free(ec_key);

    // Reallocate signature to actual size
    signature = realloc(signature, signature_len);

    return signature;
}

int main() {
    const char* message = "hello world";
    const char* private_key_hex = "1234567890123456789012345678901234567890123456789012345678901234";

    unsigned char* signature = sign_message(message, private_key_hex);

    if (signature) {
        printf("Signature: ");
        for (int i = 0; i < strlen((const char*)signature); i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        free(signature);
    } else {
        printf("Error generating signature\n");
    }

    return 0;
}