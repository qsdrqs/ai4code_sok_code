package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"math/big"
)

// signNIST256 signs a message using ECDSA with P-256 curve and SHA-256 hash
func signNIST256(message []byte, signingKeyRaw []byte) ([]byte, error) {
	// Convert raw private key bytes to ecdsa.PrivateKey
	privateKey := &ecdsa.PrivateKey{}
	privateKey.Curve = elliptic.P256()
	privateKey.D = new(big.Int).SetBytes(signingKeyRaw)
	privateKey.PublicKey.X, privateKey.PublicKey.Y = privateKey.Curve.ScalarBaseMult(signingKeyRaw)

	// Hash the message with SHA-256
	hash := sha256.Sum256(message)

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Encode signature as concatenated r||s (32 bytes each for P-256)
	signature := make([]byte, 64)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Pad with leading zeros if necessary and copy to signature buffer
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):], sBytes)

	return signature, nil
}

// verifyNIST256 verifies a signature using ECDSA with P-256 curve and SHA-256 hash
func verifyNIST256(message []byte, signature []byte, verifyingKeyRaw []byte) bool {
	// Signature should be 64 bytes (32 for r, 32 for s)
	if len(signature) != 64 {
		return false
	}

	// Extract r and s from signature
	r := new(big.Int).SetBytes(signature[:32])
	s := new(big.Int).SetBytes(signature[32:])

	// Convert raw public key bytes to ecdsa.PublicKey
	// Assuming uncompressed format: 0x04 || x || y (65 bytes total)
	// or compressed format handling
	publicKey := &ecdsa.PublicKey{}
	publicKey.Curve = elliptic.P256()

	if len(verifyingKeyRaw) == 64 {
		// Raw format: x || y (32 bytes each)
		publicKey.X = new(big.Int).SetBytes(verifyingKeyRaw[:32])
		publicKey.Y = new(big.Int).SetBytes(verifyingKeyRaw[32:])
	} else if len(verifyingKeyRaw) == 65 && verifyingKeyRaw[0] == 0x04 {
		// Uncompressed format: 0x04 || x || y
		publicKey.X = new(big.Int).SetBytes(verifyingKeyRaw[1:33])
		publicKey.Y = new(big.Int).SetBytes(verifyingKeyRaw[33:])
	} else {
		return false
	}

	// Hash the message with SHA-256
	hash := sha256.Sum256(message)

	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], r, s)
}