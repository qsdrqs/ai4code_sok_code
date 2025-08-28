package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
	"log"
)

// signMessage produces a DER-encoded ECDSA signature for the given message.
func signMessage(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	hash := sha1.Sum(message)                    // Same default hash as the Python ecdsa lib
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}

func main() {
	// Generate a P-384 (NIST384p) key pair.
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("failed to generate key: %v", err)
	}

	msg := []byte("testing")

	// Sign the message.
	signature, err := signMessage(sk, msg)
	if err != nil {
		log.Fatalf("failed to sign message: %v", err)
	}

	// Verify the signature.
	hash := sha1.Sum(msg)
	valid := ecdsa.VerifyASN1(&sk.PublicKey, hash[:], signature)

	fmt.Println(valid) // Should print: true
}