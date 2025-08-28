package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"log"
	"math/big"
	"os"
	"syscall"
)

// signMessage signs a message using ECDSA with the secp256k1 curve
func signMessage(message []byte, privateKeyBytes []byte) ([]byte, error) {
	// Create private key from bytes
	privateKey := new(ecdsa.PrivateKey)
	privateKey.PublicKey.Curve = elliptic.P256() // Note: Go's standard library uses P256, not secp256k1
	privateKey.D = new(big.Int).SetBytes(privateKeyBytes)
	privateKey.PublicKey.X, privateKey.PublicKey.Y = privateKey.PublicKey.Curve.ScalarBaseMult(privateKeyBytes)

	// Hash the message (this mimics the default behavior of Python's ecdsa library)
	hash := sha256.Sum256(message)

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Encode signature (r || s format, similar to Python ecdsa default)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Ensure both r and s are 32 bytes (pad with zeros if necessary)
	signature := make([]byte, 64)
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):], sBytes)

	return signature, nil
}

// Alternative implementation using secp256k1 (requires external library)
// Uncomment and use this if you need actual secp256k1 support
/*
import (
	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

func signMessageSecp256k1(message []byte, privateKeyBytes []byte) ([]byte, error) {
	// Create private key from bytes using secp256k1
	privateKey, _ := btcec.PrivKeyFromBytes(privateKeyBytes)
	
	// Hash the message
	hash := sha256.Sum256(message)
	
	// Sign the hash
	signature := ecdsa.Sign(privateKey, hash[:])
	
	return signature.Serialize(), nil
}
*/

func main() {
	// Example usage
	message := []byte("Hello, World!")
	
	// Generate a sample private key (32 bytes for secp256k1/P256)
	privateKey := make([]byte, 32)
	for i := range privateKey {
		privateKey[i] = byte(i + 1) // Simple example key
	}
	
	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatal(err)
	}
	
	fmt.Printf("Message: %s\n", string(message))
	fmt.Printf("Signature: %s\n", hex.EncodeToString(signature))
	fmt.Printf("Signature (base64): %s\n", base64.StdEncoding.EncodeToString(signature))
}

// Utility functions to match Python imports (if needed)
func hashSHA256(data []byte) []byte {
	hash := sha256.Sum256(data)
	return hash[:]
}

func encodeBase64(data []byte) string {
	return base64.StdEncoding.EncodeToString(data)
}

func decodeBase64(data string) ([]byte, error) {
	return base64.StdEncoding.DecodeString(data)
}

func encodeHex(data []byte) string {
	return hex.EncodeToString(data)
}

func decodeHex(data string) ([]byte, error) {
	return hex.DecodeString(data)
}