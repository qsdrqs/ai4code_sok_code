package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func signMessage(sk *ecdsa.PrivateKey, message string) bool {
	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		fmt.Println("Error signing message:", err)
		return false
	}

	// Verify the signature
	valid := ecdsa.Verify(&sk.PublicKey, hash[:], r, s)
	return valid
}

func main() {
	// Generate a private key using the SECP256k1 curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	// Test the signMessage function
	message := ".."
	isValid := signMessage(privateKey, message)
	fmt.Println(isValid)
}