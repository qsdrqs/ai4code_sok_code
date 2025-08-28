package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "errors"
    "io"
)

// encrypt encrypts the input string using AES-GCM with the provided key
func encrypt(input string, key []byte) ([]byte, error) {
    // Create AES cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create GCM mode
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Generate random nonce
    nonce := make([]byte, gcm.NonceSize())
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return nil, err
    }

    // Encrypt the data
    ciphertext := gcm.Seal(nonce, nonce, []byte(input), nil)
    
    // Encode to base64 for easier handling (similar to Fernet's output)
    encoded := make([]byte, base64.StdEncoding.EncodedLen(len(ciphertext)))
    base64.StdEncoding.Encode(encoded, ciphertext)
    
    return encoded, nil
}

// decrypt decrypts the input bytes using AES-GCM with the provided key
func decrypt(input []byte, key []byte) (string, error) {
    // Decode from base64
    ciphertext := make([]byte, base64.StdEncoding.DecodedLen(len(input)))
    n, err := base64.StdEncoding.Decode(ciphertext, input)
    if err != nil {
        return "", err
    }
    ciphertext = ciphertext[:n]

    // Create AES cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Create GCM mode
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return "", err
    }

    // Check minimum ciphertext length
    nonceSize := gcm.NonceSize()
    if len(ciphertext) < nonceSize {
        return "", errors.New("ciphertext too short")
    }

    // Extract nonce and ciphertext
    nonce, ciphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]

    // Decrypt the data
    plaintext, err := gcm.Open(nil, nonce, ciphertext, nil)
    if err != nil {
        return "", err
    }

    return string(plaintext), nil
}

// generateKey generates a 32-byte key for AES-256
func generateKey() []byte {
    key := make([]byte, 32) // 32 bytes for AES-256
    rand.Read(key)
    return key
}

// Example usage:
func main() {
    // Generate a key (in practice, you'd want to store this securely)
    key := generateKey()
    
    // Encrypt
    message := "Hello, World!"
    encrypted, err := encrypt(message, key)
    if err != nil {
        panic(err)
    }
    
    // Decrypt
    decrypted, err := decrypt(encrypted, key)
    if err != nil {
        panic(err)
    }
    
    println("Original:", message)
    println("Encrypted:", string(encrypted))
    println("Decrypted:", decrypted)
}