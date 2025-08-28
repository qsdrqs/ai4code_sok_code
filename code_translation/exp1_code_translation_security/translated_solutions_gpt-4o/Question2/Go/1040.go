package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"log"
)

// signMessage signs a message using the provided private key.
func signMessage(message []byte, privateKeyHex string) ([]byte, error) {
	// Decode the private key from hex string
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		return nil, fmt.Errorf("failed to decode private key: %v", err)
	}

	// Create a new private key from the byte slice
	privateKey, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	privateKey.D.SetBytes(privateKeyBytes)

	// Sign the message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, message)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %v", err)
	}

	// Concatenate r and s values to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Example usage
	message := []byte("your message here")
	privateKeyHex := "your_private_key_in_hex"

	signature, err := signMessage(message, privateKeyHex)
	if err != nil {
		log.Fatalf("Error signing message: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}