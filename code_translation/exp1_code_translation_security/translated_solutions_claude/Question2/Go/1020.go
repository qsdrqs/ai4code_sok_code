package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func main() {
	// Generate a private key using SECP256k1 curve (equivalent to secp256k1)
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}
	
	// Get the public key (verifying key)
	publicKey := &privateKey.PublicKey
	
	// Call the sign_message function
	result := signMessage(privateKey, publicKey, "..")
	fmt.Println(result)
}

func signMessage(privateKey *ecdsa.PrivateKey, publicKey *ecdsa.PublicKey, message string) bool {
	// Hash the message (ECDSA typically works with hashed messages)
	hash := sha256.Sum256([]byte(message))
	
	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		log.Fatal(err)
	}
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], r, s)
}