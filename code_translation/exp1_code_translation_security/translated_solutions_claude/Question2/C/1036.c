#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/bn.h>

// Function to convert hex string to bytes
int hex_to_bytes(const char* hex_string, unsigned char* bytes, int max_len) {
    int len = strlen(hex_string);
    if (len % 2 != 0 || len / 2 > max_len) {
        return -1;
    }
    
    for (int i = 0; i < len; i += 2) {
        sscanf(hex_string + i, "%2hhx", &bytes[i / 2]);
    }
    
    return len / 2;
}

// Function to convert bytes to hex string
void bytes_to_hex(const unsigned char* bytes, int len, char* hex_string) {
    for (int i = 0; i < len; i++) {
        sprintf(hex_string + i * 2, "%02x", bytes[i]);
    }
    hex_string[len * 2] = '\0';
}

char* sign_message(const char* message, const unsigned char* private_key, int private_key_len) {
    // Hash the message using SHA256
    unsigned char message_hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(message_hash, &sha256);
    
    // Create EC_KEY structure for SECP256k1
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return NULL;
    }
    
    // Convert private key bytes to BIGNUM
    BIGNUM* private_key_bn = BN_bin2bn(private_key, private_key_len, NULL);
    if (!private_key_bn) {
        fprintf(stderr, "Failed to convert private key to BIGNUM\n");
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Set the private key
    if (EC_KEY_set_private_key(ec_key, private_key_bn) != 1) {
        fprintf(stderr, "Failed to set private key\n");
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Generate the corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* public_key_point = EC_POINT_new(group);
    if (EC_POINT_mul(group, public_key_point, private_key_bn, NULL, NULL, NULL) != 1) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(public_key_point);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    if (EC_KEY_set_public_key(ec_key, public_key_point) != 1) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(public_key_point);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Sign the message hash
    ECDSA_SIG* signature = ECDSA_do_sign(message_hash, SHA256_DIGEST_LENGTH, ec_key);
    if (!signature) {
        fprintf(stderr, "Failed to sign message\n");
        EC_POINT_free(public_key_point);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Convert signature to DER format
    int der_len = i2d_ECDSA_SIG(signature, NULL);
    if (der_len <= 0) {
        fprintf(stderr, "Failed to get DER length\n");
        ECDSA_SIG_free(signature);
        EC_POINT_free(public_key_point);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    unsigned char* der_signature = malloc(der_len);
    unsigned char* der_ptr = der_signature;
    
    if (i2d_ECDSA_SIG(signature, &der_ptr) != der_len) {
        fprintf(stderr, "Failed to convert signature to DER\n");
        free(der_signature);
        ECDSA_SIG_free(signature);
        EC_POINT_free(public_key_point);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Convert DER signature to hex string
    char* hex_signature = malloc(der_len * 2 + 1);
    bytes_to_hex(der_signature, der_len, hex_signature);
    
    // Cleanup
    free(der_signature);
    ECDSA_SIG_free(signature);
    EC_POINT_free(public_key_point);
    BN_free(private_key_bn);
    EC_KEY_free(ec_key);
    
    return hex_signature;
}

int main() {
    // Test the function
    const char* private_key_hex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
    const char* message = "Hello";
    
    // Convert hex private key to bytes
    unsigned char private_key[32];
    int private_key_len = hex_to_bytes(private_key_hex, private_key, sizeof(private_key));
    
    if (private_key_len != 32) {
        fprintf(stderr, "Invalid private key length\n");
        return 1;
    }
    
    // Sign the message
    char* signature = sign_message(message, private_key, private_key_len);
    
    if (signature) {
        printf("%s\n", signature);
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign message\n");
        return 1;
    }
    
    return 0;
}