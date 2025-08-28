package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "errors"
    "log"

    "github.com/btcsuite/btcutil/ecdsa"
)

// Sign signs a given message using a given ECDSA signing key.
func Sign(message []byte, privateKeyBytes []byte) ([]byte, error) {
    // Parse the private key from bytes
    privateKey, err := ecdsa.ParsePrivateKey(privateKeyBytes, elliptic.Secp256k1)
    if err != nil {
        return nil, err
    }

    // Sign the message using the ECDSA private key
    r, s, err := ecdsa.Sign(rand.Reader, privateKey, message)
    if err != nil {
        return nil, err
    }

    // Marshal the signature to bytes
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Example usage
    privateKeyBytes, err := hex.DecodeString("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef")
    if err != nil {
        log.Fatal(err)
    }

    message := []byte("Hello, World!")
    signedMessage, err := Sign(message, privateKeyBytes)
    if err != nil {
        log.Fatal(err)
    }

    log.Printf("Signed Message: %x\n", signedMessage)
}