package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "fmt"
)

// encrypt encrypts a message using AES-GCM (equivalent to AES-EAX)
func encrypt(m []byte, sk []byte) ([]byte, []byte, error) {
    block, err := aes.NewCipher(sk)
    if err != nil {
        return nil, nil, err
    }
    
    // Use GCM mode (similar to EAX mode)
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, nil, err
    }
    
    // Generate a random nonce
    nonce := make([]byte, gcm.NonceSize())
    if _, err := rand.Read(nonce); err != nil {
        return nil, nil, err
    }
    
    // Encrypt and authenticate
    ciphertext := gcm.Seal(nil, nonce, m, nil)
    
    return ciphertext, nonce, nil
}

// decrypt decrypts a ciphertext using AES-GCM
func decrypt(c []byte, nonce []byte, sk []byte) ([]byte, error) {
    block, err := aes.NewCipher(sk)
    if err != nil {
        return nil, err
    }
    
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }
    
    // Decrypt and verify
    plaintext, err := gcm.Open(nil, nonce, c, nil)
    if err != nil {
        return nil, err
    }
    
    return plaintext, nil
}

// Example usage
func main() {
    // Example key (32 bytes for AES-256)
    key := []byte("12345678901234567890123456789012")
    message := []byte("Hello, World!")
    
    // Encrypt
    ciphertext, nonce, err := encrypt(message, key)
    if err != nil {
        fmt.Printf("Encryption error: %v\n", err)
        return
    }
    
    fmt.Printf("Encrypted: %x\n", ciphertext)
    fmt.Printf("Nonce: %x\n", nonce)
    
    // Decrypt
    decrypted, err := decrypt(ciphertext, nonce, key)
    if err != nil {
        fmt.Printf("Decryption error: %v\n", err)
        return
    }
    
    fmt.Printf("Decrypted: %s\n", string(decrypted))
}