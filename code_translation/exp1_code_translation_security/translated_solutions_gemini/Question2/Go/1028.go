package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// Sign signs a given message using an ECDSA private key.
// It first hashes the message using SHA-256 and then signs the hash.
//
// Parameters:
//   privateKey: The ECDSA private key (*ecdsa.PrivateKey) for signing.
//   message:    The message to be signed ([]byte).
//
// Returns:
//   A DER-encoded ASN.1 signature ([]byte) and an error if signing fails.
func Sign(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// The message must be hashed before signing. SHA-256 is a common choice.
	hash := sha256.Sum256(message)

	// Sign the hash of the message using the private key.
	// ecdsa.SignASN1 is convenient as it returns a single byte slice.
	return ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
}

// Verify verifies a given signature using an ECDSA public key.
// It first hashes the message using SHA-256 and then verifies the signature against the hash.
//
// Parameters:
//   publicKey: The ECDSA public key (*ecdsa.PublicKey) for verification.
//   message:   The original message that was signed ([]byte).
//   signature: The DER-encoded ASN.1 signature to be verified ([]byte).
//
// Returns:
//   True if the signature is valid, False otherwise.
func Verify(publicKey *ecdsa.PublicKey, message []byte, signature []byte) bool {
	// The message must be hashed with the same algorithm used for signing.
	hash := sha256.Sum256(message)

	// Verify the signature against the hash of the message using the public key.
	return ecdsa.VerifyASN1(publicKey, hash[:], signature)
}