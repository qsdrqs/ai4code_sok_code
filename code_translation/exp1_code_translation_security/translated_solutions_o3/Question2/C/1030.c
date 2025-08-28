/*  ----------------------------------------------------------
 *  Python → C translation of the snippet supplied by the user
 *  ---------------------------------------------------------- */

#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>

/*  These two headers come from OpenSSL (or any other SHA-3 provider)
 *  and from libsecp256k1 respectively.  They will be available in the
 *  build environment as promised.
 */
#include <openssl/evp.h>      /* for SHA3-256 */
#include <secp256k1.h>        /* for secp256k1 ECDSA             */

/* -------------------------------------------------------------
 * Compute SHA3-256 and return the 32-byte digest in ‘out_hash’.
 * ------------------------------------------------------------- */
static void sha3_256_hash(const char *msg, uint8_t out_hash[32])
{
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();

    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_DigestUpdate  (ctx, msg, strlen(msg));
    EVP_DigestFinal_ex(ctx, out_hash, NULL);

    EVP_MD_CTX_free(ctx);
}

/* ----------------------------------------------------------------
 * Produce a cryptographically–strong 32-byte (256-bit) random number
 * suitable for a secp256k1 private key.  Keep drawing until the key
 * is valid ( < curve order ).
 * ---------------------------------------------------------------- */
static void random_private_key(secp256k1_context *ctx, uint8_t key32[32])
{
    FILE *rnd = fopen("/dev/urandom", "rb");
    if (!rnd) {
        perror("Cannot open /dev/urandom");
        exit(EXIT_FAILURE);
    }

    do {
        if (fread(key32, 1, 32, rnd) != 32) {
            fprintf(stderr, "Insufficient random data\n");
            exit(EXIT_FAILURE);
        }
        /* secp256k1_ec_seckey_verify() returns 1 if the key is valid. */
    } while (!secp256k1_ec_seckey_verify(ctx, key32));

    fclose(rnd);
}

/* ----------------------------------------------------------------
 * Dump an array of bytes as a hexadecimal string (without 0x prefix).
 * ---------------------------------------------------------------- */
static void hexprint(const uint8_t *data, size_t len)
{
    for (size_t i = 0; i < len; ++i)
        printf("%02x", data[i]);
}

int main(void)
{
    /* 1. Message to be signed */
    const char *msg = "Message for ECDSA signing";

    /* 2. Hash message with SHA3-256 -------------------------------- */
    uint8_t msg_hash[32];
    sha3_256_hash(msg, msg_hash);

    /* 3. Initialise a libsecp256k1 context (both sign and verify) -- */
    secp256k1_context *ctx = secp256k1_context_create(
        SECP256K1_CONTEXT_SIGN | SECP256K1_CONTEXT_VERIFY);

    /* 4. Generate a random private key ----------------------------- */
    uint8_t seckey[32];
    random_private_key(ctx, seckey);

    /* 5. Sign the hash -------------------------------------------- */
    secp256k1_ecdsa_signature signature;
    if (!secp256k1_ecdsa_sign(ctx, &signature, msg_hash, seckey,
                              NULL, NULL)) {
        fprintf(stderr, "Failed to create signature\n");
        secp256k1_context_destroy(ctx);
        return EXIT_FAILURE;
    }

    /* 6. Extract r and s (compact form = r||s, 32 bytes each) ------ */
    uint8_t sig64[64];                 /* r (0-31) | s (32-63)       */
    secp256k1_ecdsa_signature_serialize_compact(ctx, sig64, &signature);

    /* 7. Display results ------------------------------------------ */
    printf("Message: %s\n", msg);

    printf("Private key: 0x");
    hexprint(seckey, 32);
    printf("\n");

    printf("Signature: r=0x");
    hexprint(sig64, 32);           /* r  */
    printf(", s=0x");
    hexprint(sig64 + 32, 32);      /* s  */
    printf("\n");

    /* 8. (Optional) verify to prove everything works --------------- */
    secp256k1_pubkey pubkey;
    secp256k1_ec_pubkey_create(ctx, &pubkey, seckey);

    int verified = secp256k1_ecdsa_verify(ctx, &signature,
                                          msg_hash, &pubkey);

    printf("Signature valid? %s\n", verified ? "yes" : "no");

    /* 9. Cleanup --------------------------------------------------- */
    secp256k1_context_destroy(ctx);
    return verified ? EXIT_SUCCESS : EXIT_FAILURE;
}