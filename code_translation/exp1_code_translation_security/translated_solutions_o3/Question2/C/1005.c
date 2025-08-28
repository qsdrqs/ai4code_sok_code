/* ecdsa_demo.c
 *
 *   C translation of:
 *       import ecdsa
 *       def sign(msg, key):
 *           vk = key.verifying_key
 *           signature = key.sign(msg)
 *           assert vk.verify(signature, msg)
 *           return signature
 *
 *       def test():
 *           sk = ecdsa.SigningKey.generate()
 *           print(sign(b"message", sk))
 */

#include <stdio.h>
#include <string.h>
#include <assert.h>

#include <openssl/sha.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>

/* Convenience helper to dump a byte-array like Python prints bytes.hex() */
static void print_hex(const unsigned char *buf, size_t len)
{
    putchar('b'); putchar('"');
    for (size_t i = 0; i < len; ++i)
        printf("%02x", buf[i]);
    putchar('"'); putchar('\n');
}

/* Rough C equivalent of the Python “sign” function */
static unsigned char *sign_message(const unsigned char *msg,
                                   size_t               msg_len,
                                   EC_KEY              *key,
                                   unsigned int        *sig_len)
{
    /* --- 1. Hash the message (SHA-256 is used here) --------------------- */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(msg, msg_len, hash);

    /* --- 2. Allocate a signature buffer big enough for the curve -------- */
    int max_len = ECDSA_size(key);
    unsigned char *sig = (unsigned char *)OPENSSL_malloc(max_len);
    if (!sig) {
        fprintf(stderr, "Memory allocation failure\n");
        return NULL;
    }

    /* --- 3. Sign --------------------------------------------------------- */
    if (ECDSA_sign(0, hash, sizeof(hash), sig, sig_len, key) != 1) {
        fprintf(stderr, "ECDSA_sign() failed\n");
        OPENSSL_free(sig);
        return NULL;
    }

    /* --- 4. Verify (assert like the Python code) ------------------------ */
    int ok = ECDSA_verify(0, hash, sizeof(hash), sig, *sig_len, key);
    assert(ok == 1);     /* aborts the program if verification fails */

    return sig;          /* caller must OPENSSL_free() this */
}

/* Rough C equivalent of the Python “test” function */
static void test(void)
{
    /* Generate a fresh private key; Python's ecdsa library defaults to
       NIST P-256 (prime256v1), so we do the same here. */
    EC_KEY *sk = EC_KEY_new_by_curve_name(NID_X9_62_prime256v1);
    if (!sk || EC_KEY_generate_key(sk) != 1) {
        fprintf(stderr, "Key generation failed\n");
        EC_KEY_free(sk);
        return;
    }

    const unsigned char text[] = "message";
    unsigned int sig_len = 0;

    unsigned char *sig = sign_message(text, sizeof(text) - 1, sk, &sig_len);
    if (!sig) {
        EC_KEY_free(sk);
        return;          /* error already printed */
    }

    print_hex(sig, sig_len);   /* mimics Python’s print(sign(...)) */

    /* House-keeping */
    OPENSSL_free(sig);
    EC_KEY_free(sk);
}

int main(void)
{
    test();
    return 0;
}