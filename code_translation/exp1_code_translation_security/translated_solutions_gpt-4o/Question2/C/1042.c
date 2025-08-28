#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdlib.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key_hex, size_t* signature_len) {
    // Create a new EC_KEY object with the SECP256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        return NULL;
    }

    // Convert the private key from hex to binary
    BIGNUM* priv_key_bn = BN_new();
    if (BN_hex2bn(&priv_key_bn, (const char*)private_key_hex) == 0) {
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Set the private key
    if (EC_KEY_set_private_key(ec_key, priv_key_bn) == 0) {
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Create a buffer for the signature
    unsigned char* signature = (unsigned char*)malloc(ECDSA_size(ec_key));
    if (signature == NULL) {
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Sign the message
    if (ECDSA_sign(0, message, message_len, signature, (unsigned int*)signature_len, ec_key) == 0) {
        free(signature);
        BN_free(priv_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Clean up
    BN_free(priv_key_bn);
    EC_KEY_free(ec_key);

    return signature;
}