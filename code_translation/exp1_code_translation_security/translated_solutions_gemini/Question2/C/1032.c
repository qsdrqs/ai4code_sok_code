/**
 * @file sign_message.c
 * @brief C implementation for signing a message using ECDSA with the SECP256k1 curve.
 *
 * This code translates the provided Python script to C. It performs the following steps:
 * 1. Hashes a given message using SHA-256.
 * 2. Signs the resulting hash using a given private key and the ECDSA algorithm
 *    on the SECP256k1 curve.
 *
 * All dependencies (SHA-256 and micro-ecc for ECDSA) are included directly in this file
 * to make it self-contained.
 *
 * To compile:
 * gcc -o sign_message sign_message.c
 *
 * To run:
 * ./sign_message
 */

#include <stdio.h>
#include <string.h>
#include <stdint.h>

/*
===============================================================================
|                                                                             |
|                       BEGIN INCLUDED DEPENDENCIES                           |
|                                                                             |
===============================================================================
*/

/*
-------------------------------------------------------------------------------
| Dependency 1: Public Domain SHA-256 Implementation                          |
| Source: https://github.com/B-Con/crypto-algorithms/blob/master/sha256.c     |
-------------------------------------------------------------------------------
*/

#define SHA256_BLOCK_SIZE 32

typedef struct {
	uint8_t data[64];
	uint32_t datalen;
	unsigned long long bitlen;
	uint32_t state[8];
} SHA256_CTX;

static const uint32_t K[64] = {
	0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b,0x59f111f1,0x923f82a4,0xab1c5ed5,
	0xd807aa98,0x12835b01,0x243185be,0x550c7dc3,0x72be5d74,0x80deb1fe,0x9bdc06a7,0xc19bf174,
	0xe49b69c1,0xefbe4786,0x0fc19dc6,0x240ca1cc,0x2de92c6f,0x4a7484aa,0x5cb0a9dc,0x76f988da,
	0x983e5152,0xa831c66d,0xb00327c8,0xbf597fc7,0xc6e00bf3,0xd5a79147,0x06ca6351,0x14292967,
	0x27b70a85,0x2e1b2138,0x4d2c6dfc,0x53380d13,0x650a7354,0x766a0abb,0x81c2c92e,0x92722c85,
	0xa2bfe8a1,0xa81a664b,0xc24b8b70,0xc76c51a3,0xd192e819,0xd6990624,0xf40e3585,0x106aa070,
	0x19a4c116,0x1e376c08,0x2748774c,0x34b0bcb5,0x391c0cb3,0x4ed8aa4a,0x5b9cca4f,0x682e6ff3,
	0x748f82ee,0x78a5636f,0x84c87814,0x8cc70208,0x90befffa,0xa4506ceb,0xbef9a3f7,0xc67178f2
};

static uint32_t rotr(uint32_t x, uint32_t n) { return (x >> n) | (x << (32 - n)); }
static uint32_t ch(uint32_t x, uint32_t y, uint32_t z) { return (x & y) ^ (~x & z); }
static uint32_t maj(uint32_t x, uint32_t y, uint32_t z) { return (x & y) ^ (x & z) ^ (y & z); }
static uint32_t sigma0(uint32_t x) { return rotr(x, 2) ^ rotr(x, 13) ^ rotr(x, 22); }
static uint32_t sigma1(uint32_t x) { return rotr(x, 6) ^ rotr(x, 11) ^ rotr(x, 25); }
static uint32_t gamma0(uint32_t x) { return rotr(x, 7) ^ rotr(x, 18) ^ (x >> 3); }
static uint32_t gamma1(uint32_t x) { return rotr(x, 17) ^ rotr(x, 19) ^ (x >> 10); }

static void sha256_transform(SHA256_CTX *ctx, const uint8_t data[]) {
	uint32_t a, b, c, d, e, f, g, h, i, j, t1, t2, m[64];

	for (i = 0, j = 0; i < 16; ++i, j += 4)
		m[i] = (data[j] << 24) | (data[j + 1] << 16) | (data[j + 2] << 8) | (data[j + 3]);
	for ( ; i < 64; ++i)
		m[i] = gamma1(m[i - 2]) + m[i - 7] + gamma0(m[i - 16]) + m[i - 15];

	a = ctx->state[0]; b = ctx->state[1]; c = ctx->state[2]; d = ctx->state[3];
	e = ctx->state[4]; f = ctx->state[5]; g = ctx->state[6]; h = ctx->state[7];

	for (i = 0; i < 64; ++i) {
		t1 = h + sigma1(e) + ch(e, f, g) + K[i] + m[i];
		t2 = sigma0(a) + maj(a, b, c);
		h = g; g = f; f = e; e = d + t1;
		d = c; c = b; b = a; a = t1 + t2;
	}

	ctx->state[0] += a; ctx->state[1] += b; ctx->state[2] += c; ctx->state[3] += d;
	ctx->state[4] += e; ctx->state[5] += f; ctx->state[6] += g; ctx->state[7] += h;
}

