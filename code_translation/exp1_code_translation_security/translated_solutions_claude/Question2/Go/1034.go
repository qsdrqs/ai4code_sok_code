package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func main() {
	// Generate a private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	// Get the public key from private key
	publicKey := &privateKey.PublicKey

	message := "Test message"

	// Sign the message
	signature, err := signMessage(message, privateKey)
	if err != nil {
		panic(err)
	}

	// Verify the signature
	isValid := verifySignature(message, signature, publicKey)
	fmt.Println(isValid)
}

// Signature represents an ECDSA signature
type Signature struct {
	R, S *big.Int
}

// signMessage signs a message using ECDSA
func signMessage(message string, privateKey *ecdsa.PrivateKey) (*Signature, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	
	return &Signature{R: r, S: s}, nil
}

// verifySignature verifies an ECDSA signature
func verifySignature(message string, signature *Signature, publicKey *ecdsa.PublicKey) bool {
	// Hash the message
	hash := sha256.Sum256([]byte(message))
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], signature.R, signature.S)
}