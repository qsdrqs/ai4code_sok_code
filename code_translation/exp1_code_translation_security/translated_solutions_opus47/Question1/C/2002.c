/*
 * C translation of the Python (pyaes) AES-256-CTR example.
 *
 * All the cryptographic pieces that "pyaes" supplies are inlined here so
 * there are no external dependencies.  The CTR mode defaults -- a 128-bit
 * big-endian counter whose initial value is 1 -- match pyaes'
 * AESModeOfOperationCTR.
 *
 * Build: cc -std=c99 -O2 this_file.c -o this_file
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

/* =====================================================================
 * AES-256 block cipher + CTR mode (self-contained).
 * ===================================================================== */

enum {
    AES_Nb          = 4,
    AES_Nk          = 8,
    AES_Nr          = 14,
    AES_BLOCKLEN    = 16,
    AES_KEYLEN      = 32,
    AES_keyExpSize  = 240
};

typedef uint8_t aes_state_t[4][4];

static const uint8_t aes_sbox[256] = {
    0x63,0x7c,0x77,0x7b,0xf2,0x6b,0x6f,0xc5,0x30,0x01,0x67,0x2b,0xfe,0xd7,0xab,0x76,
    0xca,0x82,0xc9,0x7d,0xfa,0x59,0x47,0xf0,0xad,0xd4,0xa2,0xaf,0x9c,0xa4,0x72,0xc0,
    0xb7,0xfd,0x93,0x26,0x36,0x3f,0xf7,0xcc,0x34,0xa5,0xe5,0xf1,0x71,0xd8,0x31,0x15,
    0x04,0xc7,0x23,0xc3,0x18,0x96,0x05,0x9a,0x07,0x12,0x80,0xe2,0xeb,0x27,0xb2,0x75,
    0x09,0x83,0x2c,0x1a,0x1b,0x6e,0x5a,0xa0,0x52,0x3b,0xd6,0xb3,0x29,0xe3,0x2f,0x84,
    0x53,0xd1,0x00,0xed,0x20,0xfc,0xb1,0x5b,0x6a,0xcb,0xbe,0x39,0x4a,0x4c,0x58,0xcf,
    0xd0,0xef,0xaa,0xfb,0x43,0x4d,0x33,0x85,0x45,0xf9,0x02,0x7f,0x50,0x3c,0x9f,0xa8,
    0x51,0xa3,0x40,0x8f,0x92,0x9d,0x38,0xf5,0xbc,0xb6,0xda,0x21,0x10,0xff,0xf3,0xd2,
    0xcd,0x0c,0x13,0xec,0x5f,0x97,0x44,0x17,0xc4,0xa7,0x7e,0x3d,0x64,0x5d,0x19,0x73,
    0x60,0x81,0x4f,0xdc,0x22,0x2a,0x90,0x88,0x46,0xee,0xb8,0x14,0xde,0x5e,0x0b,0xdb,
    0xe0,0x32,0x3a,0x0a,0x49,0x06,0x24,0x5c,0xc2,0xd3,0xac,0x62,0x91,0x95,0xe4,0x79,
    0xe7,0xc8,0x37,0x6d,0x8d,0xd5,0x4e,0xa9,0x6c,0x56,0xf4,0xea,0x65,0x7a,0xae,0x08,
    0xba,0x78,0x25,0x2e,0x1c,0xa6,0xb4,0xc6,0xe8,0xdd,0x74,0x1f,0x4b,0xbd,0x8b,0x8a,
    0x70,0x3e,0xb5,0x66,0x48,0x03,0xf6,0x0e,0x61,0x35,0x57,0xb9,0x86,0xc1,0x1d,0x9e,
    0xe1,0xf8,0x98,0x11,0x69,0xd9,0x8e,0x94,0x9b,0x1e,0x87,0xe9,0xce,0x55,0x28,0xdf,
    0x8c,0xa1,0x89,0x0d,0xbf,0xe6,0x42,0x68,0x41,0x99,0x2d,0x0f,0xb0,0x54,0xbb,0x16
};

