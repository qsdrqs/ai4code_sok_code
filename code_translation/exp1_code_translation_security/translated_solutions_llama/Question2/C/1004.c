#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to sign a message using ECDSA
unsigned char* sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key, size_t private_key_len) {
    // Set up the ECDSA curve (SECP256k1)
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create ECDSA key\n");
        return NULL;
    }

    // Load the private key
    BIGNUM* private_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!private_bn) {
        fprintf(stderr, "Failed to load private key\n");
        EC_KEY_free(key);
        return NULL;
    }

    // Set the private key
    if (!EC_KEY_set_private_key(key, private_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        BN_free(private_bn);
        EC_KEY_free(key);
        return NULL;
    }

    // Sign the message
    ECDSA_SIG* signature = ECDSA_do_sign(message, message_len, key);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        BN_free(private_bn);
        EC_KEY_free(key);
        return NULL;
    }

    // Get the signature length
    int sig_len = ECDSA_size(key);
    if (sig_len <= 0) {
        fprintf(stderr, "Failed to get signature length\n");
        ECDSA_SIG_free(signature);
        BN_free(private_bn);
        EC_KEY_free(key);
        return NULL;
    }

    // Allocate memory for the signature
    unsigned char* signed_message = malloc(sig_len);
    if (!signed_message) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        ECDSA_SIG_free(signature);
        BN_free(private_bn);
        EC_KEY_free(key);
        return NULL;
    }

    // Convert the signature to a byte array
    if (!ECDSA_SIG_to_bytes(signed_message, signature, sig_len)) {
        fprintf(stderr, "Failed to convert signature to byte array\n");
        free(signed_message);
        ECDSA_SIG_free(signature);
        BN_free(private_bn);
        EC_KEY_free(key);
        return NULL;
    }

    // Clean up
    ECDSA_SIG_free(signature);
    BN_free(private_bn);
    EC_KEY_free(key);

    return signed_message;
}

int main() {
    const char* message_str = "hello";
    const char* private_key_str = "qwertyuiopasdfghjklzxcvbnmqwerty";

    // Encode the message and private key as byte arrays
    size_t message_len = strlen(message_str);
    size_t private_key_len = strlen(private_key_str);
    unsigned char message[message_len];
    unsigned char private_key[private_key_len];
    memcpy(message, message_str, message_len);
    memcpy(private_key, private_key_str, private_key_len);

    // Sign the message
    unsigned char* signed_message = sign_message(message, message_len, private_key, private_key_len);
    if (signed_message) {
        // Print the signed message (as a hexadecimal string)
        for (int i = 0; i < ECDSA_size(EC_KEY_new_by_curve_name(NID_secp256k1)); i++) {
            printf("%02x", signed_message[i]);
        }
        printf("\n");

        // Free the signed message
        free(signed_message);
    }

    return 0;
}