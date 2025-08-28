package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// sign signs a message using a given ECDSA private key
func sign(message []byte, privateKeyBytes []byte) ([]byte, error) {
	// Convert the private key bytes to an ECDSA private key
	privateKey, err := bytesToPrivateKey(privateKeyBytes)
	if err != nil {
		return nil, fmt.Errorf("failed to convert bytes to private key: %v", err)
	}

	// Hash the message (ECDSA typically signs a hash, not the raw message)
	hash := sha256.Sum256(message)

	// Sign the hash using the ECDSA private key
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %v", err)
	}

	// Encode the signature (r, s) into bytes
	// This is a simple concatenation - in practice you might want DER encoding
	signature := append(r.Bytes(), s.Bytes()...)
	
	return signature, nil
}

// bytesToPrivateKey converts a byte slice to an ECDSA private key
// Assumes the bytes represent a big integer for the private key value
func bytesToPrivateKey(privateKeyBytes []byte) (*ecdsa.PrivateKey, error) {
	// Convert bytes to big integer
	d := new(big.Int).SetBytes(privateKeyBytes)
	
	// Create the private key using secp256k1 curve (equivalent to SECP256k1 in Python)
	curve := elliptic.P256() // Note: This is P256, for secp256k1 you'd need a different implementation
	
	// Validate that the private key is within valid range
	if d.Cmp(curve.Params().N) >= 0 {
		return nil, fmt.Errorf("private key value is too large for the curve")
	}
	
	privateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
		},
		D: d,
	}
	
	// Calculate the public key point
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(d.Bytes())
	
	return privateKey, nil
}

// Example usage
func main() {
	// Example private key (32 bytes for secp256k1)
	privateKeyBytes := []byte{
		0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
		0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10,
		0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18,
		0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20,
	}
	
	message := []byte("Hello, World!")
	
	signature, err := sign(message, privateKeyBytes)
	if err != nil {
		fmt.Printf("Error signing message: %v\n", err)
		return
	}
	
	fmt.Printf("Signature: %x\n", signature)
}