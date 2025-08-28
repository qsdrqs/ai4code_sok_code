#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

// -- AES Constants and Tables --
// AES-128 uses a 128-bit (16-byte) key and block size, and 10 rounds.
#define AES_KEY_SIZE   16 // in bytes
#define AES_BLOCK_SIZE 16 // in bytes
#define AES_ROUNDS     10

// The S-box substitution table
const uint8_t s_box[256] = {
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

// The inverse S-box
const uint8_t inv_s_box[256] = {
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

// The round constant (Rcon)
const uint8_t Rcon[11] = {
    0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36
};

// -- Core AES Functions --

// Expands the key into a key schedule
void key_expansion(const uint8_t* key, uint8_t* round_keys) {
    memcpy(round_keys, key, AES_KEY_SIZE);

    for (int i = AES_KEY_SIZE; i < AES_BLOCK_SIZE * (AES_ROUNDS + 1); i += 4) {
        uint8_t* temp = round_keys + i - 4;
        
        if (i % AES_KEY_SIZE == 0) {
            // Rotate word
            uint8_t k = temp[0];
            temp[0] = temp[1];
            temp[1] = temp[2];
            temp[2] = temp[3];
            temp[3] = k;

            // SubBytes
            temp[0] = s_box[temp[0]];
            temp[1] = s_box[temp[1]];
            temp[2] = s_box[temp[2]];
            temp[3] = s_box[temp[3]];

            // XOR with Rcon
            temp[0] ^= Rcon[i / AES_KEY_SIZE];
        }
        
        for (int j = 0; j < 4; ++j) {
            round_keys[i + j] = round_keys[i + j - AES_KEY_SIZE] ^ temp[j];
        }
    }
}

void add_round_key(uint8_t state[4][4], const uint8_t* round_key) {
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            state[j][i] ^= round_key[i * 4 + j];
        }
    }
}

void sub_bytes(uint8_t state[4][4]) {
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            state[i][j] = s_box[state[i][j]];
        }
    }
}

void inv_sub_bytes(uint8_t state[4][4]) {
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            state[i][j] = inv_s_box[state[i][j]];
        }
    }
}

void shift_rows(uint8_t state[4][4]) {
    uint8_t temp;
    // Row 1: no shift
    // Row 2: 1-byte left shift
    temp = state[1][0];
    state[1][0] = state[1][1];
    state[1][1] = state[1][2];
    state[1][2] = state[1][3];
    state[1][3] = temp;
    // Row 3: 2-byte left shift
    temp = state[2][0];
    state[2][0] = state[2][2];
    state[2][2] = temp;
    temp = state[2][1];
    state[2][1] = state[2][3];
    state[2][3] = temp;
    // Row 4: 3-byte left shift
    temp = state[3][0];
    state[3][0] = state[3][3];
    state[3][3] = state[3][2];
    state[3][2] = state[3][1];
    state[3][1] = temp;
}

void inv_shift_rows(uint8_t state[4][4]) {
    uint8_t temp;
    // Row 1: no shift
    // Row 2: 1-byte right shift
    temp = state[1][3];
    state[1][3] = state[1][2];
    state[1][2] = state[1][1];
    state[1][1] = state[1][0];
    state[1][0] = temp;
    // Row 3: 2-byte right shift
    temp = state[2][0];
    state[2][0] = state[2][2];
    state[2][2] = temp;
    temp = state[2][1];
    state[2][1] = state[2][3];
    state[2][3] = temp;
    // Row 4: 3-byte right shift
    temp = state[3][0];
    state[3][0] = state[3][1];
    state[3][1] = state[3][2];
    state[3][2] = state[3][3];
    state[3][3] = temp;
}

uint8_t gmul(uint8_t a, uint8_t b) {
    uint8_t p = 0;
    for (int i = 0; i < 8; i++) {
        if (b & 1) {
            p ^= a;
        }
        uint8_t hi_bit_set = (a & 0x80);
        a <<= 1;
        if (hi_bit_set) {
            a ^= 0x1b; // x^8 + x^4 + x^3 + x + 1
        }
        b >>= 1;
    }
    return p;
}

void mix_columns(uint8_t state[4][4]) {
    uint8_t temp[4][4];
    for (int i = 0; i < 4; ++i) {
        temp[0][i] = gmul(0x02, state[0][i]) ^ gmul(0x03, state[1][i]) ^ state[2][i] ^ state[3][i];
        temp[1][i] = state[0][i] ^ gmul(0x02, state[1][i]) ^ gmul(0x03, state[2][i]) ^ state[3][i];
        temp[2][i] = state[0][i] ^ state[1][i] ^ gmul(0x02, state[2][i]) ^ gmul(0x03, state[3][i]);
        temp[3][i] = gmul(0x03, state[0][i]) ^ state[1][i] ^ state[2][i] ^ gmul(0x02, state[3][i]);
    }
    memcpy(state, temp, sizeof(temp));
}

