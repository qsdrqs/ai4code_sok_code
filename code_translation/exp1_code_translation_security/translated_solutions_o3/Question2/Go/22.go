package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1" // `ecdsa`-python defaults to SHA-1 for the digest
	"github.com/btcsuite/btcd/btcec/v2" // secp256k1 implementation
)

// myfunction generates a new secp256k1 key-pair and signs `message`.
// It returns the ASN.1-encoded signature and any error that occurred.
func myfunction(message []byte) ([]byte, error) {
	// 1. Create a private key on the secp256k1 curve.
	privKey, err := ecdsa.GenerateKey(btcec.S256(), rand.Reader)
	if err != nil {
		return nil, err
	}

	// 2. Hash the message (same default as python-ecdsa: SHA-1).
	digest := sha1.Sum(message)

	// 3. Sign the digest; result is ASN.1 DER encoded.
	sig, err := ecdsa.SignASN1(rand.Reader, privKey, digest[:])
	if err != nil {
		return nil, err
	}

	return sig, nil
}