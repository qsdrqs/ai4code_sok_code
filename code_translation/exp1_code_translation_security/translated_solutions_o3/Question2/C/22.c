/*  myfile.c  */

#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/ec.h>
#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>

/*
 * Equivalent of the Python function:
 *
 *   sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
 *   sig = sk.sign(message)
 *
 *   NOTE:  The Python version does not return anything, so this function
 *          mimics that behaviour.  The generated key is thrown away after
 *          the call, and the signature is only printed.  Adapt as needed.
 */
void myfunction(const unsigned char *message, size_t message_len)
{
    /* 1. Generate a fresh EC key pair on curve secp256k1. */
    EC_KEY *eckey = EC_KEY_new_by_curve_name(NID_secp256k1);
    if (!eckey) {
        fprintf(stderr, "Could not allocate EC_KEY\n");
        return;
    }

    if (EC_KEY_generate_key(eckey) != 1) {
        fprintf(stderr, "Key generation failed\n");
        EC_KEY_free(eckey);
        return;
    }

    /* 2. Allocate a buffer big enough to hold the DER-encoded signature. */
    unsigned int sig_len = ECDSA_size(eckey);
    unsigned char *signature = OPENSSL_malloc(sig_len);
    if (!signature) {
        fprintf(stderr, "Out of memory allocating signature buffer\n");
        EC_KEY_free(eckey);
        return;
    }

    /* 3. Sign the input message. */
    if (ECDSA_sign(/* type = */ 0,
                   message,
                   (int)message_len,
                   signature,
                   &sig_len,
                   eckey) != 1) {
        fprintf(stderr, "Signing failed\n");
        OPENSSL_free(signature);
        EC_KEY_free(eckey);
        return;
    }

    /* Success – for demo purposes just print the size. */
    printf("Signature created: %u bytes\n", sig_len);

    /* Clean up. */
    OPENSSL_free(signature);
    EC_KEY_free(eckey);
}

/* Small driver to show usage */
int main(void)
{
    const char *msg = "Hello, ECDSA from C!";
    myfunction((const unsigned char *)msg, strlen(msg));
    return 0;
}