package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "log"
)

func main() {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Get the verifying key (public key)
    vk := sk.Public()

    // Test the signature
    msg := []byte("message")
    signature, err := sk.Sign(rand.Reader, msg, nil)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    valid := vk.Verify(msg, signature)
    if !valid {
        log.Fatal("Invalid signature")
    }

    log.Println("Signature is valid")
}