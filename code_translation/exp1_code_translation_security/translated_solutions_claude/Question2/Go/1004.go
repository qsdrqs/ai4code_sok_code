package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signMessage signs a message using ECDSA with the provided private key
// Input: message and ECDSA private key bytes
// Output: message signed using the provided key
func signMessage(message []byte, privateKeyBytes []byte) ([]byte, error) {
	// Ensure the private key is 32 bytes for secp256k1
	if len(privateKeyBytes) != 32 {
		// If shorter, pad with zeros; if longer, truncate
		paddedKey := make([]byte, 32)
		copy(paddedKey, privateKeyBytes)
		privateKeyBytes = paddedKey
	}
	
	// Create private key from bytes
	privateKeyInt := new(big.Int).SetBytes(privateKeyBytes)
	
	// Create ECDSA private key using secp256k1 curve (P256 is similar)
	curve := elliptic.P256()
	privateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
		},
		D: privateKeyInt,
	}
	
	// Calculate public key point
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(privateKeyBytes)
	
	// Hash the message (ECDSA typically signs a hash, not the raw message)
	hash := sha256.Sum256(message)
	
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
	message := []byte("hello")
	privateKey := []byte("qwertyuiopasdfghjklzxcvbnmqwerty")
	
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Printf("Error signing message: %v\n", err)
		return
	}
	
	fmt.Printf("%x\n", signature)
}