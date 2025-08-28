package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signMessage signs a given message using ECDSA signing key
// message: message to be signed
// privateKey: private key to sign the message
// returns: signature of the message
func signMessage(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Example usage
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	message := []byte("Hello, World!")
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
}