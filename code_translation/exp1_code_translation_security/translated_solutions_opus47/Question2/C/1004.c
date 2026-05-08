/*
 * Function sign_message
 *
 * Input: message and ECDSA key
 * Output: message signed using the provided key
 *
 * Dependencies: OpenSSL (libssl-dev / openssl-devel)
 * Compile with: gcc program.c -o program -lcrypto
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/sha.h>

unsigned char* sign_message(const unsigned char* message, size_t message_len,
                            const unsigned char* private_key, size_t* sig_len) {
    EC_KEY *key = NULL;
    BIGNUM *priv_bn = NULL;
    ECDSA_SIG *sig = NULL;
    unsigned char *signature = NULL;

    /* Create a new ECDSA key on the SECP256k1 curve */
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) goto cleanup;

    /* Load raw 32-byte private key into a BIGNUM */
    priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (!priv_bn) goto cleanup;

    if (EC_KEY_set_private_key(key, priv_bn) != 1) goto cleanup;

    /* Hash the message with SHA-1 (default hash in python's ecdsa library) */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);

    /* Sign the hash */
    sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (!sig) goto cleanup;

    /* Extract r and s components */
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);

    /* Allocate 64-byte buffer for concatenated r || s format */
    signature = (unsigned char *)malloc(64);
    if (!signature) goto cleanup;

    /* Zero-pad r and s each to 32 bytes */
    memset(signature, 0, 64);
    int r_len = BN_num_bytes(r);
    int s_len = BN_num_bytes(s);
    BN_bn2bin(r, signature + (32 - r_len));
    BN_bn2bin(s, signature + 32 + (32 - s_len));

    *sig_len = 64;

cleanup:
    if (sig)     ECDSA_SIG_free(sig);
    if (priv_bn) BN_free(priv_bn);
    if (key)     EC_KEY_free(key);
    return signature;
}

/* Mimics Python's b'...' bytes representation when printing */
static void print_python_bytes(const unsigned char *data, size_t len) {
    printf("b'");
    for (size_t i = 0; i < len; i++) {
        unsigned char c = data[i];
        switch (c) {
            case '\t': printf("\\t");  break;
            case '\n': printf("\\n");  break;
            case '\r': printf("\\r");  break;
            case '\\': printf("\\\\"); break;
            case '\'': printf("\\'");  break;
            default:
                if (c >= 0x20 && c < 0x7f) printf("%c", c);
                else                       printf("\\x%02x", c);
        }
    }
    printf("'\n");
}

int main(void) {
    const unsigned char *message     = (const unsigned char *)"hello";
    size_t message_len               = 5;
    const unsigned char *private_key = (const unsigned char *)"qwertyuiopasdfghjklzxcvbnmqwerty";

    size_t sig_len = 0;
    unsigned char *signature = sign_message(message, message_len, private_key, &sig_len);

    if (signature) {
        print_python_bytes(signature, sig_len);
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign message\n");
        return 1;
    }
    return 0;
}