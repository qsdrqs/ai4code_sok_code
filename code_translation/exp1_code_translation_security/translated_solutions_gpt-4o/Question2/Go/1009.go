package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(sk *ecdsa.PrivateKey, message []byte) ([]byte, []byte, error) {
	hash := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func verify(vk *ecdsa.PublicKey, message, rBytes, sBytes []byte) bool {
	hash := sha256.Sum256(message)
	var r, s = new(big.Int), new(big.Int)
	r.SetBytes(rBytes)
	s.SetBytes(sBytes)
	return ecdsa.Verify(vk, hash[:], r, s)
}

func main() {
	message := []byte("hehehe")

	// Generate a private key using the P-384 curve
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Sign the message
	r, s, err := sign(sk, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Verify the signature
	valid := verify(&sk.PublicKey, message, r, s)
	fmt.Println(valid)
}