static const uint8_t aes_rcon[11] = {
    0x8d,0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80,0x1b,0x36
};

static void aes_key_expansion(uint8_t *rk, const uint8_t *key) {
    unsigned i;
    uint8_t t[4];

    for (i = 0; i < AES_Nk; ++i) {
        rk[i*4+0] = key[i*4+0];
        rk[i*4+1] = key[i*4+1];
        rk[i*4+2] = key[i*4+2];
        rk[i*4+3] = key[i*4+3];
    }

    for (i = AES_Nk; i < AES_Nb * (AES_Nr + 1); ++i) {
        unsigned p = (i - 1) * 4;
        t[0] = rk[p+0]; t[1] = rk[p+1];
        t[2] = rk[p+2]; t[3] = rk[p+3];

        if (i % AES_Nk == 0) {
            uint8_t tmp = t[0];
            t[0] = t[1]; t[1] = t[2]; t[2] = t[3]; t[3] = tmp;
            t[0] = aes_sbox[t[0]];
            t[1] = aes_sbox[t[1]];
            t[2] = aes_sbox[t[2]];
            t[3] = aes_sbox[t[3]];
            t[0] ^= aes_rcon[i / AES_Nk];
        } else if (i % AES_Nk == 4) {
            t[0] = aes_sbox[t[0]];
            t[1] = aes_sbox[t[1]];
            t[2] = aes_sbox[t[2]];
            t[3] = aes_sbox[t[3]];
        }

        {
            unsigned j = i * 4, q = (i - AES_Nk) * 4;
            rk[j+0] = rk[q+0] ^ t[0];
            rk[j+1] = rk[q+1] ^ t[1];
            rk[j+2] = rk[q+2] ^ t[2];
            rk[j+3] = rk[q+3] ^ t[3];
        }
    }
}

static void aes_add_round_key(unsigned round, aes_state_t *state,
                              const uint8_t *rk) {
    unsigned i, j;
    for (i = 0; i < 4; ++i)
        for (j = 0; j < 4; ++j)
            (*state)[i][j] ^= rk[round * AES_Nb * 4 + i * AES_Nb + j];
}

static void aes_sub_bytes(aes_state_t *state) {
    unsigned i, j;
    for (i = 0; i < 4; ++i)
        for (j = 0; j < 4; ++j)
            (*state)[i][j] = aes_sbox[(*state)[i][j]];
}

static void aes_shift_rows(aes_state_t *state) {
    uint8_t t;

    t              = (*state)[0][1];
    (*state)[0][1] = (*state)[1][1];
    (*state)[1][1] = (*state)[2][1];
    (*state)[2][1] = (*state)[3][1];
    (*state)[3][1] = t;

    t              = (*state)[0][2];
    (*state)[0][2] = (*state)[2][2];
    (*state)[2][2] = t;
    t              = (*state)[1][2];
    (*state)[1][2] = (*state)[3][2];
    (*state)[3][2] = t;

    t              = (*state)[0][3];
    (*state)[0][3] = (*state)[3][3];
    (*state)[3][3] = (*state)[2][3];
    (*state)[2][3] = (*state)[1][3];
    (*state)[1][3] = t;
}

static uint8_t aes_xtime(uint8_t x) {
    return (uint8_t)((x << 1) ^ (((x >> 7) & 1) * 0x1b));
}

