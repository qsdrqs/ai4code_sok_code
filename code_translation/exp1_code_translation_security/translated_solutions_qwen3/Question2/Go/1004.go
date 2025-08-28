package main

import (
	"crypto/sha1"
	"fmt"
	"github.com/btcsuite/btcd/btcec"
)

// signMessage signs the given message using the provided private key with ECDSA and SECP256k1.
// It returns the signature as a 64-byte slice: [R (32 bytes)][S (32 bytes)].
func signMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Use btcec to create a private key from the raw bytes
	privKey, err := btcec.PrivKeyFromBytes(btcec.S256(), privateKey)
	if err != nil {
		return nil, err
	}

	// Compute SHA-1 hash of the message
	hash := sha1.Sum(message)

	// Sign the hash using the private key
	signature, err := privKey.Sign(hash[:])
	if err != nil {
		return nil, err
	}

	// Prepare 32-byte buffers for R and S
	rBytes := make([]byte, 32)
	sBytes := make([]byte, 32)

	// Fill the buffers with R and S values in big-endian format
	signature.R.FillBytes(rBytes)
	signature.S.FillBytes(sBytes)

	// Concatenate R and S to form the final 64-byte signature
	return append(rBytes, sBytes...), nil
}

func main() {
	// Example usage
	message := []byte("hello")
	privateKey := []byte("qwertyuiopasdfghjklzxcvbnmqwerty")

	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	// Print the signature as a slice of integers (Go's default for byte slices)
	fmt.Println(signature)
}