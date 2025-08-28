/*
 *  Python code being translated:
 *
 *  from Crypto.Cipher import AES
 *
 *  key = b'Sixteen byte key'
 *
 *  def encrypt(key, msg):
 *      cipher = AES.new(key, AES.MODE_EAX)
 *      ciphertext, tag = cipher.encrypt_and_digest(msg)
 *      return (ciphertext, tag, cipher.nonce)
 *
 *  def decrypt(key, ciphertext, nonce, tag=None):
 *      cipher = AES.new(key, AES.MODE_EAX, nonce=nonce)
 *      plaintext = cipher.decrypt(ciphertext)
 *      if tag is not None:
 *          cipher.verify(tag)
 *      return plaintext
 *
 *  ctext, tag, nonce = encrypt(key, b"message")
 *  print(decrypt(key, ctext, nonce, tag=tag))
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tomcrypt.h>   /* <-- the only external dependency          */

#define TAG_LEN     16          /* identical to PyCryptodome default   */
#define NONCE_LEN   16          /* identical to PyCryptodome default   */
#define KEY_LEN     16          /* 128-bit key (“Sixteen byte key”)     */

/* ------------------------------------------------------------------ */
/*  encrypt()  – mirrors the Python encrypt()                          */
/* ------------------------------------------------------------------ */
int encrypt_AES_EAX(const unsigned char *key,
                    const unsigned char *msg,      unsigned long msg_len,
                    unsigned char       *ct,       /* out : ciphertext    */
                    unsigned char       *tag,      /* out : TAG_LEN bytes */
                    unsigned char       *nonce)    /* out : NONCE_LEN     */
{
    int           err;
    symmetric_EAX eax;
    unsigned long taglen = TAG_LEN;

    /* In real programs use a proper CSPRNG; here we use yarrow for demo.    */
    /* -------------------------------------------------------------------- */
    static prng_state yarrow_prng;
    static int        yarrow_ready = 0;

    if (!yarrow_ready) {                       /* one-time PRNG setup      */
        if ((err = yarrow_start(&yarrow_prng))              != CRYPT_OK ||
            (err = yarrow_add_entropy((const unsigned char*)"seed",4,
                                       &yarrow_prng))       != CRYPT_OK ||
            (err = yarrow_ready(&yarrow_prng))              != CRYPT_OK) {
            fprintf(stderr, "PRNG setup failed: %s\n", error_to_string(err));
            return err;
        }
        yarrow_ready = 1;
    }

    if (yarrow_read(nonce, NONCE_LEN, &yarrow_prng) != NONCE_LEN) {
        fprintf(stderr, "Unable to obtain nonce bytes\n");
        return CRYPT_ERROR;
    }

    /* ---- LibTomCrypt: initialise EAX state ----------------------------- */
    int aes_idx = find_cipher("aes");
    if (aes_idx == -1) {
        fprintf(stderr, "AES cipher not registered in LibTomCrypt\n");
        return CRYPT_INVALID_CIPHER;
    }

    if ((err = eax_init(&eax,
                        aes_idx,
                        key,            KEY_LEN,      /* key & key-len   */
                        nonce,          NONCE_LEN,    /* nonce & len     */
                        TAG_LEN))                    /* tag length req. */
        != CRYPT_OK) {
        fprintf(stderr, "eax_init: %s\n", error_to_string(err));
        return err;
    }

    /* (We do not use additional authenticated data, so skip addheader)    */

    if ((err = eax_encrypt(msg, ct, msg_len, &eax)) != CRYPT_OK) {
        fprintf(stderr, "eax_encrypt: %s\n", error_to_string(err));
        return err;
    }

    if ((err = eax_done(&eax, tag, &taglen)) != CRYPT_OK) {
        fprintf(stderr, "eax_done: %s\n", error_to_string(err));
        return err;
    }

    /* taglen is always TAG_LEN due to the parameter we passed into eax_init */
    (void)taglen;
    return CRYPT_OK;
}

/* ------------------------------------------------------------------ */
/*  decrypt()  – mirrors the Python decrypt()                          */
/* ------------------------------------------------------------------ */
int decrypt_AES_EAX(const unsigned char *key,
                    const unsigned char *ct,       unsigned long ct_len,
                    const unsigned char *nonce,    unsigned long nonce_len,
                    const unsigned char *tag,      unsigned long tag_len,
                    unsigned char       *pt)       /* out : recovered text */
{
    int             err;
    symmetric_EAX   eax;
    unsigned char   tag_calc[TAG_LEN];
    unsigned long   taglen_out = TAG_LEN;

    int aes_idx = find_cipher("aes");
    if (aes_idx == -1) return CRYPT_INVALID_CIPHER;

    if ((err = eax_init(&eax,
                        aes_idx,
                        key, KEY_LEN,
                        nonce, nonce_len,
                        TAG_LEN)) != CRYPT_OK) {
        fprintf(stderr, "eax_init(dec): %s\n", error_to_string(err));
        return err;
    }

    if ((err = eax_decrypt(ct, pt, ct_len, &eax)) != CRYPT_OK) {
        fprintf(stderr, "eax_decrypt: %s\n", error_to_string(err));
        return err;
    }

    if ((err = eax_done(&eax, tag_calc, &taglen_out)) != CRYPT_OK) {
        fprintf(stderr, "eax_done(dec): %s\n", error_to_string(err));
        return err;
    }

    if (tag != NULL) {      /* replicate Python’s optional verify      */
        if (tag_len != taglen_out ||
            compare_ct(tag, tag_calc, TAG_LEN) != 0)  { /* constant-time */
            fprintf(stderr, "Authentication failed!\n");
            return CRYPT_ERROR;
        }
    }
    return CRYPT_OK;
}

/* ================================================================== */
int main(void)
{
    unsigned char key[KEY_LEN] = "Sixteen byte key";
    const  char  *message      = "message";

    unsigned char ciphertext[128];
    unsigned char nonce[NONCE_LEN];
    unsigned char tag[TAG_LEN];
    unsigned char plaintext[128];

    unsigned long msg_len = (unsigned long)strlen(message);

    /* ---------------- Encrypt ---------------------------------------- */
    if (encrypt_AES_EAX(key,
                        (const unsigned char *)message, msg_len,
                        ciphertext,
                        tag,
                        nonce) != CRYPT_OK) {
        return EXIT_FAILURE;
    }

    /* ---------------- Decrypt ---------------------------------------- */
    if (decrypt_AES_EAX(key,
                        ciphertext,      msg_len,
                        nonce,           NONCE_LEN,
                        tag,             TAG_LEN,
                        plaintext) != CRYPT_OK) {
        return EXIT_FAILURE;
    }

    /* Add NUL so we can print like a C-string */
    plaintext[msg_len] = '\0';
    printf("%s\n", (char *)plaintext);   /* should print:  message */

    return EXIT_SUCCESS;
}