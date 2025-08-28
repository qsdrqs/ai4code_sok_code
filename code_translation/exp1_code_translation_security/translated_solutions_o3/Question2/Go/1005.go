package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"encoding/asn1"
	"fmt"
	"log"
)

// asn1Signature is the DER structure that will hold the ECDSA
// (r,s) pair.  Python-ecdsa’s default .sign() method also returns a
// DER-encoded structure, so we mimic that here.
type asn1Signature struct {
	R, S *big.Int
}

// sign is the Go equivalent of the Python `sign` function.
//
// It receives the message to be signed and an ECDSA private key,
// returns a DER-encoded signature and an error (if any).
func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message the same way the python-ecdsa default does (SHA-1).
	digest := sha1.Sum(msg)

	// Produce the signature (r,s) pair.
	r, s, err := ecdsa.Sign(rand.Reader, key, digest[:])
	if err != nil {
		return nil, err
	}

	// “Assert” that the signature verifies.
	if !ecdsa.Verify(&key.PublicKey, digest[:], r, s) {
		return nil, fmt.Errorf("signature verification failed")
	}

	// Marshal (r,s) into DER.
	sig, err := asn1.Marshal(asn1Signature{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return sig, nil
}

func test() {
	// Generate a fresh private key (curve defaults to P-256 here).
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	sig, err := sign([]byte("message"), sk)
	if err != nil {
		log.Fatalf("sign failed: %v", err)
	}

	fmt.Printf("signature: %x\n", sig)
}

func main() {
	test()
}