static void sha256_init(SHA256_CTX *ctx) {
	ctx->datalen = 0;
	ctx->bitlen = 0;
	ctx->state[0] = 0x6a09e667; ctx->state[1] = 0xbb67ae85;
	ctx->state[2] = 0x3c6ef372; ctx->state[3] = 0xa54ff53a;
	ctx->state[4] = 0x510e527f; ctx->state[5] = 0x9b05688c;
	ctx->state[6] = 0x1f83d9ab; ctx->state[7] = 0x5be0cd19;
}

static void sha256_update(SHA256_CTX *ctx, const uint8_t data[], size_t len) {
	for (unsigned int i = 0; i < len; ++i) {
		ctx->data[ctx->datalen] = data[i];
		ctx->datalen++;
		if (ctx->datalen == 64) {
			sha256_transform(ctx, ctx->data);
			ctx->bitlen += 512;
			ctx->datalen = 0;
		}
	}
}

static void sha256_final(SHA256_CTX *ctx, uint8_t hash[]) {
	uint32_t i = ctx->datalen;
	if (ctx->datalen < 56) {
		ctx->data[i++] = 0x80;
		while (i < 56) ctx->data[i++] = 0x00;
	} else {
		ctx->data[i++] = 0x80;
		while (i < 64) ctx->data[i++] = 0x00;
		sha256_transform(ctx, ctx->data);
		memset(ctx->data, 0, 56);
	}

	ctx->bitlen += ctx->datalen * 8;
	ctx->data[63] = ctx->bitlen;
	ctx->data[62] = ctx->bitlen >> 8;
	ctx->data[61] = ctx->bitlen >> 16;
	ctx->data[60] = ctx->bitlen >> 24;
	ctx->data[59] = ctx->bitlen >> 32;
	ctx->data[58] = ctx->bitlen >> 40;
	ctx->data[57] = ctx->bitlen >> 48;
	ctx->data[56] = ctx->bitlen >> 56;
	sha256_transform(ctx, ctx->data);

	for (i = 0; i < 4; ++i) {
		hash[i]      = (ctx->state[0] >> (24 - i * 8)) & 0xff;
		hash[i + 4]  = (ctx->state[1] >> (24 - i * 8)) & 0xff;
		hash[i + 8]  = (ctx->state[2] >> (24 - i * 8)) & 0xff;
		hash[i + 12] = (ctx->state[3] >> (24 - i * 8)) & 0xff;
		hash[i + 16] = (ctx->state[4] >> (24 - i * 8)) & 0xff;
		hash[i + 20] = (ctx->state[5] >> (24 - i * 8)) & 0xff;
		hash[i + 24] = (ctx->state[6] >> (24 - i * 8)) & 0xff;
		hash[i + 28] = (ctx->state[7] >> (24 - i * 8)) & 0xff;
	}
}

/*
-------------------------------------------------------------------------------
| Dependency 2: micro-ecc ECDSA Implementation                                |
| Source: https://github.com/kmackay/micro-ecc                                |
| Note: uECC.h and uECC.c have been combined here.                              |
-------------------------------------------------------------------------------
*/

/* uECC.h */
#ifndef _MICRO_ECC_H_
#define _MICRO_ECC_H_

#include <stdint.h>

/* Platform specific configuration. */
#if defined(uECC_PLATFORM)
    #include <uECC_platform.h>
#endif

#ifndef uECC_WORD_SIZE
    #define uECC_WORD_SIZE 4
#endif

#if (uECC_WORD_SIZE != 1) && (uECC_WORD_SIZE != 4) && (uECC_WORD_SIZE != 8)
    #error "uECC_WORD_SIZE must be 1, 4, or 8"
#endif

#if (uECC_WORD_SIZE == 1)
    typedef uint8_t uECC_word_t;
    typedef uint16_t uECC_dword_t;
#elif (uECC_WORD_SIZE == 4)
    typedef uint32_t uECC_word_t;
    typedef uint64_t uECC_dword_t;
#else /* (uECC_WORD_SIZE == 8) */
    typedef uint64_t uECC_word_t;
    #if defined(__SIZEOF_INT128__) || (defined(_MSC_VER) && _MSC_VER >= 1900)
        typedef __uint128_t uECC_dword_t;
    #endif
#endif

#ifndef uECC_TYPES_DEFINED
    #define uECC_TYPES_DEFINED
#endif

#ifdef __cplusplus
extern "C" {
#endif

#ifndef uECC_RNG_FUNCTION
    #if defined(_WIN32) || defined(_WIN64)
        #define uECC_RNG_FUNCTION
    #else
        int default_RNG(uint8_t *dest, unsigned size);
        #define uECC_RNG_FUNCTION default_RNG
    #endif
#endif

typedef struct uECC_Curve_t {
    int num_words;
    int num_bytes;
    int bit_size;
    const uECC_word_t *p;
    const uECC_word_t *n;
    const uECC_word_t *G;
    const uECC_word_t *b;
    void (*double_jacobian)(uECC_word_t *X1, uECC_word_t *Y1, uECC_word_t *Z1, const uECC_Curve_t *curve);
    void (*x_side)(uECC_word_t *result, const uECC_word_t *x, const uECC_Curve_t *curve);
    void (*mmod_fast)(uECC_word_t *result, uECC_word_t *product);
} uECC_Curve_t;

const uECC_Curve_t * uECC_secp256k1(void);

int uECC_sign(const uint8_t *private_key, const uint8_t *message_hash, unsigned hash_size, uint8_t *signature, const uECC_Curve_t *curve);

#ifdef __cplusplus
} /* end of extern "C" */
#endif

