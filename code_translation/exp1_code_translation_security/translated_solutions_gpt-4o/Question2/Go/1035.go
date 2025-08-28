package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(msg []byte, sk *ecdsa.PrivateKey) ([]byte, []byte, error) {
	hash := sha256.Sum256(msg)
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func verify(msg []byte, rBytes, sBytes []byte, vk *ecdsa.PublicKey) bool {
	hash := sha256.Sum256(msg)
	var r, s = new(big.Int), new(big.Int)
	r.SetBytes(rBytes)
	s.SetBytes(sBytes)
	return ecdsa.Verify(vk, hash[:], r, s)
}

func main() {
	// Generate a new private key using the P-192 curve
	sk, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	vk := &sk.PublicKey

	// Sign the message
	msg := []byte("message")
	r, s, err := sign(msg, sk)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Verify the signature
	if verify(msg, r, s, vk) {
		fmt.Println("Signature verified successfully")
	} else {
		fmt.Println("Failed to verify signature")
	}
}