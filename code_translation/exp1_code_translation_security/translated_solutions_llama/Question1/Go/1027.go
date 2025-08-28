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

// Function to pad a byte slice to a multiple of block size
func pad(data []byte, blockSize int) []byte {
    paddingLength := blockSize - (len(data) % blockSize)
    paddedData := make([]byte, len(data)+paddingLength)
    copy(paddedData, data)
    for i := len(data); i < len(paddedData); i++ {
        paddedData[i] = byte(paddingLength)
    }
    return paddedData
}

// Function to unpad a byte slice
func unpad(data []byte, blockSize int) ([]byte, error) {
    if len(data) == 0 || len(data)%blockSize != 0 {
        return nil, errors.New("invalid data length")
    }
    paddingLength := int(data[len(data)-1])
    if paddingLength < 1 || paddingLength > blockSize {
        return nil, errors.New("invalid padding length")
    }
    return data[:len(data)-paddingLength], nil
}

// Function to encrypt a message using AES in CBC mode
func encrypt(message string, key []byte) ([]byte, error) {
    // Convert message to bytes
    messageBytes := []byte(message)

    // Pad message
    paddedMessage := pad(messageBytes, aes.BlockSize)

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new CBC mode
    iv := make([]byte, aes.BlockSize)
    _, err = io.ReadFull(rand.Reader, iv)
    if err != nil {
        return nil, err
    }
    stream := cipher.NewCBCEncrypter(block, iv)

    // Encrypt message
    ciphertext := make([]byte, len(iv)+len(paddedMessage))
    copy(ciphertext, iv)
    stream.CryptBlocks(ciphertext[aes.BlockSize:], paddedMessage)

    // Return ciphertext
    return ciphertext, nil
}

// Function to decrypt a ciphertext using AES in CBC mode
func decrypt(ciphertext []byte, key []byte) (string, error) {
    // Check if ciphertext is at least one block long
    if len(ciphertext) < aes.BlockSize {
        return "", errors.New("ciphertext is too short")
    }

    // Extract IV
    iv := ciphertext[:aes.BlockSize]

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Create a new CBC mode
    stream := cipher.NewCBCDecrypter(block, iv)

    // Decrypt message
    paddedMessage := make([]byte, len(ciphertext)-aes.BlockSize)
    stream.CryptBlocks(paddedMessage, ciphertext[aes.BlockSize:])

    // Unpad message
    messageBytes, err := unpad(paddedMessage, aes.BlockSize)
    if err != nil {
        return "", err
    }

    // Convert message to string
    message := string(messageBytes)

    // Return message
    return message, nil
}

func main() {
    key := []byte("0123456789012345") // 128-bit key
    message := "Hello, World!"
    fmt.Println("Original Message:", message)

    ciphertext, err := encrypt(message, key)
    if err != nil {
        fmt.Println("Error encrypting:", err)
        return
    }
    fmt.Println("Ciphertext:", base64.StdEncoding.EncodeToString(ciphertext))

    decryptedMessage, err := decrypt(ciphertext, key)
    if err != nil {
        fmt.Println("Error decrypting:", err)
        return
    }
    fmt.Println("Decrypted Message:", decryptedMessage)
}