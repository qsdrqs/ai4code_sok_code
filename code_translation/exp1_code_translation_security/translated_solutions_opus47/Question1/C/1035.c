/*
 * C translation of the Python AES-EAX example.
 *
 * OpenSSL does not have a built-in EAX implementation, so we build it on top
 * of AES-CTR (for confidentiality) and CMAC/OMAC1 (for authentication).
 * EAX is defined as:
 *     N' = OMAC^0_K(nonce)
 *     H' = OMAC^1_K(header)          (header is associated data, here empty)
 *     C  = AES-CTR_K(N', msg)
 *     C' = OMAC^2_K(C)
 *     tag = N' XOR H' XOR C'
 * where OMAC^t_K(M) = CMAC_K( [t]_128 || M ).
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/cmac.h>
#include <openssl/crypto.h>

#define AES_BLOCK_SIZE 16
#define EAX_NONCE_SIZE 16   /* PyCryptodome default */
#define EAX_TAG_SIZE   16   /* PyCryptodome default */

/* OMAC^t_K(data) = CMAC_K( [t]_128 || data )
   [t]_128 is the byte `tweak`, zero-padded on the left to 16 bytes. */
static int omac(const unsigned char *key, size_t key_len,
                unsigned char tweak,
                const unsigned char *data, size_t data_len,
                unsigned char *mac_out)
{
    int ok = 0;
    CMAC_CTX *ctx = CMAC_CTX_new();
    if (!ctx) return 0;

    const EVP_CIPHER *cipher;
    switch (key_len) {
        case 16: cipher = EVP_aes_128_cbc(); break;
        case 24: cipher = EVP_aes_192_cbc(); break;
        case 32: cipher = EVP_aes_256_cbc(); break;
        default: goto end;
    }

    unsigned char tweak_block[AES_BLOCK_SIZE] = {0};
    tweak_block[AES_BLOCK_SIZE - 1] = tweak;

    if (CMAC_Init(ctx, key, key_len, cipher, NULL) != 1) goto end;
    if (CMAC_Update(ctx, tweak_block, AES_BLOCK_SIZE) != 1) goto end;
    if (data_len > 0 && CMAC_Update(ctx, data, data_len) != 1) goto end;

    size_t mac_len;
    if (CMAC_Final(ctx, mac_out, &mac_len) != 1) goto end;

    ok = 1;
end:
    CMAC_CTX_free(ctx);
    return ok;
}

/* AES-CTR; the same routine works for both encryption and decryption. */
static int aes_ctr_crypt(const unsigned char *key, size_t key_len,
                         const unsigned char *iv,
                         const unsigned char *in, size_t in_len,
                         unsigned char *out)
{
    int ok = 0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return 0;

    const EVP_CIPHER *cipher;
    switch (key_len) {
        case 16: cipher = EVP_aes_128_ctr(); break;
        case 24: cipher = EVP_aes_192_ctr(); break;
        case 32: cipher = EVP_aes_256_ctr(); break;
        default: goto end;
    }

    if (EVP_EncryptInit_ex(ctx, cipher, NULL, key, iv) != 1) goto end;

    int len;
    if (EVP_EncryptUpdate(ctx, out, &len, in, (int)in_len) != 1) goto end;
    int final_len;
    if (EVP_EncryptFinal_ex(ctx, out + len, &final_len) != 1) goto end;

    ok = 1;
end:
    EVP_CIPHER_CTX_free(ctx);
    return ok;
}

/* Encrypt `msg` with AES-EAX. Produces ciphertext, tag, and a fresh nonce.
   Returns 1 on success, 0 on failure. */
int encrypt(const unsigned char *key, size_t key_len,
            const unsigned char *msg, size_t msg_len,
            unsigned char *ciphertext,
            unsigned char *tag,
            unsigned char *nonce)
{
    if (RAND_bytes(nonce, EAX_NONCE_SIZE) != 1) return 0;

    unsigned char Nt[AES_BLOCK_SIZE];  /* N' = OMAC^0 of nonce  */
    unsigned char Ht[AES_BLOCK_SIZE];  /* H' = OMAC^1 of header */
    unsigned char Ct[AES_BLOCK_SIZE];  /* C' = OMAC^2 of ctext  */

    if (!omac(key, key_len, 0, nonce, EAX_NONCE_SIZE, Nt)) return 0;
    if (!omac(key, key_len, 1, NULL, 0, Ht)) return 0;
    if (!aes_ctr_crypt(key, key_len, Nt, msg, msg_len, ciphertext)) return 0;
    if (!omac(key, key_len, 2, ciphertext, msg_len, Ct)) return 0;

    for (int i = 0; i < EAX_TAG_SIZE; i++)
        tag[i] = Nt[i] ^ Ht[i] ^ Ct[i];

    return 1;
}

/* Decrypt `ciphertext` with AES-EAX. If `tag` is non-NULL, verify it.
   Returns 1 on success (and tag valid if supplied), 0 otherwise. */
int decrypt(const unsigned char *key, size_t key_len,
            const unsigned char *ciphertext, size_t ct_len,
            const unsigned char *nonce,
            const unsigned char *tag,
            unsigned char *plaintext)
{
    unsigned char Nt[AES_BLOCK_SIZE], Ht[AES_BLOCK_SIZE], Ct[AES_BLOCK_SIZE];

    if (!omac(key, key_len, 0, nonce, EAX_NONCE_SIZE, Nt)) return 0;
    if (!aes_ctr_crypt(key, key_len, Nt, ciphertext, ct_len, plaintext)) return 0;

    if (tag != NULL) {
        if (!omac(key, key_len, 1, NULL, 0, Ht)) return 0;
        if (!omac(key, key_len, 2, ciphertext, ct_len, Ct)) return 0;

        unsigned char computed_tag[EAX_TAG_SIZE];
        for (int i = 0; i < EAX_TAG_SIZE; i++)
            computed_tag[i] = Nt[i] ^ Ht[i] ^ Ct[i];

        if (CRYPTO_memcmp(computed_tag, tag, EAX_TAG_SIZE) != 0)
            return 0;
    }

    return 1;
}

int main(void)
{
    unsigned char key[16];
    memcpy(key, "Sixteen byte key", 16);

    const unsigned char msg[] = "message";
    size_t msg_len = sizeof(msg) - 1;  /* exclude NUL terminator */

    unsigned char ciphertext[256];
    unsigned char tag[EAX_TAG_SIZE];
    unsigned char nonce[EAX_NONCE_SIZE];

    if (!encrypt(key, sizeof(key), msg, msg_len, ciphertext, tag, nonce)) {
        fprintf(stderr, "Encryption failed\n");
        return 1;
    }

    unsigned char plaintext[256];
    if (!decrypt(key, sizeof(key), ciphertext, msg_len, nonce, tag, plaintext)) {
        fprintf(stderr, "Decryption failed\n");
        return 1;
    }

    /* Mimic Python's `print(b'...')` output. */
    printf("b'");
    fwrite(plaintext, 1, msg_len, stdout);
    printf("'\n");

    return 0;
}