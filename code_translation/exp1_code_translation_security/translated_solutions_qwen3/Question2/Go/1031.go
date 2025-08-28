package main

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
)

// sign signs a message using RSA with PKCS1_v1_5 padding and SHA256 hashing.
// It returns the signature or an error if the signing fails.
func sign(msg []byte, privKey *rsa.PrivateKey) ([]byte, error) {
	// Compute SHA-256 hash of the message
	digest := sha256.Sum256(msg)

	// Sign the hash using PKCS1_v1_5 padding
	signature, err := rsa.SignPKCS1v15(rand.Reader, privKey, crypto.SHA256, digest[:])
	if err != nil {
		return nil, err
	}

	return signature, nil
}