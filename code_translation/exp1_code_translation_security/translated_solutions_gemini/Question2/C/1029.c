#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // for EC_KEY, EC_GROUP, etc
#include <openssl/ecdsa.h>   // for ECDSA_sign, ECDSA_verify
#include <openssl/obj_mac.h> // for NID_secp384r1
#include <openssl/sha.h>     // for SHA384
#include <openssl/bn.h>      // for BIGNUM

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Generates an ECDSA key pair on the NIST P-384 curve (secp384r1).
 *
 * @return A pointer to an EC_KEY object containing the new key pair.
 *         The caller is responsible for freeing this object with EC_KEY_free().
 *         Returns NULL on failure.
 */
EC_KEY* ecdsa_genkey() {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (key == NULL) {
        fprintf(stderr, "Error: Failed to create EC_KEY object.\n");
        return NULL;
    }
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Error: Failed to generate EC key pair.\n");
        EC_KEY_free(key);
        return NULL;
    }
    return key;
}

/**
 * @brief Signs a message using the provided private key.
 *
 * @param sk The EC_KEY object containing the private key.
 * @param message The message data to sign.
 * @param message_len The length of the message data.
 * @param sig_len_out A pointer to store the length of the output signature.
 * @return A pointer to the signature buffer. The caller is responsible for
 *         freeing this buffer with free(). Returns NULL on failure.
 */
unsigned char* ecdsa_sign(EC_KEY* sk, const unsigned char* message, size_t message_len, unsigned int* sig_len_out) {
    // 1. Hash the message
    unsigned char digest[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, digest);

    // 2. Create a signature
    unsigned int sig_len = ECDSA_size(sk);
    unsigned char* signature = (unsigned char*)malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature.\n");
        return NULL;
    }

    if (ECDSA_sign(0, digest, SHA384_DIGEST_LENGTH, signature, &sig_len, sk) != 1) {
        fprintf(stderr, "Error: Failed to sign message.\n");
        free(signature);
        return NULL;
    }

    *sig_len_out = sig_len;
    return signature;
}

/**
 * @brief Verifies a signature against a message using the provided public key.
 *
 * @param vk The EC_KEY object containing the public key.
 * @param signature The signature to verify.
 * @param sig_len The length of the signature.
 * @param message The original message data.
 * @param message_len The length of the message data.
 * @return 1 if the signature is valid, 0 if it is invalid, and -1 on error.
 */
int ecdsa_verify_key(EC_KEY* vk, const unsigned char* signature, size_t sig_len, const unsigned char* message, size_t message_len) {
    // 1. Hash the message
    unsigned char digest[SHA384_DIGEST_LENGTH];
    SHA384(message, message_len, digest);

    // 2. Verify the signature
    // ECDSA_verify returns 1 for a valid signature, 0 for an invalid one, and -1 on error.
    return ECDSA_verify(0, digest, SHA384_DIGEST_LENGTH, signature, sig_len, vk);
}


int main() {
    // The message to be signed
    const char* message_str = "This is a test message";
    const unsigned char* message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);

    printf("Original Message: %s\n\n", message_str);

    // 1. Generate ECDSA key pair
    EC_KEY* key_pair = ecdsa_genkey();
    if (key_pair == NULL) {
        return 1; // Exit with an error code
    }
    printf("Successfully generated key pair on NIST P-384 (secp384r1) curve.\n");

    // 2. Extract and print the private key (scalar)
    const BIGNUM* private_key_bn = EC_KEY_get0_private_key(key_pair);
    int private_key_len = BN_num_bytes(private_key_bn);
    unsigned char* private_key_bytes = (unsigned char*)malloc(private_key_len);
    BN_bn2bin(private_key_bn, private_key_bytes);
    print_hex("Private Key (sk)", private_key_bytes, private_key_len);
    free(private_key_bytes);

    // 3. Extract and print the public key (uncompressed point)
    // The Python library's to_string() for a verifying key returns the raw X and Y coordinates concatenated.
    // We will replicate that format. P-384 coordinates are 48 bytes each.
    const EC_POINT* public_key_point = EC_KEY_get0_public_key(key_pair);
    const EC_GROUP* group = EC_KEY_get0_group(key_pair);
    BIGNUM* x = BN_new();
    BIGNUM* y = BN_new();
    if (EC_POINT_get_affine_coordinates_GFp(group, public_key_point, x, y, NULL)) {
        unsigned char public_key_bytes[96]; // 48 bytes for X + 48 bytes for Y
        // Use BN_bn2bin_padded to ensure each coordinate is 48 bytes long
        BN_bn2bin_padded(public_key_bytes, 48, x);
        BN_bn2bin_padded(public_key_bytes + 48, 48, y);
        print_hex("Public Key (vk)", public_key_bytes, 96);
    }
    BN_free(x);
    BN_free(y);
    printf("\n");

    // 4. Sign the message
    unsigned int signature_len;
    unsigned char* signature = ecdsa_sign(key_pair, message, message_len, &signature_len);
    if (signature == NULL) {
        EC_KEY_free(key_pair);
        return 1;
    }
    print_hex("Signature", signature, signature_len);
    printf("\n");

    // 5. Verify the signature
    int verification_status = ecdsa_verify_key(key_pair, signature, signature_len, message, message_len);

    printf("Verifying signature...\n");
    if (verification_status == 1) {
        printf("Result: Verification successful (True)\n");
    } else if (verification_status == 0) {
        printf("Result: Verification failed (False)\n");
    } else {
        printf("Result: An error occurred during verification.\n");
    }

    // 6. Clean up allocated resources
    free(signature);
    EC_KEY_free(key_pair);

    return 0;
}