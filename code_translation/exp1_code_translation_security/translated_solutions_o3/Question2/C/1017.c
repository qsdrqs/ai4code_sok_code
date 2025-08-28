/* sign_message.c */

#include <stddef.h>   /* size_t */
#include "ecdsa_api.h"/* <-- replace with the header that defines
                          *    EC_SigningKey and EC_Signature as well as
                          *    the signing routine you’ll use.            */

/*
 * Signs `message` with the given ECDSA signing key.
 *
 * PARAMETERS
 *   message      – pointer to the byte‐buffer to be signed
 *   message_len  – length of that buffer (in bytes)
 *   signing_key  – pointer to an already-initialized EC signing key
 *
 * RETURNS
 *   Pointer to an EC_Signature object on success; NULL on failure.
 *   Lifetime / cleanup semantics follow whatever your crypto library
 *   dictates (often: free with EC_signature_free(…) or similar).
 */
EC_Signature *sign_message(const unsigned char *message,
                           size_t               message_len,
                           const EC_SigningKey *signing_key)
{
    /* Delegate the actual work to the underlying crypto library.         *
     * ecdsa_sign(...) is a placeholder for whatever call your library    *
     * makes available—e.g. uECC_sign(), ECDSA_do_sign(), etc.            */
    EC_Signature *signature = ecdsa_sign(signing_key,
                                         message,
                                         message_len);

    /* Simply relay the result back to the caller (Python’s “return
     * signature”).  Any error handling—NULL checks, logging, etc.—can be
     * added here if desired.                                             */
    return signature;
}