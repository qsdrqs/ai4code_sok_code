package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
    "math/big"
)

// Signature represents an ECDSA signature
type Signature struct {
    R, S *big.Int
}

// Function that signs input with the given ECDSA private key
func sign(message []byte, privateKey *ecdsa.PrivateKey) (*Signature, error) {
    // Hash the message using SHA256
    hash := sha256.Sum256(message)
    
    // Sign the hash
    r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
    if err != nil {
        return nil, fmt.Errorf("failed to sign message: %v", err)
    }
    
    return &Signature{R: r, S: s}, nil
}