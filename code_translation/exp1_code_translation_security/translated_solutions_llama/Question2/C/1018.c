#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <secp256k1.h>
#include <openssl/ripemd.h>

// Function to sign a message with an ECDSA key
unsigned char* sign(const char* msg, const unsigned char* private_key, size_t* sig_len) {
    // Initialize the secp256k1 context
    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_SIGN);

    // Set up the private key
    secp256k1_ecdsa_signature sig;
    secp256k1_pub_key pub_key;
    int ret = secp256k1_ec_seckey_verify(ctx, private_key);
    if (ret != 1) {
        printf("Invalid private key\n");
        secp256k1_context_destroy(ctx);
        return NULL;
    }

    // Sign the message
    ret = secp256k1_ecdsa_sign(ctx, &sig, (const unsigned char*)msg, strlen(msg), private_key, NULL, NULL);
    if (ret != 1) {
        printf("Failed to sign message\n");
        secp256k1_context_destroy(ctx);
        return NULL;
    }

    // Get the signature length
    size_t signature_len = 0;
    unsigned char* signature = NULL;
    secp256k1_ecdsa_signature_serialize_der(ctx, NULL, &signature_len, &sig);
    signature = malloc(signature_len);
    secp256k1_ecdsa_signature_serialize_der(ctx, signature, &signature_len, &sig);

    // Clean up
    secp256k1_context_destroy(ctx);

    // Return the signature and its length
    *sig_len = signature_len;
    return signature;
}

int main() {
    // Example usage
    const char* msg = "message";
    const unsigned char private_key[32] = { /* your private key here */ };
    size_t sig_len;
    unsigned char* sig = sign(msg, private_key, &sig_len);

    if (sig != NULL) {
        printf("Signature length: %zu\n", sig_len);
        for (size_t i = 0; i < sig_len; i++) {
            printf("%02x", sig[i]);
        }
        printf("\n");
        free(sig);
    }

    return 0;
}