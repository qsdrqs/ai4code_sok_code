#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // For Elliptic Curve functions (EC_KEY, etc.)
#include <openssl/ecdsa.h>   // For ECDSA signing functions (ECDSA_sign)
#include <openssl/obj_mac.h> // For NID_secp384r1
#include <openssl/sha.h>     // For SHA256
#include <openssl/err.h>     // For error reporting

// --- Helper Functions ---

// A function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// A function to print binary data in hex format
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s", label);
    for (size_t i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}


/**
 * @brief Signs data using a given EC private key.
 *
 * This function corresponds to the Python `sign` function.
 * It first hashes the data with SHA256 and then signs the resulting digest.
 *
 * @param key The EC_KEY object containing the private key.
 * @param data The raw data to be signed.
 * @param data_len The length of the data.
 * @param sig_len_out A pointer to store the length of the output signature.
 * @return A dynamically allocated buffer containing the DER-encoded signature.
 *         The caller is responsible for freeing this buffer. Returns NULL on failure.
 */
unsigned char* sign_data(EC_KEY *key, const unsigned char *data, size_t data_len, unsigned int *sig_len_out) {
    // 1. Hash the data using SHA256
    // The Python code does this implicitly: ec.ECDSA(hashes.SHA256())
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256(data, data_len, digest)) {
        fprintf(stderr, "Failed to compute SHA256 digest.\n");
        return NULL;
    }

    // 2. Create a buffer for the signature
    // ECDSA_size returns the maximum possible size for a signature from this key
    unsigned int sig_len = ECDSA_size(key);
    unsigned char *signature = malloc(sig_len);
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        return NULL;
    }

    // 3. Sign the digest
    // This is the equivalent of `key.sign(...)`
    // ECDSA_sign returns 1 on success, 0 on failure
    if (1 != ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, signature, &sig_len, key)) {
        fprintf(stderr, "Failed to sign digest.\n");
        handle_errors(); // Print OpenSSL-specific errors
        free(signature);
        return NULL;
    }

    // The actual signature length is now in sig_len
    *sig_len_out = sig_len;
    return signature;
}

/**
 * @brief Main test function.
 *
 * This function corresponds to the Python `test` function.
 */
void test() {
    EC_KEY *private_key = NULL;
    unsigned char *signature = NULL;

    // 1. Generate a private key
    // This is equivalent to `ec.generate_private_key(ec.SECP384R1(), backend)`
    printf("Generating EC private key on curve secp384r1...\n");
    private_key = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (private_key == NULL) {
        fprintf(stderr, "Failed to create EC_KEY object.\n");
        handle_errors();
    }

    if (1 != EC_KEY_generate_key(private_key)) {
        fprintf(stderr, "Failed to generate EC key pair.\n");
        handle_errors();
    }

    // 2. Define the data to be signed
    // This is equivalent to `data = b"this is some data I'd like to sign"`
    const char *data_str = "this is some data I'd like to sign";
    const unsigned char *data = (const unsigned char *)data_str;
    size_t data_len = strlen(data_str);
    print_hex("Data to sign (ASCII): ", data, data_len);


    // 3. Sign the data
    // This is equivalent to `sign(private_key, data)`
    unsigned int sig_len;
    signature = sign_data(private_key, data, data_len, &sig_len);

    if (signature) {
        // 4. Print the signature
        // This is equivalent to `print(...)`
        print_hex("Signature (DER-encoded hex): ", signature, sig_len);
    }

    // --- Cleanup ---
    // C requires manual memory management, which Python handles automatically.
    if (signature) {
        free(signature);
    }
    if (private_key) {
        EC_KEY_free(private_key);
    }
}

int main() {
    test();
    return 0;
}