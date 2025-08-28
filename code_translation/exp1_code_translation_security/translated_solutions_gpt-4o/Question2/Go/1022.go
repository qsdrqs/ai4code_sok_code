package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"

	"github.com/ethereum/go-ethereum/crypto"
)

func main() {
	// Generate Keys
	privateKey, err := ecdsa.GenerateKey(crypto.S256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}
	publicKey := &privateKey.PublicKey

	message := "My test message"
	messageHash := sha256.Sum256([]byte(message))

	// Generate Signature
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, messageHash[:])
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Verify if signature is valid
	isValid := ecdsa.Verify(publicKey, messageHash[:], r, s)
	fmt.Println(isValid)
}