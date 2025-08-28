#include <stdio.h>
#include <stdlib.hh>
#include <string.h>

// OpenSSL headers for Elliptic Curve Cryptography and SHA256
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>

// A helper function to handle and print OpenSSL errors.
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// Generates a new ECDSA private key.
// Corresponds to: SigningKey.generate()
// The Python library defaults to the NIST192p curve, so we use its
// OpenSSL identifier: NID_X9_62_prime192v1
EC_KEY* generate_private_key() {
    EC_KEY *key = NULL;

    // Create a new EC_KEY object for the specified curve
    key = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        handle_errors();
    }

    // Generate the private and public key pair
    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Failed to generate EC key pair.\n");
        EC_KEY_free(key); // Clean up on failure
        handle_errors();
    }

    printf("Successfully generated a new private key.\n");
    return key;
}

// Signs a message using the provided private key.
// Corresponds to: key.sign(message.encode())
//
// @param message The string message to sign.
// @param key The EC_KEY object containing the private key.
// @param sig_len A pointer to an unsigned int where the length of the signature will be stored.
// @return A pointer to the signature bytes. The caller is responsible for freeing this memory.
unsigned char* sign_message(const char* message, EC_KEY* key, unsigned int* sig_len) {
    // 1. Create a digest (hash) of the message. ECDSA signs the hash, not the raw message.
    // We'll use SHA-256, a common standard.
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256((const unsigned char*)message, strlen(message), digest)) {
        fprintf(stderr, "Failed to compute SHA256 digest of the message.\n");
        return NULL;
    }

    // 2. Allocate memory for the signature.
    // ECDSA_size() returns the maximum possible size for a signature with this key.
    unsigned char* signature = malloc(ECDSA_size(key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return NULL;
    }

    // 3. Sign the digest with the private key.
    // The actual signature length is written to sig_len.
    if (ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, signature, sig_len, key) != 1) {
        fprintf(stderr, "Failed to sign the digest.\n");
        free(signature); // Clean up on failure
        handle_errors();
        return NULL;
    }

    return signature;
}

// Helper function to print a byte array in hexadecimal format.
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    // This corresponds to: SigningKey.generate()
    EC_KEY* private_key = generate_private_key();

    const char* message = "something";
    unsigned char* signature = NULL;
    unsigned int signature_len = 0;

    printf("Signing message: \"%s\"\n", message);

    // This corresponds to: sign("something", private_key)
    signature = sign_message(message, private_key, &signature_len);

    if (signature) {
        // This corresponds to: print(signature)
        print_hex("Signature (hex)", signature, signature_len);

        // --- Cleanup ---
        free(signature); // Free the memory allocated for the signature
    } else {
        fprintf(stderr, "Signing failed.\n");
    }

    // Free the EC_KEY object
    EC_KEY_free(private_key);

    // Clean up OpenSSL's error strings
    ERR_free_strings();

    return 0;
}