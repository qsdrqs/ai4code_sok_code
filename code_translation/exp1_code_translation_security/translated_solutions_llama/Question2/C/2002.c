#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a new ECDSA key pair
EC_KEY* generate_ecdsa_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to generate ECDSA key\n");
        return NULL;
    }

    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate ECDSA key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign a message using ECDSA
unsigned char* sign(EC_KEY* key, const char* message, size_t message_len, size_t* signature_len) {
    const EC_GROUP* group = EC_KEY_get0_group(key);
    ECDSA_SIG* sig = ECDSA_do_sign((const unsigned char*)message, message_len, group, EC_KEY_get0_private_key(key));
    if (!sig) {
        printf("Failed to generate signature\n");
        return NULL;
    }

    *signature_len = ECDSA_size(key);
    unsigned char* signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        printf("Memory allocation failed\n");
        ECDSA_free(sig);
        return NULL;
    }

    ECDSA_get0_signature(sig, (const unsigned char**)&signature);

    ECDSA_free(sig);
    return signature;
}

// Function to verify an ECDSA signature
int verify(EC_KEY* key, const unsigned char* signature, size_t signature_len, const char* message, size_t message_len) {
    ECDSA_SIG* sig = ECDSA_SIG_new();
    if (!sig) {
        printf("Memory allocation failed\n");
        return 0;
    }

    BIGNUM* r = BN_bin2bn(signature, 32, NULL);
    BIGNUM* s = BN_bin2bn(signature + 32, 32, NULL);
    if (!ECDSA_SIG_set0(sig, r, s)) {
        printf("Failed to set signature\n");
        BN_free(r);
        BN_free(s);
        ECDSA_SIG_free(sig);
        return 0;
    }

    int result = ECDSA_do_verify((const unsigned char*)message, message_len, sig, EC_KEY_get0_group(key), EC_KEY_get0_public_key(key));

    ECDSA_SIG_free(sig);
    return result;
}

int main() {
    // Generate a new ECDSA key pair
    EC_KEY* key = generate_ecdsa_key();
    if (!key) {
        return 1;
    }

    // Sign a message
    const char* message = "hello world";
    size_t message_len = strlen(message);
    size_t signature_len;
    unsigned char* signature = sign(key, message, message_len, &signature_len);
    if (!signature) {
        EC_KEY_free(key);
        return 1;
    }

    // Verify the signature
    const char* verification_message = "hello worfld";
    size_t verification_message_len = strlen(verification_message);
    int verification_result = verify(key, signature, signature_len, verification_message, verification_message_len);
    printf("%d\n", verification_result); // Should print 0 (verification failed)

    // Free resources
    free(signature);
    EC_KEY_free(key);

    return 0;
}