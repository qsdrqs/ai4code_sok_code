package main

import (
	"crypto/ed25519"
	"fmt"
)

func signMessage(message []byte, signingKey ed25519.PrivateKey) ([]byte, error) {
	signature := ed25519.Sign(signingKey, message)
	return signature, nil
}

func main() {
	// Example usage
	message := []byte("Your message here")
	// signingKey should be an ed25519.PrivateKey
	// For demonstration, we'll generate a new key pair
	publicKey, privateKey, err := ed25519.GenerateKey(nil)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
	fmt.Printf("Public Key: %x\n", publicKey)
}