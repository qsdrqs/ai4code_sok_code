package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "errors"
    "fmt"
    "io"
)

// Function to generate a symmetric key
func generateKey() ([]byte, error) {
    // Generate a random key
    key := make([]byte, 16)
    _, err := io.ReadFull(rand.Reader, key)
    if err != nil {
        return nil, err
    }
    return key, nil
}

// Function to encrypt a string using a symmetric key
func encrypt(key []byte, data string) ([]byte, []byte, error) {
    // Convert the data to bytes
    dataBytes := []byte(data)

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, nil, err
    }

    // Generate a random nonce
    nonce := make([]byte, 12)
    _, err = io.ReadFull(rand.Reader, nonce)
    if err != nil {
        return nil, nil, err
    }

    // Create a GCM mode
    stream := cipher.NewCTR(block, nonce)

    // Encrypt the data
    ciphertext := make([]byte, len(dataBytes))
    stream.XORKeyStream(ciphertext, dataBytes)

    return ciphertext, nonce, nil
}

// Function to decrypt a string
func decrypt(key []byte, nonce []byte, ciphertext []byte) ([]byte, error) {
    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a GCM mode
    stream := cipher.NewCTR(block, nonce)

    // Decrypt the data
    data := make([]byte, len(ciphertext))
    stream.XORKeyStream(data, ciphertext)

    return data, nil
}

func main() {
    // Example usage
    key, err := generateKey()
    if err != nil {
        fmt.Println(err)
        return
    }

    testData := "Jim's test"
    encrypted, nonce, err := encrypt(key, testData)
    if err != nil {
        fmt.Println(err)
        return
    }

    restoredData, err := decrypt(key, nonce, encrypted)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println(string(restoredData))
}