package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
	"math/big"
)

func sign(msg string, privateKey *ecdsa.PrivateKey) ([]byte, []byte, error) {
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, []byte(msg))
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func main() {
	// Generate a new ECDSA private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	// Message to sign
	message := "message"

	// Sign the message
	r, s, err := sign(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature (r, s): %x, %x\n", r, s)
}