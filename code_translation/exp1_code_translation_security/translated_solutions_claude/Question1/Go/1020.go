package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "fmt"
)

// PKCS7 padding function
func pad(data []byte) []byte {
    blockSize := aes.BlockSize
    padding := blockSize - len(data)%blockSize
    padtext := make([]byte, padding)
    for i := range padtext {
        padtext[i] = byte(padding)
    }
    return append(data, padtext...)
}

// PKCS7 unpadding function
func unpad(data []byte) []byte {
    if len(data) == 0 {
        return data
    }
    padding := int(data[len(data)-1])
    if padding > len(data) || padding > aes.BlockSize {
        return data // Invalid padding
    }
    return data[:len(data)-padding]
}

func encrypt(key []byte, keySize int, plaintext []byte) ([]byte, error) {
    // Generate random IV
    iv := make([]byte, aes.BlockSize)
    if _, err := rand.Read(iv); err != nil {
        return nil, err
    }
    
    // Create AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    
    // Pad the plaintext
    paddedTxt := pad(plaintext)
    
    // Create CBC mode cipher
    mode := cipher.NewCBCEncrypter(block, iv)
    
    // Encrypt the padded plaintext
    cipherTxt := make([]byte, len(paddedTxt))
    mode.CryptBlocks(cipherTxt, paddedTxt)
    
    // Prepend IV to ciphertext and encode to base64
    combined := append(iv, cipherTxt...)
    encoded := make([]byte, base64.StdEncoding.EncodedLen(len(combined)))
    base64.StdEncoding.Encode(encoded, combined)
    
    return encoded, nil
}

func decrypt(key []byte, keySize int, ciphertext []byte) ([]byte, error) {
    // Decode from base64
    decoded := make([]byte, base64.StdEncoding.DecodedLen(len(ciphertext)))
    n, err := base64.StdEncoding.Decode(decoded, ciphertext)
    if err != nil {
        return nil, err
    }
    decoded = decoded[:n]
    
    // Extract IV (first 16 bytes)
    if len(decoded) < aes.BlockSize {
        return nil, fmt.Errorf("ciphertext too short")
    }
    iv := decoded[:aes.BlockSize]
    cipherData := decoded[aes.BlockSize:]
    
    // Create AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    
    // Create CBC mode cipher
    mode := cipher.NewCBCDecrypter(block, iv)
    
    // Decrypt the ciphertext
    plaintext := make([]byte, len(cipherData))
    mode.CryptBlocks(plaintext, cipherData)
    
    // Remove padding
    return unpad(plaintext), nil
}

// Example usage
func main() {
    key := []byte("1234567890123456") // 16 bytes for AES-128
    plaintext := []byte("Hello, World!")
    
    // Encrypt
    encrypted, err := encrypt(key, 128, plaintext)
    if err != nil {
        fmt.Printf("Encryption error: %v\n", err)
        return
    }
    fmt.Printf("Encrypted: %s\n", string(encrypted))
    
    // Decrypt
    decrypted, err := decrypt(key, 128, encrypted)
    if err != nil {
        fmt.Printf("Decryption error: %v\n", err)
        return
    }
    fmt.Printf("Decrypted: %s\n", string(decrypted))
}