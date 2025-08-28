package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
	"encoding/asn1"
	"math/big"
)

// ecdsaSignature is a helper struct for ASN.1 DER encoding of ECDSA signatures.
type ecdsaSignature struct {
	R, S *big.Int
}

// signMessage signs the given message using the provided ECDSA private key.
// It returns the signature in DER-encoded ASN.1 format.
func signMessage(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// Step 1: Hash the message using SHA-1 (default in Python's ecdsa)
	hashed := sha1.Sum(message)

	// Step 2: Sign the hash using the ECDSA private key
	r, s, err := ecdsa.Sign(rand.Reader, key, hashed[:])
	if err != nil {
		return nil, err
	}

	// Step 3: Marshal the signature into DER-encoded ASN.1 format
	sig := ecdsaSignature{R: r, S: s}
	asn1Sig, err := asn1.Marshal(sig)
	if err != nil {
		return nil, err
	}

	return asn1Sig, nil
}