// All dependencies are included in this single file.
// The code is structured as follows:
// 1. Standard C headers.
// 2. Platform-specific headers and implementation for random number generation.
// 3. Base64 encoding and decoding implementation.
// 4. AES-128 (ECB mode) implementation from the public domain "tiny-aes-c" library.
// 5. The translated Python functions:
//    - generate_secret_key_for_AES_cipher
//    - encrypt_message
//    - decrypt_message
// 6. The main function to demonstrate the usage, equivalent to the Python script's main block.

// =================================================================================================
// 1. STANDARD C HEADERS
// =================================================================================================
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h> // For uint8_t

// =================================================================================================
// 2. RANDOM NUMBER GENERATION (os.urandom equivalent)
// =================================================================================================
#ifdef _WIN32
#include <windows.h>
#include <wincrypt.h> // For CryptGenRandom
#else
#include <fcntl.h>   // For O_RDONLY
#include <unistd.h>  // For read(), close()
#endif

// Fills the buffer with cryptographically secure random bytes.
void get_random_bytes(uint8_t* buffer, size_t len) {
#ifdef _WIN32
    HCRYPTPROV hCryptProv;
    if (!CryptAcquireContext(&hCryptProv, NULL, NULL, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT)) {
        fprintf(stderr, "Error: CryptAcquireContext failed.\n");
        exit(1);
    }
    if (!CryptGenRandom(hCryptProv, (DWORD)len, buffer)) {
        fprintf(stderr, "Error: CryptGenRandom failed.\n");
        CryptReleaseContext(hCryptProv, 0);
        exit(1);
    }
    CryptReleaseContext(hCryptProv, 0);
#else
    int fd = open("/dev/urandom", O_RDONLY);
    if (fd < 0) {
        perror("Error opening /dev/urandom");
        exit(1);
    }
    ssize_t bytes_read = read(fd, buffer, len);
    if (bytes_read < 0 || (size_t)bytes_read != len) {
        fprintf(stderr, "Error: Could not read enough random bytes.\n");
        close(fd);
        exit(1);
    }
    close(fd);
#endif
}


// =================================================================================================
// 3. BASE64 IMPLEMENTATION (base64 equivalent)
// =================================================================================================
static const char b64_chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

char* base64_encode(const uint8_t* data, size_t input_length) {
    size_t output_length = 4 * ((input_length + 2) / 3);
    char* encoded_data = (char*)malloc(output_length + 1);
    if (encoded_data == NULL) return NULL;

    for (size_t i = 0, j = 0; i < input_length;) {
        uint32_t octet_a = i < input_length ? data[i++] : 0;
        uint32_t octet_b = i < input_length ? data[i++] : 0;
        uint32_t octet_c = i < input_length ? data[i++] : 0;
        uint32_t triple = (octet_a << 16) + (octet_b << 8) + octet_c;

        encoded_data[j++] = b64_chars[(triple >> 18) & 0x3F];
        encoded_data[j++] = b64_chars[(triple >> 12) & 0x3F];
        encoded_data[j++] = b64_chars[(triple >> 6) & 0x3F];
        encoded_data[j++] = b64_chars[triple & 0x3F];
    }

    int mod_table[] = {0, 2, 1};
    for (int i = 0; i < mod_table[input_length % 3]; i++) {
        encoded_data[output_length - 1 - i] = '=';
    }
    encoded_data[output_length] = '\0';
    return encoded_data;
}

uint8_t* base64_decode(const char* data, size_t* output_length) {
    size_t input_length = strlen(data);
    if (input_length % 4 != 0) return NULL;

    *output_length = input_length / 4 * 3;
    if (data[input_length - 1] == '=') (*output_length)--;
    if (data[input_length - 2] == '=') (*output_length)--;

    uint8_t* decoded_data = (uint8_t*)malloc(*output_length);
    if (decoded_data == NULL) return NULL;

    for (size_t i = 0, j = 0; i < input_length;) {
        uint32_t sextet_a = data[i] == '=' ? 0 & i++ : strchr(b64_chars, data[i++]) - b64_chars;
        uint32_t sextet_b = data[i] == '=' ? 0 & i++ : strchr(b64_chars, data[i++]) - b64_chars;
        uint32_t sextet_c = data[i] == '=' ? 0 & i++ : strchr(b64_chars, data[i++]) - b64_chars;
        uint32_t sextet_d = data[i] == '=' ? 0 & i++ : strchr(b64_chars, data[i++]) - b64_chars;
        uint32_t triple = (sextet_a << 18) + (sextet_b << 12) + (sextet_c << 6) + sextet_d;

        if (j < *output_length) decoded_data[j++] = (triple >> 16) & 0xFF;
        if (j < *output_length) decoded_data[j++] = (triple >> 8) & 0xFF;
        if (j < *output_length) decoded_data[j++] = triple & 0xFF;
    }
    return decoded_data;
}


