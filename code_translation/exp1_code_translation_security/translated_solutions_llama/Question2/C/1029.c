#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to generate a new ECDSA key pair
void ecdsa_genkey(EC_KEY **sk, EC_KEY **vk) {
    *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (*sk == NULL) {
        printf("Failed to generate ECDSA key\n");
        exit(1);
    }

    *vk = EC_KEY_dup(*sk);
    if (*vk == NULL) {
        printf("Failed to duplicate ECDSA key\n");
        exit(1);
    }
}

// Function to sign a message using ECDSA
unsigned char *ecdsa_sign(EC_KEY *sk, const unsigned char *message, size_t message_len, size_t *signature_len) {
    ECDSA_SIG *signature;
    unsigned char *signature_bytes;

    signature = ECDSA_do_sign(message, message_len, sk);
    if (signature == NULL) {
        printf("Failed to sign message\n");
        exit(1);
    }

    *signature_len = ECDSA_size(sk);
    signature_bytes = (unsigned char *)malloc(*signature_len);
    if (signature_bytes == NULL) {
        printf("Failed to allocate memory for signature\n");
        exit(1);
    }

    ECDSA_SIG_to_bytes(signature_bytes, signature);
    ECDSA_SIG_free(signature);

    return signature_bytes;
}

// Function to verify a signature using ECDSA
int ecdsa_verify(EC_KEY *vk, const unsigned char *signature, size_t signature_len, const unsigned char *message, size_t message_len) {
    ECDSA_SIG *ecdsa_sig;

    ecdsa_sig = ECDSA_SIG_new();
    if (ecdsa_sig == NULL) {
        printf("Failed to create ECDSA signature object\n");
        exit(1);
    }

    if (!ECDSA_SIG_from_bytes(ecdsa_sig, signature, signature_len)) {
        printf("Failed to parse ECDSA signature\n");
        ECDSA_SIG_free(ecdsa_sig);
        return 0;
    }

    int result = ECDSA_do_verify(message, message_len, ecdsa_sig, vk);
    ECDSA_SIG_free(ecdsa_sig);

    return result;
}

// Function to print the private key
void print_private_key(EC_KEY *sk) {
    const BIGNUM *priv_key = EC_KEY_get0_private_key(sk);
    char *priv_key_str = BN_bn2hex(priv_key);
    printf("Private Key: %s\n", priv_key_str);
    OPENSSL_free(priv_key_str);
}

// Function to print the public key (verifying key)
void print_public_key(EC_KEY *vk) {
    const EC_POINT *pub_key = EC_KEY_get0_public_key(vk);
    const EC_GROUP *group = EC_KEY_get0_group(vk);
    size_t len = EC_POINT_point2oct(group, pub_key, POINT_CONVERSION_COMPRESSED, NULL, 0, NULL);
    unsigned char *pub_key_bytes = (unsigned char *)malloc(len);
    if (pub_key_bytes == NULL) {
        printf("Failed to allocate memory for public key\n");
        exit(1);
    }

    EC_POINT_point2oct(group, pub_key, POINT_CONVERSION_COMPRESSED, pub_key_bytes, len, NULL);
    printf("Public Key: ");
    for (size_t i = 0; i < len; i++) {
        printf("%02x", pub_key_bytes[i]);
    }
    printf("\n");
    free(pub_key_bytes);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Generate a new ECDSA key pair
    EC_KEY *sk, *vk;
    ecdsa_genkey(&sk, &vk);

    // Print the private key
    print_private_key(sk);

    // Print the public key (verifying key)
    print_public_key(vk);

    // Message to sign
    const unsigned char *message = (const unsigned char *)"This is a test message";
    size_t message_len = strlen((const char *)message);

    // Sign the message
    size_t signature_len;
    unsigned char *signature = ecdsa_sign(sk, message, message_len, &signature_len);

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Verify the signature
    int verification_result = ecdsa_verify(vk, signature, signature_len, message, message_len);
    printf("Verification result: %d\n", verification_result);

    // Clean up
    free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}