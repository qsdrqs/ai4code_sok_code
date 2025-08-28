#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>      // For Elliptic Curve functions
#include <openssl/ecdsa.h>   // For ECDSA sign/verify
#include <openssl/obj_mac.h> // For NID_secp256k1
#include <openssl/sha.h>     // For SHA256
#include <openssl/err.h>     // For error reporting

// A helper function to handle OpenSSL errors
void handle_errors() {
    ERR_print_errors_fp(stderr);
    abort();
}

int main() {
    // 1. Generate a new private/public key pair
    // In Python: privateKey = PrivateKey()
    //            publicKey = privateKey.publicKey()

    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (key == NULL) {
        fprintf(stderr, "Error creating EC_KEY object.\n");
        handle_errors();
    }

    if (EC_KEY_generate_key(key) != 1) {
        fprintf(stderr, "Error generating EC key pair.\n");
        handle_errors();
    }

    printf("Successfully generated a new EC key pair.\n");

    // 2. Define the message
    // In Python: message = "Test message"
    const char *message = "Test message";
    const size_t message_len = strlen(message);

    // 3. Hash the message (ECDSA operates on a hash of the message)
    // This step is implicit in the high-level Python library.
    unsigned char digest[SHA256_DIGEST_LENGTH];
    if (!SHA256((const unsigned char *)message, message_len, digest)) {
        fprintf(stderr, "Error creating SHA256 digest.\n");
        EC_KEY_free(key);
        return 1;
    }

    // 4. Sign the message digest
    // In Python: signature = Ecdsa.sign(message, privateKey)
    
    // The signature is stored in a DER-encoded format. We first get the max size.
    unsigned char *signature = malloc(ECDSA_size(key));
    if (signature == NULL) {
        fprintf(stderr, "Failed to allocate memory for signature.\n");
        EC_KEY_free(key);
        return 1;
    }
    unsigned int signature_len;

    if (ECDSA_sign(0, digest, SHA256_DIGEST_LENGTH, signature, &signature_len, key) != 1) {
        fprintf(stderr, "Error signing the digest.\n");
        handle_errors();
    }

    printf("Message signed successfully.\n");

    // 5. Verify the signature
    // In Python: Ecdsa.verify(message, signature, publicKey)
    int verification_status = ECDSA_verify(0, digest, SHA256_DIGEST_LENGTH, signature, signature_len, key);

    // 6. Print the result
    // In Python: print(...)
    if (verification_status == 1) {
        printf("Verification successful (True)\n");
    } else if (verification_status == 0) {
        printf("Verification failed (False)\n");
    } else {
        fprintf(stderr, "Verification error.\n");
        handle_errors();
    }

    // Cleanup
    EC_KEY_free(key);
    free(signature);
    
    // This is good practice for OpenSSL applications
    ERR_free_strings();

    return 0;
}