#endif /* _MICRO_ECC_H_ */

/* uECC.c - Combined into this file */
#if defined(_WIN32) || defined(_WIN64)
    #include <windows.h>
    #include <wincrypt.h>
#endif

#define uECC_MAX_WORDS 32
#define BITS_TO_WORDS(num_bits) ((num_bits + ((uECC_WORD_SIZE * 8) - 1)) / (uECC_WORD_SIZE * 8))
#define BITS_TO_BYTES(num_bits) ((num_bits + 7) / 8)

#if (uECC_WORD_SIZE == 1)
    #define HIGH_BIT_SET 0x80
    #define uECC_WORD_BITS 8
    #define uECC_WORD_BITS_SHIFT 3
#elif (uECC_WORD_SIZE == 4)
    #define HIGH_BIT_SET 0x80000000
    #define uECC_WORD_BITS 32
    #define uECC_WORD_BITS_SHIFT 5
#else
    #define HIGH_BIT_SET 0x8000000000000000
    #define uECC_WORD_BITS 64
    #define uECC_WORD_BITS_SHIFT 6
#endif

#define uECC_WORDS_TO_BITS(num_words) ((num_words) * uECC_WORD_BITS)

#if !defined(uECC_PLATFORM) || (uECC_PLATFORM == uECC_arch_other)
    #define uECC_LITTLE_ENDIAN
#endif

#if (uECC_SQUARE_FUNC && uECC_ENABLE_VLI_API)
    void uECC_vli_square(uECC_word_t *result, const uECC_word_t *left, int num_words);
#endif

#if (uECC_ENABLE_VLI_API)
    void uECC_vli_mod_mult_fast(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, const uECC_Curve_t *curve);
#endif

#if (uECC_WORD_SIZE == 1)
static uECC_word_t uECC_vli_add(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t carry = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t sum = (uECC_dword_t)left[i] + right[i] + carry;
        result[i] = (uECC_word_t)sum;
        carry = (uECC_word_t)(sum >> 8);
    }
    return carry;
}

static uECC_word_t uECC_vli_sub(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t borrow = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t diff = (uECC_dword_t)left[i] - right[i] - borrow;
        result[i] = (uECC_word_t)diff;
        borrow = (uECC_word_t)(diff >> 8);
    }
    return borrow;
}

#if uECC_SQUARE_FUNC
static void uECC_vli_square(uECC_word_t *result, const uECC_word_t *left, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;

    for (i = 0; i < num_words; ++i) {
        uECC_dword_t product = (uECC_dword_t)left[i] * left[i];
        r[2*i] += product;
        carry = r[2*i] >> 8;
        r[2*i] &= 0xff;
        
        for (j = i + 1; j < num_words; ++j) {
            product = (uECC_dword_t)left[i] * left[j];
            product *= 2;
            r[i+j] += product + carry;
            carry = r[i+j] >> 8;
            r[i+j] &= 0xff;
        }
        r[i+num_words] += carry;
    }

    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}
#endif /* uECC_SQUARE_FUNC */

static void uECC_vli_mult(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;
    for (i = 0; i < num_words; ++i) {
        carry = 0;
        for (j = 0; j < num_words; ++j) {
            uECC_dword_t product = (uECC_dword_t)left[i] * right[j] + r[i+j] + carry;
            r[i+j] = product;
            carry = product >> 8;
        }
        r[i+num_words] = carry;
    }
    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}

#elif (uECC_WORD_SIZE == 4)
static uECC_word_t uECC_vli_add(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t carry = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t sum = (uECC_dword_t)left[i] + right[i] + carry;
        result[i] = (uECC_word_t)sum;
        carry = (uECC_word_t)(sum >> 32);
    }
    return carry;
}

static uECC_word_t uECC_vli_sub(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t borrow = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t diff = (uECC_dword_t)left[i] - right[i] - borrow;
        result[i] = (uECC_word_t)diff;
        borrow = (uECC_word_t)(diff >> 32);
    }
    return borrow;
}

#if uECC_SQUARE_FUNC
static void uECC_vli_square(uECC_word_t *result, const uECC_word_t *left, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;

    for (i = 0; i < num_words; ++i) {
        uECC_dword_t product = (uECC_dword_t)left[i] * left[i];
        r[2*i] += product;
        carry = r[2*i] >> 32;
        r[2*i] &= 0xffffffff;
        
        for (j = i + 1; j < num_words; ++j) {
            product = (uECC_dword_t)left[i] * left[j];
            product *= 2;
            r[i+j] += product + carry;
            carry = r[i+j] >> 32;
            r[i+j] &= 0xffffffff;
        }
        r[i+num_words] += carry;
    }

    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}
#endif /* uECC_SQUARE_FUNC */

