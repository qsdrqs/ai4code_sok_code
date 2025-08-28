package cryptohelpers

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
)

// Sign signs msg with privKey using SHA-256 and PKCS#1 v1.5.
// It returns the raw signature bytes or an error.
func Sign(msg []byte, privKey *rsa.PrivateKey) ([]byte, error) {
	// Hash the message.
	digest := sha256.Sum256(msg)

	// Perform PKCS#1 v1.5 signing.
	sig, err := rsa.SignPKCS1v15(rand.Reader, privKey, crypto.SHA256, digest[:])
	if err != nil {
		return nil, err
	}
	return sig, nil
}