package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

// sign signs the UTF-8 encoded input string using the provided ECDSA private key.
func sign(inp string, key *ecdsa.PrivateKey) []byte {
	// Hash the input using SHA-256
	hash := sha256.Sum256([]byte(inp))

	// Sign the hash using the private key
	signature, _ := key.Sign(rand.Reader, hash[:], nil)
	return signature
}

func main() {
	// Generate a new ECDSA private key using the P-256 curve
	sk, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)

	// Sign the message "Hello world"
	sig := sign("Hello world", sk)

	// Print the signature (as a slice of bytes)
	fmt.Println(sig)
}