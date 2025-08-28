package main

import (
	"crypto/sha256"
	"fmt"
	"log"

	"github.com/paulmillr/noble-ed25519"
)

func main() {
	// Generate a new private key
	privateKey, err := ed25519.GeneratePrivateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Derive the public key from the private key
	publicKey := privateKey.Public()

	message := "Test message"

	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the message
	signature, err := privateKey.Sign(hash[:])
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Verify the signature
	valid := publicKey.Verify(hash[:], signature)
	fmt.Println(valid)
}