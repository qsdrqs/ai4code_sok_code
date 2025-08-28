package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256(msg)
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}

	signature := append(r.Bytes(), s.Bytes()...)
	if !ecdsa.Verify(&key.PublicKey, hash[:], r, s) {
		return nil, fmt.Errorf("failed to verify signature")
	}

	return signature, nil
}

func test() {
	// Generate a new ECDSA private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("failed to generate key: %v", err)
	}

	// Sign the message
	signature, err := sign([]byte("message"), privateKey)
	if err != nil {
		log.Fatalf("failed to sign message: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}

func main() {
	test()
}