// =================================================================================================
// 4. AES IMPLEMENTATION (Crypto.Cipher.AES equivalent)
// Source: tiny-aes-c (public domain) by kokke
// Mode: ECB (Electronic Codebook), which is the default for PyCrypto's AES.new(key)
// =================================================================================================
#define AES128 1
#define AES_BLOCKLEN 16 // Block length in bytes
#define AES_KEYLEN 16   // Key length in bytes for AES-128
#define AES_keyExpSize 176

struct AES_ctx {
    uint8_t RoundKey[AES_keyExpSize];
};

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
    0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
};
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
    0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
};
static const uint8_t Rcon[11] = { 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36 };

#define getSBoxValue(num) (sbox[(num)])
#define getSBoxInvert(num) (rsbox[(num)])

static void KeyExpansion(uint8_t* RoundKey, const uint8_t* Key) {
    unsigned i, j, k;
    uint8_t tempa[4];
    for (i = 0; i < 4; ++i) {
        RoundKey[i * 4 + 0] = Key[i * 4 + 0];
        RoundKey[i * 4 + 1] = Key[i * 4 + 1];
        RoundKey[i * 4 + 2] = Key[i * 4 + 2];
        RoundKey[i * 4 + 3] = Key[i * 4 + 3];
    }
    for (i = 4; i < 4 * (10 + 1); ++i) {
        k = (i - 1) * 4;
        tempa[0] = RoundKey[k + 0];
        tempa[1] = RoundKey[k + 1];
        tempa[2] = RoundKey[k + 2];
        tempa[3] = RoundKey[k + 3];
        if (i % 4 == 0) {
            k = tempa[0];
            tempa[0] = tempa[1];
            tempa[1] = tempa[2];
            tempa[2] = tempa[3];
            tempa[3] = k;
            tempa[0] = getSBoxValue(tempa[0]);
            tempa[1] = getSBoxValue(tempa[1]);
            tempa[2] = getSBoxValue(tempa[2]);
            tempa[3] = getSBoxValue(tempa[3]);
            tempa[0] = tempa[0] ^ Rcon[i / 4];
        }
        j = i * 4; k = (i - 4) * 4;
        RoundKey[j + 0] = RoundKey[k + 0] ^ tempa[0];
        RoundKey[j + 1] = RoundKey[k + 1] ^ tempa[1];
        RoundKey[j + 2] = RoundKey[k + 2] ^ tempa[2];
        RoundKey[j + 3] = RoundKey[k + 3] ^ tempa[3];
    }
}

void AES_init_ctx(struct AES_ctx* ctx, const uint8_t* key) {
    KeyExpansion(ctx->RoundKey, key);
}

static void AddRoundKey(uint8_t round, uint8_t state[4][4], const uint8_t* RoundKey) {
    uint8_t i, j;
    for (i = 0; i < 4; ++i) {
        for (j = 0; j < 4; ++j) {
            state[i][j] ^= RoundKey[round * 16 + i * 4 + j];
        }
    }
}

static void SubBytes(uint8_t state[4][4]) {
    uint8_t i, j;
    for (i = 0; i < 4; ++i) {
        for (j = 0; j < 4; ++j) {
            state[j][i] = getSBoxValue(state[j][i]);
        }
    }
}

static void ShiftRows(uint8_t state[4][4]) {
    uint8_t temp;
    temp = state[0][1]; state[0][1] = state[1][1]; state[1][1] = state[2][1]; state[2][1] = state[3][1]; state[3][1] = temp;
    temp = state[0][2]; state[0][2] = state[2][2]; state[2][2] = temp; temp = state[1][2]; state[1][2] = state[3][2]; state[3][2] = temp;
    temp = state[0][3]; state[0][3] = state[3][3]; state[3][3] = state[2][3]; state[2][3] = state[1][3]; state[1][3] = temp;
}

