package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(msg []byte, key *ecdsa.PrivateKey) []byte {
	// Hash the message
	hash := sha256.Sum256(msg)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		log.Fatal(err)
	}
	
	// Verify the signature
	vk := &key.PublicKey
	if !ecdsa.Verify(vk, hash[:], r, s) {
		log.Fatal("Signature verification failed")
	}
	
	// Convert signature to bytes (simple concatenation of r and s)
	signature := append(r.Bytes(), s.Bytes()...)
	return signature
}

func test() {
	// Generate a new private key
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}
	
	message := []byte("message")
	signature := sign(message, sk)
	fmt.Printf("%x\n", signature)
}

func main() {
	test()
}