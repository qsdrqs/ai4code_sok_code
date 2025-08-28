package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func generateEcdsaSignedMsg(message string, privateKey *ecdsa.PrivateKey) (*big.Int, *big.Int, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, nil, err
	}

	return r, s, nil
}

func main() {
	// Generate Keys
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}
	publicKey := &privateKey.PublicKey

	message := "My test message"
	r, s, err := generateEcdsaSignedMsg(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: (r: %s, s: %s)\n", r.Text(16), s.Text(16))
	fmt.Printf("Public Key: (X: %s, Y: %s)\n", publicKey.X.Text(16), publicKey.Y.Text(16))
}