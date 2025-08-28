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

// signString signs the supplied data with the given ECDSA private key.
// The resulting signature is returned in ASN.1-DER format, matching the
// output of Python’s cryptography library.
func signString(privKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Hash the input with SHA-256 (equivalent to hashes.SHA256()).
	hash := sha256.Sum256(data)

	// Generate the signature (r, s) over the hash.
	r, s, err := ecdsa.Sign(rand.Reader, privKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Marshal (r, s) into ASN.1-DER so the byte layout matches Python’s.
	return asn1.Marshal(struct {
		R, S *big.Int
	}{r, s})
}

func main() {
	// Equivalent to ec.generate_private_key(ec.SECP384R1()) in Python
	privKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	data := []byte("hello world")

	sig, err := signString(privKey, data)
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}

	fmt.Printf("DER signature: %x\n", sig)
}