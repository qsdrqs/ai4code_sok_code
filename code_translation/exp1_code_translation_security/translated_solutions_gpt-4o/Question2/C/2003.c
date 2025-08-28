#include <stdio.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>

void sign(const char *inp, EC_KEY *key, unsigned char **signature, unsigned int *sig_len) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char *)inp, strlen(inp), hash);

    *signature = OPENSSL_malloc(ECDSA_size(key));
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, *signature, sig_len, key)) {
        fprintf(stderr, "Error signing message\n");
    }
}

int main() {
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!sk) {
        fprintf(stderr, "Error creating key\n");
        return 1;
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Error generating key\n");
        EC_KEY_free(sk);
        return 1;
    }

    unsigned char *signature = NULL;
    unsigned int sig_len = 0;
    sign("Hello world", sk, &signature, &sig_len);

    if (signature) {
        printf("Signature: ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
        OPENSSL_free(signature);
    }

    EC_KEY_free(sk);
    return 0;
}