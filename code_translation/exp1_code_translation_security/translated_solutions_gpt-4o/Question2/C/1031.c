#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * Sign a message using a private key
 */
unsigned char* sign(const unsigned char* msg, size_t msg_len, RSA* priv_key, size_t* sig_len) {
    // Create a SHA-256 digest of the message
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    if (!SHA256_Init(&sha256)) {
        fprintf(stderr, "Error initializing SHA256 context\n");
        return NULL;
    }
    if (!SHA256_Update(&sha256, msg, msg_len)) {
        fprintf(stderr, "Error updating SHA256 context\n");
        return NULL;
    }
    if (!SHA256_Final(hash, &sha256)) {
        fprintf(stderr, "Error finalizing SHA256 hash\n");
        return NULL;
    }

    // Allocate memory for the signature
    unsigned char* signature = (unsigned char*)malloc(RSA_size(priv_key));
    if (!signature) {
        fprintf(stderr, "Error allocating memory for signature\n");
        return NULL;
    }

    // Sign the hash using the private key
    if (!RSA_sign(NID_sha256, hash, SHA256_DIGEST_LENGTH, signature, (unsigned int*)sig_len, priv_key)) {
        fprintf(stderr, "Error signing message: %s\n", ERR_error_string(ERR_get_error(), NULL));
        free(signature);
        return NULL;
    }

    return signature;
}

int main() {
    // Load the private key from a PEM file
    FILE* priv_key_file = fopen("private_key.pem", "r");
    if (!priv_key_file) {
        fprintf(stderr, "Error opening private key file\n");
        return 1;
    }

    RSA* priv_key = PEM_read_RSAPrivateKey(priv_key_file, NULL, NULL, NULL);
    fclose(priv_key_file);

    if (!priv_key) {
        fprintf(stderr, "Error reading private key\n");
        return 1;
    }

    // Message to be signed
    const char* message = "This is a test message";
    size_t message_len = strlen(message);

    // Sign the message
    size_t sig_len = 0;
    unsigned char* signature = sign((const unsigned char*)message, message_len, priv_key, &sig_len);

    if (signature) {
        printf("Message signed successfully. Signature length: %zu\n", sig_len);

        // Free the signature memory
        free(signature);
    }

    // Free the private key
    RSA_free(priv_key);

    return 0;
}