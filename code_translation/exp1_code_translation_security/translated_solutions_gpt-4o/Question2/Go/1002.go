package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, []byte, error) {
	hash := sha256.Sum256(data)
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func test() {
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	data := []byte("this is some data I'd like to sign")
	r, s, err := sign(privateKey, data)
	if err != nil {
		log.Fatalf("Failed to sign data: %v", err)
	}

	fmt.Printf("Signature (r, s): (%x, %x)\n", r, s)
}

func main() {
	test()
}