#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <string.h>
#include <stdio.h>

// Function to sign a message using a given ECDSA private key
unsigned char* sign(const unsigned char* message, size_t message_len, const unsigned char* private_key, size_t* sig_len) {
    // Create a new EC_KEY object with the SECP256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object\n");
        return NULL;
    }

    // Set the private key
    BIGNUM* priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (priv_bn == NULL || !EC_KEY_set_private_key(ec_key, priv_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(ec_key);
        BN_free(priv_bn);
        return NULL;
    }
    BN_free(priv_bn);

    // Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Sign the hashed message
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(ec_key);
        return NULL;
    }

    // Convert the signature to a DER-encoded byte array
    unsigned char* der_sig = NULL;
    *sig_len = i2d_ECDSA_SIG(signature, &der_sig);

    // Free resources
    ECDSA_SIG_free(signature);
    EC_KEY_free(ec_key);

    return der_sig;
}

int main() {
    // Example usage
    const unsigned char message[] = "Hello, world!";
    const unsigned char private_key[32] = { /* 32-byte private key */ };
    size_t sig_len = 0;

    unsigned char* signed_message = sign(message, strlen((const char*)message), private_key, &sig_len);
    if (signed_message != NULL) {
        printf("Signed message (DER format):\n");
        for (size_t i = 0; i < sig_len; i++) {
            printf("%02x", signed_message[i]);
        }
        printf("\n");

        // Free the signed message
        OPENSSL_free(signed_message);
    }

    return 0;
}