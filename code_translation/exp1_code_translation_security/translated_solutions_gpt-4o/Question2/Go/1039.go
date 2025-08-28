package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func signMessage(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Combine r and s into a single byte slice
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Example usage (you would need to provide a valid private key)
	// privateKey := ... (initialize your ECDSA private key here)

	// message := "message"
	// signature, err := signMessage(message, privateKey)
	// if err != nil {
	// 	fmt.Println("Error signing message:", err)
	// } else {
	// 	fmt.Printf("Signature: %x\n", signature)
	// }
}