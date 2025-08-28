package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signUsingECDSA signs a message using the provided ECDSA private key.
func signUsingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Hash the message using SHA256
	hash := sha256.Sum256(message)

	// Sign the hashed message using the private key
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to create the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Example usage
	// Generate a new ECDSA private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	message := []byte("Hello, ECDSA!")
	signature, err := signUsingECDSA(privateKey, message)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
}