package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// sign signs a message using the provided private key and returns the signature.
func sign(msg []byte, privKey *ecdsa.PrivateKey) ([]byte, error) {
	// Compute the SHA-256 hash of the message
	hash := sha256.Sum256(msg)

	// Sign the hash using the private key
	r, s, err := ecdsa.Sign(rand.Reader, privKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Combine r and s into a single byte slice (DER encoding can also be used)
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Example usage
	// Generate a private key for testing
	privKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Message to sign
	msg := []byte("Hello, World!")

	// Sign the message
	signature, err := sign(msg, privKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}