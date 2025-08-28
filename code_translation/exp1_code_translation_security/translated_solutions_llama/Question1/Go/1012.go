package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "crypto/sha256"
    "encoding/base64"
    "errors"
    "fmt"
    "io"
)

// Key generation and encryption/decryption functions
var key []byte

func generateKey() ([]byte, error) {
    // Generate a random 32-byte key
    key = make([]byte, 32)
    _, err := io.ReadFull(rand.Reader, key)
    if err != nil {
        return nil, err
    }
    return key, nil
}

func encryptText(plainText string) (string, error) {
    if key == nil {
        return "", errors.New("key not generated")
    }

    // Create a new Fernet-like cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Generate a random 128-bit IV
    iv := make([]byte, 12)
    _, err = io.ReadFull(rand.Reader, iv)
    if err != nil {
        return "", err
    }

    // Create a new GCM mode
    stream := cipher.NewCTR(block, iv)

    // Pad the plaintext
    paddedText := pad(plainText)

    // Encrypt the padded plaintext
    encryptedText := make([]byte, len(paddedText))
    stream.XORKeyStream(encryptedText, []byte(paddedText))

    // Return the IV and encrypted text as a base64-encoded string
    return base64.StdEncoding.EncodeToString(append(iv, encryptedText...)), nil
}

func decryptText(encryptedText string) (string, error) {
    if key == nil {
        return "", errors.New("key not generated")
    }

    // Decode the base64-encoded string
    decodedText, err := base64.StdEncoding.DecodeString(encryptedText)
    if err != nil {
        return "", err
    }

    // Extract the IV and encrypted text
    if len(decodedText) < 12 {
        return "", errors.New("invalid encrypted text")
    }
    iv := decodedText[:12]
    encryptedTextBytes := decodedText[12:]

    // Create a new Fernet-like cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Create a new GCM mode
    stream := cipher.NewCTR(block, iv)

    // Decrypt the encrypted text
    decryptedText := make([]byte, len(encryptedTextBytes))
    stream.XORKeyStream(decryptedText, encryptedTextBytes)

    // Unpad the decrypted text
    unpaddedText, err := unpad(decryptedText)
    if err != nil {
        return "", err
    }

    return string(unpaddedText), nil
}

// Helper functions for padding and unpadding
const blockSize = 16

func pad(s string) []byte {
    b := []byte(s)
    padding := blockSize - len(b)%blockSize
    for i := 0; i < padding; i++ {
        b = append(b, byte(padding))
    }
    return b
}

func unpad(b []byte) ([]byte, error) {
    if len(b) == 0 {
        return nil, errors.New("empty byte slice")
    }
    padding := int(b[len(b)-1])
    if padding < 1 || padding > blockSize {
        return nil, errors.New("invalid padding")
    }
    if len(b) < padding {
        return nil, errors.New("invalid padding")
    }
    return b[:len(b)-padding], nil
}

func main() {
    // Generate a new key
    key, err := generateKey()
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Generated Key:", base64.StdEncoding.EncodeToString(key))

    // Test encryption and decryption
    plainText := "Hello, World!"
    encryptedText, err := encryptText(plainText)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Encrypted Text:", encryptedText)

    decryptedText, err := decryptText(encryptedText)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Decrypted Text:", decryptedText)
}