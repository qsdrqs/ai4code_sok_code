package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/binary"
    "errors"
    "fmt"
    "io"
)

// Encrypt encrypts the given data using AES in CBC mode.
func Encrypt(data, key, iv []byte) ([]byte, error) {
    // Create a new AES cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new CBC mode.
    mode := cipher.NewCBCEncrypter(block, iv)

    // Encrypt the data.
    ct := make([]byte, mode.BlockSize()+len(data))
    ivCopy := make([]byte, block.BlockSize())
    copy(ivCopy, iv)
    copy(ct, ivCopy)
    mode.CryptBlocks(ct[block.BlockSize():], data)

    return ct, nil
}

// Decrypt decrypts the given data using AES in CBC mode.
func Decrypt(data, key, iv []byte) ([]byte, error) {
    // Check if the data is at least as long as the IV.
    if len(data) < aes.BlockSize() {
        return nil, errors.New("data is too short")
    }

    // Create a new AES cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new CBC mode.
    mode := cipher.NewCBCDecrypter(block, data[:aes.BlockSize()])

    // Decrypt the data.
    pt := make([]byte, len(data)-aes.BlockSize())
    mode.CryptBlocks(pt, data[aes.BlockSize():])

    return pt, nil
}

func main() {
    // Generate random data, key, and IV.
    data := []byte("a secret message")
    key := make([]byte, 32)
    iv := make([]byte, 16)
    if _, err := io.ReadFull(rand.Reader, key); err != nil {
        fmt.Println(err)
        return
    }
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        fmt.Println(err)
        return
    }

    // Encrypt the data.
    e, err := Encrypt(data, key, iv)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Decrypt the data.
    d, err := Decrypt(e, key, iv)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Check if the decrypted data matches the original data.
    if !fmt.Sprint(d) == fmt.Sprint(data) {
        fmt.Println("decrypted data does not match original data")
        return
    }

    fmt.Println("Decrypted data matches original data")
}