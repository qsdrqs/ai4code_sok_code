#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// OpenSSL headers
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/err.h>

/**
 * @brief Signs a hardcoded message using ECDSA, mimicking the Python snippet.
 *
 * This function translates the Python code `key.sign(b"message")`. It takes a
 * raw private key, signs the hardcoded string "message" using the secp256k1
 * curve and a SHA-256 digest, and returns the raw signature.
 *
 * @param priv_key_bytes A pointer to the buffer containing the raw 32-byte private key.
 * @param priv_key_len The length of the private key buffer (should be 32 for secp256k1).
 * @param out_sig_len A pointer to an unsigned int where the length of the resulting
 *                    signature (64 bytes) will be stored.
 * @return A pointer to a newly allocated buffer containing the raw signature (r || s).
 *         The caller is responsible for freeing this memory using free().
 *         Returns NULL on failure.
 */
unsigned char* sign_data(const unsigned char* priv_key_bytes, size_t priv_key_len, unsigned int* out_sig_len) {
    EC_KEY *key = NULL;
    BIGNUM *priv_key_bn = NULL;
    ECDSA_SIG *signature_obj = NULL;
    unsigned char* sig_buf = NULL;

    // The Python code hardcodes the message, so we do the same here.
    const unsigned char msg[] = "message";
    unsigned char hash[SHA256_DIGEST_LENGTH];

    // 1. Create an EC_KEY object from the secp256k1 curve
    key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (key == NULL) {
        fprintf(stderr, "Error: Failed to create EC_KEY object.\n");
        goto cleanup;
    }

    // 2. Convert the raw private key bytes into a BIGNUM object
    priv_key_bn = BN_bin2bn(priv_key_bytes, priv_key_len, NULL);
    if (priv_key_bn == NULL) {
        fprintf(stderr, "Error: Failed to convert private key to BIGNUM.\n");
        goto cleanup;
    }

    // 3. Set the private key for the EC_KEY object
    if (!EC_KEY_set_private_key(key, priv_key_bn)) {
        fprintf(stderr, "Error: Failed to set private key.\n");
        goto cleanup;
    }

    // 4. Hash the message using SHA-256
    if (!SHA256(msg, strlen((const char*)msg), hash)) {
        fprintf(stderr, "Error: Failed to compute SHA-256 hash.\n");
        goto cleanup;
    }

    // 5. Sign the hash with the private key
    // This creates the signature as an ECDSA_SIG object (DER format)
    signature_obj = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    if (signature_obj == NULL) {
        fprintf(stderr, "Error: Failed to sign the hash.\n");
        goto cleanup;
    }

    // 6. Convert the signature from ECDSA_SIG object to raw (r || s) format
    const BIGNUM *r, *s;
    ECDSA_SIG_get0(signature_obj, &r, &s);

    const EC_GROUP *group = EC_KEY_get0_group(key);
    int field_size = EC_GROUP_get_degree(group) / 8; // Should be 32 for secp256k1
    if (field_size <= 0) {
        fprintf(stderr, "Error: Invalid group field size.\n");
        goto cleanup;
    }

    *out_sig_len = 2 * field_size;
    sig_buf = (unsigned char*)malloc(*out_sig_len);
    if (sig_buf == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature buffer.\n");
        goto cleanup;
    }

    // Pad r and s to the field size and concatenate them.
    if (BN_bn2binpad(r, sig_buf, field_size) < 0 ||
        BN_bn2binpad(s, sig_buf + field_size, field_size) < 0) {
        fprintf(stderr, "Error: Failed to convert signature BIGNUMs to binary.\n");
        free(sig_buf);
        sig_buf = NULL; // Ensure we return NULL on failure
        goto cleanup;
    }

cleanup:
    // Free all allocated resources
    if (signature_obj) ECDSA_SIG_free(signature_obj);
    if (priv_key_bn) BN_free(priv_key_bn);
    if (key) EC_KEY_free(key);
    // Note: sig_buf is not freed here because it is the return value.
    // It is freed on the error path within the conversion step.

    return sig_buf;
}

// Helper function to print a byte array in hexadecimal format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Main function to demonstrate the usage of the sign function
int main() {
    // A sample 32-byte private key for secp256k1.
    // In a real application, this must be kept secret and secure.
    const unsigned char private_key[32] = {
        0xC9, 0xAF, 0xA9, 0xD8, 0x45, 0xBA, 0x75, 0x16,
        0x6B, 0x5C, 0x21, 0x57, 0x66, 0x89, 0x74, 0x19,
        0xAE, 0xC3, 0x3B, 0x32, 0xA8, 0x3E, 0x95, 0xAE,
        0xF2, 0xAE, 0x9A, 0x2A, 0x2A, 0x69, 0x50, 0xF5
    };

    printf("Signing hardcoded message: \"message\"\n");
    print_hex("Using private key", private_key, sizeof(private_key));

    unsigned int sig_len = 0;
    unsigned char* signature = sign_data(private_key, sizeof(private_key), &sig_len);

    if (signature) {
        print_hex("Resulting Signature (r||s)", signature, sig_len);
        // The caller is responsible for freeing the returned signature buffer.
        free(signature);
    } else {
        fprintf(stderr, "Signing failed.\n");
        // Print any OpenSSL errors that might have occurred
        ERR_print_errors_fp(stderr);
        return 1;
    }

    return 0;
}