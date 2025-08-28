package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"

	"github.com/btcsuite/btcd/btcec"
)

// signMessage signs a given message using an ECDSA private key with the SECP256k1 curve.
// It returns the DER-encoded signature or an error if the private key is invalid.
func signMessage(message string, privateKey []byte) ([]byte, error) {
	// Step 1: Hash the message using SHA-256
	messageHash := sha256.Sum256([]byte(message))

	// Step 2: Parse the private key into a SECP256k1 private key object
	privKey, err := btcec.PrivKeyFromBytes(privateKey)
	if err != nil {
		return nil, fmt.Errorf("failed to parse private key: %w", err)
	}

	// Step 3: Sign the hash using the private key
	signature := privKey.Sign(messageHash[:])

	// Step 4: Serialize the signature in DER format
	return signature.Serialize(), nil
}

func main() {
	// Test message and private key
	message := "Hello, world!"
	privateKeyHex := "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725"

	// Decode the private key from hex
	privateKey, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		panic(fmt.Sprintf("failed to decode private key: %v", err))
	}

	// Sign the message
	signature, err := signMessage(message, privateKey)
	if err != nil {
		panic(fmt.Sprintf("failed to sign message: %v", err))
	}

	// Print the signature (as a slice of decimal bytes)
	fmt.Println(signature)
}