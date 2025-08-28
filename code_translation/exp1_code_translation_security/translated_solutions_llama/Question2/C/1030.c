#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

// Function to generate a SHA3-256 hash
uint64_t sha3_256Hash(const char *msg) {
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, msg, strlen(msg));
    SHA256_Final(hash, &sha256);

    uint64_t hashBig = 0;
    for (int i = 0; i < SHA256_DIGEST_LENGTH; i++) {
        hashBig = (hashBig << 8) | hash[i];
    }
    return hashBig;
}

// Function to sign a message using ECDSA with secp256k1
int signECDSAsecp256k1(const char *msg, const BIGNUM *privKey, ECDSA *ecdsa, BIGNUM *r, BIGNUM *s) {
    uint64_t msgHash = sha3_256Hash(msg);
    BIGNUM *msgHashBN = BN_new();
    BN_set_word(msgHashBN, msgHash);

    ECDSA_do_sign_ex(ecdsa, msgHashBN, NULL, r, s, privKey, NULL, NULL);
    BN_free(msgHashBN);
    return 1;
}

// Function to verify a signature using ECDSA with secp256k1
int verifyECDSAsecp256k1(const char *msg, const BIGNUM *r, const BIGNUM *s, const ECDSA *ecdsa) {
    uint64_t msgHash = sha3_256Hash(msg);
    BIGNUM *msgHashBN = BN_new();
    BN_set_word(msgHashBN, msgHash);

    int ret = ECDSA_do_verify_ex(msgHashBN, NULL, r, s, ecdsa, NULL, NULL);
    BN_free(msgHashBN);
    return ret;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Set up the secp256k1 curve
    const EC_METHOD *method = EC_METHOD_get_ecdsa();
    EC_KEY *key = EC_KEY_new(method, NULL);
    const EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256k1);
    EC_KEY_set_group(key, group);
    EC_GROUP_free(group);

    // Generate a random private key
    BIGNUM *order = BN_new();
    EC_GROUP_get_order(EC_KEY_get0_group(key), order, NULL);
    BIGNUM *privKey = BN_new();
    do {
        BN_rand_range(privKey, order);
    } while (BN_cmp(privKey, BN_value_one()) <= 0);
    EC_KEY_set_private_key(key, privKey);
    BN_free(order);

    // Get the public key
    const EC_POINT *pubKey = EC_KEY_get0_public_key(key);

    // Sign a message
    const char *msg = "Message for ECDSA signing";
    BIGNUM *r = BN_new();
    BIGNUM *s = BN_new();
    signECDSAsecp256k1(msg, EC_KEY_get0_private_key(key), key, r, s);

    // Print the results
    printf("Message: %s\n", msg);
    char *privKeyHex = BN_bn2hex(EC_KEY_get0_private_key(key));
    printf("Private key: %s\n", privKeyHex);
    OPENSSL_free(privKeyHex);
    char *rHex = BN_bn2hex(r);
    char *sHex = BN_bn2hex(s);
    printf("Signature: r=%s, s=%s\n", rHex, sHex);
    OPENSSL_free(rHex);
    OPENSSL_free(sHex);

    // Verify the signature
    int valid = verifyECDSAsecp256k1(msg, r, s, key);
    printf("Signature valid: %s\n", valid ? "yes" : "no");

    // Clean up
    BN_free(r);
    BN_free(s);
    BN_free(privKey);
    EC_KEY_free(key);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}