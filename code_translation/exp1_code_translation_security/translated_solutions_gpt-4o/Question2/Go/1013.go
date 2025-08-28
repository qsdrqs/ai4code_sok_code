package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func sign(message string, privateKey *ecdsa.PrivateKey) ([]byte, []byte, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, nil, err
	}

	return r.Bytes(), s.Bytes(), nil
}

func main() {
	// Generate a private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	// Sign the message
	r, s, err := sign("something", privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: (r: %x, s: %x)\n", r, s)
}