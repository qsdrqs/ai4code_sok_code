#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/evp.h>
#include <openssl/pem.h>
#include <openssl/rand.h>
#include <openssl/sha.h>
#include <openssl/err.h>

// Structure to hold key pair
typedef struct {
    EVP_PKEY *private_key;
    EVP_PKEY *public_key;
} ecdsa_keypair_t;

// Structure to hold signature
typedef struct {
    unsigned char *data;
    size_t length;
} ecdsa_signature_t;

// Initialize OpenSSL
void init_openssl() {
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();
}

// Cleanup OpenSSL
void cleanup_openssl() {
    EVP_cleanup();
    ERR_free_strings();
}

// Print hex representation of data
void print_hex(const unsigned char *data, size_t len) {
    for (size_t i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// Generate ECDSA key pair (equivalent to NIST384p)
ecdsa_keypair_t* ecdsa_genkey() {
    ecdsa_keypair_t *keypair = malloc(sizeof(ecdsa_keypair_t));
    if (!keypair) return NULL;
    
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    if (!pctx) {
        free(keypair);
        return NULL;
    }
    
    if (EVP_PKEY_keygen_init(pctx) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        free(keypair);
        return NULL;
    }
    
    if (EVP_PKEY_CTX_set_ec_paramgen_curve_nid(pctx, NID_secp384r1) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        free(keypair);
        return NULL;
    }
    
    EVP_PKEY *pkey = NULL;
    if (EVP_PKEY_keygen(pctx, &pkey) <= 0) {
        EVP_PKEY_CTX_free(pctx);
        free(keypair);
        return NULL;
    }
    
    keypair->private_key = pkey;
    keypair->public_key = pkey;
    EVP_PKEY_up_ref(pkey); // Increment reference count
    
    EVP_PKEY_CTX_free(pctx);
    return keypair;
}

// Sign message with private key
ecdsa_signature_t* ecdsa_sign(EVP_PKEY *private_key, const unsigned char *message, size_t message_len) {
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    if (!mdctx) return NULL;
    
    if (EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, private_key) <= 0) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    if (EVP_DigestSignUpdate(mdctx, message, message_len) <= 0) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    size_t sig_len;
    if (EVP_DigestSignFinal(mdctx, NULL, &sig_len) <= 0) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    ecdsa_signature_t *signature = malloc(sizeof(ecdsa_signature_t));
    if (!signature) {
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    signature->data = malloc(sig_len);
    if (!signature->data) {
        free(signature);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    signature->length = sig_len;
    
    if (EVP_DigestSignFinal(mdctx, signature->data, &sig_len) <= 0) {
        free(signature->data);
        free(signature);
        EVP_MD_CTX_free(mdctx);
        return NULL;
    }
    
    signature->length = sig_len;
    EVP_MD_CTX_free(mdctx);
    return signature;
}

// Verify signature with public key
int ecdsa_verify_key(EVP_PKEY *public_key, ecdsa_signature_t *signature, const unsigned char *message, size_t message_len) {
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    if (!mdctx) return 0;
    
    if (EVP_DigestVerifyInit(mdctx, NULL, EVP_sha256(), NULL, public_key) <= 0) {
        EVP_MD_CTX_free(mdctx);
        return 0;
    }
    
    if (EVP_DigestVerifyUpdate(mdctx, message, message_len) <= 0) {
        EVP_MD_CTX_free(mdctx);
        return 0;
    }
    
    int result = EVP_DigestVerifyFinal(mdctx, signature->data, signature->length);
    EVP_MD_CTX_free(mdctx);
    
    return (result == 1) ? 1 : 0;
}

// Legacy function - equivalent to ecdsa_verify
int ecdsa_verify(ecdsa_signature_t *signature, const unsigned char *message, size_t message_len, EVP_PKEY *public_key) {
    return ecdsa_verify_key(public_key, signature, message, message_len);
}

// Legacy function - generates key, signs, and verifies (similar to original ecdsa_key function)
ecdsa_signature_t* ecdsa_key(const unsigned char *message, size_t message_len, EVP_PKEY *key) {
    ecdsa_keypair_t *keypair = ecdsa_genkey();
    if (!keypair) return NULL;
    
    ecdsa_signature_t *signature = ecdsa_sign(keypair->private_key, message, message_len);
    if (!signature) {
        EVP_PKEY_free(keypair->private_key);
        EVP_PKEY_free(keypair->public_key);
        free(keypair);
        return NULL;
    }
    
    int verify_result = ecdsa_verify_key(keypair->public_key, signature, message, message_len);
    printf("Verification result: %s\n", verify_result ? "True" : "False");
    
    EVP_PKEY_free(keypair->private_key);
    EVP_PKEY_free(keypair->public_key);
    free(keypair);
    
    return signature;
}

// Get private key as string (DER format)
void print_private_key(EVP_PKEY *private_key) {
    BIO *bio = BIO_new(BIO_s_mem());
    if (!bio) return;
    
    if (i2d_PrivateKey_bio(bio, private_key) > 0) {
        char *data;
        long len = BIO_get_mem_data(bio, &data);
        printf("Private key (hex): ");
        print_hex((unsigned char*)data, len);
    }
    
    BIO_free(bio);
}

// Get public key as string (DER format)
void print_public_key(EVP_PKEY *public_key) {
    BIO *bio = BIO_new(BIO_s_mem());
    if (!bio) return;
    
    if (i2d_PUBKEY_bio(bio, public_key) > 0) {
        char *data;
        long len = BIO_get_mem_data(bio, &data);
        printf("Public key (hex): ");
        print_hex((unsigned char*)data, len);
    }
    
    BIO_free(bio);
}

// Free signature
void free_signature(ecdsa_signature_t *signature) {
    if (signature) {
        if (signature->data) {
            free(signature->data);
        }
        free(signature);
    }
}

// Free keypair
void free_keypair(ecdsa_keypair_t *keypair) {
    if (keypair) {
        if (keypair->private_key) {
            EVP_PKEY_free(keypair->private_key);
        }
        if (keypair->public_key) {
            EVP_PKEY_free(keypair->public_key);
        }
        free(keypair);
    }
}

int main() {
    init_openssl();
    
    const char *message_str = "This is a test message";
    const unsigned char *message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);
    
    // Generate key pair
    ecdsa_keypair_t *keypair = ecdsa_genkey();
    if (!keypair) {
        printf("Failed to generate key pair\n");
        cleanup_openssl();
        return 1;
    }
    
    // Print keys
    print_private_key(keypair->private_key);
    print_public_key(keypair->public_key);
    
    // Sign message
    ecdsa_signature_t *signature = ecdsa_sign(keypair->private_key, message, message_len);
    if (!signature) {
        printf("Failed to sign message\n");
        free_keypair(keypair);
        cleanup_openssl();
        return 1;
    }
    
    // Print signature
    printf("Signature (hex): ");
    print_hex(signature->data, signature->length);
    
    // Verify signature
    int verify_result = ecdsa_verify_key(keypair->public_key, signature, message, message_len);
    printf("Verification result: %s\n", verify_result ? "True" : "False");
    
    // Cleanup
    free_signature(signature);
    free_keypair(keypair);
    cleanup_openssl();
    
    return 0;
}