#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Structure to hold a signature and its length
typedef struct {
    unsigned char *data;
    unsigned int len;
} Signature;

Signature sign(EC_KEY *sk, const char *m) {
    Signature sig;
    
    // ecdsa library uses SHA-1 by default for NIST192p curve
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)m, strlen(m), hash);
    
    sig.data = malloc(ECDSA_size(sk));
    ECDSA_sign(0, hash, SHA_DIGEST_LENGTH, sig.data, &sig.len, sk);
    return sig;
}

void test() {
    // SigningKey.generate() defaults to NIST192p curve
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    EC_KEY_generate_key(sk);
    
    // Derive verifying key (public key only)
    EC_KEY *vk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    EC_KEY_set_public_key(vk, EC_KEY_get0_public_key(sk));
    
    Signature signature = sign(sk, "hello world");
    
    // Verify with different (mistyped) message
    const char *verify_msg = "hello worfld";
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1((const unsigned char *)verify_msg, strlen(verify_msg), hash);
    
    int result = ECDSA_verify(0, hash, SHA_DIGEST_LENGTH,
                              signature.data, signature.len, vk);
    printf("%s\n", result == 1 ? "True" : "False");
    
    free(signature.data);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
}

int main() {
    test();
    return 0;
}