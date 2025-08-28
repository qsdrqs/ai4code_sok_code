package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/sha256"
    "encoding/hex"
    "fmt"
    "log"
)

// SignMessage signs a given message using a given ECDSA private key
func SignMessage(message string, privateKeyBytes []byte) ([]byte, error) {
    // Hash the message
    messageHash := sha256.Sum256([]byte(message))

    // Create a new ECDSA private key object
    privateKey := new(ecdsa.PrivateKey)
    privateKey.Curve = elliptic.SECP256k1
    privateKey.D = new(big.Int).SetBytes(privateKeyBytes)

    x, y := elliptic.GenerateKey(privateKey.Curve, privateKey.D)
    privateKey.PublicKey.X = x
    privateKey.PublicKey.Y = y

    // Sign the message hash
    r, s, err := ecdsa.SignASN1(privateKey, messageHash[:])
    if err != nil {
        return nil, err
    }

    // Convert signature to DER format
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Test function
    message := "Hello, world!"
    privateKeyHex := "18e14a7b6a307f426a94f8114701e7c8e774e7f9a47e2c2035db29a206321725"
    privateKeyBytes, err := hex.DecodeString(privateKeyHex)
    if err != nil {
        log.Fatal(err)
    }

    signature, err := SignMessage(message, privateKeyBytes)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println(signature)
}