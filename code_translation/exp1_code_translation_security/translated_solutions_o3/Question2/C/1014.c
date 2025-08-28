/*
 * Functions that sign a given message using a given ECDSA signing key
 */

#include <stdint.h>
#include <stddef.h>
#include <openssl/sha.h>
#include <openssl/ecdsa.h>

/*
 * Signs a given message using a given ECDSA signing key.
 *
 * Parameters
 * ----------
 * message        : Pointer to the message bytes.
 * message_len    : Length of the message (in bytes).
 * eckey          : Pointer to an already-initialised EC_KEY that
 *                  contains the private (signing) key.
 * signature      : Buffer where the DER-encoded signature will be written.
 * signature_len  : Input  -> size of `signature` buffer (bytes).
 *                  Output -> actual number of bytes written.
 *
 * Returns
 * -------
 * 1 on success, 0 on failure.
 */
int sign_message(const uint8_t *message,
                 size_t         message_len,
                 EC_KEY        *eckey,
                 uint8_t       *signature,
                 size_t        *signature_len)
{
    /* 1. Hash the message (SHA-256) */
    uint8_t hash[SHA256_DIGEST_LENGTH];
    SHA256(message, message_len, hash);

    /* 2. Sign the hash.  The output is DER-encoded. */
    unsigned int sig_len_tmp = (unsigned int)*signature_len;  /* OpenSSL expects unsigned int */
    int result = ECDSA_sign(/* type = */ 0,
                            /* dgst     */ hash,
                            /* dgstlen  */ SHA256_DIGEST_LENGTH,
                            /* sig      */ signature,
                            /* siglen   */ &sig_len_tmp,
                            /* eckey    */ eckey);

    /* Copy back the actual signature length */
    *signature_len = (size_t)sig_len_tmp;

    return result;  /* 1 = success, 0 = error (per OpenSSL’s ECDSA_sign) */
}