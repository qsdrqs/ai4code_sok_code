#include <stdio.h>
#include <string.h>

/*  All the headers below will be available in the environment
 *  where this file is compiled, so you do **not** have to worry
 *  about their exact implementation.  They expose an API that is
 *  functionally equivalent to the Python classes used in the
 *  original snippet.
 */
#include "ecdsa.h"       /* Ecdsa_sign / Ecdsa_verify / Signature … */
#include "privateKey.h"  /* PrivateKey_new / PrivateKey_free …      */
#include "publicKey.h"   /* PublicKey_free …                         */

/* ------------------------------------------------------------ *
 *  Equivalent C translation of the original Python code        *
 * ------------------------------------------------------------ */
int main(void)
{
    /* 1.  Generate a brand-new private key and derive
     *     the associated public key.
     */
    PrivateKey *privateKey = PrivateKey_new();          /* -> same as PrivateKey() in Python */
    PublicKey  *publicKey  = PrivateKey_publicKey(privateKey);

    /* 2.  Message that we want to sign.                       */
    const char *message = "Test message";
    size_t      msgLen  = strlen(message);

    /* 3.  Sign the message.                                   */
    Signature *signature = Ecdsa_sign((const uint8_t *)message,
                                      msgLen,
                                      privateKey);

    /* 4.  Verify the signature.                               */
    int verified = Ecdsa_verify((const uint8_t *)message,
                                msgLen,
                                signature,
                                publicKey);

    /* 5.  Print the result (Python prints either “True” or “False”). */
    printf(verified ? "True\n" : "False\n");

    /* 6.  House-keeping: free all heap-allocated objects.      */
    Signature_free(signature);
    PublicKey_free(publicKey);
    PrivateKey_free(privateKey);

    return 0;
}