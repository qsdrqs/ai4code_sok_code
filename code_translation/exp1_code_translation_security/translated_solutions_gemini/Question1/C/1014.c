/*
 * =====================================================================================
 *
 *       Filename:  main.c
 *
 *    Description:  C translation of the Python AESCipher example.
 *                  This file is self-contained and includes all necessary dependencies.
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

// Platform-specific includes for generating random IV
#ifdef _WIN32
#include <windows.h>
#include <wincrypt.h>
#else
#include <fcntl.h>
#include <unistd.h>
#endif

/*
------------------------------------------------------------------------------------------
|                                                                                        |
|                                  DEPENDENCIES START                                    |
|                                                                                        |
------------------------------------------------------------------------------------------
*/

/*
 * ---------------------------------------------------------------------------
 * Dependency 1: tiny-aes-c (https://github.com/kokke/tiny-aes-c)
 * A small and portable AES implementation in C.
 * ---------------------------------------------------------------------------
 */

// #define the macros below to 1/0 to enable/disable the mode of operation.
//
// CBC enables AES encryption in CBC-mode of operation.
// CTR enables encryption in counter-mode.
// ECB enables the basic ECB 16-byte block algorithm. All can be enabled simultaneously.

// The #ifndef-guard allows it to be configured before #include'ing or pasting it.
#ifndef CBC
  #define CBC 1
#endif

#ifndef ECB
  #define ECB 1
#endif

#ifndef CTR
  #define CTR 1
#endif

#define AES128 1
//#define AES192 1
#define AES256 1

#define AES_BLOCKLEN 16 // Block length in bytes - AES is 128b block only

#if defined(AES256) && (AES256 == 1)
    #define AES_KEYLEN 32
    #define AES_keyExpSize 240
#elif defined(AES192) && (AES192 == 1)
    #define AES_KEYLEN 24
    #define AES_keyExpSize 208
#else
    #define AES_KEYLEN 16   // Key length in bytes
    #define AES_keyExpSize 176
#endif

struct AES_ctx
{
  uint8_t RoundKey[AES_keyExpSize];
#if (defined(CBC) && (CBC == 1)) || (defined(CTR) && (CTR == 1))
  uint8_t Iv[AES_BLOCKLEN];
#endif
};

void AES_init_ctx(struct AES_ctx* ctx, const uint8_t* key);
#if (defined(CBC) && (CBC == 1)) || (defined(CTR) && (CTR == 1))
void AES_init_ctx_iv(struct AES_ctx* ctx, const uint8_t* key, const uint8_t* iv);
void AES_ctx_set_iv(struct AES_ctx* ctx, const uint8_t* iv);
#endif

#if defined(ECB) && (ECB == 1)
// buffer size is exactly AES_BLOCKLEN bytes; 
// you need only AES_init_ctx as IV is not used in ECB
// NB: ECB is considered insecure for most uses
void AES_ECB_encrypt(const struct AES_ctx* ctx, uint8_t* buf);
void AES_ECB_decrypt(const struct AES_ctx* ctx, uint8_t* buf);
#endif

#if defined(CBC) && (CBC == 1)
// buffer size MUST be mutiple of AES_BLOCKLEN;
// Suggest https://en.wikipedia.org/wiki/Padding_(cryptography)#PKCS7 for padding scheme
// NOTES: you need to set IV in ctx via AES_init_ctx_iv() or AES_ctx_set_iv()
//        no IV should be transmitted in cleartext with CBC as it undermines security!
void AES_CBC_encrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, size_t length);
void AES_CBC_decrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, size_t length);
#endif

#if defined(CTR) && (CTR == 1)
// Same function for encrypting as for decrypting. 
// IV is incremented for every block, and used after encryption as XOR-key for plaintext.
// Suggesting https://en.wikipedia.org/wiki/Padding_(cryptography)#PKCS7 for padding scheme
// NOTES: you need to set IV in ctx with AES_init_ctx_iv() or AES_ctx_set_iv()
//        no IV should be transmitted in cleartext with CTR as it undermines security!
void AES_CTR_xcrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, size_t length);
#endif

