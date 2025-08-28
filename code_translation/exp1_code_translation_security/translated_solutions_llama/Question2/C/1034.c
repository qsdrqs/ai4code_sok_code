#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/ec.h>
#include <openssl/rand.h>

// Function to generate a private key
EC_KEY* generate_private_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to create EC_KEY\n");
        return NULL;
    }

    BIGNUM* priv = BN_new();
    if (!priv) {
        printf("Failed to create BIGNUM\n");
        EC_KEY_free(key);
        return NULL;
    }

    // Generate a random private key
    do {
        if (!BN_rand_range(priv, EC_get0_order(EC_KEY_get0_group(key)))) {
            printf("Failed to generate random BIGNUM\n");
            BN_free(priv);
            EC_KEY_free(key);
            return NULL;
        }
    } while (BN_is_zero(priv));

    if (!EC_KEY_set_private_key(key, priv)) {
        printf("Failed to set private key\n");
        BN_free(priv);
        EC_KEY_free(key);
        return NULL;
    }

    BN_free(priv);

    return key;
}

// Function to sign a message with a private key
unsigned char* ecdsa_sign(const char* message, EC_KEY* private_key, size_t* signature_len) {
    const unsigned char* message_bytes = (const unsigned char*)message;
    size_t message_len = strlen(message);

    ECDSA_SIG* signature = ECDSA_do_sign(message_bytes, message_len, private_key);
    if (!signature) {
        printf("Failed to sign message\n");
        return NULL;
    }

    const BIGNUM* r = ECDSA_SIG_get0_r(signature);
    const BIGNUM* s = ECDSA_SIG_get0_s(signature);

    size_t r_len = BN_num_bytes(r);
    size_t s_len = BN_num_bytes(s);

    // Calculate the total length of the signature
    *signature_len = 64; // 32 bytes for r and 32 bytes for s

    unsigned char* signature_bytes = (unsigned char*)malloc(*signature_len);
    if (!signature_bytes) {
        printf("Failed to allocate memory for signature\n");
        ECDSA_SIG_free(signature);
        return NULL;
    }

    // Convert r and s to bytes
    BN_bn2lebinpad(r, signature_bytes, r_len);
    memset(signature_bytes + r_len, 0, 32 - r_len);
    BN_bn2lebinpad(s, signature_bytes + 32, s_len);
    memset(signature_bytes + 32 + s_len, 0, 32 - s_len);

    ECDSA_SIG_free(signature);

    return signature_bytes;
}

// Function to verify a signature with a public key
int ecdsa_verify(const char* message, const unsigned char* signature, size_t signature_len, EC_KEY* public_key) {
    const unsigned char* message_bytes = (const unsigned char*)message;
    size_t message_len = strlen(message);

    BIGNUM* r = BN_bin2bn(signature, 32, NULL);
    BIGNUM* s = BN_bin2bn(signature + 32, 32, NULL);

    ECDSA_SIG* signature_struct = ECDSA_SIG_new();
    ECDSA_SIG_set0(signature_struct, r, s);

    int result = ECDSA_do_verify(message_bytes, message_len, signature_struct, public_key);

    ECDSA_SIG_free(signature_struct);

    return result;
}

int main() {
    // Generate a private key
    EC_KEY* private_key = generate_private_key();
    if (!private_key) {
        return 1;
    }

    // Get the public key
    EC_KEY* public_key = EC_KEY_dup(private_key);
    if (!public_key) {
        printf("Failed to duplicate EC_KEY\n");
        EC_KEY_free(private_key);
        return 1;
    }

    // Set the public key
    if (!EC_KEY_set_public_key(public_key, EC_KEY_get0_public_key(private_key))) {
        printf("Failed to set public key\n");
        EC_KEY_free(private_key);
        EC_KEY_free(public_key);
        return 1;
    }

    const char* message = "Test message";

    // Sign the message
    size_t signature_len;
    unsigned char* signature = ecdsa_sign(message, private_key, &signature_len);
    if (!signature) {
        EC_KEY_free(private_key);
        EC_KEY_free(public_key);
        return 1;
    }

    // Verify the signature
    int verification_result = ecdsa_verify(message, signature, signature_len, public_key);
    printf("Verification result: %d\n", verification_result);

    free(signature);
    EC_KEY_free(private_key);
    EC_KEY_free(public_key);

    return 0;
}