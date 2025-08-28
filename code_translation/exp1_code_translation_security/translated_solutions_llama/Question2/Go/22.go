package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"log"
)

func myFunction(message []byte) ([]byte, error) {
	// Generate a new ECDSA private key with SECP256k1 curve
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		return nil, err
	}

	// Sign the message
	sig, err := sk.Sign(rand.Reader, message, nil)
	if err != nil {
		return nil, err
	}

	return sig, nil
}

func main() {
	message := []byte("Hello, World!")
	sig, err := myFunction(message)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Signature (hex):", hex.EncodeToString(sig))
}