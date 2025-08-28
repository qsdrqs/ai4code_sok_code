/*
 *  Sign a message with an RSA private key
 *  (PKCS#1 v1.5 ‑ SHA-256).
 *
 *  Equivalent to the Python snippet:
 *
 *      signer   = PKCS1_v1_5.new(priv_key)
 *      digest   = SHA256.new()
 *      digest.update(msg)
 *      signature = signer.sign(digest)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <openssl/evp.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/sha.h>
#include <openssl/err.h>

/*
 * Return value:
 *     pointer to freshly-allocated signature buffer (must be free()'d),
 *     NULL on error.
 * On success *sig_len contains the signature length (in bytes).
 *
 * priv_key_path  – path to a PEM encoded RSA private key.
 * msg / msg_len  – data to be signed.
 */
unsigned char *sign_message(const char *priv_key_path,
                            const unsigned char *msg, size_t msg_len,
                            size_t *sig_len)
{
    unsigned char  hash[SHA256_DIGEST_LENGTH];
    unsigned char *signature = NULL;
    RSA           *rsa       = NULL;
    FILE          *fp        = NULL;

    /* ------------------------------------------------------------------ */
    /* 1) Read the private key                                            */
    /* ------------------------------------------------------------------ */
    fp = fopen(priv_key_path, "r");
    if (!fp) {
        perror("fopen");
        goto cleanup;
    }

    rsa = PEM_read_RSAPrivateKey(fp, NULL, NULL, NULL);
    fclose(fp);
    fp = NULL;

    if (!rsa) {
        fprintf(stderr, "PEM_read_RSAPrivateKey: %s\n",
                ERR_error_string(ERR_get_error(), NULL));
        goto cleanup;
    }

    /* ------------------------------------------------------------------ */
    /* 2) Compute SHA-256 of the message                                  */
    /* ------------------------------------------------------------------ */
    if (!SHA256(msg, msg_len, hash)) {           /* returns hash pointer on success */
        fprintf(stderr, "SHA256 failed\n");
        goto cleanup;
    }

    /* ------------------------------------------------------------------ */
    /* 3) Allocate buffer and create the signature                        */
    /* ------------------------------------------------------------------ */
    signature = malloc(RSA_size(rsa));
    if (!signature) {
        perror("malloc");
        goto cleanup;
    }

    if (RSA_sign(NID_sha256,
                 hash, SHA256_DIGEST_LENGTH,
                 signature, (unsigned int *)sig_len,
                 rsa) != 1)
    {
        fprintf(stderr, "RSA_sign: %s\n",
                ERR_error_string(ERR_get_error(), NULL));
        free(signature);
        signature = NULL;
        goto cleanup;
    }

cleanup:
    if (rsa) RSA_free(rsa);
    if (fp)  fclose(fp);
    return signature;   /* NULL if an error occurred */
}

/* ---------------------------------------------------------------------- */
/* Example usage                                                          */
/* ---------------------------------------------------------------------- */
int main(void)
{
    const char *message = "Hello, PKCS#1 v1.5 world!";
    size_t      sig_len;
    unsigned char *sig =
        sign_message("private.pem",
                     (const unsigned char *)message, strlen(message),
                     &sig_len);

    if (!sig) {
        fprintf(stderr, "Signing failed\n");
        return EXIT_FAILURE;
    }

    printf("Signature (%zu bytes):\n", sig_len);
    for (size_t i = 0; i < sig_len; ++i)
        printf("%02x", sig[i]);
    putchar('\n');

    free(sig);
    return EXIT_SUCCESS;
}