// tiny-aes-c implementation
#define Nb 4
#if defined(AES256) && (AES256 == 1)
#define Nk 8
#define Nr 14
#elif defined(AES192) && (AES192 == 1)
#define Nk 6
#define Nr 12
#else
#define Nk 4
#define Nr 10
#endif

typedef uint8_t state_t[4][4];
static const uint8_t sbox[256] = {
  0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
  0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
  0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
  0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
  0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
  0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
  0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
  0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
  0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
  0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
  0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
  0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
  0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
  0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
  0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
  0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16 };
static const uint8_t rsbox[256] = {
  0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
  0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
  0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
  0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
  0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
  0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
  0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
  0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
  0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
  0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
  0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
  0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
  0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
  0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
  0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
  0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d };
static const uint8_t Rcon[11] = { 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36 };
#define getSBoxValue(num) (sbox[(num)])
static void KeyExpansion(uint8_t* RoundKey, const uint8_t* Key) {
  unsigned i, j, k;
  uint8_t tempa[4]; 
  for (i = 0; i < Nk; ++i) {
    RoundKey[(i * 4) + 0] = Key[(i * 4) + 0];
    RoundKey[(i * 4) + 1] = Key[(i * 4) + 1];
    RoundKey[(i * 4) + 2] = Key[(i * 4) + 2];
    RoundKey[(i * 4) + 3] = Key[(i * 4) + 3];
  }
  for (i = Nk; i < Nb * (Nr + 1); ++i) {
    {
      k = (i - 1) * 4;
      tempa[0]=RoundKey[k + 0];
      tempa[1]=RoundKey[k + 1];
      tempa[2]=RoundKey[k + 2];
      tempa[3]=RoundKey[k + 3];
    }
    if (i % Nk == 0) {
      {
        const uint8_t u8tmp = tempa[0];
        tempa[0] = tempa[1];
        tempa[1] = tempa[2];
        tempa[2] = tempa[3];
        tempa[3] = u8tmp;
      }
      {
        tempa[0] = getSBoxValue(tempa[0]);
        tempa[1] = getSBoxValue(tempa[1]);
        tempa[2] = getSBoxValue(tempa[2]);
        tempa[3] = getSBoxValue(tempa[3]);
      }
      tempa[0] = tempa[0] ^ Rcon[i/Nk];
    }
#if defined(AES256) && (AES256 == 1)
    if (i % Nk == 4) {
      {
        tempa[0] = getSBoxValue(tempa[0]);
        tempa[1] = getSBoxValue(tempa[1]);
        tempa[2] = getSBoxValue(tempa[2]);
        tempa[3] = getSBoxValue(tempa[3]);
      }
    }
#endif
    j = i * 4; k=(i - Nk) * 4;
    RoundKey[j + 0] = RoundKey[k + 0] ^ tempa[0];
    RoundKey[j + 1] = RoundKey[k + 1] ^ tempa[1];
    RoundKey[j + 2] = RoundKey[k + 2] ^ tempa[2];
    RoundKey[j + 3] = RoundKey[k + 3] ^ tempa[3];
  }
}
void AES_init_ctx(struct AES_ctx* ctx, const uint8_t* key) { KeyExpansion(ctx->RoundKey, key); }
#if (defined(CBC) && (CBC == 1)) || (defined(CTR) && (CTR == 1))
void AES_init_ctx_iv(struct AES_ctx* ctx, const uint8_t* key, const uint8_t* iv) {
  KeyExpansion(ctx->RoundKey, key);
  memcpy(ctx->Iv, iv, AES_BLOCKLEN);
}
void AES_ctx_set_iv(struct AES_ctx* ctx, const uint8_t* iv) { memcpy(ctx->Iv, iv, AES_BLOCKLEN); }
#endif
static void AddRoundKey(uint8_t round, state_t* state, const uint8_t* RoundKey) {
  uint8_t i,j;
  for (i = 0; i < 4; ++i) {
    for (j = 0; j < 4; ++j) {
      (*state)[i][j] ^= RoundKey[(round * Nb * 4) + (i * Nb) + j];
    }
  }
}
static void SubBytes(state_t* state) {
  uint8_t i, j;
  for (i = 0; i < 4; ++i) {
    for (j = 0; j < 4; ++j) {
      (*state)[j][i] = getSBoxValue((*state)[j][i]);
    }
  }
}
static void ShiftRows(state_t* state) {
  uint8_t temp;
  temp           = (*state)[0][1];
  (*state)[0][1] = (*state)[1][1];
  (*state)[1][1] = (*state)[2][1];
  (*state)[2][1] = (*state)[3][1];
  (*state)[3][1] = temp;
  temp           = (*state)[0][2];
  (*state)[0][2] = (*state)[2][2];
  (*state)[2][2] = temp;
  temp           = (*state)[1][2];
  (*state)[1][2] = (*state)[3][2];
  (*state)[3][2] = temp;
  temp           = (*state)[0][3];
  (*state)[0][3] = (*state)[3][3];
  (*state)[3][3] = (*state)[2][3];
  (*state)[2][3] = (*state)[1][3];
  (*state)[1][3] = temp;
}
static uint8_t xtime(uint8_t x) { return ((x<<1) ^ (((x>>7) & 1) * 0x1b)); }
static void MixColumns(state_t* state) {
  uint8_t i;
  uint8_t Tmp, Tm, t;
  for (i = 0; i < 4; ++i) {  
    t   = (*state)[i][0];
    Tmp = (*state)[i][0] ^ (*state)[i][1] ^ (*state)[i][2] ^ (*state)[i][3];
    Tm  = (*state)[i][0] ^ (*state)[i][1]; Tm = xtime(Tm); (*state)[i][0] ^= Tm ^ Tmp;
    Tm  = (*state)[i][1] ^ (*state)[i][2]; Tm = xtime(Tm); (*state)[i][1] ^= Tm ^ Tmp;
    Tm  = (*state)[i][2] ^ (*state)[i][3]; Tm = xtime(Tm); (*state)[i][2] ^= Tm ^ Tmp;
    Tm  = (*state)[i][3] ^ t;              Tm = xtime(Tm); (*state)[i][3] ^= Tm ^ Tmp;
  }
}
#define getSBoxInvert(num) (rsbox[(num)])
static void InvMixColumns(state_t* state) {
  int i;
  uint8_t a, b, c, d;
  for (i = 0; i < 4; ++i) { 
    a = (*state)[i][0];
    b = (*state)[i][1];
    c = (*state)[i][2];
    d = (*state)[i][3];
    (*state)[i][0] = xtime(xtime(xtime(xtime(a ^ c)))) ^ xtime(xtime(xtime(b ^ d))) ^ xtime(xtime(a ^ b)) ^ xtime(c ^ d) ^ b ^ c ^ d;
    (*state)[i][1] = xtime(xtime(xtime(xtime(b ^ d)))) ^ xtime(xtime(xtime(a ^ c))) ^ xtime(xtime(b ^ c)) ^ xtime(a ^ d) ^ a ^ c ^ d;
    (*state)[i][2] = xtime(xtime(xtime(xtime(a ^ c)))) ^ xtime(xtime(xtime(b ^ d))) ^ xtime(xtime(c ^ d)) ^ xtime(a ^ b) ^ a ^ b ^ d;
    (*state)[i][3] = xtime(xtime(xtime(xtime(b ^ d)))) ^ xtime(xtime(xtime(a ^ c))) ^ xtime(xtime(a ^ d)) ^ xtime(b ^ c) ^ a ^ b ^ c;
  }
}
static void InvSubBytes(state_t* state) {
  uint8_t i, j;
  for (i = 0; i < 4; ++i) {
    for (j = 0; j < 4; ++j) {
      (*state)[j][i] = getSBoxInvert((*state)[j][i]);
    }
  }
}
static void InvShiftRows(state_t* state) {
  uint8_t temp;
  temp = (*state)[3][1];
  (*state)[3][1] = (*state)[2][1];
  (*state)[2][1] = (*state)[1][1];
  (*state)[1][1] = (*state)[0][1];
  (*state)[0][1] = temp;
  temp = (*state)[0][2];
  (*state)[0][2] = (*state)[2][2];
  (*state)[2][2] = temp;
  temp = (*state)[1][2];
  (*state)[1][2] = (*state)[3][2];
  (*state)[3][2] = temp;
  temp = (*state)[0][3];
  (*state)[0][3] = (*state)[1][3];
  (*state)[1][3] = (*state)[2][3];
  (*state)[2][3] = (*state)[3][3];
  (*state)[3][3] = temp;
}
static void Cipher(state_t* state, const uint8_t* RoundKey) {
  uint8_t round = 0;
  AddRoundKey(0, state, RoundKey);
  for (round = 1; round < Nr; ++round) {
    SubBytes(state);
    ShiftRows(state);
    MixColumns(state);
    AddRoundKey(round, state, RoundKey);
  }
  SubBytes(state);
  ShiftRows(state);
  AddRoundKey(Nr, state, RoundKey);
}
static void InvCipher(state_t* state, const uint8_t* RoundKey) {
  uint8_t round = 0;
  AddRoundKey(Nr, state, RoundKey);
  for (round = (Nr - 1); round > 0; --round) {
    InvShiftRows(state);
    InvSubBytes(state);
    AddRoundKey(round, state, RoundKey);
    InvMixColumns(state);
  }
  InvShiftRows(state);
  InvSubBytes(state);
  AddRoundKey(0, state, RoundKey);
}
static void XorWithIv(uint8_t* buf, const uint8_t* Iv) {
  uint8_t i;
  for (i = 0; i < AES_BLOCKLEN; ++i) {
    buf[i] ^= Iv[i];
  }
}
#if defined(CBC) && (CBC == 1)
void AES_CBC_encrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, size_t length) {
  size_t i;
  uint8_t* Iv = ctx->Iv;
  for (i = 0; i < length; i += AES_BLOCKLEN) {
    XorWithIv(buf, Iv);
    Cipher((state_t*)buf, ctx->RoundKey);
    Iv = buf;
    buf += AES_BLOCKLEN;
  }
  memcpy(ctx->Iv, Iv, AES_BLOCKLEN);
}
void AES_CBC_decrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, size_t length) {
  size_t i;
  uint8_t storeNextIv[AES_BLOCKLEN];
  for (i = 0; i < length; i += AES_BLOCKLEN) {
    memcpy(storeNextIv, buf, AES_BLOCKLEN);
    InvCipher((state_t*)buf, ctx->RoundKey);
    XorWithIv(buf, ctx->Iv);
    memcpy(ctx->Iv, storeNextIv, AES_BLOCKLEN);
    buf += AES_BLOCKLEN;
  }
}
#endif