#define xtime(x) ((x << 1) ^ (((x >> 7) & 1) * 0x1b))
static void MixColumns(uint8_t state[4][4]) {
    uint8_t i, a, b, c, d;
    for (i = 0; i < 4; ++i) {
        a = state[i][0]; b = state[i][1]; c = state[i][2]; d = state[i][3];
        state[i][0] = xtime(a) ^ (xtime(b) ^ b) ^ c ^ d;
        state[i][1] = a ^ xtime(b) ^ (xtime(c) ^ c) ^ d;
        state[i][2] = a ^ b ^ xtime(c) ^ (xtime(d) ^ d);
        state[i][3] = (xtime(a) ^ a) ^ b ^ c ^ xtime(d);
    }
}

static void InvMixColumns(uint8_t state[4][4]) {
    int i;
    uint8_t a, b, c, d;
    for (i = 0; i < 4; ++i) {
        a = state[i][0]; b = state[i][1]; c = state[i][2]; d = state[i][3];
        state[i][0] = xtime(xtime(xtime(xtime(a ^ c)))) ^ xtime(xtime(xtime(a ^ b ^ d))) ^ xtime(xtime(a ^ b ^ c)) ^ xtime(b ^ c ^ d) ^ (a ^ c ^ d);
        state[i][1] = xtime(xtime(xtime(xtime(b ^ d)))) ^ xtime(xtime(xtime(a ^ b ^ c))) ^ xtime(xtime(b ^ c ^ d)) ^ xtime(a ^ c ^ d) ^ (a ^ b ^ d);
        state[i][2] = xtime(xtime(xtime(xtime(a ^ c)))) ^ xtime(xtime(xtime(b ^ c ^ d))) ^ xtime(xtime(a ^ c ^ d)) ^ xtime(a ^ b ^ d) ^ (a ^ b ^ c);
        state[i][3] = xtime(xtime(xtime(xtime(b ^ d)))) ^ xtime(xtime(xtime(a ^ c ^ d))) ^ xtime(xtime(a ^ b ^ d)) ^ xtime(a ^ b ^ c) ^ (b ^ c ^ d);
    }
}

static void InvSubBytes(uint8_t state[4][4]) {
    uint8_t i, j;
    for (i = 0; i < 4; ++i) {
        for (j = 0; j < 4; ++j) {
            state[j][i] = getSBoxInvert(state[j][i]);
        }
    }
}

static void InvShiftRows(uint8_t state[4][4]) {
    uint8_t temp;
    temp = state[3][1]; state[3][1] = state[2][1]; state[2][1] = state[1][1]; state[1][1] = state[0][1]; state[0][1] = temp;
    temp = state[0][2]; state[0][2] = state[2][2]; state[2][2] = temp; temp = state[1][2]; state[1][2] = state[3][2]; state[3][2] = temp;
    temp = state[3][3]; state[3][3] = state[0][3]; state[0][3] = state[1][3]; state[1][3] = state[2][3]; state[2][3] = temp;
}

static void Cipher(uint8_t state[4][4], const uint8_t* RoundKey) {
    uint8_t round = 0;
    AddRoundKey(0, state, RoundKey);
    for (round = 1; round < 10; ++round) {
        SubBytes(state); ShiftRows(state); MixColumns(state); AddRoundKey(round, state, RoundKey);
    }
    SubBytes(state); ShiftRows(state); AddRoundKey(10, state, RoundKey);
}

static void InvCipher(uint8_t state[4][4], const uint8_t* RoundKey) {
    uint8_t round = 0;
    AddRoundKey(10, state, RoundKey);
    for (round = 9; round > 0; --round) {
        InvShiftRows(state); InvSubBytes(state); AddRoundKey(round, state, RoundKey); InvMixColumns(state);
    }
    InvShiftRows(state); InvSubBytes(state); AddRoundKey(0, state, RoundKey);
}

void AES_ECB_encrypt(const struct AES_ctx* ctx, uint8_t* buf) {
    Cipher((uint8_t(*)[4])buf, ctx->RoundKey);
}

void AES_ECB_decrypt(const struct AES_ctx* ctx, uint8_t* buf) {
    InvCipher((uint8_t(*)[4])buf, ctx->RoundKey);
}


// =================================================================================================
// 5. TRANSLATED PYTHON FUNCTIONS
// =================================================================================================

char* generate_secret_key_for_AES_cipher() {
    // AES key length must be 16 for AES-128
    const int AES_key_length = 16;
    // Generate a random secret key
    uint8_t secret_key[AES_key_length];
    get_random_bytes(secret_key, AES_key_length);
    // Encode this secret key for storing safely
    char* encoded_secret_key = base64_encode(secret_key, AES_key_length);
    return encoded_secret_key;
}

