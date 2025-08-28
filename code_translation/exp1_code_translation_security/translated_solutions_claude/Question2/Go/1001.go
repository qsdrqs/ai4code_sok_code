package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
)

func signusingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    // Hash the message using SHA256
    hash := sha256.Sum256(message)
    
    // Sign the hash using ECDSA
    signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
    if err != nil {
        return nil, fmt.Errorf("failed to sign message: %v", err)
    }
    
    return signature, nil
}