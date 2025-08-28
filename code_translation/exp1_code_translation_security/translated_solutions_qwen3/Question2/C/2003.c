#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>  // For NID definitions
#include <openssl/sha.h>      // For SHA-1 hashing
#include <openssl/err.h>      // For error handling
#include <openssl/evp.h>      // For OpenSSL initialization
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Error handling function
void handle_errors() {
    ERR_print_errors_fp(stderr);
    exit(EXIT_FAILURE);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate ECDSA key pair using the secp192r1 (NIST192p) curve
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    if (!sk) handle_errors();

    if (!EC_KEY_generate_key(sk)) handle_errors();

    // Message to sign
    const char *msg = "Hello world";
    size_t msg_len = strlen(msg);

    // Compute SHA-1 hash of the message
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)msg, msg_len, hash);

    // Allocate buffer for the signature
    size_t sig_len = ECDSA_size(sk);
    unsigned char *sig = OPENSSL_malloc(sig_len);
    if (!sig) handle_errors();

    // Sign the hash using ECDSA
    if (ECDSA_sign(NID_sha1, hash, SHA_DIGEST_LENGTH, sig, &sig_len, sk) <= 0) {
        handle_errors();
    }

    // Output the DER-encoded signature to stdout
    fwrite(sig, 1, sig_len, stdout);
    printf("\n");  // Match Python's print behavior with a newline

    // Clean up
    OPENSSL_free(sig);
    EC_KEY_free(sk);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}