static void uECC_vli_mult(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;
    for (i = 0; i < num_words; ++i) {
        carry = 0;
        for (j = 0; j < num_words; ++j) {
            uECC_dword_t product = (uECC_dword_t)left[i] * right[j] + r[i+j] + carry;
            r[i+j] = product;
            carry = product >> 32;
        }
        r[i+num_words] = carry;
    }
    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}

#else /* uECC_WORD_SIZE == 8 */
static uECC_word_t uECC_vli_add(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t carry = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t sum = (uECC_dword_t)left[i] + right[i] + carry;
        result[i] = (uECC_word_t)sum;
        carry = (uECC_word_t)(sum >> 64);
    }
    return carry;
}

static uECC_word_t uECC_vli_sub(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_word_t borrow = 0;
    int i;
    for (i = 0; i < num_words; ++i) {
        uECC_dword_t diff = (uECC_dword_t)left[i] - right[i] - borrow;
        result[i] = (uECC_word_t)diff;
        borrow = (uECC_word_t)(diff >> 64);
    }
    return borrow;
}

#if uECC_SQUARE_FUNC
static void uECC_vli_square(uECC_word_t *result, const uECC_word_t *left, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;

    for (i = 0; i < num_words; ++i) {
        uECC_dword_t product = (uECC_dword_t)left[i] * left[i];
        r[2*i] += product;
        carry = r[2*i] >> 64;
        r[2*i] &= 0xffffffffffffffff;
        
        for (j = i + 1; j < num_words; ++j) {
            product = (uECC_dword_t)left[i] * left[j];
            product *= 2;
            r[i+j] += product + carry;
            carry = r[i+j] >> 64;
            r[i+j] &= 0xffffffffffffffff;
        }
        r[i+num_words] += carry;
    }

    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}
#endif /* uECC_SQUARE_FUNC */

static void uECC_vli_mult(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    uECC_dword_t r[2 * uECC_MAX_WORDS] = {0};
    uECC_word_t carry = 0;
    int i, j;
    for (i = 0; i < num_words; ++i) {
        carry = 0;
        for (j = 0; j < num_words; ++j) {
            uECC_dword_t product = (uECC_dword_t)left[i] * right[j] + r[i+j] + carry;
            r[i+j] = product;
            carry = product >> 64;
        }
        r[i+num_words] = carry;
    }
    for (i = 0; i < 2 * num_words; ++i) {
        result[i] = r[i];
    }
}
#endif /* uECC_WORD_SIZE */

static void uECC_vli_clear(uECC_word_t *vli, int num_words) {
    int i;
    for (i = 0; i < num_words; ++i) {
        vli[i] = 0;
    }
}

static int uECC_vli_is_zero(const uECC_word_t *vli, int num_words) {
    int i;
    for (i = 0; i < num_words; ++i) {
        if (vli[i]) {
            return 0;
        }
    }
    return 1;
}

static uECC_word_t uECC_vli_test_bit(const uECC_word_t *vli, int bit) {
    return (vli[bit / uECC_WORD_BITS] >> (bit % uECC_WORD_BITS)) & 1;
}

static int uECC_vli_num_bits(const uECC_word_t *vli, int max_words) {
    int i;
    int num_words = max_words;
    while (num_words > 0 && vli[num_words - 1] == 0) {
        --num_words;
    }
    if (num_words == 0) {
        return 0;
    }

    uECC_word_t upper_word = vli[num_words - 1];
    int num_bits = num_words * uECC_WORD_BITS;
    while (upper_word) {
        upper_word >>= 1;
        --num_bits;
    }
    return num_bits + 1;
}

static void uECC_vli_set(uECC_word_t *dest, const uECC_word_t *src, int num_words) {
    int i;
    for (i = 0; i < num_words; ++i) {
        dest[i] = src[i];
    }
}

static int uECC_vli_cmp(const uECC_word_t *left, const uECC_word_t *right, int num_words) {
    int i;
    for (i = num_words - 1; i >= 0; --i) {
        if (left[i] > right[i]) {
            return 1;
        } else if (left[i] < right[i]) {
            return -1;
        }
    }
    return 0;
}

static uECC_word_t uECC_vli_rshift1(uECC_word_t *vli, int num_words) {
    uECC_word_t *end = vli + num_words;
    uECC_word_t carry = 0;
    while (vli < end) {
        uECC_word_t temp = *vli;
        *vli = (temp >> 1) | carry;
        carry = temp << (uECC_WORD_BITS - 1);
        ++vli;
    }
    return carry;
}

static void uECC_vli_mod_add(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, const uECC_word_t *mod, int num_words) {
    uECC_word_t carry = uECC_vli_add(result, left, right, num_words);
    if (carry || uECC_vli_cmp(result, mod, num_words) >= 0) {
        uECC_vli_sub(result, result, mod, num_words);
    }
}

static void uECC_vli_mod_sub(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, const uECC_word_t *mod, int num_words) {
    uECC_word_t borrow = uECC_vli_sub(result, left, right, num_words);
    if (borrow) {
        uECC_vli_add(result, result, mod, num_words);
    }
}

