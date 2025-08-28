package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"math/big"
)

// signMessage signs a message using a given ECDSA private key
func signMessage(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Sign the message hash
	r, s, err := ecdsa.Sign(nil, privateKey, messageHash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s values to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	// Test function
	message := "Hello, world!"
	privateKeyHex := "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725"

	// Decode the private key from hex
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		fmt.Println("Error decoding private key:", err)
		return
	}

	// Create a new ECDSA private key object
	curve := elliptic.P256() // SECP256k1 is not directly available in Go's standard library
	privateKey := new(ecdsa.PrivateKey)
	privateKey.D = new(big.Int).SetBytes(privateKeyBytes)
	privateKey.PublicKey.Curve = curve
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(privateKeyBytes)

	// Sign the message
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	// Print the signature
	fmt.Printf("Signature: %x\n", signature)
}