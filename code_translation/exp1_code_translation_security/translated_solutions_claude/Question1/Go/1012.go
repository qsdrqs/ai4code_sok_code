package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "fmt"
    "io"
)

var key []byte

func init() {
    // Generate a 32-byte key for AES-256
    key = make([]byte, 32)
    if _, err := io.ReadFull(rand.Reader, key); err != nil {
        panic(err)
    }
}

func encryptText(plainText string) (string, error) {
    // Create cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Create GCM mode
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return "", err
    }

    // Generate nonce
    nonce := make([]byte, gcm.NonceSize())
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return "", err
    }

    // Encrypt the data
    ciphertext := gcm.Seal(nonce, nonce, []byte(plainText), nil)
    
    // Return base64 encoded string
    return base64.StdEncoding.EncodeToString(ciphertext), nil
}

func decryptText(encryptedText string) (string, error) {
    // Decode base64
    ciphertext, err := base64.StdEncoding.DecodeString(encryptedText)
    if err != nil {
        return "", err
    }

    // Create cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Create GCM mode
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return "", err
    }

    // Extract nonce and ciphertext
    nonceSize := gcm.NonceSize()
    if len(ciphertext) < nonceSize {
        return "", fmt.Errorf("ciphertext too short")
    }

    nonce, ciphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]

    // Decrypt
    plaintext, err := gcm.Open(nil, nonce, ciphertext, nil)
    if err != nil {
        return "", err
    }

    return string(plaintext), nil
}

// Example usage
func main() {
    plainText := "Hello, World!"
    
    encrypted, err := encryptText(plainText)
    if err != nil {
        fmt.Printf("Encryption error: %v\n", err)
        return
    }
    fmt.Printf("Encrypted: %s\n", encrypted)
    
    decrypted, err := decryptText(encrypted)
    if err != nil {
        fmt.Printf("Decryption error: %v\n", err)
        return
    }
    fmt.Printf("Decrypted: %s\n", decrypted)
}