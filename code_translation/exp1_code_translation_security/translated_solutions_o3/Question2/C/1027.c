/*
 * Assumed external definitions:
 *
 * typedef struct Signature     Signature;
 * typedef struct SigningKey    SigningKey;
 *
 * The SigningKey structure exposes a member function pointer with the
 * following prototype:
 *
 *     Signature (*sign)(const SigningKey *self,
 *                       const void       *msg,
 *                       size_t            msg_len);
 *
 * All required headers / declarations will be supplied elsewhere.
 */

#include <stddef.h>   /* for size_t */

/*--------------------------------------------------------------------
 * sign_message
 * -------------------------------------------------------------------
 * Signs the supplied message using the provided signing key and
 * returns the resulting Signature object.
 *
 * Parameters
 *   message      – pointer to the message buffer
 *   message_len  – length of the message (in bytes)
 *   signing_key  – pointer to an initialized SigningKey instance
 *
 * Returns
 *   Signature    – the detached signature produced by signing_key
 * ------------------------------------------------------------------*/
Signature sign_message(const void      *message,
                       size_t           message_len,
                       const SigningKey *signing_key)
{
    /* Delegate the work to the key’s sign method. */
    return signing_key->sign(signing_key, message, message_len);
}