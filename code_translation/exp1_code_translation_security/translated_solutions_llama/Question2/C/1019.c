#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>

// Function to generate a private key
EC_KEY* generate_private_key() {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) {
        printf("Failed to generate private key\n");
        return NULL;
    }

    if (!EC_KEY_generate_key(key)) {
        printf("Failed to generate key\n");
        EC_KEY_free(key);
        return NULL;
    }

    return key;
}

// Function to sign a string
unsigned char* sign_string(EC_KEY* private_key, const unsigned char* data, size_t data_len, size_t* signature_len) {
    const EC_GROUP* group = EC_KEY_get0_group(private_key);
    ECDSA_SIG* sig = ECDSA_do_sign(data, data_len, group);
    if (!sig) {
        printf("Failed to generate signature\n");
        return NULL;
    }

    const BIGNUM* r = ECDSA_SIG_get0_r(sig);
    const BIGNUM* s = ECDSA_SIG_get0_s(sig);
    size_t r_len = BN_num_bytes(r);
    size_t s_len = BN_num_bytes(s);

    // ECDSA signature is 2 * (field size / 8) bytes long
    *signature_len = 2 * ((EC_GROUP_get_degree(group) + 7) / 8);
    unsigned char* signature = (unsigned char*)malloc(*signature_len);
    if (!signature) {
        printf("Memory allocation failed\n");
        ECDSA_SIG_free(sig);
        return NULL;
    }

    // Convert r and s to octet strings and concatenate them
    BN_bn2binpad(r, signature, r_len);
    memset(signature + r_len, 0, *signature_len - r_len);
    BN_bn2binpad(s, signature + (*signature_len / 2), s_len);
    memset(signature + (*signature_len / 2) + s_len, 0, *signature_len / 2 - s_len);

    ECDSA_SIG_free(sig);
    return signature;
}

int main() {
    // Generate a private key
    EC_KEY* private_key = generate_private_key();
    if (!private_key) {
        return 1;
    }

    // Data to be signed
    const unsigned char* data = (const unsigned char*)"hello world";
    size_t data_len = strlen((const char*)data);

    // Sign the data
    size_t signature_len;
    unsigned char* signature = sign_string(private_key, data, data_len, &signature_len);
    if (!signature) {
        EC_KEY_free(private_key);
        return 1;
    }

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Free resources
    free(signature);
    EC_KEY_free(private_key);

    return 0;
}