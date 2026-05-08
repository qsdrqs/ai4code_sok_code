package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"

	"github.com/btcsuite/btcd/btcec/v2"
)

// SignMessage signs a given message using ECDSA signing key with SECP256k1 curve.
// message: message to be signed
// privateKey: private key bytes (32 bytes) to sign the message
// Returns: signature of the message (64 bytes: r || s concatenation) and error if any
func SignMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Create signing key from private key bytes using SECP256k1 curve
	priv, _ := btcec.PrivKeyFromBytes(privateKey)

	// Hash the message using SHA-1 (default hash function in Python's ecdsa library)
	hash := sha1.Sum(message)

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, priv.ToECDSA(), hash[:])
	if err != nil {
		return nil, err
	}

	// Serialize signature as concatenation of r and s (each padded to 32 bytes)
	// This matches Python ecdsa library's default sigencode_string format
	signature := make([]byte, 64)
	r.FillBytes(signature[:32])
	s.FillBytes(signature[32:])

	return signature, nil
}