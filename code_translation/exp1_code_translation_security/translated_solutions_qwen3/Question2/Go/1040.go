package main

import (
	"crypto/sha1"
	"github.com/btcsuite/btcec"
)

// signMessage signs the given message using the provided private key (32-byte slice)
// and returns the DER-encoded ECDSA signature.
// The message is hashed using SHA1 before signing, as in the original Python code.
func signMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Parse the private key from the byte slice
	privKey, err := btcec.PrivKeyFromBytes(privateKey)
	if err != nil {
		return nil, err
	}

	// Hash the message using SHA1
	messageHash := sha1.Sum(message)

	// Sign the hash using the private key
	signature, err := privKey.Sign(messageHash[:])
	if err != nil {
		return nil, err
	}

	// Return the DER-encoded signature
	return signature.Serialize(), nil
}