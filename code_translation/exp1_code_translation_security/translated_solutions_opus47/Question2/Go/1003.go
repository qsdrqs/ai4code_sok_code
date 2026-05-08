package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"errors"
	"math/big"
)

func SignNIST256(message, signingKeyRaw []byte) ([]byte, error) {
	if len(signingKeyRaw) != 32 {
		return nil, errors.New("invalid signing key length; expected 32 bytes")
	}

	curve := elliptic.P256()

	// Parse the private key from raw bytes (32-byte big-endian scalar)
	d := new(big.Int).SetBytes(signingKeyRaw)

	// Derive the public key from the private key
	x, y := curve.ScalarBaseMult(signingKeyRaw)

	privKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
			X:     x,
			Y:     y,
		},
		D: d,
	}

	// Hash the message with SHA-256
	hash := sha256.Sum256(message)

	// Sign
	r, s, err := ecdsa.Sign(rand.Reader, privKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Encode signature in raw format (r||s, 32 bytes each) to match
	// python-ecdsa's default output.
	signature := make([]byte, 64)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):64], sBytes)

	return signature, nil
}

func VerifyNIST256(message, signature, verifyingKeyRaw []byte) bool {
	if len(signature) != 64 {
		return false
	}
	if len(verifyingKeyRaw) != 64 {
		return false
	}

	curve := elliptic.P256()

	// Parse the public key from raw bytes (X || Y, 32 bytes each, no 0x04 prefix)
	x := new(big.Int).SetBytes(verifyingKeyRaw[:32])
	y := new(big.Int).SetBytes(verifyingKeyRaw[32:])

	if !curve.IsOnCurve(x, y) {
		return false
	}

	pubKey := &ecdsa.PublicKey{
		Curve: curve,
		X:     x,
		Y:     y,
	}

	// Parse signature
	r := new(big.Int).SetBytes(signature[:32])
	s := new(big.Int).SetBytes(signature[32:])

	// Hash the message with SHA-256
	hash := sha256.Sum256(message)

	// Verify; returns false on bad signature (equivalent to Python's BadSignatureError)
	return ecdsa.Verify(pubKey, hash[:], r, s)
}