package ecdsautil

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// Sign signs the supplied message with the given ECDSA private key using SHA-256.
// It returns the signature encoded in ASN.1 DER format (the same encoding returned
// by Python's cryptography library).
func Sign(message []byte, privKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message with SHA-256.
	hash := sha256.Sum256(message)

	// Produce the ECDSA signature (r, s).
	r, s, err := ecdsa.Sign(rand.Reader, privKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Encode (r, s) to ASN.1 DER so the output layout matches Python's .sign().
	type ecdsaSignature struct {
		R, S *big.Int
	}

	return asn1.Marshal(ecdsaSignature{R: r, S: s})
}