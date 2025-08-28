package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
	"log"
)

func sign(message string, key *ecdsa.PrivateKey) []byte {
	// Hash the message (ecdsa library in Python uses SHA-1 by default)
	hash := sha1.Sum([]byte(message))
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		log.Fatal(err)
	}
	
	// Combine r and s into a single byte slice (similar to Python's ecdsa output)
	signature := append(r.Bytes(), s.Bytes()...)
	return signature
}

func generateKey() *ecdsa.PrivateKey {
	// Generate a private key using P-192 curve (equivalent to NIST192p)
	privateKey, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}
	return privateKey
}

func main() {
	key := generateKey()
	signature := sign("something", key)
	fmt.Printf("%x\n", signature)
}