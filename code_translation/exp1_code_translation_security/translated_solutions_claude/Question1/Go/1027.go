package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "fmt"
    "io"
)

// PKCS7 padding implementation
func pad(data []byte, blockSize int) []byte {
    padding := blockSize - len(data)%blockSize
    padtext := make([]byte, padding)
    for i := range padtext {
        padtext[i] = byte(padding)
    }
    return append(data, padtext...)
}

// PKCS7 unpadding implementation
func unpad(data []byte, blockSize int) ([]byte, error) {
    if len(data) == 0 {
        return nil, fmt.Errorf("data is empty")
    }
    
    padding := int(data[len(data)-1])
    if padding > blockSize || padding == 0 {
        return nil, fmt.Errorf("invalid padding")
    }
    
    if len(data) < padding {
        return nil, fmt.Errorf("invalid padding size")
    }
    
    // Verify padding
    for i := len(data) - padding; i < len(data); i++ {
        if data[i] != byte(padding) {
            return nil, fmt.Errorf("invalid padding")
        }
    }
    
    return data[:len(data)-padding], nil
}

func encrypt(message string, key []byte) ([]byte, error) {
    // Convert message to bytes
    messageBytes := []byte(message)
    
    // Pad message
    paddedMessage := pad(messageBytes, aes.BlockSize)
    
    // Create cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    
    // Create IV (initialization vector)
    iv := make([]byte, aes.BlockSize)
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, err
    }
    
    // Create CBC mode encrypter
    mode := cipher.NewCBCEncrypter(block, iv)
    
    // Encrypt message
    ciphertext := make([]byte, len(paddedMessage))
    mode.CryptBlocks(ciphertext, paddedMessage)
    
    // Prepend IV to ciphertext (common practice for CBC mode)
    result := append(iv, ciphertext...)
    
    return result, nil
}

func decrypt(ciphertext []byte, key []byte) (string, error) {
    if len(ciphertext) < aes.BlockSize {
        return "", fmt.Errorf("ciphertext too short")
    }
    
    // Extract IV from the beginning of ciphertext
    iv := ciphertext[:aes.BlockSize]
    ciphertext = ciphertext[aes.BlockSize:]
    
    // Create cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }
    
    // Create CBC mode decrypter
    mode := cipher.NewCBCDecrypter(block, iv)
    
    // Decrypt message
    decrypted := make([]byte, len(ciphertext))
    mode.CryptBlocks(decrypted, ciphertext)
    
    // Unpad message
    message, err := unpad(decrypted, aes.BlockSize)
    if err != nil {
        return "", err
    }
    
    // Convert message to string
    return string(message), nil
}

// Example usage
func main() {
    // Example 32-byte key for AES-256
    key := []byte("12345678901234567890123456789012")
    message := "Hello, World!"
    
    // Encrypt
    encrypted, err := encrypt(message, key)
    if err != nil {
        fmt.Printf("Encryption error: %v\n", err)
        return
    }
    
    fmt.Printf("Encrypted: %x\n", encrypted)
    
    // Decrypt
    decrypted, err := decrypt(encrypted, key)
    if err != nil {
        fmt.Printf("Decryption error: %v\n", err)
        return
    }
    
    fmt.Printf("Decrypted: %s\n", decrypted)
}