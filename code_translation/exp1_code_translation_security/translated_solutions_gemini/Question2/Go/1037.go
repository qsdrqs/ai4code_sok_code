package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
	"log"
	"math/big"
)

// main is the entry point of the program.
func main() {
	// Equivalent to Python's: sk = SigningKey.generate(curve=NIST384p)
	// We generate a new private key using the P-384 elliptic curve.
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		// Use log.Fatal to print the error and exit if key generation fails.
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Equivalent to Python's: vk = sk.verifying_key
	// The public key is derived from the private key.
	publicKey := &privateKey.PublicKey

	message := []byte("testing")

	// Equivalent to Python's: signature = signMessage(sk, b"testing")
	// We call our helper function to sign the message.
	// Note: The Go standard library represents the signature as two integers (r and s),
	// unlike the Python library which returns a single byte string.
	r, s, err := signMessage(privateKey, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Equivalent to Python's: print(vk.verify(signature, b"testing"))
	// To verify, we must use the same hashing algorithm on the original message.
	hasher := sha512.New384()
	hasher.Write(message)
	hash := hasher.Sum(nil)

	// Verify the signature against the hash using the public key.
	isValid := ecdsa.Verify(publicKey, hash, r, s)

	// Print the boolean result of the verification.
	fmt.Println(isValid)
}

// signMessage signs a message using the given private key.
// It first hashes the message with SHA-384, which is appropriate for the P-384 curve,
// and then signs the hash.
// It returns the two components of the signature, r and s, and an error.
func signMessage(key *ecdsa.PrivateKey, message []byte) (r, s *big.Int, err error) {
	// The Go crypto/ecdsa library signs a hash of the data, not the data itself.
	// This is a security best practice. The Python ecdsa library does this implicitly.
	hasher := sha512.New384()
	hasher.Write(message)
	hash := hasher.Sum(nil)

	// Sign the hash of the message.
	r, s, err = ecdsa.Sign(rand.Reader, key, hash)
	if err != nil {
		return nil, nil, err
	}
	return r, s, nil
}