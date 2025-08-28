/* sign.c */

#include "crypto_dependencies.h"   /* <- whatever header(s) define Signature,
                                      SecretKey, etc. */

/* ------------------------------------------------------------------
 *  The Python original was:
 *
 *      def sign(m, sk):
 *          return sk.sign(m)
 *
 *  In C we model `sk` as a structure whose first member is a function
 *  pointer named `sign`.  The concrete structure definition is expected
 *  to live in whatever header you include above.
 * ------------------------------------------------------------------ */

/* C translation */
Signature sign(const uint8_t *msg, size_t msg_len, const SecretKey *sk)
{
    /* defer the actual work to the key’s `sign` method */
    return sk->sign(msg, msg_len);
}