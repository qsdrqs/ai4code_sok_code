package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

func main() {
    // Generate a new private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Get the public key from the private key
    publicKey := privateKey.Public()

    // Message to be signed
    message := []byte("Test message")

    // Sign the message
    signature, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    valid := ecdsa.VerifyASN1(publicKey, message, signature)

    fmt.Println(valid)
}