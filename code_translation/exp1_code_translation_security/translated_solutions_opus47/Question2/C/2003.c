/*
 * Compile with: gcc -o sign sign.c -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>

unsigned char* sign(const char* inp, EC_KEY* key, size_t* sig_len) {
    /* Hash input with SHA-1 (default hash for Python ecdsa library) */
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char*)inp, strlen(inp), hash);

    /* Sign the hash */
    ECDSA_SIG* sig = ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, key);
    if (!sig) return NULL;

    /* Extract r and s components */
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(sig, &r, &s);

    /* Determine size of each component based on the curve */
    const EC_GROUP* group = EC_KEY_get0_group(key);
    int curve_size = (EC_GROUP_get_degree(group) + 7) / 8;

    *sig_len = 2 * curve_size;
    unsigned char* signature = malloc(*sig_len);
    if (!signature) {
        ECDSA_SIG_free(sig);
        return NULL;
    }

    /* Concatenate r and s as big-endian, zero-padded (matches ecdsa's sigencode_string) */
    BN_bn2binpad(r, signature, curve_size);
    BN_bn2binpad(s, signature + curve_size, curve_size);

    ECDSA_SIG_free(sig);
    return signature;
}

/* Print a byte array similar to Python's print(bytes) output */
static void print_bytes(const unsigned char* data, size_t len) {
    printf("b'");
    for (size_t i = 0; i < len; i++) {
        unsigned char b = data[i];
        if (b == '\\')      printf("\\\\");
        else if (b == '\'') printf("\\'");
        else if (b == '\n') printf("\\n");
        else if (b == '\r') printf("\\r");
        else if (b == '\t') printf("\\t");
        else if (b >= 0x20 && b < 0x7f) printf("%c", b);
        else                printf("\\x%02x", b);
    }
    printf("'\n");
}

int main(void) {
    /* Generate signing key using NIST192p (ecdsa library default curve) */
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!sk) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate EC_KEY\n");
        EC_KEY_free(sk);
        return 1;
    }

    size_t sig_len;
    unsigned char* signature = sign("Hello world", sk, &sig_len);
    if (!signature) {
        fprintf(stderr, "Failed to sign\n");
        EC_KEY_free(sk);
        return 1;
    }

    print_bytes(signature, sig_len);

    free(signature);
    EC_KEY_free(sk);
    return 0;
}