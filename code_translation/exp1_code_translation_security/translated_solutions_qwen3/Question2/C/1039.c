#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <string.h>
#include <openssl/err.h>

/**
 * Signs the literal string "message" using the provided EC private key.
 *
 * @param message     Ignored; the function always signs the string "message".
 * @param message_len Ignored.
 * @param key         The EC private key to use for signing.
 * @param sig_len     Output parameter for the length of the returned signature.
 * @return            A pointer to the signature buffer (must be freed with OPENSSL_free),
 *                    or NULL on failure.
 */
unsigned char *signMessage(const unsigned char *message, size_t message_len, EC_KEY *key, unsigned int *sig_len) {
    // Silence unused parameter warnings
    (void)message;
    (void)message_len;

    // Step 1: Compute SHA-1 digest of the literal string "message"
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)"message", 7, digest);

    // Step 2: Sign the digest using the EC private key
    ECDSA_SIG *sig = ECDSA_do_sign(digest, SHA_DIGEST_LENGTH, key);
    if (!sig) {
        return NULL;
    }

    // Step 3: Get the curve's order to determine the required padding length
    const EC_GROUP *group = EC_KEY_get0_group(key);
    if (!group) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    BIGNUM *order = BN_new();
    if (!order) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    if (!EC_GROUP_get_order(group, order, NULL)) {
        BN_free(order);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    int order_len = BN_num_bytes(order);

    // Step 4: Allocate and zero buffers for r and s
    unsigned char *r_buf = OPENSSL_zalloc(order_len);
    unsigned char *s_buf = OPENSSL_zalloc(order_len);
    if (!r_buf || !s_buf) {
        OPENSSL_free(r_buf);
        OPENSSL_free(s_buf);
        BN_free(order);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Step 5: Convert r and s to big-endian, zero-padded buffers
    int r_n = BN_num_bytes(sig->r);
    int s_n = BN_num_bytes(sig->s);

    if (r_n > order_len || s_n > order_len) {
        OPENSSL_free(r_buf);
        OPENSSL_free(s_buf);
        BN_free(order);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    BN_bn2bin(sig->r, r_buf + (order_len - r_n));
    BN_bn2bin(sig->s, s_buf + (order_len - s_n));

    // Step 6: Concatenate r and s into the final signature buffer
    *sig_len = order_len * 2;
    unsigned char *signature = OPENSSL_malloc(*sig_len);
    if (!signature) {
        OPENSSL_free(r_buf);
        OPENSSL_free(s_buf);
        BN_free(order);
        ECDSA_SIG_free(sig);
        return NULL;
    }

    memcpy(signature, r_buf, order_len);
    memcpy(signature + order_len, s_buf, order_len);

    // Step 7: Clean up
    OPENSSL_free(r_buf);
    OPENSSL_free(s_buf);
    BN_free(order);
    ECDSA_SIG_free(sig);

    return signature;
}