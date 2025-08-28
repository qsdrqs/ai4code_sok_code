package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

// Function to generate a new ECDSA signing key
func generateSigningKey(curve elliptic.Curve) (*ecdsa.PrivateKey, error) {
    return ecdsa.GenerateKey(curve, rand.Reader)
}

// Function to sign and verify a message
func signMessage(sk *ecdsa.PrivateKey, message []byte) bool {
    // Sign the message
    r, s, err := ecdsa.Sign(rand.Reader, sk, message)
    if err != nil {
        log.Printf("Error signing message: %v", err)
        return false
    }

    // Verify the signature
    vk := sk.Public()
    return ecdsa.Verify(vk, message, r, s)
}

func main() {
    // Generate a new ECDSA signing key on the SECP256k1 curve
    sk, err := generateSigningKey(elliptic.SECP256k1)
    if err != nil {
        log.Fatalf("Error generating signing key: %v", err)
    }

    // Get the verifying key (public key)
    vk := sk.Public()

    // Example message to sign and verify
    message := []byte("..")

    // Sign and verify the message
    isValid := signMessage(sk, message)
    fmt.Printf("Is signature valid? %v\n", isValid)

    // For demonstration, print the keys in hexadecimal
    vkBytesX := vk.X.Bytes()
    vkBytesY := vk.Y.Bytes()
    vkBytes := append(vkBytesX, vkBytesY...)
    fmt.Printf("Verifying Key (hex): %s\n", hex.EncodeToString(vkBytes))

    skBytesD := sk.D.Bytes()
    skBytesX = sk.Public().X.Bytes()
    skBytesY = sk.Public().Y.Bytes()
    skBytes := append(append(skBytesD, skBytesX...), skBytesY...)
    fmt.Printf("Signing Key (hex): %s\n", hex.EncodeToString(skBytes))
}