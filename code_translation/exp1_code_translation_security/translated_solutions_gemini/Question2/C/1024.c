#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers for Elliptic Curve, ECDSA, SHA256, and Base64 encoding
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bio.h>
#include <openssl/pem.h>
#include <openssl/err.h>

/**
 * @brief Generates an ECDSA signature for a given message and private key.
 *
 * This function replicates the behavior of `Ecdsa.sign(message, privateKey)`.
 * 1. It first computes the SHA-256 hash of the input message.
 * 2. It then signs the hash using the provided EC_KEY private key.
 * 3. The raw signature (r, s values) is DER-encoded.
 * 4. The DER-encoded signature is then Base64-encoded to create a portable string.
 *
 * @param message The string message to be signed.
 * @param privateKey A pointer to an EC_KEY object containing the private key.
 * @return A dynamically allocated string containing the Base64-encoded signature.
 *         The caller is responsible for freeing this memory with free().
 *         Returns NULL on failure.
 */
char* generate_ecdsa_signed_msg(const char* message, EC_KEY* privateKey) {
    // Step 1: Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256((const unsigned char*)message, strlen(message), hash);

    // Step 2: Sign the hash with the private key
    ECDSA_SIG *signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, privateKey);
    if (signature == NULL) {
        fprintf(stderr, "Error: Failed to generate ECDSA signature.\n");
        ERR_print_errors_fp(stderr);
        return NULL;
    }

    // Step 3: DER-encode the signature
    unsigned char *der_sig = NULL;
    int der_len = i2d_ECDSA_SIG(signature, &der_sig);
    ECDSA_SIG_free(signature); // Free the signature object
    if (der_len <= 0 || der_sig == NULL) {
        fprintf(stderr, "Error: Failed to DER-encode signature.\n");
        ERR_print_errors_fp(stderr);
        return NULL;
    }

    // Step 4: Base64-encode the DER-encoded signature
    BIO *b64 = BIO_new(BIO_f_base64());
    BIO *mem = BIO_new(BIO_s_mem());
    BIO_push(b64, mem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL); // No newlines in the output

    BIO_write(b64, der_sig, der_len);
    BIO_flush(b64);

    BUF_MEM *bptr;
    BIO_get_mem_ptr(mem, &bptr);

    // Create a null-terminated string from the BIO's memory
    char *b64_text = (char *)malloc(bptr->length + 1);
    if (b64_text) {
        memcpy(b64_text, bptr->data, bptr->length);
        b64_text[bptr->length] = '\0';
    } else {
        fprintf(stderr, "Error: Malloc failed for Base64 string.\n");
    }

    // Cleanup
    OPENSSL_free(der_sig);
    BIO_free_all(b64); // This also frees the `mem` BIO

    return b64_text;
}

int main() {
    // The Python `if __name__=="__main__":` block translates to the main function in C.

    // --- Generate Keys ---
    // The Python library uses the secp256k1 curve by default.
    EC_KEY *privateKey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (privateKey == NULL) {
        fprintf(stderr, "Error: Failed to create EC_KEY object.\n");
        ERR_print_errors_fp(stderr);
        return 1;
    }

    // Generate the private and public key pair.
    if (EC_KEY_generate_key(privateKey) != 1) {
        fprintf(stderr, "Error: Failed to generate EC key pair.\n");
        ERR_print_errors_fp(stderr);
        EC_KEY_free(privateKey);
        return 1;
    }
    // The public key is derived and stored within the `privateKey` object,
    // equivalent to `publicKey = privateKey.publicKey()`

    // --- Sign Message ---
    const char* message = "My test message";
    char* signature_b64 = generate_ecdsa_signed_msg(message, privateKey);

    if (signature_b64) {
        printf("%s\n", signature_b64);
        // Free the memory allocated by the signing function
        free(signature_b64);
    }

    // --- Cleanup ---
    EC_KEY_free(privateKey);

    // Optional: Free OpenSSL error strings and algorithms
    ERR_free_strings();
    EVP_cleanup();

    return 0;
}