char* encrypt_message(const char* private_msg, const char* encoded_secret_key, char padding_character) {
    // Decode the encoded secret key
    size_t secret_key_len;
    uint8_t* secret_key = base64_decode(encoded_secret_key, &secret_key_len);
    if (!secret_key) {
        fprintf(stderr, "Error: Failed to decode secret key.\n");
        return NULL;
    }

    // Pad the private_msg because AES requires the length to be a multiple of 16
    size_t msg_len = strlen(private_msg);
    size_t padding_size = (AES_BLOCKLEN - (msg_len % AES_BLOCKLEN)) % AES_BLOCKLEN;
    size_t padded_msg_len = msg_len + padding_size;
    
    uint8_t* padded_private_msg = (uint8_t*)malloc(padded_msg_len);
    if (!padded_private_msg) {
        free(secret_key);
        return NULL;
    }
    memcpy(padded_private_msg, private_msg, msg_len);
    memset(padded_private_msg + msg_len, padding_character, padding_size);

    // Use the decoded secret key to create an AES cipher context
    struct AES_ctx ctx;
    AES_init_ctx(&ctx, secret_key);

    // Encrypt the padded message block by block
    for (size_t i = 0; i < padded_msg_len; i += AES_BLOCKLEN) {
        AES_ECB_encrypt(&ctx, padded_private_msg + i);
    }

    // Encode the encrypted msg for storing safely
    char* encoded_encrypted_msg = base64_encode(padded_private_msg, padded_msg_len);

    // Cleanup
    free(secret_key);
    free(padded_private_msg);

    return encoded_encrypted_msg;
}

char* decrypt_message(const char* encoded_encrypted_msg, const char* encoded_secret_key, char padding_character) {
    // Decode the encoded secret key and encrypted message
    size_t secret_key_len;
    uint8_t* secret_key = base64_decode(encoded_secret_key, &secret_key_len);
    if (!secret_key) {
        fprintf(stderr, "Error: Failed to decode secret key.\n");
        return NULL;
    }

    size_t encrypted_msg_len;
    uint8_t* encrypted_msg = base64_decode(encoded_encrypted_msg, &encrypted_msg_len);
    if (!encrypted_msg) {
        free(secret_key);
        fprintf(stderr, "Error: Failed to decode encrypted message.\n");
        return NULL;
    }

    // Use the decoded secret key to create an AES cipher context
    struct AES_ctx ctx;
    AES_init_ctx(&ctx, secret_key);

    // Decrypt the message block by block
    for (size_t i = 0; i < encrypted_msg_len; i += AES_BLOCKLEN) {
        AES_ECB_decrypt(&ctx, encrypted_msg + i);
    }
    // `encrypted_msg` now holds the decrypted, padded message

    // Unpad the decrypted message
    size_t unpadded_len = encrypted_msg_len;
    while (unpadded_len > 0 && encrypted_msg[unpadded_len - 1] == padding_character) {
        unpadded_len--;
    }

    // Convert decrypted message into a null-terminated string
    char* unpadded_private_msg = (char*)malloc(unpadded_len + 1);
    if (!unpadded_private_msg) {
        free(secret_key);
        free(encrypted_msg);
        return NULL;
    }
    memcpy(unpadded_private_msg, encrypted_msg, unpadded_len);
    unpadded_private_msg[unpadded_len] = '\0';

    // Cleanup
    free(secret_key);
    free(encrypted_msg);

    return unpadded_private_msg;
}


// =================================================================================================
// 6. MAIN FUNCTION (DEMONSTRATION)
// =================================================================================================
int main() {
    const char* private_msg = "\n My test string\n";
    char padding_character = '{';

    // Generate, encrypt, and decrypt
    char* secret_key = generate_secret_key_for_AES_cipher();
    char* encrypted_msg = encrypt_message(private_msg, secret_key, padding_character);
    char* decrypted_msg = decrypt_message(encrypted_msg, secret_key, padding_character);

    // Print results
    if (secret_key && encrypted_msg && decrypted_msg) {
        printf("   Secret Key: %s - (%zu)\n", secret_key, strlen(secret_key));
        printf("Encrypted Msg: %s - (%zu)\n", encrypted_msg, strlen(encrypted_msg));
        printf("Decrypted Msg: %s - (%zu)\n", decrypted_msg, strlen(decrypted_msg));
    } else {
        printf("An error occurred during the encryption/decryption process.\n");
    }

    // Free all dynamically allocated memory
    free(secret_key);
    free(encrypted_msg);
    free(decrypted_msg);

    return 0;
}