void inv_mix_columns(uint8_t state[4][4]) {
    uint8_t temp[4][4];
    for (int i = 0; i < 4; ++i) {
        temp[0][i] = gmul(0x0e, state[0][i]) ^ gmul(0x0b, state[1][i]) ^ gmul(0x0d, state[2][i]) ^ gmul(0x09, state[3][i]);
        temp[1][i] = gmul(0x09, state[0][i]) ^ gmul(0x0e, state[1][i]) ^ gmul(0x0b, state[2][i]) ^ gmul(0x0d, state[3][i]);
        temp[2][i] = gmul(0x0d, state[0][i]) ^ gmul(0x09, state[1][i]) ^ gmul(0x0e, state[2][i]) ^ gmul(0x0b, state[3][i]);
        temp[3][i] = gmul(0x0b, state[0][i]) ^ gmul(0x0d, state[1][i]) ^ gmul(0x09, state[2][i]) ^ gmul(0x0e, state[3][i]);
    }
    memcpy(state, temp, sizeof(temp));
}

void aes_encrypt_block(const uint8_t* input, uint8_t* output, const uint8_t* round_keys) {
    uint8_t state[4][4];
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            state[j][i] = input[i * 4 + j];
        }
    }

    add_round_key(state, round_keys);

    for (int round = 1; round < AES_ROUNDS; ++round) {
        sub_bytes(state);
        shift_rows(state);
        mix_columns(state);
        add_round_key(state, round_keys + round * AES_BLOCK_SIZE);
    }

    sub_bytes(state);
    shift_rows(state);
    add_round_key(state, round_keys + AES_ROUNDS * AES_BLOCK_SIZE);

    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            output[i * 4 + j] = state[j][i];
        }
    }
}

void aes_decrypt_block(const uint8_t* input, uint8_t* output, const uint8_t* round_keys) {
    uint8_t state[4][4];
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            state[j][i] = input[i * 4 + j];
        }
    }

    add_round_key(state, round_keys + AES_ROUNDS * AES_BLOCK_SIZE);

    for (int round = AES_ROUNDS - 1; round >= 1; --round) {
        inv_shift_rows(state);
        inv_sub_bytes(state);
        add_round_key(state, round_keys + round * AES_BLOCK_SIZE);
        inv_mix_columns(state);
    }

    inv_shift_rows(state);
    inv_sub_bytes(state);
    add_round_key(state, round_keys);

    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            output[i * 4 + j] = state[j][i];
        }
    }
}

// -- C "Class" Implementation --

// The struct holds the expanded key schedule.
typedef struct {
    uint8_t round_keys[AES_BLOCK_SIZE * (AES_ROUNDS + 1)];
    // The blk_sz from Python is always AES_BLOCK_SIZE (16) for AES.
} AESCipher;

// "Constructor" for the AESCipher struct.
// Takes a 16-byte key and initializes the cipher.
AESCipher* AESCipher_create(const uint8_t* key) {
    AESCipher* cipher = (AESCipher*)malloc(sizeof(AESCipher));
    if (!cipher) {
        return NULL;
    }
    key_expansion(key, cipher->round_keys);
    return cipher;
}

// "Destructor" to free the cipher object.
void AESCipher_destroy(AESCipher* cipher) {
    if (cipher) {
        free(cipher);
    }
}

// Encrypts a message. The returned ciphertext must be freed by the caller.
// The length of the ciphertext is returned via the ciphertext_len pointer.
uint8_t* AESCipher_encrypt(AESCipher* cipher, const uint8_t* msg, size_t msg_len, size_t* ciphertext_len) {
    // 1. Padding (PKCS#7)
    size_t pad_len = AES_BLOCK_SIZE - (msg_len % AES_BLOCK_SIZE);
    size_t padded_len = msg_len + pad_len;
    uint8_t* padded_msg = (uint8_t*)malloc(padded_len);
    if (!padded_msg) {
        return NULL;
    }
    memcpy(padded_msg, msg, msg_len);
    for (size_t i = 0; i < pad_len; ++i) {
        padded_msg[msg_len + i] = (uint8_t)pad_len;
    }

    // 2. Encryption (ECB Mode)
    uint8_t* ciphertext = (uint8_t*)malloc(padded_len);
    if (!ciphertext) {
        free(padded_msg);
        return NULL;
    }

    for (size_t i = 0; i < padded_len; i += AES_BLOCK_SIZE) {
        aes_encrypt_block(padded_msg + i, ciphertext + i, cipher->round_keys);
    }

    free(padded_msg);
    *ciphertext_len = padded_len;
    return ciphertext;
}

