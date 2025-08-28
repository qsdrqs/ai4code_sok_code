#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to sign a message using ECDSA with SECP256k1
char* sign_message(const char* message, const unsigned char* private_key) {
    // Step 1: Compute SHA-256 hash of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), hash);

    // Step 2: Create EC_KEY with SECP256k1 curve
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return NULL;
    }

    // Step 3: Convert private key bytes to BIGNUM
    BIGNUM* priv_key = BN_new();
    BN_bin2bn(private_key, 32, priv_key);

    // Step 4: Set private key in EC_KEY
    if (!EC_KEY_set_private_key(key, priv_key)) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(key);
        BN_free(priv_key);
        return NULL;
    }

    // Step 5: Sign the hash
    unsigned int sig_len = ECDSA_size(key);
    unsigned char* sig = malloc(sig_len);
    if (!sig) {
        fprintf(stderr, "Failed to allocate signature buffer\n");
        EC_KEY_free(key);
        BN_free(priv_key);
        return NULL;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, sig, &sig_len, key) != 1) {
        fprintf(stderr, "ECDSA_sign failed\n");
        free(sig);
        EC_KEY_free(key);
        BN_free(priv_key);
        return NULL;
    }

    // Step 6: Convert signature to hex string
    char* hex_sig = malloc(sig_len * 2 + 1);
    if (!hex_sig) {
        fprintf(stderr, "Failed to allocate hex signature buffer\n");
        free(sig);
        EC_KEY_free(key);
        BN_free(priv_key);
        return NULL;
    }

    for (size_t i = 0; i < sig_len; i++) {
        sprintf(hex_sig + i * 2, "%02x", sig[i]);
    }
    hex_sig[sig_len * 2] = '\0';

    // Step 7: Clean up
    free(sig);
    EC_KEY_free(key);
    BN_free(priv_key);

    return hex_sig;
}

// Main function to test the sign_message function
int main() {
    // Test private key (32 bytes, hex encoded)
    const char* private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    size_t private_key_len = strlen(private_key_hex) / 2;

    // Convert hex string to binary
    unsigned char* private_key = malloc(private_key_len);
    if (!private_key) {
        fprintf(stderr, "Failed to allocate private key buffer\n");
        return 1;
    }

    for (size_t i = 0; i < private_key_len; i++) {
        sscanf(private_key_hex + (i * 2), "%2hhx", &private_key[i]);
    }

    // Message to sign
    const char* message = "Hello";

    // Sign the message
    char* signature = sign_message(message, private_key);
    if (signature) {
        printf("Signature: %s\n", signature);
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign message\n");
    }

    // Clean up
    free(private_key);

    return 0;
}