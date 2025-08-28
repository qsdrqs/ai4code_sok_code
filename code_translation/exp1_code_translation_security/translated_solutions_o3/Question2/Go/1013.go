package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"math/big"
)

// sign signs a message using the given ECDSA private key.
// It returns a DER-encoded (r, s) signature.
func sign(message string, key *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message; ECDSA operates on a hash, not raw bytes.
	hash := sha256.Sum256([]byte(message))

	// Create the signature.
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}

	// Marshal (r, s) into ASN.1 DER (common wire format for ECDSA signatures).
	type ecdsaSignature struct {
		R, S *big.Int
	}
	return asn1.Marshal(ecdsaSignature{R: r, S: s})
}

func main() {
	// Generate a new key on the NIST-P192 curve.
	key, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		panic(err)
	}

	// Sign the message and print the resulting signature.
	sig, err := sign("something", key)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Signature: %x\n", sig)
}