package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/hex"
    "errors"
    "fmt"
    "io"
)

const (
    keySize = 16 // 128 bits
)

var (
    key = []byte("Sixteen byte key")
)

func encrypt(key, msg []byte) ([]byte, []byte, []byte, error) {
    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, nil, nil, err
    }

    // Create a new GCM mode
    stream := cipher.NewGCM(block)

    // Generate a random nonce
    nonce := make([]byte, stream.NonceSize())
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return nil, nil, nil, err
    }

    // Encrypt the message
    ciphertext := make([]byte, stream.Seal(nil, nonce, msg, nil))
    stream.Seal(ciphertext[:0], nonce, msg, nil)

    return ciphertext, nonce, ciphertext[:stream.NonceSize()], nil
}

func decrypt(key, ciphertext, nonce []byte) ([]byte, error) {
    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new GCM mode
    stream, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Decrypt the message
    if len(ciphertext) < stream.Overhead() {
        return nil, errors.New("ciphertext too short")
    }
    plaintext, err := stream.Open(nil, nonce, ciphertext[stream.NonceSize():], nil)
    if err != nil {
        return nil, err
    }

    return plaintext, nil
}

func main() {
    msg := []byte("message")
    if len(key) != keySize {
        fmt.Println("Invalid key size. Key must be", keySize, "bytes.")
        return
    }

    ctext, nonce, tag, err := encrypt(key, msg)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Ciphertext:", hex.EncodeToString(ctext))
    fmt.Println("Nonce:", hex.EncodeToString(nonce))
    fmt.Println("Tag:", hex.EncodeToString(tag))

    plaintext, err := decrypt(key, ctext, nonce)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Decrypted message:", string(plaintext))
}