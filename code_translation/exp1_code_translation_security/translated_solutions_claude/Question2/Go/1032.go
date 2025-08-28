package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"
	"math/big"
)

// signMessage signs a message using a given ECDSA private key
func signMessage(message string, privateKeyBytes []byte) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Create a new ECDSA private key object from the byte slice
	privateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: elliptic.P256(), // Using P256 as it's the standard, similar to SECP256k1
		},
		D: new(big.Int).SetBytes(privateKeyBytes),
	}

	// Calculate the public key point
	privateKey.PublicKey.X, privateKey.PublicKey.Y = privateKey.PublicKey.Curve.ScalarBaseMult(privateKeyBytes)

	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, messageHash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %v", err)
	}

	// Combine r and s into a single signature byte slice
	// This is a simple concatenation - in practice you might want to use DER encoding
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Ensure both r and s are 32 bytes (pad with zeros if necessary)
	signature := make([]byte, 64)
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):], sBytes)

	return signature, nil
}

func main() {
	// Test function
	message := "Hello, world!"
	privateKeyHex := "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725"
	
	privateKey, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		log.Fatalf("Failed to decode private key: %v", err)
	}

	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}