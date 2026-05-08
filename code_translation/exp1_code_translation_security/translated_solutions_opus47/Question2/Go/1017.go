package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// SignMessage signs a given message using a given ECDSA signing key
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message first (ECDSA in Go requires a pre-hashed message)
	hash := sha256.Sum256(message)

	// Sign the message using the signing key
	signature, err := ecdsa.SignASN1(rand.Reader, signingKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Return the signature
	return signature, nil
}