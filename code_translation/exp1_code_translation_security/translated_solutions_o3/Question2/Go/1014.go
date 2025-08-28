// Package signer contains helpers for ECDSA signing.
package signer

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// SignMessage signs an arbitrary byte slice with an ECDSA private key and
// returns a DER-encoded signature (r||s), identical to the behaviour of
// ecdsa.util.sigencode_der in the original Python code.
//
// Params
//   message []byte           – message to be signed
//   privKey *ecdsa.PrivateKey – key to sign with
//
// Returns
//   []byte – DER-encoded signature
//   error  – any error that occurred while hashing, signing, or encoding
func SignMessage(message []byte, privKey *ecdsa.PrivateKey) ([]byte, error) {
	// 1. Hash the message using SHA-256 (same as hashlib.sha256 in Python).
	hash := sha256.Sum256(message)

	// 2. Sign the digest.
	r, s, err := ecdsa.Sign(rand.Reader, privKey, hash[:])
	if err != nil {
		return nil, err
	}

	// 3. Encode the (r, s) pair to ASN.1 DER to match Python's `sigencode_der`.
	type ecdsaSignature struct {
		R, S *big.Int
	}
	der, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, err
	}
	return der, nil
}