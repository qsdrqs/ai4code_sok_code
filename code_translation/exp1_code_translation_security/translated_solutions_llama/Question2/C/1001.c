#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>
#include <stdio.h>
#include <stdlib.h>

// Function to generate ECDSA signature
unsigned char* signUsingECDSA(const unsigned char* privateKey, size_t privateKeyLen,
                                const unsigned char* message, size_t messageLen,
                                size_t* signatureLen) {
    // Define the curve (e.g., secp256r1)
    const char* curveName = "secp256r1";
    EC_KEY* ecKey = EC_KEY_new_by_curve_name(OBJ_create("secp256r1", "secp256r1", "EC"));
    if (!ecKey) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return NULL;
    }

    // Set the private key
    BIGNUM* privateBN = BN_bin2bn(privateKey, privateKeyLen, NULL);
    if (!privateBN) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(ecKey);
        return NULL;
    }
    EC_KEY_set_private_key(ecKey, privateBN);
    BN_free(privateBN);

    // Create a new ECDSA signature
    ECDSA_SIG* ecdsaSig = ECDSA_do_sign(message, messageLen, ecKey);
    if (!ecdsaSig) {
        fprintf(stderr, "Failed to generate ECDSA signature\n");
        EC_KEY_free(ecKey);
        return NULL;
    }

    // Get the signature
    const unsigned char* sigR;
    const unsigned char* sigS;
    size_t sigRlen;
    size_t sigSlen;
    ECDSA_SIG_get0(ecdsaSig, &sigR, &sigRlen, &sigS, &sigSlen);

    // The signature is r || s || 0x04 (for compressed format) but here we only need r and s concatenated
    *signatureLen = sigRlen + sigSlen;
    unsigned char* signature = (unsigned char*)malloc(*signatureLen);
    if (!signature) {
        fprintf(stderr, "Memory allocation failed\n");
        ECDSA_SIG_free(ecdsaSig);
        EC_KEY_free(ecKey);
        return NULL;
    }
    memcpy(signature, sigR, sigRlen);
    memcpy(signature + sigRlen, sigS, sigSlen);

    // Clean up
    ECDSA_SIG_free(ecdsaSig);
    EC_KEY_free(ecKey);

    return signature;
}

int main() {
    // Example usage
    const unsigned char privateKey[] = {/* Your private key bytes */};
    size_t privateKeyLen = sizeof(privateKey);
    const unsigned char message[] = "Hello, World!";
    size_t messageLen = strlen((const char*)message);

    size_t signatureLen;
    unsigned char* signature = signUsingECDSA(privateKey, privateKeyLen, message, messageLen, &signatureLen);
    if (signature) {
        printf("ECDSA Signature: ");
        for (size_t i = 0; i < signatureLen; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");

        // Don't forget to free the signature
        free(signature);
    }

    return 0;
}