#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>      // For Elliptic Curve functions
#include <openssl/evp.h>     // For the high-level EVP interface
#include <openssl/err.h>     // For error reporting
#include <openssl/pem.h>     // For printing the key (optional)

// A helper function to print OpenSSL errors and exit.
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

// A helper function to print a byte array in hex format.
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (len=%zu): ", label, len);
    for (size_t i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Generates an Elliptic Curve (EC) private key.
 *
 * This function replicates `ec.generate_private_key(ec.SECP384R1())`.
 *
 * @param curve_nid The OpenSSL NID for the desired curve (e.g., NID_secp384r1).
 * @return A pointer to an EVP_PKEY structure containing the new key, or NULL on failure.
 *         The caller is responsible for freeing the key with EVP_PKEY_free().
 */
EVP_PKEY* generate_private_key(int curve_nid) {
    EVP_PKEY* pkey = NULL;
    EVP_PKEY_CTX* pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) handle_errors();

    if (EVP_PKEY_keygen_init(pctx) <= 0) handle_errors();

    // Set the curve for key generation
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, curve_nid) <= 0) handle_errors();

    // Generate the key
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) handle_errors();

    EVP_PKEY_CTX_free(pctx);
    return pkey;
}


/**
 * @brief Signs a block of data using ECDSA with SHA-256.
 *
 * This function is a direct C translation of the Python `sign_string` function.
 * It takes a private key and data, and returns the DER-encoded signature.
 *
 * @param pkey The private key (EVP_PKEY*) to sign with.
 * @param data The data to be signed.
 * @param data_len The length of the data.
 * @param sig A pointer to an unsigned char* that will be allocated and filled with the signature.
 *            The caller is responsible for freeing this memory with free().
 * @param sig_len A pointer to a size_t that will be filled with the signature's length.
 * @return 0 on success, -1 on failure.
 */
int sign_string(EVP_PKEY *pkey, const unsigned char *data, size_t data_len, unsigned char **sig, size_t *sig_len) {
    EVP_MD_CTX *md_ctx = NULL;
    int ret = -1;

    // 1. Create a message digest context
    if (!(md_ctx = EVP_MD_CTX_new())) {
        fprintf(stderr, "Error: EVP_MD_CTX_new failed.\n");
        handle_errors();
    }

    // 2. Initialize the signing operation.
    //    We specify the hash algorithm (SHA256) here. The signing algorithm (ECDSA)
    //    is automatically determined from the private key type.
    if (EVP_DigestSignInit(md_ctx, NULL, EVP_sha256(), NULL, pkey) <= 0) {
        fprintf(stderr, "Error: EVP_DigestSignInit failed.\n");
        goto cleanup;
    }

    // 3. Provide the data to be signed
    if (EVP_DigestSignUpdate(md_ctx, data, data_len) <= 0) {
        fprintf(stderr, "Error: EVP_DigestSignUpdate failed.\n");
        goto cleanup;
    }

    // 4. Finalize the signature.
    //    First, determine the required buffer size.
    size_t required_len;
    if (EVP_DigestSignFinal(md_ctx, NULL, &required_len) <= 0) {
        fprintf(stderr, "Error: EVP_DigestSignFinal (for size) failed.\n");
        goto cleanup;
    }

    //    Allocate memory for the signature
    *sig = (unsigned char *)malloc(required_len);
    if (*sig == NULL) {
        fprintf(stderr, "Error: malloc failed for signature.\n");
        goto cleanup;
    }
    *sig_len = required_len;

    //    Now, get the actual signature
    if (EVP_DigestSignFinal(md_ctx, *sig, sig_len) <= 0) {
        fprintf(stderr, "Error: EVP_DigestSignFinal (for signature) failed.\n");
        free(*sig);
        *sig = NULL;
        goto cleanup;
    }

    // Success
    ret = 0;

cleanup:
    // Free the context
    if (md_ctx) {
        EVP_MD_CTX_free(md_ctx);
    }
    return ret;
}

int main() {
    EVP_PKEY *private_key = NULL;
    unsigned char *signature = NULL;
    size_t signature_len = 0;

    // The data to sign, equivalent to b"this is some data I'd like to sign"
    const unsigned char data_to_sign[] = "this is some data I'd like to sign";
    // Note: sizeof() includes the null terminator, so we subtract 1.
    const size_t data_len = sizeof(data_to_sign) - 1;

    printf("1. Generating EC private key (SECP384R1)...\n");
    // The Python code uses SECP384R1, which corresponds to NID_secp384r1 in OpenSSL.
    private_key = generate_private_key(NID_secp384r1);
    if (!private_key) {
        fprintf(stderr, "Failed to generate private key.\n");
        return 1;
    }
    printf("Key generation successful.\n\n");

    // Optional: Print the private key in PEM format to verify
    // PEM_write_PrivateKey(stdout, private_key, NULL, NULL, 0, NULL, NULL);

    printf("2. Signing data with ECDSA-SHA256...\n");
    print_hex("Data to sign", data_to_sign, data_len);

    if (sign_string(private_key, data_to_sign, data_len, &signature, &signature_len) != 0) {
        fprintf(stderr, "Failed to sign the data.\n");
        EVP_PKEY_free(private_key);
        return 1;
    }

    printf("Signing successful.\n");
    print_hex("Signature (DER encoded)", signature, signature_len);

    // Cleanup
    printf("\n3. Cleaning up resources.\n");
    free(signature);
    EVP_PKEY_free(private_key);

    return 0;
}