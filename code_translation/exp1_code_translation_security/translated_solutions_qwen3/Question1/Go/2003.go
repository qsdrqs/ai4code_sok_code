package main

import (
    "crypto/aes"
    "bytes"
)

// encode encrypts the input using AES in ECB mode.
// The input must be a multiple of the AES block size (16 bytes).
func encode(inp, key []byte) []byte {
    block, _ := aes.NewCipher(key)
    ciphertext := make([]byte, len(inp))
    for i := 0; i < len(inp); i += aes.BlockSize {
        block.Encrypt(ciphertext[i:i+aes.BlockSize], inp[i:i+aes.BlockSize])
    }
    return ciphertext
}

// decode decrypts the input using AES in ECB mode.
// The input must be a multiple of the AES block size (16 bytes).
func decode(inp, key []byte) []byte {
    block, _ := aes.NewCipher(key)
    plaintext := make([]byte, len(inp))
    for i := 0; i < len(inp); i += aes.BlockSize {
        block.Decrypt(plaintext[i:i+aes.BlockSize], inp[i:i+aes.BlockSize])
    }
    return plaintext
}

func main() {
    // Test input and key (both 16 bytes)
    inp := []byte("1111111111111111")
    key := []byte("1111111111111111")

    // Encode and then decode the input
    decoded := decode(encode(inp, key), key)

    // Assert that the decoded result matches the original input
    if !bytes.Equal(decoded, inp) {
        panic("Assertion failed: decoded data does not match original input")
    }
}