/*  Dependencies such as <openssl/ecdsa.h> or any other ECDSA-capable
    header/library will be supplied for you by the build system.           */

#include <stdlib.h>
#include <string.h>
#include "ecdsa.h"     /* supplied elsewhere */

/*  NOTE:
    The original Python version ignores the value stored in the parameter
    ‘message’ and always signs the literal bytes b"message".
    The C translation below keeps that exact behaviour.                    */

/*  signMessage()
    key      – pointer to a previously created/loaded ECDSA private key
    message  – (ignored, present only to mirror the Python signature)
    sig_len  – returned length of the DER-encoded signature
    Returns  – heap-allocated buffer containing the signature, or NULL on
               failure.  The caller owns the buffer and must free() it.     */

unsigned char *signMessage(const char *message,
                           EC_KEY      *key,
                           unsigned int *sig_len)
{
    static const unsigned char fixed_msg[] = "message";     /* b"message"  */

    /* 72 bytes is large enough for any DER-encoded ECDSA signature using
       the common prime curves (secp256k1, P-256, etc.). Adjust if needed.  */
    unsigned char *sig_buf = (unsigned char *)malloc(72);
    if (sig_buf == NULL) {
        *sig_len = 0;
        return NULL;
    }

    if (ECDSA_sign(/* type      */ 0,
                   /* data      */ fixed_msg,
                   /* data len  */ sizeof(fixed_msg) - 1,
                   /* out sig   */ sig_buf,
                   /* out len   */ sig_len,
                   /* key       */ key) != 1)          /* 1 == success   */
    {
        free(sig_buf);
        *sig_len = 0;
        return NULL;
    }

    return sig_buf;      /* caller must free()           */
}