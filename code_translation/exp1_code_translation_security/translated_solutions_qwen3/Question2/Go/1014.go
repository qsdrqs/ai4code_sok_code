package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
)

// signMessage signs a given message using a given ECDSA signing key.
// It returns the DER-encoded signature of the message.
func signMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
    // Hash the message using SHA-256
    messageHash := sha256.Sum256(message)

    // Sign the message hash using the ECDSA private key
    signature, err := signingKey.Sign(rand.Reader, messageHash[:], nil)
    if err != nil {
        return nil, err
    }

    return signature, nil
}