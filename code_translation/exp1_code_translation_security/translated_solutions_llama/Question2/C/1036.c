#include <stdio.h>
#include <string.h>
#include <openssl/ecdsa.h>
#include <openssl/sha.h>
#include <openssl/obj_mac.h>

// Function to sign a message with a private key
char* sign_message(const char* message, const unsigned char* private_key, size_t private_key_len) {
    // Hash the message
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(message_hash, &sha256);

    // Create an ECDSA context
    EC_KEY* ecdsa_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ecdsa_key) {
        return NULL;
    }

    // Set the private key
    BIGNUM* private_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!private_bn) {
        EC_KEY_free(ecdsa_key);
        return NULL;
    }
    EC_KEY_set_private_key(ecdsa_key, private_bn);
    BN_free(private_bn);

    // Sign the message
    ECDSA_SIG* signature = ECDSA_do_sign(message_hash, SHA256_DIGEST_LENGTH, ecdsa_key);
    if (!signature) {
        EC_KEY_free(ecdsa_key);
        return NULL;
    }

    // Convert the signature to DER format
    int der_len = ECDSA_size(ecdsa_key);
    unsigned char* der_signature = malloc(der_len);
    if (!der_signature) {
        ECDSA_free(signature);
        EC_KEY_free(ecdsa_key);
        return NULL;
    }
    unsigned int der_signature_len;
    ECDSA_sign_asn1(ECDSA_SIG_get0_r(signature), ECDSA_SIG_get0_s(signature), &der_signature_len, der_signature, der_len);

    // Convert the DER signature to hexadecimal
    char* hex_signature = malloc((der_signature_len * 2) + 1);
    if (!hex_signature) {
        free(der_signature);
        ECDSA_free(signature);
        EC_KEY_free(ecdsa_key);
        return NULL;
    }
    for (int i = 0; i < der_signature_len; i++) {
        sprintf(hex_signature + (i * 2), "%02x", der_signature[i]);
    }
    hex_signature[der_signature_len * 2] = '\0';

    // Clean up
    free(der_signature);
    ECDSA_free(signature);
    EC_KEY_free(ecdsa_key);

    return hex_signature;
}

int main() {
    // Test the function
    const char* private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    size_t private_key_len = strlen(private_key_hex) / 2;
    unsigned char private_key[private_key_len];
    for (int i = 0; i < private_key_len; i++) {
        sscanf(private_key_hex + (i * 2), "%2hhx", &private_key[i]);
    }

    const char* message = "Hello";
    char* signature = sign_message(message, private_key, private_key_len);
    if (signature) {
        printf("%s\n", signature);
        free(signature);
    } else {
        printf("Failed to sign message\n");
    }

    return 0;
}