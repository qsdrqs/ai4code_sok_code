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

// GenerateKey generates a new AES-256-CBC key.
func GenerateKey() ([]byte, error) {
    key := make([]byte, 32)
    _, err := io.ReadFull(rand.Reader, key)
    if err != nil {
        return nil, err
    }
    return key, nil
}

// Encrypt encrypts a message using a symmetric key.
func Encrypt(key, message []byte) ([]byte, error) {
    // Generate a random IV.
    iv := make([]byte, 12)
    _, err := io.ReadFull(rand.Reader, iv)
    if err != nil {
        return nil, err
    }

    // Create a new AES-256-GCM cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    stream := cipher.NewCTR(block, iv)

    // Encrypt the message.
    encMessage := make([]byte, len(iv)+len(message))
    copy(encMessage[:12], iv)
    stream.XORKeyStream(encMessage[12:], message)

    return encMessage, nil
}

// Decrypt decrypts an encrypted message using a symmetric key.
func Decrypt(key, encMessage []byte) ([]byte, error) {
    if len(encMessage) < 12 {
        return nil, errors.New("invalid encrypted message")
    }

    // Extract the IV.
    iv := encMessage[:12]

    // Create a new AES-256-GCM cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }
    stream := cipher.NewCTR(block, iv)

    // Decrypt the message.
    decMessage := make([]byte, len(encMessage)-12)
    stream.XORKeyStream(decMessage, encMessage[12:])

    return decMessage, nil
}

func main() {
    message := "the solutions are here somewhere"
    fmt.Println("Secret Message:", message)

    // Generate a new key.
    key, err := GenerateKey()
    if err != nil {
        fmt.Println(err)
        return
    }

    // Encrypt the message.
    encMessage, err := Encrypt(key, []byte(message))
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Encrypted Message:", base64.StdEncoding.EncodeToString(encMessage))

    // Decrypt the message.
    decMessage, err := Decrypt(key, encMessage)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Decrypted Message:", string(decMessage))
}