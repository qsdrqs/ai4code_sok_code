package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"

    "github.com/starkterm/elliptic"
)

func main() {
    // Generate Keys
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    publicKey := privateKey.Public()

    message := []byte("My test message")

    // Generate Signature
    signature, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
    if err != nil {
        log.Fatal(err)
    }

    // Verify if signature is valid
    valid := ecdsa.VerifyASN1(publicKey, message, signature)
    fmt.Println(valid)

    // For debugging purposes, you can print the keys and signature
    fmt.Println("Private Key:")
    fmt.Println(privateKey)
    fmt.Println("Public Key:")
    fmt.Println(publicKey)
    fmt.Println("Signature (hex):")
    fmt.Println(hex.Dump(signature))
}