/*
 *  sign_message.c
 *
 *  Build command (example):
 *      cc -Wall -Wextra -O2 sign_message.c -lcrypto -o sign_message
 *
 *  NOTE:  All OpenSSL headers / libraries are assumed to be available.
 */

#include <openssl/ecdsa.h>
#include <openssl/obj_mac.h>
#include <openssl/err.h>
#include <stdlib.h>
#include <string.h>

/*
 * sign_message()
 *
 * Parameters
 *   msg      : pointer to the data to be signed
 *   msg_len  : length of the data
 *   curve_nid: OpenSSL NID of the EC curve to use (e.g. NID_secp256k1)
 *   sig_len  : (output) receives the length of the produced signature
 *
 * Returns
 *   Pointer to a malloc()-ed buffer containing the DER-encoded ECDSA
 *   signature on success, or NULL on error.
 *
 *   The caller must free() the returned buffer.
 */
unsigned char *
sign_message(const unsigned char *msg,
             size_t               msg_len,
             int                  curve_nid,
             unsigned int        *sig_len)
{
    EC_KEY       *key       = NULL;
    unsigned char *sig_buf  = NULL;

    /* 1. Generate a fresh private key on the requested curve. */
    key = EC_KEY_new_by_curve_name(curve_nid);
    if (!key)
        goto error;

    if (EC_KEY_generate_key(key) != 1)
        goto error;

    /* 2. Determine how big the signature will be, allocate buffer. */
    int buf_sz = ECDSA_size(key);
    sig_buf     = (unsigned char *)malloc(buf_sz);
    if (!sig_buf)
        goto error;

    /* 3. Sign the message. */
    if (ECDSA_sign(0, msg, (int)msg_len, sig_buf, sig_len, key) != 1)
        goto error;

    /* Clean up the key object; the signature is all we need now. */
    EC_KEY_free(key);
    return sig_buf;

error:
    if (sig_buf)
        free(sig_buf);
    if (key)
        EC_KEY_free(key);
    if (sig_len)
        *sig_len = 0;
    return NULL;
}

/* Example usage
#include <stdio.h>

int main(void)
{
    const unsigned char message[] = "Hello ECDSA!";
    unsigned int sig_len;
    unsigned char *sig = sign_message(message,
                                      sizeof(message) - 1,
                                      NID_secp256k1,  /* same as python's SECP256k1 curve * /
                                      &sig_len);

    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        return 1;
    }

    printf("Signature length: %u bytes\n", sig_len);

    // ... do something with signature ...

    free(sig);
    return 0;
}
*/