static void aes_mix_columns(aes_state_t *state) {
    unsigned i;
    for (i = 0; i < 4; ++i) {
        uint8_t t0  = (*state)[i][0];
        uint8_t all = (*state)[i][0] ^ (*state)[i][1]
                    ^ (*state)[i][2] ^ (*state)[i][3];
        uint8_t tm;
        tm = (*state)[i][0] ^ (*state)[i][1]; tm = aes_xtime(tm);
        (*state)[i][0] ^= tm ^ all;
        tm = (*state)[i][1] ^ (*state)[i][2]; tm = aes_xtime(tm);
        (*state)[i][1] ^= tm ^ all;
        tm = (*state)[i][2] ^ (*state)[i][3]; tm = aes_xtime(tm);
        (*state)[i][2] ^= tm ^ all;
        tm = (*state)[i][3] ^ t0;              tm = aes_xtime(tm);
        (*state)[i][3] ^= tm ^ all;
    }
}

static void aes_encrypt_block(aes_state_t *state, const uint8_t *rk) {
    unsigned round;

    aes_add_round_key(0, state, rk);
    for (round = 1; round < AES_Nr; ++round) {
        aes_sub_bytes(state);
        aes_shift_rows(state);
        aes_mix_columns(state);
        aes_add_round_key(round, state, rk);
    }
    aes_sub_bytes(state);
    aes_shift_rows(state);
    aes_add_round_key(AES_Nr, state, rk);
}

/*
 * AES-256-CTR is symmetric: encryption and decryption are the same
 * operation.  buf is processed in place.
 */
static void aes256_ctr_xcrypt(const uint8_t *key, uint8_t *buf, size_t len) {
    uint8_t rk[AES_keyExpSize];
    uint8_t counter[AES_BLOCKLEN];
    uint8_t stream[AES_BLOCKLEN];
    size_t  i;
    int     bi, j;

    aes_key_expansion(rk, key);

    memset(counter, 0, AES_BLOCKLEN);
    counter[AES_BLOCKLEN - 1] = 1;   /* initial counter = 1 (big-endian) */

    for (i = 0, bi = AES_BLOCKLEN; i < len; ++i, ++bi) {
        if (bi == AES_BLOCKLEN) {
            memcpy(stream, counter, AES_BLOCKLEN);
            aes_encrypt_block((aes_state_t *)stream, rk);

            /* Increment the 128-bit big-endian counter. */
            for (j = AES_BLOCKLEN - 1; j >= 0; --j) {
                if (counter[j] != 0xFF) { counter[j]++; break; }
                counter[j] = 0;
            }
            bi = 0;
        }
        buf[i] ^= stream[bi];
    }
}

/* =====================================================================
 * Python-style encrypt / decrypt wrappers.
 * ===================================================================== */

/* Where k is 256-bit (32 bytes).
 * Returns a freshly malloc'd buffer of length *out_len; caller must free(). */
static uint8_t *encrypt(const uint8_t *k, const char *m, size_t *out_len) {
    size_t   len = strlen(m);
    uint8_t *out = (uint8_t *)malloc(len ? len : 1);
    if (!out) { *out_len = 0; return NULL; }
    memcpy(out, m, len);
    aes256_ctr_xcrypt(k, out, len);
    *out_len = len;
    return out;
}

/* Where k is 256-bit (32 bytes).
 * Returns a freshly malloc'd, NUL-terminated UTF-8 string; caller must free(). */
static char *decrypt(const uint8_t *k, const uint8_t *ct, size_t ct_len) {
    char *out = (char *)malloc(ct_len + 1);
    if (!out) return NULL;
    memcpy(out, ct, ct_len);
    aes256_ctr_xcrypt(k, (uint8_t *)out, ct_len);
    out[ct_len] = '\0';
    return out;
}

/* =====================================================================
 * test()   (mirrors the Python test())
 * ===================================================================== */

static void round_trip_test(void) {
    const uint8_t *k = (const uint8_t *)"This_key_for_demo_purposes_only!";
    const char    *m = "Text may be any length you wish, no padding is required";
    size_t   ct_len;
    uint8_t *ct = encrypt(k, m, &ct_len);
    char    *pt = decrypt(k, ct, ct_len);
    printf("%s\n", pt);
    free(ct);
    free(pt);
}

static void test(void) {
    round_trip_test();
}

int main(void) {
    test();
    return 0;
}