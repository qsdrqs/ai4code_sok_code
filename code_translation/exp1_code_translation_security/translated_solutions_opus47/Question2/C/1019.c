#include <openssl/ec.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <openssl/obj_mac.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

/*
 * Sign data using ECDSA with SHA-256.
 *
 * Returns a dynamically-allocated buffer containing the DER-encoded signature
 * (matching the output format of Python's cryptography library), or NULL on
 * error. The caller is responsible for freeing the returned buffer.
 * The length of the signature is written to *sig_len.
 */
unsigned char *sign_string(EVP_PKEY *private_key, const unsigned char *data,
                           size_t data_len, size_t *sig_len) {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    if (!ctx) {
        return NULL;
    }

    /* Equivalent to ec.ECDSA(hashes.SHA256()) */
    if (EVP_DigestSignInit(ctx, NULL, EVP_sha256(), NULL, private_key) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    if (EVP_DigestSignUpdate(ctx, data, data_len) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    /* First call: determine required signature buffer size */
    if (EVP_DigestSignFinal(ctx, NULL, sig_len) <= 0) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    unsigned char *signature = (unsigned char *)malloc(*sig_len);
    if (!signature) {
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    /* Second call: actually produce the signature */
    if (EVP_DigestSignFinal(ctx, signature, sig_len) <= 0) {
        free(signature);
        EVP_MD_CTX_free(ctx);
        return NULL;
    }

    EVP_MD_CTX_free(ctx);
    return signature;
}

/*
// Equivalent to the commented-out Python example:
// print(utils.decode_dss_signature(sign_string(ec.generate_private_key(ec.SECP384R1()), b"hello world")))
int main(void) {
    // Generate SECP384R1 private key (equivalent to ec.generate_private_key(ec.SECP384R1()))
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    EVP_PKEY_keygen_init(pctx);
    EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp384r1);
    EVP_PKEY *private_key = NULL;
    EVP_PKEY_keygen(pctx, &private_key);
    EVP_PKEY_CTX_free(pctx);

    const unsigned char data[] = "hello world";
    size_t sig_len = 0;
    unsigned char *signature = sign_string(private_key, data, sizeof(data) - 1, &sig_len);

    if (signature) {
        for (size_t i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
        free(signature);
    }

    EVP_PKEY_free(private_key);
    return 0;
}
*/