// Compile with: gcc -o ecdsa_example ecdsa_example.c -lssl -lcrypto

#include <stdio.h>
#include <string.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/sha.h>

// Equivalent of Python's sign function.
// The Python ecdsa library's sign() hashes the message with SHA-1 (default)
// and signs the resulting digest.
ECDSA_SIG *sign(EC_KEY *sk, const unsigned char *message, size_t message_len) {
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, digest);
    return ECDSA_do_sign(digest, SHA_DIGEST_LENGTH, sk);
}

int main(void) {
    const unsigned char *message = (const unsigned char *)"hehehe";
    size_t message_len = strlen((const char *)message);

    // sk = SigningKey.generate(curve=NIST384p)
    // NIST P-384 is also known as secp384r1
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_secp384r1);
    if (sk == NULL) {
        fprintf(stderr, "Failed to create EC_KEY\n");
        return 1;
    }

    if (!EC_KEY_generate_key(sk)) {
        fprintf(stderr, "Failed to generate key\n");
        EC_KEY_free(sk);
        return 1;
    }

    // sig = sign(sk, message)
    ECDSA_SIG *sig = sign(sk, message, message_len);
    if (sig == NULL) {
        fprintf(stderr, "Failed to sign message\n");
        EC_KEY_free(sk);
        return 1;
    }

    // sk.verifying_key.verify(sig, message)
    unsigned char digest[SHA_DIGEST_LENGTH];
    SHA1(message, message_len, digest);
    int result = ECDSA_do_verify(digest, SHA_DIGEST_LENGTH, sig, sk);

    // print(...)
    printf("%s\n", result == 1 ? "True" : "False");

    // Cleanup
    ECDSA_SIG_free(sig);
    EC_KEY_free(sk);

    return 0;
}