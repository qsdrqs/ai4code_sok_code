#include <stddef.h>  // For size_t

/**
 * @brief Signs a message using a secret key.
 *
 * @param m     Pointer to the message to be signed.
 * @param mlen  Length of the message in bytes.
 * @param sk    Pointer to the secret key structure.
 * @return      Pointer to the signature buffer, or NULL on failure.
 *              The caller is responsible for freeing the returned buffer.
 */
unsigned char* sign(const unsigned char* m, size_t mlen, const void* sk) {
    return crypto_sign(m, mlen, sk);
}