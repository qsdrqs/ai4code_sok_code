package ecdsautil

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// SignMessage signs the given message with the supplied ECDSA private key
// and returns the DER-encoded signature.
//
// • message – arbitrary data to be signed
// • privKey – ECDSA private key to sign with
//
// The function first hashes the message (ECDSA signs a hash, not the raw
// message), then produces a DER-encoded signature that can be verified with
// ecdsa.VerifyASN1.
func SignMessage(message []byte, privKey *ecdsa.PrivateKey) ([]byte, error) {
	// 1. Hash the message (SHA-256 in this example; use whatever your system requires).
	digest := sha256.Sum256(message)

	// 2. Sign the hash.
	sig, err := ecdsa.SignASN1(rand.Reader, privKey, digest[:])
	if err != nil {
		return nil, err
	}

	// 3. Return the signature bytes.
	return sig, nil
}