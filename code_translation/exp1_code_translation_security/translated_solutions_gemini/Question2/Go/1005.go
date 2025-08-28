package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// sign generates an ECDSA signature for a message using a private key.
// It replicates the behavior of the Python version, including the self-verification step.
func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// In Go's crypto/ecdsa, we must first hash the message before signing.
	// We'll use SHA-256, a standard and secure choice.
	hash := sha256.Sum256(msg)

	// Sign the hash with the private key.
	// This returns the two components of an ECDSA signature, r and s.
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// This section corresponds to Python's `assert vk.verify(signature, msg)`.
	// We verify our own signature to ensure it was generated correctly.
	// The public key is available as part of the private key struct.
	if !ecdsa.Verify(&key.PublicKey, hash[:], r, s) {
		return nil, fmt.Errorf("failed to verify own signature")
	}

	// The Python library returns r and s concatenated. We will do the same.
	// We get the byte size of the curve's order and create a signature
	// slice of double that size to hold r and s.
	curveByteSize := key.Curve.Params().ByteSize
	signature := make([]byte, 2*curveByteSize)
	r.FillBytes(signature[:curveByteSize])
	s.FillBytes(signature[curveByteSize:])

	return signature, nil
}

// test generates a key, signs a message, and prints the signature.
// It is the Go equivalent of the Python test() function.
func test() {
	// Generate a new private key using the P-256 elliptic curve.
	// This is a common and secure default, equivalent to secp256r1.
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Sign a sample message.
	message := []byte("message")
	signature, err := sign(message, sk)
	if err != nil {
		log.Fatalf("Signing failed: %v", err)
	}

	// Print the signature as a hexadecimal string for readability.
	fmt.Printf("%x\n", signature)
}

func main() {
	test()
}