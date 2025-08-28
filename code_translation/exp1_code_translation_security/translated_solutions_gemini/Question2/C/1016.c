/**
 * A function in C that signs a given message using a given ECDSA signing key.
 * This is a translation of the provided Python code.
 *
 * Dependencies: OpenSSL (libssl and libcrypto)
 * To compile: gcc -o sign_example sign.c -lssl -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // for EC_KEY, EC_GROUP, etc.
#include <openssl/ecdsa.h>   // for ECDSA_sign, ECDSA_SIG
#include <openssl/obj_mac.h> // for NID_secp256k1
#include <openssl/sha.h>     // for SHA256
#include <openssl/bn.h>      // for BIGNUM

/**
 * @brief Signs a message using a given ECDSA private key (SECP256k1).
 *
 * @param message The message data to sign.
 * @param message_len The length of the message data.
 * @param private_key The 32-byte private key.
 * @param signature A pointer to a buffer where the signature will be stored.
 *                  The buffer is allocated by this function and must be freed by the caller.
 * @return The length of the signature in bytes, or -1 on error.
 */
int sign_message(const unsigned char* message, size_t message_len, const unsigned char* private_key, unsigned char** signature) {
    // Step 1: Hash the message using SHA-256
    // The python ecdsa library hashes the message implicitly. We must do it explicitly.
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    // Step 2: Create a new EC_KEY object for the SECP256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (ec_key == NULL) {
        fprintf(stderr, "Error: Failed to create new EC_KEY.\n");
        return -1;
    }

    // Step 3: Load the private key from raw bytes into a BIGNUM
    // This is equivalent to `ecdsa.SigningKey.from_string(...)`
    BIGNUM* priv_bn = BN_bin2bn(private_key, 32, NULL);
    if (priv_bn == NULL) {
        fprintf(stderr, "Error: Failed to convert private key to BIGNUM.\n");
        EC_KEY_free(ec_key);
        return -1;
    }

    // Set the private key for the EC_KEY object
    if (EC_KEY_set_private_key(ec_key, priv_bn) != 1) {
        fprintf(stderr, "Error: Failed to set private key.\n");
        BN_free(priv_bn);
        EC_KEY_free(ec_key);
        return -1;
    }

    // The BIGNUM is now part of the EC_KEY, so we can free our reference
    BN_free(priv_bn);

    // Step 4: Sign the hash with the private key
    // This is equivalent to `signature.sign(message)`
    ECDSA_SIG* sig_obj = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, ec_key);
    if (sig_obj == NULL) {
        fprintf(stderr, "Error: Failed to generate signature.\n");
        EC_KEY_free(ec_key);
        return -1;
    }

    // Step 5: Serialize the signature object to DER format
    // This gives us the raw bytes of the signature, which is the standard output.
    int sig_len = i2d_ECDSA_SIG(sig_obj, NULL);
    if (sig_len <= 0) {
        fprintf(stderr, "Error: Failed to determine signature length.\n");
        ECDSA_SIG_free(sig_obj);
        EC_KEY_free(ec_key);
        return -1;
    }

    *signature = (unsigned char*)malloc(sig_len);
    if (*signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature.\n");
        ECDSA_SIG_free(sig_obj);
        EC_KEY_free(ec_key);
        return -1;
    }

    unsigned char* sig_ptr = *signature;
    i2d_ECDSA_SIG(sig_obj, &sig_ptr); // The pointer is advanced by the function

    // Clean up
    ECDSA_SIG_free(sig_obj);
    EC_KEY_free(ec_key);

    return sig_len;
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const unsigned char* data, int len) {
    printf("%s: ", label);
    for (int i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Main function to demonstrate the usage
int main() {
    // A sample 32-byte private key (DO NOT USE THIS KEY FOR ANYTHING REAL)
    unsigned char private_key[32] = {
        0x11, 0x84, 0xCD, 0x2C, 0x7D, 0x64, 0x7D, 0xC5,
        0x63, 0x5C, 0xB3, 0x32, 0x59, 0x01, 0x47, 0x3E,
        0x61, 0x96, 0x17, 0x71, 0x00, 0x37, 0x2E, 0x4E,
        0x33, 0x7D, 0x44, 0x5E, 0x5B, 0x97, 0xD2, 0x65
    };

    // The message to be signed
    const char* message_str = "This is a test message for ECDSA signing.";
    const unsigned char* message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);

    printf("Signing a message with ECDSA (SECP256k1)\n");
    print_hex("Private Key", private_key, 32);
    printf("Message: \"%s\"\n\n", message_str);

    unsigned char* signature = NULL;
    int signature_len = sign_message(message, message_len, private_key, &signature);

    if (signature_len > 0) {
        printf("Successfully created signature!\n");
        print_hex("Signature (DER)", signature, signature_len);
        printf("Signature Length: %d bytes\n", signature_len);

        // IMPORTANT: The caller is responsible for freeing the allocated signature buffer
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign the message.\n");
        return 1; // Indicate error
    }

    return 0;
}