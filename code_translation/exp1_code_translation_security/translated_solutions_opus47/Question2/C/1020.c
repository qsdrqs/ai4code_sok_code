#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>
#include <openssl/err.h>

// Sign a message and verify the signature
// Returns 1 if verification succeeds, 0 otherwise
int sign_message(EC_KEY *sk, EC_KEY *vk, const unsigned char *message, size_t message_len) {
    // Hash the message (python-ecdsa uses SHA-1 by default)
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, hash);
    
    // Allocate buffer for signature
    unsigned int sig_len = ECDSA_size(sk);
    unsigned char *sig = malloc(sig_len);
    if (sig == NULL) {
        return 0;
    }
    
    // sk.sign(message)
    if (!ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, sig, &sig_len, sk)) {
        free(sig);
        return 0;
    }
    
    // vk.verify(sig, message)
    int verify_status = ECDSA_verify(0, hash, SHA_DIGEST_LENGTH, sig, sig_len, vk);
    
    free(sig);
    return verify_status == 1;
}

int main(void) {
    // sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (sk == NULL || !EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate EC key\n");
        if (sk) EC_KEY_free(sk);
        return 1;
    }
    
    // vk = sk.get_verifying_key()
    EC_KEY *vk = EC_KEY_new_by_curve_name(NID_secp256k1);
    EC_KEY_set_public_key(vk, EC_KEY_get0_public_key(sk));
    
    // print(sign_message(sk, ".."))
    const char *message = "..";
    int result = sign_message(sk, vk, (const unsigned char *)message, strlen(message));
    printf("%s\n", result ? "True" : "False");
    
    // Cleanup
    EC_KEY_free(sk);
    EC_KEY_free(vk);
    
    return 0;
}