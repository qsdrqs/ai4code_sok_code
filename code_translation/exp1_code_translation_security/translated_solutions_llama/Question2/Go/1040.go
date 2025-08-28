package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/base64"
    "encoding/hex"
    "errors"
    "fmt"
    "log"

    "github.com/btcsuite/btcutil/ecdsa"
)

// SignMessage signs a message using the provided private key.
func SignMessage(message []byte, privateKeyBytes []byte) ([]byte, error) {
    // Parse the private key from bytes.
    sk, err := ecdsa.ParsePrivateKey(privateKeyBytes, elliptic.Secp256k1)
    if err != nil {
        return nil, err
    }

    // Sign the message.
    r, s, err := ecdsa.Sign(rand.Reader, sk, message)
    if err != nil {
        return nil, err
    }

    // Marshal the signature in DER format.
    signature := ecdsa.Marshal(r, s)

    return signature, nil
}

func main() {
    // Example usage.
    privateKeyHex := "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"
    privateKeyBytes, err := hex.DecodeString(privateKeyHex)
    if err != nil {
        log.Fatal(err)
    }

    message := []byte("Hello, World!")

    signature, err := SignMessage(message, privateKeyBytes)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Signature (hex):", hex.EncodeToString(signature))
    fmt.Println("Signature (base64):", base64.StdEncoding.EncodeToString(signature))
}