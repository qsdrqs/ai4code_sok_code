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

// GenerateKey generates a Fernet key
func GenerateKey() ([]byte, error) {
    key := make([]byte, 32)
    _, err := io.ReadFull(rand.Reader, key)
    if err != nil {
        return nil, err
    }
    return key, nil
}

// Encrypt encrypts the input using the provided key
func Encrypt(input string, key []byte) ([]byte, error) {
    // Fernet key is base64 encoded
    if len(key) != 32 {
        return nil, errors.New("invalid key length")
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Generate a random 128-bit IV
    iv := make([]byte, 16)
    _, err = io.ReadFull(rand.Reader, iv)
    if err != nil {
        return nil, err
    }

    // Create a new CBC mode
    stream := cipher.NewCFBEncrypter(block, iv)

    // Encrypt the input
    encMessage := make([]byte, len(iv)+len(input))
    copy(encMessage[:16], iv)
    stream.XORKeyStream(encMessage[16:], []byte(input))

    // Return base64 encoded encrypted message
    return base64.StdEncoding.EncodeToString(encMessage), nil
}

// Decrypt decrypts the input using the provided key
func Decrypt(encMessage string, key []byte) (string, error) {
    // Fernet key is base64 encoded
    if len(key) != 32 {
        return "", errors.New("invalid key length")
    }

    // Decode base64 encoded encrypted message
    encMessageBytes, err := base64.StdEncoding.DecodeString(encMessage)
    if err != nil {
        return "", err
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return "", err
    }

    // Extract IV
    if len(encMessageBytes) < 16 {
        return "", errors.New("invalid encrypted message length")
    }
    iv := encMessageBytes[:16]

    // Create a new CBC mode
    stream := cipher.NewCFBDecrypter(block, iv)

    // Decrypt the input
    decMessage := make([]byte, len(encMessageBytes)-16)
    stream.XORKeyStream(decMessage, encMessageBytes[16:])

    // Return decrypted message
    return string(decMessage), nil
}

func main() {
    // Generate a Fernet key
    key, err := GenerateKey()
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Generated Key:", base64.StdEncoding.EncodeToString(key))

    // Encrypt a message
    input := "Hello, World!"
    encMessage, err := Encrypt(input, key)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Encrypted Message:", encMessage)

    // Decrypt the message
    decMessage, err := Decrypt(encMessage, key)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Decrypted Message:", decMessage)
}