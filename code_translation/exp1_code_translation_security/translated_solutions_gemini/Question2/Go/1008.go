// main.go
package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"

	"github.com/btcsuite/btcd/btcec/v2"
)

/*
signMessage signs a message using an ECDSA private key on the SECP256k1 curve.

This function replicates the behavior of the provided Python script. It takes a
message and a 32-byte private key as strings, signs the message, and returns
the raw 64-byte signature (r || s).
*/
func signMessage(message string, privateKeyStr string) ([]byte, error) {
	// In the Python code, the private key string is encoded to bytes.
	// A private key for SECP256k1 must be a 32-byte number.
	privKeyBytes := []byte(privateKeyStr)
	if len(privKeyBytes) != 32 {
		return nil, fmt.Errorf("private key must be 32 bytes long, but got %d bytes", len(privKeyBytes))
	}

	// Create a private key object from the raw bytes.
	// The btcec library uses the same SECP256k1 curve as the Python example.
	// The second return value is the public key, which we don't need for signing.
	privKey, _ := btcec.PrivKeyFromBytes(privKeyBytes)

	// Hash the message before signing. ECDSA operates on a fixed-size hash of the
	// message, not the message itself. The Python ecdsa library uses SHA-256
	// by default for the SECP256k1 curve.
	messageHash := sha256.Sum256([]byte(message))

	// Sign the hash. The btcec library, like the Python ecdsa library,
	// uses deterministic signatures (RFC6979) by default. This ensures that
	// signing the same message with the same key always produces the same signature.
	signature, err := privKey.Sign(messageHash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The Python library's default behavior is to return the raw signature as a
	// 64-byte concatenation of r and s. We will manually construct this format
	// to match the original output. Note that the standard and more common format
	// for ECDSA signatures is DER encoding, which can be obtained via signature.Serialize().
	rBytes := signature.R.Bytes()
	sBytes := signature.S.Bytes()

	// The r and s components of the signature must each be 32 bytes.
	// We pad them with leading zeros if they are shorter.
	rPadded := make([]byte, 32)
	sPadded := make([]byte, 32)
	copy(rPadded[32-len(rBytes):], rBytes)
	copy(sPadded[32-len(sBytes):], sBytes)

	// Concatenate the padded r and s values to form the final 64-byte signature.
	rawSignature := append(rPadded, sPadded...)

	return rawSignature, nil
}

func main() {
	message := "hello world"
	// The private key must be a 32-character (32-byte) string to match the Python example.
	privateKey := "12345678901234567890123456789012"

	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Error: %v", err)
	}

	// The Python script prints the raw bytes of the signature. For better
	// readability and easier verification, we print the hex-encoded string,
	// which is a common practice in Go.
	fmt.Println(hex.EncodeToString(signature))
}