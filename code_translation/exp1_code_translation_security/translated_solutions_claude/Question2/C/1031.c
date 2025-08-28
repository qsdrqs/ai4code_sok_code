#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/sha.h>
#include <openssl/err.h>
#include <openssl/evp.h>
#include <string.h>
#include <stdlib.h>

/**
 * Sign a message using RSA-PKCS#1 v1.5 with SHA-256
 * 
 * @param msg: message to sign
 * @param msg_len: length of the message
 * @param priv_key: RSA private key
 * @param signature: output buffer for signature (must be allocated)
 * @param sig_len: pointer to store signature length
 * @return: 1 on success, 0 on failure
 */
int sign(const unsigned char* msg, size_t msg_len, RSA* priv_key, 
         unsigned char* signature, unsigned int* sig_len) {
    
    // Create SHA-256 digest
    unsigned char digest[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256_ctx;
    
    if (!SHA256_Init(&sha256_ctx)) {
        return 0;
    }
    
    if (!SHA256_Update(&sha256_ctx, msg, msg_len)) {
        return 0;
    }
    
    if (!SHA256_Final(digest, &sha256_ctx)) {
        return 0;
    }
    
    // Sign the digest using RSA-PKCS#1 v1.5
    int result = RSA_sign(NID_sha256, digest, SHA256_DIGEST_LENGTH, 
                         signature, sig_len, priv_key);
    
    return result;
}

// Helper function to load RSA private key from PEM string
RSA* load_private_key_from_pem(const char* pem_string) {
    BIO* bio = BIO_new_mem_buf(pem_string, -1);
    if (!bio) {
        return NULL;
    }
    
    RSA* rsa = PEM_read_bio_RSAPrivateKey(bio, NULL, NULL, NULL);
    BIO_free(bio);
    
    return rsa;
}

// Helper function to load RSA private key from file
RSA* load_private_key_from_file(const char* filename) {
    FILE* fp = fopen(filename, "r");
    if (!fp) {
        return NULL;
    }
    
    RSA* rsa = PEM_read_RSAPrivateKey(fp, NULL, NULL, NULL);
    fclose(fp);
    
    return rsa;
}

// Example usage
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
    
    // Example message
    const char* message = "Hello, World!";
    size_t msg_len = strlen(message);
    
    // Load private key (replace with your actual key file)
    RSA* private_key = load_private_key_from_file("private_key.pem");
    if (!private_key) {
        fprintf(stderr, "Failed to load private key\n");
        return 1;
    }
    
    // Allocate signature buffer
    int key_size = RSA_size(private_key);
    unsigned char* signature = malloc(key_size);
    unsigned int sig_len;
    
    // Sign the message
    if (sign((const unsigned char*)message, msg_len, private_key, 
             signature, &sig_len)) {
        printf("Message signed successfully\n");
        printf("Signature length: %u bytes\n", sig_len);
        
        // Print signature in hex
        printf("Signature: ");
        for (unsigned int i = 0; i < sig_len; i++) {
            printf("%02x", signature[i]);
        }
        printf("\n");
    } else {
        fprintf(stderr, "Signing failed\n");
        ERR_print_errors_fp(stderr);
    }
    
    // Cleanup
    free(signature);
    RSA_free(private_key);
    EVP_cleanup();
    ERR_free_strings();
    
    return 0;
}