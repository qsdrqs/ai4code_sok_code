/*
 * ECDSA example using OpenSSL (translated from python's `ecdsa` library)
 *
 * Compile:
 *   gcc -o ecdsa_example ecdsa_example.c -lcrypto
 *
 * Note: The EC_KEY API is deprecated in OpenSSL 3.x but still functional.
 * You may need: -Wno-deprecated-declarations
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/err.h>

#define P384_ORDER_BYTES      48    /* P-384 private key / r / s / x / y size */
#define P384_SIGNATURE_BYTES  96    /* r || s */
#define P384_PUBLIC_KEY_BYTES 96    /* x || y (without the 0x04 prefix) */

/* -------- Python-like byte string printer (b'...') -------- */
static void print_bytes(const unsigned char *data, size_t len) {
    printf("b'");
    for (size_t i = 0; i < len; i++) {
        unsigned char c = data[i];
        if (c >= 0x20 && c <= 0x7e && c != '\\' && c != '\'')
            printf("%c", c);
        else
            printf("\\x%02x", c);
    }
    printf("'\n");
}

/* -------- Python: ecdsa_genkey() -- returns (sk, vk) --
 * In OpenSSL, one EC_KEY holds both private and public key */
EC_KEY* ecdsa_genkey(void) {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) { ERR_print_errors_fp(stderr); return NULL; }
    if (!EC_KEY_generate_key(key)) {
        ERR_print_errors_fp(stderr);
        EC_KEY_free(key);
        return NULL;
    }
    return key;
}

/* -------- Python: ecdsa_sign(sk, message) --
 * Returns heap-allocated r || s (96 bytes for P-384) */
unsigned char* ecdsa_sign(EC_KEY *sk,
                          const unsigned char *message, size_t message_len,
                          size_t *sig_len) {
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash);

    ECDSA_SIG *sig = ECDSA_do_sign(hash, SHA384_DIGEST_LENGTH, sk);
    if (!sig) { ERR_print_errors_fp(stderr); return NULL; }

    const BIGNUM *r = NULL, *s = NULL;
    ECDSA_SIG_get0(sig, &r, &s);

    unsigned char *signature = (unsigned char*)malloc(P384_SIGNATURE_BYTES);
    if (!signature) { ECDSA_SIG_free(sig); return NULL; }

    /* Fixed-length r || s, matching python's `ecdsa` default serializer */
    BN_bn2binpad(r, signature,                     P384_ORDER_BYTES);
    BN_bn2binpad(s, signature + P384_ORDER_BYTES, P384_ORDER_BYTES);

    *sig_len = P384_SIGNATURE_BYTES;
    ECDSA_SIG_free(sig);
    return signature;
}

/* -------- Python: ecdsa_verify_key(vk, signature, message) -- */
int ecdsa_verify_key(EC_KEY *vk,
                     const unsigned char *signature, size_t sig_len,
                     const unsigned char *message, size_t message_len) {
    if (sig_len != P384_SIGNATURE_BYTES) return 0;

    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, hash);

    ECDSA_SIG *sig = ECDSA_SIG_new();
    if (!sig) return 0;

    BIGNUM *r = BN_bin2bn(signature,                     P384_ORDER_BYTES, NULL);
    BIGNUM *s = BN_bin2bn(signature + P384_ORDER_BYTES, P384_ORDER_BYTES, NULL);
    if (!r || !s || !ECDSA_SIG_set0(sig, r, s)) {
        BN_free(r); BN_free(s);
        ECDSA_SIG_free(sig);
        return 0;
    }
    /* sig now owns r and s */

    int result = ECDSA_do_verify(hash, SHA384_DIGEST_LENGTH, sig, vk);
    ECDSA_SIG_free(sig);
    return result;
}

/* -------- Python: ecdsa_verify(signature, message, vk) -- */
int ecdsa_verify(const unsigned char *signature, size_t sig_len,
                 const unsigned char *message, size_t message_len,
                 EC_KEY *vk) {
    return ecdsa_verify_key(vk, signature, sig_len, message, message_len);
}

/* -------- Python: ecdsa_key(message, key) --
 * Generates key, signs, verifies, returns signature */
unsigned char* ecdsa_key(const unsigned char *message, size_t message_len,
                         size_t *sig_len) {
    EC_KEY *key = ecdsa_genkey();
    if (!key) return NULL;

    unsigned char *signature = ecdsa_sign(key, message, message_len, sig_len);
    if (!signature) { EC_KEY_free(key); return NULL; }

    int ok = ecdsa_verify_key(key, signature, *sig_len, message, message_len);
    printf("%s\n", ok == 1 ? "True" : "False");

    EC_KEY_free(key);
    return signature;
}

/* -------- Helpers to replicate sk.to_string() / vk.to_string() -------- */
static void get_private_key_bytes(EC_KEY *key, unsigned char *out48) {
    const BIGNUM *priv = EC_KEY_get0_private_key(key);
    BN_bn2binpad(priv, out48, P384_ORDER_BYTES);
}

static int get_public_key_bytes(EC_KEY *key, unsigned char *out96) {
    const EC_POINT *pub   = EC_KEY_get0_public_key(key);
    const EC_GROUP *group = EC_KEY_get0_group(key);

    BIGNUM *x = BN_new(), *y = BN_new();
    BN_CTX *ctx = BN_CTX_new();
    if (!x || !y || !ctx) {
        BN_free(x); BN_free(y); BN_CTX_free(ctx);
        return 0;
    }

    int ok = EC_POINT_get_affine_coordinates(group, pub, x, y, ctx);
    if (ok) {
        BN_bn2binpad(x, out96,                     P384_ORDER_BYTES);
        BN_bn2binpad(y, out96 + P384_ORDER_BYTES, P384_ORDER_BYTES);
    }
    BN_free(x); BN_free(y); BN_CTX_free(ctx);
    return ok;
}

/* -------- Main (equivalent to `if __name__ == "__main__"`) -------- */
int main(void) {
    const unsigned char message[] = "This is a test message";
    size_t message_len = sizeof(message) - 1;  /* strip the NUL */

    /* sk, vk = ecdsa_genkey() */
    EC_KEY *key = ecdsa_genkey();
    if (!key) return 1;

    /* print(sk.to_string()) */
    unsigned char sk_bytes[P384_ORDER_BYTES];
    get_private_key_bytes(key, sk_bytes);
    print_bytes(sk_bytes, sizeof(sk_bytes));

    /* print(vk.to_string()) */
    unsigned char vk_bytes[P384_PUBLIC_KEY_BYTES];
    get_public_key_bytes(key, vk_bytes);
    print_bytes(vk_bytes, sizeof(vk_bytes));

    /* signature = ecdsa_sign(sk, message) */
    size_t sig_len = 0;
    unsigned char *signature = ecdsa_sign(key, message, message_len, &sig_len);
    if (!signature) { EC_KEY_free(key); return 1; }

    /* print(signature) */
    print_bytes(signature, sig_len);

    /* print(ecdsa_verify_key(vk, signature, message)) */
    int ok = ecdsa_verify_key(key, signature, sig_len, message, message_len);
    printf("%s\n", ok == 1 ? "True" : "False");

    free(signature);
    EC_KEY_free(key);
    return 0;
}