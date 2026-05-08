package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2"
)

// signMessage signs a message using ECDSA with SECP256k1 curve.
// Input: message and ECDSA private key (as bytes)
// Output: message signed using the provided key
func signMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Create a new ECDSA key from the private key bytes using SECP256k1 curve
	privKey, _ := btcec.PrivKeyFromBytes(privateKey)

	// Convert to standard crypto/ecdsa.PrivateKey
	ecdsaKey := privKey.ToECDSA()

	// Hash the message (Python's ecdsa library defaults to SHA1)
	hash := sha1.Sum(message)

	// Sign the message
	r, s, err := ecdsa.Sign(rand.Reader, ecdsaKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Convert r and s to raw bytes concatenated (matching Python ecdsa's default sigencode_string)
	// Both r and s are padded to 32 bytes each
	signature := make([]byte, 64)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):64], sBytes)

	return signature, nil
}

func main() {
	signature, err := signMessage([]byte("hello"), []byte("qwertyuiopasdfghjklzxcvbnmqwerty"))
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	fmt.Println(signature)
}