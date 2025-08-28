package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"encoding/asn1"
	"math/big"
)

// signMessage signs the given message using the provided ECDSA private key.
// It returns the DER-encoded signature as a byte slice.
func signMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Step 1: Sign the message using the ECDSA private key
	r, s, err := ecdsa.Sign(rand.Reader, signingKey, message)
	if err != nil {
		return nil, err
	}

	// Step 2: Define a struct to hold the R and S values for ASN.1 encoding
	type ecdsaSig struct {
		R, S *big.Int
	}

	// Step 3: Marshal the R and S values into a DER-encoded byte slice
	sig, err := asn1.Marshal(ecdsaSig{R: r, S: s})
	if err != nil {
		return nil, err
	}

	// Step 4: Return the DER-encoded signature
	return sig, nil
}