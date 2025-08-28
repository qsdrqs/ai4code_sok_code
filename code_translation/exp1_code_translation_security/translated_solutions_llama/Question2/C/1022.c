#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a random private key
EC_KEY* generate_private_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to create EC_KEY\n");
        return NULL;
    }

    BIGNUM* private_key = BN_new();
    if (!private_key) {
        printf("Failed to create BIGNUM\n");
        EC_KEY_free(key);
        return NULL;
    }

    // Generate a random private key
    if (!BN_rand_range(private_key, EC_get0_order(EC_KEY_get0_group(key), NULL, NULL))) {
        printf("Failed to generate random private key\n");
        BN_free(private_key);
        EC_KEY_free(key);
        return NULL;
    }

    // Set the private key
    if (!EC_KEY_set_private_key(key, private_key)) {
        printf("Failed to set private key\n");
        BN_free(private_key);
        EC_KEY_free(key);
        return NULL;
    }

    // Compute the public key
    if (!EC_KEY_generate_public_key(key)) {
        printf("Failed to generate public key\n");
        BN_free(private_key);
        EC_KEY_free(key);
        return NULL;
    }

    BN_free(private_key);
    return key;
}

int main() {
    // Generate private and public keys
    EC_KEY* private_key = generate_private_key();
    if (!private_key) {
        return 1;
    }

    const EC_POINT* public_key = EC_KEY_get0_public_key(private_key);

    // Message to be signed
    const char* message = "My test message";
    size_t message_len = strlen(message);

    // Generate signature
    ECDSA_SIG* signature = ECDSA_do_sign((const unsigned char*)message, message_len, private_key);
    if (!signature) {
        printf("Failed to generate signature\n");
        EC_KEY_free(private_key);
        return 1;
    }

    // Verify signature
    int is_valid = ECDSA_do_verify((const unsigned char*)message, message_len, signature, public_key, EC_KEY_get0_group(private_key));
    if (is_valid == 1) {
        printf("Signature is valid\n");
    } else if (is_valid == 0) {
        printf("Signature is not valid\n");
    } else {
        printf("Error verifying signature\n");
    }

    // Clean up
    ECDSA_SIG_free(signature);
    EC_KEY_free(private_key);

    return 0;
}