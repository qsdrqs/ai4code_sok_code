package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
	"math/big"
)

// signMessage signs a message using the given ECDSA private key.
// It first hashes the message with SHA-256 and then signs the hash.
// The signature is returned as a raw byte slice (r || s), matching the
// default format of the Python ecdsa library.
//
// Note: The original Python function ignored its `message` parameter and signed
// the literal b"message". This Go version is implemented to sign the message
// passed as a parameter, which is more conventional and useful.
func signMessage(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// The ecdsa.Sign function in Go's crypto library signs a hash of the data,
	// not the raw data itself. We'll use SHA-256, a common and secure choice.
	hash := sha256.Sum256(message)

	// Sign the hash. This returns the two components of the signature, r and s.
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The Python ecdsa library's default format is to concatenate r and s.
	// We must pad r and s to the curve's byte size to ensure a fixed-length signature.
	curveByteSize := key.Curve.Params().ByteSize
	signature := make([]byte, 2*curveByteSize)
	r.FillBytes(signature[:curveByteSize])
	s.FillBytes(signature[curveByteSize:])

	return signature, nil
}

// main function to demonstrate the usage of signMessage
func main() {
	// 1. Generate a new private key for demonstration purposes.
	// In a real application, you would load a key from a secure location.
	// We use the P-256 curve, a standard choice for ECDSA.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}
	publicKey := &privateKey.PublicKey

	// 2. Define the message to be signed.
	message := []byte("message")

	// 3. Sign the message using our translated function.
	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("Signature (hex): %x\n", signature)
	fmt.Printf("Signature Length: %d bytes\n", len(signature))

	// 4. (Optional but recommended) Verify the signature to prove it's valid.
	// To verify, we must parse r and s back from the concatenated signature slice.
	curveByteSize := publicKey.Curve.Params().ByteSize
	if len(signature) != 2*curveByteSize {
		log.Fatalf("signature has incorrect length")
	}
	r := new(big.Int).SetBytes(signature[:curveByteSize])
	s := new(big.Int).SetBytes(signature[curveByteSize:])

	// We need to hash the original message again for verification.
	hash := sha256.Sum256(message)

	// Verify the signature using the public key.
	isValid := ecdsa.Verify(publicKey, hash[:], r, s)

	fmt.Printf("Signature Verified: %t\n", isValid)
}