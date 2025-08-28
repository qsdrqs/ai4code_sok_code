package main

import (
    "crypto/aes"
    "crypto/cipher"
    "errors"
)

type AESCipher struct {
    key   []byte
    blkSz int
}

func NewAESCipher(key []byte, blkSz int) *AESCipher {
    return &AESCipher{
        key:   key,
        blkSz: blkSz,
    }
}

func (a *AESCipher) Encrypt(msg []byte) ([]byte, error) {
    // Padding
    padLen := a.blkSz - (len(msg) % a.blkSz)
    padding := make([]byte, padLen)
    for i := range padding {
        padding[i] = byte(padLen)
    }
    msg = append(msg, padding...)
    
    // Encryption
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return nil, err
    }
    
    ciphertext := make([]byte, len(msg))
    
    // ECB mode encryption (encrypt each block separately)
    for i := 0; i < len(msg); i += a.blkSz {
        block.Encrypt(ciphertext[i:i+a.blkSz], msg[i:i+a.blkSz])
    }
    
    return ciphertext, nil
}

func (a *AESCipher) Decrypt(ciphertext []byte) ([]byte, error) {
    // Decryption
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return nil, err
    }
    
    if len(ciphertext)%a.blkSz != 0 {
        return nil, errors.New("ciphertext length is not a multiple of block size")
    }
    
    msg := make([]byte, len(ciphertext))
    
    // ECB mode decryption (decrypt each block separately)
    for i := 0; i < len(ciphertext); i += a.blkSz {
        block.Decrypt(msg[i:i+a.blkSz], ciphertext[i:i+a.blkSz])
    }
    
    // Remove padding
    if len(msg) == 0 {
        return nil, errors.New("empty message")
    }
    
    padLen := int(msg[len(msg)-1])
    if padLen > len(msg) || padLen == 0 {
        return nil, errors.New("invalid padding")
    }
    
    return msg[:len(msg)-padLen], nil
}

// Example usage:
func main() {
    // Example key (16 bytes for AES-128)
    key := []byte("1234567890123456")
    
    // Create cipher with block size 16 (AES block size)
    cipher := NewAESCipher(key, 16)
    
    // Example message
    message := []byte("Hello, World!")
    
    // Encrypt
    encrypted, err := cipher.Encrypt(message)
    if err != nil {
        panic(err)
    }
    
    // Decrypt
    decrypted, err := cipher.Decrypt(encrypted)
    if err != nil {
        panic(err)
    }
    
    println("Original:", string(message))
    println("Decrypted:", string(decrypted))
}