/*
 * ---------------------------------------------------------------------------
 * Dependency 2: SHA-256 (From https://github.com/B-Con/crypto-algorithms)
 * A public domain SHA-256 implementation.
 * ---------------------------------------------------------------------------
 */
#define SHA256_BLOCK_SIZE 32
typedef struct {
	uint8_t data[64];
	uint32_t datalen;
	unsigned long long bitlen;
	uint32_t state[8];
} SHA256_CTX;

void sha256_init(SHA256_CTX *ctx);
void sha256_update(SHA256_CTX *ctx, const uint8_t data[], size_t len);
void sha256_final(SHA256_CTX *ctx, uint8_t hash[]);

static uint32_t k[64] = {
	0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b,0x59f111f1,0x923f82a4,0xab1c5ed5,
	0xd807aa98,0x12835b01,0x243185be,0x550c7dc3,0x72be5d74,0x80deb1fe,0x9bdc06a7,0xc19bf174,
	0xe49b69c1,0xefbe4786,0x0fc19dc6,0x240ca1cc,0x2de92c6f,0x4a7484aa,0x5cb0a9dc,0x76f988da,
	0x983e5152,0xa831c66d,0xb00327c8,0xbf597fc7,0xc6e00bf3,0xd5a79147,0x06ca6351,0x14292967,
	0x27b70a85,0x2e1b2138,0x4d2c6dfc,0x53380d13,0x650a7354,0x766a0abb,0x81c2c92e,0x92722c85,
	0xa2bfe8a1,0xa81a664b,0xc24b8b70,0xc76c51a3,0xd192e819,0xd6990624,0xf40e3585,0x106aa070,
	0x19a4c116,0x1e376c08,0x2748774c,0x34b0bcb5,0x391c0cb3,0x4ed8aa4a,0x5b9cca4f,0x682e6ff3,
	0x748f82ee,0x78a5636f,0x84c87814,0x8cc70208,0x90befffa,0xa4506ceb,0xbef9a3f7,0xc67178f2
};
#define ROTR(a,b) (((a) >> (b)) | ((a) << (32-(b))))
#define Ch(x,y,z) (((x) & (y)) ^ (~(x) & (z)))
#define Maj(x,y,z) (((x) & (y)) ^ ((x) & (z)) ^ ((y) & (z)))
#define EP0(x) (ROTR(x,2) ^ ROTR(x,13) ^ ROTR(x,22))
#define EP1(x) (ROTR(x,6) ^ ROTR(x,11) ^ ROTR(x,25))
#define SIG0(x) (ROTR(x,7) ^ ROTR(x,18) ^ ((x) >> 3))
#define SIG1(x) (ROTR(x,17) ^ ROTR(x,19) ^ ((x) >> 10))

