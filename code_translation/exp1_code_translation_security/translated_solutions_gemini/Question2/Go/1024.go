package main

import (
	// Standard library packages
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"log"

	// Third-party dependency for the secp256k1 curve, matching the Python library's default.
	// In a real project, you would add this with: go get github.com/btcsuite/btcd/btcec/v2
	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

// generateEcdsaSignedMsg signs a message using a private key.
// It first hashes the message with SHA-256, then signs the hash.
// The resulting signature is returned as a Base64-encoded string in DER format.
func generateEcdsaSignedMsg(message string, privateKey *btcec.PrivateKey) (string, error) {
	// ECDSA operates on a hash of the message, not the message itself.
	// We use SHA-256, which is a standard and secure choice.
	messageHash := sha256.Sum256([]byte(message))

	// Sign the hash to produce a signature.
	// The signature object contains two integers, R and S.
	signature, err := ecdsa.Sign(privateKey, messageHash[:])
	if err != nil {
		return "", fmt.Errorf("failed to sign message hash: %w", err)
	}

	// Serialize the signature into the standard DER (Distinguished Encoding Rules) format.
	// This is a common, portable format for signatures.
	derSignature := signature.Serialize()

	// Encode the DER-formatted signature into Base64 for easy printing and transport.
	return base64.StdEncoding.EncodeToString(derSignature), nil
}

func main() {
	// --- Generate Keys ---
	// This is equivalent to `privateKey = PrivateKey()` in Python.
	// It creates a new private key for the secp256k1 curve.
	privateKey, err := btcec.NewPrivateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// This is equivalent to `publicKey = privateKey.publicKey()` in Python.
	// We don't use the public key for signing, but we generate it for completeness.
	publicKey := privateKey.PubKey()

	fmt.Printf("Generated Private Key (Hex): %x\n", privateKey.Serialize())
	fmt.Printf("Generated Public Key (Compressed Hex): %x\n\n", publicKey.SerializeCompressed())

	// --- Sign Message ---
	message := "My test message"
	fmt.Printf("Original Message: %s\n", message)

	// Call the function to sign the message.
	signedMsg, err := generateEcdsaSignedMsg(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to generate signature: %v", err)
	}

	// The Python `print(signature)` shows a representation of the signature object.
	// In Go, we print the more standard and portable Base64 encoded DER signature.
	fmt.Printf("Generated Signature (Base64 DER): %s\n", signedMsg)
}