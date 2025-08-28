package main

import (
	"crypto/ecdsa"
	"crypto/rand"
)

// sign signs the *fixed* message `"message"` with the given ECDSA private key.
// It emulates the original Python behavior, which ignores the incoming msg
// parameter and always signs the byte‐slice literal b"message".
func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// NOTE: The original Python code always signs b"message", regardless of the
	// msg argument. We keep the same semantics here.
	fixedMsg := []byte("message")

	// crypto/ecdsa has a helper that returns a DER-encoded ASN.1 signature.
	sig, err := ecdsa.SignASN1(rand.Reader, key, fixedMsg)
	if err != nil {
		return nil, err
	}
	return sig, nil
}