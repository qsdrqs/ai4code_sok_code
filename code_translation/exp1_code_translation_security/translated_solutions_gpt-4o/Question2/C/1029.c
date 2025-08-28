#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to generate ECDSA key pair
void ecdsa_genkey(EC_KEY **sk, EC_KEY **vk) {
    *sk = EC_KEY_new_by_curve_name(NID_secp384r1); // NIST P-384 curve
    if (*sk == NULL) {
        fprintf(stderr, "Error creating EC_KEY object\n");
        exit(1);
    }

    if (EC_KEY_generate_key(*sk) != 1) {
        fprintf(stderr, "Error generating EC key pair\n");
        exit(1);
    }

    *vk = *sk; // In OpenSSL, the public key is part of the private key object
}

// Function to sign a message
unsigned char *ecdsa_sign(EC_KEY *sk, const unsigned char *message, size_t message_len, unsigned int *sig_len) {
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash); // Hash the message

    unsigned char *signature = (unsigned char *)malloc(ECDSA_size(sk));
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        exit(1);
    }

    if (ECDSA_sign(0, hash, SHA384_DIGEST_LENGTH, signature, sig_len, sk) != 1) {
        fprintf(stderr, "Error signing message\n");
        free(signature);
        exit(1);
    }

    return signature;
}

// Function to verify a signature
int ecdsa_verify_key(EC_KEY *vk, const unsigned char *signature, unsigned int sig_len, const unsigned char *message, size_t message_len) {
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash); // Hash the message

    return ECDSA_verify(0, hash, SHA384_DIGEST_LENGTH, signature, sig_len, vk);
}

int main() {
    const char *message = "This is a test message";
    size_t message_len = strlen(message);

    EC_KEY *sk = NULL, *vk = NULL;
    ecdsa_genkey(&sk, &vk);

    // Print private key
    const BIGNUM *priv_key = EC_KEY_get0_private_key(sk);
    if (priv_key) {
        char *priv_hex = BN_bn2hex(priv_key);
        printf("Private Key: %s\n", priv_hex);
        OPENSSL_free(priv_hex);
    }

    // Print public key
    const EC_POINT *pub_key = EC_KEY_get0_public_key(vk);
    if (pub_key) {
        char *pub_hex = EC_POINT_point2hex(EC_KEY_get0_group(vk), pub_key, POINT_CONVERSION_UNCOMPRESSED, NULL);
        printf("Public Key: %s\n", pub_hex);
        OPENSSL_free(pub_hex);
    }

    // Sign the message
    unsigned int sig_len;
    unsigned char *signature = ecdsa_sign(sk, (const unsigned char *)message, message_len, &sig_len);
    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Verify the signature
    int verify_result = ecdsa_verify_key(vk, signature, sig_len, (const unsigned char *)message, message_len);
    printf("Signature verification: %s\n", verify_result == 1 ? "Success" : "Failure");

    // Clean up
    free(signature);
    EC_KEY_free(sk);

    return 0;
}