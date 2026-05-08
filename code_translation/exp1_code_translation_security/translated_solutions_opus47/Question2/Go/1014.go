package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// SignMessage signs a given message using a given ECDSA signing key
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256(message)

	// Sign the message hash with DER (ASN.1) encoding
	signature, err := ecdsa.SignASN1(rand.Reader, signingKey, messageHash[:])
	if err != nil {
		return nil, err
	}

	return signature, nil
}