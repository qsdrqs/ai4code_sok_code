/*  sign.c
 *  Equivalent of Python:
 *      private_key.sign(message, ec.ECDSA(hashes.SHA256()))
 */

#include <openssl/sha.h>      /* SHA256()                     */
#include <openssl/ecdsa.h>    /* ECDSA_sign(), EC_KEY, etc.   */
#include <stdlib.h>           /* malloc(), free()             */
#include <string.h>           /* memset()                     */


/*-------------------------------------------------------------
 *  sign_ecdsa_sha256
 *-------------------------------------------------------------
 *  msg       : pointer to data to be signed
 *  msg_len   : length of the data (bytes)
 *  priv_key  : EC_KEY that holds the *private* part of key-pair
 *  sig       : (out) pointer that will receive malloc'ed buffer
 *  sig_len   : (out) number of bytes actually written to *sig
 *
 *  returns 1 on success, 0 on error
 *
 *  NOTE: Caller owns the buffer returned in *sig and must free it
 *------------------------------------------------------------*/
int sign_ecdsa_sha256(const unsigned char  *msg,
                      size_t                msg_len,
                      EC_KEY               *priv_key,
                      unsigned char       **sig,
                      unsigned int         *sig_len)
{
    if (!msg || !priv_key || !sig || !sig_len)
        return 0;

    /* 1. Hash the message with SHA-256 */
    unsigned char hash[SHA256_DIGEST_LENGTH];
    if (SHA256(msg, msg_len, hash) == NULL)
        return 0;

    /* 2. Allocate a buffer big enough for the signature           */
    *sig_len = ECDSA_size(priv_key);        /* upper-bound size    */
    *sig = (unsigned char *)malloc(*sig_len);
    if (*sig == NULL)
        return 0;

    /* 3. Sign the hash */
    if (ECDSA_sign(/*type*/ 0,
                   /*dgst*/ hash,
                   /*dgstlen*/ SHA256_DIGEST_LENGTH,
                   /*sig*/ *sig,
                   /*siglen*/ sig_len,
                   /*key*/ priv_key) != 1)
    {
        free(*sig);
        *sig = NULL;
        return 0;
    }

    /* success */
    return 1;
}