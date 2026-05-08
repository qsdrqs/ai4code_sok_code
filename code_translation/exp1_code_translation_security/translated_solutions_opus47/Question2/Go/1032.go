package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2"
)

// signMessage signs a message using a given ECDSA private key
func signMessage(message string, privateKey []byte) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Create a new ECDSA private key object using SECP256k1 curve
	privKey, _ := btcec.PrivKeyFromBytes(privateKey)
	privateKeyObject := privKey.ToECDSA()

	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKeyObject, messageHash[:])
	if err != nil {
		return nil, err
	}

	// Combine r and s to form the signature (matches Python ecdsa's default sigencode_string:
	// concatenation of r and s, each padded to 32 bytes for SECP256k1)
	signature := make([]byte, 64)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):64], sBytes)

	return signature, nil
}

// main is the test function
func main() {
	message := "Hello, world!"
	privateKey, err := hex.DecodeString("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725")
	if err != nil {
		fmt.Println("Error decoding private key:", err)
		return
	}

	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Println(signature)
}