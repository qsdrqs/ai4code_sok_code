package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// Function that signs input with the given ECDSA private key
func sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message using SHA256
	hash := sha256.Sum256(message)

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Serialize the signature (r, s) into a single byte slice
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Generate a new ECDSA private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Message to be signed
	message := []byte("Hello, World!")

	// Sign the message
	signature, err := sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}