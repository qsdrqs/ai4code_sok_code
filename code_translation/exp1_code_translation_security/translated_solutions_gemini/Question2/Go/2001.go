package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
	"crypto/elliptic"
)

// Sign signs the input message with the given ECDSA private key.
// It first hashes the message with SHA-256 and then signs the hash.
func Sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// The message is first hashed using SHA-256
	hash := sha256.Sum256(message)

	// The hash is then signed using the private key.
	// rand.Reader is a global, shared instance of a cryptographically
	// secure random number generator.
	// The signature is returned in ASN.1 DER format.
	signature, err := ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	return signature, nil
}

// Example usage to demonstrate the function
func main() {
	// 1. Generate a new ECDSA private key for demonstration purposes.
	// In a real application, you would load an existing key.
	// We use the P-256 curve, which is a common choice.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Define a message to be signed.
	message := []byte("this is a message to be signed")

	// 3. Call the Sign function.
	signature, err := Sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Message: %s\n", message)
	fmt.Printf("Signature (hex): %x\n", signature)

	// 4. (Optional) Verify the signature with the corresponding public key.
	// This demonstrates that the signature is valid.
	publicKey := &privateKey.PublicKey
	hash := sha256.Sum256(message)
	isValid := ecdsa.VerifyASN1(publicKey, hash[:], signature)

	fmt.Printf("Signature is valid: %t\n", isValid)
}