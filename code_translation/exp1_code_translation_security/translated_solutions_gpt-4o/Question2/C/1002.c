#include <stdio.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/sha.h>
#include <openssl/err.h>

void handleErrors(void) {
    ERR_print_errors_fp(stderr);
    abort();
}

EC_KEY* generate_private_key() {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (key == NULL) handleErrors();

    if (EC_KEY_generate_key(key) != 1) handleErrors();

    return key;
}

unsigned char* sign(EC_KEY *key, const unsigned char *data, size_t data_len, unsigned int *sig_len) {
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    if (mdctx == NULL) handleErrors();

    const EVP_MD *md = EVP_sha256();
    if (EVP_DigestSignInit(mdctx, NULL, md, NULL, key) != 1) handleErrors();

    if (EVP_DigestSignUpdate(mdctx, data, data_len) != 1) handleErrors();

    if (EVP_DigestSignFinal(mdctx, NULL, sig_len) != 1) handleErrors();

    unsigned char *sig = (unsigned char *)OPENSSL_malloc(*sig_len);
    if (sig == NULL) handleErrors();

    if (EVP_DigestSignFinal(mdctx, sig, sig_len) != 1) handleErrors();

    EVP_MD_CTX_free(mdctx);

    return sig;
}

void test() {
    EC_KEY *private_key = generate_private_key();
    const char *data = "this is some data I'd like to sign";
    size_t data_len = strlen(data);

    unsigned int sig_len;
    unsigned char *signature = sign(private_key, (const unsigned char *)data, data_len, &sig_len);

    printf("Signature: ");
    for (unsigned int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    OPENSSL_free(signature);
    EC_KEY_free(private_key);
}

int main() {
    test();
    return 0;
}