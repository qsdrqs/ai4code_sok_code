package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
	"math/big"
)

func sign(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, []byte, error) {
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, message)
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func main() {
	// Generate a new private key using the P-192 curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	message := []byte("i am a message")
	r, s, err := sign(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: (r: %x, s: %x)\n", r, s)
}