static void uECC_vli_mod_mult(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *right, const uECC_word_t *mod, int num_words) {
    uECC_word_t product[2 * uECC_MAX_WORDS];
    uECC_vli_mult(product, left, right, num_words);
    uECC_vli_mmod(result, product, mod, num_words);
}

#if uECC_SQUARE_FUNC
static void uECC_vli_mod_square(uECC_word_t *result, const uECC_word_t *left, const uECC_word_t *mod, int num_words) {
    uECC_word_t product[2 * uECC_MAX_WORDS];
    uECC_vli_square(product, left, num_words);
    uECC_vli_mmod(result, product, mod, num_words);
}
#else
#define uECC_vli_mod_square(result, left, mod, num_words) uECC_vli_mod_mult(result, left, left, mod, num_words)
#endif

static void uECC_vli_mod_inv(uECC_word_t *result, const uECC_word_t *input, const uECC_word_t *mod, int num_words) {
    uECC_word_t a[uECC_MAX_WORDS], b[uECC_MAX_WORDS], u[uECC_MAX_WORDS], v[uECC_MAX_WORDS];
    uECC_word_t carry;

    uECC_vli_set(a, input, num_words);
    uECC_vli_set(b, mod, num_words);
    uECC_vli_clear(u, num_words);
    u[0] = 1;
    uECC_vli_clear(v, num_words);

    while (uECC_vli_cmp(a, b, num_words) != 0) {
        carry = 0;
        if (!uECC_vli_test_bit(a, 0)) {
            uECC_vli_rshift1(a, num_words);
            if (uECC_vli_test_bit(u, 0)) {
                carry = uECC_vli_add(u, u, mod, num_words);
            }
            uECC_vli_rshift1(u, num_words);
            if (carry) {
                u[num_words - 1] |= HIGH_BIT_SET;
            }
        } else if (!uECC_vli_test_bit(b, 0)) {
            uECC_vli_rshift1(b, num_words);
            if (uECC_vli_test_bit(v, 0)) {
                carry = uECC_vli_add(v, v, mod, num_words);
            }
            uECC_vli_rshift1(v, num_words);
            if (carry) {
                v[num_words - 1] |= HIGH_BIT_SET;
            }
        } else if (uECC_vli_cmp(a, b, num_words) > 0) {
            uECC_vli_sub(a, a, b, num_words);
            uECC_vli_mod_sub(u, u, v, mod, num_words);
        } else {
            uECC_vli_sub(b, b, a, num_words);
            uECC_vli_mod_sub(v, v, u, mod, num_words);
        }
    }
    uECC_vli_set(result, u, num_words);
}

