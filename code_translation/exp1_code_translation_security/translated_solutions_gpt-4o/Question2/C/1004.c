#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

void sign_message(const unsigned char *message, const unsigned char *private_key, unsigned char **signature, unsigned int *sig_len) {
    // Create a new EC_KEY object
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Failed to create new EC Key\n");
        return;
    }

    // Set the private key
    BIGNUM *priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (EC_KEY_set_private_key(eckey, priv_bn) != 1) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return;
    }

    // Hash the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, strlen((const char *)message), hash);

    // Sign the message
    *signature = OPENSSL_malloc(ECDSA_size(eckey));
    if (*signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature\n");
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, *signature, sig_len, eckey) != 1) {
        fprintf(stderr, "Failed to sign message\n");
        OPENSSL_free(*signature);
        EC_KEY_free(eckey);
        BN_free(priv_bn);
        return;
    }

    // Clean up
    EC_KEY_free(eckey);
    BN_free(priv_bn);
}

int main() {
    const char *message = "hello";
    const unsigned char private_key[32] = "qwertyuiopasdfghjklzxcvbnmqwerty";
    unsigned char *signature = NULL;
    unsigned int sig_len = 0;

    sign_message((const unsigned char *)message, private_key, &signature, &sig_len);

    if (signature != NULL) {
        printf("Signature: ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        OPENSSL_free(signature);
    }

    return 0;
}