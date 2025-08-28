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

// Encrypt encrypts a message using AES in EAX mode.
func Encrypt(plaintext []byte, sk []byte) ([]byte, []byte, error) {
    // Generate a random 96-bit IV.
    iv := make([]byte, 12)
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, nil, err
    }

    // Create a new AES cipher.
    block, err := aes.NewCipher(sk)
    if err != nil {
        return nil, nil, err
    }

    // Create a new EAX stream.
    eax, err := cipher.NewCTRMode(block, iv)
    if err != nil {
        return nil, nil, err
    }

    // Encrypt the plaintext.
    ciphertext := make([]byte, len(plaintext))
    eax.XORKeyStream(ciphertext, plaintext)

    return ciphertext, iv, nil
}

// Decrypt decrypts a ciphertext using AES in EAX mode.
func Decrypt(ciphertext []byte, iv []byte, sk []byte) ([]byte, error) {
    // Create a new AES cipher.
    block, err := aes.NewCipher(sk)
    if err != nil {
        return nil, err
    }

    // Create a new EAX stream.
    eax, err := cipher.NewCTRMode(block, iv)
    if err != nil {
        return nil, err
    }

    // Decrypt the ciphertext.
    plaintext := make([]byte, len(ciphertext))
    eax.XORKeyStream(plaintext, ciphertext)

    return plaintext, nil
}

func main() {
    // Example usage.
    sk := []byte{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15}
    m := []byte("Hello, World!")

    ciphertext, iv, err := Encrypt(m, sk)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Ciphertext:", ciphertext)
    fmt.Println("IV:", iv)

    decrypted, err := Decrypt(ciphertext, iv, sk)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Decrypted:", string(decrypted))
}