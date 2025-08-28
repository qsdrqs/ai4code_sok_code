package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"fmt"
	"math/big"
)

// Function in Go that signs a given message using a given ECDSA signing key
func signMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Sign the message using the signing key
	r, s, err := ecdsa.Sign(rand.Reader, signingKey, message)
	if err != nil {
		return nil, err
	}

	// Convert the signature to a byte slice
	signature := append(r.Bytes(), s.Bytes()...)

	// Return the signature
	return signature, nil
}

func main() {
	// Example usage (assuming signingKey is already defined and initialized)
	// message := []byte("your message here")
	// signature, err := signMessage(message, signingKey)
	// if err != nil {
	//     fmt.Println("Error signing message:", err)
	// } else {
	//     fmt.Println("Signature:", signature)
	// }
}