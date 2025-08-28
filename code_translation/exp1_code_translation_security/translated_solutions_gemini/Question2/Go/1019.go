package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"log"
	"math/big"

	// The Python example uses SECP384R1, which corresponds to Go's P384 curve.
	// We import the elliptic package to generate a key for the example.
	"crypto/elliptic"
)

// ecdsaSignature is a helper struct to marshal the R and S values of an
// ECDSA signature into an ASN.1 DER-encoded sequence.
type ecdsaSignature struct {
	R, S *big.Int
}

// SignString signs a byte slice using the given ECDSA private key.
// It first hashes the data with SHA256 and then signs the resulting hash.
// The signature is returned as a DER-encoded ASN.1 byte slice, which is a
// common format compatible with many cryptographic libraries (like Python's).
func SignString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Step 1: Hash the data using SHA256.
	// The ecdsa.Sign function in Go expects a hash of the data, not the raw data itself.
	hash := sha256.Sum256(data)

	// Step 2: Sign the hash with the private key.
	// rand.Reader is a cryptographically secure source of randomness.
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign data: %w", err)
	}

	// Step 3: Marshal the R and S signature components into a DER-encoded ASN.1 structure.
	// This creates a single byte slice that represents the signature.
	signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, fmt.Errorf("failed to marshal signature: %w", err)
	}

	return signature, nil
}

// main function demonstrates how to use the SignString function.
func main() {
	// Generate a new private key using the P-384 curve (equivalent to SECP384R1).
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The data to be signed.
	data := []byte("this is some data I'd like to sign")

	// Call the function to sign the data.
	signature, err := SignString(privateKey, data)
	if err != nil {
		log.Fatalf("Failed to sign string: %v", err)
	}

	fmt.Printf("Original Data: %s\n", data)
	fmt.Printf("Generated Signature (DER-encoded): %x\n", signature)

	// --- Verification (for demonstration) ---
	// To verify the signature, we need the public key and the original data hash.
	publicKey := &privateKey.PublicKey
	hash := sha256.Sum256(data)

	// The ecdsa.Verify function needs the R and S values, so we unmarshal the signature.
	var sig ecdsaSignature
	_, err = asn1.Unmarshal(signature, &sig)
	if err != nil {
		log.Fatalf("Failed to unmarshal signature: %v", err)
	}

	// Verify the signature.
	isValid := ecdsa.Verify(publicKey, hash[:], sig.R, sig.S)
	if isValid {
		fmt.Println("Signature verification successful.")
	} else {
		fmt.Println("Signature verification failed.")
	}
}