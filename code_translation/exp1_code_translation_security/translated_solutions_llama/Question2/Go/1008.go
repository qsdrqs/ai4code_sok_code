package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "errors"
    "fmt"
    "log"
)

// signMessage signs a message using an ECDSA private key.
func signMessage(message string, privateKeyHex string) ([]byte, error) {
    // Decode the private key from hexadecimal
    privateKeyBytes, err := hex.DecodeString(privateKeyHex)
    if err != nil {
        return nil, err
    }

    // Create a new ECDSA private key from the bytes
    privateKey := new(ecdsa.PrivateKey)
    privateKey.Curve = elliptic.SECP256k1
    privateKey.D = new(big.Int).SetBytes(privateKeyBytes)

    // Sign the message
    messageBytes := []byte(message)
    r, s, err := ecdsa.Sign(rand.Reader, privateKey, messageBytes)
    if err != nil {
        return nil, err
    }

    // Convert the signature to a single byte slice
    rBytes := r.Bytes()
    sBytes := s.Bytes()
    // Ensure r and s are 32 bytes each
    if len(rBytes) < 32 {
        rBytes = append(make([]byte, 32-len(rBytes)), rBytes...)
    }
    if len(sBytes) < 32 {
        sBytes = append(make([]byte, 32-len(sBytes)), sBytes...)
    }
    signature := append(rBytes, sBytes...)
    return signature, nil
}

func main() {
    message := "hello world"
    privateKeyHex := "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"

    signature, err := signMessage(message, privateKeyHex)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println(signature)
}