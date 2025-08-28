package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signMessage signs a given message using a randomly generated ECDSA private key.
func signMessage(message []byte) ([]byte, *ecdsa.PrivateKey, error) {
	// Generate a random private key using the specified elliptic curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		return nil, nil, fmt.Errorf("failed to generate private key: %v", err)
	}

	// Hash the message using SHA-256
	hash := sha256.Sum256(message)

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, nil, fmt.Errorf("failed to sign message: %v", err)
	}

	// Combine r and s into a single signature
	signature := append(r.Bytes(), s.Bytes()...)

	return signature, privateKey, nil
}

func main() {
	message := []byte("Hello, ECDSA!")
	signature, privateKey, err := signMessage(message)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
	fmt.Printf("Private Key: %x\n", privateKey.D.Bytes())
}