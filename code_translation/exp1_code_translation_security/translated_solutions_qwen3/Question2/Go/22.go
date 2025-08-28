package main

import (
	"crypto/sha256"
	"github.com/btcsuite/btcd/btcec"
)

// myfunction generates a SECP256k1 private key and signs the given message.
// It returns the DER-encoded signature and an error if any step fails.
func myfunction(message []byte) ([]byte, error) {
	// Generate a new private key using the SECP256k1 curve
	sk, err := btcec.NewPrivateKey(btcec.S256())
	if err != nil {
		return nil, err
	}

	// Hash the message using SHA-256
	hash := sha256.Sum256(message)

	// Sign the hash
	sig := sk.Sign(hash[:])

	// Return the DER-encoded signature
	return sig.Serialize(), nil
}