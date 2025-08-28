#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to generate a key pair (private and public)
void ecdsa_genkey(EC_KEY **sk, EC_KEY **vk) {
    *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    EC_KEY_generate_key(*sk);

    *vk = EC_KEY_new_by_curve_name(NID_secp384r1);
    EC_KEY_set_public_key(*vk, EC_KEY_get0_public_key(*sk));
}

// Function to sign a message using the private key
ECDSA_SIG* ecdsa_sign(EC_KEY *sk, const unsigned char *message, size_t message_len) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, digest);
    return ECDSA_do_sign(digest, SHA_DIGEST_LENGTH, sk);
}

// Function to verify a signature using the public key
int ecdsa_verify_key(EC_KEY *vk, ECDSA_SIG *sig, const unsigned char *message, size_t message_len) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, digest);
    return ECDSA_do_verify(digest, SHA_DIGEST_LENGTH, sig, vk);
}

// Function to generate a key, sign a message, verify, and return the signature
ECDSA_SIG* ecdsa_key(const unsigned char *message, size_t message_len) {
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    EC_KEY_generate_key(sk);

    EC_KEY *vk = EC_KEY_new_by_curve_name(NID_secp384r1);
    EC_KEY_set_public_key(vk, EC_KEY_get0_public_key(sk));

    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, digest);

    ECDSA_SIG *sig = ECDSA_do_sign(digest, SHA_DIGEST_LENGTH, sk);

    int verified = ECDSA_do_verify(digest, SHA_DIGEST_LENGTH, sig, vk);
    printf("Verification result: %d\n", verified);

    EC_KEY_free(sk);
    EC_KEY_free(vk);
    return sig;
}

int main() {
    const unsigned char message[] = "This is a test message";
    size_t message_len = strlen((const char *)message);

    EC_KEY *sk, *vk;
    ecdsa_genkey(&sk, &vk);

    // Print private key (48 bytes)
    BIGNUM *priv = EC_KEY_get0_private_key(sk);
    unsigned char priv_bin[48];
    BN_bn2binpad(priv, priv_bin, 48);
    printf("Private Key (hex): ");
    for (int i = 0; i < 48; i++) {
        printf("%02x", priv_bin[i]);
    }
    printf("\n");

    // Print public key (uncompressed format: 0x04 || x || y)
    const EC_GROUP *group = EC_KEY_get0_group(vk);
    EC_POINT *pub = EC_KEY_get0_public_key(vk);
    size_t pub_len = EC_POINT_point2oct(group, pub, POINT_CONVERSION_UNCOMPRESSED, NULL, 0, NULL);
    unsigned char *pub_bin = OPENSSL_malloc(pub_len);
    EC_POINT_point2oct(group, pub, POINT_CONVERSION_UNCOMPRESSED, pub_bin, pub_len, NULL);

    printf("Public Key (hex): ");
    for (size_t i = 0; i < pub_len; i++) {
        printf("%02x", pub_bin[i]);
    }
    printf("\n");
    OPENSSL_free(pub_bin);

    // Sign the message
    ECDSA_SIG *sig = ecdsa_sign(sk, message, message_len);

    // Print signature (DER encoding)
    unsigned char *der_sig = NULL;
    int der_sig_len = i2d_ECDSA_SIG(sig, &der_sig);
    printf("Signature (hex): ");
    for (int i = 0; i < der_sig_len; i++) {
        printf("%02x", der_sig[i]);
    }
    printf("\n");
    OPENSSL_free(der_sig);

    // Verify the signature
    int verified = ecdsa_verify_key(vk, sig, message, message_len);
    printf("Final Verification: %d\n", verified);

    // Clean up
    ECDSA_SIG_free(sig);
    EC_KEY_free(sk);
    EC_KEY_free(vk);

    return 0;
}