static void sha256_transform(SHA256_CTX *ctx, const uint8_t data[]) {
	uint32_t a, b, c, d, e, f, g, h, i, j, t1, t2, m[64];
	for (i = 0, j = 0; i < 16; ++i, j += 4)
		m[i] = (data[j] << 24) | (data[j + 1] << 16) | (data[j + 2] << 8) | (data[j + 3]);
	for ( ; i < 64; ++i)
		m[i] = SIG1(m[i - 2]) + m[i - 7] + SIG0(m[i - 15]) + m[i - 16];
	a = ctx->state[0]; b = ctx->state[1]; c = ctx->state[2]; d = ctx->state[3];
	e = ctx->state[4]; f = ctx->state[5]; g = ctx->state[6]; h = ctx->state[7];
	for (i = 0; i < 64; ++i) {
		t1 = h + EP1(e) + Ch(e,f,g) + k[i] + m[i];
		t2 = EP0(a) + Maj(a,b,c);
		h = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2;
	}
	ctx->state[0] += a; ctx->state[1] += b; ctx->state[2] += c; ctx->state[3] += d;
	ctx->state[4] += e; ctx->state[5] += f; ctx->state[6] += g; ctx->state[7] += h;
}
void sha256_init(SHA256_CTX *ctx) {
	ctx->datalen = 0;
	ctx->bitlen = 0;
	ctx->state[0] = 0x6a09e667; ctx->state[1] = 0xbb67ae85;
	ctx->state[2] = 0x3c6ef372; ctx->state[3] = 0xa54ff53a;
	ctx->state[4] = 0x510e527f; ctx->state[5] = 0x9b05688c;
	ctx->state[6] = 0x1f83d9ab; ctx->state[7] = 0x5be0cd19;
}
void sha256_update(SHA256_CTX *ctx, const uint8_t data[], size_t len) {
	uint32_t i;
	for (i = 0; i < len; ++i) {
		ctx->data[ctx->datalen] = data[i];
		ctx->datalen++;
		if (ctx->datalen == 64) {
			sha256_transform(ctx, ctx->data);
			ctx->bitlen += 512;
			ctx->datalen = 0;
		}
	}
}
void sha256_final(SHA256_CTX *ctx, uint8_t hash[]) {
	uint32_t i;
	i = ctx->datalen;
	if (ctx->datalen < 56) {
		ctx->data[i++] = 0x80;
		while (i < 56)
			ctx->data[i++] = 0x00;
	}
	else {
		ctx->data[i++] = 0x80;
		while (i < 64)
			ctx->data[i++] = 0x00;
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
 * ---------------------------------------------------------------------------
 * Dependency 3: Base64 (From https://github.com/superwills/NibbleAndAHalf)
 * A public domain Base64 encoder/decoder.
 * ---------------------------------------------------------------------------
 */
static const char* b64_table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

// Returns the size of the output buffer required for base64 encoding
size_t b64_encoded_size(size_t inlen) {
    size_t ret = inlen;
    if (inlen % 3 != 0)
        ret += 3 - (inlen % 3);
    ret /= 3;
    ret *= 4;
    return ret;
}

// Returns the size of the output buffer required for base64 decoding
size_t b64_decoded_size(const char *in) {
    size_t len = strlen(in);
    size_t ret = len / 4 * 3;
    for (size_t i = len; i-- > 0; ) {
        if (in[i] == '=') {
            ret--;
        } else {
            break;
        }
    }
    return ret;
}

char* b64_encode(const unsigned char *in, size_t len) {
    char *out;
    size_t elen, i, j, v;

    if (in == NULL || len == 0) return NULL;

    elen = b64_encoded_size(len);
    out = malloc(elen + 1);
    out[elen] = '\0';

    for (i = 0, j = 0; i < len; i += 3, j += 4) {
        v = in[i];
        v = i + 1 < len ? v << 8 | in[i + 1] : v << 8;
        v = i + 2 < len ? v << 8 | in[i + 2] : v << 8;

        out[j] = b64_table[(v >> 18) & 0x3F];
        out[j + 1] = b64_table[(v >> 12) & 0x3F];
        if (i + 1 < len) {
            out[j + 2] = b64_table[(v >> 6) & 0x3F];
        } else {
            out[j + 2] = '=';
        }
        if (i + 2 < len) {
            out[j + 3] = b64_table[v & 0x3F];
        } else {
            out[j + 3] = '=';
        }
    }
    return out;
}

unsigned char* b64_decode(const char *in, size_t *out_len) {
    size_t len = strlen(in);
    unsigned char *out;
    size_t i, j;
    int v;

    if (in == NULL) return NULL;
    if (len % 4 != 0) return NULL;

    *out_len = b64_decoded_size(in);
    out = malloc(*out_len);
    if (out == NULL) return NULL;

    for (i = 0, j = 0; i < len; i += 4, j += 3) {
        v = 0;
        for (int k = 0; k < 4; k++) {
            const char *p = strchr(b64_table, in[i+k]);
            if (p == NULL) { // Invalid character
                if (in[i+k] == '=') {
                    v <<= 6;
                } else {
                    free(out);
                    return NULL;
                }
            } else {
                v <<= 6;
                v |= (p - b64_table);
            }
        }

        out[j] = (v >> 16) & 0xFF;
        if (in[i + 2] != '=')
            out[j + 1] = (v >> 8) & 0xFF;
        if (in[i + 3] != '=')
            out[j + 2] = v & 0xFF;
    }
    return out;
}

/*
------------------------------------------------------------------------------------------
|                                                                                        |
|                                   DEPENDENCIES END                                     |
|                                                                                        |
------------------------------------------------------------------------------------------
*/


/*
 * =====================================================================================
 *
 *       CORE LOGIC: AESCipher Implementation
 *
 * =====================================================================================
 */

// The struct to hold our cipher's state, similar to the Python class.
typedef struct {
    uint8_t key[AES_KEYLEN]; // 32 bytes for AES-256
} AESCipher;

// Function to generate cryptographically secure random bytes for the IV.
int generate_iv(uint8_t* iv, size_t size) {
#ifdef _WIN32
    HCRYPTPROV hCryptProv;
    if (!CryptAcquireContext(&hCryptProv, NULL, NULL, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT)) {
        return -1;
    }
    if (!CryptGenRandom(hCryptProv, size, iv)) {
        CryptReleaseContext(hCryptProv, 0);
        return -1;
    }
    CryptReleaseContext(hCryptProv, 0);
    return 0;
#else
    int fd = open("/dev/urandom", O_RDONLY);
    if (fd == -1) {
        perror("Failed to open /dev/urandom");
        return -1;
    }
    ssize_t result = read(fd, iv, size);
    close(fd);
    return (result == (ssize_t)size) ? 0 : -1;
#endif
}

/**
 * @brief Creates and initializes an AESCipher object.
 *        Derives a 32-byte key from the input password using SHA-256.
 * @param key The password string.
 * @return A pointer to the newly created AESCipher object, or NULL on failure.
 *         The caller is responsible for freeing this with aes_cipher_destroy().
 */
AESCipher* aes_cipher_create(const char* key) {
    AESCipher* cipher = (AESCipher*)malloc(sizeof(AESCipher));
    if (!cipher) {
        return NULL;
    }

    SHA256_CTX ctx;
    sha256_init(&ctx);
    sha256_update(&ctx, (const uint8_t*)key, strlen(key));
    sha256_final(&ctx, cipher->key);

    return cipher;
}

/**
 * @brief Frees the memory associated with an AESCipher object.
 * @param cipher The AESCipher object to destroy.
 */
void aes_cipher_destroy(AESCipher* cipher) {
    if (cipher) {
        // Securely zero out the key before freeing
        memset(cipher->key, 0, sizeof(cipher->key));
        free(cipher);
    }
}

/**
 * @brief Pads data using PKCS#7-style padding to a 32-byte boundary.
 * @param raw The raw data to pad.
 * @param raw_len The length of the raw data.
 * @param padded_len A pointer to store the new, padded length.
 * @return A new buffer containing the padded data. Caller must free this buffer.
 */
static uint8_t* _pad(const char* raw, size_t raw_len, size_t* padded_len) {
    // This matches the Python logic: self.bs = 32
    const int block_size = 32; 
    size_t padding_len = block_size - (raw_len % block_size);
    *padded_len = raw_len + padding_len;

    uint8_t* padded_data = (uint8_t*)malloc(*padded_len);
    if (!padded_data) return NULL;

    memcpy(padded_data, raw, raw_len);
    for (size_t i = 0; i < padding_len; ++i) {
        padded_data[raw_len + i] = (uint8_t)padding_len;
    }
    return padded_data;
}

/**
 * @brief Unpads data that was padded with PKCS#7.
 * @param data The decrypted data buffer.
 * @param data_len The length of the decrypted data.
 * @return The new length of the data after unpadding.
 */
static size_t _unpad(const uint8_t* data, size_t data_len) {
    if (data_len == 0) return 0;
    // The value of the last byte is the length of the padding
    uint8_t padding_len = data[data_len - 1];
    if (padding_len > data_len) return 0; // Invalid padding
    return data_len - padding_len;
}

/**
 * @brief Encrypts a raw string.
 * @param cipher The initialized AESCipher object.
 * @param raw The plaintext string to encrypt.
 * @return A Base64 encoded string of (IV + Ciphertext).
 *         The caller is responsible for freeing the returned string.
 *         Returns NULL on failure.
 */
char* aes_cipher_encrypt(AESCipher* cipher, const char* raw) {
    // 1. Pad the raw data
    size_t raw_len = strlen(raw);
    size_t padded_len;
    uint8_t* padded_raw = _pad(raw, raw_len, &padded_len);
    if (!padded_raw) return NULL;

    // 2. Generate a random IV
    uint8_t iv[AES_BLOCKLEN];
    if (generate_iv(iv, AES_BLOCKLEN) != 0) {
        free(padded_raw);
        return NULL;
    }

    // 3. Encrypt the padded data
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, cipher->key, iv);
    AES_CBC_encrypt_buffer(&ctx, padded_raw, padded_len); // padded_raw is now the ciphertext

    // 4. Prepend IV to the ciphertext
    size_t final_len = AES_BLOCKLEN + padded_len;
    uint8_t* iv_and_ciphertext = (uint8_t*)malloc(final_len);
    if (!iv_and_ciphertext) {
        free(padded_raw);
        return NULL;
    }
    memcpy(iv_and_ciphertext, iv, AES_BLOCKLEN);
    memcpy(iv_and_ciphertext + AES_BLOCKLEN, padded_raw, padded_len);

    // 5. Base64 encode the result
    char* b64_output = b64_encode(iv_and_ciphertext, final_len);

    // 6. Clean up intermediate buffers
    free(padded_raw);
    free(iv_and_ciphertext);

    return b64_output;
}

/**
 * @brief Decrypts a Base64 encoded string.
 * @param cipher The initialized AESCipher object.
 * @param enc The Base64 encoded string to decrypt.
 * @return The original plaintext string.
 *         The caller is responsible for freeing the returned string.
 *         Returns NULL on failure (e.g., bad encoding, invalid padding).
 */
char* aes_cipher_decrypt(AESCipher* cipher, const char* enc) {
    // 1. Base64 decode the input
    size_t decoded_len;
    uint8_t* decoded_data = b64_decode(enc, &decoded_len);
    if (!decoded_data) return NULL;

    // 2. Extract IV and ciphertext
    if (decoded_len < AES_BLOCKLEN) {
        free(decoded_data);
        return NULL; // Not enough data for an IV
    }
    uint8_t* iv = decoded_data;
    uint8_t* ciphertext = decoded_data + AES_BLOCKLEN;
    size_t ciphertext_len = decoded_len - AES_BLOCKLEN;

    // 3. Decrypt the ciphertext
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, cipher->key, iv);
    AES_CBC_decrypt_buffer(&ctx, ciphertext, ciphertext_len); // ciphertext is now decrypted data

    // 4. Unpad the decrypted data
    size_t unpadded_len = _unpad(ciphertext, ciphertext_len);

    // 5. Create a null-terminated string from the result
    char* decrypted_string = (char*)malloc(unpadded_len + 1);
    if (!decrypted_string) {
        free(decoded_data);
        return NULL;
    }
    memcpy(decrypted_string, ciphertext, unpadded_len);
    decrypted_string[unpadded_len] = '\0';

    // 6. Clean up
    free(decoded_data);

    return decrypted_string;
}


/*
 * =====================================================================================
 *
 *       MAIN: Example Usage
 *
 * =====================================================================================
 */

int main() {
    const char* key = "mysecretpassword";
    const char* message = "Secret Message A";

    printf("Original message: %s\n", message);
    printf("Password: %s\n\n", key);

    // Create cipher object
    AESCipher* cipher = aes_cipher_create(key);
    if (!cipher) {
        fprintf(stderr, "Failed to create cipher.\n");
        return 1;
    }

    // Encrypt
    char* encrypted = aes_cipher_encrypt(cipher, message);
    if (!encrypted) {
        fprintf(stderr, "Encryption failed.\n");
        aes_cipher_destroy(cipher);
        return 1;
    }
    printf("Encrypted: %s\n", encrypted);

    // Decrypt
    char* decrypted = aes_cipher_decrypt(cipher, encrypted);
    if (!decrypted) {
        fprintf(stderr, "Decryption failed.\n");
        free(encrypted);
        aes_cipher_destroy(cipher);
        return 1;
    }
    printf("Decrypted: %s\n", decrypted);

    // Verify
    if (strcmp(message, decrypted) == 0) {
        printf("\nSuccess: Decrypted message matches original.\n");
    } else {
        printf("\nError: Decrypted message does NOT match original.\n");
    }

    // Clean up
    free(encrypted);
    free(decrypted);
    aes_cipher_destroy(cipher);

    return 0;
}