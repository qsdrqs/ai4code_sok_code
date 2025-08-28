package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"log"
	"math/big"
)

// ecdsaSignature is a helper struct used to ASN.1 marshal the R and S values of an ECDSA signature.
type ecdsaSignature struct {
	R, S *big.Int
}

// SignMessage signs a given message using a given ECDSA private key.
// This function corresponds to the Python function `sign_message`.
//
// Parameters:
//   message: The message to be signed, as a byte slice.
//   signingKey: The *ecdsa.PrivateKey to use for signing.
//
// Returns:
//   A byte slice containing the ASN.1 DER-encoded signature, and an error if any occurred.
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// In Go's crypto/ecdsa, signing is performed on a hash of the data, not the data itself.
	// We'll use SHA-256 to hash the message, as this is a common standard.
	hash := sha256.Sum256(message)

	// Sign the hash with the private key.
	// ecdsa.Sign returns the two integer components of the signature, R and S.
	r, s, err := ecdsa.Sign(rand.Reader, signingKey, hash[:])
	if err != nil {
		// If signing fails, return the error.
		return nil, fmt.Errorf("error signing message: %w", err)
	}

	// The common format for ECDSA signatures is an ASN.1 sequence of the R and S integers.
	// We'll marshal them into this format to produce a single byte slice.
	signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		// If marshaling fails, return the error.
		return nil, fmt.Errorf("error marshaling signature: %w", err)
	}

	// Return the DER-encoded signature.
	return signature, nil
}

// main function to demonstrate the usage of SignMessage.
func main() {
	// 1. Generate an ECDSA private key for demonstration purposes.
	// In a real application, you would load a key from a secure location.
	// We'll use the P-256 curve, a common and secure choice.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Define the message to be signed.
	message := []byte("This is a test message for ECDSA signing.")

	// 3. Call the SignMessage function to sign the message.
	signature, err := SignMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("Signature (DER-encoded, hex): %x\n\n", signature)

	// 4. (Optional but recommended) Verify the signature to confirm it's correct.
	// This requires the public key, the original message hash, and the signature.
	hashToVerify := sha256.Sum256(message)
	isValid := ecdsa.VerifyASN1(&privateKey.PublicKey, hashToVerify[:], signature)

	fmt.Printf("Is the signature valid? %t\n", isValid) // Should be true
}