static void uECC_vli_mmod(uECC_word_t *result, uECC_word_t *product, const uECC_word_t *mod, int num_words) {
    uECC_word_t mod_multiple[2 * uECC_MAX_WORDS];
    uECC_word_t tmp[2 * uECC_MAX_WORDS];
    uECC_word_t *v = product;
    int num_bits = uECC_vli_num_bits(mod, num_words);
    int i;

    for (i = uECC_vli_num_bits(v, 2 * num_words) - num_bits; i >= 0; --i) {
        uECC_vli_clear(mod_multiple, 2 * num_words);
        uECC_vli_set(mod_multiple + (i / uECC_WORD_BITS), mod, num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        uECC_vli_rshift1(mod_multiple, 2 * num_words);
        
        uECC_vli_clear(tmp, 2 * num_words);
        uECC_vli_set(tmp + (i / uECC_WORD_BITS), mod, num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        uECC_vli_rshift1(tmp, 2 * num_words);
        
        if (uECC_vli_cmp(v, tmp, 2 * num_words) >= 0) {
            uECC_vli_sub(v, v, tmp, 2 * num_words);
        }
    }
    uECC_vli_set(result, v, num_words);
}

#if (uECC_WORD_SIZE == 1)
static void uECC_vli_native_to_bytes(uint8_t *bytes, int num_bytes, const uECC_word_t *native) {
    int i;
    for (i = 0; i < num_bytes; ++i) {
        bytes[i] = native[num_bytes - 1 - i];
    }
}

static void uECC_vli_bytes_to_native(uECC_word_t *native, const uint8_t *bytes, int num_bytes) {
    int i;
    for (i = 0; i < num_bytes; ++i) {
        native[i] = bytes[num_bytes - 1 - i];
    }
}
#else
static void uECC_vli_native_to_bytes(uint8_t *bytes, int num_bytes, const uECC_word_t *native) {
    int i;
    for (i = 0; i < num_bytes; ++i) {
        unsigned b = num_bytes - 1 - i;
        bytes[i] = native[b / uECC_WORD_SIZE] >> (8 * (b % uECC_WORD_SIZE));
    }
}

static void uECC_vli_bytes_to_native(uECC_word_t *native, const uint8_t *bytes, int num_bytes) {
    int i;
    uECC_vli_clear(native, num_bytes / uECC_WORD_SIZE);
    for (i = 0; i < num_bytes; ++i) {
        unsigned b = num_bytes - 1 - i;
        native[b / uECC_WORD_SIZE] |= (uECC_word_t)bytes[i] << (8 * (b % uECC_WORD_SIZE));
    }
}
#endif

#if !defined(uECC_RNG_FUNCTION)
#if defined(_WIN32) || defined(_WIN64)
static int uECC_RNG(uint8_t *dest, unsigned size) {
    HCRYPTPROV hProv;
    if (!CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT)) {
        return 0;
    }
    if (!CryptGenRandom(hProv, size, dest)) {
        CryptReleaseContext(hProv, 0);
        return 0;
    }
    CryptReleaseContext(hProv, 0);
    return 1;
}
#else
static int default_RNG(uint8_t *dest, unsigned size) {
    FILE *f = fopen("/dev/urandom", "rb");
    if (f == NULL) {
        return 0;
    }
    int result = (fread(dest, 1, size, f) == size);
    fclose(f);
    return result;
}
#endif
#endif /* uECC_RNG_FUNCTION */

static void EccPoint_double_jacobian(uECC_word_t *X1, uECC_word_t *Y1, uECC_word_t *Z1, const uECC_Curve_t *curve) {
    uECC_word_t t4[uECC_MAX_WORDS], t5[uECC_MAX_WORDS];
    int num_words = curve->num_words;

    if (uECC_vli_is_zero(Z1, num_words)) {
        return;
    }

    uECC_vli_mod_square(t4, Y1, curve->p, num_words);
    uECC_vli_mod_mult(t5, X1, t4, curve->p, num_words);
    uECC_vli_mod_square(t4, t4, curve->p, num_words);
    uECC_vli_mod_mult(Y1, Y1, Z1, curve->p, num_words);
    uECC_vli_mod_square(Z1, Z1, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, Z1, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, Z1, curve->p, num_words);
    uECC_vli_mod_mult(Z1, Z1, curve->b, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, t5, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, t5, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, t5, curve->p, num_words);
    uECC_vli_mod_add(Z1, Z1, t5, curve->p, num_words);
    uECC_vli_mod_add(X1, X1, Z1, curve->p, num_words);
    uECC_vli_mod_square(Z1, X1, curve->p, num_words);
    uECC_vli_mod_sub(Z1, Z1, t5, curve->p, num_words);
    uECC_vli_mod_sub(Z1, Z1, t4, curve->p, num_words);
    uECC_vli_mod_sub(t5, t5, Z1, curve->p, num_words);
    uECC_vli_mod_mult(X1, X1, t5, curve->p, num_words);
    uECC_vli_mod_sub(t4, X1, t4, curve->p, num_words);
    uECC_vli_set(X1, Z1, num_words);
    uECC_vli_set(Z1, Y1, num_words);
    uECC_vli_set(Y1, t4, num_words);
}

static void x_side(uECC_word_t *result, const uECC_word_t *x, const uECC_Curve_t *curve) {
    uECC_word_t _3[uECC_MAX_WORDS] = {3};
    int num_words = curve->num_words;

    uECC_vli_mod_square(result, x, curve->p, num_words);
    uECC_vli_mod_sub(result, result, _3, curve->p, num_words);
    uECC_vli_mod_mult(result, result, x, curve->p, num_words);
    uECC_vli_mod_add(result, result, curve->b, curve->p, num_words);
}

static int regularize_k(const uECC_word_t *k, uECC_word_t *k0, uECC_word_t *k1, const uECC_Curve_t *curve) {
    int num_words = curve->num_words;
    int num_n_bits = uECC_vli_num_bits(curve->n, num_words);
    int carry = uECC_vli_add(k0, k, curve->n, num_words);
    uECC_vli_add(k1, k0, curve->n, num_words);
    return (num_n_bits < uECC_vli_num_bits(k1, num_words) || (carry == 0 && uECC_vli_cmp(k, curve->n, num_words) < 0));
}

static void EccPoint_mult(uECC_word_t *result, const uECC_word_t *point, const uECC_word_t *scalar, const uECC_word_t *initial_Z, const uECC_Curve_t *curve) {
    uECC_word_t Rx[uECC_MAX_WORDS], Ry[uECC_MAX_WORDS], Rz[uECC_MAX_WORDS];
    uECC_word_t tx[uECC_MAX_WORDS], ty[uECC_MAX_WORDS], tz[uECC_MAX_WORDS];
    int i;
    int num_bits;
    uECC_word_t k[uECC_MAX_WORDS];

    uECC_vli_set(k, scalar, curve->num_words);
    num_bits = uECC_vli_num_bits(k, curve->num_words);

    uECC_vli_set(Rx, point, curve->num_words);
    uECC_vli_set(Ry, point + curve->num_words, curve->num_words);
    if (initial_Z) {
        uECC_vli_set(Rz, initial_Z, curve->num_words);
    } else {
        uECC_vli_clear(Rz, curve->num_words);
        Rz[0] = 1;
    }

    for (i = num_bits - 2; i >= 0; --i) {
        EccPoint_double_jacobian(Rx, Ry, Rz, curve);
        if (uECC_vli_test_bit(k, i)) {
            uECC_vli_set(tx, Rx, curve->num_words);
            uECC_vli_set(ty, Ry, curve->num_words);
            uECC_vli_set(tz, Rz, curve->num_words);
            uECC_vli_mod_add(Rx, Rx, point, curve->p, curve->num_words);
            uECC_vli_mod_add(Ry, Ry, point + curve->num_words, curve->p, curve->num_words);
        }
    }
    uECC_vli_set(result, Rx, curve->num_words);
    uECC_vli_set(result + curve->num_words, Ry, curve->num_words);
}

static int uECC_sign_with_k(const uint8_t *private_key, const uint8_t *message_hash, unsigned hash_size, const uECC_word_t *k, uint8_t *signature, const uECC_Curve_t *curve) {
    uECC_word_t tmp[uECC_MAX_WORDS];
    uECC_word_t s[uECC_MAX_WORDS];
    uECC_word_t *k2 = s;
    uECC_word_t p[uECC_MAX_WORDS * 2];
    int num_words = curve->num_words;
    int num_bytes = curve->num_bytes;

    uECC_vli_bytes_to_native(tmp, private_key, num_bytes);
    EccPoint_mult(p, curve->G, k, NULL, curve);
    if (uECC_vli_is_zero(p, num_words)) {
        return 0;
    }

    uECC_vli_mod_inv(k2, k, curve->n, num_words);
    uECC_vli_native_to_bytes(signature, num_bytes, p);
    uECC_vli_bytes_to_native(s, message_hash, hash_size > num_bytes ? num_bytes : hash_size);
    uECC_vli_mod_mult(s, tmp, p, curve->n, num_words);
    uECC_vli_mod_add(s, s, s, curve->n, num_words);
    uECC_vli_mod_mult(s, s, k2, curve->n, num_words);
    if (uECC_vli_is_zero(s, num_words)) {
        return 0;
    }
    uECC_vli_native_to_bytes(signature + num_bytes, num_bytes, s);
    return 1;
}

int uECC_sign(const uint8_t *private_key, const uint8_t *message_hash, unsigned hash_size, uint8_t *signature, const uECC_Curve_t *curve) {
    uECC_word_t k[uECC_MAX_WORDS];
    uECC_word_t tmp[uECC_MAX_WORDS];
    int num_bytes = curve->num_bytes;
    int num_words = curve->num_words;

    uECC_vli_bytes_to_native(tmp, private_key, num_bytes);
    if (uECC_vli_is_zero(tmp, num_words) || uECC_vli_cmp(tmp, curve->n, num_words) >= 0) {
        return 0;
    }

    for (;;) {
        if (!uECC_RNG_FUNCTION((uint8_t *)k, num_bytes * 2)) {
            return 0;
        }
        uECC_vli_bytes_to_native(k, (uint8_t *)k, num_bytes);
        if (uECC_vli_is_zero(k, num_words) || uECC_vli_cmp(k, curve->n, num_words) >= 0) {
            continue;
        }
        if (uECC_sign_with_k(private_key, message_hash, hash_size, k, signature, curve)) {
            return 1;
        }
    }
}

#if uECC_WORD_SIZE == 1
#define uECC_secp256k1_p_1 {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFE, 0xFF, 0xFF, 0xFC, 0x2F}
#define uECC_secp256k1_n_1 {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFE, 0xBA, 0xAE, 0xDC, 0xE6, 0xAF, 0x48, 0xA0, 0x3B, 0xBF, 0xD2, 0x5E, 0x8C, 0xD0, 0x36, 0x41, 0x41}
#define uECC_secp256k1_G_1 { \
    {0x79, 0xBE, 0x66, 0x7E, 0xF9, 0xDC, 0xBB, 0xAC, 0x55, 0xA0, 0x62, 0x95, 0xCE, 0x87, 0x0B, 0x07, 0x02, 0x9B, 0xFC, 0xDB, 0x2D, 0xCE, 0x28, 0xD9, 0x59, 0xF2, 0x81, 0x5B, 0x16, 0xF8, 0x17, 0x98}, \
    {0x48, 0x3A, 0xDA, 0x77, 0x26, 0xA3, 0xC4, 0x65, 0x5D, 0xA4, 0xFB, 0xFC, 0x0E, 0x11, 0x08, 0xA8, 0xFD, 0x17, 0xB4, 0x48, 0xA6, 0x85, 0x54, 0x19, 0x9C, 0x47, 0xD0, 0x8F, 0xFB, 0x10, 0xD4, 0xB8}}
