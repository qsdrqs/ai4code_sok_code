package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"

	"github.com/btcsuite/btcd/btcec/v2"
)

// Sign signs a given message using a given ECDSA signing key.
func Sign(message []byte, privateKey []byte) ([]byte, error) {
	// Create a new ECDSA signing key from the private key bytes (SECP256k1 curve)
	privKey, _ := btcec.PrivKeyFromBytes(privateKey)

	// Hash the message using SHA-1 (matching Python's ecdsa library default hashfunc)
	hash := sha1.Sum(message)

	// Sign the message using the ECDSA signing key
	r, s, err := ecdsa.Sign(rand.Reader, privKey.ToECDSA(), hash[:])
	if err != nil {
		return nil, err
	}

	// Encode signature as r || s (32 bytes each), matching Python's sigencode_string default
	signature := make([]byte, 64)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):], sBytes)

	// Return the signed message
	return signature, nil
}