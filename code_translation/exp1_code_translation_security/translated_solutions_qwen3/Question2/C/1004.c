#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/objects.h>
#include <openssl/err.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Signs a message using an ECDSA private key with the SECP256k1 curve.
 *
 * @param message         The message to sign (raw bytes).
 * @param msg_len         Length of the message in bytes.
 * @param private_key     The private key as a byte string (32 bytes for SECP256k1).
 * @param pk_len          Length of the private key in bytes.
 * @param sig_len         Output: Length of the resulting signature.
 * @return                The DER-encoded signature, or NULL on failure.
 */
unsigned char *sign_message(const unsigned char *message, int msg_len,
                             const unsigned char *private_key, int pk_len,
                             unsigned int *sig_len) {
    EC_GROUP *group = NULL;
    EC_KEY *key = NULL;
    EC_POINT *pub_key = NULL;
    BN_CTX *ctx = NULL;
    BIGNUM *prv = NULL, *order = NULL;
    unsigned char *signature = NULL;

    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Create the SECP256k1 curve group
    group = EC_GROUP_new_by_nid(NID_secp256k1);
    if (!group) goto cleanup;

    // Create a BIGNUM context
    ctx = BN_CTX_new();
    if (!ctx) goto cleanup;

    // Get the order of the curve
    order = BN_new();
    if (!order || !EC_GROUP_get_order(group, order, ctx)) goto cleanup;

    // Convert private key bytes to BIGNUM and reduce modulo the order
    prv = BN_new();
    if (!prv || !BN_bin2bn(private_key, pk_len, prv)) goto cleanup;
    BN_nnmod(prv, prv, order, ctx);

    // Create and configure the EC key
    key = EC_KEY_new();
    if (!key || !EC_KEY_set_group(key, group) || !EC_KEY_set_private_key(key, prv)) goto cleanup;

    // Compute the public key
    pub_key = EC_POINT_new(group);
    if (!pub_key || !EC_POINT_mul(group, pub_key, prv, NULL, NULL, ctx)) goto cleanup;
    if (!EC_KEY_set_public_key(key, pub_key)) goto cleanup;

    // Hash the message using SHA-1
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, msg_len, hash);

    // Determine the required signature buffer size
    *sig_len = ECDSA_size(key);
    signature = (unsigned char *)malloc(*sig_len);
    if (!signature) goto cleanup;

    // Sign the hash
    if (!ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, signature, sig_len, key)) {
        free(signature);
        signature = NULL;
        goto cleanup;
    }

cleanup:
    // Clean up all OpenSSL objects
    if (group) EC_GROUP_free(group);
    if (ctx) BN_CTX_free(ctx);
    if (prv) BN_free(prv);
    if (order) BN_free(order);
    if (pub_key) EC_POINT_free(pub_key);
    if (key) EC_KEY_free(key);

    return signature;
}

/**
 * Main function to demonstrate the sign_message function.
 */
int main() {
    const char *msg = "hello";
    const char *private_key_str = "qwertyuiopasdfghjklzxcvbnmqwerty";
    unsigned int sig_len;
    unsigned char *signature = sign_message((const unsigned char *)msg, strlen(msg),
                                            (const unsigned char *)private_key_str, strlen(private_key_str),
                                            &sig_len);

    if (signature) {
        // Print the signature in hexadecimal format
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
        free(signature);
    } else {
        fprintf(stderr, "Error: Failed to sign message.\n");
        return 1;
    }

    return 0;
}