// Decrypts a ciphertext. The returned message must be freed by the caller.
// The length of the message is returned via the msg_len pointer.
uint8_t* AESCipher_decrypt(AESCipher* cipher, const uint8_t* ciphertext, size_t ciphertext_len, size_t* msg_len) {
    if (ciphertext_len % AES_BLOCK_SIZE != 0) {
        // Ciphertext must be a multiple of the block size
        return NULL;
    }

    // 1. Decryption (ECB Mode)
    uint8_t* decrypted_padded = (uint8_t*)malloc(ciphertext_len);
    if (!decrypted_padded) {
        return NULL;
    }

    for (size_t i = 0; i < ciphertext_len; i += AES_BLOCK_SIZE) {
        aes_decrypt_block(ciphertext + i, decrypted_padded + i, cipher->round_keys);
    }

    // 2. Remove Padding
    uint8_t pad_len = decrypted_padded[ciphertext_len - 1];
    if (pad_len == 0 || pad_len > AES_BLOCK_SIZE) {
        // Invalid padding
        free(decrypted_padded);
        return NULL;
    }
    
    // Verify padding bytes
    for(size_t i = 0; i < pad_len; ++i) {
        if (decrypted_padded[ciphertext_len - 1 - i] != pad_len) {
            // Invalid padding
            free(decrypted_padded);
            return NULL;
        }
    }

    *msg_len = ciphertext_len - pad_len;
    uint8_t* msg = (uint8_t*)malloc(*msg_len);
    if (!msg) {
        free(decrypted_padded);
        return NULL;
    }
    memcpy(msg, decrypted_padded, *msg_len);

    free(decrypted_padded);
    return msg;
}

// Helper function to print byte arrays in hex format
void print_hex(const char* label, const uint8_t* data, size_t len) {
    printf("%s (%zu bytes): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

// -- Main function for demonstration --
int main() {
    // A 16-byte (128-bit) key
    uint8_t key[AES_KEY_SIZE] = {
        0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6,
        0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c
    };

    // The message to encrypt
    const char* original_msg_str = "This is a secret message.";
    uint8_t* original_msg = (uint8_t*)original_msg_str;
    size_t original_msg_len = strlen(original_msg_str);

    printf("--- AES-128 ECB Example ---\n");
    print_hex("Key", key, AES_KEY_SIZE);
    printf("Original Message: %s\n\n", original_msg_str);

    // 1. Create the cipher object
    AESCipher* cipher = AESCipher_create(key);
    if (!cipher) {
        fprintf(stderr, "Failed to create cipher.\n");
        return 1;
    }

    // 2. Encrypt the message
    size_t ciphertext_len;
    uint8_t* ciphertext = AESCipher_encrypt(cipher, original_msg, original_msg_len, &ciphertext_len);
    if (!ciphertext) {
        fprintf(stderr, "Encryption failed.\n");
        AESCipher_destroy(cipher);
        return 1;
    }
    print_hex("Ciphertext (Hex)", ciphertext, ciphertext_len);

    // 3. Decrypt the message
    size_t decrypted_msg_len;
    uint8_t* decrypted_msg = AESCipher_decrypt(cipher, ciphertext, ciphertext_len, &decrypted_msg_len);
    if (!decrypted_msg) {
        fprintf(stderr, "Decryption failed.\n");
        free(ciphertext);
        AESCipher_destroy(cipher);
        return 1;
    }
    
    // Print decrypted message (add null terminator for printing as a string)
    char* decrypted_str = (char*)malloc(decrypted_msg_len + 1);
    memcpy(decrypted_str, decrypted_msg, decrypted_msg_len);
    decrypted_str[decrypted_msg_len] = '\0';
    printf("Decrypted Message: %s\n\n", decrypted_str);

    // 4. Verify and clean up
    if (original_msg_len == decrypted_msg_len && memcmp(original_msg, decrypted_msg, original_msg_len) == 0) {
        printf("Success: Original and decrypted messages match!\n");
    } else {
        printf("Failure: Messages do not match!\n");
    }

    free(ciphertext);
    free(decrypted_msg);
    free(decrypted_str);
    AESCipher_destroy(cipher);

    return 0;
}