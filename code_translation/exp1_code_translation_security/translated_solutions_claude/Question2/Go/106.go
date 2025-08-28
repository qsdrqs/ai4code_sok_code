package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// Signs a given message using a given ECDSA signing key
//
// Args:
//     message: input message
//     key: ECDSA private key used to sign the message
//
// Return:
//     r, s values of the signature and error if any
func sign(message []byte, key *ecdsa.PrivateKey) (r, s *big.Int, err error) {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Sign the hash
	return ecdsa.Sign(rand.Reader, key, hash[:])
}

// Verifies a signature against a message using the public key
func verify(r, s *big.Int, message []byte, publicKey *ecdsa.PublicKey) bool {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], r, s)
}

func main() {
	// Generate a new ECDSA private key using secp256r1 (P-256) curve
	// Note: Go's standard library uses P-256, not secp256k1
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	// Get the public key
	publicKey := &privateKey.PublicKey
	
	// Sign the message
	message := []byte("helloworld")
	r, s, err := sign(message, privateKey)
	if err != nil {
		panic(err)
	}
	
	// Verify the signature
	isValid := verify(r, s, message, publicKey)
	fmt.Println(isValid)
}