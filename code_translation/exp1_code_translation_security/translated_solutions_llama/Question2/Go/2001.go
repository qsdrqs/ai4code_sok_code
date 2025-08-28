package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/sha256"
    "encoding/pem"
    "log"

    "golang.org/x/crypto/ecdsa"
)

// Function that signs input with the given ECDSA private key
func sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
    // Create a new SHA256 hash of the message
    hash := sha256.Sum256(message)

    // Sign the hash with the private key
    r, s, err := ecdsa.SignASN1(privateKey, hash[:])
    if err != nil {
        return nil, err
    }

    // Marshal the signature
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Example usage
    // Generate a new ECDSA private key (for demonstration purposes)
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), nil)
    if err != nil {
        log.Fatal(err)
    }

    // PEM encode the private key (for demonstration purposes)
    privateKeyPEM := pem.EncodeToMemory(
        &pem.Block{Type: "EC PRIVATE KEY", Bytes: x509.MarshalPKCS1PrivateKey(privateKey)},
    )

    // Load the private key from PEM (for demonstration purposes)
    // privateKey, err := x509.ParsePKCS1PrivateKey(privateKeyPEM)
    // if err != nil {
    //     log.Fatal(err)
    // }

    message := []byte("Hello, World!")
    signature, err := sign(message, privateKey)
    if err != nil {
        log.Fatal(err)
    }

    log.Println("Signature:", signature)
}