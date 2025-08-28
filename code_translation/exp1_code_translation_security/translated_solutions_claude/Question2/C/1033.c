#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/evp.h>

// Structure to hold signature data
typedef struct {
    unsigned char* data;
    size_t length;
} Signature;

// Structure to hold key data
typedef struct {
    EC_KEY* ec_key;
    int curve_nid;
} SigningKey;

// Function to create a signing key with specified curve
SigningKey* create_signing_key(int curve_nid) {
    SigningKey* key = malloc(sizeof(SigningKey));
    if (!key) return NULL;
    
    key->curve_nid = curve_nid;
    key->ec_key = EC_KEY_new_by_curve_name(curve_nid);
    if (!key->ec_key) {
        free(key);
        return NULL;
    }
    
    return key;
}

// Function to generate a random private key
int generate_key(SigningKey* signing_key) {
    if (!signing_key || !signing_key->ec_key) {
        return 0;
    }
    
    // Generate the key pair
    if (EC_KEY_generate_key(signing_key->ec_key) != 1) {
        return 0;
    }
    
    return 1;
}

// Function to get the public key (verifying key)
EC_POINT* get_verifying_key(SigningKey* signing_key) {
    if (!signing_key || !signing_key->ec_key) {
        return NULL;
    }
    
    return (EC_POINT*)EC_KEY_get0_public_key(signing_key->ec_key);
}

// Function to sign a message
Signature* sign_message(const char* message, int curve_nid) {
    // Generate a random private key
    SigningKey* signing_key = create_signing_key(curve_nid);
    if (!signing_key) {
        return NULL;
    }
    
    if (!generate_key(signing_key)) {
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    // Get the public key (equivalent to verifying_key in Python)
    EC_POINT* verifying_key = get_verifying_key(signing_key);
    if (!verifying_key) {
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    // Hash the message using SHA-256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, message, strlen(message));
    SHA256_Final(hash, &sha256);
    
    // Sign the hash
    ECDSA_SIG* ecdsa_sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, signing_key->ec_key);
    if (!ecdsa_sig) {
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    // Convert signature to DER format
    int sig_len = i2d_ECDSA_SIG(ecdsa_sig, NULL);
    if (sig_len <= 0) {
        ECDSA_SIG_free(ecdsa_sig);
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    Signature* signature = malloc(sizeof(Signature));
    if (!signature) {
        ECDSA_SIG_free(ecdsa_sig);
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    signature->data = malloc(sig_len);
    if (!signature->data) {
        free(signature);
        ECDSA_SIG_free(ecdsa_sig);
        EC_KEY_free(signing_key->ec_key);
        free(signing_key);
        return NULL;
    }
    
    unsigned char* sig_ptr = signature->data;
    signature->length = i2d_ECDSA_SIG(ecdsa_sig, &sig_ptr);
    
    // Cleanup
    ECDSA_SIG_free(ecdsa_sig);
    EC_KEY_free(signing_key->ec_key);
    free(signing_key);
    
    return signature;
}

// Function to free signature memory
void free_signature(Signature* sig) {
    if (sig) {
        if (sig->data) {
            free(sig->data);
        }
        free(sig);
    }
}

// Example usage
int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    
    const char* message = "Hello, World!";
    
    // Sign with secp256k1 curve (Bitcoin curve)
    Signature* signature = sign_message(message, NID_secp256k1);
    
    if (signature) {
        printf("Message signed successfully!\n");
        printf("Signature length: %zu bytes\n", signature->length);
        
        // Print signature in hex format
        printf("Signature (hex): ");
        for (size_t i = 0; i < signature->length; i++) {
            printf("%02x", signature->data[i]);
        }
        printf("\n");
        
        free_signature(signature);
    } else {
        printf("Failed to sign message\n");
        return 1;
    }
    
    // Cleanup OpenSSL
    EVP_cleanup();
    
    return 0;
}