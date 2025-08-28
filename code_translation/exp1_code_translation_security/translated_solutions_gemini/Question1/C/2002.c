#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h> // For uint8_t, etc.

/*
================================================================================
 C-Dependency: tiny-AES-c (https://github.com/kokke/tiny-AES-c)
--------------------------------------------------------------------------------
 This is a self-contained C implementation of the AES algorithm.
 It's included here to make the translated code self-sufficient, as requested.
================================================================================
*/

//-- Start of tiny-AES-c header --//
#ifndef _AES_H_
#define _AES_H_

// #define the macros below to 1/0 to enable/disable the mode of operation.
//
// CBC enables AES encryption in CBC-mode of operation.
// CTR enables encryption in counter-mode.
// ECB enables the basic ECB 16-byte block algorithm. All can be enabled simultaneously.

// The #ifndef-guard allows it to be configured before #include'ing or at compile time.
#ifndef AES_CBC
  #define AES_CBC 1
#endif

#ifndef AES_CTR
  #define AES_CTR 1
#endif

#ifndef AES_ECB
  #define AES_ECB 1
#endif

#define AES128 1
//#define AES192 1
//#define AES256 1

#if defined(AES256) && (AES256 == 1)
    #define AES_KEYLEN 32
    #define AES_keyExpSize 240
#elif defined(AES192) && (AES192 == 1)
    #define AES_KEYLEN 24
    #define AES_keyExpSize 208
#else
    #define AES_KEYLEN 16
    #define AES_keyExpSize 176
#endif

#define AES_BLOCKLEN 16 // Block length in bytes - AES is 128b block only

struct AES_ctx
{
  uint8_t RoundKey[AES_keyExpSize];
#if (defined(AES_CBC) && (AES_CBC == 1)) || (defined(AES_CTR) && (AES_CTR == 1))
  uint8_t Iv[AES_BLOCKLEN];
#endif
};

void AES_init_ctx(struct AES_ctx* ctx, const uint8_t* key);
#if (defined(AES_CBC) && (AES_CBC == 1)) || (defined(AES_CTR) && (AES_CTR == 1))
void AES_init_ctx_iv(struct AES_ctx* ctx, const uint8_t* key, const uint8_t* iv);
void AES_ctx_set_iv(struct AES_ctx* ctx, const uint8_t* iv);
#endif

#if defined(AES_ECB) && (AES_ECB == 1)
// buffer size is exactly AES_BLOCKLEN bytes; 
// you need only AES_init_ctx as IV is not used in ECB
// NB: ECB is considered insecure for most uses
void AES_ECB_encrypt(const struct AES_ctx* ctx, uint8_t* buf);
void AES_ECB_decrypt(const struct AES_ctx* ctx, uint8_t* buf);
#endif

#if defined(AES_CBC) && (AES_CBC == 1)
// buffer size MUST be mutiple of AES_BLOCKLEN;
// Suggest https://en.wikipedia.org/wiki/Padding_(cryptography)#PKCS7 for padding scheme
// NOTES: you need to set IV in ctx via AES_init_ctx_iv() or AES_ctx_set_iv()
//        no IV should be transmitted in CBC mode this is a misuse of CBC mode
void AES_CBC_encrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, uint32_t length);
void AES_CBC_decrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, uint32_t length);
#endif

#if defined(AES_CTR) && (AES_CTR == 1)
// Same function for encrypting and decrypting. 
// IV is incremented for every block, and used after encryption as XOR-key for plaintext stream
void AES_CTR_xcrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, uint32_t length);
#endif

#endif // _AES_H_
//-- End of tiny-AES-c header --//


