package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"errors"
	"fmt"
	"math/big"
)

// SignNist256 signs a message using a raw P-256 private key.
// It mimics the behavior of python-ecdsa's default signing, which uses SHA-256
// and produces a 64-byte signature by concatenating r and s.
//
// Parameters:
//   message: The message to be signed.
//   signingKeyRaw: A 32-byte slice representing the raw private key scalar.
//
// Returns:
//   A 64-byte signature ([r || s]) or an error if signing fails.
func SignNist256(message []byte, signingKeyRaw []byte) ([]byte, error) {
	// The python-ecdsa library expects a 32-byte raw private key for NIST256p.
	if len(signingKeyRaw) != 32 {
		return nil, errors.New("invalid raw private key length, expected 32 bytes")
	}

	// Get the NIST P-256 curve.
	curve := elliptic.P256()

	// In Go, an ecdsa.PrivateKey includes the public key. We can derive the public
	// key directly from the private key scalar and the curve.
	priv := new(ecdsa.PrivateKey)
	priv.D = new(big.Int).SetBytes(signingKeyRaw)
	priv.PublicKey.Curve = curve
	priv.PublicKey.X, priv.PublicKey.Y = curve.ScalarBaseMult(signingKeyRaw)

	// Hash the message with SHA-256, as specified by the hashfunc in the Python code.
	hash := sha256.Sum256(message)

	// Sign the hash. ecdsa.Sign returns the signature as two big.Ints (r, s).
	r, s, err := ecdsa.Sign(rand.Reader, priv, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The python-ecdsa library by default concatenates r and s to form the signature.
	// For P-256, each value is 32 bytes, making the total signature 64 bytes.
	// We use FillBytes to ensure each part is padded to 32 bytes.
	curveOrderByteSize := curve.Params().P.BitLen() / 8
	signature := make([]byte, 2*curveOrderByteSize)
	r.FillBytes(signature[:curveOrderByteSize])
	s.FillBytes(signature[curveOrderByteSize:])

	return signature, nil
}

// VerifyNist256 verifies a signature against a message using a raw P-256 public key.
// It mimics the behavior of python-ecdsa's verification, returning false on any error.
//
// Parameters:
//   message: The original message that was signed.
//   signature: The 64-byte signature to verify ([r || s]).
//   verifyingKeyRaw: The raw public key in uncompressed format (65 bytes: 0x04 || X || Y).
//
// Returns:
//   True if the signature is valid, False otherwise.
func VerifyNist256(message []byte, signature []byte, verifyingKeyRaw []byte) bool {
	// Get the NIST P-256 curve.
	curve := elliptic.P256()
	curveOrderByteSize := curve.Params().P.BitLen() / 8

	// Validate input lengths to prevent panics and handle invalid data gracefully.
	// Signature should be 2 * 32 = 64 bytes.
	if len(signature) != 2*curveOrderByteSize {
		return false
	}
	// Uncompressed public key should be 1 (prefix) + 2 * 32 = 65 bytes.
	if len(verifyingKeyRaw) != 1+2*curveOrderByteSize {
		return false
	}

	// Parse the public key from its raw uncompressed format.
	// elliptic.Unmarshal returns the (X, Y) coordinates.
	x, y := elliptic.Unmarshal(curve, verifyingKeyRaw)
	if x == nil {
		// Unmarshal returns x=nil on error (e.g., invalid format or point not on curve).
		return false
	}
	pubKey := &ecdsa.PublicKey{Curve: curve, X: x, Y: y}

	// Split the 64-byte signature into its r and s components.
	r := new(big.Int).SetBytes(signature[:curveOrderByteSize])
	s := new(big.Int).SetBytes(signature[curveOrderByteSize:])

	// Hash the message with SHA-256.
	hash := sha256.Sum256(message)

	// Verify the signature against the hash.
	// ecdsa.Verify returns a bool, which is exactly what we need.
	return ecdsa.Verify(pubKey, hash[:], r, s)
}