package main

import (
	"crypto/ed25519" // For Ed25519 digital signatures
	"encoding/hex"   // For printing byte slices in a readable format
	"fmt"            // For printing to the console
	"log"            // For handling errors
)

// SignMessage signs a message using a private key and returns the signature.
// It uses the Ed25519 algorithm, a modern and secure choice for digital signatures.
func SignMessage(message []byte, signingKey ed25519.PrivateKey) []byte {
	// The ed25519.Sign function is a direct equivalent to Python's signing_key.sign(message)
	return ed25519.Sign(signingKey, message)
}

// main function to demonstrate the usage of SignMessage.
func main() {
	// 1. Define a message to be signed.
	// In Go, raw data is typically represented as a byte slice ([]byte).
	message := []byte("This is a secret message")

	// 2. Generate a new public/private key pair for signing.
	// The `ed25519.GenerateKey` function returns a public key, a private key, and an error.
	// We pass `nil` for the random source, which makes it use `crypto/rand.Reader`.
	publicKey, privateKey, err := ed25519.GenerateKey(nil)
	if err != nil {
		log.Fatalf("Failed to generate key pair: %v", err)
	}

	fmt.Printf("Original Message: %s\n", message)
	fmt.Println("---")

	// 3. Sign the message using our function.
	signature := SignMessage(message, privateKey)

	// Signatures are binary data, so we print it in hexadecimal format for readability.
	fmt.Printf("Generated Signature: %s\n", hex.EncodeToString(signature))
	fmt.Println("---")

	// 4. (Bonus) Verify the signature to confirm it's valid.
	// This step is crucial in a real application to ensure the message and signature match.
	isValid := ed25519.Verify(publicKey, message, signature)

	fmt.Printf("Is the signature valid? %t\n", isValid)

	// Let's try to verify with a tampered message to show it fails.
	tamperedMessage := []byte("This is a tampered message")
	isTamperedValid := ed25519.Verify(publicKey, tamperedMessage, signature)
	fmt.Printf("Is the signature valid for a tampered message? %t\n", isTamperedValid)
}