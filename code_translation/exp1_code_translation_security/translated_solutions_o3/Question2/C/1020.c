/*
 *  Equivalent of
 *
 *    sk = ecdsa.SigningKey.generate(curve = ecdsa.SECP256k1)
 *    vk = sk.get_verifying_key()
 *
 *    def sign_message(sk, message):
 *        sig = sk.sign(message)
 *        return vk.verify(sig, message)
 *
 *    print(sign_message(sk, ".."))
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/sha.h>      /* SHA256()                                  */
#include <secp256k1.h>        /* libsecp256k1 – ECDSA on the K1 curve      */

#define MESSAGE ".."          /* identical to the Python snippet           */

/* ------------------------------------------------------------------------- */
/* Helper: sign the message and verify the signature                         */
/* ------------------------------------------------------------------------- */
static int
sign_and_verify(secp256k1_context          *ctx,
                const unsigned char        *seckey,
                const secp256k1_pubkey     *pubkey,
                const char                 *message)
{
    unsigned char           hash[32];
    secp256k1_ecdsa_signature sig;

    /* 1. Hash the message (ECDSA signs fixed-length digests) -------------- */
    SHA256((const unsigned char *)message, strlen(message), hash);

    /* 2. Sign ------------------------------------------------------------- */
    if (!secp256k1_ecdsa_sign(ctx, &sig, hash, seckey, NULL, NULL))
        return 0;

    /* 3. Verify ----------------------------------------------------------- */
    return secp256k1_ecdsa_verify(ctx, &sig, hash, pubkey);
}

/* ------------------------------------------------------------------------- */
int main(void)
{
    /* Create a context that can both sign and verify ---------------------- */
    secp256k1_context *ctx =
        secp256k1_context_create(SECP256K1_CONTEXT_SIGN |
                                 SECP256K1_CONTEXT_VERIFY);

    /* --------------------------------------------------------------------- */
    /* Generate a fresh, valid 32-byte private key                           */
    /* --------------------------------------------------------------------- */
    unsigned char seckey[32];
    FILE *rnd = fopen("/dev/urandom", "rb");
    if (rnd == NULL) {
        perror("Cannot open /dev/urandom");
        return 1;
    }
    do {
        fread(seckey, 1, sizeof(seckey), rnd);
    } while (!secp256k1_ec_seckey_verify(ctx, seckey));
    fclose(rnd);

    /* Derive corresponding public key ------------------------------------- */
    secp256k1_pubkey pubkey;
    if (!secp256k1_ec_pubkey_create(ctx, &pubkey, seckey)) {
        fprintf(stderr, "Public-key generation failed\n");
        secp256k1_context_destroy(ctx);
        return 1;
    }

    /* --------------------------------------------------------------------- */
    /* Python’s  sign_message(sk, "..")  equivalent                          */
    /* --------------------------------------------------------------------- */
    int ok = sign_and_verify(ctx, seckey, &pubkey, MESSAGE);

    printf("%s\n", ok ? "True" : "False");      /* prints “True” on success */

    secp256k1_context_destroy(ctx);
    return 0;
}