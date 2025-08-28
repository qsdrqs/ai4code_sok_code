package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

// sign generates a digital signature for a given message using a private key.
func sign(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
    // Convert the message to bytes
    messageBytes := []byte(message)

    // Generate a digital signature
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, messageBytes)
    if err != nil {
        return nil, err
    }

    // Combine r and s into a single byte slice
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Generate a new private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Sign a message
    message := "something"
    signature, err := sign(message, privateKey)
    if err != nil {
        log.Fatal(err)
    }

    // Print the signature in hexadecimal
    fmt.Println(hex.EncodeToString(signature))
}