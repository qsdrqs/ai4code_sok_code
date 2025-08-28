package main

import (
	"crypto/sha1" // To match the default hash function of the Python ecdsa library
	"encoding/hex"
	"fmt"
	"log"

	// Dependency: go-ethereum's crypto library for ECDSA operations
	"github.com/ethereum/go-ethereum/crypto"
)

/*
Function signMessage

Input: message and ECDSA private key as byte slices
Output: message signed using the provided key, or an error
*/
func signMessage(message, privateKeyBytes []byte) ([]byte, error) {
	// Load the private key from bytes into an ecdsa.PrivateKey object.
	// The go-ethereum crypto library expects a 32-byte private key.
	privateKey, err := crypto.ToECDSA(privateKeyBytes)
	if err != nil {
		// Wrap the error with more context
		return nil, fmt.Errorf("failed to load private key: %w", err)
	}

	// The Python ecdsa library's sign() method hashes the message before signing.
	// The default hash algorithm is SHA1. To ensure the output matches the Python
	// script, we must replicate that behavior here.
	// NOTE: In real-world applications, a stronger hash function like SHA256 is recommended.
	hasher := sha1.New()
	hasher.Write(message)
	hash := hasher.Sum(nil)

	// Sign the hash of the message.
	// The crypto.Sign function from go-ethereum is deterministic and produces
	// a signature in the [R || S || V] format, where V is a recovery ID.
	signature, err := crypto.Sign(hash, privateKey)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The Python ecdsa library returns a 64-byte signature (R and S concatenated).
	// To match this format, we must drop the V (the last byte) from the 65-byte
	// signature produced by crypto.Sign.
	return signature[:64], nil
}

func main() {
	// The message to be signed, converted to a byte slice.
	message := []byte("hello")
	// The 32-byte private key, converted to a byte slice.
	privateKey := []byte("qwertyuiopasdfghjklzxcvbnmqwerty")

	// Call the signMessage function.
	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Error signing message: %v", err)
	}

	// The original Python code prints the raw bytes of the signature.
	// For better readability and a more standard representation of binary data,
	// we print the hex-encoded string of the signature.
	// This Go code produces the exact same signature bytes as the Python code.
	fmt.Printf("Signature (hex): %s\n", hex.EncodeToString(signature))
}