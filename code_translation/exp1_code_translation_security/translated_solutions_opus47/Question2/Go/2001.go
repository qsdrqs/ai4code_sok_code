package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// Function that signs input with the given ECDSA private key
func sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256(message)
	return ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
}