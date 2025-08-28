package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func signString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Hash the data using SHA256
	hash := sha256.Sum256(data)

	// Sign the hashed data
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Convert the signature to a byte slice
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Generate a private key using the P-384 curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Data to be signed
	data := []byte("hello world")

	// Sign the data
	signature, err := signString(privateKey, data)
	if err != nil {
		log.Fatalf("Failed to sign data: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}