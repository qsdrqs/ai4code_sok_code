#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>

// Function to generate a new ECDSA signing key
EC_KEY* generate_sk(int curve) {
    EC_KEY* sk = EC_KEY_new_by_curve_name(curve);
    if (!sk) {
        fprintf(stderr, "Failed to generate ECDSA key\n");
        exit(1);
    }

    // Generate a new private key
    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate ECDSA private key\n");
        EC_KEY_free(sk);
        exit(1);
    }

    return sk;
}

// Function to sign a message with the provided signing key
unsigned char* sign(EC_KEY* sk, const unsigned char* message, size_t message_len, size_t* sig_len) {
    const EC_GROUP* group = EC_KEY_get0_group(sk);
    ECDSA_SIG* sig = ECDSA_do_sign(message, message_len, group, EC_KEY_get0_private_key(sk), NULL);
    if (!sig) {
        fprintf(stderr, "Failed to generate ECDSA signature\n");
        exit(1);
    }

    // Get the signature length
    *sig_len = ECDSA_size(sk);

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(*sig_len);
    if (!signature) {
        fprintf(stderr, "Memory allocation failed\n");
        ECDSA_free(sig);
        exit(1);
    }

    // Serialize the signature
    if (!ECDSA_SIG_to_bytes(signature, sig, *sig_len)) {
        fprintf(stderr, "Failed to serialize ECDSA signature\n");
        free(signature);
        ECDSA_free(sig);
        exit(1);
    }

    ECDSA_free(sig);

    return signature;
}

// Function to verify a signature with the provided verifying key and message
int verify(EC_KEY* vk, const unsigned char* sig, size_t sig_len, const unsigned char* message, size_t message_len) {
    ECDSA_SIG* ecdsa_sig = ECDSA_SIG_from_bytes(sig, sig_len);
    if (!ecdsa_sig) {
        fprintf(stderr, "Failed to parse ECDSA signature\n");
        return 0;
    }

    int result = ECDSA_do_verify(message, message_len, ecdsa_sig, EC_KEY_get0_public_key(vk), NULL);
    ECDSA_SIG_free(ecdsa_sig);

    return result;
}

int main() {
    // Define the curve (NIST384p)
    int curve = NID_secp384r1;

    // Generate a new signing key
    EC_KEY* sk = generate_sk(curve);

    // Get the verifying key
    EC_KEY* vk = EC_KEY_dup(sk);
    if (!vk) {
        fprintf(stderr, "Failed to duplicate ECDSA key\n");
        EC_KEY_free(sk);
        exit(1);
    }

    // Set the public key for verification
    if (!EC_KEY_set_public_key(vk, EC_KEY_get0_public_key(sk))) {
        fprintf(stderr, "Failed to set ECDSA public key\n");
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        exit(1);
    }

    // Define the message to sign
    const char* message_str = "hehehe";
    size_t message_len = strlen(message_str);
    unsigned char* message = (unsigned char*)message_str;

    // Sign the message
    size_t sig_len;
    unsigned char* sig = sign(sk, message, message_len, &sig_len);

    // Verify the signature
    int result = verify(vk, sig, sig_len, message, message_len);

    // Print the verification result
    printf("%d\n", result);

    // Clean up
    free(sig);
    EC_KEY_free(sk);
    EC_KEY_free(vk);

    return 0;
}