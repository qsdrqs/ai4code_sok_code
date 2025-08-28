#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/rand.h>

// Function to generate a new ECDSA signing key
EC_KEY* generate_ecdsa_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp256r1);
    if (!key) {
        printf("Failed to generate ECDSA key\n");
        return NULL;
    }

    // Generate a new private key
    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate ECDSA private key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign input data with the provided ECDSA key
unsigned char* sign(const char* inp, EC_KEY* key, int* sig_len) {
    const char* curve_name = "secp256r1";
    const EC_GROUP* group = EC_KEY_get0_group(key);
    const EC_METHOD* method = EC_GROUP_get_method(group);
    const char* method_name = EC_METHOD_get_name(method);

    // Check if the curve is secp256r1
    if (strcmp(EC_GROUP_get_curve_name(group), OBJ_nid2ln(NID_secp256r1)) != 0) {
        printf("Unsupported curve: %s\n", curve_name);
        return NULL;
    }

    // Encode input to sign
    size_t inp_len = strlen(inp);
    unsigned char* inp_encoded = (unsigned char*)malloc(inp_len);
    memcpy(inp_encoded, inp, inp_len);

    // Create a new ECDSA signature
    ECDSA_SIG* sig = ECDSA_do_sign(inp_encoded, inp_len, key);
    if (!sig) {
        printf("Failed to create ECDSA signature\n");
        free(inp_encoded);
        return NULL;
    }

    // Get the signature length
    *sig_len = ECDSA_size(key);

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(*sig_len);

    // Get the signature
    if (!ECDSA_get0_signature(sig, (const unsigned char**)&signature)) {
        printf("Failed to get ECDSA signature\n");
        ECDSA_SIG_free(sig);
        free(inp_encoded);
        free(signature);
        return NULL;
    }

    ECDSA_SIG_free(sig);
    free(inp_encoded);

    return signature;
}

int main() {
    // Generate a new ECDSA signing key
    EC_KEY* sk = generate_ecdsa_key();
    if (!sk) {
        return 1;
    }

    // Sign the input data
    int sig_len;
    unsigned char* signature = sign("Hello world", sk, &sig_len);
    if (!signature) {
        EC_KEY_free(sk);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (int i = 0; i < sig_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    free(signature);
    EC_KEY_free(sk);

    return 0;
}