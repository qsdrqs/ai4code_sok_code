package main

import (
    "crypto/ecdsa"
    "crypto/sha256"
    "encoding/pem"
    "errors"
    "log"

    "golang.org/x/crypto/pkix"
)

// signUsingECDSA signs a message using ECDSA with SHA256 hashing.
func signUsingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    if key == nil {
        return nil, errors.New("private key is nil")
    }

    // Create a new SHA256 hash of the message.
    hash := sha256.Sum256(message)

    // Sign the hash using ECDSA.
    r, s, err := ecdsa.SignASN1(key, hash[:])
    if err != nil {
        return nil, err
    }

    // DER encode the signature.
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Example usage:
    // Assuming you have a private key in PEM format.
    privateKeyPEM := []byte(`-----BEGIN PRIVATE KEY-----
...your_private_key_here...
-----END PRIVATE KEY-----`)

    // Load the private key.
    block, _ := pem.Decode(privateKeyPEM)
    if block == nil {
        log.Fatal("failed to decode private key PEM")
    }

    // Parse the private key.
    key, err := x509.ParsePKCS8PrivateKey(block.Bytes)
    if err != nil {
        log.Fatal(err)
    }

    ecdsaKey, ok := key.(*ecdsa.PrivateKey)
    if !ok {
        log.Fatal("not an ECDSA private key")
    }

    message := []byte("Hello, World!")
    signature, err := signUsingECDSA(ecdsaKey, message)
    if err != nil {
        log.Fatal(err)
    }

    log.Printf("Signature: %x\n", signature)
}