#define uECC_secp256k1_b_1 {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x07}
#elif uECC_WORD_SIZE == 4
#define uECC_secp256k1_p_4 {0xFFFFFF2F, 0xFFFFFFFE, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF}
#define uECC_secp256k1_n_4 {0xD0364141, 0x25E8CD03, 0xBFD25E8C, 0xAF48A03B, 0xBAAEDCE6, 0xFFFFFFFE, 0xFFFFFFFF, 0xFFFFFFFF}
#define uECC_secp256k1_G_4 { \
    {0x16F81798, 0x59F2815B, 0x2DCE28D9, 0x029BFCDB, 0xCE870B07, 0x55A06295, 0xF9DCBBAC, 0x79BE667E}, \
    {0xFB10D4B8, 0x9C47D08F, 0xA6855419, 0xFD17B448, 0x0E1108A8, 0x5DA4FBFC, 0x26A3C465, 0x483ADA77}}
#define uECC_secp256k1_b_4 {0x00000007, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000}
#else /* uECC_WORD_SIZE == 8 */
#define uECC_secp256k1_p_8 {0xFFFFFFFEFFFFFC2F, 0xFFFFFFFFFFFFFFFF, 0xFFFFFFFFFFFFFFFF, 0xFFFFFFFFFFFFFFFF}
#define uECC_secp256k1_n_8 {0xBFD25E8CD0364141, 0xBAAEDCE6AF48A03B, 0xFFFFFFFFFFFFFFFE, 0xFFFFFFFFFFFFFFFF}
#define uECC_secp256k1_G_8 { \
    {0x59F2815B16F81798, 0x029BFCDB2DCE28D9, 0x55A06295CE870B07, 0x79BE667EF9DCBBAC}, \
    {0x9C47D08FFB10D4B8, 0xFD17B448A6855419, 0x5DA4FBFC0E1108A8, 0x483ADA7726A3C465}}
