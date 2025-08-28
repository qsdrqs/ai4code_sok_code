#include <stdio.h>
#include <string.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>
#include <openssl/evp.h>

// Function to convert a hex string to a byte array
void hex_to_bytes(const char *hex, unsigned char *bytes, size_t bytes_len) {
    for (size_t i = 0; i < bytes_len; i++) {
        sscanf(hex + 2*i, "%2hhx", &bytes[i]);
    }
}

// Function to sign a message
char* sign_message(const char *message, const unsigned char *private_key_hex) {
    // Hash the message
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256((unsigned char*)message, strlen(message), message_hash);

    // Create a new EC key object
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (eckey == NULL) {
        fprintf(stderr, "Failed to create new EC Key\n");
        return NULL;
    }

    // Convert the private key from hex to BIGNUM
    BIGNUM *priv_key_bn = BN_new();
    BN_bin2bn(private_key_hex, 32, priv_key_bn);

    // Set the private key
    if (EC_KEY_set_private_key(eckey, priv_key_bn) != 1) {
        fprintf(stderr, "Failed to set private key\n");
        EC_KEY_free(eckey);
        BN_free(priv_key_bn);
        return NULL;
    }

    // Sign the message hash
    unsigned int sig_len;
    unsigned char *signature = OPENSSL_malloc(ECDSA_size(eckey));
    if (ECDSA_sign(0, message_hash, SHA256_DIGEST_LENGTH, signature, &sig_len, eckey) != 1) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(eckey);
        BN_free(priv_key_bn);
        OPENSSL_free(signature);
        return NULL;
    }

    // Convert the signature to hex
    char *signature_hex = OPENSSL_malloc(sig_len * 2 + 1);
    for (unsigned int i = 0; i < sig_len; i++) {
        sprintf(signature_hex + 2*i, "%02x", signature[i]);
    }
    signature_hex[sig_len * 2] = '\0';

    // Clean up
    EC_KEY_free(eckey);
    BN_free(priv_key_bn);
    OPENSSL_free(signature);

    return signature_hex;
}

int main() {
    // Test the function
    const char *private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    unsigned char private_key[32];
    hex_to_bytes(private_key_hex, private_key, 32);

    const char *message = "Hello";
    char *signature = sign_message(message, private_key);
    if (signature != NULL) {
        printf("Signature: %s\n", signature);
        OPENSSL_free(signature);
    }

    return 0;
}