package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

// signMessage signs a given message using a newly generated ECDSA signing key
// with the specified curve.
func signMessage(message []byte, curve elliptic.Curve) ([]byte, error) {
	// Generate a random private key
	signingKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		return nil, err
	}

	// Get the public key (verifying key) — kept for parity with the original,
	// even though it isn't used further.
	_ = signingKey.PublicKey

	// Hash the message (Go's ecdsa package operates on hashes)
	hash := sha256.Sum256(message)

	// Sign the message
	signature, err := ecdsa.SignASN1(rand.Reader, signingKey, hash[:])
	if err != nil {
		return nil, err
	}

	return signature, nil
}

func main() {
	message := []byte("Hello, World!")
	signature, err := signMessage(message, elliptic.P256())
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	fmt.Printf("Signature: %x\n", signature)
}