package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(key *ecdsa.PrivateKey, data []byte) []byte {
	// Hash the data using SHA256
	hash := sha256.Sum256(data)
	
	// Sign the hash
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		log.Fatal(err)
	}
	
	return signature
}

func test() {
	// Generate private key using P-384 curve (equivalent to SECP384R1)
	private_key, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}
	
	data := []byte("this is some data I'd like to sign")
	signature := sign(private_key, data)
	fmt.Printf("%x\n", signature)
}

func main() {
	test()
}