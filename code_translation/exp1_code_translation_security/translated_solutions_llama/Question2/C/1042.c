#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

/**
 * Signs a given message using ECDSA signing key.
 *
 * @param message      The message to be signed.
 * @param message_len  The length of the message.
 * @param private_key  The private key to sign the message.
 * @param private_key_len  The length of the private key.
 * @param signature    The buffer to store the signature.
 * @param signature_len  The buffer to store the length of the signature.
 *
 * @return 1 on success, 0 on failure.
 */
int sign_message(const unsigned char *message, size_t message_len,
                 const unsigned char *private_key, size_t private_key_len,
                 unsigned char **signature, size_t *signature_len) {
    // Create an ECDSA context
    ECDSA *ecdsa;
    const EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256k1);
    if (!group) {
        ERR_print_errors_fp(stderr);
        return 0;
    }

    ecdsa = ECDSA_new();
    if (!ecdsa) {
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    // Set the curve
    if (!ECDSA_set_group(ecdsa, group)) {
        ECDSA_free(ecdsa);
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    // Set the private key
    BIGNUM *bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!bn) {
        ECDSA_free(ecdsa);
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    if (!ECDSA_set_private_key(ecdsa, bn)) {
        BN_free(bn);
        ECDSA_free(ecdsa);
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    BN_free(bn);

    // Sign the message
    unsigned char *sig = OPENSSL_malloc(ECDSA_size(ecdsa));
    if (!sig) {
        ECDSA_free(ecdsa);
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    unsigned int sig_len;
    if (!ECDSA_sign(ECDSA_SHA256, message, message_len, sig, &sig_len, ecdsa)) {
        OPENSSL_free(sig);
        ECDSA_free(ecdsa);
        EC_GROUP_free(group);
        ERR_print_errors_fp(stderr);
        return 0;
    }

    // Store the signature and its length
    *signature = sig;
    *signature_len = sig_len;

    ECDSA_free(ecdsa);
    EC_GROUP_free(group);

    return 1;
}

int main() {
    const unsigned char *message = (const unsigned char *)"Hello, World!";
    size_t message_len = strlen((const char *)message);
    const unsigned char *private_key = "\x01\x02\x03\x04\x05\x06\x07\x08\x09\x10\x11\x12\x13\x14\x15\x16"
                                      "\x17\x18\x19\x20\x21\x22\x23\x24\x25\x26\x27\x28\x29\x30\x31";
    size_t private_key_len = 32;

    unsigned char *signature;
    size_t signature_len;

    if (sign_message(message, message_len, private_key, private_key_len, &signature, &signature_len)) {
        printf("Signature: ");
        for (size_t i = 0; i < signature_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        // Don't forget to free the signature
        OPENSSL_free(signature);
    } else {
        printf("Failed to sign the message.\n");
    }

    return 0;
}