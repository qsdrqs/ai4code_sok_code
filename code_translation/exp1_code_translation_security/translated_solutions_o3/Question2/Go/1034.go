package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func main() {
	// Create a new private / public key-pair (P-256 curve is the closest
	// match to what “ellipticcurve” uses by default in the Python snippet)
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	publicKey := &privateKey.PublicKey

	// The message we want to sign
	message := "Test message"

	// ECDSA signs a hash of the message, not the raw bytes
	hash := sha256.Sum256([]byte(message))

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		panic(err)
	}

	// Verify the signature
	valid := ecdsa.Verify(publicKey, hash[:], r, s)

	fmt.Println(valid) // prints: true
}