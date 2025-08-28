// Package provides functions that sign a given message using a given ECDSA signing key
package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// ECDSASignature represents the ASN.1 DER structure for ECDSA signatures
type ECDSASignature struct {
	R, S *big.Int
}

// SignMessage signs a given message using a given ECDSA signing key
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256(message)

	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, signingKey, messageHash[:])
	if err != nil {
		return nil, err
	}

	// Encode signature in DER format (equivalent to sigencode_der)
	signature := ECDSASignature{R: r, S: s}
	derSignature, err := asn1.Marshal(signature)
	if err != nil {
		return nil, err
	}

	return derSignature, nil
}