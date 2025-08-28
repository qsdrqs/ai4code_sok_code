#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * Converts a hexadecimal string to binary data.
 *
 * @param hex Input hexadecimal string (must be even length).
 * @param bin Output buffer to store binary data.
 * @param bin_len Length of the output buffer (must be hex length / 2).
 */
void hex_to_bin(const char *hex, unsigned char *bin, size_t bin_len) {
    size_t len = strlen(hex);
    if (len % 2 != 0 || bin_len != len / 2) {
        fprintf(stderr, "Invalid hex string or buffer size mismatch.\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < bin_len; i++) {
        sscanf(hex + 2 * i, "%2hhx", &bin[i]);
    }
}

/**
 * Signs a message using an ECDSA private key.
 *
 * @param message The message to sign.
 * @param private_key The private key in binary form (32 bytes).
 * @param private_key_len Length of the private key (must be 32).
 * @param signature Output buffer to store the signature.
 * @param sig_len Pointer to the size of the signature buffer (on input), and actual size on output.
 * @return 1 on success, 0 on failure.
 */
int sign_message(const char *message, const unsigned char *private_key, size_t private_key_len,
                 unsigned char *signature, size_t *sig_len) {
    if (private_key_len != 32) {
        fprintf(stderr, "Private key must be 32 bytes.\n");
        return 0;
    }

    // Step 1: Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);

    // Step 2: Create EC_KEY for secp256k1 curve
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!key) {
        fprintf(stderr, "Failed to create EC_KEY.\n");
        return 0;
    }

    // Step 3: Convert private key bytes to BIGNUM
    BIGNUM *priv = BN_bin2bn(private_key, 32, BN_new());
    if (!priv) {
        fprintf(stderr, "Failed to convert private key to BIGNUM.\n");
        EC_KEY_free(key);
        return 0;
    }

    // Step 4: Set private key in EC_KEY
    if (EC_KEY_set_private_key(key, priv) != 1) {
        fprintf(stderr, "Failed to set private key.\n");
        BN_free(priv);
        EC_KEY_free(key);
        return 0;
    }

    // Step 5: Sign the hash
    unsigned int openssl_sig_len = (unsigned int)*sig_len;
    int result = ECDSA_sign(NID_sha256, hash, SHA256_DIGEST_LENGTH, signature, &openssl_sig_len, key);

    // Step 6: Clean up
    BN_free(priv);
    EC_KEY_free(key);

    *sig_len = (size_t)openssl_sig_len;
    return result == 1;
}

/**
 * Main function to test the ECDSA signing process.
 */
int main() {
    const char *message = "Hello, world!";
    const char *private_key_hex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";

    // Convert private key from hex to binary
    unsigned char private_key[32];
    hex_to_bin(private_key_hex, private_key, sizeof(private_key));

    // Buffer to hold the signature (DER-encoded ECDSA signature max size is ~72 bytes)
    unsigned char signature[72];
    size_t sig_len = sizeof(signature);

    // Sign the message
    if (sign_message(message, private_key, sizeof(private_key), signature, &sig_len)) {
        // Print the signature in hexadecimal format
        for (size_t i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        fprintf(stderr, "Failed to sign message.\n");
        return 1;
    }

    return 0;
}