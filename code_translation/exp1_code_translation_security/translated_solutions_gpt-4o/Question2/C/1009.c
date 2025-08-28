#include <stdio.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

int main() {
    // Generate a new EC key pair
    EC_KEY *ec_key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error creating EC key\n");
        return 1;
    }

    if (EC_KEY_generate_key(ec_key) == 0) {
        fprintf(stderr, "Error generating EC key\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    // Message to be signed
    const unsigned char *message = (unsigned char *)"hehehe";
    size_t message_len = 6;

    // Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Sign the hash
    unsigned int sig_len;
    unsigned char *signature = OPENSSL_malloc(ECDSA_size(ec_key));
    if (signature == NULL) {
        fprintf(stderr, "Error allocating memory for signature\n");
        EC_KEY_free(ec_key);
        return 1;
    }

    if (ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &sig_len, ec_key) == 0) {
        fprintf(stderr, "Error signing message\n");
        OPENSSL_free(signature);
        EC_KEY_free(ec_key);
        return 1;
    }

    // Verify the signature
    int verify_status = ECDSA_verify(0, hash, SHA256_DIGEST_LENGTH, signature, sig_len, ec_key);
    if (verify_status == 1) {
        printf("Signature is valid\n");
    } else {
        printf("Signature is invalid\n");
    }

    // Clean up
    OPENSSL_free(signature);
    EC_KEY_free(ec_key);

    return 0;
}