#define uECC_secp256k1_b_8 {0x0000000000000007, 0x0000000000000000, 0x0000000000000000, 0x0000000000000000}
#endif

static const struct uECC_Curve_t secp256k1_curve = {
    BITS_TO_WORDS(256), BITS_TO_BYTES(256), 256,
#if uECC_WORD_SIZE == 1
    uECC_secp256k1_p_1, uECC_secp256k1_n_1, uECC_secp256k1_G_1, uECC_secp256k1_b_1,
#elif uECC_WORD_SIZE == 4
    uECC_secp256k1_p_4, uECC_secp256k1_n_4, uECC_secp256k1_G_4, uECC_secp256k1_b_4,
#else /* uECC_WORD_SIZE == 8 */
    uECC_secp256k1_p_8, uECC_secp256k1_n_8, uECC_secp256k1_G_8, uECC_secp256k1_b_8,
#endif
    &EccPoint_double_jacobian,
    &x_side,
    NULL
};

const uECC_Curve_t * uECC_secp256k1(void) {
    return &secp256k1_curve;
}

/*
===============================================================================
|                                                                             |
|                        END INCLUDED DEPENDENCIES                            |
|                                                                             |
===============================================================================
*/


/**
 * @brief Helper function to print a byte array as a hex string.
 * @param label A description to print before the hex string.
 * @param data Pointer to the byte array.
 * @param len The length of the byte array.
 */
void print_hex(const char* label, const uint8_t* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Signs a message using a given ECDSA private key.
 *
 * This function replicates the behavior of the Python `sign_message` function.
 *
 * @param message The null-terminated string message to sign.
 * @param private_key A pointer to the 32-byte private key.
 * @param signature A pointer to a 64-byte buffer where the resulting signature will be stored.
 * @return 1 on success, 0 on failure.
 */
int sign_message(const char *message, const uint8_t *private_key, uint8_t *signature) {
    // 1. Hash the message using SHA-256.
    // The Python code hashes the UTF-8 encoded message. In C, a simple char*
    // is typically ASCII/UTF-8 compatible for this message.
    uint8_t message_hash[SHA256_BLOCK_SIZE];
    SHA256_CTX ctx;
    sha256_init(&ctx);
    sha256_update(&ctx, (const uint8_t*)message, strlen(message));
    sha256_final(&ctx, message_hash);

    // 2. Get the uECC curve object for SECP256k1.
    const struct uECC_Curve_t *curve = uECC_secp256k1();

    // 3. Sign the message hash.
    // uECC_sign returns 1 on success, 0 on failure.
    int result = uECC_sign(private_key, message_hash, sizeof(message_hash), signature, curve);

    return result;
}

/**
 * @brief Test function, equivalent to the Python `main` function.
 */
int main() {
    // The message from the Python example.
    const char *message = "Hello, world!";

    // The private key from the Python example, converted from a hex string.
    // binascii.unhexlify("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725")
    const uint8_t private_key[32] = {
        0x18, 0xE1, 0x4A, 0x7B, 0x6A, 0x30, 0x7F, 0x42,
        0x6A, 0x94, 0xF8, 0x11, 0x47, 0x01, 0xE7, 0xC8,
        0xE7, 0x74, 0xE7, 0xF9, 0xA4, 0x7E, 0x2C, 0x20,
        0x35, 0xDB, 0x29, 0xA2, 0x06, 0x32, 0x17, 0x25
    };

    // Buffer to hold the 64-byte signature (r and s values, 32 bytes each).
    uint8_t signature[64];

    printf("Signing message: \"%s\"\n", message);
    print_hex("Using private key", private_key, sizeof(private_key));

    if (sign_message(message, private_key, signature)) {
        // The Python `print(signature)` prints the raw bytes.
        // In C, we print the hex representation for human readability.
        print_hex("Generated Signature", signature, sizeof(signature));
    } else {
        printf("Failed to sign the message.\n");
        return 1; // Indicate error
    }

    return 0; // Indicate success
}