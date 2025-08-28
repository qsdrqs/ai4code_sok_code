#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

// Function to sign a message using a given ECDSA private key
unsigned char* sign_message(const char* message, const unsigned char* private_key, size_t private_key_len, size_t* signature_len) {
    // Hash the message
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(message_hash, &sha256);

    // Create a new ECDSA private key object
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    BIGNUM* private_bn = BN_bin2bn(private_key, private_key_len, NULL);
    EC_KEY_set_private_key(ec_key, private_bn);
    BN_free(private_bn);

    // Sign the message hash
    ECDSA_SIG* signature = ECDSA_do_sign(message_hash, SHA256_DIGEST_LENGTH, ec_key);

    // Get the signature in DER format
    int der_len = ECDSA_size(ec_key);
    unsigned char* der_signature = (unsigned char*)malloc(der_len);
    *signature_len = ECDSA_do_sign_to_der(signature, der_signature, &der_len);
    ECDSA_SIG_free(signature);

    // Clean up
    EC_KEY_free(ec_key);

    return der_signature;
}

int main() {
    // Test function
    const char* message = "Hello, world!";
    const unsigned char* private_key = (const unsigned char*)"\x18\xE1\x4A\x7B\x6A\x30\x7F\x42\x6A\x94\xF8\x11\x47\x00\x1E\x7C\x8E\x77\x4E\x7F\x9A\x47\xE2\xC2\x03\x5D\xB2\x9A\x20\x63\x21\x72\x5";
    size_t private_key_len = 32;
    size_t signature_len;

    unsigned char* signature = sign_message(message, private_key, private_key_len, &signature_len);

    // Print the signature
    printf("Signature: ");
    for (size_t i = 0; i < signature_len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");

    // Clean up
    free(signature);

    return 0;
}