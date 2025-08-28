package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// sign produces an ASN.1-encoded ECDSA signature for message using key.
func sign(message []byte, key *ecdsa.PrivateKey) []byte {
	digest := sha256.Sum256(message)

	// ecdsa.SignASN1 returns the DER/ASN.1 encoded form (one continuous []byte).
	sig, err := ecdsa.SignASN1(rand.Reader, key, digest[:])
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}
	return sig
}

func main() {
	// Equivalent to `SigningKey.generate()`; default curve in the
	// original Python snippet was NIST P-192.
	key, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	msg := []byte("i am a message")
	fmt.Printf("%x\n", sign(msg, key))
}