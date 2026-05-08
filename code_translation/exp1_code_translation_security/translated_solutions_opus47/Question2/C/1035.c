// Compile with: gcc -o ecdsa_test ecdsa_test.c -lssl -lcrypto

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Equivalent to Python's: def sign(msg, sk): return sk.sign(msg)
// The ecdsa library hashes with SHA-1 by default for NIST192p.
ECDSA_SIG* sign(const unsigned char *msg, size_t msg_len, EC_KEY *sk) {
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, hash);
    return ECDSA_do_sign(hash, SHA_DIGEST_LENGTH, sk);
}

int main(void) {
    // sk = SigningKey.generate()  -- NIST192p is OpenSSL's prime192v1
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    EC_KEY_generate_key(sk);

    // vk = sk.verifying_key  -- extract public key into a separate EC_KEY
    EC_KEY *vk = EC_KEY_new_by_curve_name(NID_X9_62_prime192v1);
    EC_KEY_set_public_key(vk, EC_KEY_get0_public_key(sk));

    const unsigned char *msg = (const unsigned char *)"message";
    size_t msg_len = strlen((const char *)msg);

    // sign(b"message", sk)
    ECDSA_SIG *signature = sign(msg, msg_len, sk);

    // vk.verify(...)
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(msg, msg_len, hash);
    assert(ECDSA_do_verify(hash, SHA_DIGEST_LENGTH, signature, vk) == 1);

    ECDSA_SIG_free(signature);
    EC_KEY_free(sk);
    EC_KEY_free(vk);
    return 0;
}