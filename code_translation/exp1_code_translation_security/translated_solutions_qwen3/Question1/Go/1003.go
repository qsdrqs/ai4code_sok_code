package main

import (
    "crypto/aes"
    "errors"
)

// EncryptBytesAES encrypts the given data using AES in ECB mode with the provided key.
// The key must be 16, 24, or 32 bytes long (AES-128, AES-192, or AES-256).
// The data must be a multiple of 16 bytes in length (AES block size).
func EncryptBytesAES(data, key []byte) ([]byte, error) {
    // Validate key size
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size: must be 16, 24, or 32 bytes")
    }

    // Validate data length
    if len(data)%16 != 0 {
        return nil, errors.New("data length must be a multiple of 16 bytes")
    }

    // Create a new AES block cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Prepare the output buffer
    ciphertext := make([]byte, len(data))

    // Encrypt each block independently (ECB mode)
    for i := 0; i < len(data); i += 16 {
        block.Encrypt(ciphertext[i:i+16], data[i:i+16])
    }

    return ciphertext, nil
}

// DecryptBytesAES decrypts the given data using AES in ECB mode with the provided key.
// The key must be 16, 24, or 32 bytes long (AES-128, AES-192, or AES-256).
// The data must be a multiple of 16 bytes in length (AES block size).
func DecryptBytesAES(data, key []byte) ([]byte, error) {
    // Validate key size
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size: must be 16, 24, or 32 bytes")
    }

    // Validate data length
    if len(data)%16 != 0 {
        return nil, errors.New("data length must be a multiple of 16 bytes")
    }

    // Create a new AES block cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Prepare the output buffer
    plaintext := make([]byte, len(data))

    // Decrypt each block independently (ECB mode)
    for i := 0; i < len(data); i += 16 {
        block.Decrypt(plaintext[i:i+16], data[i:i+16])
    }

    return plaintext, nil
}