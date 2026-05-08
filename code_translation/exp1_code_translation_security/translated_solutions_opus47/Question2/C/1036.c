/*
 * Compile with: gcc sign.c -o sign -lssl -lcrypto
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/sha.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/bn.h>
#include <openssl/obj_mac.h>

/* Helper: Convert hex string to bytes (like binascii.unhexlify) */
int hex_to_bytes(const char *hex, unsigned char *bytes, int max_len) {
    int len = strlen(hex) / 2;
    if (len > max_len) return -1;
    for (int i = 0; i < len; i++) {
        unsigned int byte;
        if (sscanf(hex + 2 * i, "%2x", &byte) != 1) return -1;
        bytes[i] = (unsigned char)byte;
    }
    return len;
}

/* Helper: Convert bytes to hex string (like binascii.hexlify) */
void bytes_to_hex(const unsigned char *bytes, int len, char *hex) {
    for (int i = 0; i < len; i++) {
        sprintf(hex + 2 * i, "%02x", bytes[i]);
    }
    hex[2 * len] = '\0';
}

char *sign_message(const char *message, const unsigned char *private_key, int key_len) {
    /* Hash the message using SHA-256 */
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char *)message, strlen(message), message_hash);

    /* Create EC_KEY with SECP256k1 curve */
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC key\n");
        return NULL;
    }

    /* Load private key bytes into BIGNUM and attach to EC_KEY */
    BIGNUM *priv_bn = BN_bin2bn(private_key, key_len, NULL);
    if (!priv_bn) {
        EC_KEY_free(ec_key);
        return NULL;
    }
    if (!EC_KEY_set_private_key(ec_key, priv_bn)) {
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    /* Sign the message digest with ECDSA */
    ECDSA_SIG *sig = ECDSA_do_sign(message_hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!sig) {
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    /* DER-encode the signature (like sigencode=ecdsa.util.sigencode_der) */
    unsigned char *der = NULL;
    int der_len = i2d_ECDSA_SIG(sig, &der);
    if (der_len <= 0) {
        ECDSA_SIG_free(sig);
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }

    /* Allocate hex output (2 hex chars per byte + null terminator) */
    char *hex_sig = malloc(2 * der_len + 1);
    if (!hex_sig) {
        OPENSSL_free(der);
        ECDSA_SIG_free(sig);
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    bytes_to_hex(der, der_len, hex_sig);

    /* Cleanup */
    OPENSSL_free(der);
    ECDSA_SIG_free(sig);
    BN_free(priv_bn);
    EC_KEY_free(ec_key);

    return hex_sig;
}

int main(void) {
    /* Test the function */
    const char *private_key_hex =
        "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    unsigned char private_key[32];
    int key_len = hex_to_bytes(private_key_hex, private_key, sizeof(private_key));

    if (key_len != 32) {
        fprintf(stderr, "Invalid private key\n");
        return 1;
    }

    const char *message = "Hello";
    char *signature = sign_message(message, private_key, key_len);

    if (signature) {
        printf("%s\n", signature);
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign message\n");
        return 1;
    }

    return 0;
}