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

// Function to encrypt a string using AES
func encryptString(message string, obj cipher.Stream) ([]byte, error) {
    // Convert the message to bytes
    messageBytes := []byte(message)

    // Create a buffer to hold the encrypted message
    encryptedMessage := make([]byte, len(messageBytes))

    // Encrypt the message
    obj.XORKeyStream(encryptedMessage, messageBytes)

    return encryptedMessage, nil
}

// Function to decrypt a string using AES
func decryptString(ciphertext []byte, obj cipher.Stream) ([]byte, error) {
    // Create a buffer to hold the decrypted message
    decryptedMessage := make([]byte, len(ciphertext))

    // Decrypt the message
    obj.XORKeyStream(decryptedMessage, ciphertext)

    return decryptedMessage, nil
}

func main() {
    // Define the key and IV
    key := []byte("This is a key123")
    iv := []byte("This is an IV456")

    // Create a new AES cipher object
    block, err := aes.NewCipher(key)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Create a new CFB stream
    stream := cipher.NewCFBDecrypter(block, iv)

    // Create another stream for decryption (same key and IV)
    stream2 := cipher.NewCFBDecrypter(block, iv)

    // Define the message to encrypt
    message := "The answer is no"

    // Encrypt the message
    enc, err := encryptString(message, stream)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Decrypt the message
    dec, err := decryptString(enc, stream2)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Print the results
    fmt.Println("Encrypted message (base64):", base64.StdEncoding.EncodeToString(enc))
    fmt.Println("Decrypted message:", string(dec))
}