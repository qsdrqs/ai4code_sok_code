package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
)

func myFunction(message []byte) {
	// Generate a new private key using the SECP256k1 curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	// Sign the message using the private key
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, message)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	// Combine r and s into a single signature (optional, depending on your use case)
	fmt.Printf("Signature: (r: %s, s: %s)\n", r.String(), s.String())
}

func main() {
	message := []byte("your message here")
	myFunction(message)
}