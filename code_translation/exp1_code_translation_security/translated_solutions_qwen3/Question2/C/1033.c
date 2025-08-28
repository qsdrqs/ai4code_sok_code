#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/objects.h>
#include <openssl/sha.h>
#include <stdlib.h>
#include <string.h>

/**
 * Signs a message using a newly generated ECDSA key on the specified curve.
 *
 * @param message     Pointer to the message data to be signed.
 * @param message_len Length of the message in bytes.
 * @param curve_name  Name of the elliptic curve (e.g., "prime256v1" for P-256).
 * @param sig_len     Pointer to a size_t where the length of the returned signature will be stored.
 *
 * @return A pointer to the signature buffer (64 bytes for P-256), or NULL on failure.
 *         The caller is responsible for freeing the returned buffer.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, const char* curve_name, size_t* sig_len) {
    EC_KEY* key = NULL;
    EC_GROUP* group = NULL;
    ECDSA_SIG* sig = NULL;
    BIGNUM* order = NULL;
    unsigned char msg_hash[SHA_DIGEST_LENGTH];
    unsigned char* signature = NULL;

    // Step 1: Compute SHA-1 hash of the message
    if (!SHA1(message, message_len, msg_hash)) {
        goto cleanup;
    }

    // Step 2: Get the NID (Numeric Identifier) for the curve from its name
    int curve_nid = OBJ_sn2nid(curve_name);
    if (curve_nid == NID_undef) {
        curve_nid = OBJ_ln2nid(curve_name);
        if (curve_nid == NID_undef) {
            goto cleanup;
        }
    }

    // Step 3: Create and initialize the EC group for the specified curve
    group = EC_GROUP_new_by_curve_name(curve_nid);
    if (!group) {
        goto cleanup;
    }

    // Step 4: Generate a new EC key pair
    key = EC_KEY_new();
    if (!key || EC_KEY_set_group(key, group) != 1 || !EC_KEY_generate_key(key)) {
        goto cleanup;
    }

    // Step 5: Sign the SHA-1 hash using ECDSA
    sig = ECDSA_do_sign(msg_hash, SHA_DIGEST_LENGTH, key);
    if (!sig) {
        goto cleanup;
    }

    // Step 6: Determine the size of the signature components (r and s)
    order = BN_new();
    if (!order || !EC_GROUP_get_order(group, order, NULL)) {
        goto cleanup;
    }

    int bn_len = (BN_num_bits(order) + 7) / 8; // Length in bytes of the curve's order
    *sig_len = bn_len * 2;

    // Step 7: Allocate buffers for r and s, and the final signature
    unsigned char* r_buf = (unsigned char*)calloc(bn_len, sizeof(unsigned char));
    unsigned char* s_buf = (unsigned char*)calloc(bn_len, sizeof(unsigned char));
    signature = (unsigned char*)malloc(*sig_len);

    if (!r_buf || !s_buf || !signature) {
        free(r_buf);
        free(s_buf);
        free(signature);
        signature = NULL;
        goto cleanup;
    }

    // Step 8: Convert r and s to fixed-length byte arrays (padded with zeros)
    int r_bytes = BN_bn2bin(sig->r, r_buf + (bn_len - BN_num_bytes(sig->r)));
    int s_bytes = BN_bn2bin(sig->s, s_buf + (bn_len - BN_num_bytes(sig->s)));

    if (r_bytes < 0 || s_bytes < 0) {
        free(r_buf);
        free(s_buf);
        free(signature);
        signature = NULL;
        goto cleanup;
    }

    // Step 9: Concatenate r and s into the final signature buffer
    memcpy(signature, r_buf, bn_len);
    memcpy(signature + bn_len, s_buf, bn_len);

cleanup:
    // Step 10: Clean up all allocated resources
    free(r_buf);
    free(s_buf);
    ECDSA_SIG_free(sig);
    EC_KEY_free(key);
    EC_GROUP_free(group);
    BN_free(order);

    return signature;
}