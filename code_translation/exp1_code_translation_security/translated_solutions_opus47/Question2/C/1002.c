#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/obj_mac.h>

/* Sign data using EC key with ECDSA-SHA256.
   Returns dynamically allocated signature buffer (caller must OPENSSL_free it),
   or NULL on error. Writes signature length to *sig_len. */
unsigned char *sign(EVP_PKEY *key, const unsigned char *data, size_t data_len,
                    size_t *sig_len) {
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    if (!mdctx) return NULL;

    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, key) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    if (EVP_DigestSignUpdate(mdctx, data, data_len) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    /* First call determines required signature buffer size */
    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) != 1) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    unsigned char *sig = OPENSSL_malloc(*sig_len);
    if (!sig) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    /* Second call writes the actual signature */
    if (EVP_DigestSignFinal(mdctx, sig, sig_len) != 1) {
        OPENSSL_free(sig);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }

    EVP_MD_CTX_free(mdctx);
    return sig;
}

/* Print bytes in Python's b'...' repr style */
static void print_bytes(const unsigned char *data, size_t len) {
    printf("b'");
    for (size_t i = 0; i < len; i++) {
        unsigned char c = data[i];
        if (c == '\\')      printf("\\\\");
        else if (c == '\'') printf("\\'");
        else if (c == '\n') printf("\\n");
        else if (c == '\r') printf("\\r");
        else if (c == '\t') printf("\\t");
        else if (c >= 32 && c <= 126) printf("%c", c);
        else                printf("\\x%02x", c);
    }
    printf("'\n");
}

void test(void) {
    /* Generate EC private key on the SECP384R1 curve */
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) {
        fprintf(stderr, "EVP_PKEY_CTX_new_id failed\n");
        return;
    }

    if (EVP_PKEY_keygen_init(pctx) != 1) {
        fprintf(stderr, "EVP_PKEY_keygen_init failed\n");
        EVP_PKEY_CTX_free(pctx);
        return;
    }

    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp384r1) != 1) {
        fprintf(stderr, "Failed to set curve to SECP384R1\n");
        EVP_PKEY_CTX_free(pctx);
        return;
    }

    EVP_PKEY *private_key = NULL;
    if (EVP_PKEY_keygen(pctx, &private_key) != 1) {
        fprintf(stderr, "EVP_PKEY_keygen failed\n");
        EVP_PKEY_CTX_free(pctx);
        return;
    }
    EVP_PKEY_CTX_free(pctx);

    const unsigned char *data = (const unsigned char *)"this is some data I'd like to sign";
    size_t data_len = strlen((const char *)data);

    size_t sig_len = 0;
    unsigned char *sig = sign(private_key, data, data_len, &sig_len);
    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        EVP_PKEY_free(private_key);
        return;
    }

    print_bytes(sig, sig_len);

    OPENSSL_free(sig);
    EVP_PKEY_free(private_key);
}

int main(void) {
    test();
    return 0;
}