#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/bn.h>
#include <openssl/evp.h>

/*
 * sign message using ECDSA key
 */
unsigned char* sign_message(const char* message, const char* private_key_str, int* signature_len) {
    // Create EC_KEY structure for secp256k1 curve
    EC_KEY* ec_key = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!ec_key) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return NULL;
    }
    
    // Convert private key string to BIGNUM
    BIGNUM* private_key_bn = BN_new();
    if (!BN_bin2bn((unsigned char*)private_key_str, strlen(private_key_str), private_key_bn)) {
        fprintf(stderr, "Failed to convert private key\n");
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Set private key
    if (!EC_KEY_set_private_key(ec_key, private_key_bn)) {
        fprintf(stderr, "Failed to set private key\n");
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Generate corresponding public key
    const EC_GROUP* group = EC_KEY_get0_group(ec_key);
    EC_POINT* pub_key = EC_POINT_new(group);
    if (!EC_POINT_mul(group, pub_key, private_key_bn, NULL, NULL, NULL)) {
        fprintf(stderr, "Failed to generate public key\n");
        EC_POINT_free(pub_key);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    if (!EC_KEY_set_public_key(ec_key, pub_key)) {
        fprintf(stderr, "Failed to set public key\n");
        EC_POINT_free(pub_key);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    // Hash the message using SHA256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);
    
    // Sign the hash
    unsigned char* signature = malloc(ECDSA_size(ec_key));
    unsigned int sig_len;
    
    if (!ECDSA_sign(0, hash, SHA256_DIGEST_LENGTH, signature, &sig_len, ec_key)) {
        fprintf(stderr, "Failed to sign message\n");
        free(signature);
        EC_POINT_free(pub_key);
        BN_free(private_key_bn);
        EC_KEY_free(ec_key);
        return NULL;
    }
    
    *signature_len = sig_len;
    
    // Cleanup
    EC_POINT_free(pub_key);
    BN_free(private_key_bn);
    EC_KEY_free(ec_key);
    
    return signature;
}

void print_signature_hex(unsigned char* signature, int len) {
    printf("Signature (hex): ");
    for (int i = 0; i < len; i++) {
        printf("%02x", signature[i]);
    }
    printf("\n");
}

int main() {
    const char* message = "hello world";
    const char* private_key = "12345678901234567890123456789012";
    
    int signature_len;
    unsigned char* signature = sign_message(message, private_key, &signature_len);
    
    if (signature) {
        printf("Message: %s\n", message);
        printf("Private key: %s\n", private_key);
        printf("Signature length: %d bytes\n", signature_len);
        print_signature_hex(signature, signature_len);
        free(signature);
    } else {
        printf("Failed to sign message\n");
        return 1;
    }
    
    return 0;
}