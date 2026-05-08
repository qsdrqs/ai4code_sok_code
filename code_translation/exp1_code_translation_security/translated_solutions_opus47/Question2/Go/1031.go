package main

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
)

// sign signs a message using RSA PKCS1 v1.5 with SHA256
func sign(msg []byte, privKey *rsa.PrivateKey) ([]byte, error) {
	// Compute SHA256 digest of the message
	digest := sha256.Sum256(msg)

	// Sign the digest using PKCS1 v1.5
	signature, err := rsa.SignPKCS1v15(rand.Reader, privKey, crypto.SHA256, digest[:])
	if err != nil {
		return nil, err
	}
	return signature, nil
}