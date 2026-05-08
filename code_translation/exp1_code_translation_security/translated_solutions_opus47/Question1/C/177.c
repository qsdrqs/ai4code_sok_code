/*
 *  C translation of the Python AES-EAX encrypt/decrypt pair, using OpenSSL.
 *
 *  OpenSSL has no built-in EAX mode, so we build it on top of
 *  AES-CTR and AES-CMAC (EAX = CTR for privacy + OMAC for integrity).
 *
 *  Build with:  cc this_file.c -lcrypto
 */

#include <openssl/evp.h>
#include <openssl/cmac.h>
#include <openssl/rand.h>
#include <stdlib.h>
#include <string.h>

#define AES_BLOCK_SIZE 16
#define EAX_NONCE_LEN  16        /* PyCryptodome default (= AES block size) */
#define AES_KEY_LEN    16        /* AES-128 */

/* (ciphertext, nonce) pair, mirroring the Python tuple. */
typedef struct {
    unsigned char *c;                          /* ciphertext; caller frees */
    size_t         c_len;
    unsigned char  nonce[EAX_NONCE_LEN];
} ciphertext_nonce_t;

/* EAX's OMAC^t_K(data) = CMAC_K( <15 zero bytes, byte t> || data ) */
static int eax_omac(const unsigned char *key,
                    unsigned char        t,
                    const unsigned char *data,
                    size_t               data_len,
                    unsigned char        mac_out[AES_BLOCK_SIZE])
{
    CMAC_CTX *ctx = CMAC_CTX_new();
    if (!ctx) return 0;

    unsigned char prefix[AES_BLOCK_SIZE] = {0};
    prefix[AES_BLOCK_SIZE - 1] = t;

    int ok = CMAC_Init(ctx, key, AES_KEY_LEN, EVP_aes_128_cbc(), NULL);
    if (ok) ok = CMAC_Update(ctx, prefix, AES_BLOCK_SIZE);
    if (ok && data_len) ok = CMAC_Update(ctx, data, data_len);

    size_t mac_len = AES_BLOCK_SIZE;
    if (ok) ok = CMAC_Final(ctx, mac_out, &mac_len);

    CMAC_CTX_free(ctx);
    return ok;
}

/* AES-128-CTR; CTR is symmetric so this handles both encrypt and decrypt. */
static int aes_ctr_crypt(const unsigned char *key,
                         const unsigned char *iv,
                         const unsigned char *in,
                         size_t               in_len,
                         unsigned char       *out)
{
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    if (!ctx) return 0;

    int out_len = 0, final_len = 0;
    int ok = EVP_EncryptInit_ex(ctx, EVP_aes_128_ctr(), NULL, key, iv);
    if (ok) ok = EVP_EncryptUpdate(ctx, out, &out_len, in, (int)in_len);
    if (ok) ok = EVP_EncryptFinal_ex(ctx, out + out_len, &final_len);

    EVP_CIPHER_CTX_free(ctx);
    return ok;
}

/*
 *  def encrypt(m, sk):
 *      cipher = AES.new(sk, AES.MODE_EAX)
 *      nonce  = cipher.nonce
 *      c, _   = cipher.encrypt_and_digest(m)
 *      return (c, nonce)
 */
ciphertext_nonce_t encrypt(const unsigned char *m, size_t m_len,
                           const unsigned char *sk)
{
    ciphertext_nonce_t cn;
    cn.c     = (unsigned char *)malloc(m_len);
    cn.c_len = m_len;

    /* AES.new(sk, AES.MODE_EAX) picks a random 16-byte nonce. */
    RAND_bytes(cn.nonce, EAX_NONCE_LEN);

    /* EAX initial counter N' = OMAC^0(nonce) */
    unsigned char N_prime[AES_BLOCK_SIZE];
    eax_omac(sk, 0, cn.nonce, EAX_NONCE_LEN, N_prime);

    /* c = CTR_{sk}^{N'}(m); tag is discarded (the "_" in Python). */
    aes_ctr_crypt(sk, N_prime, m, m_len, cn.c);

    return cn;
}

/*
 *  def decrypt(cn, sk):
 *      (c, nonce) = cn
 *      cipher = AES.new(sk, AES.MODE_EAX, nonce=nonce)
 *      return cipher.decrypt(c)
 */
unsigned char *decrypt(ciphertext_nonce_t  cn,
                       const unsigned char *sk,
                       size_t              *m_len_out)
{
    const unsigned char *c     = cn.c;
    size_t               c_len = cn.c_len;
    const unsigned char *nonce = cn.nonce;

    unsigned char *m = (unsigned char *)malloc(c_len);
    *m_len_out = c_len;

    unsigned char N_prime[AES_BLOCK_SIZE];
    eax_omac(sk, 0, nonce, EAX_NONCE_LEN, N_prime);

    /* m = CTR_{sk}^{N'}(c)  (CTR decryption is identical to encryption) */
    aes_ctr_crypt(sk, N_prime, c, c_len, m);

    return m;
}