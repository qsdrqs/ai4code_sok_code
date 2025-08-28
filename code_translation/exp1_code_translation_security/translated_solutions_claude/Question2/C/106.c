#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/rand.h>
#include <openssl/err.h>

/**
 * Signs a given message using a given ECDSA signing key
 * 
 * Args:
 *     message: input message
 *     message_len: length of the message
 *     key: ECDSA key used to sign the message
 *     sig_len: pointer to store the signature length
 *     
 * Return:
 *     signed message (ECDSA_SIG structure converted to DER format)
 */
unsigned char* sign(const unsigned char* message, size_t message_len, EC_KEY* key, unsigned int* sig_len) {
    // Hash the message using SHA256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);
    
    // Sign the hash
    ECDSA_SIG* sig = ECDSA_do_sign(hash, SHA256_DIGEST_LENGTH, key);
    if (!sig) {
        fprintf(stderr, "Error signing message\n");
        return NULL;
    }
    
    // Convert signature to DER format
    int der_len = i2d_ECDSA_SIG(sig, NULL);
    if (der_len <= 0) {
        ECDSA_SIG_free(sig);
        return NULL;
    }
    
    unsigned char* der_sig = malloc(der_len);
    if (!der_sig) {
        ECDSA_SIG_free(sig);
        return NULL;
    }
    
    unsigned char* der_sig_ptr = der_sig;
    i2d_ECDSA_SIG(sig, &der_sig_ptr);
    
    *sig_len = der_len;
    ECDSA_SIG_free(sig);
    return der_sig;
}

int verify_signature(const unsigned char* message, size_t message_len, 
                    const unsigned char* signature, unsigned int sig_len, EC_KEY* key) {
    // Hash the message using SHA256
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);
    
    // Convert DER signature back to ECDSA_SIG
    const unsigned char* sig_ptr = signature;
    ECDSA_SIG* sig = d2i_ECDSA_SIG(NULL, &sig_ptr, sig_len);
    if (!sig) {
        fprintf(stderr, "Error parsing signature\n");
        return 0;
    }
    
    // Verify the signature
    int result = ECDSA_do_verify(hash, SHA256_DIGEST_LENGTH, sig, key);
    ECDSA_SIG_free(sig);
    
    return result;
}

int main() {
    // Initialize OpenSSL
    ERR_load_crypto_strings();
    
    // Generate SECP256k1 key pair (equivalent to sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1))
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
    
    // Extract public key (equivalent to vk = sk.get_verifying_key())
    EC_KEY* vk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!vk) {
        fprintf(stderr, "Error creating public key\n");
        EC_KEY_free(sk);
        return 1;
    }
    
    const EC_POINT* pub_key_point = EC_KEY_get0_public_key(sk);
    if (EC_KEY_set_public_key(vk, pub_key_point) != 1) {
        fprintf(stderr, "Error setting public key\n");
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        return 1;
    }
    
    // Sign the message (equivalent to signed_message = sign(b"helloworld", sk))
    const char* message = "helloworld";
    unsigned int sig_len;
    unsigned char* signed_message = sign((const unsigned char*)message, strlen(message), sk, &sig_len);
    
    if (!signed_message) {
        fprintf(stderr, "Error signing message\n");
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        return 1;
    }
    
    // Verify the signature (equivalent to print(vk.verify(signed_message, b"helloworld")))
    int verification_result = verify_signature((const unsigned char*)message, strlen(message), 
                                             signed_message, sig_len, vk);
    
    if (verification_result == 1) {
        printf("True\n");  // Signature is valid
    } else if (verification_result == 0) {
        printf("False\n"); // Signature is invalid
    } else {
        printf("Error during verification\n");
    }
    
    // Cleanup
    free(signed_message);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
    ERR_free_strings();
    
    return 0;
}