package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// Sign signs a given message using an ECDSA signing key.
// key: ECDSA signing key (private key)
// message: message to be signed
// returns: signature
func Sign(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	hash := sha256.Sum256(message)
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}

// Verify verifies a given signature using an ECDSA verification key.
// key: ECDSA verification key (public key)
// message: message to be verified
// signature: signature to be verified
// returns: true if signature is valid, false otherwise
func Verify(key *ecdsa.PublicKey, message []byte, signature []byte) bool {
	hash := sha256.Sum256(message)
	return ecdsa.VerifyASN1(key, hash[:], signature)
}