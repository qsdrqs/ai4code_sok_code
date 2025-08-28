package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// SignMessage signs a given message using a newly generated ECDSA key for the specified curve.
// This function mimics the behavior of the Python example by generating a new key on each call.
//
// Parameters:
//   message: The byte slice message to be signed.
//   curve:   The elliptic curve to use (e.g., elliptic.P256()).
//
// Returns:
//   A byte slice containing the concatenated R and S components of the signature,
//   or an error if the signing process fails.
func SignMessage(message []byte, curve elliptic.Curve) ([]byte, error) {
	// 1. Generate a new random private key for the specified curve.
	// The Python code generates a new key on every call, so we replicate that here.
	privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		return nil, fmt.Errorf("failed to generate private key: %w", err)
	}

	// 2. Hash the message. ECDSA signs a hash of the message, not the message itself.
	// We use SHA-256, a secure and standard choice.
	hash := sha256.Sum256(message)

	// 3. Sign the hash of the message with the private key.
	// This returns the two signature components, r and s.
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// 4. Concatenate r and s to produce the final signature.
	// We pad each component to the byte size of the curve's order to ensure a fixed-length signature.
	curveByteSize := curve.Params().ByteSize
	signature := make([]byte, curveByteSize*2)
	r.FillBytes(signature[:curveByteSize])
	s.FillBytes(signature[curveByteSize:])

	return signature, nil
}

// main function to demonstrate the usage of SignMessage.
func main() {
	// The message to be signed.
	message := []byte("This is a test message for ECDSA signing")

	// The elliptic curve to use. P-256 is a common and secure choice.
	curve := elliptic.P256()

	// Call the function to sign the message.
	signature, err := SignMessage(message, curve)
	if err != nil {
		log.Fatalf("Error signing message: %v", err)
	}

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("Signature (hex): %x\n", signature)
	fmt.Printf("Signature Length: %d bytes\n", len(signature))

	// Note: Because a new key is generated every time,
	// running this program multiple times will produce a different signature each time.
}