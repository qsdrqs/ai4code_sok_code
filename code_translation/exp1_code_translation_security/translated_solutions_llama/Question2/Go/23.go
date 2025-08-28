package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

// GenerateKey generates a new ECDSA private key.
func generateKey() (*ecdsa.PrivateKey, error) {
    return ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
}

// Sign signs a message with the provided private key.
func sign(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
    r, s, err := ecdsa.Sign(rand.Reader, key, message)
    if err != nil {
        return nil, err
    }

    // Convert the signature to a single byte slice.
    // ECDSA signatures are (r, s) pairs, so we need to encode them.
    sig := append(r.Bytes(), s.Bytes()...)
    return sig, nil
}

// Verify verifies a signature with the provided public key.
func verify(message, signature []byte, key *ecdsa.PublicKey) bool {
    r := new(big.Int).SetBytes(signature[:len(signature)/2])
    s := new(big.Int).SetBytes(signature[len(signature)/2:])

    return ecdsa.Verify(key, message, r, s)
}

func main() {
    // Generate a new private key.
    key, err := generateKey()
    if err != nil {
        log.Fatal(err)
    }

    // Sign a message.
    message := []byte("i am a message")
    signature, err := sign(message, key)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature.
    valid := verify(message, signature, key.Public())
    fmt.Printf("Signature valid: %t\n", valid)

    // Print the signature in hexadecimal.
    fmt.Printf("Signature: %s\n", hex.EncodeToString(signature))
}