#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // For Elliptic Curve functions (EC_KEY, etc.)
#include <openssl/ecdsa.h>   // For ECDSA signing functions (ECDSA_sign)
#include <openssl/sha.h>     // For SHA256 hashing
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/err.h>     // For error reporting

/*
 * @brief Signs a message using ECDSA with a given private key.
 *
 * This function replicates the behavior of the Python code:
 * 1. It first computes the SHA-256 hash of the input message.
 * 2. It then signs the resulting hash using the provided EC_KEY.
 * 3. The signature is returned as a DER-encoded byte array.
 *
 * @param message The raw message data to be signed.
 * @param message_len The length of the message data.
 * @param eckey A pointer to an EC_KEY object containing the private key.
 * @param sig_len A pointer to a size_t variable where the length of the
 *                output signature will be stored.
 * @return A dynamically allocated buffer containing the DER-encoded signature.
 *         The caller is responsible for freeing this buffer using free().
 *         Returns NULL on failure.
 */
unsigned char* sign_ecdsa(const unsigned char* message, size_t message_len, EC_KEY* eckey, size_t* sig_len) {
    // Step 1: Hash the message using SHA-256
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256(message, message_len, digest)) {
        fprintf(stderr, "Failed to compute SHA-256 hash.\n");
        return NULL;
    }

    // Step 2: Sign the hash with the private key
    // ECDSA_sign returns a newly allocated ECDSA_SIG object.
    ECDSA_SIG* signature = ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, eckey);
    if (signature == NULL) {
        fprintf(stderr, "Failed to sign the digest.\n");
        ERR_print_errors_fp(stderr);
        return NULL;
    }

    // Step 3: Serialize the signature into DER format
    unsigned char* der_sig = NULL;
    int der_len = i2d_ECDSA_SIG(signature, &der_sig);
    if (der_len <= 0) {
        fprintf(stderr, "Failed to encode signature to DER format.\n");
        ERR_print_errors_fp(stderr);
        ECDSA_SIG_free(signature); // Clean up the signature object
        return NULL;
    }

    // The i2d_* functions allocate memory for der_sig, which we must return.
    // The caller is responsible for freeing it.
    *sig_len = der_len;

    // Clean up the intermediate signature object
    ECDSA_SIG_free(signature);

    return der_sig;
}

// Helper function to print a byte array in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes):\n", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n\n");
}

// --- Example Usage ---
int main() {
    // 1. Create a sample message
    const char* message_str = "This is the message to be signed.";
    const unsigned char* message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);
    print_hex("Original Message", message, message_len);

    // 2. Generate an ECDSA private key for demonstration
    // In a real application, you would load this from a file or secure storage.
    // We use the secp256k1 curve, commonly used in cryptocurrencies.
    EC_KEY* eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        return 1;
    }
    if (EC_KEY_generate_key(eckey) != 1) {
        fprintf(stderr, "Failed to generate EC key pair.\n");
        EC_KEY_free(eckey);
        return 1;
    }
    printf("Successfully generated a temporary ECDSA private key.\n\n");

    // 3. Call the sign function
    size_t signature_len = 0;
    unsigned char* signature = sign_ecdsa(message, message_len, eckey, &signature_len);

    if (signature) {
        printf("Successfully created signature.\n");
        print_hex("ECDSA Signature (DER format)", signature, signature_len);

        // 4. Clean up allocated memory
        free(signature); // Free the signature buffer returned by our function
    } else {
        fprintf(stderr, "Signature creation failed.\n");
    }

    // Clean up the EC_KEY object
    EC_KEY_free(eckey);

    // Clean up OpenSSL's error strings
    ERR_free_strings();

    return 0;
}