package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "encoding/pem"
    "errors"
    "log"

    "golang.org/x/crypto/ecdsa"
)

// SignMessage signs a given message using a provided ECDSA signing key.
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
    // Hash the message
    hash := sha256.Sum256(message)

    // Sign the hashed message using the signing key
    r, s, err := ecdsa.SignASN1(rand.Reader, signingKey, hash[:])
    if err != nil {
        return nil, err
    }

    // Marshal the signature
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Example usage
    // Generate a new ECDSA private key
    privateKey, err := ecdsa.GenerateKey(ecdsa.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // PEM encode the private key for demonstration
    privateKeyPEM := pem.EncodeToMemory(
        &pem.Block{Type: "EC PRIVATE KEY", Bytes: x509.MarshalPKCS1PrivateKey(privateKey)},
    )

    log.Println("Private Key (PEM):")
    log.Println(string(privateKeyPEM))

    // Message to sign
    message := []byte("Hello, World!")

    // Sign the message
    signature, err := SignMessage(message, privateKey)
    if err != nil {
        log.Fatal(err)
    }

    log.Println("Signature:")
    log.Println(signature)
}