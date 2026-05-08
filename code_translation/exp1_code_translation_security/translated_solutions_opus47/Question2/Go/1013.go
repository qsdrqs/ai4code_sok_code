package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(message string, key *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256([]byte(message))
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}
	return signature, nil
}

func main() {
	// Note: Go's standard library doesn't include NIST192p (P-192).
	// Using P-256 as a modern, widely-used alternative.
	key, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	signature, err := sign("something", key)
	if err != nil {
		fmt.Println("Error signing:", err)
		return
	}

	fmt.Println(signature)
}