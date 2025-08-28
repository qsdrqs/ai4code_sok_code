package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// sign_message signs a message using ECDSA key
func signMessage(message string, privateKeyStr string) ([]byte, error) {
	// Convert message to bytes
	messageBytes := []byte(message)
	
	// Convert private key string to bytes and then to big.Int
	privateKeyBytes := []byte(privateKeyStr)
	
	// Ensure the private key is exactly 32 bytes for secp256k1
	if len(privateKeyBytes) != 32 {
		return nil, fmt.Errorf("private key must be exactly 32 bytes")
	}
	
	// Create private key from bytes
	privateKeyInt := new(big.Int).SetBytes(privateKeyBytes)
	
	// Create ECDSA private key using secp256k1 curve
	curve := elliptic.P256() // Note: Go's standard library doesn't have secp256k1, using P256 instead
	privateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
		},
		D: privateKeyInt,
	}
	
	// Calculate public key point
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(privateKeyBytes)
	
	// Hash the message (ECDSA typically signs a hash, not the raw message)
	hash := sha256.Sum256(messageBytes)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	
	// Combine r and s into a signature (simple concatenation)
	signature := append(r.Bytes(), s.Bytes()...)
	
	return signature, nil
}

func main() {
	signature, err := signMessage("hello world", "12345678901234567890123456789012")
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return
	}
	
	fmt.Printf("%x\n", signature)
}