#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to generate a signing key
EC_KEY* generate_signing_key(void) {
    EC_KEY* key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (!key) {
        fprintf(stderr, "Failed to generate key\n");
        exit(1);
    }

    if (!EC_KEY_generate_key(key)) {
        fprintf(stderr, "Failed to generate key\n");
        EC_KEY_free(key);
        exit(1);
    }

    return key;
}

// Function to sign a message
unsigned char* sign_message(EC_KEY* key, const unsigned char* message, size_t message_len, size_t* signature_len) {
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, key);
    if (!sig) {
        fprintf(stderr, "Failed to sign message\n");
        exit(1);
    }

    const unsigned char* sig_bytes = ECDSA_SIG_get0_r(sig);
    size_t r_len = BN_num_bytes(ECDSA_SIG_get0_r(sig));
    const unsigned char* s_bytes = ECDSA_SIG_get0_s(sig);
    size_t s_len = BN_num_bytes(ECDSA_SIG_get0_s(sig));

    // Calculate total signature length
    *signature_len = r_len + s_len + 1; // +1 for the 0x04 prefix

    unsigned char* signature = malloc(*signature_len);
    if (!signature) {
        fprintf(stderr, "Memory allocation failed\n");
        ECDSA_SIG_free(sig);
        exit(1);
    }

    // Copy r and s into the signature buffer
    signature[0] = 0x04; // Prefix
    memcpy(signature + 1, sig_bytes, r_len);
    memcpy(signature + 1 + r_len, s_bytes, s_len);

    ECDSA_SIG_free(sig);

    return signature;
}

// Function to verify a signature
int verify_signature(const EC_KEY* key, const unsigned char* signature, size_t signature_len, const unsigned char* message, size_t message_len) {
    ECDSA_SIG* sig = ECDSA_SIG_new();
    if (!sig) {
        fprintf(stderr, "Failed to create ECDSA_SIG object\n");
        return 0;
    }

    BIGNUM* r = BN_bin2bn(signature + 1, signature_len / 2, NULL);
    BIGNUM* s = BN_bin2bn(signature + 1 + signature_len / 2, signature_len / 2, NULL);

    if (!ECDSA_SIG_set0_r(s, r)) {
        fprintf(stderr, "Failed to set r and s in ECDSA_SIG\n");
        BN_free(r);
        BN_free(s);
        ECDSA_SIG_free(sig);
        return 0;
    }

    int result = ECDSA_do_verify(message, message_len, sig, key);

    BN_free(r);
    BN_free(s);
    ECDSA_SIG_free(sig);

    return result;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();

    // Generate a signing key
    EC_KEY* sk = generate_signing_key();

    // Get the verifying key
    const EC_KEY* vk = sk;

    // Sign a message
    const unsigned char* message = (const unsigned char*)"testing";
    size_t message_len = strlen((const char*)message);
    size_t signature_len;
    unsigned char* signature = sign_message(sk, message, message_len, &signature_len);

    // Verify the signature
    int verification_result = verify_signature(vk, signature, signature_len, message, message_len);

    printf("%d\n", verification_result);

    // Clean up
    free(signature);
    EC_KEY_free(sk);

    // Clean up OpenSSL
    EVP_cleanup();

    return 0;
}