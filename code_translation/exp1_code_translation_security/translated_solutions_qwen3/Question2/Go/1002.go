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

// sign signs the given data using the provided ECDSA private key with SHA-256.
// It returns the DER-encoded signature as a byte slice.
func sign(key *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Step 1: Hash the input data using SHA-256
	hash := sha256.Sum256(data)

	// Step 2: Sign the hash using ECDSA
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}

	// Step 3: Marshal the signature into DER format using ASN.1
	sig := struct {
		R, S *big.Int
	}{
		R: r,
		S: s,
	}

	return asn1.Marshal(sig)
}

// test generates a private key, signs a sample message, and prints the signature.
func test() {
	// Step 1: Generate a private key using the SECP384R1 curve
	privKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Step 2: Define the data to be signed
	data := []byte("this is some data I'd like to sign")

	// Step 3: Sign the data
	sig, err := sign(privKey, data)
	if err != nil {
		log.Fatalf("Failed to sign data: %v", err)
	}

	// Step 4: Print the DER-encoded signature
	fmt.Println(sig)
}

// main runs the test function
func main() {
	test()
}