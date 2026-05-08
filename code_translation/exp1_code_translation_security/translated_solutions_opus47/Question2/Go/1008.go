package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2"
)

// sign message using Ecdsa key
func signMessage(message string, privateKey string) ([]byte, error) {
	messageBytes := []byte(message)
	privateKeyBytes := []byte(privateKey)

	// Create private key from bytes using SECP256k1 curve
	priv, _ := btcec.PrivKeyFromBytes(privateKeyBytes)

	// Python's ecdsa library uses SHA-1 as the default hash function
	hash := sha1.Sum(messageBytes)

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, priv.ToECDSA(), hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to form the raw signature (64 bytes)
	// This matches the default `sigencode_string` format used by Python's ecdsa
	signature := make([]byte, 64)
	r.FillBytes(signature[:32])
	s.FillBytes(signature[32:])

	return signature, nil
}

func main() {
	sig, err := signMessage("hello world", "12345678901234567890123456789012")
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(sig)
}