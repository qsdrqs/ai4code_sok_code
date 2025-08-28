package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// signMessage has the same behaviour as the original Python version:
// regardless of the value passed in `message`, it signs the literal
// string "message" with the given ECDSA private key.
func signMessage(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// The Python code signs the hard-coded value b"message".
	dataToSign := []byte("message")

	// ECDSA in Go expects a hash of the data, not the raw data itself.
	hash := sha256.Sum256(dataToSign)

	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}

	// Pack (r, s) into a single byte slice similar to Python-ecdsa’s default DER form.
	return asn1.Marshal(struct {
		R, S *big.Int
	}{r, s})
}