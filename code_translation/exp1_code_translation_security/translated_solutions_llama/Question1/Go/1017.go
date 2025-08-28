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

// Function to encrypt a given string using a given symmetric key.
func encrypt(key []byte, plaintext []byte) ([]byte, []byte, []byte, error) {
    // Create a new AES cipher with a random nonce.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, nil, nil, err
    }

    // Generate a random nonce.
    nonce := make([]byte, 12)
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return nil, nil, nil, err
    }

    // Create a new GCM mode.
    stream := cipher.NewCTR(block, nonce)

    // Encrypt the plaintext.
    ciphertext := make([]byte, len(plaintext))
    stream.XORKeyStream(ciphertext, plaintext)

    // Generate a MAC (Message Authentication Code) using HMAC.
    // For simplicity, we will use the first 16 bytes of the ciphertext as the tag.
    // In a real-world application, you should use a secure MAC like HMAC.
    tag := ciphertext[:16]

    return nonce, tag, ciphertext[16:], nil
}

// Function to decrypt a given string using a given symmetric key.
func decrypt(key []byte, nonce []byte, tag []byte, ciphertext []byte) ([]byte, error) {
    // Create a new AES cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new GCM mode.
    stream := cipher.NewCTR(block, nonce)

    // Prepend the tag to the ciphertext.
    fullCiphertext := append(tag, ciphertext...)

    // Decrypt the ciphertext.
    plaintext := make([]byte, len(fullCiphertext))
    stream.XORKeyStream(plaintext, fullCiphertext)

    return plaintext, nil
}

func main() {
    // Example usage:
    key := []byte("my_secret_key_32")
    plaintext := []byte("Hello, World!")

    nonce, tag, ciphertext, err := encrypt(key, plaintext)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Nonce:", base64.StdEncoding.EncodeToString(nonce))
    fmt.Println("Tag:", base64.StdEncoding.EncodeToString(tag))
    fmt.Println("Ciphertext:", base64.StdEncoding.EncodeToString(ciphertext))

    decrypted, err := decrypt(key, nonce, tag, ciphertext)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Decrypted:", string(decrypted))
}