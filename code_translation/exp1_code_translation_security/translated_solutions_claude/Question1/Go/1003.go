package main

import (
    "crypto/aes"
    "fmt"
)

func encryptBytesAES(data, key []byte) ([]byte, error) {
    cipher, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    
    // Check if data length is multiple of block size (16 bytes for AES)
    if len(data)%aes.BlockSize != 0 {
        return nil, fmt.Errorf("data length must be multiple of %d bytes", aes.BlockSize)
    }
    
    encrypted := make([]byte, len(data))
    
    // Encrypt each block
    for i := 0; i < len(data); i += aes.BlockSize {
        cipher.Encrypt(encrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
    }
    
    return encrypted, nil
}

func decryptBytesAES(data, key []byte) ([]byte, error) {
    cipher, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    
    // Check if data length is multiple of block size (16 bytes for AES)
    if len(data)%aes.BlockSize != 0 {
        return nil, fmt.Errorf("data length must be multiple of %d bytes", aes.BlockSize)
    }
    
    decrypted := make([]byte, len(data))
    
    // Decrypt each block
    for i := 0; i < len(data); i += aes.BlockSize {
        cipher.Decrypt(decrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
    }
    
    return decrypted, nil
}