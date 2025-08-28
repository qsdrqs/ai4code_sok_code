// This Go program is a translation of the provided Python script.
// It demonstrates how to sign a message using an ECDSA private key
// with the SECP256k1 curve.

package main

import (
	"crypto/ecdsa"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"

	// The go-ethereum crypto package is used for its robust implementation
	// of the SECP256k1 curve, which is not included in Go's standard library.
	"github.com/ethereum/go-ethereum/crypto"
)

// signMessage signs a given message with a private key and returns the signature.
// It replicates the functionality of the Python version.
func signMessage(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// 1. Hash the message using SHA-256.
	// The Python code encodes the message to 'utf-8' first, which is Go's default
	// string encoding, so we can directly convert the string to a byte slice.
	hasher := sha256.New()
	hasher.Write([]byte(message))
	messageHash := hasher.Sum(nil)

	// 2. Sign the hash with the private key.
	// The crypto.Sign function from go-ethereum uses a deterministic nonce
	// (RFC 6979), just like the Python ecdsa library, ensuring that the same
	// message and key will always produce the same signature.
	//
	// Note: crypto.Sign returns the signature in [R || S || V] format, where V is
	// the recovery ID (0 or 1). The Python ecdsa library's sign() method returns
	// only the 64-byte [R || S] signature. We slice the result to match.
	signature, err := crypto.Sign(messageHash, privateKey)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message hash: %w", err)
	}

	// 3. Return the 64-byte signature [R || S] to match the Python output.
	return signature[:64], nil
}

func main() {
	// The message to be signed.
	message := "Hello, world!"

	// The private key as a hex string (same as in the Python example).
	privateKeyHex := "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725"

	// Decode the hex-encoded private key into raw bytes.
	// This is the Go equivalent of Python's `binascii.unhexlify()`.
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		log.Fatalf("Failed to decode private key from hex: %v", err)
	}

	// Create an ecdsa.PrivateKey object from the raw bytes.
	// The `crypto.ToECDSA` helper function handles this conversion.
	privateKey, err := crypto.ToECDSA(privateKeyBytes)
	if err != nil {
		log.Fatalf("Failed to create ECDSA private key: %v", err)
	}

	// Call the signMessage function to get the signature.
	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Print the resulting signature.
	// The Python code's `print(signature)` shows a bytes literal (e.g., b'\\x90...').
	// For clarity and common practice in Go, we print the hex-encoded string
	// of the signature, which is more readable and useful for verification.
	fmt.Printf("Signature (hex): %s\n", hex.EncodeToString(signature))
}