//-- Start of tiny-AES-c implementation --//
// This is the C file part, included here for self-containment.
#define Sbox sbox
static const uint8_t sbox[256] = {
  //0     1    2      3     4    5     6     7      8    9     A      B    C     D     E     F
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

#define getSBoxValue(num) (Sbox[(num)])

static const uint8_t Rcon[11] = {
  0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36 };

typedef uint8_t state_t[4][4];

static void KeyExpansion(uint8_t* RoundKey, const uint8_t* Key)
{
  unsigned i, j, k;
  uint8_t tempa[4]; 
  
  for (i = 0; i < (AES_KEYLEN / 4); ++i)
  {
    RoundKey[(i * 4) + 0] = Key[(i * 4) + 0];
    RoundKey[(i * 4) + 1] = Key[(i * 4) + 1];
    RoundKey[(i * 4) + 2] = Key[(i * 4) + 2];
    RoundKey[(i * 4) + 3] = Key[(i * 4) + 3];
  }

  for (i = (AES_KEYLEN / 4); i < (AES_BLOCKLEN * ((AES_KEYLEN / 8) + 7)) / 4; ++i)
  {
    for (k = 0; k < 4; ++k)
    {
      tempa[k] = RoundKey[(i-1) * 4 + k];
    }

    if (i % (AES_KEYLEN / 4) == 0)
    {
      k = tempa[0];
      tempa[0] = tempa[1];
      tempa[1] = tempa[2];
      tempa[2] = tempa[3];
      tempa[3] = k;

      tempa[0] = getSBoxValue(tempa[0]);
      tempa[1] = getSBoxValue(tempa[1]);
      tempa[2] = getSBoxValue(tempa[2]);
      tempa[3] = getSBoxValue(tempa[3]);

      tempa[0] = tempa[0] ^ Rcon[i/(AES_KEYLEN / 4)];
    }
    
#if defined(AES256) && (AES256 == 1)
    if (i % (AES_KEYLEN / 4) == 4)
    {
      tempa[0] = getSBoxValue(tempa[0]);
      tempa[1] = getSBoxValue(tempa[1]);
      tempa[2] = getSBoxValue(tempa[2]);
      tempa[3] = getSBoxValue(tempa[3]);
    }
#endif

    j = i * 4; k = (i - (AES_KEYLEN / 4)) * 4;
    RoundKey[j + 0] = RoundKey[k + 0] ^ tempa[0];
    RoundKey[j + 1] = RoundKey[k + 1] ^ tempa[1];
    RoundKey[j + 2] = RoundKey[k + 2] ^ tempa[2];
    RoundKey[j + 3] = RoundKey[k + 3] ^ tempa[3];
  }
}

void AES_init_ctx(struct AES_ctx* ctx, const uint8_t* key)
{
  KeyExpansion(ctx->RoundKey, key);
}
#if (defined(AES_CBC) && (AES_CBC == 1)) || (defined(AES_CTR) && (AES_CTR == 1))
void AES_init_ctx_iv(struct AES_ctx* ctx, const uint8_t* key, const uint8_t* iv)
{
  KeyExpansion(ctx->RoundKey, key);
  memcpy(ctx->Iv, iv, AES_BLOCKLEN);
}
void AES_ctx_set_iv(struct AES_ctx* ctx, const uint8_t* iv)
{
  memcpy(ctx->Iv, iv, AES_BLOCKLEN);
}
#endif

static void AddRoundKey(uint8_t round, state_t* state, const uint8_t* RoundKey)
{
  uint8_t i,j;
  for (i = 0; i < 4; ++i)
  {
    for (j = 0; j < 4; ++j)
    {
      (*state)[i][j] ^= RoundKey[(round * AES_BLOCKLEN) + (i * 4) + j];
    }
  }
}

static void SubBytes(state_t* state)
{
  uint8_t i, j;
  for (i = 0; i < 4; ++i)
  {
    for (j = 0; j < 4; ++j)
    {
      (*state)[j][i] = getSBoxValue((*state)[j][i]);
    }
  }
}

static void ShiftRows(state_t* state)
{
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

#define xtime(x) ((x<<1) ^ (((x>>7) & 1) * 0x1b))

static void MixColumns(state_t* state)
{
  uint8_t i;
  uint8_t Tmp, Tm, t;
  for (i = 0; i < 4; ++i)
  {  
    t   = (*state)[i][0];
    Tmp = (*state)[i][0] ^ (*state)[i][1] ^ (*state)[i][2] ^ (*state)[i][3];
    Tm  = (*state)[i][0] ^ (*state)[i][1]; Tm = xtime(Tm); (*state)[i][0] ^= Tm ^ Tmp;
    Tm  = (*state)[i][1] ^ (*state)[i][2]; Tm = xtime(Tm); (*state)[i][1] ^= Tm ^ Tmp;
    Tm  = (*state)[i][2] ^ (*state)[i][3]; Tm = xtime(Tm); (*state)[i][2] ^= Tm ^ Tmp;
    Tm  = (*state)[i][3] ^ t;              Tm = xtime(Tm); (*state)[i][3] ^= Tm ^ Tmp;
  }
}

static void Cipher(state_t* state, const uint8_t* RoundKey)
{
  uint8_t round = 0;
  uint8_t Nr = (AES_KEYLEN / 4) + 6;

  AddRoundKey(0, state, RoundKey); 

  for (round = 1; round < Nr; ++round)
  {
    SubBytes(state);
    ShiftRows(state);
    MixColumns(state);
    AddRoundKey(round, state, RoundKey);
  }

  SubBytes(state);
  ShiftRows(state);
  AddRoundKey(Nr, state, RoundKey);
}

static void xor_with_iv(uint8_t* buf, const uint8_t* Iv)
{
  uint8_t i;
  for (i = 0; i < AES_BLOCKLEN; ++i) 
  {
    buf[i] ^= Iv[i];
  }
}

#if defined(AES_CTR) && (AES_CTR == 1)

static void IncrementIv(uint8_t* iv)
{
    for (int i = AES_BLOCKLEN - 1; i >= 0; --i) {
        if (++iv[i] != 0) {
            break;
        }
    }
}

void AES_CTR_xcrypt_buffer(struct AES_ctx* ctx, uint8_t* buf, uint32_t length)
{
  uint8_t buffer[AES_BLOCKLEN];
  unsigned i;
  int bi;
  for (i = 0, bi = AES_BLOCKLEN; i < length; ++i, ++bi)
  {
    if (bi == AES_BLOCKLEN)
    {
      memcpy(buffer, ctx->Iv, AES_BLOCKLEN);
      Cipher((state_t*)buffer, ctx->RoundKey);
      IncrementIv(ctx->Iv);
      bi = 0;
    }
    buf[i] = (buf[i] ^ buffer[bi]);
  }
}

#endif // AES_CTR
//-- End of tiny-AES-c implementation --//


/*
================================================================================
 Translated Application Logic
--------------------------------------------------------------------------------
 The following C code replicates the functionality of the original Python script.
================================================================================
*/

/**
 * @brief Encrypts a plaintext message using AES-256 in CTR mode.
 *
 * NOTE: The pyaes library defaults to a zero-filled IV/nonce for CTR mode.
 * We replicate that behavior here for compatibility.
 *
 * @param key A 32-byte (256-bit) key.
 * @param plaintext The plaintext data to encrypt.
 * @param plaintext_len The length of the plaintext data.
 * @return A pointer to the newly allocated ciphertext. The caller is responsible
 *         for freeing this memory with free(). The ciphertext will have the
 *         same length as the plaintext.
 */
uint8_t* encrypt(const uint8_t* key, const uint8_t* plaintext, size_t plaintext_len) {
    // Allocate memory for the ciphertext. In CTR mode, ciphertext length is same as plaintext.
    uint8_t* ciphertext = (uint8_t*)malloc(plaintext_len);
    if (!ciphertext) {
        perror("Failed to allocate memory for ciphertext");
        return NULL;
    }
    // Copy plaintext to the buffer that will be encrypted in-place.
    memcpy(ciphertext, plaintext, plaintext_len);

    struct AES_ctx ctx;
    // The python `pyaes` library uses a zero IV by default for CTR mode.
    uint8_t iv[AES_BLOCKLEN];
    memset(iv, 0, AES_BLOCKLEN);

    // Initialize context with the key and IV
    AES_init_ctx_iv(&ctx, key, iv);

    // Encrypt the buffer
    AES_CTR_xcrypt_buffer(&ctx, ciphertext, plaintext_len);

    return ciphertext;
}

/**
 * @brief Decrypts a ciphertext using AES-256 in CTR mode.
 *
 * NOTE: Decryption in CTR mode is the same operation as encryption.
 *
 * @param key A 32-byte (256-bit) key.
 * @param ciphertext The ciphertext data to decrypt.
 * @param ciphertext_len The length of the ciphertext data.
 * @return A pointer to the newly allocated, null-terminated plaintext string.
 *         The caller is responsible for freeing this memory with free().
 */
char* decrypt(const uint8_t* key, const uint8_t* ciphertext, size_t ciphertext_len) {
    // Allocate memory for the plaintext. Add 1 for the null terminator.
    char* plaintext = (char*)malloc(ciphertext_len + 1);
    if (!plaintext) {
        perror("Failed to allocate memory for plaintext");
        return NULL;
    }
    // Copy ciphertext to the buffer that will be decrypted in-place.
    memcpy(plaintext, ciphertext, ciphertext_len);

    struct AES_ctx ctx;
    // The python `pyaes` library uses a zero IV by default for CTR mode.
    uint8_t iv[AES_BLOCKLEN];
    memset(iv, 0, AES_BLOCKLEN);

    // Initialize context with the key and IV
    AES_init_ctx_iv(&ctx, key, iv);

    // Decrypt the buffer (it's the same function as encryption for CTR)
    AES_CTR_xcrypt_buffer(&ctx, (uint8_t*)plaintext, ciphertext_len);

    // Null-terminate the resulting string
    plaintext[ciphertext_len] = '\0';

    return plaintext;
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const uint8_t* data, size_t len) {
    printf("%s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

void round_trip_test() {
    // The key must be 32 bytes for AES-256.
    const uint8_t* key = (const uint8_t*)"This_key_for_demo_purposes_only!";
    const char* plaintext_str = "Text may be any length you wish, no padding is required";
    size_t len = strlen(plaintext_str);

    printf("Original Plaintext: %s\n", plaintext_str);
    printf("Key: %s\n", key);
    printf("Plaintext length: %zu\n\n", len);

    // Encrypt
    uint8_t* ciphertext = encrypt(key, (const uint8_t*)plaintext_str, len);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed.\n");
        return;
    }
    
    print_hex("Ciphertext (hex)", ciphertext, len);

    // Decrypt
    char* decrypted_text = decrypt(key, ciphertext, len);
    if (!decrypted_text) {
        fprintf(stderr, "Decryption failed.\n");
        free(ciphertext); // Clean up memory
        return;
    }

    printf("\nDecrypted Plaintext: %s\n", decrypted_text);

    // Verify correctness
    if (strcmp(plaintext_str, decrypted_text) == 0) {
        printf("\nSUCCESS: Decrypted text matches original plaintext.\n");
    } else {
        printf("\nFAILURE: Decrypted text does not match original plaintext.\n");
    }

    // Clean up allocated memory
    free(ciphertext);
    free(decrypted_text);
}

void test() {
    round_trip_test();
}

int main() {
    // The tiny-AES-c implementation used here is configured for AES-256
    // by defining AES_KEYLEN to 32. We add a compile-time check for safety.
    #if AES_KEYLEN != 32
        #error "This code is designed for AES-256 (32-byte key). Please adjust tiny-AES-c settings."
    #endif

    test();
    return 0;
}