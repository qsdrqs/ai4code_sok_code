/**
 * @file sign_message.c
 * @brief A C implementation for signing a message using ECDSA with a SECP256k1 key.
 *
 * This program is a translation of the provided Python script. It uses the OpenSSL
 * library to perform the cryptographic operations.
 *
 * -- Dependencies --
 * This code requires the OpenSSL library to be installed.
 * On Debian/Ubuntu: sudo apt-get install libssl-dev
 * On RedHat/CentOS: sudo yum install openssl-devel
 * On macOS (with Homebrew): brew install openssl
 *
 * -- Compilation --
 * gcc -o sign_message sign_message.c -lssl -lcrypto
 *
 * On macOS with Homebrew, you might need to specify the path:
 * gcc -o sign_message sign_message.c -I/usr/local/opt/openssl/include -L/usr/local/opt/openssl/lib -lssl -lcrypto
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// OpenSSL headers
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <openssl/bn.h>

/**
 * @brief Helper function to print data in hexadecimal format.
 * @param label A description for the data being printed.
 * @param data A pointer to the data buffer.
 * @param len The length of the data.
 */
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Signs a message using a given private key with ECDSA (SECP256k1).
 *
 * This function directly translates the Python `ecdsa.SigningKey.sign()` behavior,
 * which signs the raw message bytes directly.
 *
 * NOTE: In most real-world applications, it is standard practice to sign the
 * cryptographic hash (e.g., SHA-256) of the message, not the raw message itself.
 * This implementation is a literal translation and thus signs the raw message.
 *
 * @param message The message to be signed.
 * @param message_len The length of the message.
 * @param private_key_bytes A 32-byte buffer containing the raw private key.
 * @param signature_len A pointer to a size_t variable where the length of the
 *                      resulting signature will be stored.
 * @return A dynamically allocated buffer containing the DER-encoded signature.
 *         The caller is responsible for freeing this memory with `free()`.
 *         Returns NULL on failure.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len,
                            const unsigned char* private_key_bytes,
                            size_t* signature_len) {
    EC_KEY *eckey = NULL;
    BIGNUM *priv_key_bn = NULL;
    unsigned char *signature = NULL;
    unsigned char *result = NULL;
    unsigned int sig_len_int;

    // 1. Create a new EC_KEY object for the SECP256k1 curve.
    eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Error: Failed to create new EC_KEY object.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 2. Convert the raw private key bytes into a BIGNUM object.
    priv_key_bn = BN_bin2bn(private_key_bytes, 32, NULL);
    if (priv_key_bn == NULL) {
        fprintf(stderr, "Error: Failed to convert private key to BIGNUM.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 3. Set the private key for the EC_KEY object.
    if (EC_KEY_set_private_key(eckey, priv_key_bn) != 1) {
        fprintf(stderr, "Error: Failed to set private key.\n");
        ERR_print_errors_fp(stderr);
        goto cleanup;
    }

    // 4. (Optional but recommended) Generate the public key from the private key.
    // This step also validates the private key.
    const EC_GROUP *group = EC_KEY_get0_group(eckey);
    EC_POINT *pub_key_point = EC_POINT_new(group);
    if (pub_key_point == NULL || EC_POINT_mul(group, pub_key_point, priv_key_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Error: Failed to derive public key.\n");
        ERR_print_errors_fp(stderr);
        if (pub_key_point) EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    if (EC_KEY_set_public_key(eckey, pub_key_point) != 1) {
        fprintf(stderr, "Error: Failed to set public key.\n");
        ERR_print_errors_fp(stderr);
        EC_POINT_free(pub_key_point);
        goto cleanup;
    }
    EC_POINT_free(pub_key_point);

    // 5. Allocate memory for the signature.
    // ECDSA_size() returns the maximum possible size for a DER-encoded signature.
    signature = (unsigned char*)malloc(ECDSA_size(eckey));
    if (signature == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory for signature.\n");
        goto cleanup;
    }

    // 6. Sign the message.
    // We pass the raw message directly, as done in the Python example.
    if (ECDSA_sign(0, message, message_len, signature, &sig_len_int, eckey) != 1) {
        fprintf(stderr, "Error: Failed to sign the message.\n");
        ERR_print_errors_fp(stderr);
        free(signature); // Free the allocated buffer on failure
        goto cleanup;
    }

    // Success. Set the output length and the result pointer.
    *signature_len = (size_t)sig_len_int;
    result = signature;
    signature = NULL; // Prevent the successful signature from being freed in cleanup

cleanup:
    // Free all allocated OpenSSL objects and memory.
    if (eckey) EC_KEY_free(eckey);
    if (priv_key_bn) BN_free(priv_key_bn);
    // If signature is not NULL here, it means an error occurred after its allocation.
    if (signature) free(signature);

    return result;
}

int main() {
    // Input data from the Python example
    const char* message_str = "hello";
    // A 32-byte private key
    const unsigned char private_key[32] = "qwertyuiopasdfghjklzxcvbnmqwerty";

    size_t signature_len = 0;

    printf("Signing message...\n");
    print_hex("Message", (const unsigned char*)message_str, strlen(message_str));
    print_hex("Private Key", private_key, 32);
    printf("\n");

    // Call the signing function
    unsigned char* signature = sign_message(
        (const unsigned char*)message_str,
        strlen(message_str),
        private_key,
        &signature_len
    );

    if (signature) {
        printf("Message signed successfully!\n");
        print_hex("Signature (DER encoded)", signature, signature_len);

        // Clean up the returned signature buffer
        free(signature);
    } else {
        printf("Failed to sign the message.\n");
        return 1; // Indicate error
    }

    return 0;
}