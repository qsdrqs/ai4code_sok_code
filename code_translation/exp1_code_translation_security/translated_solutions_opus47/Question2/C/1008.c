#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/sha.h>

/*
 * Sign message using ECDSA key (secp256k1 curve)
 *
 * Returns: allocated buffer (caller must free) containing 64-byte signature (r || s).
 *          NULL on failure.
 * sig_len: output parameter set to the length of the signature
 */
unsigned char* sign_message(const char* message, const char* private_key, size_t* sig_len) {
    EC_KEY*       eckey     = NULL;
    BIGNUM*       priv_bn   = NULL;
    ECDSA_SIG*    sig       = NULL;
    unsigned char* signature = NULL;
    const BIGNUM *r, *s;
    unsigned char hash[SHA_DIGEST_LENGTH];
    int r_len, s_len;

    *sig_len = 0;

    /* Create EC_KEY with secp256k1 curve */
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!eckey) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        goto cleanup;
    }

    /* Convert private key bytes (big-endian) to BIGNUM */
    priv_bn = BN_bin2bn((const unsigned char*)private_key, (int)strlen(private_key), NULL);
    if (!priv_bn) {
        fprintf(stderr, "Failed to create BIGNUM from private key\n");
        goto cleanup;
    }

    /* Set the private key */
    if (!EC_KEY_set_private_key(eckey, priv_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        goto cleanup;
    }

    /* Hash the message with SHA-1 (default in Python's ecdsa library) */
    SHA1((const unsigned char*)message, strlen(message), hash);

    /* Sign the hash */
    sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, eckey);
    if (!sig) {
        fprintf(stderr, "Failed to sign message\n");
        goto cleanup;
    }

    /* Extract r and s components */
    ECDSA_SIG_get0(sig, &r, &s);

    /* Allocate 64-byte buffer for raw signature (32 bytes r + 32 bytes s) */
    signature = (unsigned char*)calloc(64, 1);
    if (!signature) {
        fprintf(stderr, "Failed to allocate signature buffer\n");
        goto cleanup;
    }

    /* Write r and s right-aligned (big-endian) in 32-byte blocks */
    r_len = BN_num_bytes(r);
    s_len = BN_num_bytes(s);
    BN_bn2bin(r, signature + (32 - r_len));
    BN_bn2bin(s, signature + 32 + (32 - s_len));

    *sig_len = 64;

cleanup:
    if (sig)     ECDSA_SIG_free(sig);
    if (priv_bn) BN_free(priv_bn);
    if (eckey)   EC_KEY_free(eckey);
    return signature;
}

/* Print bytes in Python b'...' format */
static void print_bytes(const unsigned char* data, size_t len) {
    printf("b'");
    for (size_t i = 0; i < len; i++) {
        unsigned char c = data[i];
        if      (c == '\\') printf("\\\\");
        else if (c == '\'') printf("\\'");
        else if (c == '\n') printf("\\n");
        else if (c == '\r') printf("\\r");
        else if (c == '\t') printf("\\t");
        else if (c >= 0x20 && c < 0x7F) printf("%c", c);
        else printf("\\x%02x", c);
    }
    printf("'\n");
}

int main(void) {
    const char* message     = "hello world";
    const char* private_key = "12345678901234567890123456789012";
    size_t sig_len = 0;

    unsigned char* signature = sign_message(message, private_key, &sig_len);
    if (!signature) {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    print_bytes(signature, sig_len);

    free(signature);
    return 0;
}