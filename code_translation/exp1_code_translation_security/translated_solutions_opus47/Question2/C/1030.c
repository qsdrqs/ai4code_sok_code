#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <openssl/bn.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/obj_mac.h>

// Compute SHA3-256 hash of a message (stores 32 raw bytes in `hash`)
void sha3_256Hash(const char* msg, unsigned char* hash) {
    EVP_MD_CTX* ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_DigestUpdate(ctx, msg, strlen(msg));
    unsigned int hashLen;
    EVP_DigestFinal_ex(ctx, hash, &hashLen);
    EVP_MD_CTX_free(ctx);
}

// Sign a message using ECDSA secp256k1 + SHA3-256
ECDSA_SIG* signECDSAsecp256k1(const char* msg, EC_KEY* ecKey) {
    unsigned char hash[32];
    sha3_256Hash(msg, hash);
    return ECDSA_do_sign(hash, 32, ecKey);
}

// Verify a signature using ECDSA secp256k1 + SHA3-256
int verifyECDSAsecp256k1(const char* msg, ECDSA_SIG* signature, EC_KEY* ecKey) {
    unsigned char hash[32];
    sha3_256Hash(msg, hash);
    return ECDSA_do_verify(hash, 32, signature, ecKey);
}

// Convert string in-place to lowercase (Python's hex() uses lowercase)
static void toLowercase(char* str) {
    for (; *str; ++str) *str = tolower((unsigned char)*str);
}

int main(void) {
    // Create EC key using secp256k1 curve
    EC_KEY* ecKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ecKey) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    // Generate random private key (and corresponding public key)
    // internally uses a cryptographically secure RNG and
    // picks a value in [1, order-1] — analogous to secrets.randbelow(order)
    if (!EC_KEY_generate_key(ecKey)) {
        fprintf(stderr, "Failed to generate key\n");
        EC_KEY_free(ecKey);
        return 1;
    }

    const BIGNUM* privKey = EC_KEY_get0_private_key(ecKey);

    // ECDSA sign message (using the curve secp256k1 + SHA3-256)
    const char* msg = "Message for ECDSA signing";
    ECDSA_SIG* signature = signECDSAsecp256k1(msg, ecKey);
    if (!signature) {
        fprintf(stderr, "Failed to sign\n");
        EC_KEY_free(ecKey);
        return 1;
    }

    // Extract r and s from the signature
    const BIGNUM* r;
    const BIGNUM* s;
    ECDSA_SIG_get0(signature, &r, &s);

    // Convert BIGNUMs to hex strings
    char* privKeyHex = BN_bn2hex(privKey);
    char* rHex = BN_bn2hex(r);
    char* sHex = BN_bn2hex(s);

    toLowercase(privKeyHex);
    toLowercase(rHex);
    toLowercase(sHex);

    printf("Message: %s\n", msg);
    printf("Private key: 0x%s\n", privKeyHex);
    printf("Signature: r=0x%s, s=0x%s\n", rHex, sHex);

    // Cleanup
    OPENSSL_free(privKeyHex);
    OPENSSL_free(rHex);
    OPENSSL_free(sHex);
    ECDSA_SIG_free(signature);
    EC_KEY_free(ecKey);

    return 0;
}