package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"math/big"
)

// ecdsaSignature is a helper struct to marshal the R and S values of an
// ECDSA signature into an ASN.1 DER-encoded sequence.
type ecdsaSignature struct {
	R, S *big.Int
}

// SignMessage signs a given message using a given ECDSA signing key.
// It replicates the Python function's behavior by first hashing the message
// with SHA-256 and then signing the hash. The resulting signature is
// returned in DER format.
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message using SHA-256
	messageHash := sha256.Sum256(message)

	// Sign the message hash.
	// ecdsa.Sign returns the R and S components of the signature.
	r, s, err := ecdsa.Sign(rand.Reader, signingKey, messageHash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message hash: %w", err)
	}

	// Encode the R and S values into a DER-encoded ASN.1 sequence,
	// which is a standard format for ECDSA signatures.
	signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, fmt.Errorf("failed to marshal signature to DER format: %w", err)
	}

	return signature, nil
}