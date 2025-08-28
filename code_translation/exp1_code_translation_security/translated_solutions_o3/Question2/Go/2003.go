package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"log"
	"math/big"
)

// ecdsaSignature is used only for ASN.1 DER encoding of (r,s).
type ecdsaSignature struct {
	R, S *big.Int
}

// sign signs the given text with the provided ECDSA private key
// and returns a DER-encoded (r,s) signature.
func sign(text string, key *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message first (ECDSA always signs a hash, not raw bytes).
	digest := sha256.Sum256([]byte(text))

	r, s, err := ecdsa.Sign(rand.Reader, key, digest[:])
	if err != nil {
		return nil, err
	}

	// Convert (r,s) into the same DER representation
	// that Python-ecdsa returns by default.
	return asn1.Marshal(ecdsaSignature{R: r, S: s})
}

func main() {
	// Generate a new ECDSA private key (P-256 curve).
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	sig, err := sign("Hello world", sk)
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}

	// Print the signature as hex, similar to Python’s raw-bytes output.
	fmt.Printf("%x\n", sig)
}