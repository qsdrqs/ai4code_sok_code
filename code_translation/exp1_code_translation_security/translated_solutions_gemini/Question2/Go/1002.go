package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// sign generates an ECDSA signature for the given data using the provided private key.
// It first hashes the data with SHA-256 and then signs the hash.
// This is equivalent to Python's `key.sign(data, ec.ECDSA(hashes.SHA256()))`.
// The signature is returned in ASN.1 DER format.
func sign(key *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Hash the data using SHA-256. The ecdsa.SignASN1 function requires the hash of the data, not the data itself.
	hash := sha256.Sum256(data)

	// Sign the hash with the private key.
	// rand.Reader is a global, shared instance of a cryptographically
	// secure random number generator, used for generating the 'k' value in ECDSA.
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}
	return signature, nil
}

// test demonstrates the key generation and signing process.
func test() {
	// Generate a new private key using the P-384 curve.
	// This is the Go equivalent of Python's ec.SECP384R1().
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		// Use log.Fatalf to print the error and exit if key generation fails.
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The data to be signed, as a byte slice.
	data := []byte("this is some data I'd like to sign")

	// Call the sign function to generate the signature.
	signature, err := sign(privateKey, data)
	if err != nil {
		log.Fatalf("Failed to sign data: %v", err)
	}

	// The Python code prints the raw bytes of the signature.
	// In Go, we print the hexadecimal representation, which is more readable.
	fmt.Printf("Signature (hex): %x\n", signature)
}

func main() {
	test()
}