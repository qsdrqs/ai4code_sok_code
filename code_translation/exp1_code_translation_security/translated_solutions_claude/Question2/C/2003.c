#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/err.h>

// Function to print bytes in hexadecimal format
void print_hex(const unsigned char* data, int len) {
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Function to sign input string with ECDSA key
ECDSA_SIG* sign(const char* inp, EC_KEY* key) {
    // Hash the input using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, inp, strlen(inp));
    SHA256_Final(hash, &sha256);
    
    // Sign the hash
    ECDSA_SIG* signature = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    if (!signature) {
        fprintf(stderr, "Error signing data\n");
        ERR_print_errors_fp(stderr);
        return NULL;
    }
    
    return signature;
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Generate ECDSA key pair (using secp256k1 curve, similar to Python ecdsa default)
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!sk) {
        fprintf(stderr, "Error creating EC_KEY\n");
        return 1;
    }
    
    if (EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Error generating key pair\n");
        EC_KEY_free(sk);
        return 1;
    }
    
    // Sign "Hello world"
    ECDSA_SIG* signature = sign("Hello world", sk);
    if (!signature) {
        EC_KEY_free(sk);
        return 1;
    }
    
    // Convert signature to DER format for printing (similar to Python output)
    int sig_len = i2d_ECDSA_SIG(signature, NULL);
    unsigned char* sig_der = malloc(sig_len);
    unsigned char* sig_der_ptr = sig_der;
    i2d_ECDSA_SIG(signature, &sig_der_ptr);
    
    // Print the signature in hex format
    printf("Signature: ");
    print_hex(sig_der, sig_len);
    
    // Cleanup
    free(sig_der);
    ECDSA_SIG_free(signature);
    EC_KEY_free(sk);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}