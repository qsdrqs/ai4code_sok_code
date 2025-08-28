package main

import (
	"crypto/ecdsa"
	"crypto/sha256"
	"encoding/asn1"
	"io"
	"math/big"
)

// Sign signs the given message using the provided ECDSA private key.
// It returns the DER-encoded ECDSA signature of the message.
func Sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Step 1: Compute the SHA-256 hash of the message
	hash := sha256.Sum256(message)

	// Step 2: Sign the hash using the ECDSA private key
	// The first argument is an io.Reader for entropy, but Go's ecdsa.Sign
	// uses RFC 6979 for deterministic signing, so we can pass nil.
	r, s, err := ecdsa.Sign(nil, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Step 3: Encode the signature in DER format using ASN.1
	// The ECDSA signature is a SEQUENCE of two INTEGERs: R and S
	sig, err := asn1.Marshal(struct {
		R, S *big.Int
	}{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return sig, nil
}