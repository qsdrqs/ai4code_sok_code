package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
    "log"
)

func signMessage(message string, key *ecdsa.PrivateKey) ([]byte, error) {
    // Hash the message
    hash := sha256.Sum256([]byte("message"))
    
    // Sign the hash
    r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
    if err != nil {
        return nil, err
    }
    
    // Combine r and s into a single signature
    // This is a simple concatenation - in practice you might want to use ASN.1 DER encoding
    signature := append(r.Bytes(), s.Bytes()...)
    
    return signature, nil
}

// Example usage:
func main() {
    // Generate a private key for demonstration
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }
    
    signature, err := signMessage("test message", privateKey)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Printf("Signature: %x\n", signature)
}