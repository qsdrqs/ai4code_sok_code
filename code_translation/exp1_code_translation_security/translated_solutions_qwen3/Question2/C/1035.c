#include <openssl/ec.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <assert.h>
#include <string.h>
#include <openssl/err.h>

// Function to sign a message using ECDSA
unsigned char* sign(const unsigned char* msg, size_t msg_len, EC_KEY* sk, size_t* sig_len) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, digest);

    *sig_len = ECDSA_size(sk);
    unsigned char* sig = OPENSSL_malloc(*sig_len);
    if (!sig) {
        return NULL;
    }

    unsigned int temp_len;
    if (ECDSA_sign(0, digest, SHA_DIGEST_LENGTH, sig, &temp_len, sk) != 1) {
        OPENSSL_free(sig);
        return NULL;
    }

    *sig_len = temp_len;
    return sig;
}

// Function to verify a signature using ECDSA
int verify(const unsigned char* sig, size_t sig_len, const unsigned char* msg, size_t msg_len, EC_KEY* vk) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, digest);
    return ECDSA_verify(0, digest, SHA_DIGEST_LENGTH, sig, sig_len, vk);
}

int main() {
    // Initialize OpenSSL
    OpenSSL_add_all_algorithms();
    ERR_load_crypto_strings();

    // Generate ECDSA private key using NIST P-192 curve
    EC_KEY* sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    assert(sk != NULL && "Failed to create EC_KEY");

    if (!EC_KEY_generate_key(sk)) {
        EC_KEY_free(sk);
        assert(0 && "Failed to generate ECDSA key pair");
    }

    // Extract the public key (verifying key)
    EC_KEY* vk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    assert(vk != NULL && "Failed to create verifying EC_KEY");

    const EC_POINT* pub_key = EC_KEY_get0_public_key(sk);
    assert(pub_key != NULL && "Failed to get public key");

    if (!EC_KEY_set_public_key(vk, pub_key)) {
        EC_KEY_free(sk);
        EC_KEY_free(vk);
        assert(0 && "Failed to set public key");
    }

    // Message to sign
    const char* message = "message";
    size_t message_len = strlen(message);

    // Sign the message
    size_t sig_len;
    unsigned char* signature = sign((const unsigned char*)message, message_len, sk, &sig_len);
    assert(signature != NULL && "Failed to sign message");

    // Verify the signature
    int verified = verify(signature, sig_len, (const unsigned char*)message, message_len, vk);
    assert(verified == 1 && "Signature verification failed");

    // Cleanup
    OPENSSL_free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
    EVP_cleanup();
    ERR_free_strings();

    return 0;
}